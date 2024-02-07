package com.VisitorsManagement;
import java.awt.Color;
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

public class User_Login extends JFrame implements ActionListener,KeyListener {
	JLabel user_id,user_password,login,login1,sign,a,b;
	JComboBox user_idf;
	JPasswordField user_passwordf;
	JButton loginb,newsign,forgot,back;
	Connection con;PreparedStatement st;ResultSet rs;
public User_Login() {
		
	setSize(1400,800);
	setTitle("USER LOGIN");
	setLayout(null);
	setLocationRelativeTo(null); 
	setResizable(false);
	
	
	
	ImageIcon bg1 = new ImageIcon(ClassLoader.getSystemResource("login.jpg"));
    Image bg2= bg1.getImage().getScaledInstance(1400, 800, Image.SCALE_DEFAULT);
    ImageIcon bg3 = new ImageIcon(bg2);
    JLabel image = new JLabel(bg3);
    image.setBounds(0,0, 1400, 800);
    add(image);
    
    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("welcome_user.png"));
    Image i2 = i1.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT);
    ImageIcon i3 = new ImageIcon(i2);
    JLabel image1 = new JLabel("USER LOGIN",i3,SwingConstants.CENTER);
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
   	
	user_id=new JLabel("User Id:");
	user_id.setBounds(100,150,100,30);
	user_id.setFont(new Font("Arial",Font.BOLD,16));
	user_id.setForeground(Color.BLACK);
	con.add(user_id);
	user_idf=new JComboBox();
	user_idf.setBounds(100,190,350,30);
	con.add(user_idf);
	filldata();
	user_idf.addKeyListener(this);
	
	
	user_password=new JLabel("Password:");
	user_password.setBounds(100,240,100,30);
	user_password.setFont(new Font("Arial",Font.BOLD,16));
	user_password.setForeground(Color.BLACK);
	con.add(user_password);
	user_passwordf=new JPasswordField();
	user_passwordf.setBounds(100,280,350,30);
	con.add(user_passwordf);
	user_passwordf.addKeyListener(this);
	
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
	back.setBounds(350,400,100,30);
	back.setFont(new Font("Arial",Font.BOLD,16));
	back.setContentAreaFilled(false);
	back.setFocusPainted(false);
	back.setBorderPainted(false);
	back.setForeground(new Color(4,118,208));
	con.add(back);
	back.addActionListener(this);
	
	setVisible(true);
	setDefaultCloseOperation(EXIT_ON_CLOSE);
}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new User_Login();

	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		if(ae.getSource()==(loginb))
		{
			try{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");

				String uid=user_idf.getSelectedItem().toString();
				String pas=user_passwordf.getText();
				Statement st=con.createStatement();
				String sql="select * from usermaster where userid='"+uid+"'and userpassword='"+pas+"'";
				
				ResultSet rs=st.executeQuery(sql);
				if(user_idf.getSelectedItem().toString().length()==0||user_passwordf.getText().length()==0)
				{
					JOptionPane.showMessageDialog(this, "Fill all the entries First");
				}
				
				if (rs.next())
				{
					JOptionPane.showMessageDialog(null, "Login successfully");
					 User_screen d=new User_screen();
					    String z=rs.getString("username");
					    char y=z.charAt(0);
					    d.id.setText(user_idf.getSelectedItem().toString());
					    d.label.setText(Character.toString(y));
					    d.name.setText(rs.getString("username"));
					    d.email.setText(rs.getString("useremail"));
					    d.phone.setText("+91 "+rs.getString("userphone"));
					    d.gender.setText(rs.getString("usergender"));
					    
					    if(rs.getString("status").equals("1"))
					    {
					    	d.status.setText("Active");
					    }
					    else
					    {
					    	d.status.setText("Inactive");
					    }
					
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
			new User_Insert();
		}
		if(ae.getSource()==(forgot))
		{
			new User_Update();
		}
		if(ae.getSource()==(back))
		{
			new welcome();
		}
	}

	void filldata()
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			String sql="select userid from usermaster";
			st=con.prepareStatement(sql);
			rs=st.executeQuery();
			while(rs.next())
			{
				user_idf.addItem(rs.getString(1));
			}
			
		}
		catch(Exception ex) {
			
		}
	}
	

	@Override
	public void keyPressed(KeyEvent ar) {
		// TODO Auto-generated method stub
		if(ar.getSource()==user_idf)
		{
			if(ar.getKeyCode()==ar.VK_ENTER)
			{
				user_passwordf.requestFocus();
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}

