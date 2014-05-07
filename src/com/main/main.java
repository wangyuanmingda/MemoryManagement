package com.main;


import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.Timer;


import com.Class.Disk;
import com.Class.Logicmemory;
import com.Controller.controller;
import com.Interface.Memory;
import com.Interface.MyJFrame;

public class main {
	private static  ArrayList<Logicmemory> mmlist=new ArrayList<>();
	private static Timer timer;

	
	public static void main(String[] args) {
		Disk disk=new Disk();
		MyJFrame frame=new MyJFrame();
		
		
		for(int i=1;i<=4;i++){
			Logicmemory lm=new Logicmemory();
			Memory mem=new Memory("µÚ"+i+"¿é", i);
			frame.bgjp.add(mem);
			mmlist.add(lm);
		}
		ActionListener timelistener = new controller(disk,mmlist);
		timer = new Timer(1500, timelistener);
		timer.start();
		frame.setVisible(true);
	}
}
