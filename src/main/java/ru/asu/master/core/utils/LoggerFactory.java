package ru.asu.master.core.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LoggerFactory {

    private LoggerFactory() {
    }

    public static Log createLogger(String name) {
        return LogFactory.getLog(name);
    }
}
