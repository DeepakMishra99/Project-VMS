package com.VisitorsManagement;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.*;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;
import com.util.data.*;

public class Employee_insert extends JFrame implements ActionListener,KeyListener {
	JLabel lblempid,lblempname,lblempemail,lblempphone,lblempaddress,lblempgender,lblhodid,lbldepid,validate_msg;
	JRadioButton radiomale,radiofemale;
	JTextField txtempid,txtempname,txtempemail,txtempphone,txtempaddress;
	JButton btnsave,btnclear,btnclose,btnnew;
	ButtonGroup bg;
	String gender;
	Connection con;
	PreparedStatement st;ResultSet rs;
	JComboBox combohodid,combodepid;
	JProgressBar jp;
	int val=0;
	public Employee_insert() {
		setSize(1400,800); 
		setLayout(null);
		setTitle("Employee Insert");
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
	    
	    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("insert.png"));
	    Image i2 = i1.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
	    ImageIcon i3 = new ImageIcon(i2);
	    JLabel image1 = new JLabel("Employee Insert",i3,SwingConstants.CENTER);
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
		txtempid=new JTextField();
		txtempid.setText(getID());
		txtempid.setEditable(false);
		txtempid.setFont(new Font("Arial",Font.BOLD,16));
		txtempid.addKeyListener(this);
		txtempid.setBounds(600,150,250,30);
		add(txtempid);
		image.add(txtempid);
	    
	    lbldepid=new JLabel("DEPARTMENT ID:");
	    lbldepid.setBounds(350,200,250,30);
	    lbldepid.setFont(new Font("Arial",Font.BOLD,16));
	    add(lbldepid);
		image.add(lbldepid);
		combodepid=new JComboBox();
		combodepid.setBounds(600,200,250,30);
		add(combodepid);
		image.add(combodepid);
		filldepid();
		combodepid.addKeyListener(this);
		
		lblhodid=new JLabel("HOD ID:");
		lblhodid.setBounds(350,250,250,30);
		lblhodid.setFont(new Font("Arial",Font.BOLD,16));
	    add(lblhodid);
		image.add(lblhodid);
		combohodid=new JComboBox();
		combohodid.setBounds(600,250,250,30);
		add(combohodid);
		image.add(combohodid);
		fillhodid();
		combohodid.addKeyListener(this);
		
		lblempname=new JLabel("EMPLOYEE NAME:");
		lblempname.setBounds(350,300,150,30);
		lblempname.setFont(new Font("Arial",Font.BOLD,16));
	    add(lblempname);
		image.add(lblempname);
		txtempname=new JTextField();
		txtempname.addKeyListener(this);
		txtempname.setBounds(600,300,250,30);
	    add(txtempname);
	    image.add(txtempname);
	    
		
	    lblempemail=new JLabel("EMPLOYEE EMAIL:");
	    lblempemail.setBounds(350,350,150,30);
	    lblempemail.setFont(new Font("Arial",Font.BOLD,16));
	    add(lblempemail);
		image.add(lblempemail);
		txtempemail=new JTextField();
		txtempemail.addKeyListener(this);
		txtempemail.setBounds(600,350,250,30);
	    add(txtempemail);
	    image.add(txtempemail);
	    
	    validate_msg=new JLabel();
		validate_msg.setBounds(880,350,150,30);
		validate_msg.setFont(new Font("Arial",Font.BOLD,16));
		add(validate_msg);
		image.add(validate_msg);
		
	    lblempphone=new JLabel("EMPLOYEE PHONE No.:");
	    lblempphone.setBounds(350,400,250,30);
	    lblempphone.setFont(new Font("Arial",Font.BOLD,16));
	    add(lblempphone);
		image.add(lblempphone);
		txtempphone=new JTextField();
		txtempphone.addKeyListener(this);
		txtempphone.setBounds(600,400,250,30);
	    add(txtempphone);
	    image.add(txtempphone);
		
	    lblempaddress=new JLabel("EMPLOYEE ADDRESS:");
	    lblempaddress.setBounds(350,450,250,30);
	    lblempaddress.setFont(new Font("Arial",Font.BOLD,16));
	    add(lblempaddress);
		image.add(lblempaddress);
		txtempaddress=new JTextField();
		txtempaddress.addKeyListener(this);
		txtempaddress.setBounds(600,450,250,30);
	    add(txtempaddress);
	    image.add(txtempaddress);
		
	    lblempgender=new JLabel("EMPLOYEE GENDER:");
	    lblempgender.setBounds(350,500,250,30);
	    lblempgender.setFont(new Font("Arial",Font.BOLD,16));
	    add(lblempgender);
		image.add(lblempgender);
		
		radiomale=new JRadioButton("Male");
		radiomale.setBounds(600,500,100,30);
		radiomale.setFont(new Font("Arial",Font.BOLD,16));
		radiomale.setContentAreaFilled(false);
		radiofemale=new JRadioButton("Female");
		radiofemale.setBounds(750,500,100,30);
		radiofemale.setFont(new Font("Arial",Font.BOLD,16));
		radiofemale.setContentAreaFilled(false);
		bg=new ButtonGroup();
		bg.add(radiomale);
		bg.add(radiofemale);
		add(radiomale);
		add(radiofemale);
		image.add(radiomale);
		image.add(radiofemale);
		
		jp=new JProgressBar();
		jp.setMinimum(0);
		jp.setMaximum(100);
		jp.setValue(val);
		jp.setBounds(300,600,800,20);
		add(jp);
		image.add(jp);
		jp.setStringPainted(true);
		
		btnsave=new JButton("SAVE");
		btnsave.setBounds(400,550,150,30);
		add(btnsave);
		image.add(btnsave);
		
