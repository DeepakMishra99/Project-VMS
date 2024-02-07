package com.VisitorsManagement;
import javax.swing.*;

import com.util.data.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.*;
import java.sql.*;
public class ShiftMaster_datafind extends JFrame implements ItemListener{
	JTextField txtFT,txtTT;
	JComboBox cbShId;
	JLabel lblSMH,lblShId,lblFT,lblTT;
	JButton Btsmf,Btsmc,Btsmcl;
	Connection con;PreparedStatement st;ResultSet rs;
	public ShiftMaster_datafind() {
		setSize(1400,800);
		setLayout(null);
		setTitle("Shift Master Find");
		setLocationRelativeTo(null); 
		setResizable(false);
		
		ImageIcon bg1 = new ImageIcon(ClassLoader.getSystemResource("all_bg.jpg"));
	    Image bg2= bg1.getImage().getScaledInstance(1400, 800, Image.SCALE_DEFAULT);
	    ImageIcon bg3 = new ImageIcon(bg2);
	    JLabel image = new JLabel(bg3);
	    image.setBounds(0,0, 1400, 800);
	    add(image);
	    
	    ImageIcon logo1 = new ImageIcon(ClassLoader.getSystemResource("shift.png"));
	    Image logo2= logo1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
	    ImageIcon logo3 = new ImageIcon(logo2);
	    JLabel image_logo = new JLabel("SHIFT MASTER",logo3,SwingConstants.CENTER);
	    image_logo.setVerticalTextPosition(JLabel.BOTTOM);
	    image_logo.setHorizontalTextPosition(JLabel.CENTER);
	    image_logo.setFont(new Font("Arial",Font.BOLD,16));
	    image_logo.setBounds(10,10,200, 150);
	    add(image_logo);
	    image.add(image_logo);
	    
	    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("find.png"));
	    Image i2 = i1.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
	    ImageIcon i3 = new ImageIcon(i2);
	    JLabel image1 = new JLabel("Shift Master Find",i3,SwingConstants.CENTER);
	    image1.setVerticalTextPosition(JLabel.CENTER);
	    image1.setHorizontalTextPosition(JLabel.RIGHT);
	    image1.setForeground(new Color(0,0,128));
	    image1.setFont(new Font("Arial",Font.BOLD,30));
	    image1.setBounds(550,0, 400, 150);
	    add(image1);
	    image.add(image1);
		
	    lblShId=new JLabel("SHIFT ID:");
	    lblShId.setBounds(450,150,150,30);
	    lblShId.setFont(new Font("Arial",Font.BOLD,16));
		add(lblShId);
		image.add(lblShId);
		
		cbShId=new JComboBox();
		cbShId.setBounds(650,150,250,30);
		cbShId.insertItemAt("", 0);
		add(cbShId);
		image.add(cbShId);
		filldata();
		cbShId.addItemListener(this);
		
		lblFT=new JLabel("FROM TIME:");
		lblFT.setBounds(450,200,150,30);
		lblFT.setFont(new Font("Arial",Font.BOLD,16));
		add(lblFT);
		image.add(lblFT);
		txtFT=new JTextField();
		txtFT.setBounds(650,200,250,30);
		add(txtFT);
		image.add(txtFT);
		
		lblTT=new JLabel("TO TIME:");
		lblTT.setBounds(450,250,150,30);
		lblTT.setFont(new Font("Arial",Font.BOLD,16));
		add(lblTT);
		image.add(lblTT);
		txtTT=new JTextField();
		txtTT.setBounds(650,250,250,30);
		add(txtTT);
		image.add(txtTT);

	
		
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
 }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ShiftMaster_datafind();
	}
	
void filldata()
{
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		//Step-2 Connection create
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
		//Step-3 Statement create
		String sql="select ShiftId from ShiftMaster";
		st=con.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		while(rs.next())
		{
			cbShId.addItem(rs.getString(1));
		}
	}
	catch (Exception ex) {}
}
@Override
public void itemStateChanged(ItemEvent e) {
	// TODO Auto-generated method stub
	if(e.getSource()==cbShId)
	{
		String k=cbShId.getSelectedItem().toString();
		SfindDataHandle obj=new SfindDataHandle();
		String s[]=obj.findData(k);
		if(s!=null)
		{
			txtFT.setText(s[0]);
			txtTT.setText(s[1]);
				
}
		else
		{
			JOptionPane.showMessageDialog(this,"Record not found");
		}
		
	}
	
}
}