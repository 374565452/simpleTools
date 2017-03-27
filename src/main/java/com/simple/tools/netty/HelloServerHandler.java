package com.simple.tools.netty;

import java.net.InetAddress;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

public class HelloServerHandler extends SimpleChannelInboundHandler<String> {

	private ChannelGroup group;// = new
								// DefaultChannelGroup(ImmediateEventExecutor.INSTANCE);

	public HelloServerHandler(ChannelGroup group) {
		this.group = group;
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		//group.add(ctx.channel());
		// super.channelActive(ctx);
		DeviceFactory.getInstance().put(ctx.channel());
		System.out.println("active----accept a client --------the channel hashcode is" + ctx.channel().hashCode());
		System.out.println("the group size is %d --" + group.size());
		// ctx.channel().close();
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		// super.channelInactive(ctx);
		DeviceFactory.getInstance().remove(ctx.channel());
		System.out.println("inactive-----disconnected a client --------");
		System.out.println("the group size is ----" + group.size());
		ctx.channel().writeAndFlush("welcome to netty!");
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		System.out.println("RamoteAddress : " + ctx.channel().remoteAddress() + " active !");
		//ctx.writeAndFlush("Welcome to " + InetAddress.getLocalHost().getHostName() + " service!\n");
		System.out.println("the receive msg is : "+msg);
		//DeviceFactory.getInstance().invokeData(ctx.channel().hashCode(), msg);
		ctx.channel().writeAndFlush(msg);
	}
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		//super.channelReadComplete(ctx);
		System.out.println("received-- complete---- --------");
	}
	
	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		//super.channelRegistered(ctx);
		System.out.println("registered ---a client---- --------");
	}
	
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		System.out.println("handler----added the hashcode is " +ctx.channel().hashCode());
	}
	
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		System.out.println("handler----removed the hashcode is " +ctx.channel().hashCode());
	};
	
	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
		//super.channelUnregistered(ctx);
		System.out.println("unregistered ---a client---- --------");
	}
}
