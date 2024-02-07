package com.util.data;
import java.sql.*;

import javax.swing.*;
public class visitorupdatehandle {
	Connection con;PreparedStatement st;ResultSet rs;
	public String updatedata(String a,String b,String c,String d,String e,String f,String g,String h)
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			String sql="update Visitors set VisitorName=?,VisitorEmail=?,VisitorPhone=?,VisitorAddress=?,Gender=?,GovIdType=?,GovId=?,Status=? where VisitorId=?";
			st=con.prepareStatement(sql);
			st.setString(1, a);
			st.setString(2, b);
			st.setString(3, c);
			st.setString(4,d);
			st.setString(5,e);
			st.setString(6,f);
			st.setString(7,g);
			st.setInt(8,1);
			st.setString(9,h);
			int as=st.executeUpdate();
			con.close();
			return "Record Saved";
		}
		catch(Exception ex){
			return ex.toString();
		}
	}
}