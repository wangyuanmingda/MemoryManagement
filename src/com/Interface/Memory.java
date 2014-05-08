package com.Interface;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;
//总共有四个
public class Memory extends JPanel {
	public JLabel condition;
	public int color=1;
	
	public Memory(String s,int count) {
		 condition = new JLabel(s);
		 setLayout(null);
		 setBounds(100*count, 100, 101, 150);
		 add(condition);
		 condition.setBounds(29, 100, 50, 50);
	}
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		if(color==1){
		g.setColor(Color.YELLOW);
		}else{
			g.setColor(Color.blue);
		}
		g.fillRect(0, 0, 100, 100);
		g.setColor(Color.BLACK);
		g.drawLine(100, 0, 100, 100);
		g.drawLine(0, 0, 0, 100);
		g.drawLine(0, 0,100, 0);
		g.drawLine(0, 100, 100, 100);
		

		

	}
}
