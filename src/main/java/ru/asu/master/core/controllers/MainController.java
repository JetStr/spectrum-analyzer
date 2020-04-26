package ru.asu.master.core.controllers;

import org.apache.commons.logging.Log;
import ru.asu.master.core.utils.LoggerFactory;
import ru.asu.master.gui.Window;

public class MainController{
    private static final Log logger = LoggerFactory.createLogger("MainController");
    private final Window window;

    public MainController() {
        this.window = new Window("Spectrum analyzer");
    }
}
