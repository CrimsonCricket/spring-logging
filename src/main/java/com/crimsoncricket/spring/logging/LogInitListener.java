/*
 * Copyright 2015 Martijn van der Woud - The Crimson Cricket Internet Services
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *
 */

package com.crimsoncricket.spring.logging;

import org.springframework.util.Log4jConfigurer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.FileNotFoundException;

public abstract class LogInitListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        String logConfigLocation = logConfigLocation();
        System.out.println("Using logging configuation file: " + logConfigLocation);

        int refreshInterval = 5000; // Check the configuration file for changes every five seconds
        if (logConfigLocation == null) {
            /*
            * If the config location has not been set,
            * we use the configuration file log4j.properties in the classpath.
            *
            * In that case we do not specify a refresh interval, since the configuration
            * will not change during the lifetime of the servlet context.
            */
            logConfigLocation = "classpath:log4j.properties";
            refreshInterval = 0;
        }

        try {

            if (refreshInterval == 0) {
                Log4jConfigurer.initLogging(logConfigLocation);
            } else {
                Log4jConfigurer.initLogging(logConfigLocation, refreshInterval);
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    protected abstract String logConfigLocation();

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }


}
