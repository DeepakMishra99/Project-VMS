package com.VisitorsManagement;

import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.*;
import java.sql.*;
public class Typemasterdelete extends JFrame implements ActionListener,ItemListener{
	JTextField txt_name;
	JComboBox Comboid;
	JLabel lblT_type,lblT_name,lbl;
	JButton T_Delete,T_find,T_Clear,T_Close;
	Connection con;PreparedStatement st;
	ResultSet rs;
	public Typemasterdelete() {
		setSize(1400,800);
		setLayout(null);
		setTitle("TYPE MASTER DELETE");
		setLocationRelativeTo(null); 
		setResizable(false);
		
		ImageIcon bg1 = new ImageIcon(ClassLoader.getSystemResource("all_bg.jpg"));
	    Image bg2= bg1.getImage().getScaledInstance(1400, 800, Image.SCALE_DEFAULT);
	    ImageIcon bg3 = new ImageIcon(bg2);
	    JLabel image = new JLabel(bg3);
	    image.setBounds(0,0, 1400, 800);
	    add(image);
	    
	    ImageIcon logo1 = new ImageIcon(ClassLoader.getSystemResource("type.png"));
	    Image logo2= logo1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
	    ImageIcon logo3 = new ImageIcon(logo2);
	    JLabel image_logo = new JLabel("TYPE MASTER",logo3,SwingConstants.CENTER);
	    image_logo.setVerticalTextPosition(JLabel.BOTTOM);
	    image_logo.setHorizontalTextPosition(JLabel.CENTER);
	    image_logo.setFont(new Font("Arial",Font.BOLD,16));
	    image_logo.setBounds(10,10, 200, 150);
	    add(image_logo);
	    image.add(image_logo);
	    
	    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("delete.png"));
	    Image i2 = i1.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
	    ImageIcon i3 = new ImageIcon(i2);
	    JLabel image1 = new JLabel("Type Master Delete",i3,SwingConstants.CENTER);
	    image1.setVerticalTextPosition(JLabel.CENTER);
	    image1.setHorizontalTextPosition(JLabel.RIGHT);
	    image1.setForeground(new Color(0,0,128));
	    image1.setFont(new Font("Arial",Font.BOLD,30));
	    image1.setBounds(550,0, 400, 150);
	    add(image1);
	    image.add(image1);
		
		
		lblT_type=new JLabel("ID TYPE");
		lblT_type.setBounds(450,150,150,40);
		lblT_type.setFont(new Font("Arial",Font.BOLD,16));
		add(lblT_type);
		image.add(lblT_type);
		Comboid=new JComboBox();
		Comboid.setBounds(600,150,250, 40);
		Comboid.insertItemAt("", 0);
		add(Comboid);
		image.add(Comboid);
		filldata();
		Comboid.addItemListener(this);
		
		lblT_name=new JLabel("ID NAME");
		lblT_name.setBounds(450,220,150,40);
		lblT_name.setFont(new Font("Arial",Font.BOLD,16));
		add(lblT_name);
		image.add(lblT_name);
		txt_name=new JTextField();
		txt_name.setBounds(600,220,250,40);
		add(txt_name);
		image.add(txt_name);
		
		T_Delete=new JButton("DELETE");
		T_Delete.setBounds(400,320,100,30);
		T_Delete.setBackground(new Color(173,216,230));
		add(T_Delete);
		image.add(T_Delete);
		
		T_Clear=new JButton("CLEAR");
		T_Clear.setBounds(600,320,100,30);
		T_Clear.setBackground(new Color(173,216,230));
		add(T_Clear);
		image.add(T_Clear);
		
		T_Close=new JButton("CLOSE");
		T_Close.setBounds(800,320,100,30);
		add(T_Close);
		T_Close.setBackground(new Color(173,216,230));
		image.add(T_Close);

		
		T_Delete.addActionListener(this);
		T_Clear.addActionListener(this);
		T_Close.addActionListener(this);
		
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Typemasterdelete();
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		if(ae.getSource()==T_Delete)
		{
			JOptionPane.showMessageDialog(this,"Are you sure you want to delete the data");
			try {
				int opt=JOptionPane.showConfirmDialog(null,"Are you sure to Delete","Delete",JOptionPane.YES_NO_OPTION);
				if(opt==0)
				{
				//Step-1 Load the driver
				Class.forName("oracle.jdbc.driver.OracleDriver");
				//Step-2 Connection create
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
				//Step-3 Statement create
				String sql="delete from TypeMaster where IDTYPE=?";
				String IDTYPE=Comboid.getSelectedItem().toString();
				st=con.prepareStatement(sql);
				st.setString(1,IDTYPE);
				int ws=st.executeUpdate();
				JOptionPane.showMessageDialog(this, "Record Delete");
				con.close();
				Comboid.getSelectedItem().toString();
				
			}
			}
			catch(Exception ex) {
				System.out.println(ex.toString());
			}
			
		}
			
			if(ae.getSource()==T_Clear)
			{
				txt_name.setText("");
				
			}
			if(ae.getSource()==T_Close)
			{
				System.exit(1);			
			}
	}
			
void filldata()
{
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		//Step-2 Connection create
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
		//Step-3 Statement create
		String sql="select IDTYPE from TypeMaster";
		st=con.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		while(rs.next())
		{
			Comboid.addItem(rs.getString(1));
		}
	}
	catch (Exception ex)
	{
		
	}
}
@Override
public void itemStateChanged(ItemEvent e) {
	// TODO Auto-generated method stub
	if(e.getSource()==Comboid)
	{
		try {
			//Step-1 Load the driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Step-2 Connection create
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//Step-3 Statement create
			String sql="select IDNAME from TypeMaster where IDTYPE=?";
			String IDTYPE=Comboid.getSelectedItem().toString();
			st=con.prepareStatement(sql);
			st.setString(1,IDTYPE);
			rs=st.executeQuery();
			if (rs.next())
			{
				txt_name.setText(rs.getString(1));
				
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

	
}
}