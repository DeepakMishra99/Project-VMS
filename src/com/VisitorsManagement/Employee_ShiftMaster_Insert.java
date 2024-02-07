package com.VisitorsManagement;

import java.awt.Color;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import com.util.data.*;
import javax.swing.*;

public class Employee_ShiftMaster_Insert extends JFrame implements ActionListener,KeyListener {
	JLabel employeeshiftmaster,employee_id,shift_id;
	JTextField employee_idf;
	JButton ji,jc,jco,jn;
	JComboBox comboshid,cb;
	JProgressBar jp;
	int val=0;
	Connection con;PreparedStatement st;ResultSet rs;
	public Employee_ShiftMaster_Insert(){
		setSize(1400,800);
		setLayout(null);
		setTitle("Employee Shift Insert");
		setLocationRelativeTo(null); 
		setResizable(false);
		
		ImageIcon bg1 = new ImageIcon(ClassLoader.getSystemResource("all_bg.jpg"));
	    Image bg2= bg1.getImage().getScaledInstance(1400, 800, Image.SCALE_DEFAULT);
	    ImageIcon bg3 = new ImageIcon(bg2);
	    JLabel image = new JLabel(bg3);
	    image.setBounds(0,0, 1400, 800);
	    add(image);
	    
	    ImageIcon logo1 = new ImageIcon(ClassLoader.getSystemResource("shift.png"));
	    Image logo2= logo1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
	    ImageIcon logo3 = new ImageIcon(logo2);
	    JLabel image_logo = new JLabel("EMPLOYEE SHIFT MASTER",logo3,SwingConstants.CENTER);
	    image_logo.setVerticalTextPosition(JLabel.BOTTOM);
	    image_logo.setHorizontalTextPosition(JLabel.CENTER);
	    image_logo.setFont(new Font("Arial",Font.BOLD,16));
	    image_logo.setBounds(10,10,300, 150);
	    add(image_logo);
	    image.add(image_logo);
	    
	    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("insert.png"));
	    Image i2 = i1.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
	    ImageIcon i3 = new ImageIcon(i2);
	    JLabel image1 = new JLabel("Employee Shift Insert",i3,SwingConstants.CENTER);
	    image1.setVerticalTextPosition(JLabel.CENTER);
	    image1.setHorizontalTextPosition(JLabel.RIGHT);
	    image1.setFont(new Font("Arial",Font.BOLD,30));
	    image1.setForeground(new Color(0,0,128));
	    image1.setBounds(480,0,400, 150);
	    add(image1);
	    image.add(image1);
		
	    employee_id=new JLabel("EMPLOYEE ID:");
		employee_id.setBounds(450,250,150,30);
		employee_id.setFont(new Font("Arial",Font.BOLD,16));
		add(employee_id);
		image.add(employee_id);		
		cb=new JComboBox();
		cb.setBounds(600,250,250,30);
		cb.insertItemAt("", 0);
		add(cb);
		image.add(cb);
		filldata();
		
		
		shift_id=new JLabel("SHIFT ID:");
		shift_id.setBounds(450,300,150,30);
		shift_id.setFont(new Font("Arial",Font.BOLD,16));
		add(shift_id);
		image.add(shift_id);
		comboshid=new JComboBox();
		comboshid.setBounds(600,300,250,30);
		add(comboshid);
		image.add(comboshid);
		fillshid();
		comboshid.addKeyListener(this);
		
		jp=new JProgressBar();
		jp.setMinimum(0);
		jp.setMaximum(100);
		jp.setValue(val);
		jp.setBounds(300,450,800,20);
		jp.setFont(new Font("Arial",Font.BOLD,16));
		add(jp);
		jp.setStringPainted(true);
		image.add(jp);
		
		ji = new JButton("INSERT");
		ji.setBounds(400,360,150,30);
		ji.setFont(new Font("Arial",Font.BOLD,16));
		ji.setBackground(new Color(173,216,230));
		add(ji);
		image.add(ji);
		ji.addActionListener(this);
		
		jc = new JButton("CLEAR");
		jc.setBounds(600,360,150,30);
		jc.setFont(new Font("Arial",Font.BOLD,16));
		jc.setBackground(new Color(173,216,230));
		add(jc);
		image.add(jc);
		jc.addActionListener(this);
		
		jco=new JButton("CLOSE");
	    jco.setBounds(800, 360,150, 30);
	    jco.setFont(new Font("Arial",Font.BOLD,16));
	    jco.setBackground(new Color(173,216,230));
		add(jco);
		image.add(jco);
		jco.addActionListener(this);
		
		
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Employee_ShiftMaster_Insert();

	}
	
	void filldata()
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			String sql="select EmpId from EmployeeShiftMaster";
			st=con.prepareStatement(sql);
			rs=st.executeQuery();
			while (rs.next())
			{
				cb.addItem(rs.getString(1));
			}
			}
		catch(Exception ex) {}
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		if(ae.getSource()==ji)
		{
		
			if(employee_idf.getText().length()==0||comboshid.getSelectedItem().toString().length()==0)
			{
				JOptionPane.showMessageDialog(this, "Check all the entries First");
			}
			else
			{
				String emp_id=employee_idf.getText();
				String s_id=comboshid.getSelectedItem().toString();
				Employee_ShiftMaster_DataHandle obj=new Employee_ShiftMaster_DataHandle();
				String msg=obj.insertData(emp_id,s_id);
				JOptionPane.showMessageDialog(this, msg);
				employee_idf.setText("");
				comboshid.getSelectedItem().toString();
			}
		}
		
		if(ae.getSource()==jc)
		{
		
			employee_idf.setText("");
			comboshid.getSelectedItem().toString();
			val=0;
			jp.setValue(val);
		}
		
		if(ae.getSource()==jco)
		{
			this.dispose();
		}
	}
		String getID()
		{
			String e_id;
			try {
				
		
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
				String sql="select  EmpId from EmployeeShiftMaster";
				Statement st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
				ResultSet rs=st.executeQuery(sql);
				if (rs.next())
				{
					rs.last();
					String employee_id=rs.getString(1);
					String w=employee_id.substring(1,employee_id.length());
					int x=Integer.parseInt(w);
					if (x<10)
						e_id="E"+"000"+(x+1);
					else if(x>=10 && x<99)
						e_id="E"+"00"+(x+1);
					else if(x>=100 && x<999)
						e_id="E"+"0"+(x+1);
					else
						e_id="E"+(x+1);
						}
				else
				{
					
					e_id="E0001";
							}
				return e_id;
					
			}
			catch(Exception ex) {
				return ex.toString();
			}

		
		
	}

		@Override
		public void keyPressed(KeyEvent ar) {
			// TODO Auto-generated method stub
			if(ar.getSource()==employee_idf && employee_idf.getText().length()>0)
			{
				if(ar.getKeyCode()==ar.VK_ENTER)
				{
					comboshid.requestFocus();
					val=val+50;
					jp.setValue(val);
				}
			}
			if(ar.getSource()==comboshid && comboshid.getSelectedItem().toString().length()>0)
			{
				if(ar.getKeyCode()==ar.VK_ENTER)
				{
					comboshid.requestFocus();
					val=val+50;
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
				catch (Exception ex) {
					
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


		
	}


