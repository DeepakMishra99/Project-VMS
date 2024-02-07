package com.util.data;

import java.sql.*;
import javax.swing.*;

public class EmployeeDATA_find{
		Connection con;PreparedStatement st;ResultSet rs;
		public String[] findData(String a)
		{
			String x[]=new String[7];
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
					String sql="select employeeName,employeeEmail,employeePhone,employeeAddress,employeeGender,Hodid,Departmentid from Employee where empId=?";
					st=con.prepareStatement(sql);
					st.setString(1, a);
					rs=st.executeQuery();
					//txtusernm,txtuserem,txtuserph,txtuserad,txtusergen;
					if (rs.next())
					{
						x[0]=rs.getString(1);
						x[1]=rs.getString(2);
						x[2]=rs.getString(3);
						x[3]=rs.getString(4);
						x[4]=rs.getString(5);
						x[5]=rs.getString(6);
						x[6]=rs.getString(7);
					}
					else
					{
						System.out.println("Wrong");
						x=null;
					}
					return x;
				}
			catch(Exception ex) {
				return null;
			}


	}

}
