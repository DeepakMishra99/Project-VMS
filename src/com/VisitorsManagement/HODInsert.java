package com.VisitorsManagement;

import java.awt.*;
import java.awt.event.*;
import com.util.data.*;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;

public class HODInsert extends JFrame implements ActionListener,KeyListener{
	JLabel lblhodid,lblhodname,lblhodemail,lblhodphone,lblhodaddress,lbldepid,lblshiftid,validate_msg;
	JTextField txthodid,txthodname,txthodemail,txthodphone,txthodaddress;
	JButton btnsave,btnclear,btnnew,btnclose;
	Connection con;
	PreparedStatement st;ResultSet rs;
	JComboBox comboshid,combodepid;
	JProgressBar jp;
	int val=0;
	
	public HODInsert() {
		setSize(1400,800); 
		setLayout(null);
		setTitle("HOD Insert");
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
	    
	    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("insert.png"));
	    Image i2 = i1.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
	    ImageIcon i3 = new ImageIcon(i2);
	    JLabel image1 = new JLabel("HOD Insert",i3,SwingConstants.CENTER);
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
		txthodid=new JTextField();
		txthodid.setText(getID());
		txthodid.setEditable(false);
		txthodid.addKeyListener(this);
		txthodid.setBounds(600,150,250,30);
		txthodid.setFont(new Font("Arial",Font.BOLD,16));
		add(txthodid);
		image.add(txthodid);
	    
		lbldepid=new JLabel("DEPARTMENT ID:");
		lbldepid.setBounds(400,200,200,30);
		lbldepid.setFont(new Font("Arial",Font.BOLD,16));
     	add(lbldepid);
		image.add(lbldepid);
		combodepid=new JComboBox();
		combodepid.setBounds(600,200,250,30);
		combodepid.insertItemAt("", 0);
		add(combodepid);
		image.add(combodepid);
		filldepid();
		combodepid.addKeyListener(this);
				
	    
		
		lblhodname=new JLabel("HOD NAME:");
		lblhodname.setBounds(400,250,150,30);
		lblhodname.setFont(new Font("Arial",Font.BOLD,16));
     	add(lblhodname);
		image.add(lblhodname);
		txthodname=new JTextField();
		txthodname.addKeyListener(this);
		txthodname.setBounds(600,250,250,30);
		
		add(txthodname);
		image.add(txthodname);
			
		lblhodemail=new JLabel("HOD EMAIL:");
		lblhodemail.setBounds(400,300,150,30);
		lblhodemail.setFont(new Font("Arial",Font.BOLD,16));
     	add(lblhodemail);
		image.add(lblhodemail);
		txthodemail=new JTextField();
		txthodemail.addKeyListener(this);
		txthodemail.setBounds(600,300,250,30);
		add(txthodemail);
		image.add(txthodemail);
		
	    validate_msg=new JLabel();
		validate_msg.setBounds(880,300,150,30);
		validate_msg.setFont(new Font("Arial",Font.BOLD,16));
		add(validate_msg);
		image.add(validate_msg);
		
		lblhodphone=new JLabel("HOD PHONE No.:");
		lblhodphone.setBounds(400,350,200,30);
		lblhodphone.setFont(new Font("Arial",Font.BOLD,16));
     	add(lblhodphone);
		image.add(lblhodphone);
		txthodphone=new JTextField();
		txthodphone.addKeyListener(this);
		txthodphone.setBounds(600,350,250,30);
		add(txthodphone);
		image.add(txthodphone);
		
		lblhodaddress=new JLabel("HOD ADDRESS:");
		lblhodaddress.setBounds(400,400,200,30);
		lblhodaddress.setFont(new Font("Arial",Font.BOLD,16));
     	add(lblhodaddress);
		image.add(lblhodaddress);
		txthodaddress=new JTextField();
		txthodaddress.addKeyListener(this);
		txthodaddress.setBounds(600,400,250,30);
		add(txthodaddress);
		image.add(txthodaddress);
		
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
		comboshid.addKeyListener(this);
		
		jp=new JProgressBar();
		jp.setMinimum(0);
		jp.setMaximum(100);
		jp.setValue(val);
		jp.setBounds(300,550,800,20);
		jp.setStringPainted(true);
		add(jp);
		image.add(jp);
		
		
		btnsave = new JButton("SAVE");
		btnsave.setBounds(400,500,150,30);
		btnsave.setFont(new Font("Arial",Font.BOLD,16));
		btnsave.setBackground(new Color(173,216,230));
		add(btnsave);
		image.add(btnsave);
		
		btnclear = new JButton("CLEAR");
		btnclear.setBounds(600,500,150,30);
		btnclear.setFont(new Font("Arial",Font.BOLD,16));
		btnclear.setBackground(new Color(173,216,230));
		add(btnclear);
		image.add(btnclear);
		
		btnclose = new JButton("CLOSE");
		btnclose.setBounds(800,500,150,30);
		btnclose.setFont(new Font("Arial",Font.BOLD,16));
		btnclose.setBackground(new Color(173,216,230));
		add(btnclose);
		image.add(btnclose);
		
		
		
