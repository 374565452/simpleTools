package com.simple.tools.netty;

import io.netty.channel.Channel;

public class Session {

	private Channel channel;

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	
	public Session(Channel channel){
		this.channel=channel;
	}
	
	public void sendData(Object data) {
		boolean a = channel.isActive();
		// System.out.println("the active of the channel is === "+a);
		if (a) {
			channel.writeAndFlush(data);
		}
	}
	
}
