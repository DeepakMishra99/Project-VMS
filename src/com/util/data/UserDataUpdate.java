package com.util.data;
import java.sql.*;
import javax.swing.*;

public class UserDataUpdate{
		Connection con;PreparedStatement st;ResultSet rs;
		public String updateData(String a,String b,String c,String d,String e,String f,String h)
		{
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
				String sql="update usermaster set username=?,useremail=?,userphone=?,userAddress=?,usergender=?,userpassword=?,status=? where userid=?";
				st=con.prepareStatement(sql);
				st.setString(1, a);
				st.setString(2, b);
				st.setString(3,c);
				st.setString(4,d);
				st.setString(5,e);
				st.setString(6,f);
				st.setInt(7,1);
				st.setString(8,h);
				int as=st.executeUpdate();
				con.close();
				return "Record Updated";
			}
			catch(Exception ex) {
				return ex.toString();
			}


	}

}
