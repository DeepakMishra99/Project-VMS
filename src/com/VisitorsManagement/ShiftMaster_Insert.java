package com.VisitorsManagement;

import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.*;
import java.sql.*;
import com.util.data.*;

public class ShiftMaster_Insert extends JFrame implements ActionListener,KeyListener{
	
	JTextField txtShId,txtFT,txtTT;
	JLabel lblShId,lblFT,lblTT;
	JButton Btsms,Btsmc,Btsmcl ,Btsmn;
	Connection con;PreparedStatement st;
	JProgressBar jp;
	int val=0;
	
	public ShiftMaster_Insert() {
		
		setSize(1400,800);
		setLayout(null);
		setTitle(" Shift Master Insert");
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
	    
	    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("insert.png"));
	    Image i2 = i1.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
	    ImageIcon i3 = new ImageIcon(i2);
	    JLabel image1 = new JLabel("Shift Master Insert",i3,SwingConstants.CENTER);
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
		txtShId=new JTextField();
		txtShId.setText(getID());
		txtShId.setEditable(false);
		txtShId.setFont(new Font("Arial",Font.BOLD,16));
		txtShId.setBounds(650,150,250,30);
		add(txtShId);
		image.add(txtShId);
		txtShId.addKeyListener(this);
		
		lblFT=new JLabel("FROM TIME:");
		lblFT.setBounds(450,200,150,30);
		lblFT.setFont(new Font("Arial",Font.BOLD,16));
		add(lblFT);
		image.add(lblFT);
		txtFT=new JTextField();
		txtFT.setBounds(650,200,250,30);
		add(txtFT);
		image.add(txtFT);
		txtFT.addKeyListener(this);
		
		lblTT=new JLabel("TO TIME:");
		lblTT.setBounds(450,250,150,30);
		lblTT.setFont(new Font("Arial",Font.BOLD,16));
		add(lblTT);
		image.add(lblTT);
		txtTT=new JTextField();
		txtTT.setBounds(650,250,250,30);
		add(txtTT);
		image.add(txtTT);
		txtTT.addKeyListener(this);
		
		jp=new JProgressBar();
		jp.setMinimum(0);
		jp.setMaximum(100);
		jp.setValue(val);
		jp.setBounds(290,380,800,20);
		add(jp);
		jp.setStringPainted(true);
		image.add(jp);
		
		Btsms = new JButton("SAVE");
		Btsms.setBounds(400,320,150,30);
		Btsms.setFont(new Font("Arial",Font.BOLD,16));
		Btsms.setBackground(new Color(173,216,230));
		add(Btsms);
		image.add(Btsms);
		
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
		
		Btsms.addActionListener(this);
		Btsmc.addActionListener(this);
		Btsmcl.addActionListener(this);
	
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
 }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ShiftMaster_Insert();
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		if(ae.getSource()==Btsms)
		{
			
				if (txtShId.getText().length()==0 || txtFT.getText().length()==0 || txtTT.getText().length()==0)
				{
					JOptionPane.showMessageDialog(this, "Check all data first..");
				}
				else
				{
				String ShiftId=txtShId.getText();
				String FromTime=txtFT.getText();
				String ToTime=txtTT.getText();

				ShiftMaster_insertDataHandle obj=new ShiftMaster_insertDataHandle();
				String msg=obj.insertData(ShiftId,FromTime,ToTime);
				JOptionPane.showMessageDialog(this, msg);
				
				txtShId.setText("");
				txtFT.setText("");
				txtTT.setText("");
				
			}
		}
		if (ae.getSource()==Btsmc)
		{
			txtShId.setText("");
			txtFT.setText("");
			txtTT.setText("");
			val=0;
			jp.setValue(val);
		}
		if (ae.getSource()==Btsmcl)
		{
			this.dispose();
		}
		
		}
	
	
	String getID()
	{
		String shiftid;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			String sql="select  ShiftId from ShiftMaster";
			Statement st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs=st.executeQuery(sql);
			if (rs.next())
			{
				rs.last();
				String g=rs.getString(1);
				String w=g.substring(4,g.length());
				int x=Integer.parseInt(w);
				if (x<10)
					shiftid="SHI"+"000"+(x+1);
				else if(x>=10 && x<99)
					shiftid="SHI"+"00"+(x+1);
				else if(x>=100 && x<999)
					shiftid="SHI"+"0"+(x+1);
				else
					shiftid="SHI"+(x+1);
			}
			else {
				
				shiftid="SHI0001";
			}
			return shiftid;
		}
		catch (Exception ex) {
			return ex.toString();
		}
	}
	@Override
	public void keyPressed(KeyEvent ar) {
		// TODO Auto-generated method stub
		if(ar.getSource()==txtShId && txtShId.getText().length()>0)
		{
			if(ar.getKeyCode()==ar.VK_ENTER)
			{
				txtFT.requestFocus();
				val=val+35;
				jp.setValue(val);
			}
		}
		if(ar.getSource()==txtFT && txtFT.getText().length()>0)
		{
			if(ar.getKeyCode()==ar.VK_ENTER)
			{
				txtTT.requestFocus();
				val=val+35;
				jp.setValue(val);
			}
		}
		if(ar.getSource()==txtTT && txtTT.getText().length()>0)
		{
			if(ar.getKeyCode()==ar.VK_ENTER)
			{
				txtTT.requestFocus();
				val=val+30;
				jp.setValue(val);
			}
		}
		
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
	
	

