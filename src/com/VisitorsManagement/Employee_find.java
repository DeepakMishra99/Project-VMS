package com.VisitorsManagement;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import com.util.data.*;

public class Employee_find extends JFrame implements ItemListener {
	JLabel lblempid,lblempname,lblempemail,lblempphone,lblempaddress,lblempgender,lblhodid,lbldepid;
	JRadioButton radiomale,radiofemale;
	JTextField txtempid,txtempname,txtempemail,txtempphone,txtempaddress,txtempgender,txthodid,txtdepid;
	JButton btnfind,btnclear,btnclose;
	JComboBox comboempid;
	ButtonGroup bg;
	String gender;
	Connection con;
	ResultSet rs;
	PreparedStatement st;
	public Employee_find() {
		setSize(1400,800); 
		setLayout(null);
		setTitle("Employee Find");
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
	    
	    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("find.png"));
	    Image i2 = i1.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
	    ImageIcon i3 = new ImageIcon(i2);
	    JLabel image1 = new JLabel("Employee Find",i3,SwingConstants.CENTER);
	    image1.setVerticalTextPosition(JLabel.CENTER);
	    image1.setHorizontalTextPosition(JLabel.RIGHT);
	    image1.setFont(new Font("Arial",Font.BOLD,30));
	    image1.setBounds(550,0, 300, 150);
	    image1.setForeground(new Color(0,0,128));
	    add(image1);
	    image.add(image1);
				
	    lblempid=new JLabel("EMPLOYEE ID:");
	    lblempid.setBounds(400,150,150,30);
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
		lblempname.setBounds(400,200,150,30);
		lblempname.setFont(new Font("Arial",Font.BOLD,16));
	    add(lblempname);
		image.add(lblempname);
		txtempname=new JTextField();
		txtempname.setBounds(600,200,250,30);
	    add(txtempname);
	    image.add(txtempname);
		
	    lblempemail=new JLabel("EMPLOYEE EMAIL:");
	    lblempemail.setBounds(400,250,150,30);
	    lblempemail.setFont(new Font("Arial",Font.BOLD,16));
	    add(lblempemail);
		image.add(lblempemail);
		txtempemail=new JTextField();
		txtempemail.setBounds(600,250,250,30);
	    add(txtempemail);
	    image.add(txtempemail);
		
	    lblempphone=new JLabel("EMPLOYEE PHONE No.:");
	    lblempphone.setBounds(400,300,250,30);
	    lblempphone.setFont(new Font("Arial",Font.BOLD,16));
	    add(lblempphone);
		image.add(lblempphone);
		txtempphone=new JTextField();
		txtempphone.setBounds(600,300,250,30);
	    add(txtempphone);
	    image.add(txtempphone);
		
	    lblempaddress=new JLabel("EMPLOYEE ADDRESS:");
	    lblempaddress.setBounds(400,350,250,30);
	    lblempaddress.setFont(new Font("Arial",Font.BOLD,16));
	    add(lblempaddress);
		image.add(lblempaddress);
		txtempaddress=new JTextField();
		txtempaddress.setBounds(600,350,250,30);
	    add(txtempaddress);
	    image.add(txtempaddress);
		
	    lblempgender=new JLabel("EMPLOYEE GENDER:");
	    lblempgender.setBounds(400,400,250,30);
	    lblempgender.setFont(new Font("Arial",Font.BOLD,16));
	    add(lblempgender);
		image.add(lblempgender);
		txtempgender=new JTextField();
		txtempgender.setBounds(600,400,250,30);
		add(txtempgender);
		image.add(txtempgender);
		
		lblhodid=new JLabel("HOD ID:");
		lblhodid.setBounds(400,450,250,30);
		lblhodid.setFont(new Font("Arial",Font.BOLD,16));
	    add(lblhodid);
		image.add(lblhodid);
		txthodid=new JTextField();
		txthodid.setBounds(600,450,250,30);
	    add(txthodid);
	    image.add(txthodid);
		
	    lbldepid=new JLabel("DEPARTMENT ID:");
	    lbldepid.setBounds(400,500,250,30);
	    lbldepid.setFont(new Font("Arial",Font.BOLD,16));
	    add(lbldepid);
		image.add(lbldepid);
		txtdepid=new JTextField();
		txtdepid.setBounds(600,500,250,30);
	    add(txtdepid);
	    image.add(txtdepid);
		
		
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Employee_find();
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
					txthodid.setText(s[5]);
					txtdepid.setText(s[6]);
				}
					else
					{
						JOptionPane.showMessageDialog(this, "Data not found..");
					}		
				}
		
	}

	
}
