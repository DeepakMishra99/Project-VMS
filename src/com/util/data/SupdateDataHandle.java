package com.util.data;
import java.sql.*;

import javax.swing.JOptionPane;

public class SupdateDataHandle {
	Connection con;PreparedStatement st;ResultSet rs;
	public String updateData(String a,String b,String c)
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			String sql="update ShiftMaster set FromTime=?,ToTime=? where ShiftId=?";
			st=con.prepareStatement(sql);
			st.setString(1, a);
			st.setString(2, b);
			st.setString(3,c);
			int as=st.executeUpdate();
			con.close();
			return "Record Updated";
		}
		catch(Exception ex) {
			return ex.toString();
		}
}
	
}

