package com.he;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * log4j与flume整合
 */
public class Log4jFlume {
    public static void main(String[] args) {
        Log logger = LogFactory.getLog(Log4jFlume.class);
        logger.info("java_test");
    }
}
