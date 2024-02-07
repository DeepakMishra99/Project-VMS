package com.VisitorsManagement;

import java.awt.event.*;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;
import com.util.data.*;
import java.awt.*;

public class Admin_insert extends JFrame implements ActionListener,KeyListener{
	JLabel a_id,a_name,a_phn,a_eml,a_add,a_g,sts,a_pass,validate_msg;
	JTextField a_idf,a_namef,a_phnf,a_emlf,a_addf,a_passf;
	JButton a_sav,a_clo,a_cl,btnnew;
	Connection con;PreparedStatement st;
	JRadioButton a_m,a_f;
	ButtonGroup bg;
	String gender;
	JProgressBar jp;
	int val=0;
	Admin_insert()
	{
		setSize(1400,800);
		setTitle("ADMIN INSERT");
		setLayout(null);
		setLocationRelativeTo(null); 
		setResizable(false);
		
		ImageIcon bg1 = new ImageIcon(ClassLoader.getSystemResource("all_bg.jpg"));
	    Image bg2= bg1.getImage().getScaledInstance(1400, 800, Image.SCALE_DEFAULT);
	    ImageIcon bg3 = new ImageIcon(bg2);
	    JLabel image = new JLabel(bg3);
	    image.setBounds(0,0, 1400, 800);
	    add(image);
	    
	    ImageIcon logo1 = new ImageIcon(ClassLoader.getSystemResource("admin_logo.png"));
	    Image logo2= logo1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
	    ImageIcon logo3 = new ImageIcon(logo2);
	    JLabel image_logo = new JLabel("ADMIN",logo3,SwingConstants.CENTER);
	    image_logo.setVerticalTextPosition(JLabel.BOTTOM);
	    image_logo.setHorizontalTextPosition(JLabel.CENTER);
	    image_logo.setFont(new Font("Arial",Font.BOLD,16));
	    image_logo.setBounds(10,10, 100, 150);
	    add(image_logo);
	    image.add(image_logo);
	    
	    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("insert.png"));
	    Image i2 = i1.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
	    ImageIcon i3 = new ImageIcon(i2);
	    JLabel image1 = new JLabel("Admin Insert",i3,SwingConstants.CENTER);
	    image1.setVerticalTextPosition(JLabel.CENTER);
	    image1.setHorizontalTextPosition(JLabel.RIGHT);
	    image1.setForeground(new Color(0,0,128));
	    image1.setFont(new Font("Arial",Font.BOLD,30));
	    image1.setBounds(550,0, 400, 150);
	    add(image1);
	    image.add(image1);
	    
		a_id=new JLabel("ADMIN ID:");
		a_id.setBounds(450,150,150,30);
		a_id.setFont(new Font("Arial",Font.BOLD,16));
		add(a_id);
		image.add(a_id);
		a_idf=new JTextField();
		a_idf.setText(getID());
		a_idf.setFont(new Font("Arial",Font.BOLD,16));
		a_idf.setEditable(false);
		a_idf.addKeyListener(this);
		a_idf.setBounds(600,150,250,30);
		add(a_idf);
		
		a_name=new JLabel("ADMIN NAME:");
		a_name.setBounds(450,200,150,30);
		a_name.setFont(new Font("Arial",Font.BOLD,16));
		add(a_name);
		image.add(a_name);
		a_namef=new JTextField();
		a_namef.setBounds(600,200,250,30);
		a_namef.addKeyListener(this);
		add(a_namef);
		image.add(a_namef);
		a_phn=new JLabel("PHONE No.: ");
		a_phn.setBounds(450,250,150,30);
		a_phn.setFont(new Font("Arial",Font.BOLD,16));
		add(a_phn);
		image.add(a_phn);
		a_phnf=new JTextField();
		a_phnf.setBounds(600,250,250,30);
		a_phnf.addKeyListener(this);
		add(a_phnf);
		image.add(a_phnf);
		a_eml=new JLabel("EMAIL:");
		a_eml.setBounds(450,300,150,30);
		a_eml.setFont(new Font("Arial",Font.BOLD,16));
		add(a_eml);
		image.add(a_eml);
		a_emlf=new JTextField();
		a_emlf.setBounds(600,300,250,30);
		a_emlf.addKeyListener(this);
		add(a_emlf);
		image.add(a_emlf);
		validate_msg=new JLabel();
		validate_msg.setBounds(880,300,150,30);
		validate_msg.setFont(new Font("Arial",Font.BOLD,16));
		add(validate_msg);
		image.add(validate_msg);
		
		a_add=new JLabel("ADDRESS:");
		a_add.setBounds(450,350,150,30);
		a_add.setFont(new Font("Arial",Font.BOLD,16));
		add(a_add);
		image.add(a_add);
		a_addf=new JTextField();
		a_addf.setBounds(600,350,250,30);
		a_addf.addKeyListener(this);
		add(a_addf);
		image.add(a_addf);
		a_g=new JLabel("GENDER:");
		a_g.setBounds(450,400,100,30);
		a_g.setFont(new Font("Arial",Font.BOLD,16));
		add(a_g);
		image.add(a_g);
		a_m=new JRadioButton("Male");
		a_m.setBounds(600,400,100,30);
		a_m.setFont(new Font("Arial",Font.BOLD,16));
		a_m.setContentAreaFilled(false);
		a_f=new JRadioButton("Female");
		a_f.setBounds(700,400,100,30);
		a_f.setFont(new Font("Arial",Font.BOLD,16));
		a_f.setContentAreaFilled(false);
		bg=new ButtonGroup();
		bg.add(a_m);
		bg.add(a_f);
		add(a_m);
		add(a_f);
		image.add(a_m);
		image.add(a_f);
		