		btnclear=new JButton("CLEAR");
		btnclear.setBounds(600,550,150,30);
		add(btnclear);
		image.add(btnclear);
		
		btnclose=new JButton("CLOSE");
		btnclose.setBounds(800,550,150,30);
		add(btnclose);
		image.add(btnclose);
		
		
		
		btnsave.addActionListener(this);
		btnclear.addActionListener(this);
		btnclose.addActionListener(this);
		
		radiomale.addActionListener(this);
		radiofemale.addActionListener(this);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Employee_insert();
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		if(ae.getSource()==(btnsave))
		{
			if (txtempid.getText().length()==0 ||txtempname.getText().length()==0 ||txtempemail.getText().length()==0 ||txtempphone.getText().length()==0 ||txtempaddress.getText().length()==0||combohodid.getSelectedItem().toString().length()==0||combodepid.getSelectedItem().toString().length()==0)
			{
				JOptionPane.showMessageDialog(this,"Check all data first..");
			}
			else
			{
				String eid=txtempid.getText();
				String enm=txtempname.getText();
				String eem=txtempemail.getText();
				String eph=txtempphone.getText();
				String ead=txtempaddress.getText();
				String ehid=combohodid.getSelectedItem().toString();
				String edid=combodepid.getSelectedItem().toString();
				
				EmployeeDATA_insert obj=new EmployeeDATA_insert();
				String msg=obj.insertData(eid, enm, eem, eph, ead,gender,ehid,edid);				
				JOptionPane.showMessageDialog(this, msg);
				txtempid.setText("");
				txtempname.setText("");
				txtempemail.setText("");
				txtempphone.setText("");
				txtempaddress.setText("");
				combohodid.getSelectedItem().toString();
				combodepid.getSelectedItem().toString();
			}
	
		}		if(ae.getSource()==radiomale) {
			gender = "MALE";
		}
		if(ae.getSource()==radiofemale) {
			gender = "FEMALE";
		}
		if(ae.getSource()==btnclear)
		{
			txtempid.setText("");
			txtempname.setText("");
			txtempemail.setText("");
			txtempphone.setText("");
			txtempaddress.setText("");
			combohodid.getSelectedItem().toString();
			combodepid.getSelectedItem().toString();
			val=0;
			jp.setValue(val);
			
		}
		if(ae.getSource()==btnclose)
		{
			this.dispose();
		}
		if(ae.getSource()==radiomale) {
			gender = "MALE";
		}
		if(ae.getSource()==radiofemale) {
			gender = "FEMALE";
		}
		if(ae.getSource()==btnnew)
		{
			String r=getID();
			txtempid.setText(r);
		}
	}
	
	String getID()
	{
		String visid;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			String sql="select  empid from employee";
			Statement st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs=st.executeQuery(sql);
			if (rs.next())
			{
				rs.last();
				String g=rs.getString(1);
				String w=g.substring(4,g.length());
				int x=Integer.parseInt(w);
				if (x<10)
					visid="EMP"+"000"+(x+1);
				else if(x>=10 && x<99)
					visid="EMP"+"00"+(x+1);
				else if(x>=100 && x<999)
					visid="EMP"+"0"+(x+1);
				else
					visid="EMP"+(x+1);
			}
			else {
				
				visid="EMP0001";
			}
			return visid;
		}
		catch (Exception ex) {
			return ex.toString();
		}
	}

	@Override
	public void keyPressed(KeyEvent ar) {
		// TODO Auto-generated method stub
		//txtempid,txtempname,txtempemail,txtempphone,txtempaddress
		//combohodid,combodepid
		if(ar.getSource()==combodepid  &&combodepid.getSelectedItem().toString().length()>0)
		{
			if(ar.getKeyCode()==ar.VK_ENTER)
			{
				combohodid.requestFocus();
				val=val+15;
				jp.setValue(val);

			}
		}
		if(ar.getSource()==combohodid && combohodid.getSelectedItem().toString().length()>0)

		{
			if(ar.getKeyCode()==ar.VK_ENTER)
			{
				txtempid.requestFocus();
				val=val+15;
				jp.setValue(val);

			}
		}
		if(ar.getSource()==txtempid && txtempid.getText().length()>0)
		{
			if(ar.getKeyCode()==ar.VK_ENTER)
			{
				txtempname.requestFocus();
				val=val+14;
				jp.setValue(val);

			}
		}
		if(ar.getSource()==txtempname && txtempname.getText().length()>0)
		{
			if(ar.getKeyCode()==ar.VK_ENTER)
			{
				txtempemail.requestFocus();
				val=val+14;
				jp.setValue(val);

			}
		}
		if(ar.getSource()==txtempemail && txtempemail.getText().length()>0)
		{
			if(ar.getKeyCode()==ar.VK_ENTER)
			{
				txtempphone.requestFocus();
				val=val+14;
				jp.setValue(val);

			}
		}
		if(ar.getSource()==txtempphone && txtempphone.getText().length()>0)
		{
			if(ar.getKeyCode()==ar.VK_ENTER)
			{
				txtempaddress.requestFocus();
				val=val+14;
				jp.setValue(val);

			}
		}
		if(ar.getSource()==txtempaddress && txtempaddress.getText().length()>0)
		{
			if(ar.getKeyCode()==ar.VK_ENTER)
			{
				txtempaddress.requestFocus();
				val=val+14;
				jp.setValue(val);

			}
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

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		String regex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+.[a-zA-Z0-9.-]+$";
	      //Creating a pattern object
	      Pattern pattern = Pattern.compile(regex);
	      //Creating a Matcher object
	      Matcher matcher = pattern.matcher(txtempemail.getText());
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
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
