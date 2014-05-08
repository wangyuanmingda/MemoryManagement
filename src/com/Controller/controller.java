package com.Controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import javax.management.Query;
import javax.swing.JTextPane;

import com.Class.Disk;
import com.Class.Instruction;
import com.Class.Logicmemory;
import com.Interface.MyJFrame;
import com.Interface.Memory;

//用Timer应该更好控制时间1.5s跑一跳指令 
public class controller implements ActionListener {
	private  JTextPane jp;
	
	private Instruction newinsInstruction=new Instruction();
	private Disk disk;
	private ArrayList<Logicmemory> mmlist=new ArrayList<>();
	private int head=0,tail=0;
	private int next=0;
	private int[] FIFO=new int[400];
	private int condition;
	static final int check=0;
	static final int change_in=1;
	static final int change_out=2;
	private Logicmemory temp=new Logicmemory();


	public controller(Disk disk,ArrayList<Logicmemory> mmlist, MyJFrame frame) {
		this.disk=disk;
		this.mmlist=mmlist;
		this.jp=frame.jp;

		
		
//		newinsInstruction=disk.list.get(0);
//		next=newinsInstruction.next-1;
//		FIFO[tail]=newinsInstruction.page;
//		System.out.println("当前是"+newinsInstruction.page+"页");
//		jp.setText("当前是"+newinsInstruction.page+"页"+"\n");
//		Logicmemory temp=new Logicmemory();
//		temp=mmlist.get(0);
//		temp.nowpage=FIFO[tail];
//    	Memory jh=(Memory) frame.bgjp.getComponent(4);
//    	jh.color=0;
//    	frame.repaint();
//	//	frame.bgjp.getComponent(0).getGraphics();
//		tail++;
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
				temp.nowpage=0;
				jp.setText(jp.getText()+"第"+i+"块"+"换出"+FIFO[head]+"页"+"\n");
				System.out.println("第"+i+"块"+"换出"+FIFO[head]+"页");
			
				head++;
			}
		
		}
		
	}
	private int findplace() {
		for(int i=0;i<mmlist.size();i++){
			Logicmemory temp=new Logicmemory();
			temp=mmlist.get(i);
			if(temp.nowpage==0){
				return i+1;
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
		jp.setText(jp.getText()+"当前是"+newinsInstruction.order+"号"+newinsInstruction.page+"页"+"\n");
		System.out.println("当前是"+newinsInstruction.order+"号"+newinsInstruction.page+"页");
		if((!findpage(newinsInstruction.page))){
			FIFO[tail]=newinsInstruction.page;				
			tail++;
			if(tail-head<4){
				//如果没满，就直接放到空地
				
				int emptyplace;
				emptyplace=findplace();
				temp=mmlist.get(emptyplace);
				temp.nowpage=FIFO[tail-1];
				System.out.println("第"+emptyplace+"块调入"+temp.nowpage+"页");
				jp.setText(jp.getText()+"第"+emptyplace+"块调入"+temp.nowpage+"页"+"\n");
				condition=check;
			}else{
				condition=change_out;			
			}
		}else{
			jp.setText(jp.getText()+"页面"+newinsInstruction.page+"已经存在"+"\n");
			System.out.println("页面"+newinsInstruction.page+"已经存在");
			condition=check;
			
		}
		break;
		}
		case change_in:{
			int emptyplace;
			emptyplace=findplace();
			temp=mmlist.get(emptyplace);
			temp.nowpage=FIFO[tail-1];
			jp.setText(jp.getText()+"第"+emptyplace+"块调入"+temp.nowpage+"页"+"\n");
			System.out.println("第"+emptyplace+"块调入"+temp.nowpage+"页");
			condition=check;
		}
		case change_out:{
			replace();
			condition=change_in;
		}
		}
		
	}       

}
