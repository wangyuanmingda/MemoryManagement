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

//��TimerӦ�ø��ÿ���ʱ��1.5s��һ��ָ�� 
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
		System.out.println("��ǰ��"+newinsInstruction.page+"ҳ");
		Logicmemory temp=new Logicmemory();
		temp=mmlist.get(0);
		temp.nowpage=FIFO[tail];
		tail++;
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
		//Ĭ�ϵ�һ��ָ���ǿ�ʼָ����㵱ǰ���
		switch(condition)
		{
		case check:{
		newinsInstruction=disk.list.get(next);
		next=newinsInstruction.next-1;
		System.out.println("��ǰ��"+newinsInstruction.order+"��"+newinsInstruction.page+"ҳ");
		if((!findpage(newinsInstruction.page))){
			if(tail-head<4){
				//���û������ֱ�ӷŵ��յ�
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
			System.out.println("ҳ��"+newinsInstruction.page+"�Ѿ�����");
		}
		break;
		}
		//�����ж��ǲ�������ͬ��ҳ
		case change_in:{

		}
		}
		//��ɨһ����û��λ��
		
	}       

}
