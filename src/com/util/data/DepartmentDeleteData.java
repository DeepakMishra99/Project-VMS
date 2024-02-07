package com.util.data;


import java.sql.*;
import javax.swing.JOptionPane;
public class DepartmentDeleteData{
	Connection con;PreparedStatement st;ResultSet rs;
	public String DeleteData(String a)
	{
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			String sql="delete from Departments where DepartmentId = ?";
			st=con.prepareStatement(sql);
			st.setString(1,a);
			int as=st.executeUpdate();
			con.close();
			return"Record Deleted";
		}
		catch(Exception ex) {
			return ex.toString();
		}
	}

}



