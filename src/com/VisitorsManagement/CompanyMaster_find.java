package com.VisitorsManagement;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import com.util.data.*;

public class CompanyMaster_find extends JFrame implements ItemListener {
	JLabel lblcomid,lblcomname,lblcomemail,lblcomphone,lblcomgst;
	JRadioButton radiomale,radiofemale;
	JTextField txtcomid,txtcomname,txtcomemail,txtcomphone,txtcomgst;
	JButton btnfind,btnclear,btnclose,btnnew;JComboBox combocmid;
	ButtonGroup bg;
	String gender;
	Connection con;
	ResultSet rs;
	PreparedStatement st;
	public CompanyMaster_find() {
		setSize(1400,800); 
		setLayout(null);
		setTitle("Company Master Find");
		setLocationRelativeTo(null); 
		setResizable(false);
		
		ImageIcon bg1 = new ImageIcon(ClassLoader.getSystemResource("all_bg.jpg"));
	    Image bg2= bg1.getImage().getScaledInstance(1400, 800, Image.SCALE_DEFAULT);
	    ImageIcon bg3 = new ImageIcon(bg2);
	    JLabel image = new JLabel(bg3);
	    image.setBounds(0,0, 1400, 800);
	    add(image);
	    
	    ImageIcon logo1 = new ImageIcon(ClassLoader.getSystemResource("company.png"));
	    Image logo2= logo1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
	    ImageIcon logo3 = new ImageIcon(logo2);
	    JLabel image_logo = new JLabel("COMPANY MASTER",logo3,SwingConstants.CENTER);
	    image_logo.setVerticalTextPosition(JLabel.BOTTOM);
	    image_logo.setHorizontalTextPosition(JLabel.CENTER);
	    image_logo.setFont(new Font("Arial",Font.BOLD,16));
	    image_logo.setBounds(10,10,200, 150);
	    add(image_logo);
	    image.add(image_logo);
	    
	    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("find.png"));
	    Image i2 = i1.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
	    ImageIcon i3 = new ImageIcon(i2);
	    JLabel image1 = new JLabel(" Company Find",i3,SwingConstants.CENTER);
	    image1.setVerticalTextPosition(JLabel.CENTER);
	    image1.setHorizontalTextPosition(JLabel.RIGHT);
	    image1.setFont(new Font("Arial",Font.BOLD,30));
	    image1.setForeground(new Color(0,0,128));
	    image1.setBounds(500,0,400, 150);
	    add(image1);
	    image.add(image1);
				
		lblcomid=new JLabel("COMPANY ID:");
		lblcomid.setBounds(400, 150,150, 30);
		lblcomid.setFont(new Font("Arial",Font.BOLD,16));
		add(lblcomid);
		image.add(lblcomid);
		combocmid=new JComboBox();
		combocmid.setBounds(650,150,250,30);
		combocmid.insertItemAt("", 0);
		add(combocmid);
		image.add(combocmid);
		filldata();
		combocmid.addItemListener(this);
		
		lblcomname=new JLabel("COMPANY NAME:");
		lblcomname.setBounds(400,200, 150, 30);
		lblcomname.setFont(new Font("Arial",Font.BOLD,16));
		add(lblcomname);
		image.add(lblcomname);
		txtcomname=new JTextField();
		txtcomname.setBounds(650,200,250,30);
		add(txtcomname);
		image.add(txtcomname);
		
		lblcomemail=new JLabel("COMPANY EMAIL:");
		lblcomemail.setBounds(400, 250,150, 30);
		lblcomemail.setFont(new Font("Arial",Font.BOLD,16));
		add(lblcomemail);
		image.add(lblcomemail);
		txtcomemail=new JTextField();
		txtcomemail.setBounds(650,250,250,30);
		add(txtcomemail);
		image.add(txtcomemail);
				
		lblcomphone=new JLabel("COMPANY PHONE No.:");                 
		lblcomphone.setBounds(400,300,250, 30);
		lblcomphone.setFont(new Font("Arial",Font.BOLD,16));
		add(lblcomphone);
		image.add(lblcomphone);
		txtcomphone=new JTextField();
		txtcomphone.setBounds(650,300,250,30);
		add(txtcomphone);
		image.add(txtcomphone);
		
		lblcomgst=new JLabel("COMPANY GST:");
		lblcomgst.setBounds(400,350, 150, 30);
		lblcomgst.setFont(new Font("Arial",Font.BOLD,16));
		add(lblcomgst);
		image.add(lblcomgst);
		txtcomgst=new JTextField();
		txtcomgst.setBounds(650,350,250,30);
		add(txtcomgst);
		image.add(txtcomgst);
		

		
		
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new CompanyMaster_find();
	}

	
	void filldata()
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			String sql="select companyid from companymaster";
			st=con.prepareStatement(sql);
			rs=st.executeQuery();
			while(rs.next())
			{
				combocmid.addItem(rs.getString(1));
			}
			
		}
		catch(Exception ex) {}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==combocmid)
		{
			String a=combocmid.getSelectedItem().toString();
			CompanyMaster_findHandle obj=new CompanyMaster_findHandle();
			String s[]=obj.findData(a);
			if (s!=null) 
			{
					txtcomname.setText(s[0]);
					txtcomemail.setText(s[1]);
					txtcomphone.setText(s[2]);
					txtcomgst.setText(s[3]);
			}
				else
				{
					JOptionPane.showMessageDialog(this, "Data not found..");
				}
		}
		
	}
	
}
