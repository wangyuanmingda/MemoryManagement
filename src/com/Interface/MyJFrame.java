package com.Interface;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

public class MyJFrame extends JFrame {
	public JPanel bgjp;
	public  JTextPane jp;
	public JButton but_confirm;
	public JButton but_stop;
	public JButton but_reset;
	
	
	public MyJFrame() {
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
	//	but_confirm.addActionListener(new start_task());
		but_stop=new JButton("暂停");
		but_reset=new JButton("复位");
		add(but_confirm);
		add(but_stop);
		add(but_reset);
		but_confirm.setBounds(170, 50, 60, 30);
		but_stop.setBounds(270, 50, 60, 30);
		but_reset.setBounds(370, 50, 60, 30);
	}

}
