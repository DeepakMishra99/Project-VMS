package com.VisitorsManagement;


import java.sql.DriverManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.sql.*;
public class Employee_ShiftMaster_DataTable extends JFrame {
	JTable jt;int i=0;int j=0;
	String cols[]= {"EMPLOYEE ID","SHIFT ID","STATUS"};
	Connection con;Statement st;ResultSet rs;
	DefaultTableModel tb=new DefaultTableModel();
	
	public Employee_ShiftMaster_DataTable() {
	// TODO Auto-generated constructor stub
		setSize(1400,800);
		setTitle("EMPLOYEE SHIFT DATA TABLE");
		setVisible(true);
		setLocationRelativeTo(null);
		
		tb.setColumnIdentifiers(cols);
		jt=new JTable();
		jt.setModel(tb);
		jt.setFont(new Font("Arial",Font.BOLD,14));
		jt.setBackground(new Color(40, 100, 80));
		jt.setForeground(new Color(255,255,255));
		fillData();
		jt.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		JTableHeader header = jt.getTableHeader();
		header.setBackground(Color.yellow);
		header.setFont(new Font("Arial",Font.BOLD,16));
		JScrollPane jp=new JScrollPane(jt);
		add(jp);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	void fillData()
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			String sql="select * from EmployeeShiftMaster";
			st=con.createStatement();
			rs=st.executeQuery(sql);
			while(rs.next())
			{
				tb.addRow(new Object[] {rs.getString(1),rs.getString(2),rs.getInt(3)});
			}
		}
		catch(Exception ex) {}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Employee_ShiftMaster_DataTable();
	}
}

