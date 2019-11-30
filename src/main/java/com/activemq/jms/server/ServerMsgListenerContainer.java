package com.activemq.jms.server;

import org.springframework.jms.listener.DefaultMessageListenerContainer;

public class ServerMsgListenerContainer extends DefaultMessageListenerContainer {

	private String localSystem;

	public ServerMsgListenerContainer() {
		super();
	}

	public void setLocalSystem(String localSystem) {
		this.localSystem = localSystem;
		// 设置过滤器
		String selector = "receiveSystem" + " in ( ";
		String[] localSystems = localSystem.split(",");
		for (String losystem : localSystems) {
			selector = selector + "'" + losystem + "',";
		}
		selector = selector.substring(0, selector.length() - 1) + ") ";

		if (this.getMessageSelector() == null || "".equals(this.getMessageSelector())) {
			super.setMessageSelector(selector);
		} else {
			super.setMessageSelector(this.getMessageSelector() + " AND " + selector);
		}
	}

}
