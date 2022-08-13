package com.entity;

public class ResponseBean<T> {

	
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	T data;
	String msg;
	
	
	
	
	
}
