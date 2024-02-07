package com.VisitorsManagement;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.*;
import java.awt.*;
import com.util.data.*;
import java.awt.event.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.Random;

public class CheckIn_insert extends JFrame implements ActionListener,KeyListener,ItemListener{
	
	
	JTextField txtCIId,txtVId,txtEmId,txtHodId,txtDepId,txtDIn,txtTIn,txtExTO;
  String checkinid,datein,timein,EXTO;
	JLabel lblvisnm,lblvisem,lblCIId,lblVId,lblEmId,lblHodId,lblDepId,lblDI,lblTI,lblExTO,lblempnm,lblempem,lblhodnm,lblotp,lbldepnm;
	JButton ButtonCOS,ButtonCOC,ButtonCOCl,ButtonCON,ButtonEmp,Buttonotp;
	JComboBox combovisid,comboempid,combohodid,combodepid;
	Connection con;PreparedStatement st;ResultSet rs;
	JProgressBar jp;
	int val=0;
	String d;
	String random;int randomPin;
	
	public CheckIn_insert() {
		setSize(1400,800);
		setLayout(null);
		setTitle("Check-in Insert");
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
	    JLabel image_logo = new JLabel("CHECKIN",logo3,SwingConstants.CENTER);
	    image_logo.setVerticalTextPosition(JLabel.BOTTOM);
	    image_logo.setHorizontalTextPosition(JLabel.CENTER);
	    image_logo.setFont(new Font("Arial",Font.BOLD,16));
	    image_logo.setBounds(10,10, 100, 150);
	    add(image_logo);
	    image.add(image_logo);
	    
	    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("insert.png"));
	    Image i2 = i1.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
	    ImageIcon i3 = new ImageIcon(i2);
	    JLabel image1 = new JLabel("Check-In Insert",i3,SwingConstants.CENTER);
	    image1.setVerticalTextPosition(JLabel.CENTER);
	    image1.setHorizontalTextPosition(JLabel.RIGHT);
	    image1.setForeground(new Color(0,0,128));
	    image1.setFont(new Font("Arial",Font.BOLD,30));
	    image1.setBounds(550,0, 400, 150);
	    add(image1);
	    image.add(image1);
		
	    lblCIId=new JLabel("CHECK-IN ID:");
		lblCIId.setBounds(400,150,150,30);
		lblCIId.setFont(new Font("Arial",Font.BOLD,16));
		add(lblCIId);
		image.add(lblCIId);
		txtCIId=new JTextField();
		txtCIId.setBounds(600,150,250,30);
		txtCIId.setFont(new Font("Arial",Font.BOLD,16));
		add(txtCIId);
		image.add(txtCIId);
		txtCIId.setText(getID(checkinid));
		txtCIId.setEditable(false);
		txtCIId.addKeyListener(this);
		
		lblVId=new JLabel("VISISTOR ID:");
		lblVId.setBounds(400,200,150,30);
		lblVId.setFont(new Font("Arial",Font.BOLD,16));
		add(lblVId);
		image.add(lblVId);
		combovisid=new JComboBox();
		combovisid.insertItemAt("", 0);
		combovisid.setBounds(600,200,250,30);
		add(combovisid);
		fillvisid();
		image.add(combovisid);
		combovisid.addKeyListener(this);
		combovisid.addItemListener(this);
		lblvisnm=new JLabel();
		lblvisnm.setBounds(870,200,150,30);
		lblvisnm.setFont(new Font("Arial",Font.BOLD,16));
		add(lblvisnm);
		lblvisnm.setForeground(new Color(0,0,128));
		
		image.add(lblvisnm);
		
		lblvisem=new JLabel();
		lblvisem.setBounds(0,0,0,0);
		add(lblvisem);
		
			
   	    lblDepId=new JLabel("DEPARTMENT ID:");
		lblDepId.setBounds(400,250,150,30);
		lblDepId.setFont(new Font("Arial",Font.BOLD,16));
		add(lblDepId);
		image.add(lblDepId);
		combodepid=new JComboBox();
		combodepid.insertItemAt("", 0);
		combodepid.setBounds(600,250,250,30);
		add(combodepid);
		image.add(combodepid);
		filldepid();
		combodepid.addKeyListener(this);
		combodepid.addItemListener(this);
		
