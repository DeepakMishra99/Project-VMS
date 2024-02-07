package com.VisitorsManagement;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class defaulter extends JFrame {
	static JTable jt;int i=0;int j=0;
	String cols[]= {"DEFAULTER ID","CHECK-IN ID","DEPARTMENT ID","HOD ID","EMPLOYEE ID","DATE IN",};
	static Connection con;static Statement st,st1,st2,st3;static ResultSet rs,rs1,rs2,rs3;static double dg;
	static DefaultTableModel tb=new DefaultTableModel();
	static int n;
	JButton save;
	public defaulter() {
	// TODO Auto-generated constructor stub
		setSize(1400,800);
		setVisible(true);
		setTitle("Check-in Data Table");
		setLocationRelativeTo(null);  
	
		tb.setColumnIdentifiers(cols);
		jt=new JTable();
		
		jt.setModel(tb);
	
		jt.setFont(new Font("Arial",Font.BOLD,14));
		jt.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		fillData();
		jt.setBackground(new Color(0,76,153));
		jt.setForeground(new Color(255,255,255));
		
		
		JTableHeader header = jt.getTableHeader();
		header.setBackground(Color.yellow);
		header.setFont(new Font("Arial",Font.BOLD,16));
		JScrollPane jp=new JScrollPane(jt);
		add(jp);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
	}
	
	static void fillData()
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			String sql="(select checkinid,visitorid,depid,hodid,empid,datein from Checkin where to_date(datein)=to_date(sysdate) and (sysdate-datein)*24*60>30) ";
			st3=con.createStatement();
			rs3=st3.executeQuery(sql);
			
		
		
			while(rs3.next())
			{
			
				tb.addRow(new Object[] {rs3.getString(2),rs3.getString(1),rs3.getString(3),rs3.getString(4),rs3.getString(5),rs3.getString(6)});
			}
		}
		catch(Exception ex) {}
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	defaulter d=new defaulter();
	

	
	}


	
}
		
		
		
		
		
		
			


