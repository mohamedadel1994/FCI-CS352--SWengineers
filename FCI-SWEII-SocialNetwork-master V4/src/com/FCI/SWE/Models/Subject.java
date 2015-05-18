package com.FCI.SWE.Models;

public interface Subject {
	public abstract void attach(Observer obs);
	public abstract void Notify(String convName,String sender,String msg);
}