		lbldepnm=new JLabel();
		lbldepnm.setBounds(870,250,300,30);
		lbldepnm.setFont(new Font("Arial",Font.BOLD,16));
		add(lbldepnm);
		lbldepnm.setForeground(new Color(0,0,128));
		image.add(lbldepnm);
		
		lblHodId=new JLabel("HOD ID:");
		lblHodId.setBounds(400,300,150,30);
		lblHodId.setFont(new Font("Arial",Font.BOLD,16));
		add(lblHodId);
		image.add(lblHodId);
		combohodid=new JComboBox();
		combohodid.insertItemAt("", 0);
		combohodid.setBounds(600,300,250,30);
		add(combohodid);
		image.add(combohodid);
		fillhodid();
		combohodid.addKeyListener(this);
		combohodid.addItemListener(this);
		
		lblhodnm=new JLabel();
		lblhodnm.setBounds(870,300,150,30);
		lblhodnm.setFont(new Font("Arial",Font.BOLD,16));
		add(lblhodnm);
		lblhodnm.setForeground(new Color(0,0,128));
		image.add(lblhodnm);
		
		lblEmId=new JLabel("EMPLOYEE ID:");
		lblEmId.setBounds(400,350,150,30);
		lblEmId.setFont(new Font("Arial",Font.BOLD,16));
		add(lblEmId);
		image.add(lblEmId);
		comboempid=new JComboBox();
		comboempid.insertItemAt("", 0);
		comboempid.setBounds(600,350,250,30);
		add(comboempid);
		fillempid();
		image.add(comboempid);
		comboempid.addKeyListener(this);
		comboempid.addItemListener(this);
		
		lblempnm=new JLabel();
		lblempnm.setBounds(870,350,150,30);
		lblempnm.setFont(new Font("Arial",Font.BOLD,16));
		add(lblempnm);
		lblempnm.setForeground(new Color(0,0,128));
		image.add(lblempnm);
		
		lblempem=new JLabel();
		lblempem.setBounds(0,0,0,0);
		add(lblempem);
		
		
				
		lblDI=new JLabel("DATE IN:");
		lblDI.setBounds(400,400,150,30);
		lblDI.setFont(new Font("Arial",Font.BOLD,16));
		add(lblDI);
		image.add(lblDI);
		txtDIn=new JTextField();
		txtDIn.setBounds(600,400,250,30);
		txtDIn.setFont(new Font("Arial",Font.BOLD,16));
		add(txtDIn);
		image.add(txtDIn);
		txtDIn.setText(getID1(datein));
		txtDIn.setEditable(false);
		txtDIn.addKeyListener(this);
		
		lblTI=new JLabel("TIME IN:");
		lblTI.setBounds(400,450,150,30);
		lblTI.setFont(new Font("Arial",Font.BOLD,16));
		add(lblTI);
		image.add(lblTI);
		txtTIn=new JTextField();
		txtTIn.setBounds(600,450,250,30);
		txtTIn.setFont(new Font("Arial",Font.BOLD,16));
		add(txtTIn);
		image.add(txtTIn);
		txtTIn.setText(getID2(timein));
		txtTIn.setEditable(false);
		txtTIn.addKeyListener(this);
			
		lblExTO=new JLabel("EXPECTED TIME OUT:");
		lblExTO.setBounds(400,500,200,30);
		lblExTO.setFont(new Font("Arial",Font.BOLD,16));
		add(lblExTO);
		image.add(lblExTO);
		txtExTO=new JTextField();
		txtExTO.setBounds(600,500,250,30);
		txtExTO.setFont(new Font("Arial",Font.BOLD,16));
		txtExTO.setText(getID3(EXTO));
		txtExTO.setEditable(false);
		add(txtExTO);
		image.add(txtExTO);
		txtExTO.addKeyListener(this);
		
		jp=new JProgressBar();
		jp.setMinimum(0);
		jp.setMaximum(100);
		jp.setValue(val);
		jp.setBounds(310,560,800,20);
		add(jp);
		jp.setFont(new Font("Arial",Font.BOLD,16));
		image.add(jp);
		jp.setStringPainted(true);
		
		lblotp=new JLabel();
		lblotp.setBounds(0,0,0,0);
    	add(lblotp);
		
