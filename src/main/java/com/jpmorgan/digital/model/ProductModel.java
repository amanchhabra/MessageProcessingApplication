package com.jpmorgan.digital.model;

import com.jpmorgan.digital.domain.ProductDomain;
import com.jpmorgan.digital.domain.SaleDomain;
import com.jpmorgan.digital.helper.LogHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Product Model to store all the products and provides operations to be operated on a group
 *
 *  @author Aman Chhabra
 *  @since 1.0
 */
public class ProductModel {

    /**
     * Map storing all Products as value corresponding to name as key
     */
    private Map<String,ProductDomain> productNameMap;

    /**
     * Variable to store only instance of the class
     */
    private static ProductModel productModelInstance;

    /**
     * Log ID to store and log all the modifications done on sales
     */
    private static String LOG_MODIFICATION_ID = "Modfication";

    /**
     * Enum for all the update types allowed
     */
    public enum UpdateType{
        ADD,
        SUBTRACT,
        MULTIPLY
    }

    /**
     * Private constructor to initialize map and also restrict the access to the class itself so as to implement
     * singleton behaviour
     */
    private ProductModel(){
        productNameMap = new HashMap<String, ProductDomain>();
    }

    /**
     * Function to return only instance of the class
     * @return productModelInstance  Instance of the class
     * @since 1.0
     */
    public static ProductModel getProductModelInstance(){
        if(productModelInstance == null) {
            productModelInstance = new ProductModel();
        }
        return productModelInstance;
    }

    /**
     * Function to return the product stored corresponding to the product name provided
     *
     * If product name do not exist it creates a new product, store it and then return it
     *
     * @param productName Name of the product
     *
     * @return Product Product corresponding to the provided domain
     * @since 1.0
     */
    public ProductDomain getProduct(String productName){
        if(productName == null || productName.isEmpty())
            throw new IllegalArgumentException("Product name can not be empty");
        String formattedProductName = productName.toUpperCase();
        if(!productNameMap.containsKey(formattedProductName)){
            productNameMap.put(formattedProductName,new ProductDomain(formattedProductName));
        }
        return productNameMap.get(formattedProductName);
    }

    /**
     * Function to return all the products stored in the memory
     *
     * @return Array of ProductDomain
     * @since 1.0
     */
    public ProductDomain[] getProducts(){
        return productNameMap.values().toArray(new ProductDomain[0]);
    }

    /**
     * Function to update all the sales corresponding to all the products stored in the memory
     *
     * Update type allowed:
     * <ul>
     *     <li>Add</li>
     *     <li>Subtract</li>
     *     <li>Multiply</li>
     * </ul>
     * @param type String Update type
     * @param changeValue int Change in value of sale
     * @param productType String Name of product
     * @since 1.0
     */
    public void updateSales(UpdateType type, int changeValue, String productType) {
        for(ProductDomain productDomain:productNameMap.values()){
            for(SaleDomain saleDomain : productDomain.getSales()){
                if(saleDomain.getProductType() == getProduct(productType)) {
                    int value = saleDomain.getValue();
                    switch (type) {
                        case ADD:
                            value += changeValue;
                            break;
                        case SUBTRACT:
                            value -= changeValue;
                            break;
                        case MULTIPLY:
                            value *= changeValue;
                            break;
                    }
                    LogHelper.storeDetails(LOG_MODIFICATION_ID,"Sale value of "+productDomain.getName()+
                            " product has been modified from "+saleDomain.getValue()+"p to "+value+"p ");
                    saleDomain.setValue(value);
                }
            }
        }
    }

}
