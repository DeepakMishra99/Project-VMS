package com.util.data;
import java.sql.*;


import javax.swing.JOptionPane;
public class CheckOutDataHandle {
	Connection con;PreparedStatement st;ResultSet rs;String d;
	public String insertData(String a,String b,String c,String d,String e,String f)
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			String sql="insert into CheckOut values(?,?,?,?,?,?,sysdate)";
			st=con.prepareStatement(sql);
			st.setString(1, a);
			st.setString(2, b);
			st.setString(3,c);
			st.setString(4,d);
			st.setString(5,e);
			st.setString(6, f);
			
			
			int as=st.executeUpdate();
			con.close();
			return "Record Saved";
		}
		catch(Exception ex) {
			return ex.toString();
		}
}
	
	
}