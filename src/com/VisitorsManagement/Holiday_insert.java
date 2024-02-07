package com.VisitorsManagement;

import javax.swing.*;


import com.util.data.*;


import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class Holiday_insert extends JFrame implements ActionListener,KeyListener{
	JLabel holiday_id,holiday_name,date_of_holiday;
	JTextField holiday_idf,holiday_namef,date_of_holidayf;
	JButton save,clear,close,newid;
	Connection con;PreparedStatement st;
	JProgressBar jp;
	int val=0;
	
	public Holiday_insert() 
	{
	
		setSize(1400,800);
		setTitle("Holiday Insert");
		setLayout(null);
		setLocationRelativeTo(null); 
		setResizable(false);
		
		ImageIcon bg1 = new ImageIcon(ClassLoader.getSystemResource("all_bg.jpg"));
	    Image bg2= bg1.getImage().getScaledInstance(1400, 800, Image.SCALE_DEFAULT);
	    ImageIcon bg3 = new ImageIcon(bg2);
	    JLabel image = new JLabel(bg3);
	    image.setBounds(0,0, 1400, 800);
	    add(image);
	    
	    ImageIcon logo1 = new ImageIcon(ClassLoader.getSystemResource("holiday.png"));
	    Image logo2= logo1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
	    ImageIcon logo3 = new ImageIcon(logo2);
	    JLabel image_logo = new JLabel("HOLIDAY",logo3,SwingConstants.CENTER);
	    image_logo.setVerticalTextPosition(JLabel.BOTTOM);
	    image_logo.setHorizontalTextPosition(JLabel.CENTER);
	    image_logo.setFont(new Font("Arial",Font.BOLD,16));
	    image_logo.setBounds(10,10,200, 150);
	    add(image_logo);
	    image.add(image_logo);
	    
	    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("insert.png"));
	    Image i2 = i1.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
	    ImageIcon i3 = new ImageIcon(i2);
	    JLabel image1 = new JLabel("Holiday Insert",i3,SwingConstants.CENTER);
	    image1.setVerticalTextPosition(JLabel.CENTER);
	    image1.setHorizontalTextPosition(JLabel.RIGHT);
	    image1.setForeground(new Color(0,0,128));
	    image1.setFont(new Font("Arial",Font.BOLD,30));
	    image1.setBounds(550,0, 400, 150);
	    add(image1);
	    image.add(image1);
		
		holiday_id=new JLabel("HOLIDAY ID:");
		holiday_id.setBounds(450,170,150,30);
		holiday_id.setFont(new Font("Arial",Font.BOLD,16));
		add(holiday_id);
		image.add(holiday_id);
		holiday_idf=new JTextField();
		holiday_idf.setBounds(650,170,250,30);
		holiday_idf.setFont(new Font("Arial",Font.BOLD,16));
		holiday_idf.setText(getid());
		holiday_idf.setEditable(false);
		add(holiday_idf);
		image.add(holiday_idf);
		holiday_idf.addKeyListener(this);
		
		holiday_name=new JLabel("HOLIDAY NAME:");
		holiday_name.setBounds(450,220,350,30);
		holiday_name.setFont(new Font("Arial",Font.BOLD,16));
		add(holiday_name);
		image.add(holiday_name);
		holiday_namef=new JTextField();
		holiday_namef.setBounds(650,220,250,30);
		add(holiday_namef);
		image.add(holiday_namef);
		holiday_namef.addKeyListener(this);
		
		date_of_holiday=new JLabel("HOLIDAY DATE:");
		date_of_holiday.setBounds(450,270,250,30);
		date_of_holiday.setFont(new Font("Arial",Font.BOLD,16));
		add(date_of_holiday);
		image.add(date_of_holiday);
		date_of_holidayf=new JTextField();
		date_of_holidayf.setBounds(650,270,250,30);
		add(date_of_holidayf);
		image.add(date_of_holidayf);
		date_of_holidayf.addKeyListener(this);
	
		jp=new JProgressBar();
		jp.setMinimum(0);
		jp.setMaximum(100);
		jp.setValue(val);
		jp.setBounds(320,400,800,20);
		jp.setFont(new Font("Arial",Font.BOLD,16));
		add(jp);
		jp.setStringPainted(true);
		image.add(jp);
		
		save = new JButton("INSERT");
		save.setBounds(400,350,150,30);
		save.setFont(new Font("Arial",Font.BOLD,16));
		save.setBackground(new Color(173,216,230));
		add(save);
		image.add(save);
		save.addActionListener(this);
		
		clear = new JButton("CLEAR");
		clear.setBounds(600,350,150,30);
		clear.setFont(new Font("Arial",Font.BOLD,16));
		clear.setBackground(new Color(173,216,230));
		add(clear);
		image.add(clear);
		clear.addActionListener(this);
		
		close=new JButton("CLOSE");
	    close.setBounds(800,350, 150, 30);
	    close.setFont(new Font("Arial",Font.BOLD,16));
	    close.setBackground(new Color(173,216,230));
		add(close);
		image.add(close);
		close.addActionListener(this);
		
		
		
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
 }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Holiday_insert();
		
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		if(ae.getSource()==save)
		{
			//holiday_idf,holiday_namef,date_of_holidayf;
			if(holiday_idf.getText().length()==0||holiday_namef.getText().length()==0||date_of_holidayf.getText().length()==0)
			{
				JOptionPane.showMessageDialog(this, "Check all the entries First");
			}
			else
			{
				String holi_id=holiday_idf.getText();
				String holi_name=holiday_namef.getText();
				String dateholi=date_of_holidayf.getText();
				Holiday_DataInsert obj=new Holiday_DataInsert();
				String msg=obj.insertData(holi_id,holi_name,dateholi);
				JOptionPane.showMessageDialog(this, msg);
				holiday_idf.setText("");
				holiday_namef.setText("");
				date_of_holidayf.setText("");
			}
	
		}
		if(ae.getSource()==close)
		{
			this.dispose();
		}
		
		if(ae.getSource()==clear)
		{
		
			holiday_idf.setText("");
			holiday_namef.setText("");
			date_of_holidayf.setText("");
			val=0;
			jp.setValue(val);
			
		}
	}
		
	
		String getid()
		{
			String id;
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
				String sql="select  holidayid from holidaymaster";
				Statement st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
				ResultSet rs=st.executeQuery(sql);
				if (rs.next())
				{
					rs.last();
					String g=rs.getString(1);
					String w=g.substring(4,g.length());
					int x=Integer.parseInt(w);
					if (x<10)
						id="HOL"+"000"+(x+1);
					else if(x>=10 && x<99)
						id="HOL"+"00"+(x+1);
					else if(x>=100 && x<999)
						id="HOL"+"0"+(x+1);
					else
						id="HOL"+(x+1);
				}
				else {
					
					id="HOL0001";
				}
				return id;
			}
			catch (Exception ex) {
				return ex.toString();
			}
	
		
	}
		@Override
		public void keyPressed(KeyEvent ar) {
			// TODO Auto-generated method stub
			if(ar.getSource()==holiday_idf && holiday_idf.getText().length()>0)

			{
				if(ar.getKeyCode()==ar.VK_ENTER)
				{
					holiday_namef .requestFocus();
					val=val+34;
					jp.setValue(val);

				}
			}
			if(ar.getSource()==holiday_namef && holiday_namef.getText().length()>0)
			{
				if(ar.getKeyCode()==ar.VK_ENTER)
				{
					date_of_holidayf.requestFocus();
					val=val+34;
					jp.setValue(val);

				}
			}
			if(ar.getSource()==date_of_holidayf && date_of_holidayf.getText().length()>0)
			{
				if(ar.getKeyCode()==ar.VK_ENTER)
				{
					date_of_holidayf.requestFocus();
					val=val+32;
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