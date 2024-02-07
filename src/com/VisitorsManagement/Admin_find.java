package com.VisitorsManagement;

import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import com.util.data.*;
import java.awt.*;

public class Admin_find extends JFrame implements ItemListener {
	JTextField a_namef,a_phnf,a_emlf,a_addf,stsf,a_genderf,a_passf ;
	JLabel a_id,a_name,a_phn,a_eml,a_add,a_g,a_pass;
	JButton a_fin,a_clo,a_cl;
	JComboBox a_idf;
	Connection con;PreparedStatement st;ResultSet rs;
	Admin_find()
	{
		setSize(1400,800);
		setTitle("ADMIN FIND");
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
	    
	    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("find.png"));
	    Image i2 = i1.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
	    ImageIcon i3 = new ImageIcon(i2);
	    JLabel image1 = new JLabel("Admin Find",i3,SwingConstants.CENTER);
	    image1.setVerticalTextPosition(JLabel.CENTER);
	    image1.setHorizontalTextPosition(JLabel.RIGHT);
	    image1.setFont(new Font("Arial",Font.BOLD,30));
	    image1.setForeground(new Color(0,0,128));
	    image1.setBounds(550,0, 300, 150);
	    add(image1);
	    image.add(image1);
		
	    a_id=new JLabel("ADMIN ID:");
		a_id.setBounds(450,150,150,30);
		a_id.setFont(new Font("Arial",Font.BOLD,16));
		add(a_id);
		image.add(a_id);
		a_idf=new JComboBox();
		a_idf.setBounds(600,150,250,30);
		a_idf.insertItemAt("", 0);
		add(a_idf);
		image.add(a_idf);
		filldata();
		a_idf.addItemListener(this);
		
		a_name=new JLabel("ADMIN NAME:");
		a_name.setBounds(450,200,150,30);
		a_name.setFont(new Font("Arial",Font.BOLD,16));
		add(a_name);
		image.add(a_name);
		a_namef=new JTextField();
		a_namef.setBounds(600,200,250,30);
		add(a_namef);
		image.add(a_namef);
		
		a_phn=new JLabel("PHONE No: ");
		a_phn.setBounds(450,250,150,30);
		a_phn.setFont(new Font("Arial",Font.BOLD,16));
		add(a_phn);
		image.add(a_phn);
		a_phnf=new JTextField();
		a_phnf.setBounds(600,250,250,30);
		add(a_phnf);
		image.add(a_phnf);
		
		a_eml=new JLabel("EMAIL:");
		a_eml.setBounds(450,300,150,30);
		a_eml.setFont(new Font("Arial",Font.BOLD,16));
		add(a_eml);
		image.add(a_eml);
		a_emlf=new JTextField();
		a_emlf.setBounds(600,300,250,30);
		add(a_emlf);
		image.add(a_emlf);
		
		a_add=new JLabel("ADDRESS:");
		a_add.setBounds(450,350,150,30);
		a_add.setFont(new Font("Arial",Font.BOLD,16));
		add(a_add);
		image.add(a_add);
		a_addf=new JTextField();
		a_addf.setBounds(600,350,250,30);
		add(a_addf);
		image.add(a_addf);
		
		a_g=new JLabel("GENDER:");
		a_g.setBounds(450,400,100,30);
		a_g.setFont(new Font("Arial",Font.BOLD,16));
		add(a_g);
		image.add(a_g);
		a_genderf = new JTextField();
		a_genderf.setBounds(600,400,250,30);
		add(a_genderf);
		image.add(a_genderf);
		
		a_pass=new JLabel("PASSWORD:");
		a_pass.setBounds(450,450,150,30);
		a_pass.setFont(new Font("Arial",Font.BOLD,16));
		add(a_pass);
		image.add(a_pass);
		a_passf = new JTextField();
		a_passf.setBounds(600,450,250,30);
		add(a_passf);
		image.add(a_passf);
		
	
		
		
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Admin_find();

	}


	
		// TODO Auto-generated method stub
		
		
		void filldata()
		{
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
				String sql="select AdminId from AdminMaster";
				st=con.prepareStatement(sql);
				rs=st.executeQuery();
				while(rs.next())
				{
					a_idf.addItem(rs.getString(1));
				}
				
			}
			catch(Exception ex) {}
		
		}


		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource()==(a_idf))
			{
					String k=a_idf.getSelectedItem().toString();
					AdminDATA_find obj=new AdminDATA_find();
					String s[]=obj.findData(k);
					//a_namef,a_phnf,a_emlf,a_addf,a_genderf,a_passf
					if (s!=null) {
						a_namef.setText(s[0]);
						a_phnf.setText(s[1]);
						a_emlf.setText(s[2]);
						a_addf.setText(s[3]);
						a_genderf.setText(s[4]);
						a_passf.setText(s[5]);
					}
						else
						{
							JOptionPane.showMessageDialog(this, "Data not found..");
						}		
					}
			
		}

	}


