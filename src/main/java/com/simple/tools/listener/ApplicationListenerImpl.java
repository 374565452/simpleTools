package com.simple.tools.listener;

import javax.annotation.Resource;
import javax.servlet.ServletConfig;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletConfigAware;

import com.simple.tools.netty.NettyServer;

@Component
public class ApplicationListenerImpl implements ApplicationContextAware,ServletConfigAware,InitializingBean,ApplicationListener<ApplicationEvent>{
	
	@Resource(name="coreServer")
	private NettyServer server;
	
	//最后执行此函数，但此函数会执行多次
	//经进测试，onApplicationEvent这个方法会执行多次，所以不建议在此方法中执行初始化操作
	@Override
	public void onApplicationEvent(ApplicationEvent arg0) {
		System.out.println(" on  application event----------------");
		//new NettyServer().bind();
		
	}

	//再次执行此函数
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("after properties set -----------");
	}

	//第二执行此函数
	@Override
	public void setServletConfig(ServletConfig arg0) {
		System.out.println("set servlet config ---------");
	}

	//第一执行此函数，因为初始化操作越早越好，建议在此方法中进行初始化操作
	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		System.out.println("Set application context-------");
		new Thread(server).start();
	}

}
