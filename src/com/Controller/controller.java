package com.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import javax.management.Query;

import com.Class.Disk;
import com.Class.Instruction;
import com.Class.Logicmemory;

//用Timer应该更好控制时间1.5s跑一跳指令 
public class controller implements ActionListener {
	private int timer=1;
	private Instruction newinsInstruction=new Instruction();
	private Disk disk;
	private ArrayList<Logicmemory> mmlist=new ArrayList<>();
	private int head=0,tail=0;
	private int next;
	private int[] FIFO=new int[400];
	private int condition;
	static final int check=0;
	static final int change_in=1;
	static final int change_out=2;


	public controller(Disk disk,ArrayList<Logicmemory> mmlist) {
		this.disk=disk;
		this.mmlist=mmlist;
		newinsInstruction=disk.list.get(0);
		next=newinsInstruction.next-1;
		FIFO[tail]=newinsInstruction.page;
		System.out.println("当前是"+newinsInstruction.page+"页");
		Logicmemory temp=new Logicmemory();
		temp=mmlist.get(0);
		temp.nowpage=FIFO[tail];
		tail++;
		//重置下一次操作为check
		condition=check;
		//
		
		//绘制
	}
	private boolean findpage(int page) {
		for(int i=0;i<mmlist.size();i++){
			Logicmemory temp=new Logicmemory();
			temp=mmlist.get(i);
			if(temp.nowpage==page){
				return true;
			}
		}
		return false;
	}
	//先用FIFO
	private void replace() {
		for(int i=0;i<mmlist.size();i++){
			Logicmemory temp=new Logicmemory();
			temp=mmlist.get(i);
			if(temp.nowpage==FIFO[head]){
				temp.nowpage=FIFO[tail];
				head++;
			}
		
		}
		
	}
	private int findplace() {
		for(int i=0;i<mmlist.size();i++){
			Logicmemory temp=new Logicmemory();
			temp=mmlist.get(i);
			if(temp.nowpage==0){
				return i;
			}
		}
		return -1;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		//默认第一条指令是开始指令，先算当前情况
		switch(condition)
		{
		case check:{
		newinsInstruction=disk.list.get(next);
		next=newinsInstruction.next-1;
		System.out.println("当前是"+newinsInstruction.order+"号"+newinsInstruction.page+"页");
		if((!findpage(newinsInstruction.page))){
			if(tail-head<4){
				//如果没满，就直接放到空地
				FIFO[tail]=newinsInstruction.page;				
				tail++;
				Logicmemory temp=new Logicmemory();
				temp=mmlist.get(findplace());
				temp.nowpage=FIFO[tail];
			}else{
				FIFO[tail]=newinsInstruction.page;				
				tail++;
				replace();
			}
		}else{
			System.out.println("页面"+newinsInstruction.page+"已经存在");
		}
		break;
		}
		//最先判断是不是有相同的页
		case change_in:{

		}
		}
		//先扫一遍有没有位置
		
	}       

}
