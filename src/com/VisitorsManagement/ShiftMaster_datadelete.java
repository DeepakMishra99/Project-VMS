package com.VisitorsManagement;
import javax.swing.*;


import com.util.data.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class ShiftMaster_datadelete extends JFrame implements ActionListener,ItemListener{
	JTextField txtFT,txtTT;
	JComboBox cbShId;
	JLabel lblShId,lblFT,lblTT;
	JButton Btsmd,Btsmc,Btsmcl,Btsmf;
	Connection con;PreparedStatement st;
	ResultSet rs;
	public ShiftMaster_datadelete() {
		setSize(1400,800);
		setLayout(null);
		setTitle("Shift Master Delete");
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
	    
	    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("delete.png"));
	    Image i2 = i1.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
	    ImageIcon i3 = new ImageIcon(i2);
	    JLabel image1 = new JLabel("Shift Master Delete",i3,SwingConstants.CENTER);
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
		
		Btsmd = new JButton("DELETE");
		Btsmd.setBounds(400,320,150,30);
		Btsmd.setFont(new Font("Arial",Font.BOLD,16));
		Btsmd.setBackground(new Color(173,216,230));
		add(Btsmd);
		image.add(Btsmd);
		
		Btsmc = new JButton("CLEAR");
		Btsmc.setBounds(600,320,150,30);
		Btsmc.setFont(new Font("Arial",Font.BOLD,16));
		Btsmc.setBackground(new Color(173,216,230));
		add(Btsmc);
		image.add(Btsmc);
		
		Btsmcl = new JButton("CLOSE");
		Btsmcl.setBounds(800,320,150,30);
		Btsmcl.setFont(new Font("Arial",Font.BOLD,16));
		Btsmcl.setBackground(new Color(173,216,230));
		add(Btsmcl);
		image.add(Btsmcl);
	
		
		Btsmd.addActionListener(this);
		Btsmc.addActionListener(this);
		Btsmcl.addActionListener(this);
	
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ShiftMaster_datadelete();
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		if(ae.getSource()==Btsmd)
		{
			
				if (cbShId.getSelectedItem().toString().length()==0 || txtFT.getText().length()==0 || txtTT.getText().length()==0)
				{
					JOptionPane.showMessageDialog(this, "Check all data first..");
				}
				else
				{
					int opt=JOptionPane.showConfirmDialog(null,"Are you sure to Delete","Delete",JOptionPane.YES_NO_OPTION);
					if(opt==0)
					{
				String ShiftId=cbShId.getSelectedItem().toString();
				String FromTime=txtFT.getText();
				String ToTime=txtTT.getText();
			
				SdeleteDataHandle obj=new SdeleteDataHandle();
				String msg=obj.deleteData(ShiftId,ToTime,FromTime);
				JOptionPane.showMessageDialog(this, msg);
				
				cbShId.getSelectedItem().toString();
				txtTT.setText("");
				txtFT.setText("");
					}
				
	}
			
		}
			
			if(ae.getSource()==Btsmc)
			{
				txtFT.setText("");
				txtTT.setText("");
				
			}
			if(ae.getSource()==Btsmcl)
			{
				this.dispose();			
			}
			
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