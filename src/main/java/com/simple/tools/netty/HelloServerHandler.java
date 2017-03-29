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
	//建立连接后，最后执行这个函数 3
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		//group.add(ctx.channel());
		// super.channelActive(ctx);
		//DeviceFactory.getInstance().put(ctx.channel());
		System.out.println("active----accept a client --------the channel hashcode is" + ctx.channel().hashCode());
		System.out.println("the group size is %d --" + group.size());
		System.out.println("---------------shut down the connect--------------------- ");
		ctx.channel().writeAndFlush("Welcom to the netty !");
		//ctx.channel().close();
	}

	//连接断开后，先执行 这个 1
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		// super.channelInactive(ctx);
		//DeviceFactory.getInstance().remove(ctx.channel());
		System.out.println("inactive-----disconnected a client --------");
		System.out.println("the group size is ----" + group.size());
		//ctx.channel().writeAndFlush("welcome to netty!");
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
	
	//建立连接后，再次执行这个函数2
	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		//super.channelRegistered(ctx);
		System.out.println("registered ---a client---- --------");
	}
	
	//建立连接后，先执行这个函数 1
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		System.out.println("handler----added the hashcode is " +ctx.channel().hashCode());
	}
	//连接断开后，最后执行这个函数 3
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		System.out.println("handler----removed the hashcode is " +ctx.channel().hashCode());
	};
	
	//连接断开后，再次执行这个函数 2
	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
		//super.channelUnregistered(ctx);
		System.out.println("unregistered ---a client---- --------");
	}
}
