package com.simple.tools.netty;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.ImmediateEventExecutor;

@Component(value="coreServer")
@Scope("singleton")
public class NettyServer implements Runnable {

	private static Logger logger=Logger.getLogger(NettyServer.class);
	
	private ChannelGroup group = new DefaultChannelGroup(ImmediateEventExecutor.INSTANCE);
	
	public ChannelGroup getGroup() {
		return group;
	}
	public void setGroup(ChannelGroup group) {
		this.group = group;
	}

	public void bind() {
		System.out.println("server start................");
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workGroup = new NioEventLoopGroup();
 
		try {
 
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workGroup);
			b.channel(NioServerSocketChannel.class);
			b.option(ChannelOption.SO_BACKLOG, 1024); //连接数
			b.option(ChannelOption.TCP_NODELAY, true);//不延迟，消 息立即发送
			b.option(ChannelOption.SO_KEEPALIVE,true); //长连接
			b.childHandler(new ChildChannelHandler(this));
			
			// 绑定端口
			ChannelFuture f = b.bind(7002).sync();
			
			String info =String.format("the web server is start at port %d", 9999);
			logger.info(info);
			System.out.println(info);
			// 等待服务端监听端口关闭
			f.channel().closeFuture().sync();
 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("exit in the system-----------------------");
			// 优雅的退出
			bossGroup.shutdownGracefully();
			workGroup.shutdownGracefully();
		}
 
	}
	
	public static void main(String[] args) {
		logger.info("-------------system starting-----------------");
		new NettyServer().bind();
	}
	@Override
	public void run() {
		this.bind();
	}
}
