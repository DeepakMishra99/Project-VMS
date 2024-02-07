package com.VisitorsManagement;

import javax.swing.*;

import com.util.data.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.*;
import java.sql.*;

public class User_Update extends JFrame implements ActionListener,ItemListener{
	JLabel lbluserid,lblusernm,lbluserem,lbluserph,lbluserad,lblusergen,lbluserpaas;
	JComboBox cb1;
	JButton b_Find,b_Update,b_Clear,b_Close;
	JTextField txtusernm,txtuserem,txtuserph,txtuserad,txtusergen,txtuserpas;
	Connection con;PreparedStatement st;ResultSet rs;
	public User_Update()
	{
		setSize(1400,800);
		setTitle("User Update");
		setLayout(null);
		setLocationRelativeTo(null); 
		setResizable(false);
		
		
		ImageIcon bg1 = new ImageIcon(ClassLoader.getSystemResource("all_bg.jpg"));
	    Image bg2= bg1.getImage().getScaledInstance(1400, 800, Image.SCALE_DEFAULT);
	    ImageIcon bg3 = new ImageIcon(bg2);
	    JLabel image = new JLabel(bg3);
	    image.setBounds(0,0, 1400, 800);
	    add(image);
	    
	    ImageIcon logo1 = new ImageIcon(ClassLoader.getSystemResource("user_logo.png"));
	    Image logo2= logo1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
	    ImageIcon logo3 = new ImageIcon(logo2);
	    JLabel image_logo = new JLabel("USER",logo3,SwingConstants.CENTER);
	    image_logo.setVerticalTextPosition(JLabel.BOTTOM);
	    image_logo.setHorizontalTextPosition(JLabel.CENTER);
	    image_logo.setFont(new Font("Arial",Font.BOLD,16));
	    image_logo.setBounds(10,10, 100, 150);
	    add(image_logo);
	    image.add(image_logo);
	    
	    
	    
	    
	    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("update.png"));
	    Image i2 = i1.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
	    ImageIcon i3 = new ImageIcon(i2);
	    JLabel image1 = new JLabel("User Update",i3,SwingConstants.CENTER);
	    image1.setVerticalTextPosition(JLabel.CENTER);
	    image1.setHorizontalTextPosition(JLabel.RIGHT);
	    image1.setForeground(new Color(0,0,128));
	    image1.setFont(new Font("Arial",Font.BOLD,30));
	    image1.setBounds(550,0, 400, 150);
	    add(image1);
	    image.add(image1);
	    
	
		
	    lbluserid=new JLabel("USER ID:");
	    lbluserid.setBounds(450,150,250,30);
	    lbluserid.setFont(new Font("Arial",Font.BOLD,16));
		add(lbluserid);
		image.add(lbluserid);
		cb1=new JComboBox();
		cb1.setBounds(600,150,250,30);
		cb1.insertItemAt("", 0);
		add(cb1);
		image.add(cb1);
		filldata();
		cb1.addItemListener(this);
		
		lblusernm=new JLabel("USER NAME:");
		lblusernm.setBounds(450,200,250,30);
		lblusernm.setFont(new Font("Arial",Font.BOLD,16));
		add(lblusernm);
		image.add(lblusernm);
		txtusernm=new JTextField();
		txtusernm.setBounds(600,200,250,30);
		add(txtusernm);
		image.add(txtusernm);
		
		lbluserem=new JLabel("USER EMAIL:");
		lbluserem.setBounds(450,250,250,30);
		lbluserem.setFont(new Font("Arial",Font.BOLD,16));
		add(lbluserem);
		image.add(lbluserem);
		txtuserem=new JTextField();
		txtuserem.setBounds(600,250,250,30);
		add(txtuserem);
		image.add(txtuserem);
		
		lbluserph=new JLabel("USER PHONE:");
		lbluserph.setBounds(450,300,250,30);
		lbluserph.setFont(new Font("Arial",Font.BOLD,16));
		add(lbluserph);
		image.add(lbluserph);
		txtuserph=new JTextField();
		txtuserph.setBounds(600,300,250,30);
		add(txtuserph);
		image.add(txtuserph);
		
		lbluserad=new JLabel("USER ADDRESS:");
		lbluserad.setBounds(450,350,250,30);
		lbluserad.setFont(new Font("Arial",Font.BOLD,16));
		add(lbluserad);
		image.add(lbluserad);
		txtuserad=new JTextField();
		txtuserad.setBounds(600,350,250,30);
		add(txtuserad);
		image.add(txtuserad);
		
