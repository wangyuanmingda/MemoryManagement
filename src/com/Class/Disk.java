package com.Class;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Disk {
	public ArrayList<Instruction> list=new ArrayList<>();
	private int count=0,page=1;
	public Disk(String file) {
		readInstruction(file);
	}
	//����ָ��
	private void readInstruction(String file) {
		String s;
	try { 
		
		BufferedReader input = new BufferedReader(new FileReader(file)); //��ȡ��

		while((s = input.readLine())!=null){ //�ж��Ƿ���������һ��
		String info[] = s.split(" ");
		page=Integer.parseInt(info[1])/11;
		page++;
		Instruction newinstruction=new Instruction(info[0],info[1],page);

		list.add(newinstruction);
		System.out.println("order��"+info[0]+"  ins��"+info[1] +" page:"+page);
		
		}
		input.close(); 
		}catch (Exception e) { 

		} 
	
	}
	}
