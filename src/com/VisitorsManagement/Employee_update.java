package com.VisitorsManagement;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import com.util.data.*;

public class Employee_update extends JFrame implements ActionListener,ItemListener {
	JLabel lbl,lblempid,lblempname,lblempemail,lblempphone,lblempaddress,lblempgender,lblhodid,lbldepid;
	JRadioButton radiomale,radiofemale;
	JTextField txtempid,txtempname,txtempemail,txtempphone,txtempaddress,txtempgender,txthodid,txtdepid;
	JButton btnfind,btnclear,btnclose,btnnew,btnupdate;
	JComboBox comboempid,combohodid,combodepid;
	ButtonGroup bg;
	String gender;
	Connection con;
	ResultSet rs;
	PreparedStatement st;
	public Employee_update() {
		setSize(1400,800); 
		setLayout(null);
		setTitle("Employee Update");
		setLocationRelativeTo(null); 
		setResizable(false);
		
		ImageIcon bg1 = new ImageIcon(ClassLoader.getSystemResource("all_bg.jpg"));
	    Image bg2= bg1.getImage().getScaledInstance(1400, 800, Image.SCALE_DEFAULT);
	    ImageIcon bg3 = new ImageIcon(bg2);
	    JLabel image = new JLabel(bg3);
	    image.setBounds(0,0, 1400, 800);
	    add(image);
	    
	    ImageIcon logo1 = new ImageIcon(ClassLoader.getSystemResource("employee.png"));
	    Image logo2= logo1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
	    ImageIcon logo3 = new ImageIcon(logo2);
	    JLabel image_logo = new JLabel("EMPLOYEE",logo3,SwingConstants.CENTER);
	    image_logo.setVerticalTextPosition(JLabel.BOTTOM);
	    image_logo.setHorizontalTextPosition(JLabel.CENTER);
	    image_logo.setFont(new Font("Arial",Font.BOLD,16));
	    image_logo.setBounds(10,10, 100, 150);
	    add(image_logo);
	    image.add(image_logo);
	    
	    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("update.png"));
	    Image i2 = i1.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
	    ImageIcon i3 = new ImageIcon(i2);
	    JLabel image1 = new JLabel("Employee Update",i3,SwingConstants.CENTER);
	    image1.setVerticalTextPosition(JLabel.CENTER);
	    image1.setHorizontalTextPosition(JLabel.RIGHT);
	    image1.setFont(new Font("Arial",Font.BOLD,30));
	    image1.setBounds(550,0, 400, 150);
	    image1.setForeground(new Color(0,0,128));
	    add(image1);
	    image.add(image1);
				
	    lblempid=new JLabel("EMPLOYEE ID:");
	    lblempid.setBounds(350,150,150,30);
	    lblempid.setFont(new Font("Arial",Font.BOLD,16));
		add(lblempid);
		image.add(lblempid);
		
		comboempid=new JComboBox();
		comboempid.setBounds(600,150,250,30);
		comboempid.insertItemAt("", 0);
		add(comboempid);
		image.add(comboempid);
		filldata();
		comboempid.addItemListener(this);
		
		lblempname=new JLabel("EMPLOYEE NAME:");
		lblempname.setBounds(350,200,150,30);
		lblempname.setFont(new Font("Arial",Font.BOLD,16));
	    add(lblempname);
		image.add(lblempname);
		txtempname=new JTextField();
		txtempname.setBounds(600,200,250,30);
	    add(txtempname);
	    image.add(txtempname);
		
	    lblempemail=new JLabel("EMPLOYEE EMAIL:");
	    lblempemail.setBounds(350,250,150,30);
	    lblempemail.setFont(new Font("Arial",Font.BOLD,16));
	    add(lblempemail);
		image.add(lblempemail);
		txtempemail=new JTextField();
		txtempemail.setBounds(600,250,250,30);
	    add(txtempemail);
	    image.add(txtempemail);
		
	    lblempphone=new JLabel("EMPLOYEE PHONE No.:");
	    lblempphone.setBounds(350,300,250,30);
	    lblempphone.setFont(new Font("Arial",Font.BOLD,16));
	    add(lblempphone);
		image.add(lblempphone);
		txtempphone=new JTextField();
		txtempphone.setBounds(600,300,250,30);
	    add(txtempphone);
	    image.add(txtempphone);
		
	    lblempaddress=new JLabel("EMPLOYEE ADDRESS:");
	    lblempaddress.setBounds(350,350,250,30);
	    lblempaddress.setFont(new Font("Arial",Font.BOLD,16));
	    add(lblempaddress);
		image.add(lblempaddress);
		txtempaddress=new JTextField();
		txtempaddress.setBounds(600,350,250,30);
	    add(txtempaddress);
	    image.add(txtempaddress);
		
