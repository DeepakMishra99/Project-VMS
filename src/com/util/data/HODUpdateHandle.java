package com.util.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class HODUpdateHandle {
	Connection con;PreparedStatement st;ResultSet rs;
	public String updatedata(String a,String b,String c,String d,String e,String f,String g)
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			String sql="update headofdepartment set hodName=?,hodEmail=?,hodPhone=?,hodAddress=?,departmentid=?,shiftid=?,Status=? where hodId=?";
			st=con.prepareStatement(sql);
			st.setString(1, a);
			st.setString(2, b);
			st.setString(3, c);
			st.setString(4,d);
			st.setString(5,e);
			st.setString(6,f);
			st.setInt(7,1);
			st.setString(8,g);
			int as=st.executeUpdate();
			con.close();
			return "Record Saved";
		}
		catch(Exception ex){
			return ex.toString();
		}
	}
}
