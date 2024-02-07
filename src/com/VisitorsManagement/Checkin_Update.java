package com.VisitorsManagement;

import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.*;
import java.sql.*;
import com.util.data.*;

public class Checkin_Update extends JFrame implements ActionListener,KeyListener,ItemListener{
	JLabel lblCOH,lblCIId,lblVId,lblEmId,lblHodId,lblDepId,lblDI,lblTI,lblExTO;
	JTextField txtVId,txtEmId,txtHodId,txtDepId,txtDIn,txtTIn,txtExTO;
	JButton ButtonCOF,ButtonCOU,ButtonCOCl;
	JComboBox cbCIId;
	Connection con;PreparedStatement st;ResultSet rs;
	public Checkin_Update() {
		setSize(1400,800);
		setLayout(null);
		setTitle("Check-in Update");
		setLocationRelativeTo(null); 
		setResizable(false);
		
		ImageIcon bg1 = new ImageIcon(ClassLoader.getSystemResource("all_bg.jpg"));
	    Image bg2= bg1.getImage().getScaledInstance(1400, 800, Image.SCALE_DEFAULT);
	    ImageIcon bg3 = new ImageIcon(bg2);
	    JLabel image = new JLabel(bg3);
	    image.setBounds(0,0, 1400, 800);
	    add(image);
	    
	    ImageIcon logo1 = new ImageIcon(ClassLoader.getSystemResource("check_in.png"));
	    Image logo2= logo1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
	    ImageIcon logo3 = new ImageIcon(logo2);
	    JLabel image_logo = new JLabel("CHECK IN",logo3,SwingConstants.CENTER);
	    image_logo.setVerticalTextPosition(JLabel.BOTTOM);
	    image_logo.setHorizontalTextPosition(JLabel.CENTER);
	    image_logo.setFont(new Font("Arial",Font.BOLD,16));
	    image_logo.setBounds(10,10, 100, 150);
	    add(image_logo);
	    image.add(image_logo);
	    
	    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("update.png"));
	    Image i2 = i1.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
	    ImageIcon i3 = new ImageIcon(i2);
	    JLabel image1 = new JLabel("Check-in Update",i3,SwingConstants.CENTER);
	    image1.setVerticalTextPosition(JLabel.CENTER);
	    image1.setHorizontalTextPosition(JLabel.RIGHT);
	    image1.setForeground(new Color(0,0,128));
	    image1.setFont(new Font("Arial",Font.BOLD,30));
	    image1.setBounds(550,0, 400, 150);
	    add(image1);
	    image.add(image1);

		lblCIId=new JLabel("CheckIn Id");
		lblCIId.setBounds(450,150,150,40);
		lblCIId.setFont(new Font("Arial",Font.BOLD,16));
		add(lblCIId);
		image.add(lblCIId);
		cbCIId=new JComboBox();
		cbCIId.insertItemAt("", 0);
		cbCIId.setBounds(600,150,250, 40);
		add(cbCIId);
		image.add(cbCIId);
		filldata();
		cbCIId.addItemListener(this);
		
		
		lblVId=new JLabel("VISITOR ID:");
		lblVId.setBounds(450,200,150,40);
		
		lblVId.setFont(new Font("Arial",Font.BOLD,16));
		add(lblVId);
		image.add(lblVId);
		txtVId=new JTextField();
		txtVId.setBounds(600, 200,250, 40);
		add(txtVId);
		image.add(txtVId);
		
	
		lblEmId=new JLabel("EMPLOYEE ID:");
		lblEmId.setBounds(450,250,150,40);
		lblEmId.setFont(new Font("Arial",Font.BOLD,16));
		add(lblEmId);
		image.add(lblEmId);
		txtEmId=new JTextField();
		txtEmId.setBounds(600,250,250, 40);
		add(txtEmId);
		image.add(txtEmId);
		
		lblHodId=new JLabel("HOD ID:");
		lblHodId.setBounds(450,300,150,40);
		lblHodId.setFont(new Font("Arial",Font.BOLD,16));
		add(lblHodId);
		image.add(lblHodId);
		txtHodId=new JTextField();
		txtHodId.setBounds(600, 300,250, 40);
		add(txtHodId);
		image.add(txtHodId);
		
