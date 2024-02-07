package com.util.data;
import java.sql.*;
import javax.swing.*;

public class DepartmentsDataFind{
		Connection con;PreparedStatement st;ResultSet rs;
		public String[] findData(String a)
		{
			String x[]=new String[2];
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
					String sql="select departmentname, description from departments where departmentid=?";
					st=con.prepareStatement(sql);
					st.setString(1, a);
					rs=st.executeQuery();
					if (rs.next())
					{
						x[0]=rs.getString(1);
						x[1]=rs.getString(2);
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

