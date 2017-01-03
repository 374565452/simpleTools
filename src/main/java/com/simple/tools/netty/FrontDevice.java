package com.simple.tools.netty;

public class FrontDevice {

	private Session session;

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}
	
	private Object val;
	
	
	
	public void sendData(Object data){
		this.session.sendData(data);
		/*try {
			wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		/*try {
			//等待3秒钟，接收客户端数据
			wait(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
	}
	
	public void receiveData(Object data){
		setVal(data);
		//唤醒在本线程内的所有wait操作
		//notify();
	}

	public Object getVal() {
		return val;
	}

	public void setVal(Object val) {
		this.val = val;
	}
	
}
