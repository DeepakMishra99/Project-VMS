package com.VisitorsManagement;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.*;
import com.util.data.*;

public class User_Insert extends JFrame implements ActionListener,KeyListener{
	Connection con;PreparedStatement st;
	JLabel lbluserid,lblusernm,lbluserem,lbluserph,lbluserad,lblusergen,lbluserpass,validate_msg;
	JTextField txtuserid,txtusernm,txtuserem,txtuserph,txtuserad,txtuserpass;
	JRadioButton rm,rf;
	ButtonGroup bg;
	JButton b_Save,b_Clear,b_Close,b_New;
	String gender;
	JProgressBar jp;
	int val=0;
	public User_Insert()
	{
		setSize(1400,800);
		setLayout(null);
		setTitle("User Insert");
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
	    
	    
	    
	    
	    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("insert.png"));
	    Image i2 = i1.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
	    ImageIcon i3 = new ImageIcon(i2);
	    JLabel image1 = new JLabel("User Insert",i3,SwingConstants.CENTER);
	    image1.setVerticalTextPosition(JLabel.CENTER);
	    image1.setHorizontalTextPosition(JLabel.RIGHT);
	    image1.setForeground(new Color(0,0,128));
	    image1.setFont(new Font("Arial",Font.BOLD,30));
	    image1.setBounds(550,0, 400, 150);
	    add(image1);
	    image.add(image1);
	    
		lbluserid=new JLabel("USER ID:");
		lbluserid.setBounds(450,150,150,30);
		lbluserid.setFont(new Font("Arial",Font.BOLD,16));
		add(lbluserid);
		image.add(lbluserid);
		txtuserid=new JTextField();
		txtuserid.setText(getID());
		txtuserid.setEditable(false);
		txtuserid.setBounds(620,150,250,30);
		txtuserid.setFont(new Font("Arial",Font.BOLD,16));
		add(txtuserid);
		image.add(txtuserid);
		txtuserid.addKeyListener(this);
		
		lblusernm=new JLabel("USER NAME:");
		lblusernm.setBounds(450,200,150,30);
		lblusernm.setFont(new Font("Arial",Font.BOLD,16));
		add(lblusernm);
		image.add(lblusernm);
		txtusernm=new JTextField();
		txtusernm.setBounds(620,200,250,30);
		add(txtusernm);
		image.add(txtusernm);
		txtusernm.addKeyListener(this);
		
		lbluserem=new JLabel("USER EMAIL:");
		lbluserem.setBounds(450,250,150,30);
		lbluserem.setFont(new Font("Arial",Font.BOLD,16));
		add(lbluserem);
		image.add(lbluserem);
		txtuserem=new JTextField();
		txtuserem.setBounds(620,250,250,30);
		add(txtuserem);
		image.add(txtuserem);
		txtuserem.addKeyListener(this);
		
		validate_msg=new JLabel();
		validate_msg.setBounds(880,250,150,30);
		validate_msg.setFont(new Font("Arial",Font.BOLD,16));
		add(validate_msg);
		image.add(validate_msg);
		
		lbluserph=new JLabel("USER PHONE:");
		lbluserph.setBounds(450,300,150,30);
		lbluserph.setFont(new Font("Arial",Font.BOLD,16));
		add(lbluserph);
		image.add(lbluserph);
		txtuserph=new JTextField();
		txtuserph.setBounds(620,300,250,30);
		add(txtuserph);
		image.add(txtuserph);
		txtuserph.addKeyListener(this);
		
		lbluserad=new JLabel("USER ADDRESS:");
		lbluserad.setBounds(450,350,150,30);
		lbluserad.setFont(new Font("Arial",Font.BOLD,16));
		add(lbluserad);
		image.add(lbluserad);
		txtuserad=new JTextField();
		txtuserad.setBounds(620,350,250,30);
		add(txtuserad);
		image.add(txtuserad);
		txtuserad.addKeyListener(this);
		
		lblusergen=new JLabel("USER GENDER:");
		lblusergen.setBounds(450,400,150,30);
		lblusergen.setFont(new Font("Arial",Font.BOLD,16));
		add(lblusergen);
		image.add(lblusergen);
		rm=new JRadioButton("Male");
		rm.setBounds(650,400,100,30);
		rm.setFont(new Font("Arial",Font.BOLD,16));
		rm.setContentAreaFilled(false);
		rf=new JRadioButton("Female");
		rf.setBounds(750,400,100,30);
		rf.setFont(new Font("Arial",Font.BOLD,16));
		rf.setContentAreaFilled(false);
		bg=new ButtonGroup();
		bg.add(rm);
		bg.add(rf);
		add(rm);
		add(rf);
		image.add(rm);
		image.add(rf);
		rm.addActionListener(this);
		rf.addActionListener(this);
		
		lbluserpass=new JLabel("PASSWORD:");
		lbluserpass.setBounds(450,450,150,30);
		lbluserpass.setFont(new Font("Arial",Font.BOLD,16));
		add(lbluserpass);
		image.add(lbluserpass);
		txtuserpass=new JTextField();
		txtuserpass.setBounds(620,450,250,30);
		add(txtuserpass);
		image.add(txtuserpass);
		txtuserpass.addKeyListener(this);
		
