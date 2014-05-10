package com.Class;

public class MyStack {
	private int[] hehe=new int[5];
	public int top;
	public int size;
	public MyStack() {
		top=-1;
		size=0;
	}
	public void arrange(){
		
	}
	public int gethead() {
		return hehe[top];

	}
	public int getbottom() {
		return hehe[0];

	}
	public int find(int page) {
		for(int i=0;i<=top;i++){
			if(hehe[i]==page){
				return i;
			}
		}
		System.out.println("³ö´íÀ²");
		return 0;
	}
	public void push(int num) {
		top++;
		size++;
		hehe[top]=num;

	}
	public int pop() {
		top--;
		size--;
		return (hehe[top+1]);
	}
	public boolean full(){
		if (size==4)
		{
			return true;
		}
		return false;
	}
	public void init(){
		size=0;
		top=-1;
	}
}
