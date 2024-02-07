package com.VisitorsManagement;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import com.util.data.*;

public class Department_Insert extends JFrame implements ActionListener,KeyListener {
	JLabel department,department_id,department_name,description;
	JTextField department_idf,department_namef,descriptionf;
	JButton ji,jc,jco,jn;
	Connection con;PreparedStatement st;ResultSet rs;
	JProgressBar jp;
	int val=0;
	Department_Insert()
	{
		setSize(1400,800);
		setTitle("Department Insert");
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
	    
	    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("insert.png"));
	    Image i2 = i1.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
	    ImageIcon i3 = new ImageIcon(i2);
	    JLabel image1 = new JLabel("Department Insert",i3,SwingConstants.CENTER);
	    image1.setVerticalTextPosition(JLabel.CENTER);
	    image1.setHorizontalTextPosition(JLabel.RIGHT);
	    image1.setForeground(new Color(0,0,128));
	    image1.setFont(new Font("Arial",Font.BOLD,30));
	    image1.setBounds(550,0, 400, 150);
	    add(image1);
	    image.add(image1);
		
		department_id=new JLabel("DEPARTMENT ID:");
		department_id.setBounds(400,170,150,30);
		department_id.setFont(new Font("Arial",Font.BOLD,16));
		add(department_id);
		image.add(department_id);
		department_idf=new JTextField();
		department_idf.setText(getID());
		department_idf.setEditable(false);
		department_idf.setBounds(650,170,250,30);
		department_idf.setFont(new Font("Arial",Font.BOLD,16));
		add(department_idf);
		image.add(department_idf);
		department_idf.addKeyListener(this);
		
		department_name=new JLabel("DEPARTMENT NAME:");
		department_name.setBounds(400,220,350,30);
		department_name.setFont(new Font("Arial",Font.BOLD,16));
		add(department_name);
		image.add(department_name);
		department_namef=new JTextField();
		department_namef.setBounds(650,220,250,30);
		add(department_namef);
		image.add(department_namef);
		department_namef.addKeyListener(this);
		
		description=new JLabel("DESCRIPTION:");
		description.setBounds(400,270,150,30);
		description.setFont(new Font("Arial",Font.BOLD,16));
		add(description);
		image.add(description);
		descriptionf=new JTextField();
		descriptionf.setBounds(650,270,250,30);
		add(descriptionf);
		image.add(descriptionf);
		descriptionf.addKeyListener(this);
		
		jp=new JProgressBar();
		jp.setMinimum(0);
		jp.setMaximum(100);
		jp.setValue(val);
		jp.setBounds(450,460,550,20);
		add(jp);
		jp.setStringPainted(true);
		image.add(jp);

		ji = new JButton("INSERT");
		ji.setBounds(400,360,150,30);
		ji.setFont(new Font("Arial",Font.BOLD,16));
		ji.setBackground(new Color(173,216,230));
		add(ji);
		image.add(ji);
		ji.addActionListener(this);
		
		jc = new JButton("CLEAR");
		jc.setBounds(600,360,150,30);
		jc.setBackground(new Color(173,216,230));
		jc.setFont(new Font("Arial",Font.BOLD,16));
		add(jc);
		image.add(jc);
		jc.addActionListener(this);
		
		jco=new JButton("CLOSE");
	    jco.setBounds(800, 360, 150, 30);
	    jco.setBackground(new Color(173,216,230));
	    jco.setFont(new Font("Arial",Font.BOLD,16));
		add(jco);
		image.add(jco);
		jco.addActionListener(this);
		

		
		
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

	}
	


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Department_Insert();
		

	}



	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		if(ae.getSource()==ji)
		{
			
			if(department_idf.getText().length()==0||department_namef.getText().length()==0||descriptionf.getText().length()==0)
			{
				JOptionPane.showMessageDialog(this, "Check all the entries First");
			}
			else
			{
				String dep_id=department_idf.getText();
				String dep_name=department_namef.getText();
				String des=descriptionf.getText();
				DepartmentsDataHandle obj=new DepartmentsDataHandle();
				String msg=obj.insertData(dep_id,dep_name,des);
				JOptionPane.showMessageDialog(this, msg);
				department_idf.setText("");
				department_namef.setText("");
				descriptionf.setText("");
			}
		}
		


		if(ae.getSource()==jc)
		{
		
			department_idf.setText("");
			department_namef.setText("");
			descriptionf.setText("");
			val=0;
			jp.setValue(val);
			
			
		}
		if(ae.getSource()==jn)
		{
			String r=getID();
			department_idf.setText(r);
		}
		if(ae.getSource()==jco)
		{
			this.dispose();
		}
	}
		String getID()
		{
			String did;
			try {
				
		
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
				String sql="select  departmentid from departments";
				Statement st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
				ResultSet rs=st.executeQuery(sql);
				if (rs.next())
				{
					rs.last();
					String d_id=rs.getString(1);
					String w=d_id.substring(1,d_id.length());
					int x=Integer.parseInt(w);
					if (x<10)
						did="D"+"000"+(x+1);
					else if(x>=10 && x<99)
						did="D"+"00"+(x+1);
					else if(x>=100 && x<999)
						did="D"+"0"+(x+1);
					else
						did="D"+(x+1);
						}
				else
				{
					did="D0001";
							}
				return did;
					
			}
			catch(Exception ex) {
				return ex.toString();
			}

		
		
	}



		@Override
		public void keyPressed(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
			
		}



		@Override
		public void keyReleased(KeyEvent ar) {
			// TODO Auto-generated method stub
			if(ar.getSource()==department_idf && department_idf.getText().length()>0)

			{
				if(ar.getKeyCode()==ar.VK_ENTER)
				{
					department_namef.requestFocus();
					val=val+34;
					jp.setValue(val);

				}
			}
			if(ar.getSource()==department_namef && department_namef.getText().length()>0)
			{
				if(ar.getKeyCode()==ar.VK_ENTER)
				{
					descriptionf.requestFocus();
					val=val+34;
					jp.setValue(val);

				}
			}
			if(ar.getSource()==descriptionf && descriptionf.getText().length()>0)
			{
				if(ar.getKeyCode()==ar.VK_ENTER)
				{
					descriptionf.requestFocus();
					val=val+32;
					jp.setValue(val);
				}
			}
		}



		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

	



		
	}

