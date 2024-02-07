package com.VisitorsManagement;

import com.util.data.*;
import javax.swing.*;



import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.*;
import java.sql.*;
public class Holiday_update extends JFrame implements ActionListener,ItemListener{
	JLabel holiday_id,holiday_name,date_of_holiday;
	JTextField holiday_namef,date_of_holidayf;
	JComboBox holiday_idcb;
	JButton find,clear,close,update;
	
	Connection con;PreparedStatement st;
	ResultSet rs;
	public Holiday_update()
	{
		setSize(1400,800);
		setTitle("Holiday Update");
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
	    
	    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("update.png"));
	    Image i2 = i1.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
	    ImageIcon i3 = new ImageIcon(i2);
	    JLabel image1 = new JLabel("Holiday Update",i3,SwingConstants.CENTER);
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
		holiday_idcb=new JComboBox();
		holiday_idcb.setBounds(650,170,250,30);
		holiday_idcb.insertItemAt("", 0);
		add(holiday_idcb);
		image.add(holiday_idcb);
		filldata();
		holiday_idcb.addItemListener(this);
				
		holiday_name=new JLabel("HOLIDAY NAME:");
		holiday_name.setBounds(450,220,350,30);
		holiday_name.setFont(new Font("Arial",Font.BOLD,16));
		add(holiday_name);
		image.add(holiday_name);
		holiday_namef=new JTextField();
		holiday_namef.setBounds(650,220,250,30);
		add(holiday_namef);
		image.add(holiday_namef);
		
		date_of_holiday=new JLabel("HOLIDAY DATE:");
		date_of_holiday.setBounds(450,270,250,30);
		date_of_holiday.setFont(new Font("Arial",Font.BOLD,16));
		add(date_of_holiday);
		image.add(date_of_holiday);
		date_of_holidayf=new JTextField();
		date_of_holidayf.setBounds(650,270,250,30);
		add(date_of_holidayf);
		image.add(date_of_holidayf);	
		
		update = new JButton("UPDATE");
		update.setBounds(600,420,150,30);
		update.setFont(new Font("Arial",Font.BOLD,16));
		update.setBackground(new Color(173,216,230));
		add(update);
		image.add(update);
		update.addActionListener(this);
		
		clear = new JButton("CLEAR");
		clear.setBounds(800,420,150,30);
		clear.setFont(new Font("Arial",Font.BOLD,16));
		clear.setBackground(new Color(173,216,230));
		add(clear);
		image.add(clear);
		clear.addActionListener(this);
		
		close=new JButton("CLOSE");
	    close.setBounds(1000, 420,150, 30);
	    close.setFont(new Font("Arial",Font.BOLD,16));
	    close.setBackground(new Color(173,216,230));
		add(close);
		image.add(close);
		close.addActionListener(this);
		
	    find=new JButton("FIND");
	    find.setBounds(400,420,150,30);
	    find.setFont(new Font("Arial",Font.BOLD,16));
	    find.setBackground(new Color(173,216,230));
		add(find);
		image.add(find);
		find.addActionListener(this);
	
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
 }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Holiday_update();
		
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		
		
		if(ae.getSource()==update)
		{
			if(holiday_namef.getText().length()==0||date_of_holidayf.getText().length()==0)
			{
				JOptionPane.showMessageDialog(this, "Check all the entries First");
			}
			else
			{
				int opt=JOptionPane.showConfirmDialog(null,"Are you sure to Update","UPDATE",JOptionPane.YES_NO_OPTION);
				if(opt==0)
				{
				String holi_id=holiday_idcb.getSelectedItem().toString();
				String holi_name=holiday_namef.getText();
				String dateholi=date_of_holidayf.getText();
				Holiday_DataUpdate obj =new Holiday_DataUpdate();
				String msg=obj.UpdateData(holi_name,dateholi,holi_id);
				JOptionPane.showMessageDialog(this, msg);
				holiday_namef.setText("");
				date_of_holidayf.setText("");
				}
			}
		}

		
		
		if(ae.getSource()==clear)
		{
		
			
			holiday_namef.setText("");
			date_of_holidayf.setText("");
			
		}
		
		if(ae.getSource()==close)
		{
			this.dispose();
		}
	}
		
		void filldata()
		{
			try {
				//Step-1 Load the driver
				Class.forName("oracle.jdbc.driver.OracleDriver");
				//Step-2 Connection create
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
				//Step-3 Statement create
				String sql="select HolidayId from HolidayMaster";
				st=con.prepareStatement(sql);
				rs=st.executeQuery();
				while (rs.next())
				{
					holiday_idcb.addItem(rs.getString(1));
				}
				}
			catch(Exception ex) {}
		
		
	}
		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource()==holiday_idcb)
			{
			
				String k=holiday_idcb.getSelectedItem().toString();
				Holiday_DataFind obj=new Holiday_DataFind();
				String s[]=obj.findData(k);
				if(s!= null)
				{
					holiday_namef.setText(s[0]);
					date_of_holidayf.setText(s[1]);
				}
				else
				{
					JOptionPane.showMessageDialog(this, "Record Not Found");
				}
			}
			
		}

}