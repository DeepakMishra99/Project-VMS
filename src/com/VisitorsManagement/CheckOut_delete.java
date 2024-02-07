package com.VisitorsManagement;
import javax.swing.*;

import com.util.data.*;


import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class CheckOut_delete extends JFrame implements ActionListener,ItemListener{
	JTextField txtVId,txtEmId,txtHodId,txtDepId,txtDO,txtTO,txtAcTO;
	JLabel lblCOH,lblCOId,lblVId,lblEmId,lblHodId,lblDepId,lblDO,lblTO,lblAcTO;
	JButton ButtonCOD,ButtonCOC,ButtonCOCl,ButtonCOF;
	JComboBox cbCOId;
	Connection con;PreparedStatement st;
	ResultSet rs;
	public CheckOut_delete() {
		setSize(1400,800);
		setLayout(null);
		setTitle("Checkout Delete");
		setLocationRelativeTo(null); 
		setResizable(false);
		
		ImageIcon bg1 = new ImageIcon(ClassLoader.getSystemResource("all_bg.jpg"));
	    Image bg2= bg1.getImage().getScaledInstance(1400, 800, Image.SCALE_DEFAULT);
	    ImageIcon bg3 = new ImageIcon(bg2);
	    JLabel image = new JLabel(bg3);
	    image.setBounds(0,0, 1400, 800);
	    add(image);
	    
	    ImageIcon logo1 = new ImageIcon(ClassLoader.getSystemResource("check_out.png"));
	    Image logo2= logo1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
	    ImageIcon logo3 = new ImageIcon(logo2);
	    JLabel image_logo = new JLabel("CHECK OUT",logo3,SwingConstants.CENTER);
	    image_logo.setVerticalTextPosition(JLabel.BOTTOM);
	    image_logo.setHorizontalTextPosition(JLabel.CENTER);
	    image_logo.setFont(new Font("Arial",Font.BOLD,16));
	    image_logo.setBounds(10,10, 100, 150);
	    add(image_logo);
	    image.add(image_logo);
	    
	    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("delete.png"));
	    Image i2 = i1.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
	    ImageIcon i3 = new ImageIcon(i2);
	    JLabel image1 = new JLabel("Check-out Delete",i3,SwingConstants.CENTER);
	    image1.setVerticalTextPosition(JLabel.CENTER);
	    image1.setHorizontalTextPosition(JLabel.RIGHT);
	    image1.setForeground(new Color(0,0,128));
	    image1.setFont(new Font("Arial",Font.BOLD,30));
	    image1.setBounds(550,0, 400, 150);
	    add(image1);
	    image.add(image1);
	    
	
		
		lblCOH=new JLabel("CHECKOUT ID:");
		lblCOH.setBounds(450,150,150,40);
		lblCOH.setFont(new Font("Arial",Font.BOLD,16));
		add(lblCOH);
		image.add(lblCOH);
		cbCOId=new JComboBox();
		cbCOId.insertItemAt("", 0);
		cbCOId.setBounds(600,150,250, 40);
		add(cbCOId);
		image.add(cbCOId);
		filldata();
		cbCOId.addItemListener(this);
		
		
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
		
		
		lblDO=new JLabel("CHECK-IN TIME:");
		lblDO.setBounds(450,400,150,40);
		lblDO.setFont(new Font("Arial",Font.BOLD,16));
		add(lblDO);
		image.add(lblDO);
		txtDO=new JTextField();
		txtDO.setBounds(600, 400,250, 40);
		add(txtDO);
		image.add(txtDO);
		
		
		lblTO=new JLabel("CHECK-OUT TIME:");
		lblTO.setBounds(450,450,150,40);
		lblTO.setFont(new Font("Arial",Font.BOLD,16));
		add(lblTO);
		image.add(lblTO);
		txtTO=new JTextField();
		txtTO.setBounds(600,450,250, 40);
		add(txtTO);
		image.add(txtTO);

		
		ButtonCOD=new JButton("DELETE");
		ButtonCOD.setBounds(480, 575,150, 40);
		ButtonCOD.setBackground(new Color(173,216,230));
		add(ButtonCOD);
		image.add(ButtonCOD);
		
		ButtonCOC=new JButton("CLEAR");
		ButtonCOC.setBounds(650, 575,150, 40);
		ButtonCOC.setBackground(new Color(173,216,230));
		add(ButtonCOC);
		image.add(ButtonCOC);
		
		ButtonCOCl=new JButton("CLOSE");
		ButtonCOCl.setBounds(820, 575,150, 40);
		ButtonCOCl.setBackground(new Color(173,216,230));
		add(ButtonCOCl);
		image.add(ButtonCOCl);
		
		
		
		
		ButtonCOD.addActionListener(this);
		ButtonCOC.addActionListener(this);
		ButtonCOCl.addActionListener(this);
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
 }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new CheckOut_delete();
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		if(ae.getSource()==ButtonCOD)
		{
			
				if (cbCOId.getSelectedItem().toString().length()==0 || txtVId.getText().length()==0 || txtEmId.getText().length()==0 || txtHodId.getText().length()==0 || txtDepId.getText().length()==0 || txtDO.getText().length()==0 || txtTO.getText().length()==0)
				{
					JOptionPane.showMessageDialog(this, "Check all data first..");
				}
				else
				{
					int opt=JOptionPane.showConfirmDialog(null,"Are you sure to Delete","Delete",JOptionPane.YES_NO_OPTION);
					if(opt==0)
					{
				String CheckOutId=cbCOId.getSelectedItem().toString();
				String VisitorId=txtVId.getText();
				String EmpId=txtEmId.getText();
				String HodId=txtHodId.getText();
				String DepId=txtDepId.getText();
				String checkin=txtDO.getText();
				String checkout=txtTO.getText();
				

				CdeleteDataHandle obj=new CdeleteDataHandle();
				String msg=obj.deleteData(CheckOutId,VisitorId, EmpId,HodId,DepId,checkin,checkout);
				JOptionPane.showMessageDialog(this, msg);
				
				cbCOId.getSelectedItem().toString();
				txtVId.setText("");
				txtEmId.setText("");
				txtHodId.setText("");
				txtDepId.setText("");
				txtDO.setText("");
				txtTO.setText("");
			
					}
				
	}
			
		}
			
			if(ae.getSource()==ButtonCOC)
			{
				
				txtVId.setText("");
				txtEmId.setText("");
				txtHodId.setText("");
				txtDepId.setText("");
				txtDO.setText("");
				txtTO.setText("");
				txtAcTO.setText("");
				
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
		String sql="select CheckOutId from CheckOut";
		st=con.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		while(rs.next())
		{
			cbCOId.addItem(rs.getString(1));
		}
	}
	catch (Exception ex) {}
}
@Override
public void itemStateChanged(ItemEvent ae) {
	// TODO Auto-generated method stub
	if(ae.getSource()==cbCOId)
	{
		try {
			//Step-1 Load the driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Step-2 Connection create
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//Step-3 Statement create
			String sql="select VisitorId,EmpId,HodId,DepId,checkintime,checkouttime from CheckOut where CheckOutId=?";
			String CheckOutId=cbCOId.getSelectedItem().toString();
			st=con.prepareStatement(sql);
			st.setString(1,CheckOutId);
			rs=st.executeQuery();
			if (rs.next())
			{
				txtVId.setText(rs.getString(1));
				txtEmId.setText(rs.getString(2));
				txtHodId.setText(rs.getString(3));
				txtDepId.setText(rs.getString(4));
				txtDO.setText(rs.getString(5));
				txtTO.setText(rs.getString(6));
			
				
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