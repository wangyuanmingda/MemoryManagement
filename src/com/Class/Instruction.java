package com.Class;

public class Instruction {

	public Instruction() {
	}
	
	public Instruction(String order, String ins,int page) {
		this.order=Integer.parseInt(order);
		this.ins=Integer.parseInt(ins);
		this.page=page;
	}
	public int order;
	public int page;
	public int ins;
}
