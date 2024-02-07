package com.util.data;
import java.sql.*;
import javax.swing.*;

public class UserDataDelete{
		Connection con;PreparedStatement st;ResultSet rs;
		public String deleteData(String a,String b,String c,String d,String e,String f,String h)
		{
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
				String sql="delete from usermaster where userid=?";
				st=con.prepareStatement(sql);
				st.setString(1, a);
				int as=st.executeUpdate();
				con.close();
				return "Record Deleted";
			}
			catch(Exception ex) {
				return ex.toString();
			}


	}

}