	    lblempgender=new JLabel("EMPLOYEE GENDER:");
	    lblempgender.setBounds(350,400,250,30);
	    lblempgender.setFont(new Font("Arial",Font.BOLD,16));
	    add(lblempgender);
		image.add(lblempgender);
		txtempgender=new JTextField();
		txtempgender.setBounds(600,400,250,30);
		add(txtempgender);
		image.add(txtempgender);
		
		lblhodid=new JLabel("HOD ID:");
		lblhodid.setBounds(350,450,250,30);
		lblhodid.setFont(new Font("Arial",Font.BOLD,16));
	    add(lblhodid);
		image.add(lblhodid);
		combohodid=new JComboBox();
		combohodid.setBounds(600,450,250,30);
		combohodid.insertItemAt("", 0);
		add(combohodid);
		image.add(combohodid);
		fillhodid();
		
		
	    lbldepid=new JLabel("DEPARTMENT ID:");
	    lbldepid.setBounds(350,500,250,30);
	    lbldepid.setFont(new Font("Arial",Font.BOLD,16));
	    add(lbldepid);
		image.add(lbldepid);
		combodepid=new JComboBox();
		combodepid.setBounds(600,500,250,30);
		combodepid.insertItemAt("", 0);
		add(combodepid);
		image.add(combodepid);
		filldepid();
	
	    
	
		
		btnupdate=new JButton("UPDATE");
	    btnupdate.setBounds(400,550,150,30);
	    btnupdate.setBackground(new Color(173,216,230));
	    add(btnupdate);
	    image.add(btnupdate);
		
	    btnclear=new JButton("CLEAR");
		btnclear.setBounds(600,550,150,30);
		btnclear.setBackground(new Color(173,216,230));
		add(btnclear);
		image.add(btnclear);
		
		btnclose=new JButton("CLOSE");
		btnclose.setBounds(800,550,150,30);
		btnclose.setBackground(new Color(173,216,230));
		add(btnclose);
		image.add(btnclose);
		
		
		btnclear.addActionListener(this);
		btnclose.addActionListener(this);
		btnupdate.addActionListener(this);
		
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Employee_update();
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		
		if(ae.getSource()==(btnupdate))
		{
			if (comboempid.getSelectedItem().toString().length()==0||txtempname.getText().length()==0 ||txtempemail.getText().length()==0 ||txtempphone.getText().length()==0 ||txtempaddress.getText().length()==0||txtempgender.getText().length()==0)
			{
				JOptionPane.showMessageDialog(this,"Check all data first..");
			}
			else
			{
				int opt=JOptionPane.showConfirmDialog(null,"Are you sure to Update","Update",JOptionPane.YES_NO_OPTION);
				if(opt==0)
				{
				String eid=comboempid.getSelectedItem().toString();
				String enm=txtempname.getText();
				String eem=txtempemail.getText();
				String eph=txtempphone.getText();
				String ead=txtempaddress.getText();
				String eg=txtempgender.getText();
				String ehid=combohodid.getSelectedItem().toString();
				String edid=combodepid.getSelectedItem().toString();
				
				EmployeeDATA_update obj=new EmployeeDATA_update();
				String msg=obj.updateData(enm,eem,eph,ead,eg,ehid,edid,eid);				
				JOptionPane.showMessageDialog(this, msg);
				txtempname.setText("");
				txtempemail.setText("");
				txtempphone.setText("");
				txtempaddress.setText("");
				txtempgender.setText("");
				}
			}
	
		}
		if(ae.getSource()==btnclear)
		{
			
			txtempname.setText("");
			txtempemail.setText("");
			txtempphone.setText("");
			txtempaddress.setText("");
			txtempgender.setText("");
			
		}
		if(ae.getSource()==btnclose)
		{
			this.dispose();
		}
		
	}
	
	void fillhodid()
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Step-2 Connection create
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//Step-3 Statement create
			String sql="select hodid from headofdepartment";
			st=con.prepareStatement(sql);
			rs=st.executeQuery();
			while(rs.next())
			{
				combohodid.addItem(rs.getString(1));
			}
		}
		catch (Exception ex) {}
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
		
	void filldata()
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			String sql="select empid from employee";
			st=con.prepareStatement(sql);
			rs=st.executeQuery();
			while(rs.next())
			{
				comboempid.addItem(rs.getString(1));
			}
			
		}
		catch(Exception ex) {}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==(comboempid))
		{
				String k=comboempid.getSelectedItem().toString();
				EmployeeDATA_find obj=new EmployeeDATA_find();
				String s[]=obj.findData(k);
				
				if (s!=null) {
					txtempname.setText(s[0]);
					txtempemail.setText(s[1]);
					txtempphone.setText(s[2]);
					txtempaddress.setText(s[3]);
					txtempgender.setText(s[4]);
					combohodid.setSelectedItem(s[5]);
					combodepid.setSelectedItem(s[6]);
					
				}
					else
					{
						JOptionPane.showMessageDialog(this, "Data not found..");
					}		
				}
		
	}

	
}
