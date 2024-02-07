package com.util.data;


import java.sql.*;
import javax.swing.JOptionPane;
public class DepartmentsDataUpdate {
	Connection con;PreparedStatement st;ResultSet rs;
	public String UpdateData(String a,String b,String c)
	{
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			String sql="update Departments set DepartmentName = ?,Description=? where DepartmentId=?";
			st=con.prepareStatement(sql);
			st.setString(1,a);
			st.setString(2,b);
			st.setString(3,c);
			
			int as=st.executeUpdate();
			con.close();
			return"Record Updated";
		}
		catch(Exception ex) {
			return ex.toString();
		}
	}

}


