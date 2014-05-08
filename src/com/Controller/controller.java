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
import com.Interface.MyJFrame;
import com.Interface.Memory;

//��TimerӦ�ø��ÿ���ʱ��1.5s��һ��ָ�� 
public class controller implements ActionListener {
	
	static final int check=0;
	static final int change_in=1;
	static final int change_out=2;
	
	private int timer=0;
	private int lostcount=0;
	private JTextPane jp;	
	private Instruction newinsInstruction=new Instruction();
	private Disk disk;
	private ArrayList<Logicmemory> mmlist=new ArrayList<>();
	private int head=0,tail=0;
	private int[] FIFO=new int[400];
	private int condition;
	private Logicmemory temp=new Logicmemory();
	private MyJFrame frame;

	public int next=0;

	public controller(Disk disk,ArrayList<Logicmemory> mmlist, MyJFrame frame) {
		this.disk=disk;
		this.mmlist=mmlist;
		this.jp=frame.jp;
		this.frame=frame;

		
		
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
			int kuai=i+1;
			if(temp.nowpage==FIFO[head]){
				temp.nowpage=0;
				jp.setText(jp.getText()+"��"+kuai+"��"+"����"+FIFO[head]+"ҳ"+"\n");
				System.out.println("��"+kuai+"��"+"����"+FIFO[head]+"ҳ");
				
				head++;
				Memory mm=(Memory) frame.bgjp.getComponent(kuai+4);
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
	@Override
	public void actionPerformed(ActionEvent arg0) {
		//Ĭ�ϵ�һ��ָ���ǿ�ʼָ����㵱ǰ���
		timer++;
		if(timer==361){
			frame.timer.stop();
			jp.setText(jp.getText()+"ȱҳ��"+lostcount+"\n");
			System.out.println("ȱҳ��"+lostcount);
		}
		switch(condition)
		{
		case check:{
		newinsInstruction=disk.list.get(next);
		next=newinsInstruction.next-1;
		int show=next+1;
		jp.setText(jp.getText()+"��ǰ��"+newinsInstruction.order+"��"+newinsInstruction.page+"ҳ"+"ָ��"+show+"��\n");
		System.out.println("��ǰ��"+newinsInstruction.order+"��"+newinsInstruction.page+"ҳ");
		if((!findpage(newinsInstruction.page))){
			FIFO[tail]=newinsInstruction.page;		 		
			tail++;
			if(tail-head<5){
				//���û������ֱ�ӷŵ��յ�
				
				int emptyplace;
				emptyplace=findplace();
				temp=mmlist.get(emptyplace-1);
				temp.nowpage=FIFO[tail-1];
				
				System.out.println("��"+emptyplace+"�����"+temp.nowpage+"ҳ");
				jp.setText(jp.getText()+"��"+emptyplace+"�����"+temp.nowpage+"ҳ"+"\n");
				Memory mm=(Memory) frame.bgjp.getComponent(emptyplace+4);
				mm.color=0;
				lostcount++;
				frame.repaint();
				
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
			temp=mmlist.get(emptyplace-1);
			temp.nowpage=FIFO[tail-1];
			jp.setText(jp.getText()+"��"+emptyplace+"�����"+temp.nowpage+"ҳ"+"\n");
			System.out.println("��"+emptyplace+"�����"+temp.nowpage+"ҳ");
			Memory mm=(Memory) frame.bgjp.getComponent(emptyplace+4);
			mm.color=0;
			lostcount++;
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

}
