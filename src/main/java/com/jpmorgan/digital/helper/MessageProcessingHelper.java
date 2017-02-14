package com.jpmorgan.digital.helper;

import com.jpmorgan.digital.dto.MessageProcessedDTO;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Helper class to process different type of messages
 * @author Aman Chhabra
 * @since 1.0
 */
public class MessageProcessingHelper {

    /**
     * Regular Expression for the message of type single sale
     */
    private static String ONE_SALE_REGEX = "^([A-Za-z ]*) at ([0-9]{0,2})p$";

    /**
     * Regular Expression for the message of type multiple sales
     */
    private static String MULTIPLE_SALES_REGEX = "^([0-9]{0,9}) sales of ([A-Za-z ]*)s at ([0-9]{0,9})p each$";

    /**
     * Regular Expression for the message to update sales
     */
    private static String UPDATE_SALES_REGEX = "^(Add|Subtract|Multiply) ([0-9]{0,9})p? ([A-Za-z ]*)s$";

    /**
     * Set of Rules that needs to be executed to parse the message
     */
    private static String parsingRules[] = new String[]{ONE_SALE_REGEX,MULTIPLE_SALES_REGEX,UPDATE_SALES_REGEX};

    /**
     * Function to parse the message and create <code>MessageProcessedDTo</code> for the successfully parsed messages
     *
     * @param message Message to be parsed
     * @return MessageProcessedDTO
     * @since 1.0
     */
    public static MessageProcessedDTO processMessage(String message) {
        Matcher matcher  = null;
        MessageProcessedDTO messageProcessedDTO = null;
        int ruleIndex = 0;
        while(matcher == null && ruleIndex<parsingRules.length) {
            matcher = parseRegex(parsingRules[ruleIndex],message);
            if(matcher != null) {
                messageProcessedDTO  = new MessageProcessedDTO();
                int totalCount = matcher.groupCount();
                List<String> infoTokens = messageProcessedDTO.getInfoTokens();
                for (int i = 0; i <= totalCount; i++){
                    infoTokens.add(matcher.group(i));
                }
                messageProcessedDTO.setInfoTokens(infoTokens);
                switch(ruleIndex) {
                    case 0 : messageProcessedDTO.setMessageType(MessageProcessedDTO.MessageType.ONE_SALE);
                        break;
                    case 1: messageProcessedDTO.setMessageType(MessageProcessedDTO.MessageType.MULTIPLE_SALES);
                        break;
                    case 2: messageProcessedDTO.setMessageType(MessageProcessedDTO.MessageType.UPDATE_SALES);
                        break;
                }
            }
            ruleIndex++;
        }
        return  messageProcessedDTO;
    }

    /**
     * Function to match the regex pattern with the string and return matcher in case of successfully match
     * @param patternRegex String Regular Expression Pattern
     * @param message String Message to match
     * @return Matcher if message successfully matched
     *         null    if message not matched
     */
    private static Matcher parseRegex(String patternRegex, String message) {
    Pattern pattern = Pattern.compile(patternRegex);
    Matcher matcher = pattern.matcher(message);
    if(matcher.find()) {
        return matcher;
    } else {
        return null;
    }
}

}
