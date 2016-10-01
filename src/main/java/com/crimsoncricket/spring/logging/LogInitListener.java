/*
 * Copyright 2016 Martijn van der Woud - The Crimson Cricket Internet Services
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
 */

package com.crimsoncricket.spring.logging;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public abstract class LogInitListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        String logConfigLocation = logConfigLocation();
        LogConfigurer configurer = new LogConfigurer(logConfigLocation);
        configurer.configureLogging();
    }

    protected abstract String logConfigLocation();

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }


}
