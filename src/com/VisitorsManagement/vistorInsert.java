package com.VisitorsManagement;

import java.awt.*;
import java.awt.event.*;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.opencv.videoio.VideoCapture;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.PageRanges;
import javax.swing.*;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;

import java.io.FileOutputStream;
import com.itextpdf.text.Document;  
import com.itextpdf.text.Element;

import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter; 
import java.io.File;
import java.io.OutputStream;
import com.util.data.*;

import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

public class vistorInsert extends JFrame implements ActionListener,KeyListener {
	JLabel cameraScreen,lbl,lblvisid,lblvisname,lblvisemail,lblvisphone,lblvisaddress,lblgender,lblidtype,lblidno,validate_msg,otp,otp_msg,govtid_msg;
	JRadioButton radiomale,radiofemale;
	JTextField txtvisname,txtvisemail,txtvisphone,txtvisaddress,txtidno,otpf;
	JButton Capture,btnsave,btnclear,btnclose,btnprint,sendbutton,verify;
	ButtonGroup bg;
	String gender;
	JComboBox idtype;
	VideoCapture capture;
	Mat image;
	String visitor_id;String visid = null;
	Connection con;
	PreparedStatement st;
	JProgressBar jp;
	int val=0;
	private boolean clicked = false;
	String random;int randomPin;
	
	public vistorInsert() {
		setSize(1400,800); 
		setLayout(null);
		setTitle("Visitor Insert");
		setLocationRelativeTo(null); 
		setResizable(false);
		
		ImageIcon bg1 = new ImageIcon(ClassLoader.getSystemResource("all_bg.jpg"));
	    Image bg2= bg1.getImage().getScaledInstance(1400, 800, Image.SCALE_DEFAULT);
	    ImageIcon bg3 = new ImageIcon(bg2);
	    JLabel image = new JLabel(bg3);
	    image.setBounds(0,0, 1400, 800);
	    add(image);
	    
	    ImageIcon logo1 = new ImageIcon(ClassLoader.getSystemResource("visitor.png"));
	    Image logo2= logo1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
	    ImageIcon logo3 = new ImageIcon(logo2);
	    JLabel image_logo = new JLabel("VISITOR",logo3,SwingConstants.CENTER);
	    image_logo.setVerticalTextPosition(JLabel.BOTTOM);
	    image_logo.setHorizontalTextPosition(JLabel.CENTER);
	    image_logo.setFont(new Font("Arial",Font.BOLD,16));
	    image_logo.setBounds(10,10, 100, 150);
	    add(image_logo);
	    image.add(image_logo);
	    
	    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("insert.png"));
	    Image i2 = i1.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
	    ImageIcon i3 = new ImageIcon(i2);
	    JLabel image1 = new JLabel("Visitor Insert",i3,SwingConstants.CENTER);
	    image1.setVerticalTextPosition(JLabel.CENTER);
	    image1.setHorizontalTextPosition(JLabel.RIGHT);
	    image1.setFont(new Font("Arial",Font.BOLD,30));
	    image1.setBounds(550,0, 300, 150);
	    image1.setForeground(new Color(0,0,128));
	    add(image1);
	    image.add(image1);
	    
		lblvisname=new JLabel("VISITOR NAME:");
		lblvisname.setBounds(450, 130, 150, 30);
		lblvisname.setFont(new Font("Arial",Font.BOLD,16));
		add(lblvisname);
		image.add(lblvisname);
		txtvisname=new JTextField();
		txtvisname.setBounds(600,130,250,30);
		add(txtvisname);
		image.add(txtvisname);
		txtvisname.addKeyListener(this);
		
		lblvisemail=new JLabel("EMAIL:");
		lblvisemail.setBounds(450,180, 200, 30);
		lblvisemail.setFont(new Font("Arial",Font.BOLD,16));
		add(lblvisemail);
		image.add(lblvisemail);
		txtvisemail=new JTextField();
		txtvisemail.setBounds(600,180,250,30);
		add(txtvisemail);
		image.add(txtvisemail);
		
