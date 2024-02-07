package com.VisitorsManagement;
import javax.swing.*;

import com.util.data.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.*;
import java.sql.*;
public class CheckOut_find extends JFrame implements ActionListener, ItemListener{
	JTextField txtVId,txtEmId,txtHodId,txtDepId,txtDO,txtTO,txtAcTO;
	JLabel lblCOH,lblCOId,lblVId,lblEmId,lblHodId,lblDepId,lblDO,lblTO,lblAcTO;
	JButton ButtonCOF,ButtonCOC,ButtonCOCl;
	JComboBox cbCOId;
	Connection con;PreparedStatement st;ResultSet rs;
	public CheckOut_find() {
		setSize(1400,800);
		setLayout(null);
		setTitle("Checkout Find");
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
	    
	    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("find.png"));
	    Image i2 = i1.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
	    ImageIcon i3 = new ImageIcon(i2);
	    JLabel image1 = new JLabel("Check-out Find",i3,SwingConstants.CENTER);
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
		
	
		ButtonCOC=new JButton("CLEAR");
		ButtonCOC.setBounds(450, 575,150, 40);
		ButtonCOC.setBackground(new Color(173,216,230));
		add(ButtonCOC);
		image.add(ButtonCOC);
		
		ButtonCOCl=new JButton("CLOSE");
		ButtonCOCl.setBounds(750, 575,150, 40);
		ButtonCOCl.setBackground(new Color(173,216,230));
		add(ButtonCOCl);
		image.add(ButtonCOCl);
		
	
		ButtonCOC.addActionListener(this);
		ButtonCOCl.addActionListener(this);

		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
 }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new CheckOut_find();
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		if(ae.getSource()==ButtonCOF)
		{
			String k=cbCOId.getSelectedItem().toString();
			COfindDataHandle obj=new COfindDataHandle ();
			String s[]=obj.findData(k);
			if(s!=null)
			{
				txtVId.setText(s[0]);
				txtEmId.setText(s[1]);
				txtHodId.setText(s[2]);
				txtDepId.setText(s[3]);
				txtDO.setText(s[4]);
				txtTO.setText(s[5]);
						
	}
			else
			{
				JOptionPane.showMessageDialog(this,"Record not found");
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
			rs=st.executeQuery();
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
			{
				String k=cbCOId.getSelectedItem().toString();
				COfindDataHandle obj=new COfindDataHandle ();
				String s[]=obj.findData(k);
				if(s!=null)
				{
					txtVId.setText(s[0]);
					txtEmId.setText(s[1]);
					txtHodId.setText(s[2]);
					txtDepId.setText(s[3]);
					txtDO.setText(s[4]);
					txtTO.setText(s[5]);
								
		}
				else
				{
					JOptionPane.showMessageDialog(this,"Record not found");
				}
			
		}
		}}
}