package com.Interface;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.Timer;

import com.Class.Disk;
import com.Class.Logicmemory;
import com.Controller.controller;

public class MyJFrame extends JFrame {

	private static final int free=0;
	public JPanel bgjp;
	public  JTextPane jp;
	public JButton but_confirm;
	public JButton but_stop;
	public JButton but_reset;
	public Disk disk;
	
	private Timer timer;
	private controller timelistener;
	private static  ArrayList<Logicmemory> mmlist=new ArrayList<>();
	
	public MyJFrame(Disk disk) {
		this.disk=disk;

		setTitle("内存管理");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 200, 800, 350);
		setLayout(null);
		bgjp=(JPanel) getContentPane();
		bgjp.setBounds(0, 0, 800, 400);
		bgjp.setLayout(null);
		
		//多选框
		final JComboBox dbtype = new JComboBox();
		dbtype.addItem("FIFO算法");
		dbtype.addItem("LRU算法");
		dbtype.setSelectedItem("FIFO算法");//默认选中南京
		dbtype.addItemListener(new ItemListener(){
		public void itemStateChanged(ItemEvent evt) {
		if(evt.getStateChange() == ItemEvent.SELECTED){
		 try{
		    
		 }catch (Exception e){
		      
		   }
		 } 
		}		   
		  });
		add(dbtype);
		dbtype.setBounds(610, 30, 80, 30);
		
		//输出框
		  jp = new JTextPane();
		  jp.setBackground(Color.WHITE);
		  jp.setText("");
		//  jp.setText(jp.getText()+"\n"+jp.getText());

		  JScrollPane scrollPane = new JScrollPane(jp);
		  scrollPane.setBounds(550, 70, 200, 200);
		bgjp.add(scrollPane);
		
		
		
		but_confirm=new JButton("开始");	
		but_confirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				timer.start();
			}
			});
		
		
		
		but_stop=new JButton("暂停");
		but_stop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				timer.stop();
				System.out.println("进去了");
			}
			});
		but_reset=new JButton("复位");
		but_reset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//需要复位的，next,memory，logicmm
				timer.stop();
				timelistener.next=0;
				for(int i=0;i<mmlist.size();i++){
					Logicmemory temp=new Logicmemory();
					temp=mmlist.get(i);
					temp.nowpage=free;
					Memory mm=(Memory)bgjp.getComponent(5+i);
					mm.color=1;

				}
				repaint();
			}
			});
		
		
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
		
		timelistener = new controller(disk,mmlist,this);
		timer = new Timer(1500, timelistener);
	}
}
