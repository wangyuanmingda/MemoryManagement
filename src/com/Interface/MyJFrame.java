package com.Interface;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.Timer;

import com.Class.Disk;
import com.Class.Logicmemory;
import com.Controller.controller;

public class MyJFrame extends JFrame {
	private static  ArrayList<Logicmemory> mmlist=new ArrayList<>();
	public JPanel bgjp;
	public  JTextPane jp;
	public JButton but_confirm;
	public JButton but_stop;
	public JButton but_reset;
	public Disk disk;
	private Timer timer;
	
	public MyJFrame(Disk disk) {
		this.disk=disk;

		setTitle("内存管理");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 200, 800, 400);
		setLayout(null);
		bgjp=(JPanel) getContentPane();
		bgjp.setBounds(0, 0, 800, 400);
		bgjp.setLayout(null);
		
		//输出框
		  jp = new JTextPane();
		  jp.setBackground(Color.WHITE);
		  jp.setText("");
		//  jp.setText(jp.getText()+"\n"+jp.getText());

		  JScrollPane scrollPane = new JScrollPane(jp);
		  scrollPane.setBounds(550, 100, 200, 200);
		bgjp.add(scrollPane);
		
		
		
		but_confirm=new JButton("开始");
		but_confirm.addActionListener(new ActionListener() {
			// 事件处理
			@Override
			public void actionPerformed(ActionEvent e) {
			// 获得容器，设置容器背景颜色
				timer.start();
			}
			});
		but_stop=new JButton("暂停");
		but_reset=new JButton("复位");
		add(but_confirm);
		add(but_stop);
		add(but_reset);
		but_confirm.setBounds(170, 50, 60, 30);
		but_stop.setBounds(270, 50, 60, 30);
		but_reset.setBounds(370, 50, 60, 30);
		
		
		for(int i=1;i<=4;i++){
			Logicmemory lm=new Logicmemory();
			Memory mem=new Memory("第"+i+"块", i);
			bgjp.add(mem);
			mmlist.add(lm);
		}
		
		ActionListener timelistener = new controller(disk,mmlist,this);
		timer = new Timer(1500, timelistener);
	}
}
