package com.util.data;

import java.sql.*;
import javax.swing.*;

public class admin_util_ins{
		Connection con;PreparedStatement st;ResultSet rs;
		public String insertData(String a,String b,String c,String d,String e,String f,String g)
		{
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
				String sql="insert into adminmaster values(?,?,?,?,?,?,?,?)";
				st=con.prepareStatement(sql);
				st.setString(1, a);
				st.setString(2, b);
				st.setString(3,c);
				st.setString(4,d);
				st.setString(5,e);
				st.setString(6,f);
				st.setString(7,g);
				st.setInt(8,1);
				int as=st.executeUpdate();
				con.close();
				return "Record Saved";
			}
			catch(Exception ex) {
				return ex.toString();
			}


	}

}
