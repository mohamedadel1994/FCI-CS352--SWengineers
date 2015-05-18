package com.FCI.SWE.Models;

public interface Observer {
	public abstract void update(String convName,String sender,String msg); 
}