		lblDepId=new JLabel("DEPARTMENT ID:");
		lblDepId.setBounds(450,350,150,40);
		lblDepId.setFont(new Font("Arial",Font.BOLD,16));
		add(lblDepId);
		image.add(lblDepId);
		txtDepId=new JTextField();
		txtDepId.setBounds(600,350,250, 40);
		add(txtDepId);
		image.add(txtDepId);
		
	
		
		ButtonCOU=new JButton("UPDATE");
		ButtonCOU.setBounds(400, 500,150, 40);
		ButtonCOU.setBackground(new Color(173,216,230));
		add(ButtonCOU);
		image.add(ButtonCOU);
		
		ButtonCOCl=new JButton("CLOSE");
		ButtonCOCl.setBounds(700, 500,150, 40);
		ButtonCOCl.setBackground(new Color(173,216,230));
		add(ButtonCOCl);
		image.add(ButtonCOCl);
		
		
		ButtonCOU.addActionListener(this);
		ButtonCOCl.addActionListener(this);
		
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
 }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Checkin_Update();
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		
		if(ae.getSource()==ButtonCOU)
		{
			
				if ( txtVId.getText().length()==0 || txtEmId.getText().length()==0 || txtHodId.getText().length()==0 || txtDepId.getText().length()==0 )
				{
					JOptionPane.showMessageDialog(this, "Check all data first..");
				}
				else
				{
					int opt=JOptionPane.showConfirmDialog(null,"Are you sure to Update","Update",JOptionPane.YES_NO_OPTION);
					if(opt==0)
					{
				String CheckInId=cbCIId.getSelectedItem().toString();
				String VisitorId=txtVId.getText();
				String EmpId=txtEmId.getText();
				String HodId=txtHodId.getText();
				String DepId=txtDepId.getText();
			

				CheckinDATA_update obj=new CheckinDATA_update();
				String msg=obj.updateData(VisitorId, EmpId,HodId,DepId,CheckInId);
				JOptionPane.showMessageDialog(this, msg);
	
				txtVId.setText("");
				txtEmId.setText("");
				txtHodId.setText("");
				txtDepId.setText("");
				txtDIn.setText("");
				txtTIn.setText("");
				txtExTO.setText("");
				}
			}
		}
			if(ae.getSource()==ButtonCOCl)
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
		String sql="select CheckinId from Checkin";
		st=con.prepareStatement(sql);
		rs=st.executeQuery();
		while(rs.next())
		{
			cbCIId.addItem(rs.getString(1));
		}
	}
	catch (Exception ex) {}
}
@Override
public void keyPressed(KeyEvent ar) {
	// TODO Auto-generated method stub
	if(ar.getSource()==txtVId)
	{
		if(ar.getKeyCode()==ar.VK_ENTER)
		{
			txtEmId.requestFocus();
		}
	}
	if(ar.getSource()==txtEmId)
	{
		if(ar.getKeyCode()==ar.VK_ENTER)
		{
			txtHodId.requestFocus();
		}
	}
	if(ar.getSource()==txtHodId)
	{
		if(ar.getKeyCode()==ar.VK_ENTER)
		{
			txtDepId.requestFocus();
		}
	}
	if(ar.getSource()==txtDepId)
	{
		if(ar.getKeyCode()==ar.VK_ENTER)
		{
			txtDIn.requestFocus();
		}
	}
	if(ar.getSource()==txtDIn)
	{
		if(ar.getKeyCode()==ar.VK_ENTER)
		{
			txtTIn.requestFocus();
		}
	}
	if(ar.getSource()==txtTIn)
	{
		if(ar.getKeyCode()==ar.VK_ENTER)
		{
			txtExTO.requestFocus();
		}
	}
	
	
}

@Override
public void keyReleased(KeyEvent arg0) {
	// TODO Auto-generated method stub
	
}
@Override
public void keyTyped(KeyEvent arg0) {
	// TODO Auto-generated method stub
	
}
@Override
public void itemStateChanged(ItemEvent e) {
	// TODO Auto-generated method stub
	if(e.getSource()==cbCIId)
	{
		String k=cbCIId.getSelectedItem().toString();
		CheckinDATA_find obj=new CheckinDATA_find();
		String s[]=obj.findData(k);
		if(s!=null)
		{
			txtVId.setText(s[0]);
			txtEmId.setText(s[1]);
			txtHodId.setText(s[2]);
			txtDepId.setText(s[3]);
			
							
}
		else
		{
			JOptionPane.showMessageDialog(this,"Record not found");
		}
		
	}
	
}
}