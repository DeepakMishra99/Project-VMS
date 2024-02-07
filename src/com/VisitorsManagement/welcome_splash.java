package com.VisitorsManagement;

import java.awt.event.*;
import java.sql.*;
import java.util.logging.Logger;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class welcome_splash extends JFrame {
	static JLabel l1,l2;
	
	static JProgressBar jp;
	int val=0;
	welcome_splash()
	{
		setSize(1400,800);
		setLayout(null);
		setBounds(150,50,1400,800);
		setLocationRelativeTo(null); 
		setResizable(false);
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Splash.gif"));
	    Image i2 = i1.getImage().getScaledInstance(1400, 800, Image.SCALE_DEFAULT);
	    ImageIcon i3 = new ImageIcon(i2);
	    JLabel image = new JLabel(i3);
	    image.setBounds(0,0, 1400, 800);
	    add(image);
		
		jp=new JProgressBar();
		jp.setMinimum(0);
		jp.setMaximum(100);
		jp.setForeground(new Color(153,0,0));
		
		
		jp.setBounds(410,600,600,30);
		add(jp);
		image.add(jp);
		
		l1=new JLabel();
		l1.setBounds(710, 550, 100, 50);
		l1.setFont(new Font("Arial Rounded MT Bold",Font.BOLD,20));
		add(l1);
		image.add(l1);
		
	

		
		
		setUndecorated(true);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i;
		welcome_splash d=new welcome_splash();
		try
		{

		
		
		for(i=0;i<=100;i++)
		{
			Thread.sleep(35);	
			d.jp.setValue(i);
			d.l1.setText(String.valueOf(i)+"%");
			if(i==100)
			{
				jp.setVisible(false);
				
			    
			}
			
			
			
		}
		

		d.dispose();
		new welcome();

		
		}
		catch(InterruptedException ae){
			
		}
		
		
}}