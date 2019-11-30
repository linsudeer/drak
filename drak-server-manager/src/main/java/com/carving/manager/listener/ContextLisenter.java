package com.carving.manager.listener;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * ServletContextListener
 * @author Li Songlin
 * @date 2019/10/24 14:11
 **/
@WebListener
@Slf4j
public class ContextLisenter implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        log.info("ServletContext initialized...");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        log.info("ServletContext destroyed...");
    }
}
