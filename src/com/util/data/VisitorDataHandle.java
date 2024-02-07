package com.util.data;
import java.sql.*;
import javax.swing.JOptionPane;
public class VisitorDataHandle {
	Connection con;PreparedStatement st;ResultSet rs;
	public String insertData(String a,String b,String c,String d,String e,String f,String g,String h)
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			String sql="insert into Visitors values(?,?,?,?,?,?,?,?,?)";
			st=con.prepareStatement(sql);
			st.setString(1, a);
			st.setString(2, b);
			st.setString(4,c);
			st.setString(3,d);
			st.setString(5,e);
			st.setString(6, f);
			st.setString(7,g);
			st.setString(8,h);
			st.setInt(9, 1);
			int as=st.executeUpdate();
			con.close();
			return "Record Saved";
		}
		catch(Exception ex) {
			return ex.toString();
		}
}
	
}
