package com.util.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CompanyMaster_findHandle {
	Connection con;PreparedStatement st;ResultSet rs;
	public String[] findData(String a)
	{
		String x[]=new String[4];
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
				String sql="select companyname,companyemail,companyphone,companygst from companymaster where companyid=?";
				st=con.prepareStatement(sql);
				st.setString(1, a);
				rs=st.executeQuery();
				if (rs.next())
				{
					x[0]=rs.getString(1);
					x[1]=rs.getString(2);
					x[2]=rs.getString(3);
					x[3]=rs.getString(4);
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
