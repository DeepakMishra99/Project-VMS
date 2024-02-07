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

public class CompanyMaster_insert extends JFrame implements ActionListener,KeyListener {
	JLabel lblcomid,lblcomname,lblcomemail,lblcomphone,lblcomgst,validate_msg;
	JRadioButton radiomale,radiofemale;
	JTextField txtcomid,txtcomname,txtcomemail,txtcomphone,txtcomgst;
	JButton btnsave,btnclear,btnclose,btnnew;
	ButtonGroup bg;
	String gender;
	Connection con;
	PreparedStatement st;
	JProgressBar jp;
	int val=0;
	public CompanyMaster_insert() {
		setSize(1400,800);
		setTitle("Data Insert");
		setLayout(null);
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
	    
	    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("insert.png"));
	    Image i2 = i1.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
	    ImageIcon i3 = new ImageIcon(i2);
	    JLabel image1 = new JLabel(" Company Insert",i3,SwingConstants.CENTER);
	    image1.setVerticalTextPosition(JLabel.CENTER);
	    image1.setHorizontalTextPosition(JLabel.RIGHT);
	    image1.setForeground(new Color(0,0,128));
	    image1.setFont(new Font("Arial",Font.BOLD,30));
	    image1.setBounds(500,0,400, 150);
	    add(image1);
	    image.add(image1);
				
		lblcomid=new JLabel("COMPANY ID:");
		lblcomid.setBounds(400, 150,150, 30);
		lblcomid.setFont(new Font("Arial",Font.BOLD,16));
		add(lblcomid);
		image.add(lblcomid);
		txtcomid=new JTextField();
		txtcomid.setBounds(650,150,250,30);
		txtcomid.setText(getID());
		txtcomid.setEditable(false);
		txtcomid.setFont(new Font("Arial",Font.BOLD,16));
		add(txtcomid);
		image.add(txtcomid);
		txtcomid.addKeyListener(this);
		
		lblcomname=new JLabel("COMPANY NAME:");
		lblcomname.setBounds(400,200, 150, 30);
		lblcomname.setFont(new Font("Arial",Font.BOLD,16));
		add(lblcomname);
		image.add(lblcomname);
		txtcomname=new JTextField();
		txtcomname.setBounds(650,200,250,30);
		add(txtcomname);
		image.add(txtcomname);
		txtcomname.addKeyListener(this);
		
		lblcomemail=new JLabel("COMPANY EMAIL:");
		lblcomemail.setBounds(400, 250,150, 30);
		lblcomemail.setFont(new Font("Arial",Font.BOLD,16));
		add(lblcomemail);
		image.add(lblcomemail);
		txtcomemail=new JTextField();
		txtcomemail.setBounds(650,250,250,30);
		add(txtcomemail);
		image.add(txtcomemail);
		txtcomemail.addKeyListener(this);
		
		validate_msg=new JLabel();
		validate_msg.setBounds(900,250,150,30);
		validate_msg.setFont(new Font("Arial",Font.BOLD,16));
		add(validate_msg);
		image.add(validate_msg);
		
		lblcomphone=new JLabel("COMPANY PHONE No.:");                 
		lblcomphone.setBounds(400,300,250, 30);
		lblcomphone.setFont(new Font("Arial",Font.BOLD,16));
		add(lblcomphone);
		image.add(lblcomphone);
		txtcomphone=new JTextField();
		txtcomphone.setBounds(650,300,250,30);
		add(txtcomphone);
		image.add(txtcomphone);
		txtcomphone.addKeyListener(this);
		
		lblcomgst=new JLabel("COMPANY GST:");
		lblcomgst.setBounds(400,350, 150, 30);
		lblcomgst.setFont(new Font("Arial",Font.BOLD,16));
		add(lblcomgst);
		image.add(lblcomgst);
		txtcomgst=new JTextField();
		txtcomgst.setBounds(650,350,250,30);
		add(txtcomgst);
		image.add(txtcomgst);
		txtcomgst.addKeyListener(this);
		
		jp=new JProgressBar();
		jp.setMinimum(0);
		jp.setMaximum(100);
		jp.setValue(val);
		jp.setBounds(290,460,800,20);
		add(jp);
		jp.setStringPainted(true);
		image.add(jp);
		
		btnsave=new JButton("SAVE");
		btnsave.setBounds(480,400,150,40);
		btnsave.setBackground(new Color(173,216,230));
		add(btnsave);
		image.add(btnsave);
		
