package com.jpmorgan.digital.service;

/**
 * An interface which declares the operation to parse various sale messages
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

public interface MessageProcessingService {

    /**
     * Service function to process the message and store it in memory
     *
     * @param message Message to be processed
     * @throws IllegalAccessException When Service is paused
     * @throws IllegalArgumentException When invalid message is passed
     * @since 1.0
     */
    public void processMessage(String message) throws IllegalAccessException,IllegalArgumentException;
}
