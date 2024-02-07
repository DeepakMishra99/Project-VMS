package com.VisitorsManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class TypeMaster_Find extends JFrame implements ItemListener{
	JTextField txt_name;
	JComboBox Comboid;
	JLabel lblT_type,lblT_name,lbl;
	JButton T_find,T_Clear,T_Close;
	Connection con;PreparedStatement st;ResultSet rs;
	public TypeMaster_Find() {
		setSize(1400,800);
		setLayout(null);
		setTitle("TYPE MASTER FIND");
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
	    
	    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("find.png"));
	    Image i2 = i1.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
	    ImageIcon i3 = new ImageIcon(i2);
	    JLabel image1 = new JLabel("Type Master Find",i3,SwingConstants.CENTER);
	    image1.setVerticalTextPosition(JLabel.CENTER);
	    image1.setHorizontalTextPosition(JLabel.RIGHT);
	    image1.setForeground(new Color(0,0,128));
	    image1.setFont(new Font("Arial",Font.BOLD,30));
	    image1.setBounds(550,0, 400, 150);
	    add(image1);
	    image.add(image1);
		
	  
		lblT_type=new JLabel("ID TYPE:");
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
		
		lblT_name=new JLabel("ID NAME:");
		lblT_name.setBounds(450,220,150,40);
		lblT_name.setFont(new Font("Arial",Font.BOLD,16));
		add(lblT_name);
		image.add(lblT_name);
		txt_name=new JTextField();
		txt_name.setBounds(600,220,250,40);
		add(txt_name);
		image.add(txt_name);
		
	
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
 }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new TypeMaster_Find();
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