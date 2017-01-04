package com.simple.tools.model;

public class QRCodeParam {

	private String text;
	
	private String level;
	
	private int size;
	
	private int margin;
	
	private boolean trans;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getMargin() {
		return margin;
	}

	public void setMargin(int margin) {
		this.margin = margin;
	}

	public boolean isTrans() {
		return trans;
	}

	public void setTrans(boolean trans) {
		this.trans = trans;
	}
	
	
	
}
