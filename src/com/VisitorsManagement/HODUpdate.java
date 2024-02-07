package com.VisitorsManagement;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;


import java.awt.event.*;
import java.sql.*;

import javax.swing.*;

import com.util.data.*;

public class HODUpdate extends JFrame implements ActionListener,KeyListener,ItemListener {
	JLabel lbl,lblhodid,lblhodname,lblhodemail,lblhodphone,lblhodaddress,lbldepid,lblshiftid;
	JTextField txthodname,txthodemail,txthodphone,txthodaddress,txtdepid,txtshiftid;
	JButton btnfind,btnupdate,btnclear,btnclose;
	Connection con;
	PreparedStatement st;
	ResultSet rs;
	JComboBox combohodid,combodepid,comboshid;
	public HODUpdate() {
		setSize(1400,800); 
		setLayout(null);
		setTitle("HOD Update");
		setLocationRelativeTo(null); 
		setResizable(false);
		
		ImageIcon bg1 = new ImageIcon(ClassLoader.getSystemResource("all_bg.jpg"));
	    Image bg2= bg1.getImage().getScaledInstance(1400, 800, Image.SCALE_DEFAULT);
	    ImageIcon bg3 = new ImageIcon(bg2);
	    JLabel image = new JLabel(bg3);
	    image.setBounds(0,0, 1400, 800);
	    add(image);
	    
	    ImageIcon logo1 = new ImageIcon(ClassLoader.getSystemResource("hod.png"));
	    Image logo2= logo1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
	    ImageIcon logo3 = new ImageIcon(logo2);
	    JLabel image_logo = new JLabel("HEAD OF DEPARTMENT",logo3,SwingConstants.CENTER);
	    image_logo.setVerticalTextPosition(JLabel.BOTTOM);
	    image_logo.setHorizontalTextPosition(JLabel.CENTER);
	    image_logo.setFont(new Font("Arial",Font.BOLD,16));
	    image_logo.setBounds(10,10,200, 150);
	    add(image_logo);
	    image.add(image_logo);
	    
	    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("update.png"));
	    Image i2 = i1.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
	    ImageIcon i3 = new ImageIcon(i2);
	    JLabel image1 = new JLabel("HOD Update",i3,SwingConstants.CENTER);
	    image1.setVerticalTextPosition(JLabel.CENTER);
	    image1.setHorizontalTextPosition(JLabel.RIGHT);
	    image1.setForeground(new Color(0,0,128));
	    image1.setFont(new Font("Arial",Font.BOLD,30));
	    image1.setBounds(550,0, 300, 150);
	    add(image1);
	    image.add(image1);
				
	    lblhodid=new JLabel("HOD ID:");
	    lblhodid.setBounds(400,150,150,30);
	    lblhodid.setFont(new Font("Arial",Font.BOLD,16));
		add(lblhodid);
		image.add(lblhodid);
		combohodid=new JComboBox();
		combohodid.setBounds(600,150,250,30);
		combohodid.insertItemAt("", 0);
		add(combohodid);
		image.add(combohodid);
		filldata();
		combohodid.addItemListener(this);
		
		lblhodname=new JLabel("HOD NAME:");
		lblhodname.setBounds(400,200,150,30);
		lblhodname.setFont(new Font("Arial",Font.BOLD,16));
     	add(lblhodname);
		image.add(lblhodname);
		txthodname=new JTextField();
		txthodname.setBounds(600,200,250,30);
		add(txthodname);
		image.add(txthodname);
			
		lblhodemail=new JLabel("HOD EMAIL:");
		lblhodemail.setBounds(400,250,150,30);
		lblhodemail.setFont(new Font("Arial",Font.BOLD,16));
     	add(lblhodemail);
		image.add(lblhodemail);
		txthodemail=new JTextField();
		txthodemail.setBounds(600,250,250,30);
		add(txthodemail);
		image.add(txthodemail);
		
		lblhodphone=new JLabel("HOD PHONE No.:");
		lblhodphone.setBounds(400,300,200,30);
		lblhodphone.setFont(new Font("Arial",Font.BOLD,16));
     	add(lblhodphone);
		image.add(lblhodphone);
		txthodphone=new JTextField();
		txthodphone.setBounds(600,300,250,30);
		add(txthodphone);
		image.add(txthodphone);
		
		lblhodaddress=new JLabel("HOD ADDRESS:");
		lblhodaddress.setBounds(400,350,200,30);
		lblhodaddress.setFont(new Font("Arial",Font.BOLD,16));
     	add(lblhodaddress);
		image.add(lblhodaddress);
		txthodaddress=new JTextField();
		txthodaddress.setBounds(600,350,250,30);
		add(txthodaddress);
		image.add(txthodaddress);
		
		lbldepid=new JLabel("DEPARTMENT ID:");
		lbldepid.setBounds(400,400,200,30);
		lbldepid.setFont(new Font("Arial",Font.BOLD,16));
     	add(lbldepid);
		image.add(lbldepid);
		combodepid=new JComboBox();
		combodepid.setBounds(600,400,250,30);
		combodepid.insertItemAt("", 0);
		add(combodepid);
		image.add(combodepid);
		filldepid();
		
		lblshiftid=new JLabel("SHIFT ID:");
		lblshiftid.setBounds(400,450,200,30);
		lblshiftid.setFont(new Font("Arial",Font.BOLD,16));
     	add(lblshiftid);
		image.add(lblshiftid);
		comboshid=new JComboBox();
		comboshid.setBounds(600,450,250,30);
		comboshid.insertItemAt("", 0);
		add(comboshid);
		image.add(comboshid);
		fillshid();

		
	
		
		btnupdate = new JButton("UPDATE");
		btnupdate.setBounds(400,520,150,30);
		btnupdate.setFont(new Font("Arial",Font.BOLD,16));
		btnupdate.setBackground(new Color(173,216,230));
		add(btnupdate);
		image.add(btnupdate);
		
