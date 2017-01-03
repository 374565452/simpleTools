package com.simple.tools.netty;

import java.util.concurrent.ConcurrentHashMap;

import io.netty.channel.Channel;

public class DeviceFactory {

	private ConcurrentHashMap<Integer, FrontDevice> devices = new ConcurrentHashMap<Integer, FrontDevice>();

	public ConcurrentHashMap<Integer, FrontDevice> getDevices() {
		return devices;
	}

	public void setDevices(ConcurrentHashMap<Integer, FrontDevice> devices) {
		this.devices = devices;
	}

	public void put(Channel channel) {
		Session session = new Session(channel);
		FrontDevice frontDevice=new FrontDevice();
		frontDevice.setSession(session);
	System.out.println("添加 channel -------------------------");
		this.devices.put(channel.hashCode(), frontDevice);

	}

	public void remove(Channel channel) {
		// 此处remove之前，应该将设备的状态进行一次更新操作，设置为未连接状态
		FrontDevice frontDevice = this.devices.get(channel.hashCode());
		this.devices.remove(channel.hashCode());
	}

	private static class LazyHold {
		private final static DeviceFactory instance = new DeviceFactory();
	}

	private DeviceFactory() {

	}

	public static DeviceFactory getInstance() {
		return LazyHold.instance;
	}

	public Object fetchFromFrontDevice(Integer id){
		for (Integer key : devices.keySet()) {
			System.out.println("向客户端发送数据-------------------");
			// 发送数据
			FrontDevice frontDevice = devices.get(key);
			if (frontDevice != null) {
				Object val=frontDevice.getVal();
				frontDevice.setVal(null);
				return val;
			}
		}
		return null;
	}
	
	public void sendToFrontDevice(Integer id, Object data) {
		System.out.println("the devices length is --- "+devices.keySet().size());
		for (Integer key : devices.keySet()) {
			System.out.println("向客户端发送数据-------------------");
			// 发送数据
			FrontDevice frontDevice = devices.get(key);
			if (frontDevice != null) {
				frontDevice.sendData(data);
			}
		}

		/*
		 * FrontDevice frontDevice = devices.get(id); if(frontDevice != null){
		 * frontDevice.getSession().sendData(data); }
		 */
	}
	
	public void invokeData(Integer id, Object val) {
		for (Integer key : devices.keySet()) {
			System.out.println(" 接收到客户端发来的数据信息-------------------");
			// 发送数据
			FrontDevice frontDevice = devices.get(key);
			frontDevice.receiveData(val);
		}
	}
}