		btnsave.addActionListener(this);
		btnclear.addActionListener(this);
		
		btnclose.addActionListener(this);
		
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new HODInsert();
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		if(ae.getSource()==btnsave)
		{
			if (txthodid.getText().length()==0 || txthodname.getText().length()==0 || txthodemail.getText().length()==0 || txthodphone.getText().length()==0 || txthodaddress.getText().length()==0 ||combodepid.getSelectedItem().toString().length()==0 ||comboshid.getSelectedItem().toString().length()==0)
			{
				JOptionPane.showMessageDialog(this, "Check all data first..");
			}
			else {				
				String hid=txthodid.getText();
				String hna=txthodname.getText();
				String hem=txthodemail.getText();
				String hph=txthodphone.getText();
				String hadd=txthodaddress.getText();
				String dep=combodepid.getSelectedItem().toString();
				String shf=comboshid.getSelectedItem().toString();
				HODdataHandle obj=new HODdataHandle();
				String msg=obj.insertData(hid, hna, hem, hph, hadd, dep, shf);
				JOptionPane.showMessageDialog(this, msg);
				
				txthodid.setText("");
				txthodname.setText("");
				txthodemail.setText("");
				txthodphone.setText("");
				txthodaddress.setText("");
				combodepid.getSelectedItem().toString();
				comboshid.getSelectedItem().toString();
								
			}
			
	}
		if(ae.getSource()==btnclear)
		{
			txthodid.setText("");
			txthodname.setText("");
			txthodemail.setText("");
			txthodphone.setText("");
			txthodaddress.setText("");
			combodepid.getSelectedItem().toString();
			comboshid.getSelectedItem().toString();
			val=0;
			jp.setValue(val);
			
		}
		if(ae.getSource()==btnclose)
		{
			this.dispose();
		}
		
		

	}
	String getID()
	{
		String hid;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			String sql="select  hodid from headofdepartment";
			Statement st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs=st.executeQuery(sql);
			if (rs.next())
			{
				rs.last();
				String g=rs.getString(1);
				String w=g.substring(1,g.length());
				int x=Integer.parseInt(w);
				if (x<10)
					hid="H"+"000"+(x+1);
				else if(x>=10 && x<99)
					hid="H"+"00"+(x+1);
				else if(x>=100 && x<999)
					hid="H"+"0"+(x+1);
				else
					hid="H"+(x+1);
					}
			else
			{
			
				hid="H0001";
						}
			return hid;
				
		}
		catch(Exception ex) {
			return ex.toString();
		}
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==comboshid && comboshid.getSelectedItem().toString().length()>0)
		{
			if(e.getKeyCode()==e.VK_ENTER)
			{
				txthodid.requestFocus();
				val=val+15;
				jp.setValue(val);
			}
		}
		if(e.getSource()==txthodid && txthodid.getText().length()>0)
		{
			if(e.getKeyCode()==e.VK_ENTER)
			{
				txthodname.requestFocus();
				val=val+15;
				jp.setValue(val);
			}
		}
		if(e.getSource()==txthodname && txthodname.getText().length()>0)
		{
			if(e.getKeyCode()==e.VK_ENTER)
			{
				txthodemail.requestFocus();
				val=val+14;
				jp.setValue(val);
			}
		}
		if(e.getSource()==txthodemail && txthodemail.getText().length()>0)
		{
			if(e.getKeyCode()==e.VK_ENTER)
			{
				txthodphone.requestFocus();
				val=val+14;
				jp.setValue(val);
			}
		}
		if(e.getSource()==txthodphone && txthodphone.getText().length()>0)
		{
			if(e.getKeyCode()==e.VK_ENTER)
			{
				txthodaddress.requestFocus();
				val=val+14;
				jp.setValue(val);
			}
		}
		if(e.getSource()==txthodaddress && txthodaddress.getText().length()>0)
		{
			if(e.getKeyCode()==e.VK_ENTER)
			{
				comboshid.requestFocus();
				val=val+14;
				jp.setValue(val);
			}
		}
		if(e.getSource()==comboshid && comboshid.getSelectedItem().toString().length()>0)
		{
			if(e.getKeyCode()==e.VK_ENTER)
			{
				comboshid.requestFocus();
				val=val+14;
				jp.setValue(val);
			}
		}
		
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
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		String regex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+.[a-zA-Z0-9.-]+$";
	      //Creating a pattern object
	      Pattern pattern = Pattern.compile(regex);
	      //Creating a Matcher object
	      Matcher matcher = pattern.matcher(txthodemail.getText());
	      //Verifying whether given phone number is valid
	      if(matcher.matches()) {
	    	  validate_msg.setText("Valid");
	    	  validate_msg.setForeground(new Color(0,153,0));
	         
	      } else {
	    	  validate_msg.setText("Invalid");
	    	  validate_msg.setForeground(Color.RED);
	         
	      }
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}

