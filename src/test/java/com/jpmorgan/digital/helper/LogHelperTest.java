package com.jpmorgan.digital.helper;

import org.junit.Assert;
import org.junit.Test;
import sun.rmi.runtime.Log;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * A test class to verify all operations on <code>LogHelper</code>
 *
 * @author Aman Chhabra
 */

public class LogHelperTest {

    @Test
    public void verifyStoreDetails(){
        LogHelper.storeDetails("LOG_ID","message");
        Assert.assertEquals("message",LogHelper.getLogList("LOG_ID").get(0));
    }

    @Test (expected = IllegalArgumentException.class)
    public void verifyStoreDetailsWhenLogIDNull(){
        LogHelper.storeDetails(null, "message");
    }

    @Test (expected = IllegalArgumentException.class)
    public void verifyStoreDetailsWhenLogIDEmpty(){
        LogHelper.storeDetails("","message");
    }

    @Test (expected = IllegalArgumentException.class)
    public void verifyStoreDetailsWhenMessageNull(){
        LogHelper.storeDetails("LOG_ID",null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void verifyStoreDetailsWhenMessageEmpty(){
        LogHelper.storeDetails("LOG_ID","");
    }

    @Test
    public void verifySingleLogDetails(){
        PrintStream currentOutStream  = System.out;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));
        LogHelper.storeDetails("log_new_id", "message");
        LogHelper.logDetails("log_new_id");
        System.setOut(currentOutStream);
        String printedLog = new String(byteArrayOutputStream.toByteArray());
        Assert.assertTrue(printedLog.contains("message"));
    }

    @Test
    public void verifyMultipleLogDetails(){
        PrintStream currentOutStream  = System.out;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));
        for(int i=0;i<10;i++) {
            LogHelper.storeDetails("log_new_id", "message"+i);
        }
        LogHelper.logDetails("log_new_id");
        System.setOut(currentOutStream);
        String printedLog = new String(byteArrayOutputStream.toByteArray());
        for(int i=0;i<10;i++) {
            Assert.assertTrue(printedLog.contains("message"+i));
        }
    }

    @Test (expected = IllegalArgumentException.class)
    public void verifyLogDetailsWhenLogIdNull(){
        LogHelper.logDetails(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void verifyLogDetailsWhenLogIdEmpty(){
        LogHelper.logDetails("");
    }

    @Test
    public void verifyLogMessage(){
        PrintStream currentOutStream  = System.out;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));
        LogHelper.logMessage("new message");
        System.setOut(currentOutStream);
        String printedLog = new String(byteArrayOutputStream.toByteArray());
        Assert.assertTrue(printedLog.contains("new message"));
    }


    @Test (expected = IllegalArgumentException.class)
    public void verifyMessageWhenMessageNull(){
        LogHelper.logMessage(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void verifyMessageWhenMessageEmpty(){
        LogHelper.logMessage("");
    }


}
