package com.VisitorsManagement;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.util.data.CheckinDATA_find;


public class inform extends JFrame implements ActionListener,ItemListener{
	JLabel txtVId,txtEmId,txtHodId,txtDepId,txtDI,txtTI,txtExTO;
	JLabel lblCIH,lblCIId,lblVId,lblEmId,lblHodId,lblDepId,lblDI,lblTI,lblExTO;
	JLabel lblempem,lblempnm,lblvisnm,lblvisem,lblhodnm,lblhodem;
	JButton ButtonCIF,ButtonCIC,ButtonCICl;
	JComboBox cbCIId;
	Connection con;PreparedStatement st;ResultSet rs;
	public inform() {
		setSize(1400,800);
		setLayout(null);
		setTitle("Inform");
		setLocationRelativeTo(null); 
		setResizable(false);
		
		
		ImageIcon bg1 = new ImageIcon(ClassLoader.getSystemResource("all_bg.jpg"));
	    Image bg2= bg1.getImage().getScaledInstance(1400, 800, Image.SCALE_DEFAULT);
	    ImageIcon bg3 = new ImageIcon(bg2);
	    JLabel image = new JLabel(bg3);
	    image.setBounds(0,0, 1400, 800);
	    add(image);
	    
	    ImageIcon logo1 = new ImageIcon(ClassLoader.getSystemResource("warning.png"));
	    Image logo2= logo1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
	    ImageIcon logo3 = new ImageIcon(logo2);
	    JLabel image_logo = new JLabel("DEFAULTER",logo3,SwingConstants.CENTER);
	    image_logo.setVerticalTextPosition(JLabel.BOTTOM);
	    image_logo.setHorizontalTextPosition(JLabel.CENTER);
	    image_logo.setFont(new Font("Arial",Font.BOLD,16));
	    image_logo.setBounds(10,10, 100, 150);
	    add(image_logo);
	    image.add(image_logo);
	    
	    ImageIcon log1 = new ImageIcon(ClassLoader.getSystemResource("clock.png"));
	    Image log2= log1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
	    ImageIcon log3 = new ImageIcon(log2);
	    JLabel image_log = new JLabel(log3);
	    image_log.setVerticalTextPosition(JLabel.BOTTOM);
	    image_log.setHorizontalTextPosition(JLabel.CENTER);
	    
	    image_log.setBounds(10,0, 300, 150);
	    add(image_log);
	    image.add(image_log);
	    
	    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("defaulter.png"));
	    Image i2 = i1.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
	    ImageIcon i3 = new ImageIcon(i2);
	    JLabel image1 = new JLabel("Defaulter",i3,SwingConstants.CENTER);
	    image1.setVerticalTextPosition(JLabel.CENTER);
	    image1.setHorizontalTextPosition(JLabel.RIGHT);
	    image1.setForeground(new Color(0,0,128));
	    image1.setFont(new Font("Arial",Font.BOLD,30));
	    image1.setBounds(550,0, 400, 150);
	    add(image1);
	    image.add(image1);
		
		// extra
	    
	    lblempem=new JLabel();
	    lblempem.setBounds(0,0,0,0);
		add(lblempem);
		lblempnm=new JLabel();
		lblempnm.setBounds(0,0,0,0);
		add(lblempnm);
		lblvisnm=new JLabel();
		lblvisnm.setBounds(0,0,0,0);
		add(lblvisnm);
		lblvisem=new JLabel();
		lblvisem.setBounds(0,0,0,0);
		add(lblvisem);
		lblhodnm=new JLabel();
		lblhodnm.setBounds(0,0,0,0);
		add(lblhodnm);
		lblhodem=new JLabel();
		lblhodem.setBounds(0,0,0,0);
		add(lblhodem);
	   
	    
		lblCIId=new JLabel("CHECK-IN ID:");
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
		txtVId=new JLabel();
		txtVId.setBounds(600, 200,250, 40);
		add(txtVId);
		image.add(txtVId);
		
	
		lblEmId=new JLabel("EMPLOYEE ID:");
		lblEmId.setBounds(450,250,150,40);
		lblEmId.setFont(new Font("Arial",Font.BOLD,16));
		add(lblEmId);
		image.add(lblEmId);
		txtEmId=new JLabel();
		txtEmId.setBounds(600,250,250, 40);
		add(txtEmId);
		image.add(txtEmId);
		
