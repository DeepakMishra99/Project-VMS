package com.VisitorsManagement;
import javax.swing.*;
import java.awt.*;
import com.util.data.*;
import java.awt.event.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CheckOut_Insert extends JFrame implements ActionListener,KeyListener,ItemListener{
	
	JTextField txtcheckin,txtTO,txtAcTO,txtvisid,txtempid,txthodid,txtdepid;
	JLabel lblCOH,lblCOId,lblVId,lblEmId,lblHodId,lblDepId,lblDO,lblTO,lblAcTO,ldatein;
	JButton ButtonCOS,ButtonCOC,ButtonCOCl;
	Connection con;PreparedStatement st;ResultSet rs;
	JComboBox combochinid;
	JProgressBar jp;
	int val=0;
	String t,d;
	String dd=null;
	public CheckOut_Insert() {
		setSize(1400,800);
		setLayout(null);
		setTitle("Checkout Insert");
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
	    
	    
	    
	    
	    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("insert.png"));
	    Image i2 = i1.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
	    ImageIcon i3 = new ImageIcon(i2);
	    JLabel image1 = new JLabel("Check-out Insert",i3,SwingConstants.CENTER);
	    image1.setVerticalTextPosition(JLabel.CENTER);
	    image1.setHorizontalTextPosition(JLabel.RIGHT);
	    image1.setForeground(new Color(0,0,128));
	    image1.setFont(new Font("Arial",Font.BOLD,30));
	    image1.setBounds(550,0, 400, 150);
	    add(image1);
	    image.add(image1);
	    
	    
	    
	    lblCOId=new JLabel("CHECK-IN ID:");
		lblCOId.setBounds(450,150,150,40);
		lblCOId.setFont(new Font("Arial",Font.BOLD,16));
		add(lblCOId);
		image.add(lblCOId);
		combochinid=new JComboBox();
		combochinid.setBounds(600,150,250, 40);
		combochinid.insertItemAt("",0);
		add(combochinid);
		fillchinid();
		image.add(combochinid);
		combochinid.addKeyListener(this);
		combochinid.addItemListener(this);
	    
	    lblDepId=new JLabel("DEPARTMENT ID:");
		lblDepId.setBounds(450,200,150,40);
		lblDepId.setFont(new Font("Arial",Font.BOLD,16));
		add(lblDepId);
		image.add(lblDepId);
		txtdepid=new JTextField();
		txtdepid.setBounds(600,200,250, 40);
		add(txtdepid);
		image.add(txtdepid);
		
		lblHodId=new JLabel("HOD ID:");
		lblHodId.setBounds(450,250,150,40);
		lblHodId.setFont(new Font("Arial",Font.BOLD,16));
		add(lblHodId);
		image.add(lblHodId);
		txthodid=new JTextField();
		txthodid.setBounds(600,250,250, 40);
		add(txthodid);
		image.add(txthodid);
		txthodid.addKeyListener(this);
		
		lblEmId=new JLabel("EMPLOYEE ID:");
		lblEmId.setBounds(450,300,150,40);
		lblEmId.setFont(new Font("Arial",Font.BOLD,16));
		add(lblEmId);
		image.add(lblEmId);
		txtempid=new JTextField();
		txtempid.setBounds(600,300,250, 40);
		add(txtempid);
		image.add(txtempid);
		txtempid.addKeyListener(this);
		
		lblVId=new JLabel("VISITOR ID:");
		lblVId.setBounds(450,350,150,40);
		lblVId.setFont(new Font("Arial",Font.BOLD,16));
		add(lblVId);
		image.add(lblVId);
		txtvisid=new JTextField();
		txtvisid.setBounds(600,350,250, 40);
		add(txtvisid);
		image.add(txtvisid);
		txtvisid.addKeyListener(this);
		
		
		
		lblDO=new JLabel("CHECK-IN TIME:");
		lblDO.setBounds(450,400,150,40);
		lblDO.setFont(new Font("Arial",Font.BOLD,16));
		add(lblDO);
		image.add(lblDO);
		txtcheckin=new JTextField();
		txtcheckin.setBounds(600, 400,250, 40);
		add(txtcheckin);
		image.add(txtcheckin);
		txtcheckin.addKeyListener(this);
		ldatein=new JLabel();
		ldatein.setBounds(0,0,0,0);
		
		add(ldatein);
		
		
		lblTO=new JLabel("CHECK-OUT TIME:");
		lblTO.setBounds(450,450,150,40);
		lblTO.setFont(new Font("Arial",Font.BOLD,16));
		add(lblTO);
		image.add(lblTO);
		txtTO=new JTextField();
		txtTO.setText(current());
		txtTO.setBounds(600,450,250, 40);
		txtTO.setFont(new Font("Arial",Font.BOLD,16));
		add(txtTO);
		image.add(txtTO);
		txtTO.addKeyListener(this);
		
		
		
		jp=new JProgressBar();
		jp.setMinimum(0);
		jp.setMaximum(100);
		jp.setValue(val);
		jp.setBounds(270,635,800,20);
		add(jp);
		image.add(jp);
		jp.setStringPainted(true);

		ButtonCOS=new JButton("SAVE");
		ButtonCOS.setBounds(480,575,150, 40);
		ButtonCOS.setBackground(new Color(173,216,230));
		add(ButtonCOS);
		image.add(ButtonCOS);
		
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
		
		ButtonCOS.addActionListener(this);
		ButtonCOC.addActionListener(this);
		ButtonCOCl.addActionListener(this);
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
 }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new CheckOut_Insert();
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		if(ae.getSource()==ButtonCOS)
		{
			
				if (combochinid.getSelectedItem().toString().length()==0 ||txtvisid.getText().length()==0 ||txtempid.getText().length()==0 ||txthodid.getText().length()==0 ||txtdepid.getText().length()==0)
				{
					JOptionPane.showMessageDialog(this, "Check all data first..");
				}
				else
				{
				String checkoutid=combochinid.getSelectedItem().toString();
				String visitorid=txtvisid.getText();
				String empid=txtempid.getText();
				String hodid=txthodid.getText();
				String depid=txtdepid.getText();
				String checkintime=txtcheckin.getText();

				CheckOutDataHandle obj=new CheckOutDataHandle();
				String msg=obj.insertData(checkoutid,visitorid,empid,hodid,depid,checkintime);
				JOptionPane.showMessageDialog(this, msg);
				
				combochinid.getSelectedItem().toString();
				txtvisid.setText("");
				txtempid.setText("");
				txthodid.setText("");
				txtdepid.setText("");
				txtcheckin.setText("");
				
				
			}
		}
		if (ae.getSource()==ButtonCOC)
		{
			combochinid.getSelectedItem().toString();
			txtvisid.setText("");
			txtempid.setText("");
			txthodid.setText("");
			txtdepid.setText("");
			txtcheckin.setText("");
			txtTO.setText("");
		
			val=0;
			jp.setValue(val);
		}
		if (ae.getSource()==ButtonCOCl)
		{
			this.dispose();
		}
		
			}
			
	
	@Override
	public void keyPressed(KeyEvent ar) {
		// TODO Auto-generated method stub
		//txtDO,txtTO,txtAcTO
		//combovisid,comboempid,combohodid,combodepid,combochinid
		if(ar.getSource()==txtdepid && txtdepid.getText().length()>0)
		{
			if(ar.getKeyCode()==ar.VK_ENTER)
			{
				txthodid.requestFocus();
				val=val+16;
				jp.setValue(val);
			}
		}
		if(ar.getSource()==txthodid && txthodid.getText().length()>0)
		{
			if(ar.getKeyCode()==ar.VK_ENTER)
			{
				txtempid.requestFocus();
				val=val+12;
				jp.setValue(val);
			}
		}
		if(ar.getSource()==txtempid && txtempid.getText().length()>0)
		{
			if(ar.getKeyCode()==ar.VK_ENTER)
			{
				txtvisid.requestFocus();
				val=val+12;
				jp.setValue(val);
			}
		}
		if(ar.getSource()==txtvisid && txtvisid.getText().length()>0)
		{
			if(ar.getKeyCode()==ar.VK_ENTER)
			{
				combochinid.requestFocus();
				val=val+12;
				jp.setValue(val);
			}
		}
		if(ar.getSource()==combochinid && combochinid.getSelectedItem().toString().length()>0)
		{
			if(ar.getKeyCode()==ar.VK_ENTER)
			{
				txtcheckin.requestFocus();
				val=val+12;
				jp.setValue(val);
			}
		}
		if(ar.getSource()==txtcheckin && txtcheckin.getText().length()>0)
		{
			if(ar.getKeyCode()==ar.VK_ENTER)
			{
				txtTO.requestFocus();
				val=val+12;
				jp.setValue(val);

			}
		}
		if(ar.getSource()==txtTO && txtTO.getText().length()>0)
		{
			if(ar.getKeyCode()==ar.VK_ENTER)
			{
				txtAcTO.requestFocus();
				val=val+12;
				jp.setValue(val);

			}
		}
		
		

			}
	
	String current()
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Step-2 Connection create
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//TO_CHAR(sysdate+ 30/(24*60) , 'HH24:MI:SS')
			String sql = "SELECT sysdate FROM dual";
			st = con.prepareStatement(sql);
			rs = st.executeQuery();
			
			while (rs.next()) {
				d=rs.getString(1);
				
				
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return d;
		
	}
	
	String tout(String timeout)
	{
		LocalDateTime myDateObj = LocalDateTime.now();
	    
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("HH:mm:ss");
	    //myDateObj=myDateObj.plusMinutes(30);

	    timeout= myDateObj.format(myFormatObj);
	   
				
	return timeout;
	}
			
			void fillchinid()
			{
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					//Step-2 Connection create
					con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
					//Step-3 Statement create
					String sql="select CHECKINID from checkin";
					st=con.prepareStatement(sql);
					rs=st.executeQuery();
					while(rs.next())
					{
						combochinid.addItem(rs.getString(1));
					}
				}
				catch (Exception ex) {
					
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
	@Override
	public void itemStateChanged(ItemEvent ae) {
		// TODO Auto-generated method stub
		if(ae.getSource()==combochinid)
		{
			String k=combochinid.getSelectedItem().toString();
			CheckinDATA_find obj=new CheckinDATA_find();
			String s[]=obj.findData(k);
			if(s!=null)
			{
				txtvisid.setText(s[0]);
				txtempid.setText(s[1]);
				txthodid.setText(s[2]);
				txtdepid.setText(s[3]);
				txtcheckin.setText(s[4]);
								
	}
			else
			{
				JOptionPane.showMessageDialog(this,"Record not found");
			}
			
		}
		
	}

}


	

