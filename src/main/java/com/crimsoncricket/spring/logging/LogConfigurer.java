/*
 * Copyright (c) Martijn van der Woud - The Crimson Cricket Internet Services - all rights reserved
 */

package com.crimsoncricket.spring.logging;

import org.springframework.util.Log4jConfigurer;

import java.io.FileNotFoundException;

public class LogConfigurer {

    private final String logConfigLocation;
    private final int refreshInterval;

    public LogConfigurer(String logConfigLocation) {
        if (logConfigLocation == null) {
            /*
            * If the config location has not been set,
            * we use the configuration file log4j.properties in the classpath.
            *
            * In that case we do not specify a refresh interval, since the configuration
            * will not change during the lifetime of the servlet context.
            */
            this.logConfigLocation = "classpath:log4j.properties";
            refreshInterval = 0;
        } else {
            this.logConfigLocation = logConfigLocation;
            this.refreshInterval = 5000; // Check the configuration file for changes every five seconds
        }


    }

    public void configureLogging() {

        System.out.println("Using logging configuation file: " + logConfigLocation);
        try {
            if (refreshInterval == 0) {
                Log4jConfigurer.initLogging(logConfigLocation);
            } else {
                Log4jConfigurer.initLogging(logConfigLocation, refreshInterval);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
