package com.jpmorgan.digital.helper;

import com.jpmorgan.digital.dto.MessageProcessedDTO;
import org.junit.Assert;
import org.junit.Test;

/**
 * A test class to verify all operations on <code>MessageProcessingHelper</code>
 *
 * @author Aman Chhabra
 */

public class MessageProcessingHelperTest {

    @Test
    public void verifyProcessMessageWithInvalidMessage(){
        MessageProcessedDTO messageProcessedDTO = MessageProcessingHelper.processMessage("no info in message");
        Assert.assertNull(messageProcessedDTO);
    }

    @Test
    public void verifyProcessMessageWithSingleSaleMessage(){
        MessageProcessedDTO messageProcessedDTO = MessageProcessingHelper.processMessage("apple at 10p");
        Assert.assertNotNull(messageProcessedDTO);
        Assert.assertEquals(MessageProcessedDTO.MessageType.ONE_SALE, messageProcessedDTO.getMessageType());
        Assert.assertEquals("apple",messageProcessedDTO.getInfoTokens().get(1));
        Assert.assertEquals("10", messageProcessedDTO.getInfoTokens().get(2));
    }

    @Test
    public void verifyProcessMessageWithMultipleSalesMessage(){
        MessageProcessedDTO messageProcessedDTO = MessageProcessingHelper.processMessage("20 sales of apples at 10p each");
        Assert.assertNotNull(messageProcessedDTO);
        Assert.assertEquals(MessageProcessedDTO.MessageType.MULTIPLE_SALES, messageProcessedDTO.getMessageType());
        Assert.assertEquals("20",messageProcessedDTO.getInfoTokens().get(1));
        Assert.assertEquals("apple",messageProcessedDTO.getInfoTokens().get(2));
        Assert.assertEquals("10",messageProcessedDTO.getInfoTokens().get(3));
    }

    @Test
    public void verifyProcessMessageWithAddUpdateMessage(){
        MessageProcessedDTO messageProcessedDTO = MessageProcessingHelper.processMessage("Add 2 apples");
        Assert.assertNotNull(messageProcessedDTO);
        Assert.assertEquals(MessageProcessedDTO.MessageType.UPDATE_SALES, messageProcessedDTO.getMessageType());
        Assert.assertEquals("Add",messageProcessedDTO.getInfoTokens().get(1));
        Assert.assertEquals("2",messageProcessedDTO.getInfoTokens().get(2));
        Assert.assertEquals("apple",messageProcessedDTO.getInfoTokens().get(3));
    }

    @Test
    public void verifyProcessMessageWithSubtractUpdateMessage(){
        MessageProcessedDTO messageProcessedDTO = MessageProcessingHelper.processMessage("Subtract 2 apples");
        Assert.assertNotNull(messageProcessedDTO);
        Assert.assertEquals(MessageProcessedDTO.MessageType.UPDATE_SALES, messageProcessedDTO.getMessageType());
        Assert.assertEquals("Subtract",messageProcessedDTO.getInfoTokens().get(1));
        Assert.assertEquals("2",messageProcessedDTO.getInfoTokens().get(2));
        Assert.assertEquals("apple",messageProcessedDTO.getInfoTokens().get(3));
    }

    @Test
    public void verifyProcessMessageWithMultiplyUpdateMessage(){
        MessageProcessedDTO messageProcessedDTO = MessageProcessingHelper.processMessage("Multiply 2 apples");
        Assert.assertNotNull(messageProcessedDTO);
        Assert.assertEquals(MessageProcessedDTO.MessageType.UPDATE_SALES, messageProcessedDTO.getMessageType());
        Assert.assertEquals("Multiply",messageProcessedDTO.getInfoTokens().get(1));
        Assert.assertEquals("2",messageProcessedDTO.getInfoTokens().get(2));
        Assert.assertEquals("apple",messageProcessedDTO.getInfoTokens().get(3));
    }

}
