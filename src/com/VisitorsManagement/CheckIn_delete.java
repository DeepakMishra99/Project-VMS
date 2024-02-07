package com.VisitorsManagement;

import javax.swing.*;
import com.util.data.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class CheckIn_delete extends JFrame implements ActionListener,ItemListener{
	JLabel lblCHD,lblchid,lblvid,lblEmId,lblHodId,lblDepId,lblDO,lblTO,lblAcTO;
	JTextField txtVId,txtEmId,txtHodId,txtDepId,txtDO,txtTO,txtAcTO;
	JButton ButtonCOD,ButtonCOC,ButtonCOCl,ButtonCOF;
	JComboBox cb1;
	Connection con;PreparedStatement st;
	ResultSet rs;
	public CheckIn_delete() {
		setSize(1400,800);
		setLayout(null);
		setTitle("Check-in Delete");
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
	    
	    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("delete.png"));
	    Image i2 = i1.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
	    ImageIcon i3 = new ImageIcon(i2);
	    JLabel image1 = new JLabel("Check-in Delete",i3,SwingConstants.CENTER);
	    image1.setVerticalTextPosition(JLabel.CENTER);
	    image1.setHorizontalTextPosition(JLabel.RIGHT);
	    image1.setForeground(new Color(0,0,128));
	    image1.setFont(new Font("Arial",Font.BOLD,30));
	    image1.setBounds(550,0, 400, 150);
	    add(image1);
	    image.add(image1);
	    
	
		
		lblchid=new JLabel("CHECK-IN ID:");
		lblchid.setBounds(450,150,150,40);
		lblchid.setFont(new Font("Arial",Font.BOLD,16));
		add(lblchid);
		image.add(lblchid);
		cb1=new JComboBox();
		cb1.insertItemAt("", 0);
		cb1.setBounds(600,150,250, 40);
		add(cb1);
		image.add(cb1);
		filldata();
		cb1.addItemListener(this);
		
		
		lblvid=new JLabel("VISITOR ID:");
		lblvid.setBounds(450,200,150,40);
		lblvid.setFont(new Font("Arial",Font.BOLD,16));
		add(lblvid);
		image.add(lblvid);
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
		
	
		ButtonCOD=new JButton("DELETE");
		ButtonCOD.setBounds(400, 450,150, 40);
		ButtonCOD.setBackground(new Color(173,216,230));
		add(ButtonCOD);
		image.add(ButtonCOD);
		
		ButtonCOC=new JButton("CLEAR");
		ButtonCOC.setBounds(600, 450,150, 40);
		ButtonCOC.setBackground(new Color(173,216,230));
		add(ButtonCOC);
		image.add(ButtonCOC);
		
		ButtonCOCl=new JButton("CLOSE");
		ButtonCOCl.setBounds(800, 450,150, 40);
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
		new CheckIn_delete();
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		if(ae.getSource()==ButtonCOD)
		{
			
				if (cb1.getSelectedItem().toString().length()==0 || txtVId.getText().length()==0 || txtEmId.getText().length()==0 || txtHodId.getText().length()==0 || txtDepId.getText().length()==0)
				{
					JOptionPane.showMessageDialog(this, "Check all data first..");
				}
				else
				{
					int opt=JOptionPane.showConfirmDialog(null,"Are you sure to Delete","Delete",JOptionPane.YES_NO_OPTION);
					if(opt==0)
					{
				String CheckinId=cb1.getSelectedItem().toString();
				String VisitorId=txtVId.getText();
				String EmpId=txtEmId.getText();
				String HodId=txtHodId.getText();
				String DepId=txtDepId.getText();
				String Date_in=txtDO.getText();
				

				CheckinDATA_delete obj=new CheckinDATA_delete();
				String msg=obj.deleteData(CheckinId,VisitorId, EmpId,HodId,DepId,Date_in);
				JOptionPane.showMessageDialog(this, msg);
				
				cb1.getSelectedItem().toString();
				txtVId.setText("");
				txtEmId.setText("");
				txtHodId.setText("");
				txtDepId.setText("");
				txtDO.setText("");
				txtTO.setText("");
				txtAcTO.setText("");
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
		String sql="select CheckinId from Checkin";
		st=con.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		while(rs.next())
		{
			cb1.addItem(rs.getString(1));
		}
	}
	catch(Exception ex)
	{
		
	}
}
@Override
public void itemStateChanged(ItemEvent e) {
	// TODO Auto-generated method stub
	if(e.getSource()==cb1)
		
		{
			String k=cb1.getSelectedItem().toString();
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