		validate_msg=new JLabel();
		validate_msg.setBounds(880,180,100,30);
		validate_msg.setFont(new Font("Arial",Font.BOLD,14));
		add(validate_msg);
		image.add(validate_msg);
		txtvisemail.addKeyListener(this);
		sendbutton=new JButton("SEND");
		sendbutton.setBounds(1000,180,100,30);
		sendbutton.setFont(new Font("Arial",Font.BOLD,14));
		add(sendbutton);
		image.add(sendbutton);
		otp=new JLabel("OTP");                 
		otp.setBounds(550, 230, 100, 30);
		otp.setFont(new Font("Arial",Font.BOLD,16));
		add(otp);
		image.add(otp);
		otpf=new JTextField();
		otpf.setBounds(650,230,200,30);
		add(otpf);
		image.add(otpf);
		otp_msg=new JLabel();
		otp_msg.setBounds(880,230,100,30);
		otp_msg.setFont(new Font("Arial",Font.BOLD,14));
		add(otp_msg);
		image.add(otp_msg);
		verify=new JButton("VERIFY");
		verify.setBounds(1000,230,100,30);
		verify.setFont(new Font("Arial",Font.BOLD,14));
		add(verify);
		image.add(verify);
		sendbutton.addActionListener(this);
		verify.addActionListener(this);
		
		lblvisphone=new JLabel("PHONE No.:");                 
		lblvisphone.setBounds(450, 280, 150, 30);
		lblvisphone.setFont(new Font("Arial",Font.BOLD,16));
		add(lblvisphone);
		image.add(lblvisphone);
		txtvisphone=new JTextField();
		txtvisphone.setBounds(600,280,250,30);
		add(txtvisphone);
		image.add(txtvisphone);
		txtvisphone.addKeyListener(this);
		
		lblvisaddress=new JLabel("ADDRESS:");
		lblvisaddress.setBounds(450,330, 150, 30);
		lblvisaddress.setFont(new Font("Arial",Font.BOLD,16));
		add(lblvisaddress);
		image.add(lblvisaddress);
		txtvisaddress=new JTextField();
		txtvisaddress.setBounds(600,330,250,30);
		add(txtvisaddress);
		image.add(txtvisaddress);
		txtvisaddress.addKeyListener(this);
		
		lblgender=new JLabel("GENDER:");
		lblgender.setBounds(450,380, 150, 30);
		lblgender.setFont(new Font("Arial",Font.BOLD,16));
		add(lblgender);
		image.add(lblgender);
		
		radiomale=new JRadioButton("MALE");
		radiomale.setBounds(620,380,100,30);
		radiomale.setFont(new Font("Arial",Font.BOLD,16));
		radiomale.setContentAreaFilled(false);
		add(radiomale);
		
		radiomale.addKeyListener(this);
		
		radiofemale=new JRadioButton("FEMALE");
		radiofemale.setBounds(740,380,100,30);
		radiofemale.setFont(new Font("Arial",Font.BOLD,16));
		add(radiofemale);
		radiofemale.setContentAreaFilled(false);
		
		radiofemale.addKeyListener(this);
		
		bg = new ButtonGroup();
		bg.add(radiomale);
		bg.add(radiofemale);
		image.add(radiofemale);
		image.add(radiomale);
		
		
		
		lblidtype=new JLabel("GOVT. ID TYPE:");
		lblidtype.setBounds(450,430, 150, 30);
		lblidtype.setFont(new Font("Arial",Font.BOLD,16));
		add(lblidtype);
		image.add(lblidtype);
		
		idtype=new JComboBox();
		idtype.setBounds(600,430,250,30);
		add(idtype);
		image.add(idtype);
		
		filldata();
		idtype.addKeyListener(this);
		
		lblidno=new JLabel("GOVT. ID No.:");
		lblidno.setBounds(450,480, 150, 30);
		lblidno.setFont(new Font("Arial",Font.BOLD,16));
		add(lblidno);
		image.add(lblidno);
		txtidno=new JTextField();
		txtidno.setBounds(600,480,250,30);
		add(txtidno);
		image.add(txtidno);
		govtid_msg=new JLabel();
		govtid_msg.setBounds(880,480,200,30);
		govtid_msg.setFont(new Font("Arial",Font.BOLD,14));
		add(govtid_msg);
		image.add(govtid_msg);
		txtidno.addKeyListener(this);
		
		
		
		
	   
		
		jp=new JProgressBar();
		jp.setMinimum(0);
		jp.setMaximum(100);
		jp.setValue(val);
		jp.setBounds(270,620,800,20);
		add(jp);
		image.add(jp);
		jp.setStringPainted(true);
		
		btnsave=new JButton("SAVE");
		btnsave.setBounds(400,550,100,40);
		btnsave.setBackground(new Color(173,216,230));
		add(btnsave);
		image.add(btnsave);
		
