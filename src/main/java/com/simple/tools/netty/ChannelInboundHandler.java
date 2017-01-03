package com.simple.tools.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

public class ChannelInboundHandler extends ChannelInboundHandlerAdapter {
	private ChannelGroup group ;//= new DefaultChannelGroup(ImmediateEventExecutor.INSTANCE);
	
	public ChannelInboundHandler(ChannelGroup group) {
		this.group=group;
	}
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		group.add(ctx.channel());
		//super.channelActive(ctx);
		System.out.println("active----accept a client --------the channel hashcode is"+ctx.channel().hashCode());
		System.out.println("the group size is %d --"+group.size());
		//ctx.channel().close();
	}
	
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		//super.channelInactive(ctx);
		System.out.println("inactive-----disconnected a client --------");
		System.out.println("the group size is ----"+group.size());
	}
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		//super.channelRead(ctx, msg);
		//在不加解码器的情况下。msg是ByteBuf对象，添加上解码器之后，就不可以拉
		if(msg instanceof ByteBuf){
			ByteBuf buf=(ByteBuf)msg;
			int readLength=buf.readableBytes();
			byte[] result=new byte[readLength];
			System.out.println(String.format("the length is %d ",readLength));

			String val=new String(result, "UTF-8");
			System.out.println("the receive values is " + val);
			ctx.channel().writeAndFlush(msg);
			//byte[] buffer=buf.array();
			
		}else{
			ctx.channel().writeAndFlush(new Integer(4));
		}
		//在这里之前是这样写的
		/*
		 * ctx.channel().writeAndFlush(new Integer(4));
		 * 在客户端中，一直接收不到数据是因为没有配置编码 器， 只是配置拉一个解码器
		 * 然后改成ctx.channel().writeAndFlush(msg).就可以发送成功。因为msg是ByteBuf类型的
		 */
		//ctx.channel().writeAndFlush(msg);
		System.out.println("received----the data----");
		//ctx.channel().flush();
	}
	
	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		System.out.println("----------into the user event triggered--------");
		if (evt instanceof IdleStateEvent) {
	            IdleStateEvent event = (IdleStateEvent) evt;
	            if (event.state().equals(IdleState.READER_IDLE)) {
	                System.out.println("-----READER_IDLE-----");
	                // 超时关闭channel
	                ctx.close();
	            } else if (event.state().equals(IdleState.WRITER_IDLE)) {
	                System.out.println("--------WRITER_IDLE-----");
	            } else if (event.state().equals(IdleState.ALL_IDLE)) {
	                System.out.println("----ALL_IDLE-----");
	                // 发送心跳
	                ctx.channel().write("---ping\n---");
	            }
	        }
		super.userEventTriggered(ctx, evt);
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
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		super.exceptionCaught(ctx, cause);
		//System.out.println("catch the exception -------");
	}
	
	
}
