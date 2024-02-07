package com.util.data;
import java.sql.*;


import javax.swing.JOptionPane;
public class UpdateDataHandle {
	Connection con;PreparedStatement st;ResultSet rs;
	public String updateData(String a,String b,String c,String d,String e,String f,String g,String h)
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			String sql="update CheckOut set VisitorId=?,EmpId=?,HodId=?,DepId=?,DateOut=?,TimeOut=?,ActualTimeOut=? where CheckOutId=?";
			st=con.prepareStatement(sql);
			st.setString(1,a);
			st.setString(2, b);
			st.setString(3,c);
			st.setString(4,d);
			st.setString(5,e);
			st.setString(6,f);
			st.setString(7,g);
			st.setString(8,h);
			int as=st.executeUpdate();
			con.close();
			return "Record Updated";
		}
		catch(Exception ex) {
			return ex.toString();
		}
}
	
}