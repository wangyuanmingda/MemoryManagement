package com.Controller;

import java.awt.Color;
import java.awt.Frame;
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
import com.Class.MyStack;
import com.Interface.MyJFrame;
import com.Interface.Memory;
 
public class controller implements ActionListener {
	
	static final int check=0;
	static final int change_in=1;
	static final int change_out=2;
	private static final int free=0;
	

	private int timer=0;
	private int lostcount=0;
	private JTextPane jp;	
	private Instruction newinsInstruction=new Instruction();
	private Disk disk;
	private ArrayList<Logicmemory> mmlist=new ArrayList<>();

	private int[] FIFO=new int[400];
	private MyStack LRU=new MyStack();
	private MyStack support=new MyStack();
	private int condition;
	private Logicmemory temp=new Logicmemory();
	private MyJFrame frame;

	public int next=0;
	public int pattern=0;
	public int head=0,tail=0;
	
	public void init() {
		head=0;
		tail=0;
		next=0;
		condition=0;
		timer=0;
		lostcount=0;
		LRU.init();
		support.init();
		for(int i=0;i<mmlist.size();i++){
			Logicmemory temp=new Logicmemory();
			temp=mmlist.get(i);
			temp.nowpage=free;
			Memory mm=(Memory)frame.bgjp.getComponent(6+i);
			mm.color=1;		
		}
		
	}

