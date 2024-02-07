package com.VisitorsManagement;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import java.awt.event.*;

public class About extends JFrame implements ActionListener{

	JLabel about1,about2,about3,about4, team;
	JPanel jp;
	JLabel label;
	JButton back;
	
	public About() {
		
		setSize(1400,800);
		setTitle("About");
		setLayout(null);
		setLocationRelativeTo(null); 
		setResizable(false);
		
		
		
		ImageIcon bg1 = new ImageIcon(ClassLoader.getSystemResource("about.jpg"));
	    Image bg2= bg1.getImage().getScaledInstance(1400, 800, Image.SCALE_DEFAULT);
	    ImageIcon bg3 = new ImageIcon(bg2);
	    JLabel image = new JLabel(bg3);
	    image.setBounds(0,0, 1400, 800);
	    add(image);
	    
	    
	    
	    
	    
	    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("information.png"));
	    Image i2 = i1.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
	    ImageIcon i3 = new ImageIcon(i2);
	    JLabel image1 = new JLabel("ABOUT IT",i3,SwingConstants.CENTER);
	    image1.setForeground(new Color(137, 207, 240));
	    image1.setVerticalTextPosition(JLabel.CENTER);
	    image1.setHorizontalTextPosition(JLabel.RIGHT);
	    image1.setFont(new Font("Arial",Font.BOLD,40));
	    image1.setBounds(550,0, 300, 150);
	    add(image1);
	    image.add(image1);
	    
	    
	   	
		about1=new JLabel("Team Member:-");
		
		about1.setBounds(100,520,1000,40);
		about1.setFont(new Font("Arial",Font.BOLD,30));
		about1.setForeground(new Color(164, 222, 2));
		add(about1);
		image.add(about1);
		
		about2=new JLabel("Deepak ,  Nikhil,  Kunwar Dev ,  Manish ,  Harsh,  Anvesh ,  Shivam ");
		
		about2.setBounds(100,580,900,80);
		about2.setFont(new Font("Arial",Font.BOLD,20));
		about2.setForeground(new Color(164, 222, 2));
		add(about2);
		image.add(about2);

	
		
		back=new JButton("<< Back");
		back.setBounds(40,70,200,30);
		back.setFont(new Font("Arial",Font.BOLD,20));
		back.setContentAreaFilled(false);
		back.setFocusPainted(false);
		back.setBorderPainted(false);
		back.setForeground(new Color(164, 222, 2));
		image.add(back);
		back.addActionListener(this);
		
		
		
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new About();

	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		if(ae.getSource()==(back))
		{
			this .dispose();
		}
		
	}

}