		btnclear=new JButton("CLEAR");
		btnclear.setBounds(600,550,100,40);
		btnclear.setBackground(new Color(173,216,230));
		add(btnclear);
		image.add(btnclear);
		
		btnclose=new JButton("CLOSE");
		btnclose.setBounds(800,550,100,40);
		btnclose.setBackground(new Color(173,216,230));
		add(btnclose);
		image.add(btnclose);
		
		btnprint=new JButton("PRINT");
		btnprint.setBounds(1000,550,120,40);
		btnprint.setBackground(new Color(173,216,230));
		add(btnprint);
		image.add(btnprint);
		
		/*Capture=new JButton("ADD PHOTO");
		Capture.setBounds(200,550,120,40);
		Capture.setBackground(new Color(173,216,230));
		add(Capture);
		image.add(Capture);*/
		
		
		
	
		
		
		
		//Capture.addActionListener(this);
		btnsave.addActionListener(this);
		btnclear.addActionListener(this);
		btnclose.addActionListener(this);
		btnprint.addActionListener(this);
		radiomale.addActionListener(this);
		radiofemale.addActionListener(this);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	
			

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new vistorInsert();
		
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		
		if(ae.getSource()==btnsave)
		{
			
			if (txtvisname.getText().length()==0 || txtvisphone.getText().length()==0 || txtvisemail.getText().length()==0 || txtvisaddress.getText().length()==0 || idtype.getSelectedItem().toString().length()==0 || txtidno.getText().length()==0)
			{
				JOptionPane.showMessageDialog(this, "Check all data first..");
			}
			else
			{
				String eid=getID(visitor_id);
				String na=txtvisname.getText();
				String ph=txtvisphone.getText();
				String eml=txtvisemail.getText();
				String add=txtvisaddress.getText();
				String dpn=idtype.getSelectedItem().toString();
				String dpid=txtidno.getText();
				
				VisitorDataHandle obj=new VisitorDataHandle();
				String msg=obj.insertData(eid,na, ph,eml,add,gender,dpn,dpid);
				JOptionPane.showMessageDialog(this, msg);
				
				txtvisname.setText("");
				txtvisemail.setText("");
				txtvisphone.setText("");
				txtvisaddress.setText("");
				idtype.setSelectedItem("");
				txtidno.setText("");
				JOptionPane.showMessageDialog(this, "Please Note Your Visit\n"+getID(visitor_id));
				
			}
		}
		if(ae.getSource()==btnclear)
		{
			
			txtvisname.setText("");
			txtvisemail.setText("");
			txtvisphone.setText("");
			txtvisaddress.setText("");
			idtype.setSelectedItem("");
			txtidno.setText("");
			val=0;
			jp.setValue(val);
			
		}
		if(ae.getSource()==btnclose)
		{
			this.dispose();
		}
		if(ae.getSource()==radiomale) {
			gender = "MALE";
		}
		if(ae.getSource()==radiofemale) {
			gender = "FEMALE";
		}
		
