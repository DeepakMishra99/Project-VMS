package com.VisitorsManagement;
import com.util.data.*;


import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;

import com.util.data.DepartmentsDataFind;

public class Employee_ShiftMaster_Find extends JFrame implements ItemListener {
	JLabel employeeshiftmaster,employee_id,shift_id;
	JTextField employee_idf,shift_idf;
	JButton jf,jc,jco;
	JComboBox cb;
	
	Connection con;PreparedStatement st;ResultSet rs;
	public Employee_ShiftMaster_Find(){
		setSize(1400,800);
		setLayout(null);
		setTitle("Employee Shift Find");
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
	    
	    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("find.png"));
	    Image i2 = i1.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
	    ImageIcon i3 = new ImageIcon(i2);
	    JLabel image1 = new JLabel("Employee Shift Find",i3,SwingConstants.CENTER);
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
		cb.addItemListener(this);
				
		shift_id=new JLabel("SHIFT ID:");
		shift_id.setBounds(450,300,150,30);
		shift_id.setFont(new Font("Arial",Font.BOLD,16));
		add(shift_id);
		image.add(shift_id);
		shift_idf=new JTextField();
		shift_idf.setBounds(600,300,250,30);
		add(shift_idf);
		image.add(shift_idf);
		
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Employee_ShiftMaster_Find();

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
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==cb)
		{
		
			String k=cb.getSelectedItem().toString();
			Employee_ShiftMaster_DataFind obj=new Employee_ShiftMaster_DataFind();
			String s[]=obj.findData(k);
			if(s!=null)
			{
				shift_idf.setText(s[0]);
			}
			else
			{
				
				
				JOptionPane.showMessageDialog(this, "Record Not Found");
			}
		}
		
	}

}



