package com.Class;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Disk {
	public ArrayList<Instruction> list=new ArrayList<>();
	private int count=0,page=1;
	public Disk() {
		readInstruction();
	}
	//����ָ��
	private void readInstruction() {
		String s;
	try { 
		
		BufferedReader input = new BufferedReader(new FileReader("E:\\123.txt")); //��ȡ��

		while((s = input.readLine())!=null){ //�ж��Ƿ���������һ��
		String info[] = s.split(" ");
		count++;
		if(count==11){
			count=1;
			page++;
		}
		Instruction newinstruction=new Instruction(info[0],info[1],page);

		list.add(newinstruction);
		System.out.println("order��"+info[0]+"  next��"+info[1]);
		
		}
		input.close(); 
		}catch (Exception e) { 

		} 
	
	}
	}
