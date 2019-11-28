package com.activemqConsumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 消费端
 * @author lihonghui
 *
 */
public class ConsumerSpring {
	private final static Logger LOG = LoggerFactory.getLogger(ConsumerSpring.class);
    public static void main( String[] args ){
    	ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
    	LOG.info("Lisening.............");
    	applicationContext.registerShutdownHook();
    }
}
