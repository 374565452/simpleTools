package com.simple.tools.netty;

import java.util.Date;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/*
 * 因为这里只处理string类型的数据，所有要增加string类型解码器
 */
public class MyServerHanlder extends SimpleChannelInboundHandler<String> {
	/*
	 * channelAction 
	 * 
	 * channel 通道
	 * action  活跃的
	 * 
	 * 当客户端主动链接服务端的链接后，这个通道就是活跃的了。也就是客户端与服务端建立了通信通道并且可以传输数据
	 * 
	 */
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		super.channelActive(ctx);
		
	}
	
 
	/*
	 * channelInactive
	 * 
	 * channel 	通道
	 * Inactive 不活跃的
	 * 
	 * 当客户端主动断开服务端的链接后，这个通道就是不活跃的。也就是说客户端与服务端的关闭了通信通道并且不可以传输数据
	 * 
	 */
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		super.channelInactive(ctx);
	}
	
	/*
	 * channelRead0
	 * 
	 * channel 通道
	 * Read    读
	 * 
	 * I msg
	 * I 枚举类型根据你继承的SimpleChannelInboundHandler<I>设置来的
	 * 
	 * 同样你用channelRead也可以处理数据，但是作者已经提供了channelRead0，并且是抽象类
	 * 
	 * 简而言之就是从通道中读取数据，也就是服务端接收客户端发来的数据
	 * 但是这个数据在不进行解码时它是ByteBuf类型的后面例子我们在介绍
	 * 
	 */
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		System.out.println("read the data --------");
		System.out.println(new Date()+" 收到数据：");
		System.out.println(msg);
	}
	
	/*
	 * channelReadComplete
	 * 
	 * channel  通道
	 * Read     读取
	 * Complete 完成
	 * 
	 * 在通道读取完成后会在这个方法里通知，对应可以做刷新操作
	 * ctx.flush()
	 * 
	 */
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		super.channelReadComplete(ctx);
		System.out.println("read complete \r\n");
	}
	
	/*
	 * exceptionCaught
	 * 
	 * exception	异常
	 * Caught		抓住
	 * 
	 * 抓住异常，当发生异常的时候，可以做一些相应的处理，比如打印日志、关闭链接
	 * 
	 */
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		super.exceptionCaught(ctx, cause);
		ctx.close();
		System.out.println("异常信息：\r\n"+cause.getMessage());
	}
}