		ButtonCOS=new JButton("SAVE");
		ButtonCOS.setBounds(380,600,150,40);
		ButtonCOS.setBackground(new Color(173,216,230));
		add(ButtonCOS);
		ButtonCOS.setFont(new Font("Arial",Font.BOLD,16));
		image.add(ButtonCOS);
		
		ButtonCOC=new JButton("CLEAR");
		ButtonCOC.setBounds(550,600,150,40);
		ButtonCOC.setBackground(new Color(173,216,230));
		add(ButtonCOC);
		ButtonCOC.setFont(new Font("Arial",Font.BOLD,16));
		image.add(ButtonCOC);
		
		ButtonCOCl=new JButton("CLOSE");
		ButtonCOCl.setBounds(720,600,150,40);
		ButtonCOCl.setBackground(new Color(173,216,230));
		add(ButtonCOCl);
		ButtonCOCl.setFont(new Font("Arial",Font.BOLD,16));
		image.add(ButtonCOCl);
		
		ButtonCON=new JButton("NEW VISITOR");
		ButtonCON.setBounds(890,600,150,40);
		ButtonCON.setBackground(new Color(173,216,230));
		add(ButtonCON);
		ButtonCON.setFont(new Font("Arial",Font.BOLD,16));
		image.add(ButtonCON);
		
		
		
