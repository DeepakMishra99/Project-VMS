package com.VisitorsManagement;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import javax.swing.*;

import com.VisitorsManagement.CompanyMaster_insert;


public class Admin_screen extends JFrame implements ActionListener {
	Connection con;PreparedStatement st;ResultSet rs;
	
	JMenuBar mb;
	JLabel ldate,ltitle,label,lname,lid,lemail,lphone,lstatus,lgender,name,id,email,phone,status,gender;
	String nn;
	JPanel jp;
	JButton logout,manage;
	JMenu admin_master,cmp_master,dept,shift_master,hld_master,hod_master,emp_master,emp_shift,visitor,checkin,checkout,defaulter;
	JMenuItem admin1,admin2,admin3,admin4,admin5,cmp1,cmp2,cmp3,cmp4,cmp5,dept1,dept2,dept3,dept4,dept5,sft1,sft2,sft3,sft4,sft5,hld1,hld2,hld3,hld4,hld5,hod1,hod2,hod3,hod4,hod5,emp1,emp2,emp3,emp4,emp5;
	JMenuItem empsft1,empsft2,empsft3,empsft4,empsft5,visit1,visit2,visit3,visit4,visit5,chk1,chk2,chk3,chk4,chk5,chkout1,chkout2,chkout3,chkout4,chkout5,def1,def2,def3,def4,def5;
	Admin_screen()
	{
	setSize(1920,1080);
	setTitle("ADMIN");
	setLayout(null);
	setLocationRelativeTo(null); 
	setResizable(false);
	
	mb=new JMenuBar();
	
	ImageIcon bg1 = new ImageIcon(ClassLoader.getSystemResource("screen1.jpg"));
    Image bg2= bg1.getImage().getScaledInstance(2000, 1080, Image.SCALE_DEFAULT);
    ImageIcon bg3 = new ImageIcon(bg2);
    JLabel image = new JLabel(bg3);
    image.setBounds(0,0, 2000, 1080);
    add(image);
    
    jp= new JPanel();
	jp.setBounds(0, 0, 400, 1080);
	jp.setBackground(new Color(207,208,211));
	image.add(jp);
	jp.setLayout(null);
   	
	cmp_master=new JMenu("Company Master");
	cmp1=new JMenuItem("Insert");
	cmp2=new JMenuItem("Delete");
	cmp3=new JMenuItem("Update");
	cmp4=new JMenuItem("Find");
	cmp5=new JMenuItem("Show Data");
	cmp_master.add(cmp1);
	cmp_master.addSeparator();
	cmp_master.add(cmp2);
	cmp_master.addSeparator();
	cmp_master.add(cmp3);
	cmp_master.addSeparator();
	cmp_master.add(cmp4);
	cmp_master.addSeparator();
	cmp_master.add(cmp5);
	
	
	
	dept=new JMenu("Department");
	dept1=new JMenuItem("Insert");
	dept2=new JMenuItem("Delete");
	dept3=new JMenuItem("Update");
	dept4=new JMenuItem("Find");
	dept5=new JMenuItem("Show Data");
	dept.add(dept1);
	dept.addSeparator();
	dept.add(dept2);
	dept.addSeparator();
	dept.add(dept3);
	dept.addSeparator();
	dept.add(dept4);
	dept.addSeparator();
	dept.add(dept5);
	
	shift_master=new JMenu("Shift Master");
	sft1=new JMenuItem("Insert");
	sft2=new JMenuItem("Delete");
	sft3=new JMenuItem("Update");
	sft4=new JMenuItem("Find");
	sft5=new JMenuItem("Show Data");
	shift_master.add(sft1);
	shift_master.addSeparator();
	shift_master.add(sft2);
	shift_master.addSeparator();
	shift_master.add(sft3);
	shift_master.addSeparator();
	shift_master.add(sft4);
	shift_master.addSeparator();
	shift_master.add(sft5);
	
	hld_master=new JMenu("Holiday Master");
	hld1=new JMenuItem("Insert");
	hld2=new JMenuItem("Delete");
	hld3=new JMenuItem("Update");
	hld4=new JMenuItem("Find");
	hld5=new JMenuItem("Show Data");
	hld_master.add(hld1);
	hld_master.addSeparator();
	hld_master.add(hld2);
	hld_master.addSeparator();
	hld_master.add(hld3);
	hld_master.addSeparator();
	hld_master.add(hld4);
	hld_master.addSeparator();
	hld_master.add(hld5);
	
	hod_master=new JMenu("HOD");
	hod1=new JMenuItem("Insert");
	hod2=new JMenuItem("Delete");
	hod3=new JMenuItem("Update");
	hod4=new JMenuItem("Find");
	hod5=new JMenuItem("Show Data");
	hod_master.add(hod1);
	hod_master.addSeparator();
	hod_master.add(hod2);
	hod_master.addSeparator();
	hod_master.add(hod3);
	hod_master.addSeparator();
	hod_master.add(hod4);
	hod_master.addSeparator();
	hod_master.add(hod5);
	
	admin_master=new JMenu("Admin Master");
	admin1=new JMenuItem("Insert");
	admin2=new JMenuItem("Delete");
	admin3=new JMenuItem("Update");
	admin4=new JMenuItem("Find");
	admin5=new JMenuItem("Show Data");
	admin_master.add(admin1);
	admin_master.addSeparator();
	admin_master.add(admin2);
	admin_master.addSeparator();
	admin_master.add(admin3);
	admin_master.addSeparator();
	admin_master.add(admin4);
	admin_master.addSeparator();
	admin_master.add(admin5);
	
	emp_master=new JMenu("Employee");
	emp1=new JMenuItem("Insert");
	emp2=new JMenuItem("Delete");
	emp3=new JMenuItem("Update");
	emp4=new JMenuItem("Find");
	emp5=new JMenuItem("Show Data");
	emp_master.add(emp1);
	emp_master.addSeparator();
	emp_master.add(emp2);
	emp_master.addSeparator();
	emp_master.add(emp3);
	emp_master.addSeparator();
	emp_master.add(emp4);
	emp_master.addSeparator();
	emp_master.add(emp5);
	
	emp_shift=new JMenu("Employee Shift");
	empsft1=new JMenuItem("Insert");
	empsft2=new JMenuItem("Delete");
	empsft3=new JMenuItem("Update");
	empsft4=new JMenuItem("Find");
	empsft5=new JMenuItem("Show Data");
	emp_shift.add(empsft1);
	emp_shift.addSeparator();
	emp_shift.add(empsft2);
	emp_shift.addSeparator();
	emp_shift.add(empsft3);
	emp_shift.addSeparator();
	emp_shift.add(empsft4);
	emp_shift.addSeparator();
	emp_shift.add(empsft5);
	
	visitor=new JMenu("Visitor");
	visit1=new JMenuItem("Insert");
	visit2=new JMenuItem("Delete");
	visit3=new JMenuItem("Update");
	visit4=new JMenuItem("Find");
	visit5=new JMenuItem("Show Data");
	visitor.add(visit1);
	visitor.addSeparator();
	visitor.add(visit2);
	visitor.addSeparator();
	visitor.add(visit3);
	visitor.addSeparator();
	visitor.add(visit4);
	visitor.addSeparator();
	visitor.add(visit5);
	
	checkin=new JMenu("Check-in");
	chk1=new JMenuItem("Insert");
	chk2=new JMenuItem("Delete");
	chk3=new JMenuItem("Update");
	chk4=new JMenuItem("Find");
	chk5=new JMenuItem("Show Data");
	checkin.add(chk1);
	checkin.addSeparator();
	checkin.add(chk2);
	checkin.addSeparator();
	checkin.add(chk3);
	checkin.addSeparator();
	checkin.add(chk4);
	checkin.addSeparator();
	checkin.add(chk5);
	
	checkout=new JMenu("Check out");
	chkout1=new JMenuItem("Insert");
	chkout2=new JMenuItem("Delete");
	chkout3=new JMenuItem("Find");
	chkout4=new JMenuItem("Show Data");
	
	checkout.add(chkout1);
	checkout.addSeparator();
	checkout.add(chkout2);
	checkout.addSeparator();
	checkout.add(chkout3);
	checkout.addSeparator();
	checkout.add(chkout4);
	
	
	defaulter=new JMenu("Defaulter");
	def1=new JMenuItem("Defaulter Table");
	def2=new JMenuItem("Today Defaulter");
	def3=new JMenuItem("ALERT!");
	
	defaulter.add(def1);
	defaulter.addSeparator();
	defaulter.add(def2);
	defaulter.addSeparator();
	defaulter.add(def3);
	
	
	mb.add(cmp_master);
	mb.add(dept);
	mb.add(shift_master);
	mb.add(hld_master);
	mb.add(hod_master);
	mb.add(admin_master);
	mb.add(emp_master);
	mb.add(emp_shift);
	mb.add(visitor);
	mb.add(checkin);
	mb.add(checkout);
	mb.add(defaulter);
	
	
	cmp1.addActionListener(this);
	cmp2.addActionListener(this);
	cmp3.addActionListener(this);
	cmp4.addActionListener(this);
	cmp5.addActionListener(this);
	
	dept1.addActionListener(this);
	dept2.addActionListener(this);
	dept3.addActionListener(this);
	dept4.addActionListener(this);
	dept5.addActionListener(this);
	
	sft1.addActionListener(this);
	sft2.addActionListener(this);
	sft3.addActionListener(this);
	sft4.addActionListener(this);
	sft5.addActionListener(this);
	
	hld1.addActionListener(this);
	hld2.addActionListener(this);
	hld3.addActionListener(this);
	hld4.addActionListener(this);
	hld5.addActionListener(this);
	
	hod1.addActionListener(this);
	hod2.addActionListener(this);
	hod3.addActionListener(this);
	hod4.addActionListener(this);
	hod5.addActionListener(this);
	
	admin1.addActionListener(this);
	admin2.addActionListener(this);
	admin3.addActionListener(this);
	admin4.addActionListener(this);
	admin5.addActionListener(this);
	
	emp1.addActionListener(this);
	emp2.addActionListener(this);
	emp3.addActionListener(this);
	emp4.addActionListener(this);
	emp5.addActionListener(this);
	
	empsft1.addActionListener(this);
	empsft2.addActionListener(this);
	empsft3.addActionListener(this);
	empsft4.addActionListener(this);
	empsft5.addActionListener(this);
	
	visit1.addActionListener(this);
	visit2.addActionListener(this);
	visit3.addActionListener(this);
	visit4.addActionListener(this);
	visit5.addActionListener(this);
	
	chk1.addActionListener(this);
	chk2.addActionListener(this);
	chk3.addActionListener(this);
	chk4.addActionListener(this);
	chk5.addActionListener(this);
	
	
	
	chkout1.addActionListener(this);
	chkout2.addActionListener(this);
	chkout3.addActionListener(this);
	chkout4.addActionListener(this);

	
	def1.addActionListener(this);
	def2.addActionListener(this);
	def3.addActionListener(this);
	def3.setForeground(new Color(255,0,0));
	
	
	ltitle=new JLabel("        ADMIN ");
	ltitle.setBounds(0,0,400,50);
	ltitle.setOpaque(true);
	ltitle.setForeground(new Color(255,255,255));
	ltitle.setBackground(new Color(41, 71, 90));
	ltitle.setFont(new Font("Arial",Font.BOLD,50));
	jp.add(ltitle);
	
	label=new JLabel(" ");
	label.setBounds(150,100,90,100);
	label.setOpaque(true);
	label.setForeground(new Color(255,255,255));
	label.setBackground(new Color(3, 37, 76));
	label.setFont(new Font("Arial",Font.BOLD,100));
	jp.add(label);
	
	lid=new JLabel("ADMIN ID:");
	lid.setBounds(20,300,100,50);
	lid.setForeground(new Color(0,0,0));
	lid.setFont(new Font("Arial",Font.BOLD,16));
	jp.add(lid);
	id=new JLabel();
	id.setBounds(120,300,200,50);
	id.setForeground(new Color(24, 123, 205));
	id.setFont(new Font("Arial",Font.BOLD,16));
	add(id);
	jp.add(id);
	
	lname=new JLabel("NAME:");
	lname.setBounds(20,360,200,50);
	lname.setForeground(new Color(0,0,0));
	lname.setFont(new Font("Arial",Font.BOLD,16));
	add(lname);
	jp.add(lname);
	name=new JLabel();
	name.setBounds(120,360,100,50);
	name.setForeground(new Color(24, 123, 205));
	name.setFont(new Font("Arial",Font.BOLD,16));
	add(name);
	jp.add(name);

	lemail=new JLabel("EMAIL:");
	lemail.setBounds(20,420,100,50);
	lemail.setForeground(new Color(0,0,0));
	lemail.setFont(new Font("Arial",Font.BOLD,16));
	add(lemail);
	jp.add(lemail);
	email=new JLabel();
	email.setBounds(120,420,200,50);
	email.setForeground(new Color(24, 123, 205));
	email.setFont(new Font("Arial",Font.BOLD,16));
	add(email);
	jp.add(email);
	
	
	lphone=new JLabel("PHONE:");
	lphone.setBounds(20,480,100,50);
	lphone.setForeground(new Color(0,0,0));
	lphone.setFont(new Font("Arial",Font.BOLD,16));
	add(lphone);
	jp.add(lphone);
	phone=new JLabel();
	phone.setBounds(120,480,200,50);
	phone.setForeground(new Color(24, 123, 205));
	phone.setFont(new Font("Arial",Font.BOLD,16));
	add(phone);
	jp.add(phone);
	
	
	lgender=new JLabel("GENDER:");
	lgender.setBounds(20,540,100,50);
	lgender.setForeground(new Color(0,0,0));
	lgender.setFont(new Font("Arial",Font.BOLD,16));
	add(lgender);
	jp.add(lgender);
	gender=new JLabel();
	gender.setBounds(120,540,200,50);
	gender.setForeground(new Color(24, 123, 205));
	gender.setFont(new Font("Arial",Font.BOLD,16));
	add(gender);
	jp.add(gender);
	
	lstatus=new JLabel("STATUS:");
	lstatus.setBounds(20,600,100,50);
	lstatus.setForeground(new Color(0,0,0));
	lstatus.setFont(new Font("Arial",Font.BOLD,16));
	add(lstatus);
	jp.add(lstatus);
	status=new JLabel();
	status.setBounds(120,600,200,50);
	status.setForeground(new Color(24, 123, 205));
	status.setFont(new Font("Arial",Font.BOLD,16));
	add(status);
	jp.add(status);
	
	manage=new JButton("MANAGE PROFILE");
	manage.setBounds(0,680,400,50);
	manage.setForeground(new Color(255,255,255));
	manage.setBackground(new Color(41, 71, 90));
	manage.setFont(new Font("Arial",Font.BOLD,16));
	add(manage);
	jp.add(manage);
	manage.addActionListener(this);
	
	logout=new JButton("LOGOUT");
	logout.setBounds(0,730,400,50);
	logout.setForeground(new Color(255,255,255));
	logout.setBackground(new Color(41, 71, 90));
	logout.setFont(new Font("Arial",Font.BOLD,16));
	add(logout);
	jp.add(logout);
	logout.addActionListener(this);
	
	DateFormat formatter = new SimpleDateFormat("E, MMM dd yyyy");
    Calendar obj = Calendar.getInstance();
    String str = formatter.format(obj.getTime());
     
	
	ldate=new JLabel("DATE: "+str);
	ldate.setBounds(1600,30,300,50);
	ldate.setForeground(new Color(41, 71, 90));
	ldate.setFont(new Font("Arial",Font.BOLD,25));
	add(ldate);
	image.add(ldate);
	
	
	setJMenuBar(mb);
	setVisible(true);
	setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Admin_screen();

	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		if(ae.getSource()==cmp1)
		{
			new CompanyMaster_insert();
		}
		if(ae.getSource()==cmp2)
		{
			new CompanyMaster_Delete();
		}
		if(ae.getSource()==cmp3)
		{
			new CompanyMaster_update();
		}
		if(ae.getSource()==cmp4)
		{
			new CompanyMaster_find();
		}
		if(ae.getSource()==cmp5)
		{
			new CompanyMaster_DataTable();
		}
		if(ae.getSource()==dept1)
		{
			new Department_Insert();
		}
		if(ae.getSource()==dept2)
		{
			new Department_Delete();
		}
		if(ae.getSource()==dept3)
		{
			new Department_Update();
		}
		if(ae.getSource()==dept4)
		{
			new Department_Find();
		}
		if(ae.getSource()==dept5)
		{
			new Department_DataTable();
		}
		if(ae.getSource()==sft1)
		{
			new ShiftMaster_Insert();
		}
		if(ae.getSource()==sft2)
		{
			new ShiftMaster_datadelete();
		}
		if(ae.getSource()==sft3)
		{
			new ShiftMaster_dataupdate();
		}
		if(ae.getSource()==sft4)
		{
			new ShiftMaster_datafind();
		}
		if(ae.getSource()==sft5)
		{
			new ShiftMaster_DataTable();
		}
		if(ae.getSource()==hld1)
		{
			new Holiday_insert();
		}
		if(ae.getSource()==hld2)
		{
			new Holiday_delete();
		}
		if(ae.getSource()==hld3)
		{
			new Holiday_update();
		}
		if(ae.getSource()==hld4)
		{
			new Holiday_find();
		}
		if(ae.getSource()==hld5)
		{
			new HolidayDATATable();
		}
		if(ae.getSource()==hod1)
		{
			new HODInsert();
		}
		if(ae.getSource()==hod2)
		{
			new HODDelete();
		}
		if(ae.getSource()==hod3)
		{
			new HODUpdate();
		}
		if(ae.getSource()==hod4)
		{
			new HODFind();
		}
		if(ae.getSource()==hod5)
		{
			new HODDataTable();
		}
		if(ae.getSource()==admin1)
		{
			new Admin_insert();
		}
		if(ae.getSource()==admin2)
		{
			new Admin_delete();
		}
		if(ae.getSource()==admin3)
		{
			new Admin_update();
		}
		if(ae.getSource()==admin4)
		{
			new Admin_find();
		}
		if(ae.getSource()==admin5)
		{
			new Admin_Table();
		}
		if(ae.getSource()==emp1)
		{
			new Employee_insert();
		}
		if(ae.getSource()==emp2)
		{
			new Employee_delete();
		}
		if(ae.getSource()==emp3)
		{
			new Employee_update();
		}
		if(ae.getSource()==emp4)
		{
			new Employee_find();
		}
		if(ae.getSource()==emp5)
		{
			new Employee_Datatable();
		}
		if(ae.getSource()==empsft1)
		{
			new Employee_ShiftMaster_Insert();
		}
		if(ae.getSource()==empsft2)
		{
			new Employee_ShiftMaster_Delete();
		}
		if(ae.getSource()==empsft3)
		{
			new Employee_ShiftMaster_Update();
		}
		if(ae.getSource()==empsft4)
		{
			new Employee_ShiftMaster_Find();
		}
		if(ae.getSource()==empsft5)
		{
			new Employee_ShiftMaster_DataTable();
		}
		if(ae.getSource()==visit1)
		{
			new vistorInsert();
		}
		if(ae.getSource()==visit2)
		{
			new visitordelete();
		}
		if(ae.getSource()==visit3)
		{
			new visitorupdate();
		}
		if(ae.getSource()==visit4)
		{
			new vistorFind();
		}
		if(ae.getSource()==visit5)
		{
			new VisitorsDataTable();
		}
		if(ae.getSource()==chk1)
		{
			new CheckIn_insert();
		}
		if(ae.getSource()==chk2)
		{
			new CheckIn_delete();
		}
		if(ae.getSource()==chk3)
		{
			new Checkin_Update();
		}
		if(ae.getSource()==chk4)
		{
			new inform();
		}
		if(ae.getSource()==chk5)
		{
			new CheckinDATATable();
		}
		if(ae.getSource()==chkout1)
		{
			new CheckOut_Insert();
		}
		if(ae.getSource()==chkout2)
		{
			new CheckOut_delete();
		}
		if(ae.getSource()==chkout3)
		{
			new CheckOut_find();
		}
		if(ae.getSource()==chkout4)
		{
			new CheckOut_DataTable();
		}
		
		if(ae.getSource()==def1)
		{
			new defaulter();
		}
		if(ae.getSource()==def2)
		{
			new today_defaulter();
		}
		
		if(ae.getSource()==def3)
		{
			new inform();
		}
		
		
		if(ae.getSource()==manage)
		{
			new Admin_update();
		}
		if(ae.getSource()==logout)
		{
			JOptionPane.showMessageDialog(null, "Logout Successful");
			this.dispose();
		}
	
		
	}
	
	
	
}
