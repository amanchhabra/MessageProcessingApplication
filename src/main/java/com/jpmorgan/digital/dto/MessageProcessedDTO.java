package com.jpmorgan.digital.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * DTO Class storing and transferring message processed information
 */
public class MessageProcessedDTO {

    /**
     * Enum of the type of the message
     */
    public enum MessageType {
        ONE_SALE,
        MULTIPLE_SALES,
        UPDATE_SALES
    }

    /**
     * Type of the message
     */
    private MessageType messageType;

    /**
     * Info tokens received in the message
     */
    private List<String> infoTokens;

    /**
     * Check {@link com.jpmorgan.digital.dto.MessageProcessedDTO#messageType} for more information
     */
    public MessageType getMessageType() {
        return messageType;
    }

    /**
     * Check {@link com.jpmorgan.digital.dto.MessageProcessedDTO#messageType} for more information
     */
    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    /**
     * Check {@link com.jpmorgan.digital.dto.MessageProcessedDTO#infoTokens} for more information
     */
    public List<String> getInfoTokens() {
        if(infoTokens == null) {
            infoTokens = new ArrayList<String>();
        }
        return infoTokens;
    }

    /**
     * Check {@link com.jpmorgan.digital.dto.MessageProcessedDTO#infoTokens} for more information
     */
    public void setInfoTokens(List<String> infoTokens) {
        this.infoTokens = infoTokens;
    }
}
