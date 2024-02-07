package com.util.data;
import java.sql.*;
import javax.swing.*;

public class Employee_ShiftMaster_DataFind{
		Connection con;PreparedStatement st;ResultSet rs;
		public String[] findData(String a)
		{
			String x[]=new String[1];
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
					String sql="select shiftid from employeeshiftmaster where empid=?";
					st=con.prepareStatement(sql);
					st.setString(1, a);
					rs=st.executeQuery();
					if (rs.next())
					{
						x[0]=rs.getString(1);
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