		if(ae.getSource()==btnprint) {
			if (txtvisname.getText().length()==0 || txtvisphone.getText().length()==0 || txtvisemail.getText().length()==0 || txtvisaddress.getText().length()==0 || idtype.getSelectedItem().toString().length()==0 || txtidno.getText().length()==0)
			{
				JOptionPane.showMessageDialog(this, "Check all data first..");
			}
			else
			{
			String a[]=new String[8];
			a[0]=getID(visitor_id);
			a[1]=idtype.getSelectedItem().toString();
			a[2]=txtidno.getText();
			a[3]=txtvisname.getText();
			a[4]=txtvisemail.getText();
			a[5]=txtvisphone.getText();
			a[6]=gender;
			a[7]=txtvisaddress.getText();
			
			qr();
			VisitorPdf(a);
			JOptionPane.showMessageDialog(this,"Pdf created successfully.");
			}
		}
		if(ae.getSource()==sendbutton) {
			
		
			randomPin   =(int) (Math.random()*90000)+10000;
		       random  = String.valueOf(randomPin);	 
			 
			 //Send OTP To Mail
			
			  String host="mail.gmail.com";  
			  final String user="mishradeepak0101@gmail.com";//change accordingly  
			  final String password="mviyrajekngjzbmg";//change accordingly  
			    
			  String to=txtvisemail.getText();//change accordingly  
			  
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
					message.setText("Hi Visitor,\n"+"Your Email Verification Code is:"+random);
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
			    emailotp(txtvisemail.getText(),random);
			     sendbutton.setText("RESEND");
			 
		}
		if(ae.getSource()==verify) {
			boolean a=verifyemailotp(txtvisemail.getText(),otpf.getText());
			if(a==true)
			{
				
				if(otpf.getText().equals(random))
				{
					System.out.println("true");
				otp_msg.setText("Verified");
				otp_msg.setForeground(new Color(0,102,51));
				}
				else
				{
					System.out.println("false");
					otp_msg.setText("Non-verified");
					otp_msg.setForeground(Color.RED);	
				}
			}
			
			
		}
		/*if(ae.getSource()==Capture) {
			
			
				new cam();
			
			
			
			
			}*/
			
			
		}

	
	boolean verifyemailotp(String m,String n)
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			String sql="select count(*) from emailotp where emailid=? and otp=?";
			st=con.prepareStatement(sql);
			st.setString(1, m);
			st.setString(2, n);
			ResultSet rs=st.executeQuery();
			if (rs.next())
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch(Exception ae) {
			System.out.println(ae.toString());
			return false;
		}
		
	}
	void emailotp(String a,String b)
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			String sql="insert into emailotp values(?,?)";
			st=con.prepareStatement(sql);
			st.setString(1, a);
			st.setString(2, b);
			st.executeUpdate();
			con.close();
			JOptionPane.showMessageDialog(this, "OTP Done");
		}
		catch(Exception ae) {
		}
		
	}
	void filldata()
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			String sql="select idname from typemaster";
			st=con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while(rs.next())
			{
				idtype.addItem(rs.getString(1));
				
			}
		
		}
		catch(Exception ae) {
		}
		}
	
	String getID(String visid)
	{
		
	
		 	 LocalDateTime myDateObj = LocalDateTime.now();
			    
			    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");

			    visid = myDateObj.format(myFormatObj);
						
				
			return visid;
		}
		
	void VisitorPdf(String arr[])
	{
		try {
		       //Create Document instance.
		Document document = new Document();
		 
		//Create OutputStream instance.
		OutputStream outputStream = 
		new FileOutputStream(new File("E:\\JAVA\\vms_project\\src\\pdf\\"+getID(visitor_id)+".pdf"));
		 
		//Create PDFWriter instance.
		        PdfWriter.getInstance(document, outputStream);
		 
		        //Open the document.
		        document.open();
		        com.itextpdf.text.Image image = com.itextpdf.text.Image.getInstance("E:\\JAVA\\vms_project\\src\\logo1.png");
		        
		        image.scaleToFit(200, 200);
		        float x = (PageSize.A4.getWidth() - image.getScaledWidth()) / 2;
		        float y = (PageSize.A4.getHeight()-image.getScaledHeight());
		        image.setAbsolutePosition(x, y);
		        
		        //Add content to the document using Image object.
		        document.add(image);
		 
		        //Add content to the document.
		        
		        Paragraph p=new Paragraph("Visitor Copy");
		        p.setAlignment(Paragraph.ALIGN_RIGHT) ;
		        document.add(p);
		        
		        
		        String vid=getID(visitor_id);
		        com.itextpdf.text.Image image1 = com.itextpdf.text.Image.getInstance("E:\\JAVA\\vms_project\\src\\qr\\"+vid+".jpg");
		        
		        image1.scaleToFit(200, 200);
		        float x1 = (PageSize.A4.getWidth() - image1.getScaledWidth())/4;
		        float y1 = (PageSize.A4.getHeight()-2*image1.getScaledHeight());
		        image1.setAbsolutePosition(x1, y1);
		        
		        document.add(image1);
		        
		    
		
		
		        
		      //Create Table object, Here 4 specify the no. of columns
		        PdfPTable pdfPTable = new PdfPTable(2);
		        pdfPTable.setPaddingTop(500);
		        pdfPTable.setSpacingBefore(450);
		        

		        //Create cells
		          
		        PdfPCell id = new PdfPCell(new Paragraph("Visitor ID"));
		        id.setHorizontalAlignment(Element.ALIGN_CENTER);
		        id.setPadding(10);
		        PdfPCell visitorid = new PdfPCell(new Paragraph(arr[0]));
		        visitorid.setHorizontalAlignment(Element.ALIGN_CENTER);
		        visitorid.setPadding(10);
		        PdfPCell idtype= new PdfPCell(new Paragraph("Govt. ID Type"));
		        idtype.setHorizontalAlignment(Element.ALIGN_CENTER);
		        idtype.setPadding(10);
		        PdfPCell visitoridtype = new PdfPCell(new Paragraph(arr[1]));
		        visitoridtype.setHorizontalAlignment(Element.ALIGN_CENTER);
		        visitoridtype.setPadding(10);
		        PdfPCell idno= new PdfPCell(new Paragraph("Govt. ID No."));
		        idno.setHorizontalAlignment(Element.ALIGN_CENTER);
		        idno.setPadding(10);
		        PdfPCell visitoridno = new PdfPCell(new Paragraph(arr[2]));
		        visitoridno.setHorizontalAlignment(Element.ALIGN_CENTER);
		        visitoridno.setPadding(10);
		        PdfPCell name = new PdfPCell(new Paragraph("Name"));
		        name.setHorizontalAlignment(Element.ALIGN_CENTER);
		        name.setPadding(10);
		        PdfPCell visitorname = new PdfPCell(new Paragraph(arr[3]));
		        visitorname.setHorizontalAlignment(Element.ALIGN_CENTER);
		        visitorname.setPadding(10);
		        PdfPCell email = new PdfPCell(new Paragraph("Email"));
		        email.setHorizontalAlignment(Element.ALIGN_CENTER);
		        email.setPadding(10);
		        PdfPCell visitoremail = new PdfPCell(new Paragraph(arr[4]));
		        visitoremail.setHorizontalAlignment(Element.ALIGN_CENTER);
		        visitoremail.setPadding(10);
		        PdfPCell phone= new PdfPCell(new Paragraph("Phone"));
		        phone.setHorizontalAlignment(Element.ALIGN_CENTER);
		        phone.setPadding(10);
		        PdfPCell visitorphone = new PdfPCell(new Paragraph(arr[5]));
		        visitorphone.setHorizontalAlignment(Element.ALIGN_CENTER);
		        visitorphone.setPadding(10);
		        PdfPCell gender= new PdfPCell(new Paragraph("Gender"));
		        gender.setHorizontalAlignment(Element.ALIGN_CENTER);
		        gender.setPadding(10);
		        PdfPCell visitorgender = new PdfPCell(new Paragraph(arr[6])); 
		        visitorgender.setHorizontalAlignment(Element.ALIGN_CENTER);
		        visitorgender.setPadding(10);
		        PdfPCell address= new PdfPCell(new Paragraph("Address"));
		        address.setHorizontalAlignment(Element.ALIGN_CENTER);
		        address.setPadding(10);
		        PdfPCell visitoraddress = new PdfPCell(new Paragraph(arr[7])); 
		        visitoraddress.setHorizontalAlignment(Element.ALIGN_CENTER);
		        visitoraddress.setPadding(10);

		       
		        
		 
		        //Add cells to table
		        pdfPTable.addCell(id);
		        pdfPTable.addCell(visitorid);
		        pdfPTable.addCell(idtype);
		        pdfPTable.addCell(visitoridtype);
		        pdfPTable.addCell(idno);
		        pdfPTable.addCell(visitoridno);
		        pdfPTable.addCell(name);
		        pdfPTable.addCell(visitorname);
		        
		        pdfPTable.addCell(email);
		        pdfPTable.addCell(visitoremail);
		        pdfPTable.addCell(phone);
		        pdfPTable.addCell(visitorphone);
		        pdfPTable.addCell(gender);
		        pdfPTable.addCell(visitorgender);
		        pdfPTable.addCell(address);
		        pdfPTable.addCell(visitoraddress);
		        
		        
		        
		        //Add content to the document using Table objects.
		        document.add(pdfPTable);
		        
		      
		        //Close document and outputStream.
		        document.close();
		        outputStream.close();
		        
		      
		    } catch (Exception e) {
		e.printStackTrace();
		    }
		  }
	
	void qr()
	{
		try
		{
			File file=new File("E:\\JAVA\\vms_project\\src\\qr\\"+getID(visitor_id)+".jpg");
			
			String content=("Visitor ID: "+getID(visitor_id)+"\n"+
							"Name:"+txtvisname.getText());
			
			ByteArrayOutputStream out=QRCode.from(content).to(ImageType.JPG).stream();
			FileOutputStream fos=new FileOutputStream(file);
			fos.write(out.toByteArray());
			fos.close();
			System.out.println("Success");
			
			
			
		} catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void keyPressed(KeyEvent ar) {
		// TODO Auto-generated method stub
		
		if(ar.getSource()==txtvisname && txtvisname.getText().length()>0)
		{
			if(ar.getKeyCode()==ar.VK_ENTER)
			{
				txtvisemail.requestFocus();
				val=val+15;
				jp.setValue(val);
			}
		}
		if(ar.getSource()==txtvisemail && txtvisemail.getText().length()>0)
		{
			if(ar.getKeyCode()==ar.VK_ENTER)
			{
				txtvisphone.requestFocus();
				val=val+14;
				jp.setValue(val);
			}
		}
		if(ar.getSource()==txtvisphone && txtvisphone.getText().length()>0)
		{
			if(ar.getKeyCode()==ar.VK_ENTER)
			{
				txtvisaddress.requestFocus();
				val=val+14;
				jp.setValue(val);
			}
		}
		if(ar.getSource()==txtvisaddress && txtvisaddress.getText().length()>0)
		{
			if(ar.getKeyCode()==ar.VK_ENTER)
			{
				idtype.requestFocus();
				val=val+14;
				jp.setValue(val);
			}
		}
	
		
		
		if(ar.getSource()==txtidno && txtidno.getText().length()>0)
		{
			if(ar.getKeyCode()==ar.VK_ENTER)
			{
				txtidno.requestFocus();
				val=val+14;
				jp.setValue(val);
			}
		}
		
		
	}

	@Override
	public void keyReleased(KeyEvent ae) {
		// TODO Auto-generated method stub
		
		String regex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+.[a-zA-Z0-9.-]+$";
	      //Creating a pattern object
	      Pattern pattern = Pattern.compile(regex);
	      //Creating a Matcher object
	      Matcher matcher = pattern.matcher(txtvisemail.getText());
	      //Verifying whether given phone number is valid
	      if(matcher.matches()) {
	    	  validate_msg.setText("Valid");
	    	  validate_msg.setForeground(new Color(0,102,51));
	         
	      } else {
	    	  validate_msg.setText("Invalid");
	    	  validate_msg.setForeground(Color.RED);
	         
	      }
	      
	      if(idtype.getSelectedItem().equals("ADHAAR CARD ID"))
	    		  {
	    	  String regex1 = "^[0-9] {12}$";
		      //Creating a pattern object
		      Pattern pattern1 = Pattern.compile(regex1);
		      //Creating a Matcher object
		      Matcher matcher1 = pattern1.matcher(txtidno.getText());
		      //Verifying whether given phone number is valid
		      if(matcher1.matches()) {
		    	  govtid_msg.setText("Valid");
		    	  govtid_msg.setForeground(new Color(0,102,51));
		         
		      } else {
		    	  govtid_msg.setText("Invalid (Must 12 digit)");
		    	  govtid_msg.setForeground(Color.RED);
	    		  }
		          
	    		  
	    		  }
	      else if(idtype.getSelectedItem().equals("PANCARD ID"))
	      {
	    	  String regex1 = "^[A-Z]{5}[0-9]{4}[A-Z]{1}$";
		      //Creating a pattern object
		      Pattern pattern1 = Pattern.compile(regex1);
		      //Creating a Matcher object
		      Matcher matcher1 = pattern1.matcher(txtidno.getText());
		      //Verifying whether given phone number is valid
		      if(matcher1.matches()) {
		    	  govtid_msg.setText("Valid");
		    	  govtid_msg.setForeground(new Color(0,102,51));
		         
		      } else {
		    	  govtid_msg.setText("Invalid (eg. ABCDE1234X)");
		    	  govtid_msg.setForeground(Color.RED);
	    		  }  
	      }
	      else if(idtype.getSelectedItem().equals("VOTER ID"))
	      {
	    	  String regex1 = "^[A-Z]{3}[0-9]{7}$";
		      //Creating a pattern object
		      Pattern pattern1 = Pattern.compile(regex1);
		      //Creating a Matcher object
		      Matcher matcher1 = pattern1.matcher(txtidno.getText());
		      //Verifying whether given phone number is valid
		      if(matcher1.matches()) {
		    	  govtid_msg.setText("Valid");
		    	  govtid_msg.setForeground(new Color(0,102,51));
		         
		      } else {
		    	  govtid_msg.setText("Invalid (eg. ABC1234567)");
		    	  govtid_msg.setForeground(Color.RED);
	    		  }  
	      }
	      else {
	    	  
	      }
	}
	     

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