		a_pass=new JLabel("PASSWORD:");
		a_pass.setBounds(450,450,250,30);
		a_pass.setFont(new Font("Arial",Font.BOLD,16));
		add(a_pass);
		image.add(a_pass);
		a_passf=new JTextField();
		a_passf.setBounds(600,450,250,30);
		a_passf.addKeyListener(this);
		add(a_passf);
		image.add(a_passf);
		
		jp=new JProgressBar();
		jp.setMinimum(0);
		jp.setMaximum(100);
		jp.setValue(val);
		jp.setBounds(310,500,800,20);
		add(jp);
		image.add(jp);
		jp.setStringPainted(true);
				
		a_m.addActionListener(this);
		a_f.addActionListener(this);
		
		a_sav=new JButton("SAVE");
		a_sav.setBounds(450,550,150,30);
		a_sav.setBackground(new Color(173,216,230));
		add(a_sav);
		image.add(a_sav);
		a_clo=new JButton("CLOSE");
		a_clo.setBounds(620,550,150,30);
		a_clo.setBackground(new Color(173,216,230));
		add(a_clo);
		image.add(a_clo);
		a_cl=new JButton("CLEAR");
		a_cl.setBounds(790,550,150,30);
		a_cl.setBackground(new Color(173,216,230));
		add(a_cl);
		image.add(a_cl);
		
		
		
		a_sav.addActionListener(this);
		a_clo.addActionListener(this);
		a_cl.addActionListener(this);
		
		
		
		
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Admin_insert();

	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		if(ae.getSource()==(a_sav))
		{
			if (a_idf.getText().length()==0 ||a_namef.getText().length()==0 ||a_phnf.getText().length()==0 ||a_emlf.getText().length()==0 ||a_addf.getText().length()==0)
			{
				JOptionPane.showMessageDialog(this,"Check all data first..");
			}
			else
			{
				String aid=a_idf.getText();
				String anm=a_namef.getText();
				String aph=a_phnf.getText();
				String aem=a_emlf.getText();
				String aad=a_addf.getText();
				String apass=a_passf.getText();
				
				admin_util_ins obj=new admin_util_ins();
				String msg=obj.insertData(aid, anm,aph, aem, aad,gender,apass);				
				JOptionPane.showMessageDialog(this, msg);
				//a_idf,a_namef,a_phnf,a_emlf,a_addf;
				
				a_namef.setText("");
				a_phnf.setText("");
				a_emlf.setText("");
				a_addf.setText("");
				a_passf.setText("");
			}
	
		}		if(ae.getSource()==a_cl)
		{
			a_idf.setText("");
			a_namef.setText("");
			a_phnf.setText("");
			a_emlf.setText("");
			a_addf.setText("");
			a_passf.setText("");
			val=0;
			jp.setValue(val);
		}
		if(ae.getSource()==a_clo)
		{
			this.dispose();
		}
		if(ae.getSource()==a_m) {
			gender="MALE";
		}
		if (ae.getSource()==a_f)
		{
			gender="FEMALE";
		}
		
		
	}
	String getID()
	{
		String aid;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			String sql="select adminid  from adminmaster";
			Statement st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs=st.executeQuery(sql);
			if (rs.next())
			{
				rs.last();
				String g=rs.getString(1);
				String w=g.substring(3,g.length());
				int x=Integer.parseInt(w);
				if (x<10)
					aid="AD"+"000"+(x+1);
				else if(x>=10 && x<99)
					aid="AD"+"00"+(x+1);
				else if(x>=100 && x<999)
					aid="AD"+"0"+(x+1);
				else
					aid="AD"+(x+1);
			}
			else {
				
				aid="AD0001";
			}
			return aid;
		}
		catch (Exception ex) {
			return ex.toString();
		}
	}

	@Override
	public void keyPressed(KeyEvent ar) {
		// TODO Auto-generated method stub
		//a_idf,a_namef,a_phnf,a_emlf,a_addf,a_passf
		if(ar.getSource()==a_idf && a_idf.getText().length()>0)
		{
			if(ar.getKeyCode()==ar.VK_ENTER)
			{
				a_namef.requestFocus();
				val=val+16;
				jp.setValue(val);

			}
		}
		if(ar.getSource()==a_namef && a_namef.getText().length()>0)
		{
			if(ar.getKeyCode()==ar.VK_ENTER)
			{
				a_phnf.requestFocus();
				val=val+16;
				jp.setValue(val);
			}
		}
		if(ar.getSource()==a_phnf && a_phnf.getText().length()>0)
		{
			if(ar.getKeyCode()==ar.VK_ENTER)
			{
				a_emlf.requestFocus();
				val=val+16;
				jp.setValue(val);
			}
		}
		if(ar.getSource()==a_emlf && a_emlf.getText().length()>0)
		{
			if(ar.getKeyCode()==ar.VK_ENTER)
			{
				a_addf.requestFocus();
				val=val+16;
				jp.setValue(val);
			}
		}
		if(ar.getSource()==a_addf && a_addf.getText().length()>0)
		{
			if(ar.getKeyCode()==ar.VK_ENTER)
			{
				a_addf.requestFocus();
				val=val+16;
				jp.setValue(val);
			}
		}
		if(ar.getSource()==a_addf && a_addf.getText().length()>0)
		{
			if(ar.getKeyCode()==ar.VK_ENTER)
			{
				a_passf.requestFocus();
				val=val+10;
				jp.setValue(val);
			}
		}
		if(ar.getSource()==a_passf && a_passf.getText().length()>0)
		{
			if(ar.getKeyCode()==ar.VK_ENTER)
			{
				a_passf.requestFocus();
				val=val+10;
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
	      Matcher matcher = pattern.matcher(a_emlf.getText());
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