		jp=new JProgressBar();
		jp.setMinimum(0);
		jp.setMaximum(100);
		jp.setValue(val);
		jp.setBounds(300,550,800,20);
		add(jp);
		jp.setStringPainted(true);
		image.add(jp);

		b_Save=new JButton("SAVE");
		b_Save.setBounds(400,500,100,30);
		b_Save.setBackground(new Color(173,216,230));
		add(b_Save);
		image.add(b_Save);
		
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
		
		
		b_Save.addActionListener(this);
		b_Clear.addActionListener(this);
		b_Close.addActionListener(this);
		
		
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new User_Insert();

	}


	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		if(ae.getSource()==(b_Save))
		{
			if (txtuserid.getText().length()==0 || txtusernm.getText().length()==0 || txtuserem.getText().length()==0 || txtuserph.getText().length()==0 || txtuserad.getText().length()==0)
			{
				JOptionPane.showMessageDialog(this,"Check all data first..");
			}
			else
			{
				String uid=txtuserid.getText();
				String unm=txtusernm.getText();
				String uem=txtuserem.getText();
				String uph=txtuserph.getText();
				String uad=txtuserad.getText();
				String up=txtuserpass.getText();
				
				UserDataHandle obj=new UserDataHandle();
				String msg=obj.insertData(uid, unm, uem, uph, uad,gender,up);				
				JOptionPane.showMessageDialog(this, msg);
				txtuserid.setText("");
				txtusernm.setText("");
				txtuserem.setText("");
				txtuserph.setText("");
				txtuserad.setText("");
				txtuserpass.setText("");
			}
	
		}
			
			if(ae.getSource()==(b_Clear))
			{
				txtuserid.setText(" ");
				txtusernm.setText("");
				txtuserem.setText("");
				txtuserph.setText("");
				txtuserad.setText("");
				txtuserpass.setText("");
			}
			if(ae.getSource()==(b_Close))
			{
				this.dispose();			
			}
			if(ae.getSource()==rm)
			{
				gender="MALE";
			}
			if(ae.getSource()==rf)
			{
				gender="FEMALE";
			}
			

		}
		String getID()
		{
			String uid;
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
				String sql="select userid from usermaster";
				Statement st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
				ResultSet rs=st.executeQuery(sql);
				if (rs.next())
				{
					rs.last();
					String g=rs.getString(1);
					String w=g.substring(1,g.length());
					int x=Integer.parseInt(w);
					if (x<10)
						uid="U"+"000"+(x+1);
					else if(x>=10 && x<99)
						uid="U"+"00"+(x+1);
					else if(x>=100 && x<999)
						uid="U"+"0"+(x+1);
					else
						uid="U"+(x+1);
						}
				else
				{
					
					uid="U0001";
							}
				return uid;
					
			}
			catch(Exception ex) {
				return ex.toString();
			}

	}


		@Override
		public void keyPressed(KeyEvent ar) {
			// TODO Auto-generated method stub
			if(ar.getSource()==txtuserid && txtuserid.getText().length()>0)
			{
				if(ar.getKeyCode()==ar.VK_ENTER)
				{
					txtusernm.requestFocus();
					val=val+20;
					jp.setValue(val);

				}
			}
			if(ar.getSource()==txtusernm && txtusernm.getText().length()>0)
			{
				if(ar.getKeyCode()==ar.VK_ENTER)
				{
					txtuserem.requestFocus();
					val=val+16;
					jp.setValue(val);

				}
			}
			if(ar.getSource()==txtuserem && txtuserem.getText().length()>0)
			{
				if(ar.getKeyCode()==ar.VK_ENTER)
				{
					txtuserph.requestFocus();
					val=val+16;
					jp.setValue(val);

				}
			}
			if(ar.getSource()==txtuserph && txtuserph.getText().length()>0)
			{
				if(ar.getKeyCode()==ar.VK_ENTER)
				{
					txtuserad.requestFocus();
					val=val+16;
					jp.setValue(val);

				}
			}
			if(ar.getSource()==txtuserad && txtuserad.getText().length()>0)
			{
				if(ar.getKeyCode()==ar.VK_ENTER)
				{
					txtuserpass.requestFocus();
					val=val+16;
					jp.setValue(val);
		}
			}
			if(ar.getSource()==txtuserpass && txtuserpass.getText().length()>0)
			{
				if(ar.getKeyCode()==ar.VK_ENTER)
				{
					txtuserpass.requestFocus();
					val=val+16;
					jp.setValue(val);
		}
			}


		}


		@Override
		public void keyReleased(KeyEvent ae) {
			// TODO Auto-generated method stub
			String regex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+.[a-zA-Z0-9.-]+$";
		      //Creating a pattern object
		      Pattern pattern = Pattern.compile(regex);
		      //Creating a Matcher object
		      Matcher matcher = pattern.matcher(txtuserem.getText());
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
