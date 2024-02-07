package com.util.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CompanyMaster_updateHandle {
	Connection con;PreparedStatement st;ResultSet rs;
	public String updatedata(String a,String b,String c,String d,String e)
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			String sql="update companymaster set companyName=?,companyEmail=?,companyPhone=?,companygst=?,Status=? where companyId=?";
			st=con.prepareStatement(sql);
			st.setString(1, a);
			st.setString(2, b);
			st.setString(3, c);
			st.setString(4,d);
			st.setInt(5,1);
			st.setString(6,e);
			int as=st.executeUpdate();
			con.close();
			return "Record Saved";
		}
		catch(Exception ex){
			return ex.toString();
		}
	}
}
