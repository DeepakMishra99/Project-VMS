package com.VisitorsManagement;

import javax.swing.*;
import com.util.data.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

public class TypeMaster_insert extends JFrame implements ActionListener,KeyListener{
	JLabel lblT_type,lblT_name,lbl;
	JTextField txt_type,txt_name; 
	JButton T_insert,T_Clear,T_Close;
	Connection con;PreparedStatement st;
	JProgressBar jp;
	int val=0;
	TypeMaster_insert()
	{
		setSize(1400,800);
		setTitle("TYPE MASTER INSERT");
		setLayout(null);
		setLocationRelativeTo(null); 
		setResizable(false);
		
		ImageIcon bg1 = new ImageIcon(ClassLoader.getSystemResource("all_bg.jpg"));
	    Image bg2= bg1.getImage().getScaledInstance(1400, 800, Image.SCALE_DEFAULT);
	    ImageIcon bg3 = new ImageIcon(bg2);
	    JLabel image = new JLabel(bg3);
	    image.setBounds(0,0, 1400, 800);
	    add(image);
	    
	    ImageIcon logo1 = new ImageIcon(ClassLoader.getSystemResource("type.png"));
	    Image logo2= logo1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
	    ImageIcon logo3 = new ImageIcon(logo2);
	    JLabel image_logo = new JLabel("TYPE MASTER",logo3,SwingConstants.CENTER);
	    image_logo.setVerticalTextPosition(JLabel.BOTTOM);
	    image_logo.setHorizontalTextPosition(JLabel.CENTER);
	    image_logo.setFont(new Font("Arial",Font.BOLD,16));
	    image_logo.setBounds(10,10, 200, 150);
	    add(image_logo);
	    image.add(image_logo);
	    
	    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("insert.png"));
	    Image i2 = i1.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
	    ImageIcon i3 = new ImageIcon(i2);
	    JLabel image1 = new JLabel("Type Master Insert",i3,SwingConstants.CENTER);
	    image1.setVerticalTextPosition(JLabel.CENTER);
	    image1.setHorizontalTextPosition(JLabel.RIGHT);
	    image1.setForeground(new Color(0,0,128));
	    image1.setFont(new Font("Arial",Font.BOLD,30));
	    image1.setBounds(550,0, 400, 150);
	    add(image1);
	    image.add(image1);
		
		lblT_type=new JLabel("ID TYPE");
		lblT_type.setBounds(450,150,150,40);
		lblT_type.setFont(new Font("Arial",Font.BOLD,16));
		add(lblT_type);
		image.add(lblT_type);
		txt_type=new JTextField();
		txt_type.addKeyListener(this);
		txt_type.setBounds(600,150,250,40);
		add(txt_type);
		image.add(txt_type);
		
		lblT_name=new JLabel("ID NAME");
		lblT_name.setBounds(450,220,150,40);
		lblT_name.setFont(new Font("Arial",Font.BOLD,16));
		add(lblT_name);
		image.add(lblT_name);
		txt_name=new JTextField();
		txt_name.addKeyListener(this);
		txt_name.setBounds(600,220,250,40);
		add(txt_name);
		image.add(txt_name);
		
		T_insert=new JButton("SAVE");
		T_insert.setBounds(400,320,150,30);
		add(T_insert);
		T_insert.setBackground(new Color(173,216,230));
		image.add(T_insert);
		
		T_Clear=new JButton("CLEAR");
		T_Clear.setBounds(600,320,150,30);
		T_Clear.setBackground(new Color(173,216,230));
		add(T_Clear);
		image.add(T_Clear);
		
		T_Close=new JButton("CLOSE");
		T_Close.setBounds(800,320,150,30);
		add(T_Close);
		T_Close.setBackground(new Color(173,216,230));
		image.add(T_Close);
		
		jp=new JProgressBar();
		jp.setMinimum(0);
		jp.setMaximum(100);
		jp.setValue(val);
		jp.setBounds(420,385,600,20);
		add(jp);
		jp.setStringPainted(true);

		T_insert.addActionListener(this);
		T_Clear.addActionListener(this);
		T_Close.addActionListener(this);
		
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new TypeMaster_insert();

	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		if(ae.getSource()==(T_insert))
		{
			if (txt_type.getText().length()==0 ||txt_name.getText().length()==0)
			{
				JOptionPane.showMessageDialog(this,"Check all data first..");
			}
			else
			{
				JOptionPane.showMessageDialog(this,"Are you Sure");
				try {
					//Step-1 Load the driver
					Class.forName("oracle.jdbc.driver.OracleDriver");
					//Step-2 Connection create
					con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
					//Step-3 Statement create
					String sql="insert into typemaster values(?,?)";
					String ttype=txt_type.getText();
					String tname=txt_name.getText();
					st=con.prepareStatement(sql);
					st.setString(1,ttype);
					st.setString(2,tname);
					int ws=st.executeUpdate();
					JOptionPane.showMessageDialog(this, "Record Saved");
					con.close();
					txt_type.setText("");
					txt_name.setText("");
				}
				catch(Exception ex) {
					System.out.println(ex.toString());
				}
		}
		}
		if(ae.getSource()==(T_Clear))
		{
			txt_type.setText("");
			txt_name.setText("");
			val=val+0;
			jp.setValue(val);
		}
		if(ae.getSource()==(T_Close))
		{
			this.dispose();
		}
		

}

	@Override
	public void keyPressed(KeyEvent ar) {
		// TODO Auto-generated method stub
		if(ar.getSource()==txt_type && txt_type.getText().length()>0)
		{
			if(ar.getKeyCode()==ar.VK_ENTER)
			{
				txt_name.requestFocus();
				val=val+50;
				jp.setValue(val);

			}
		}
		if(ar.getSource()==txt_name && txt_name.getText().length()>0)
		{
			if(ar.getKeyCode()==ar.VK_ENTER)
			{
				txt_name.requestFocus();
				val=val+50;
				jp.setValue(val);

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
