package com.util.data;

import java.sql.*;


public class Employee_ShiftMaster_DataHandle {
	Connection con;PreparedStatement st;ResultSet rs;
	public String insertData(String a,String b)
	{
		try 
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			String sql ="insert into EmployeeShiftMaster values(?,?,?)";
			st=con.prepareStatement(sql);
			st.setString(1, a);
			st.setString(2, b);
			st.setInt(3, 1);
			int  as=st.executeUpdate();
			con.close();
			return"Record Saved";
		}
		catch(Exception ex) {
			return ex.toString();
		}
	}

}

