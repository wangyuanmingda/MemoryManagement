package com.Class;

public class Instruction {

	public Instruction() {
	}
	
	public Instruction(String order, String next,int page) {
		this.order=Integer.parseInt(order);
		this.next=Integer.parseInt(next);
		this.page=page;
	}
	public int order;
	public int page;
	public int next;
}
