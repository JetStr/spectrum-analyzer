package ru.asu.master.core;

import org.apache.commons.logging.Log;
import ru.asu.master.core.controllers.MainController;
import ru.asu.master.core.utils.LoggerFactory;

public class App {
    public static void main(String[] args) {
        Log logger = LoggerFactory.createLogger("App");
        logger.info("App start");
        try {
            new MainController();
        } catch (Exception ex) {
            logger.error(ex, ex);
        }
    }
}