		ButtonCOS.addActionListener(this);
		ButtonCOC.addActionListener(this);
		ButtonCOCl.addActionListener(this);
		txtCIId.addActionListener(this);
		txtDIn.addActionListener(this);
		txtTIn.addActionListener(this);
		ButtonCON.addActionListener(this);
		
				
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
 }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new CheckIn_insert();
	}
	
	void mail(String to, String name,String forname, String forid)
	{

		randomPin   =(int) (Math.random()*90000)+10000;
	       random  = String.valueOf(randomPin);	 
		 
		 //Send OTP To Mail
		
		  String host="mail.gmail.com";  
		  final String user="mishradeepak0101@gmail.com";//change accordingly  
		  final String password="mviyrajekngjzbmg";//change accordingly  
		    
		  
		  
		   //Get the session object  
		   Properties p = new Properties();  
		   //props.put("mail.smtp.host",host);  
		   //props.put("mail.smtp.auth", "true");  
		   p.put("mail.smtp.auth", "true");
		   p.put("mail.transport.protocol", "smtp");
		   p.put("mail.smtp.host", "smtp.gmail.com");
		   p.put("mail.smtp.port", "587");
		   p.put("mail.smtp.starttls.enable","true");
		   p.put("mail.smtp.starttls.required","true");   
		   Session session = Session.getDefaultInstance(p,  
		    new javax.mail.Authenticator() {  
		      protected PasswordAuthentication getPasswordAuthentication() {  
		    return new PasswordAuthentication(user,password);  
		      }  
		    });  
		  
		   //Compose the message  
		   
		     MimeMessage message = new MimeMessage(session);  
		     try {
				message.setFrom(new InternetAddress(user));
			} catch (AddressException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
		     try {
				message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
			} catch (AddressException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
		     try {
				message.setSubject("Visitor Email Verification OTP");
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
		     try {
				message.setText("Hi "+name+" ,\n"+"Verification OTP :"+random+
						"\n ID: "+forid+"\n NAME:"+forname
						+"\n\n This OTP is used for visitor and employee verification.");
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
		     

		       
		    //send the message  
		     try {
				Transport.send(message);
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
		  
		    
	}
	
	
	void depname() {
		try {
			//Step-1 Load the driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Step-2 Connection create
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//Step-3 Statement create
			String sql3="select departmentname from departments where departmentid=?";
			String did=combodepid.getSelectedItem().toString();
			st=con.prepareStatement(sql3);
			st.setString(1, did);
			rs=st.executeQuery();
			if (rs.next())
			{
				lbldepnm.setText(rs.getString(1));
							}
			else
			{
				JOptionPane.showMessageDialog(this,"not found");
			}
			con.close();
		}
		catch(Exception ex) {
			System.out.println(ex.toString());
		}
	}
	
	void visname()
	{
		
		
		try {
			//Step-1 Load the driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Step-2 Connection create
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//Step-3 Statement create
			String sql1="select visitorname,visitoremail from visitors where visitorid=?";
			String vid=combovisid.getSelectedItem().toString();
			st=con.prepareStatement(sql1);
			st.setString(1, vid);
			rs=st.executeQuery();
			if (rs.next())
			{
				lblvisnm.setText(rs.getString(1));
				lblvisem.setText(rs.getString(2));
				
			}
			else
			{
				JOptionPane.showMessageDialog(this,"not found");
			}
			con.close();
		}
		catch(Exception ex) {
			System.out.println(ex.toString());
		}
		
		mail(lblvisem.getText(), lblvisnm.getText(),lblvisnm.getText(), combovisid.getSelectedItem().toString());

		}
	void hodname()
	{
		try {
			//Step-1 Load the driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Step-2 Connection create
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//Step-3 Statement create
			String sql2="select hodname from headofdepartment where hodid=?";
			String hid=combohodid.getSelectedItem().toString();
			st=con.prepareStatement(sql2);
			st.setString(1, hid);
			rs=st.executeQuery();
			if (rs.next())
			{
				lblhodnm.setText(rs.getString(1));
			}
			else
			{
				JOptionPane.showMessageDialog(this,"not found");
			}
			con.close();
		}
		catch(Exception ex) {
			System.out.println(ex.toString());
		}
	}
	
	void empname()
	{
		
		
		try {
			//Step-1 Load the driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Step-2 Connection create
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//Step-3 Statement create
			String sql1="select employeename,employeeemail from employee where empid=?";
			String eid=comboempid.getSelectedItem().toString();
			st=con.prepareStatement(sql1);
			st.setString(1, eid);
			rs=st.executeQuery();
			if (rs.next())
			{
				lblempnm.setText(rs.getString(1));
				lblempem.setText(rs.getString(2));
				
			}
			else
			{
				JOptionPane.showMessageDialog(this,"not found");
			}
			con.close();
		}
		catch(Exception ex) {
			System.out.println(ex.toString());
		}
		
		mail(lblempem.getText(),lblempnm.getText(),lblvisnm.getText(),combovisid.getSelectedItem().toString());

		}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		if(ae.getSource()==comboempid)
		{
			update("none");
		}
		if(ae.getSource()==ButtonCOS)
		{
				if ( txtDIn.getText().length()==0 || txtTIn.getText().length()==0 || txtExTO.getText().length()==0)
				{
					JOptionPane.showMessageDialog(this,"Check all data first..");
				}
				else
				{
				
				String CheckInId=getID(checkinid);
				String VisitorId=combovisid.getSelectedItem().toString();
				String EmpId=comboempid.getSelectedItem().toString();
				String HodId=combohodid.getSelectedItem().toString();
				String DepId=combodepid.getSelectedItem().toString();
				

				CheckinDATA_insert obj=new CheckinDATA_insert();
				String msg=obj.insertData(CheckInId,VisitorId, EmpId,HodId,DepId);
				JOptionPane.showMessageDialog(this, msg);
				
				txtCIId.setText(getID(checkinid));
				txtDIn.setText(getID1(datein));
				}
							}
		
		if (ae.getSource()==ButtonCOC)
		{
			txtCIId.setText("");
			txtDIn.setText("");
		}
		if (ae.getSource()==ButtonCOCl)
		{
		  this.dispose();
		}
		if (ae.getSource()==ButtonCON)
		{
			new vistorInsert();
		}
			
	}
		
		private void update(String string) {
		// TODO Auto-generated method stub
		
	}
		String getID(String checkinid)
		{
			LocalDateTime myDateObj = LocalDateTime.now();
		    
		    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");

		    checkinid = "CI"+myDateObj.format(myFormatObj);
		   
					
		return checkinid;
		}
		String getID1(String datein)
		{
			LocalDateTime myDateObj = LocalDateTime.now();
		    
		    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy");

		    datein= myDateObj.format(myFormatObj);
		   
					
		return datein;
		}
		String getID2(String timein)
		{
			LocalDateTime myDateObj = LocalDateTime.now();
		    
		    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("HH:mm:ss");
		    //myDateObj=myDateObj.plusMinutes(30);

		    timein= myDateObj.format(myFormatObj);
		   
					
		return timein;
		}
		String getID3(String EXTO)
		{
			LocalDateTime myDateObj = LocalDateTime.now();
		    
		    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("HH:mm:ss");
		    myDateObj=myDateObj.plusMinutes(30);

		    EXTO= myDateObj.format(myFormatObj);
		   
					
		return EXTO;
		}
		
		String current()
		{
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				//Step-2 Connection create
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
				
				String sql = "SELECT SYSDATE FROM dual";
				st = con.prepareStatement(sql);
				rs = st.executeQuery();
				
				while (rs.next()) {
					d=rs.getString("SYSDATE");
					
					
				}
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return d;
			
		}


		
	@Override
	public void keyPressed(KeyEvent ar) {
		// TODO Auto-generated method stub
		if(ar.getSource()==txtCIId && txtCIId.getText().length()>0)
		{
			if(ar.getKeyCode()==ar.VK_ENTER)
			{
				combovisid.requestFocus();
				val=val+16;
				jp.setValue(val);

			}
		}
		if(ar.getSource()==combovisid && combovisid.getSelectedItem().toString().length()>0)
		{
			if(ar.getKeyCode()==ar.VK_ENTER)
			{
				comboempid.requestFocus();
				val=val+12;
				jp.setValue(val);
			}
		}
		if(ar.getSource()==comboempid && comboempid.getSelectedItem().toString().length()>0)
		{
			if(ar.getKeyCode()==ar.VK_ENTER)
			{
				combohodid.requestFocus();
				val=val+12;
				jp.setValue(val);
			}
		}
		if(ar.getSource()==combohodid && combohodid.getSelectedItem().toString().length()>0)
		{
			if(ar.getKeyCode()==ar.VK_ENTER)
			{
				combodepid.requestFocus();
				val=val+12;
				jp.setValue(val);
			}
		}
		if(ar.getSource()==combodepid && combohodid.getSelectedItem().toString().length()>0)
		{
			if(ar.getKeyCode()==ar.VK_ENTER)
			{
				txtDIn.requestFocus();
				val=val+12;
				jp.setValue(val);
			}
		}
		if(ar.getSource()==txtDIn && txtDIn.getText().length()>0)
		{
			if(ar.getKeyCode()==ar.VK_ENTER)
			{
				txtTIn.requestFocus();
				val=val+12;
				jp.setValue(val);
			}
		}
		if(ar.getSource()==txtTIn && txtTIn.getText().length()>0)
		{
			if(ar.getKeyCode()==ar.VK_ENTER)
			{
				txtExTO.requestFocus();
				val=val+12;
				jp.setValue(val);
			}
		}
		if(ar.getSource()==txtExTO && txtExTO.getText().length()>0)
		{
			if(ar.getKeyCode()==ar.VK_ENTER)
			{
				txtExTO.requestFocus();
				val=val+12;
				jp.setValue(val);
			}
		}
	}
	void fillvisid()
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Step-2 Connection create
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//Step-3 Statement create
			String sql="select visitorid from visitors";
			st=con.prepareStatement(sql);
			rs=st.executeQuery();
			while(rs.next())
			{
				combovisid.addItem(rs.getString(1));
			}
		}
		catch (Exception ex) {}
	}
	void fillempid()
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Step-2 Connection create
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//Step-3 Statement create
			String sql="select empid from employee";
			st=con.prepareStatement(sql);
			rs=st.executeQuery();
			while(rs.next())
			{
				comboempid.addItem(rs.getString(1));
			}
		}
		catch (Exception ex) {}
	}
	void fillhodid()
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Step-2 Connection create
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//Step-3 Statement create
			String sql="select hodid from headofdepartment";
			st=con.prepareStatement(sql);
			rs=st.executeQuery();
			while(rs.next())
			{
				combohodid.addItem(rs.getString(1));
			}
		}
		catch (Exception ex) {}
	}
	void filldepid()
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Step-2 Connection create
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//Step-3 Statement create
			String sql="select departmentid from departments";
			st=con.prepareStatement(sql);
			rs=st.executeQuery();
			while(rs.next())
			{
				combodepid.addItem(rs.getString(1));
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
		// TODO Auto-generated method stub
		
	}
	@Override
	public void itemStateChanged(ItemEvent ae) {
		// TODO Auto-generated method stub
		if(ae.getSource()==combodepid)
		{
			depname();
		}
		if(ae.getSource()==comboempid)
		{
			empname();
			
		}
		if(ae.getSource()==combohodid)
		{
			hodname();
		}
		if(ae.getSource()==combovisid)
		{
			visname();
		}
	}
}