		lblusergen=new JLabel("USER GENDER:");
		lblusergen.setBounds(450,400,250,30);
		lblusergen.setFont(new Font("Arial",Font.BOLD,16));
		add(lblusergen);
		image.add(lblusergen);
		txtusergen=new JTextField();
		txtusergen.setBounds(600,400,250,30);
		add(txtusergen);
		image.add(txtusergen);
		
		lbluserpaas=new JLabel("PASSWORD:");
		lbluserpaas.setBounds(450,450,250,30);
		lbluserpaas.setFont(new Font("Arial",Font.BOLD,16));
		add(lbluserpaas);
		image.add(lbluserpaas);
		txtuserpas=new JTextField();
		txtuserpas.setBounds(600,450,250,30);
		add(txtuserpas);
		image.add(txtuserpas);
		
	
		b_Update=new JButton("UPDATE");
		b_Update.setBounds(400,500,100,30);
		b_Update.setBackground(new Color(173,216,230));
		add(b_Update);
		image.add(b_Update);
		
		b_Clear=new JButton("CLEAR");
		b_Clear.setBounds(600,500,100,30);
		b_Clear.setBackground(new Color(173,216,230));
		add(b_Clear);
		image.add(b_Clear);
		
		b_Close=new JButton("CLOSE");
		b_Close.setBounds(800,500,100,30);
		b_Close.setBackground(new Color(173,216,230));
		add(b_Close);
		image.add(b_Close);
		
	
		b_Update.addActionListener(this);
		b_Clear.addActionListener(this);
		b_Close.addActionListener(this);
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new User_Update();

	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		
		if(ae.getSource()==(b_Update))
		{
			if (cb1.getSelectedItem().toString().length()==0|| txtusernm.getText().length()==0 || txtuserem.getText().length()==0 || txtuserph.getText().length()==0 || txtuserad.getText().length()==0|| txtusergen.getText().length()==0|| txtuserpas.getText().length()==0)
			{
				JOptionPane.showMessageDialog(this,"Check all data first..");
			}
			else
			{
				int opt=JOptionPane.showConfirmDialog(null,"Are you sure to Update","UPDATE",JOptionPane.YES_NO_OPTION);
				if(opt==0)
				{
				String uid=cb1.getSelectedItem().toString();
				String unm=txtusernm.getText();
				String uem=txtuserem.getText();
				String uph=txtuserph.getText();
				String uad=txtuserad.getText();
				String ug=txtusergen.getText();
				String up=txtuserpas.getText();
				
				UserDataUpdate obj=new UserDataUpdate();
				String msg=obj.updateData(unm, uem, uph, uad,ug,up,uid);				
				JOptionPane.showMessageDialog(this, msg);
				txtusernm.setText("");
				txtuserem.setText("");
				txtuserph.setText("");
				txtuserad.setText("");
				txtusergen.setText("");
				txtuserpas.setText("");
				}
			}
	
		}
		if(ae.getSource()==(b_Clear))
		{
			
			txtusernm.setText("");
			txtuserem.setText("");
			txtuserph.setText("");
			txtuserad.setText("");
			txtusergen.setText("");
			txtuserpas.setText("");
		}
		if(ae.getSource()==(b_Close))
		{
			this.dispose();			
		}
	}
void filldata()
{
	try {
		//Step-1 Load the driver
		Class.forName("oracle.jdbc.driver.OracleDriver");
		//Step-2 Connection create
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
		//Step-3 Statement create
		String sql="select userid from usermaster";
		st=con.prepareStatement(sql);
		rs=st.executeQuery();
		while (rs.next())
		{
			cb1.addItem(rs.getString(1));
		}
		}
	catch(Exception ex) {}

		

	}
@Override
public void itemStateChanged(ItemEvent e) {
	// TODO Auto-generated method stub
	if(e.getSource()==(cb1))
	{
			String k=cb1.getSelectedItem().toString();
			UserDataFind obj=new UserDataFind();
			String s[]=obj.findData(k);
			
			if (s!=null) {
			txtusernm.setText(s[0]);
					txtuserem.setText(s[1]);
					txtuserph.setText(s[2]);
					txtuserad.setText(s[3]);
					txtusergen.setText(s[4]);
					txtuserpas.setText(s[5]);
			}
				else
				{
					JOptionPane.showMessageDialog(this, "Data not found..");
				}		
			}
	
}
}

