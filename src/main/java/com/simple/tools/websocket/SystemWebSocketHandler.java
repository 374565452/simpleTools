package com.simple.tools.websocket;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

@Component
public class SystemWebSocketHandler implements WebSocketHandler{

	public static final Map<Long, WebSocketSession> userSocketSessionMap;

	static {
		userSocketSessionMap = new HashMap<Long, WebSocketSession>();
	}
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus arg1) throws Exception {
		System.out.println("退出一个websocket client-----------------------");
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println("连上一个websocket client -------------------");
		if(session.isOpen()){
			session.sendMessage(new TextMessage("Hello World"));
		}
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		System.out.println("接收数据--------------");
		if(message.getPayloadLength()<=0){
			return;
		}
		String recv="接收到的数据为："+message.getPayload().toString()+"aaaaa";
		System.out.println(recv);
		if(session.isOpen()){
			session.sendMessage(new TextMessage(recv));
		}
	}

	//数据传送失败后，调用的
	@Override
	public void handleTransportError(WebSocketSession session, Throwable arg1) throws Exception {
		
	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}

}