		btnclear = new JButton("CLEAR");
		btnclear.setBounds(600,520,150,30);
		btnclear.setFont(new Font("Arial",Font.BOLD,16));
		btnclear.setBackground(new Color(173,216,230));
		add(btnclear);
		image.add(btnclear);
		
		btnclose = new JButton("CLOSE");
		btnclose.setBounds(800,520,150,30);
		btnclose.setFont(new Font("Arial",Font.BOLD,16));
		btnclose.setBackground(new Color(173,216,230));
		add(btnclose);
		image.add(btnclose);
		
		
		btnupdate.addActionListener(this);
		btnclear.addActionListener(this);
		btnclose.addActionListener(this);
		
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new HODUpdate();
	}
	
	void fillshid()
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Step-2 Connection create
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//Step-3 Statement create
			String sql="select SHIFTID from shiftmaster";
			st=con.prepareStatement(sql);
			rs=st.executeQuery();
			while(rs.next())
			{
				comboshid.addItem(rs.getString(1));
			}
		}
		catch (Exception ex)
		{
			
		}
			
		}
		void filldepid()
		{
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				//Step-2 Connection create
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
				//Step-3 Statement create
				String sql="select departmentid from departments";
				st=con.prepareStatement(sql);
				rs=st.executeQuery();
				while(rs.next())
				{
					combodepid.addItem(rs.getString(1));
				}
			}
			catch (Exception ex) {
				
			}

}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		
		if(ae.getSource()==btnupdate)
		{
			if ( txthodname.getText().length()==0 || txthodphone.getText().length()==0 || txthodemail.getText().length()==0 || txthodaddress.getText().length()==0 || txtdepid.getText().length()==0|| txtshiftid.getText().length()==0)
			{
				JOptionPane.showMessageDialog(this, "Check all data first..");
			}
			else{
				int opt=JOptionPane.showConfirmDialog(null,"Are you sure to Update","UPDATE",JOptionPane.YES_NO_OPTION);
				if(opt==0)
				{
				String hid=combohodid.getSelectedItem().toString();
				String hna=txthodname.getText();
				String hem=txthodemail.getText();
				String hph=txthodphone.getText();
				String hadd=txthodaddress.getText();
				String dep=txtdepid.getText();
				String shf=txtshiftid.getText();
							
				HODUpdateHandle obj=new HODUpdateHandle();
				String msg=obj.updatedata(hna,hem,hph,hadd,dep,shf,hid);
				JOptionPane.showMessageDialog(this, msg);				
				
				txthodname.setText("");
				txthodemail.setText("");
				txthodphone.setText("");
				txthodaddress.setText("");
				txtdepid.setText("");
				txtshiftid.setText("");
				}
				
			}
		}
		if(ae.getSource()==btnclear)
		{
		
			txthodname.setText("");
			txthodemail.setText("");
			txthodphone.setText("");
			txthodaddress.setText("");
			txtdepid.setText("");
			txtshiftid.setText("");
				
		}
		if(ae.getSource()==btnclose)
		{
			this.dispose();
		}

}
	
void filldata()
{
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
		String sql="select HODId from HeadOfDepartment";
		st=con.prepareStatement(sql);
		rs=st.executeQuery();
		while(rs.next())
		{
			combohodid.addItem(rs.getString(1));
		}
		
	}
	catch(Exception ex) {}
}
@Override
public void keyPressed(KeyEvent ar) {
	// TODO Auto-generated method stub
	if(ar.getSource()==txthodname)
	{
	if (ar.getKeyCode()==ar.VK_ENTER)
	{
		
		txthodemail.requestFocus();
				
	}
	}
	if(ar.getSource()==txthodemail)
	{
	if (ar.getKeyCode()==ar.VK_ENTER)
	{
		
		txthodphone.requestFocus();
				
	}
	}
	if(ar.getSource()==txthodphone)
	{
	if (ar.getKeyCode()==ar.VK_ENTER)
	{
		
		txthodaddress.requestFocus();
				
	}
	}
	if(ar.getSource()==txthodaddress)
	{
	if (ar.getKeyCode()==ar.VK_ENTER)
	{
		
		txtdepid.requestFocus();
				
	}
	}
	if(ar.getSource()==txtdepid)
	{
	if (ar.getKeyCode()==ar.VK_ENTER)
	{
		
		txtshiftid.requestFocus();
				
	}
	}
	
	
}
@Override
public void keyReleased(KeyEvent arg0) {
	// TODO Auto-generated method stub
	
}
@Override
public void keyTyped(KeyEvent arg0) {
	// TODO Auto-generated method stub
	
}
@Override
public void itemStateChanged(ItemEvent e) {
	// TODO Auto-generated method stub
	if(e.getSource()==combohodid)
	{
		String a=combohodid.getSelectedItem().toString();
		HODFindHandle obj=new HODFindHandle();
		String s[]=obj.findData(a);
		//txtusernm,txtuserem,txtuserph,txtuserad,txtusergen;
		if (s!=null) 
		{
				txthodname.setText(s[0]);
				txthodemail.setText(s[1]);
				txthodphone.setText(s[2]);
				txthodaddress.setText(s[3]);
				combodepid.setSelectedItem(s[4]);
				comboshid.setSelectedItem(s[5]);
		}
			else
			{
				JOptionPane.showMessageDialog(this, "Data not found..");
			}
	}
	
}
}
