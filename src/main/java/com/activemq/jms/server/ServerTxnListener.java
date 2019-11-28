package com.activemq.jms.server;


import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * server的监听器
 * 
 */
public class ServerTxnListener implements MessageListener {

	private Logger log = LoggerFactory.getLogger(ServerTxnListener.class);

	
	public void onMessage(Message message) {
		
		TextMessage msg = (TextMessage) message;
		try {
			String jsonObj = msg.getText();
			log.info("读取队列消息:"+jsonObj);
		} catch (Exception e) {
			log.error("server error", e);
		}
	}



}
