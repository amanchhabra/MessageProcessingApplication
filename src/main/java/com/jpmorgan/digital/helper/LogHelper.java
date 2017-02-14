package com.jpmorgan.digital.helper;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class to store and log info details
 *
 * @author Aman Chhabra
 * @since 1.0
 */
public class LogHelper {

    private final static Logger logger = Logger.getLogger(LogHelper.class);

    /**
     * Map to store logging data corresponding to each logging ID
     */
    private static Map<String,List> logData = new HashMap<String, List>();

    /**
     * Function to store log data which can be used to log all the details later at once
     *
     * @param logId String log ID of the logs
     * @param value String Log data
     * @since 1.0
     */
    public static void storeDetails(String logId, String value){
        if(logId == null || logId.isEmpty() || value == null || value.isEmpty())
            throw new IllegalArgumentException("Log ID and Value can not be empty");
        getLogList(logId).add(value);
    }

    /**
     * Function to log all the details store for this corresponding log ID
     *
     * @param logId String log ID of the logs
     * @since 1.0
     */
    public static void logDetails(String logId){
        if(logId == null || logId.isEmpty() )
            throw new IllegalArgumentException("Log ID can not be empty");
        for(String data:getLogList(logId)){
            logMessage(data);
        }
    }

    /**
     * Function to log message without storing it
     *
     * @param message String message to be logged
     * @since 1.0
     */
    public static void logMessage(String message) {
        if(message == null || message.isEmpty() )
            throw new IllegalArgumentException("Mesage to be logged can not be empty");
        System.out.println(message);
        if (logger.isInfoEnabled()) {
            logger.info(message);
        }
    }

    /**
     * Function to get the list of Log Data corresponding to a log ID
     *
     * @param logId  String log ID of the logs
     * @return List of log data
     * @since 1.0
     */
    public static List<String> getLogList(String logId){
        if(!logData.containsKey(logId)){
            logData.put(logId,new ArrayList<String>());
        }
        return logData.get(logId);
    }

}
