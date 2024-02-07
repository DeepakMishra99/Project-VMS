package com.VisitorsManagement;

import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import com.util.data.*;
import java.awt.*;

public class Admin_delete extends JFrame  implements ActionListener,ItemListener{
	JTextField a_namef,a_phnf,a_emlf,a_addf,a_genderf,a_passf;
	JLabel a_id,a_name,a_phn,a_eml,a_add,a_g,sts,a_pass;
	JButton a_fin,a_dl,a_clo,a_cl;
	JComboBox a_idf;
	Connection con;PreparedStatement st;ResultSet rs;
	String gender;
	Admin_delete()
	{
		setSize(1400,800);
		setTitle("DELETE");
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
	    
	    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("delete.png"));
	    Image i2 = i1.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
	    ImageIcon i3 = new ImageIcon(i2);
	    JLabel image1 = new JLabel("Admin Delete",i3,SwingConstants.CENTER);
	    image1.setVerticalTextPosition(JLabel.CENTER);
	    image1.setHorizontalTextPosition(JLabel.RIGHT);
	    image1.setForeground(new Color(0,0,128));
	  
	    image1.setFont(new Font("Arial",Font.BOLD,30));
	    image1.setBounds(550,0, 300, 150);
	    add(image1);
	    image.add(image1);
	    
		a_id=new JLabel("ADMIN ID:");
		a_id.setBounds(400,150,150,30);
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
		a_name.setBounds(400,200,150,30);
		a_name.setFont(new Font("Arial",Font.BOLD,16));
		add(a_name);
		image.add(a_name);
		a_namef=new JTextField();
		a_namef.setBounds(600,200,250,30);
		add(a_namef);
		image.add(a_namef);
		
		a_phn=new JLabel("PHONE No.: ");
		a_phn.setBounds(400,250,150,30);
		a_phn.setFont(new Font("Arial",Font.BOLD,16));
		add(a_phn);
		image.add(a_phn);
		a_phnf=new JTextField();
		a_phnf.setBounds(600,250,250,30);
		add(a_phnf);
		image.add(a_phnf);
		
		a_eml=new JLabel("EMAIL:");
		a_eml.setBounds(400,300,150,30);
		a_eml.setFont(new Font("Arial",Font.BOLD,16));
		add(a_eml);
		image.add(a_eml);
		a_emlf=new JTextField();
		a_emlf.setBounds(600,300,250,30);
		add(a_emlf);
		image.add(a_emlf);
		
		a_add=new JLabel("ADDRESS:");
		a_add.setBounds(400,350,150,30);
		a_add.setFont(new Font("Arial",Font.BOLD,16));
		add(a_add);
		image.add(a_add);
		a_addf=new JTextField();
		a_addf.setBounds(600,350,250,30);
		add(a_addf);
		image.add(a_addf);
		
		a_g=new JLabel("GENDER:");
		a_g.setBounds(400,400,100,30);
		a_g.setFont(new Font("Arial",Font.BOLD,16));
		add(a_g);
		image.add(a_g);
		a_genderf = new JTextField();
		a_genderf.setBounds(600,400,250,30);
		add(a_genderf);
		image.add(a_genderf);
		
		
		
		
		a_dl=new JButton("DELETE");
		a_dl.setBounds(400,500,150,30);
		a_dl.setBackground(new Color(173,216,230));
		add(a_dl);
		image.add(a_dl);
		a_cl=new JButton("CLEAR");
		a_cl.setBounds(600,500,150,30);
		a_cl.setBackground(new Color(173,216,230));
		add(a_cl);
		image.add(a_cl);
		a_clo=new JButton("CLOSE");
		a_clo.setBounds(800,500,150,30);
		a_clo.setBackground(new Color(173,216,230));
		add(a_clo);
		image.add(a_clo);
		
		
		a_dl.addActionListener(this);
		
		a_cl.addActionListener(this);
		a_clo.addActionListener(this);
		
		
		
		
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Admin_delete();

	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		
		if(ae.getSource()==a_cl)
		{
			
			a_namef.setText("");
			a_phnf.setText("");
			a_emlf.setText("");
			a_addf.setText("");
			a_genderf.setText("");
			a_passf.setText("");
			
			
		}
		if(ae.getSource()==a_clo)
		{
			this.dispose();
	}
		//a_namef,a_phnf,a_emlf,a_addf,a_genderf,a_passf
		if(ae.getSource()==(a_dl))
		{
			if (a_idf.getSelectedItem().toString().length()==0||a_namef.getText().length()==0 ||a_phnf.getText().length()==0 ||a_emlf.getText().length()==0 ||a_addf.getText().length()==0||a_genderf.getText().length()==0||a_passf.getText().length()==0)
			{
				JOptionPane.showMessageDialog(this,"Check all Data first");
			}
			else
			{
				int opt=JOptionPane.showConfirmDialog(null,"Are you sure to Delete","Delete",JOptionPane.YES_NO_OPTION);
				if(opt==0)
				{
				String k=a_idf.getSelectedItem().toString();
				String anm=a_namef.getText();
				String aph=a_phnf.getText();
				String aem=a_emlf.getText();
				String aad=a_addf.getText();
				String ag=a_genderf.getText();
				String apass=a_passf.getText();
				
				AdminDATA_delete obj=new AdminDATA_delete();
				String msg=obj.deleteData(k,anm,aph,aem,aad,ag,apass);				
				JOptionPane.showMessageDialog(this, msg);
				a_idf.getSelectedItem().toString();
				a_namef.setText("");
				a_phnf.setText("");
				a_emlf.setText("");
				a_addf.setText("");
				a_genderf.setText("");
				a_passf.setText("");
				}
			}
		}
	
		}
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
		public void itemStateChanged(ItemEvent ae) {
			// TODO Auto-generated method stub
			if(ae.getSource()==a_idf)
			{
				try
				{
					//Step-1 Load the driver
					Class.forName("oracle.jdbc.driver.OracleDriver");
					//Step-2 Connection create
					con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
					//Step-3 Statement create
					
					String sql="select  AdminName,AdminPhone,AdminEmail,AdminAddress,AdminGender from AdminMaster where AdminId=?";
					String ad=a_idf.getSelectedItem().toString();
					st=con.prepareStatement(sql);
					st.setString(1,ad);
					rs=st.executeQuery();
					if (rs.next())
						//a_idf,a_namef,a_phnf,a_emlf,a_addf,a_passf
					{
						a_namef.setText(rs.getString(1));
						a_phnf.setText(rs.getString(2));
						a_emlf.setText(rs.getString(3));
						a_addf.setText(rs.getString(4));
						a_genderf.setText(rs.getString(5));
						a_passf.setText(rs.getString(6));
						
					}
					else
					{
						JOptionPane.showMessageDialog(this, "not found");
					}
					con.close();
					
				
					
					
				}
				catch(Exception ex) {
					System.out.println(ex.toString());
				}
			}
			
		}}


