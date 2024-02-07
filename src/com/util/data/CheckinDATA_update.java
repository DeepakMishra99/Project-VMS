package com.util.data;

import java.sql.*;

import javax.swing.*;
public class CheckinDATA_update{
	Connection con;PreparedStatement st;ResultSet rs;
	public String updateData(String a,String b,String c,String d,String e)
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			String sql="update Checkin set VisitorId=?,EmpId=?,HodId=?,DepId=? where CheckinId=?";
			st=con.prepareStatement(sql);
			st.setString(1,a);
			st.setString(2, b);
			st.setString(3,c);
			st.setString(4,d);
			st.setString(5,e);
			
			int as=st.executeUpdate();
			con.close();
			return "Record Updated";
		}
		catch(Exception ex) {
			return ex.toString();
		}
}
	
}