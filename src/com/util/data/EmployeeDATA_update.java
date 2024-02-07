package com.util.data;
import java.sql.*;
import javax.swing.*;

public class EmployeeDATA_update{
		Connection con;PreparedStatement st;ResultSet rs;
		public String updateData(String a,String b,String c,String d,String e,String f,String g,String k)
		{
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
				String sql="update employee set employeeName=?,employeeEmail=?,employeePhone=?,employeeAddress=?,employeegender=?,hodId=?,departmentid=?,status=? where empid=?";
				st=con.prepareStatement(sql);
				st.setString(1, a);
				st.setString(2, b);
				st.setString(3,c);
				st.setString(4,d);
				st.setString(5,e);
				st.setString(6,f);
				st.setString(7,g);
				st.setInt(8,1);
				st.setString(9, k);
				int as=st.executeUpdate();
				con.close();
				return "Record Updated";
			}
			catch(Exception ex) {
				return ex.toString();
			}


	}

}