		lblHodId=new JLabel("HOD ID:");
		lblHodId.setBounds(450,300,150,40);
		lblHodId.setFont(new Font("Arial",Font.BOLD,16));
		add(lblHodId);
		image.add(lblHodId);
		txtHodId=new JLabel();
		txtHodId.setBounds(600, 300,250, 40);
		add(txtHodId);
		image.add(txtHodId);
		
		lblDepId=new JLabel("DEPARTMENT ID:");
		lblDepId.setBounds(450,350,150,40);
		lblDepId.setFont(new Font("Arial",Font.BOLD,16));
		add(lblDepId);
		image.add(lblDepId);
		txtDepId=new JLabel();
		txtDepId.setBounds(600,350,250, 40);
		add(txtDepId);
		image.add(txtDepId);
		
		
		lblDI=new JLabel("DATE-IN:");
		lblDI.setBounds(450,400,150,40);
		lblDI.setFont(new Font("Arial",Font.BOLD,16));
		add(lblDI);
		image.add(lblDI);
		txtDI=new JLabel();
		txtDI.setBounds(600, 400,250, 40);
		add(txtDI);
		image.add(txtDI);
		
		
		
		
		ButtonCIF=new JButton("ALERT!");
		ButtonCIF.setBounds(900, 150,150, 40);
		ButtonCIF.setBackground(new Color(200,0,0));
		ButtonCIF.setForeground(new Color(255,255,255));
		ButtonCIF.setFont(new Font("Arial",Font.BOLD,16));
		add(ButtonCIF);
		image.add(ButtonCIF);
		
		
		
		
		ButtonCIF.addActionListener(this);
		

		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
 }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new inform();
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		
			
			if(ae.getSource()==ButtonCIF)
			{
			
				visname();
				hodname();
				empname();
				JOptionPane.showMessageDialog(this,"ALERT! Message send to:"+"\n Visitor"+"\n HOD"+"\nEmployee");
			
				
			}
			
		}
	void filldata()
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Step-2 Connection create
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//Step-3 Statement create
			String sql="(select checkinid,datein from Checkin where to_date(datein)=to_date(sysdate) and (sysdate-datein)*24*60>30)";
			st=con.prepareStatement(sql);
			rs=st.executeQuery();
			while(rs.next())
			{
				cbCIId.addItem(rs.getString(1));
			}
		}
		catch (Exception ex)
		{
			
		}
	}
	void mail(String type,String to, String name,String forname, String forid)
	{

		
		 
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
				message.setSubject(type+"ALERT!!");
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
		     try {
				message.setText(type+" ALERT!!"+"\nHi "+name+" ,\n"+"This is an ALERT! about Visitor"+
						"\n ID: "+forid+"\n NAME:"+forname);
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
	void visname()
	{
		String t="Vistior";
		
		try {
			//Step-1 Load the driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Step-2 Connection create
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//Step-3 Statement create
			String sql1="select visitorname,visitoremail from visitors where visitorid=?";
			String vid=txtVId.getText();
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
		
		mail(t,lblvisem.getText(), lblvisnm.getText(),lblvisnm.getText(), txtVId.getText());

		}
	void hodname()
	{
		String t="HOD";
		try {
			//Step-1 Load the driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Step-2 Connection create
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//Step-3 Statement create
			String sql2="select hodname,hodemail from headofdepartment where hodid=?";
			String hid=txtHodId.getText();
			st=con.prepareStatement(sql2);
			st.setString(1, hid);
			rs=st.executeQuery();
			if (rs.next())
			{
				lblhodnm.setText(rs.getString(1));
				lblhodem.setText(rs.getString(2));
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
		mail(t,lblhodem.getText(), lblhodnm.getText(),lblvisnm.getText(), txtVId.getText());
	}
	
	void empname()
	{
		String t="Employee";
		
		try {
			//Step-1 Load the driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Step-2 Connection create
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//Step-3 Statement create
			String sql1="select employeename,employeeemail from employee where empid=?";
			String eid=txtEmId.getText();
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
		
		mail(t,lblempem.getText(),lblempnm.getText(),lblvisnm.getText(),txtVId.getText());

		}
	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==cbCIId)
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
				txtDI.setText(s[4]);
								
	}
			else
			{
				JOptionPane.showMessageDialog(this,"Record not found");
			}
			
		}
		
	}
}