	public controller(Disk disk,ArrayList<Logicmemory> mmlist, MyJFrame frame) {
		this.disk=disk;
		this.mmlist=mmlist;
		this.jp=frame.jp;
		this.frame=frame;
		
		
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

	private void replace() {
		for(int i=0;i<mmlist.size();i++){
			Logicmemory temp=new Logicmemory();
			temp=mmlist.get(i);
			int kuai=i+1;
			if(temp.nowpage==FIFO[head]){
				temp.nowpage=0;
				jp.setText(jp.getText()+"第"+kuai+"块"+"换出"+FIFO[head]+"页"+"\n");
		//		System.out.println("第"+kuai+"块"+"换出"+FIFO[head]+"页");
				
				head++;
				Memory mm=(Memory) frame.bgjp.getComponent(kuai+5);
				mm.color=1;
				frame.repaint();
				return;
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
	
	private void FIFO() {

		//默认第一条指令是开始指令，先算当前情况

		switch(condition)
		{
		case check:{
			
			timer++;
			if(timer==320){
				jp.setText(jp.getText()+"缺页数"+lostcount+"\n");
				frame.timer.stop();
				System.out.println("缺页数"+lostcount);
				condition=check;
				return;
			}
		//可以改用timer
		newinsInstruction=disk.list.get(next);
		next++;
		int show=disk.list.get(next).ins;
		jp.setText(jp.getText()+"当前是"+newinsInstruction.ins+"号"+newinsInstruction.page+"页"+"指向"+show+"号\n");
	//	System.out.println("当前是"+newinsInstruction.order+"号"+newinsInstruction.page+"页");
		if((!findpage(newinsInstruction.page))){
			FIFO[tail]=newinsInstruction.page;		 		
			tail++;
			lostcount++;
			if(tail-head<5){
				//如果没满，就直接放到空地
				
				int emptyplace;
				emptyplace=findplace();
				temp=mmlist.get(emptyplace-1);
				temp.nowpage=FIFO[tail-1];
				
	//			System.out.println("第"+emptyplace+"块调入"+temp.nowpage+"页");
				jp.setText(jp.getText()+"第"+emptyplace+"块调入"+temp.nowpage+"页"+"\n");
				Memory mm=(Memory) frame.bgjp.getComponent(emptyplace+5);
				mm.color=0;

				frame.repaint();
				
				condition=check;
			}else{
				condition=change_out;			
			}
		}else{
			jp.setText(jp.getText()+"页面"+newinsInstruction.page+"已经存在"+"\n");
	//		System.out.println("页面"+newinsInstruction.page+"已经存在");
			condition=check;
			
		}
		break;
		}
		case change_in:{
			int emptyplace;
			emptyplace=findplace();
			temp=mmlist.get(emptyplace-1);
			temp.nowpage=FIFO[tail-1];
			jp.setText(jp.getText()+"第"+emptyplace+"块调入"+temp.nowpage+"页"+"\n");
	//		System.out.println("第"+emptyplace+"块调入"+temp.nowpage+"页");
			Memory mm=(Memory) frame.bgjp.getComponent(emptyplace+5);
			mm.color=0;
			frame.repaint();
			condition=check;
			break;
		}
		case change_out:{
			replace();
			condition=change_in;
			break;
		}
		}
		

	}
	
	private void LRU() {
		//LRU的实现算法实际上跟FIFO差不多，只不过是用栈

		//默认第一条指令是开始指令，先算当前情况

		switch(condition)
		{
		
		case check:{
			
			timer++;
			if(timer==320){
				frame.timer.stop();
				jp.setText(jp.getText()+"缺页数"+lostcount+"\n");
//				System.out.println("缺页数"+lostcount);
				condition=check;
				return;
			}
			
			
			newinsInstruction=disk.list.get(next);
			next++;
			int show=disk.list.get(next).ins;
		jp.setText(jp.getText()+"当前是"+newinsInstruction.ins+"号"+newinsInstruction.page+"页"+"指向"+show+"号\n");
//		System.out.println("当前是"+newinsInstruction.order+"号"+newinsInstruction.page+"页");
	
		
		if((!findpage(newinsInstruction.page))){
			lostcount++;
			if(LRU.size<4){
				//如果没满，就直接放到空地
				LRU.push(newinsInstruction.page);	
				int emptyplace;
				emptyplace=findplace();
				temp=mmlist.get(emptyplace-1);
				temp.nowpage=LRU.gethead();
				
	//			System.out.println("第"+emptyplace+"块调入"+temp.nowpage+"页");
				jp.setText(jp.getText()+"第"+emptyplace+"块调入"+temp.nowpage+"页"+"\n");
				Memory mm=(Memory) frame.bgjp.getComponent(emptyplace+5);
				mm.color=0;
	
				frame.repaint();
				
				condition=check;
			}else{
				
				condition=change_out;			
			}
		}else{
			jp.setText(jp.getText()+"页面"+newinsInstruction.page+"已经存在"+"\n");
			System.out.println("页面"+newinsInstruction.page+"已经存在");
			LRU.push(newinsInstruction.page);	

			int lasttime=LRU.find(newinsInstruction.page);
			while(LRU.top!=lasttime-1){
				support.push(LRU.pop());
				
			}
			support.pop();
			while(support.top!=-1){
				LRU.push(support.pop());
			}
			
			
			
			condition=check;
			
		}
		break;
		}
		case change_in:{
			LRU.push(newinsInstruction.page);	
			int emptyplace;
			emptyplace=findplace();
			temp=mmlist.get(emptyplace-1);
			temp.nowpage=LRU.gethead();
			
			jp.setText(jp.getText()+"第"+emptyplace+"块调入"+temp.nowpage+"页"+"\n");
	//		System.out.println("第"+emptyplace+"块调入"+temp.nowpage+"页");
			Memory mm=(Memory) frame.bgjp.getComponent(emptyplace+5);
			mm.color=0;
			frame.repaint();
			condition=check;
			break;
		}
		case change_out:{
			LRUreplace();
			condition=change_in;
			break;
		}
		}
		


	}
	
	
	private void LRUreplace() {
		for(int i=0;i<mmlist.size();i++){
			Logicmemory temp=new Logicmemory();
			temp=mmlist.get(i);
			int kuai=i+1;
			if(temp.nowpage==LRU.getbottom()){
				temp.nowpage=0;
				jp.setText(jp.getText()+"第"+kuai+"块"+"换出"+LRU.getbottom()+"页"+"\n");
		//		System.out.println("第"+kuai+"块"+"换出"+FIFO[head]+"页");
				while(LRU.top!=-1){
					support.push(LRU.pop());
					
				}
				support.pop();
				while(support.top!=-1){
					LRU.push(support.pop());
				}
				
				Memory mm=(Memory) frame.bgjp.getComponent(kuai+5);
				mm.color=1;
				frame.repaint();
				return;
			}
		
		}
		
	
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0){  
		
		if(pattern==0){
			System.out.println("FIFO");
			FIFO();
		}else{
			System.out.println("LRU");
			LRU();
		}
}
}