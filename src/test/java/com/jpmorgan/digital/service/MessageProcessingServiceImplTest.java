package com.jpmorgan.digital.service;

import com.jpmorgan.digital.model.ProductModel;
import com.jpmorgan.digital.service.impl.MessageProcessingServiceImpl;
import org.junit.Assert;
import org.junit.Test;

/**
 * A test class to verify all operations on <code>MessageProcessingServiceImpl</code>
 *
 * @author Aman Chhabra
 */
public class MessageProcessingServiceImplTest {

    MessageProcessingService messageProcessingService = new MessageProcessingServiceImpl();

    ProductModel productModel = ProductModel.getProductModelInstance();

    @Test (expected = IllegalArgumentException.class)
    public void verifyMessageParsingWithNullMessage() throws IllegalAccessException {
        messageProcessingService.processMessage(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void verifyMessageParsingWtihEmptyMessage() throws IllegalAccessException {
        messageProcessingService.processMessage("");
    }

    @Test (expected = IllegalArgumentException.class)
    public void verifyMessageParsingWithInvalidMessage() throws IllegalAccessException {
        messageProcessingService.processMessage("wrong informaion");
    }

    @Test (expected = IllegalArgumentException.class)
    public void verifyMessageParsingWithInsufficientInformation() throws IllegalAccessException {
        messageProcessingService.processMessage("apple sold");
    }

    @Test
    public void verifyMessageParsingWithSingleSaleMessage() throws IllegalAccessException {
        messageProcessingService.processMessage("someFruit at 10p");
        Assert.assertEquals(10, productModel.getProduct("someFruit").getSales().get(0).getValue());
    }

    @Test
    public void verifyMessageParsingWithMultipleSaleMessage() throws IllegalAccessException {
        messageProcessingService.processMessage("20 sales of anotherFruits at 10p each");
        Assert.assertEquals(10, productModel.getProduct("anotherFruit").getSales().get(0).getValue());
        Assert.assertEquals(20,productModel.getProduct("anotherFruit").getSales().get(0).getNoOfOccurences());
    }


    @Test
    public void verifyMessageParsingWithMultiplyUpdateSaleMessage() throws IllegalAccessException {
        messageProcessingService.processMessage("a fruit at 10p");
        messageProcessingService.processMessage("Multiply 2 a fruits");
        Assert.assertEquals(20, productModel.getProduct("a fruit").getSales().get(0).getValue());
    }


    @Test
    public void verifyMessageParsingWithSubtractUpdateSaleMessage() throws IllegalAccessException {
        messageProcessingService.processMessage("b fruit at 10p");
        messageProcessingService.processMessage("Subtract 2p b fruits");
        Assert.assertEquals(8, productModel.getProduct("b fruit").getSales().get(0).getValue());
    }

    @Test
    public void verifyMessageParsingWithAddUpdateSaleMessage() throws IllegalAccessException {
        messageProcessingService.processMessage("c fruit at 10p");
        messageProcessingService.processMessage("Add 4p c fruits");
        Assert.assertEquals(14, productModel.getProduct("c fruit").getSales().get(0).getValue());
    }

}