		btnclear=new JButton("CLEAR");
		btnclear.setBounds(650,400,150,40);
		btnclear.setBackground(new Color(173,216,230));
		add(btnclear);
		image.add(btnclear);
		
		btnclose=new JButton("CLOSE");
		btnclose.setBounds(820,400,150,40);
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
		new CompanyMaster_insert();
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		if(ae.getSource()==btnsave)
		{
			if (txtcomid.getText().length()==0 || txtcomname.getText().length()==0 || txtcomemail.getText().length()==0 || txtcomphone.getText().length()==0 || txtcomgst.getText().length()==0 )
			{
				JOptionPane.showMessageDialog(this, "Check all data first..");
			}
			else {
				String cmid=txtcomid.getText();
				String cmnm=txtcomname.getText();
				String cmem=txtcomemail.getText();
				String cmph=txtcomphone.getText();
				String cmgst=txtcomgst.getText();
				CompanyMaster_InsertHandle obj=new CompanyMaster_InsertHandle();
				String msg=obj.insertData(cmid, cmnm, cmem, cmph, cmgst);
				JOptionPane.showMessageDialog(this, msg);
								
				txtcomid.setText("");
				txtcomname.setText("");
				txtcomemail.setText("");
				txtcomphone.setText("");
				txtcomgst.setText("");
			}
		}
		
		if(ae.getSource()==btnclear)
		{
			txtcomid.setText("");
			txtcomname.setText("");
			txtcomemail.setText("");
			txtcomphone.setText("");
			txtcomgst.setText("");	
			val=0;
			jp.setValue(val);
		}
		if(ae.getSource()==btnclose)
		{
			this.dispose();
		}
		
		if(ae.getSource()==btnnew)
		{
			String r=getID();
			txtcomid.setText(r);
		}
	}
	
	String getID()
	{
		String cid;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			String sql="select companyid from companymaster";
			Statement st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs=st.executeQuery(sql);
			if (rs.next())
			{
				rs.last();
				String g=rs.getString(1);
				String w=g.substring(4,g.length());
				int x=Integer.parseInt(w);
				if (x<10)
					cid="CMP"+"000"+(x+1);
				else if(x>=10 && x<99)
					cid="CMP"+"00"+(x+1);
				else if(x>=100 && x<999)
					cid="CMP"+"0"+(x+1);
				else
					cid="CMP"+(x+1);
			}
			else {
				
				cid="CMP0001";
			}
			return cid;
		}
		catch (Exception ex) {
			return ex.toString();
		}
	}

	@Override
	public void keyPressed(KeyEvent ar) {
		// TODO Auto-generated method stub
		if(ar.getSource()==txtcomid && txtcomid.getText().length()>0)
		{
			if(ar.getKeyCode()==ar.VK_ENTER)
			{
				txtcomname.requestFocus();
				val=val+25;
				jp.setValue(val);
			}
		}
		if(ar.getSource()==txtcomname && txtcomname.getText().length()>0)
		{
			if(ar.getKeyCode()==ar.VK_ENTER)
			{
				txtcomemail.requestFocus();
				val=val+25;
				jp.setValue(val);
			}
		}
		if(ar.getSource()==txtcomemail && txtcomemail.getText().length()>0)
		{
			if(ar.getKeyCode()==ar.VK_ENTER)
			{
				txtcomphone.requestFocus();
				val=val+25;
				jp.setValue(val);
			}
		}
		if(ar.getSource()==txtcomphone && txtcomphone.getText().length()>0)
		{
			if(ar.getKeyCode()==ar.VK_ENTER)
			{
				txtcomgst.requestFocus();
				val=val+25;
				jp.setValue(val);
			}
		}
		if(ar.getSource()==txtcomgst && txtcomgst.getText().length()>0)
		{
			if(ar.getKeyCode()==ar.VK_ENTER)
			{
				txtcomgst.requestFocus();
				val=val+25;
				jp.setValue(val);
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		String regex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+.[a-zA-Z0-9.-]+$";
	      //Creating a pattern object
	      Pattern pattern = Pattern.compile(regex);
	      //Creating a Matcher object
	      Matcher matcher = pattern.matcher(txtcomemail.getText());
	      //Verifying whether given phone number is valid
	      if(matcher.matches()) {
	    	  validate_msg.setText("Valid");
	    	  validate_msg.setForeground(Color.GREEN);
	         
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
