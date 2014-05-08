package com.main;



import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;


import com.Class.Disk;
import com.Class.Logicmemory;
import com.Controller.controller;
import com.Interface.Memory;
import com.Interface.MyJFrame;

public class main {

	
	public static void main(String[] args) {
		Disk disk=new Disk("E:\\123.txt");

		MyJFrame frame=new MyJFrame(disk);
		
				

		frame.setVisible(true);
	}
}
