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

//��TimerӦ�ø��ÿ���ʱ��1.5s��һ��ָ�� 
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
//		System.out.println("��ǰ��"+newinsInstruction.page+"ҳ");
//		jp.setText("��ǰ��"+newinsInstruction.page+"ҳ"+"\n");
//		Logicmemory temp=new Logicmemory();
//		temp=mmlist.get(0);
//		temp.nowpage=FIFO[tail];
//    	Memory jh=(Memory) frame.bgjp.getComponent(4);
//    	jh.color=0;
//    	frame.repaint();
//	//	frame.bgjp.getComponent(0).getGraphics();
//		tail++;
		//������һ�β���Ϊcheck
		condition=check;
		//
		
		//����
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
	//����FIFO
	private void replace() {
		for(int i=0;i<mmlist.size();i++){
			Logicmemory temp=new Logicmemory();
			temp=mmlist.get(i);
			if(temp.nowpage==FIFO[head]){
				temp.nowpage=0;
				jp.setText(jp.getText()+"��"+i+"��"+"����"+FIFO[head]+"ҳ"+"\n");
				System.out.println("��"+i+"��"+"����"+FIFO[head]+"ҳ");
			
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
		//Ĭ�ϵ�һ��ָ���ǿ�ʼָ����㵱ǰ���
		switch(condition)
		{
		case check:{
		newinsInstruction=disk.list.get(next);
		next=newinsInstruction.next-1;
		jp.setText(jp.getText()+"��ǰ��"+newinsInstruction.order+"��"+newinsInstruction.page+"ҳ"+"\n");
		System.out.println("��ǰ��"+newinsInstruction.order+"��"+newinsInstruction.page+"ҳ");
		if((!findpage(newinsInstruction.page))){
			FIFO[tail]=newinsInstruction.page;				
			tail++;
			if(tail-head<4){
				//���û������ֱ�ӷŵ��յ�
				
				int emptyplace;
				emptyplace=findplace();
				temp=mmlist.get(emptyplace);
				temp.nowpage=FIFO[tail-1];
				System.out.println("��"+emptyplace+"�����"+temp.nowpage+"ҳ");
				jp.setText(jp.getText()+"��"+emptyplace+"�����"+temp.nowpage+"ҳ"+"\n");
				condition=check;
			}else{
				condition=change_out;			
			}
		}else{
			jp.setText(jp.getText()+"ҳ��"+newinsInstruction.page+"�Ѿ�����"+"\n");
			System.out.println("ҳ��"+newinsInstruction.page+"�Ѿ�����");
			condition=check;
			
		}
		break;
		}
		case change_in:{
			int emptyplace;
			emptyplace=findplace();
			temp=mmlist.get(emptyplace);
			temp.nowpage=FIFO[tail-1];
			jp.setText(jp.getText()+"��"+emptyplace+"�����"+temp.nowpage+"ҳ"+"\n");
			System.out.println("��"+emptyplace+"�����"+temp.nowpage+"ҳ");
			condition=check;
		}
		case change_out:{
			replace();
			condition=change_in;
		}
		}
		
	}       

}
