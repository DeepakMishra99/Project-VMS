package com.VisitorsManagement;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import com.util.data.*;

public class vistorFind extends JFrame implements ItemListener {
	
	JLabel lblvisid,lblvisname,lblvisemail,lblvisphone,lblvisaddress,lblgender,lblidtype,lblidno;
	JTextField txtvisname,txtvisemail,txtvisphone,txtvisaddress,txtidtype,txtidno,txtgender;
	JButton btnfind,btnclear,btnclose;JComboBox combovisid;
	Connection con;
	PreparedStatement st;
	ResultSet rs;
	public vistorFind() {
		setSize(1400,800); 
		setLayout(null);
		setTitle("Visitor Find");
		setLocationRelativeTo(null); 
		setResizable(false);
		
		ImageIcon bg1 = new ImageIcon(ClassLoader.getSystemResource("all_bg.jpg"));
	    Image bg2= bg1.getImage().getScaledInstance(1400, 800, Image.SCALE_DEFAULT);
	    ImageIcon bg3 = new ImageIcon(bg2);
	    JLabel image = new JLabel(bg3);
	    image.setBounds(0,0, 1400, 800);
	    add(image);
	    
	    ImageIcon logo1 = new ImageIcon(ClassLoader.getSystemResource("visitor.png"));
	    Image logo2= logo1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
	    ImageIcon logo3 = new ImageIcon(logo2);
	    JLabel image_logo = new JLabel("VISITOR",logo3,SwingConstants.CENTER);
	    image_logo.setVerticalTextPosition(JLabel.BOTTOM);
	    image_logo.setHorizontalTextPosition(JLabel.CENTER);
	    image_logo.setFont(new Font("Arial",Font.BOLD,16));
	    image_logo.setBounds(10,10, 100, 150);
	    add(image_logo);
	    image.add(image_logo);
	    
	    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("find.png"));
	    Image i2 = i1.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
	    ImageIcon i3 = new ImageIcon(i2);
	    JLabel image1 = new JLabel("Visitor Find",i3,SwingConstants.CENTER);
	    image1.setVerticalTextPosition(JLabel.CENTER);
	    image1.setHorizontalTextPosition(JLabel.RIGHT);
	    image1.setFont(new Font("Arial",Font.BOLD,30));
	    image1.setBounds(550,0, 300, 150);
	    image1.setForeground(new Color(0,0,128));
	    add(image1);
	    image.add(image1);
	    
	    lblvisid=new JLabel("VISITOR ID");
	    lblvisid.setBounds(400,150,150,30);
	    lblvisid.setFont(new Font("Arial",Font.BOLD,16));
		add(lblvisid);
		image.add(lblvisid);
		combovisid=new JComboBox();
		combovisid.setBounds(650,150,250,30);
		combovisid.insertItemAt("", 0);
		add(combovisid);
		image.add(combovisid);
	    filldata();
	    combovisid.addItemListener(this);
		
	    lblvisid=new JLabel("VISITOR NAME");
	    lblvisid.setBounds(400,200,150,30);
	    lblvisid.setFont(new Font("Arial",Font.BOLD,16));
		add(lblvisid);
		image.add(lblvisid);
		txtvisname=new JTextField();
		txtvisname.setBounds(650,200,250,30);
		add(txtvisname);
		image.add(txtvisname);
		
		lblvisemail=new JLabel("VISITOR EMAIL");
		lblvisemail.setBounds(400,250,150,30);
		lblvisemail.setFont(new Font("Arial",Font.BOLD,16));
		add(lblvisemail);
		image.add(lblvisemail);
		txtvisemail=new JTextField();
		txtvisemail.setBounds(650,250,250,30);
		add(txtvisemail);
		image.add(txtvisemail);
		
		lblvisphone=new JLabel("VISITOR PHONE NUMBER");
		lblvisphone.setBounds(400,300,300,30);
		lblvisphone.setFont(new Font("Arial",Font.BOLD,16));
		add(lblvisphone);
		image.add(lblvisphone);
		txtvisphone=new JTextField();
		txtvisphone.setBounds(650,300,250,30);
		add(txtvisphone);
		image.add(txtvisphone);	
		
		lblvisaddress=new JLabel("VISITOR ADDRESS");
		lblvisaddress.setBounds(400,350,150,30);
		lblvisaddress.setFont(new Font("Arial",Font.BOLD,16));
		add(lblvisaddress);
		image.add(lblvisaddress);
		txtvisaddress=new JTextField();
		txtvisaddress.setBounds(650,350,250,30);
		add(txtvisaddress);
		image.add(txtvisaddress);
		
		lblgender=new JLabel("VISITOR GENDER");
		lblgender.setBounds(400,400,150,30);
		lblgender.setFont(new Font("Arial",Font.BOLD,16));
		add(lblgender);
		image.add(lblgender);
		txtgender=new JTextField();
		txtgender.setBounds(650,400,250,30);
		add(txtgender);
		image.add(txtgender);
		
		lblidtype=new JLabel("GOVERNMENT ID TYPE");
		lblidtype.setBounds(400,450,300,30);
		lblidtype.setFont(new Font("Arial",Font.BOLD,16));
		add(lblidtype);
		image.add(lblidtype);
		txtidtype=new JTextField();
		txtidtype.setBounds(650,450,250,30);
		add(txtidtype);
		image.add(txtidtype);
		
		lblidno=new JLabel("GOVERNMENT ID NUMBER");
		lblidno.setBounds(400,500,300,30);
		lblidno.setFont(new Font("Arial",Font.BOLD,16));
		add(lblidno);
		image.add(lblidno);
		txtidno=new JTextField();
		txtidno.setBounds(650,500,250,30);
		add(txtidno);
		image.add(txtidno);
		
	
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new vistorFind();
	}
	
	void filldata()
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			String sql="select VisitorId from Visitors";
			st=con.prepareStatement(sql);
			rs=st.executeQuery();
			while(rs.next())
			{
				combovisid.addItem(rs.getString(1));
			}
			
		}
		catch(Exception ex) {}
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub

		if(e.getSource()==combovisid)
		{
			String k=combovisid.getSelectedItem().toString();
			VisitorFindHandle obj=new VisitorFindHandle();
			String s[]=obj.findData(k);
			if (s!=null) {
					txtvisname.setText(s[0]);
					txtvisemail.setText(s[1]);
					txtvisphone.setText(s[2]);
					txtvisaddress.setText(s[3]);
					txtgender.setText(s[4]);
					txtidtype.setText(s[5]);
					txtidno.setText(s[6]);
				}
			else
				{
					JOptionPane.showMessageDialog(this, "Data not found..");
				}	
		}
		
	}

}
