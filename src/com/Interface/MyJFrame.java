package com.Interface;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;

public class MyJFrame extends JFrame {
	public JPanel bgjp;
	private JButton but_confirm;
	private JButton but_stop;
	private JButton but_reset;
	
	public MyJFrame() {
		setTitle("�ڴ����");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 200, 800, 400);
		setLayout(null);
		bgjp=(JPanel) getContentPane();
		bgjp.setBounds(0, 0, 800, 400);
		bgjp.setLayout(null);
		
		//�����
		  JTextPane jp = new JTextPane();
		  jp.setBackground(Color.WHITE);
		  jp.setText("");
		//  jp.setText(jp.getText()+"\n"+jp.getText());
		  jp.setBounds(550, 100, 200, 200);
		bgjp.add(jp);
		
		but_confirm=new JButton("ȷ��");
		but_stop=new JButton("��ͣ");
		but_reset=new JButton("��λ");
		add(but_confirm);
		add(but_stop);
		add(but_reset);
		but_confirm.setBounds(170, 50, 60, 30);
		but_stop.setBounds(270, 50, 60, 30);
		but_reset.setBounds(370, 50, 60, 30);
	}
}
