package com.activemq.jms.server;


import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.listener.SessionAwareMessageListener;


/**
 * server的监听器
 * 
 */
//implements MessageListener 
public class ServerTxnListener implements  SessionAwareMessageListener<Message> {// 消息确认需要session，即要实现SessionSessionAwareMessageListener

	private Logger log = LoggerFactory.getLogger(ServerTxnListener.class);

	
	public void onMessage(Message message,Session session) {
		
		TextMessage msg = (TextMessage) message;
		try {
			String jsonObj = msg.getText();
			
			if(! msg.getStringProperty("txncode").equals("0220")){
				throw new RuntimeException("接收失败,抛出异常");
			}
			//确认消息，确认后，消息就会出队，如果接收失败 不确认，消息会存在队列里
			log.info("成功接收队列消息:"+jsonObj);
			message.acknowledge();
		} catch (Exception e) {
			log.error("server error", e);
			
			try {
				//消息重发
				session.recover();
			} catch (JMSException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	}



}
