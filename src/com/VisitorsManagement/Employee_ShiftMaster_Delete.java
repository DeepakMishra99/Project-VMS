package com.VisitorsManagement;
import com.util.data.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;

public class Employee_ShiftMaster_Delete extends JFrame implements ActionListener,ItemListener {
	JLabel employeeshiftmaster,employee_id,shift_id;
	JTextField shift_idf;
	JButton jd,jco,jf;
	JComboBox cb;
	
	Connection con;PreparedStatement st;ResultSet rs;
	public Employee_ShiftMaster_Delete(){
		setSize(1400,800);
		setLayout(null);
		setTitle("Employee Shift Delete");
		setLocationRelativeTo(null); 
		setResizable(false);
		
		ImageIcon bg1 = new ImageIcon(ClassLoader.getSystemResource("all_bg.jpg"));
	    Image bg2= bg1.getImage().getScaledInstance(1400, 800, Image.SCALE_DEFAULT);
	    ImageIcon bg3 = new ImageIcon(bg2);
	    JLabel image = new JLabel(bg3);
	    image.setBounds(0,0, 1400, 800);
	    add(image);
	    
	    ImageIcon logo1 = new ImageIcon(ClassLoader.getSystemResource("shift.png"));
	    Image logo2= logo1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
	    ImageIcon logo3 = new ImageIcon(logo2);
	    JLabel image_logo = new JLabel("EMPLOYEE SHIFT MASTER",logo3,SwingConstants.CENTER);
	    image_logo.setVerticalTextPosition(JLabel.BOTTOM);
	    image_logo.setHorizontalTextPosition(JLabel.CENTER);
	    image_logo.setFont(new Font("Arial",Font.BOLD,16));
	    image_logo.setBounds(10,10,300, 150);
	    
	    add(image_logo);
	    image.add(image_logo);
	    
	    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("delete.png"));
	    Image i2 = i1.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
	    ImageIcon i3 = new ImageIcon(i2);
	    JLabel image1 = new JLabel("Employee Shift Delete",i3,SwingConstants.CENTER);
	    image1.setVerticalTextPosition(JLabel.CENTER);
	    image1.setHorizontalTextPosition(JLabel.RIGHT);
	    image1.setFont(new Font("Arial",Font.BOLD,30));
	    image1.setForeground(new Color(0,0,128));
	    
	    image1.setBounds(480,0,400, 150);
	    add(image1);
	    image.add(image1);
		
		employee_id=new JLabel("EMPLOYEE ID:");
		employee_id.setBounds(450,170,150,30);
		employee_id.setFont(new Font("Arial",Font.BOLD,16));
		add(employee_id);
		image.add(employee_id);		
		cb=new JComboBox();
		cb.setBounds(600,170,250,30);
		cb.insertItemAt("", 0);
		add(cb);
		image.add(cb);
		filldata();
		cb.addItemListener(this);
				
		shift_id=new JLabel("SHIFT ID:");
		shift_id.setBounds(450,220,150,30);
		shift_id.setFont(new Font("Arial",Font.BOLD,16));
		add(shift_id);
		image.add(shift_id);
		shift_idf=new JTextField();
		shift_idf.setBounds(600,220,250,30);
		add(shift_idf);
		image.add(shift_idf);
		
		jd = new JButton("DELETE");
		jd.setBounds(400,320,150,30);
		jd.setFont(new Font("Arial",Font.BOLD,16));
		jd.setBackground(new Color(173,216,230));
		add(jd);
		image.add(jd);
		jd.addActionListener(this);
		
		jco = new JButton("CLOSE");
		jco.setBounds(700,320,150,30);
		jco.setFont(new Font("Arial",Font.BOLD,16));
		jco.setBackground(new Color(173,216,230));
		add(jco);
		image.add(jco);
		jco.addActionListener(this);
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Employee_ShiftMaster_Delete();

	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		
		if(ae.getSource()==jd)
		{
			int opt=JOptionPane.showConfirmDialog(null,"Are you sure to Delete","Delete",JOptionPane.YES_NO_OPTION);
			if(opt==0)
			{
			String emp_id=cb.getSelectedItem().toString();
			Employee_ShiftMaster_DataDelete obj = new Employee_ShiftMaster_DataDelete();
			String msg=obj.DeleteData(emp_id);
			JOptionPane.showMessageDialog(this, msg);
			}
		}
		
		if(ae.getSource()==jco)
		{
			this.dispose();
		}

		
	}
	void filldata()
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			String sql="select EmpId from EmployeeShiftMaster";
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
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
				if(e.getSource()==cb)
				{
				
					String k=cb.getSelectedItem().toString();
					Employee_ShiftMaster_DataFind obj=new Employee_ShiftMaster_DataFind();
					String s[]=obj.findData(k);
					if(s!=null)
					{
						shift_idf.setText(s[0]);
					}
					else
					{
						
						
						JOptionPane.showMessageDialog(this, "Record Not Found");
					}
				}
	}

}


