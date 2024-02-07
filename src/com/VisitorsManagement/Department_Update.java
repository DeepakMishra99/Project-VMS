package com.VisitorsManagement;

import com.util.data.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Department_Update extends JFrame implements ActionListener,KeyListener,ItemListener {
	JLabel department,department_id,department_name,description;
	JTextField department_namef,descriptionf;
	JButton ju,jc,jf,jco;
	JComboBox cb;
	
	Connection con;PreparedStatement st;ResultSet rs;
	public Department_Update(){
		setSize(1400,800);
		setTitle("Department Update");
		setLayout(null);
		setLocationRelativeTo(null); 
		setResizable(false);
		
		ImageIcon bg1 = new ImageIcon(ClassLoader.getSystemResource("all_bg.jpg"));
	    Image bg2= bg1.getImage().getScaledInstance(1400, 800, Image.SCALE_DEFAULT);
	    ImageIcon bg3 = new ImageIcon(bg2);
	    JLabel image = new JLabel(bg3);
	    image.setBounds(0,0, 1400, 800);
	    add(image);
	    
	    ImageIcon logo1 = new ImageIcon(ClassLoader.getSystemResource("department.png"));
	    Image logo2= logo1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
	    ImageIcon logo3 = new ImageIcon(logo2);
	    JLabel image_logo = new JLabel("DEPARTMENT",logo3,SwingConstants.CENTER);
	    image_logo.setVerticalTextPosition(JLabel.BOTTOM);
	    image_logo.setHorizontalTextPosition(JLabel.CENTER);
	    image_logo.setFont(new Font("Arial",Font.BOLD,16));
	    image_logo.setBounds(10,10,200, 150);
	    add(image_logo);
	    image.add(image_logo);
	    
	    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("update.png"));
	    Image i2 = i1.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
	    ImageIcon i3 = new ImageIcon(i2);
	    JLabel image1 = new JLabel("Department Update",i3,SwingConstants.CENTER);
	    image1.setVerticalTextPosition(JLabel.CENTER);
	    image1.setHorizontalTextPosition(JLabel.RIGHT);
	    image1.setFont(new Font("Arial",Font.BOLD,30));
	    image1.setForeground(new Color(0,0,128));
	    image1.setBounds(550,0, 400, 150);
	    add(image1);
	    image.add(image1);
		
		department_id=new JLabel("DEPARTMENT ID:");
		department_id.setBounds(400,170,150,30);
		department_id.setFont(new Font("Arial",Font.BOLD,16));
		add(department_id);
		image.add(department_id);
		
		cb=new JComboBox();
		cb.setBounds(650,170,250,30);
		cb.insertItemAt("", 0);
		add(cb);
		image.add(cb);
		filldata();
		cb.addItemListener(this);
	
		department_name=new JLabel("DEPARTMENT NAME:");
		department_name.setBounds(400,220,350,30);
		department_name.setFont(new Font("Arial",Font.BOLD,16));
		add(department_name);
		image.add(department_name);
		department_namef=new JTextField();
		department_namef.setBounds(650,220,250,30);
		add(department_namef);
		image.add(department_namef);
		
		description=new JLabel("DESCRIPTION:");
		description.setBounds(400,270,150,30);
		description.setFont(new Font("Arial",Font.BOLD,16));
		add(description);
		image.add(description);
		descriptionf=new JTextField();
		descriptionf.setBounds(650,270,250,30);
		add(descriptionf);
		image.add(descriptionf);
		
		ju = new JButton("UPDATE");
		ju.setBounds(400,360,150,30);
		ju.setFont(new Font("Arial",Font.BOLD,16));
		ju.setBackground(new Color(173,216,230));
		add(ju);
		image.add(ju);
		ju.addActionListener(this);
		
		jc = new JButton("CLEAR");
		jc.setBounds(600,360,150,30);
		jc.setFont(new Font("Arial",Font.BOLD,16));
		jc.setBackground(new Color(173,216,230));
		add(jc);
		image.add(jc);
		jc.addActionListener(this);
		
		jco=new JButton("CLOSE");
	    jco.setBounds(800, 360,150, 30);
	    jco.setFont(new Font("Arial",Font.BOLD,16));
	    jco.setBackground(new Color(173,216,230));
		add(jco);
		image.add(jco);
		jco.addActionListener(this);
		

		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Department_Update();

	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		
		if(ae.getSource()==ju)
		{
			if(department_namef.getText().length()==0||descriptionf.getText().length()==0)
			{
				JOptionPane.showMessageDialog(this, "Check all the entries First");
			}
			else
			{
				int opt=JOptionPane.showConfirmDialog(null,"Are you sure to Update","UPDATE",JOptionPane.YES_NO_OPTION);
				if(opt==0)
				{
				String dep_id=cb.getSelectedItem().toString();
				String dep_name=department_namef.getText();
				String des=descriptionf.getText();
				DepartmentsDataUpdate obj =new DepartmentsDataUpdate();
				String msg=obj.UpdateData(dep_name,des,dep_id);
				JOptionPane.showMessageDialog(this, msg);
				department_namef.setText("");
				descriptionf.setText("");
				}
			}
		}
		if(ae.getSource()==jc)
		{
		
			
			department_namef.setText("");
			descriptionf.setText("");
			
		}
		if(ae.getSource()==jco)
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
			String sql="select DepartmentId from Departments";
			st=con.prepareStatement(sql);
			rs=st.executeQuery();
			while (rs.next())
			{
				cb.addItem(rs.getString(1));
			}
			}
		catch(Exception ex) {}
	}
	@Override
	public void keyPressed(KeyEvent ar) {
		// TODO Auto-generated method stub
		if(ar.getSource()==department_namef)
		{
			if(ar.getKeyCode()==ar.VK_ENTER)
			{
				descriptionf.requestFocus();
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
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==cb)
		{
		
			String k=cb.getSelectedItem().toString();
			DepartmentsDataFind obj=new DepartmentsDataFind();
			String s[]=obj.findData(k);
			if(s!=null)
			{
				department_namef.setText(s[0]);
				descriptionf.setText(s[1]);
			}
			else
			{
				
				
				JOptionPane.showMessageDialog(this, "Record Not Found");
			}
		}
		
	}

}
