
package com.VisitorsManagement;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;

public class Admin_Login extends JFrame implements ActionListener,KeyListener {
	JLabel admin_id,admin_password,login,login1,sign;
	JComboBox id_value;
	JPasswordField password_value;
	JButton loginb,newsign,forgot,back;
	Connection con;PreparedStatement st;ResultSet rs;
public Admin_Login() {
		
		setSize(1400,800);
		setTitle("ADMIN LOGIN");
		setLayout(null);
		setLocationRelativeTo(null); 
		setResizable(false);
		
		
		ImageIcon bg1 = new ImageIcon(ClassLoader.getSystemResource("login.jpg"));
	    Image bg2= bg1.getImage().getScaledInstance(1400, 800, Image.SCALE_DEFAULT);
	    ImageIcon bg3 = new ImageIcon(bg2);
	    JLabel image = new JLabel(bg3);
	    image.setBounds(0,0, 1400, 800);
	    add(image);
	    
	    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("welcome_admin.png"));
	    Image i2 = i1.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT);
	    ImageIcon i3 = new ImageIcon(i2);
	    JLabel image1 = new JLabel("ADMIN LOGIN",i3,SwingConstants.CENTER);
	    image1.setVerticalTextPosition(JLabel.BOTTOM);
	    image1.setHorizontalTextPosition(JLabel.CENTER);
	    image1.setFont(new Font("Arial",Font.BOLD,16));
	    image1.setBounds(620,30, 150, 200);
	    add(image1);
	    image.add(image1);
	    
	    JPanel con = new JPanel(); 
	    con.setBackground(Color.WHITE);
	    con.setBounds(400,100,600, 500);
	    con.setLayout(null);
	    add(con);
	    image.add(con);
	   	
		admin_id=new JLabel("Admin Id:");
		admin_id.setBounds(100,150,100,30);
		admin_id.setFont(new Font("Arial",Font.BOLD,16));
		admin_id.setForeground(Color.BLACK);
		con.add(admin_id);
		id_value=new JComboBox();
		id_value.setBounds(100,190,350,30);
		con.add(id_value);
		filldata();
		id_value.addKeyListener(this);
		
		
		admin_password=new JLabel("Password:");
		admin_password.setBounds(100,240,100,30);
		admin_password.setFont(new Font("Arial",Font.BOLD,16));
		admin_password.setForeground(Color.BLACK);
		con.add(admin_password);
		password_value=new JPasswordField();
		password_value.setBounds(100,280,350,30);
		con.add(password_value);
		password_value.addKeyListener(this);
		
		forgot=new JButton("Forgot Password?");
		forgot.setBounds(60,310,200,30);
		forgot.setFont(new Font("Arial",Font.BOLD,14));
		forgot.setContentAreaFilled(false);
	    forgot.setFocusPainted(false);
	    forgot.setBorderPainted(false);
	    forgot.setForeground(Color.BLUE);
		con.add(forgot);
		forgot.addActionListener(this);
		
		loginb=new JButton("Login");
	    loginb.setBounds(200,350,200,30);
	    loginb.setFont(new Font("Arial",Font.BOLD,16));
	    loginb.setBackground(Color.BLACK);
		loginb.setForeground(Color.WHITE);
		con.add(loginb);
		loginb.addActionListener(this);
		loginb.addKeyListener(this);
		
		newsign=new JButton("Create new account!");
	    newsign.setBounds(50,400,250,30);
	    newsign.setFont(new Font("Arial",Font.BOLD,16));
	    newsign.setContentAreaFilled(false);
	    newsign.setFocusPainted(false);
	    newsign.setBorderPainted(false);
		newsign.setForeground(Color.RED);
		con.add(newsign);
		newsign.addActionListener(this);
		
		back=new JButton("<< Back");
		back.setBounds(400,400,100,30);
		back.setFont(new Font("Arial",Font.BOLD,16));
		back.setContentAreaFilled(false);
		back.setFocusPainted(false);
		back.setBorderPainted(false);
		back.setForeground(new Color(4,118,208));
		con.add(back);
		back.addActionListener(this);
		
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Admin_Login();

	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		if(ae.getSource()==(loginb))
		{
			try{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");

				String aid=id_value.getSelectedItem().toString();
				String pas=password_value.getText();
				Statement st=con.createStatement();
				
				String sql="select * from adminmaster where adminid='"+aid+"'and adminpassword='"+pas+"'";
				ResultSet rs=st.executeQuery(sql);
				
				if(id_value.getSelectedItem().toString().length()==0||password_value.getText().length()==0)
				{
					JOptionPane.showMessageDialog(this, "Fill all the entries First");
				}
				if (rs.next())
				{
					JOptionPane.showMessageDialog(null, "Login successfully");
				    
				    Admin_screen d=new Admin_screen();
				    String z=rs.getString("adminname");
				    char y=z.charAt(0);
				    d.id.setText(id_value.getSelectedItem().toString());
				    d.label.setText(Character.toString(y));
				    d.name.setText(rs.getString("adminname"));
				    d.email.setText(rs.getString("adminemail"));
				    d.phone.setText("+91 "+rs.getString("adminphone"));
				    d.gender.setText(rs.getString("admingender"));
				    
				    if(rs.getString("status").equals("1"))
				    {
				    	d.status.setText("Active");
				    }
				    else
				    {
				    	d.status.setText("Inactive");
				    }
				    d.setVisible(true);
				    
					
				}
				else
					JOptionPane.showMessageDialog(null, "Login Failed");
				con.close();
			}
			catch(Exception ex) {
			}
			
			
		}
		if(ae.getSource()==(newsign))
		{
			new Admin_insert();
		}
		if(ae.getSource()==(forgot))
		{
			new Admin_update();
		}
		if(ae.getSource()==(back))
		{
			this.dispose();
		}
	}
		
	void filldata()
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			String sql="select AdminId from AdminMaster";
			st=con.prepareStatement(sql);
			rs=st.executeQuery();
			while(rs.next())
			{
				id_value.addItem(rs.getString(1));
			}
			
		}
		catch(Exception ex) {}
	
	}

	@Override
	public void keyPressed(KeyEvent ar) {
		// TODO Auto-generated method stub
		
		if(ar.getSource()==id_value)
		{
			if(ar.getKeyCode()==ar.VK_ENTER)
			{
				password_value.requestFocus();
			}
		}
		
		if(ar.getSource()==password_value)
		{
			if(ar.getKeyCode()==ar.VK_ENTER)
			{
				try{
					Class.forName("oracle.jdbc.driver.OracleDriver");
					con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");

					String aid=id_value.getSelectedItem().toString();
					String pas=password_value.getText();
					Statement st=con.createStatement();
					
					String sql="select * from adminmaster where adminid='"+aid+"'and adminpassword='"+pas+"'";
					ResultSet rs=st.executeQuery(sql);
					
					if(id_value.getSelectedItem().toString().length()==0||password_value.getText().length()==0)
					{
						JOptionPane.showMessageDialog(this, "Fill all the entries First");
					}
					if (rs.next())
					{
						JOptionPane.showMessageDialog(null, "Login successfully");
					    
					    Admin_screen d=new Admin_screen();
					    String z=rs.getString("adminname");
					    char y=z.charAt(0);
					    d.id.setText(id_value.getSelectedItem().toString());
					    d.label.setText(Character.toString(y));
					    d.name.setText(rs.getString("adminname"));
					    d.email.setText(rs.getString("adminemail"));
					    d.phone.setText("+91 "+rs.getString("adminphone"));
					    d.gender.setText(rs.getString("admingender"));
					    
					    if(rs.getString("status").equals("1"))
					    {
					    	d.status.setText("Active");
					    }
					    else
					    {
					    	d.status.setText("Inactive");
					    }
					    d.setVisible(true);
					    
						
					}
					else
						JOptionPane.showMessageDialog(null, "Login Failed");
					con.close();
				}
				catch(Exception ex) {
				}
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}

