package com.jpmorgan.digital.service.impl;

import com.jpmorgan.digital.domain.ProductDomain;
import com.jpmorgan.digital.domain.SaleDomain;
import com.jpmorgan.digital.dto.MessageProcessedDTO;
import com.jpmorgan.digital.helper.LogHelper;
import com.jpmorgan.digital.helper.MessageProcessingHelper;
import com.jpmorgan.digital.model.ProductModel;
import com.jpmorgan.digital.service.MessageProcessingService;

import java.util.List;

/**
 * An implementation class of MessageProcessingService which provide the operation to parse various sale messages
 *
 * Different Sale Messages Allowed
 *
 * <ul>
 *     <li>Single Sale Message</li>
 *     <li>Multiple Sales Message</li>
 *     <li>Update Sales Message</li>
 * </ul>
 *
 *  @author Aman Chhabra
 *  @since 1.0
 */
public class MessageProcessingServiceImpl implements MessageProcessingService{

    /**
     * Counter to record the number of messages processed
     */
    private static int messageCounter;

    /**
     * Get instance of singleton ProductModel
     */
    private ProductModel productModel = ProductModel.getProductModelInstance();

    /**
     * Log ID to record and log all the modifications of sales
     */
    private static String LOG_MODIFICATION_ID = "Modfication";

    /**
     * Service function to process the message and store it in memory
     *
     * @param message Message to be processed
     * @throws IllegalAccessException When Service is paused
     * @throws IllegalArgumentException When invalid message is passed
     * @since 1.0
     */
    public void processMessage(String message) throws IllegalAccessException,IllegalArgumentException{
        messageCounter++;
        if(messageCounter >= 51) {
            throw new IllegalAccessException("Service is currently paused and not accepting any new messages");
        }
        if (message == null || message.isEmpty()) {
            throw new IllegalArgumentException("Message can not be empty");
        }
        MessageProcessedDTO messageProcessedDTO = MessageProcessingHelper.processMessage(message);
        if(messageProcessedDTO == null) {
            throw new IllegalArgumentException("Message Type not supported");
        }
        List<String> infoTokens = messageProcessedDTO.getInfoTokens();
        switch(messageProcessedDTO.getMessageType()) {
            case ONE_SALE:
                registerOneSale(infoTokens);
                break;
            case MULTIPLE_SALES:
                registerMultipleSales(infoTokens);
                break;
            case UPDATE_SALES:
                updateSales(infoTokens);
                break;
        }
        if(messageCounter%10 == 0) {
            logCurrentSaleData();
        }
        if(messageCounter == 50){
            LogHelper.logMessage("Service is pausing now and will not be accepting any new messages");
            LogHelper.logDetails(LOG_MODIFICATION_ID);
        }
    }

    /**
     * Function to current Sales data after every 10th message parsed
     *
     * @since 1.0
     */
    private void logCurrentSaleData(){
        String logId = "SaleDataAfter" + messageCounter;
        for(ProductDomain product:productModel.getProducts()){
            LogHelper.storeDetails(logId, "Product: " + product.getName() +
                    "  Value: " + product.getTotalValue() + " Sale:" + product.getTotalSales());
        }
        LogHelper.logDetails(logId);
    }

    /**
     * Function to update sales and apply below operations
     *
     * <ul>
     *     <li>Add</li>
     *     <li>Subtract</li>
     *     <li>Multiply</li>
     * </ul>
     *
     * @param infoTokens  Info data provided in the message
     * @since 1.0
     */
    private void updateSales(List<String> infoTokens) {
        if(infoTokens.size()<3) {
            throw new IllegalArgumentException("Insufficient information provided in message");
        }
        ProductModel.UpdateType updateType = ProductModel.UpdateType.valueOf(infoTokens.get(1).toUpperCase());
        if(updateType == null){
            throw new IllegalArgumentException("Provided update can not be implemented");
        }
        productModel.updateSales(updateType,
                Integer.parseInt(infoTokens.get(2)),infoTokens.get(3));
    }

    /**
     * Function to create and register multiple sale in the memory
     *
     * @param infoTokens Info data provided in the message
     * @since 1.0
     */
    private void registerMultipleSales(List<String> infoTokens) {
        if(infoTokens.size()<4) {
            throw new IllegalArgumentException("Insufficient information provided in message");
        }
        SaleDomain multipleSaleDomain = new SaleDomain();
        multipleSaleDomain.setNoOfOccurences(Integer.parseInt(
                infoTokens.get(1)
        ));
        ProductDomain product = productModel.getProduct(infoTokens.get(2));
        multipleSaleDomain.setProductType(product);
        multipleSaleDomain.setValue(Integer.parseInt(
                infoTokens.get(3)
        ));
        product.registerSale(multipleSaleDomain);
    }

    /**
     * Function to create and register single sale depending upon the info data provided
     *
     * @param infoTokens Info data provided in the message
     * @since 1.0
     */
    private void registerOneSale(List<String> infoTokens) {
        if(infoTokens.size()<2) {
            throw new IllegalArgumentException("Insufficient information provided in message");
        }
        SaleDomain saleDomain = new SaleDomain();
        ProductDomain product = productModel.getProduct(infoTokens.get(1));
        saleDomain.setProductType(product);
        saleDomain.setValue(Integer.parseInt(
                infoTokens.get(2)
        ));
        saleDomain.setNoOfOccurences(1);
        product.registerSale(saleDomain);
    }
}
