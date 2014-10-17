package ru.tsystems.tsproject.ecare;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Created by Selvin
 * on 17.10.2014.
 */
public class ECareLogger {
    private static ECareLogger instance;
    private Logger logger = Logger.getLogger(ECareLogger.class.getName());
    private FileHandler handler;
    private String logPath = Config.LOG_PATH;
    private File log;

    private ECareLogger() {
        log = new File(logPath);
        File dir = log.getParentFile();
        if (!log.exists()) {
            dir.mkdir();
        }
        try {
            handler = new FileHandler(logPath, 1048576, 1, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        handler.setLevel(Level.ALL);
        handler.setFormatter(new SimpleFormatter());
        logger.addHandler(handler);
        logger.info("eCare started.");
    }

    public static ECareLogger getInstance()
    {
        if (instance == null) {
            instance = new ECareLogger();
        }
        return instance;
    }

    public Logger getLogger() {
        return  logger;
    }
}
