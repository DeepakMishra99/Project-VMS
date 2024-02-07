package com.util.data;
import java.sql.*;

import javax.swing.JOptionPane;

public class ShiftMaster_insertDataHandle {
	Connection con;PreparedStatement st;ResultSet rs;
	public String insertData(String a,String b,String c)
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			String sql="insert into ShiftMaster values(?,?,?,?)";
			st=con.prepareStatement(sql);
			st.setString(1, a);
			st.setString(2, b);
			st.setString(3,c);
			st.setInt(4,1);
			int as=st.executeUpdate();
			con.close();
			return "Record Saved";
		}
		catch(Exception ex) {
			return ex.toString();
		}
}
	
}

