package ru.tsystems.tsproject.ecare;

import org.apache.log4j.Logger;

/**
 * Created by Selvin
 * on 04.10.2014.
 */
public class Main {
    private static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {

        //PropertiesConfigurator is used to configure logger from properties file
        //PropertyConfigurator.configure("log4j.properties");

        //Log in console in and log file
        logger.debug("Log4j appender configuration is successful !!");
    }
}
