package com.util.data;

import java.sql.*;

public class CompanyMaster_InsertHandle {
	Connection con;PreparedStatement st;ResultSet rs;
	public String insertData(String a,String b,String c,String d,String e)
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			String sql="insert into companymaster values(?,?,?,?,?,?)";
			st=con.prepareStatement(sql);
			st.setString(1, a);
			st.setString(2, b);
			st.setString(4,c);
			st.setString(3,d);
			st.setString(5,e);
			st.setInt(6,1);
			int as=st.executeUpdate();
			con.close();
			return "Record Saved";
		}
		catch(Exception ex) {
			return ex.toString();
		}
	}
}
