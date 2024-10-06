package com.Employee;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
public class Home extends JFrame implements ActionListener{
	//JButton add,view,remove,update,salary,attendance,exit,department,leave;
	JPanel p1,p2,p3;
	Font f=new Font("Courier New ",Font.BOLD,15);
	static JMenuItem close,add,Viewall,delete,update,salary,viewAttendance,addDepartment,leave;
public Home(){
	
	
	//This code is for those who don't want menubar just want simple buttons
	
	
//	getContentPane().setBackground(new Color(0,123,153));
//	ImageIcon  i1=new ImageIcon(ClassLoader.getSystemResource("hrhome.png"));
//    Image i2=i1.getImage().getScaledInstance(450,400,Image.SCALE_DEFAULT);
//    ImageIcon  i3=new ImageIcon(i2);
//    JLabel l1=new JLabel(i3);
//    l1.setBounds(0,0,800,650);
//    add(l1);
//
//	add=new JButton("Add Employee");
//	add.setBounds(140,150,200,40);
//	add.setForeground(Color.BLACK);
//	add.setBackground(Color.YELLOW);
//	add.setFont(f);
//	add.addActionListener(this);
//	l1.add(add);
//	
//	view=new JButton("View Employee");
//	view.setBounds(420,150,200,40);
//	view.setForeground(Color.BLACK);
//	view.setBackground(Color.YELLOW);
//	view.setFont(f);
//	view.addActionListener(this);
//	l1.add(view);
//	
//	remove=new JButton("Remove Employee");
//	remove.setBounds(140,220,200,40);
//	remove.setForeground(Color.BLACK);
//	remove.setBackground(Color.YELLOW);
//	remove.setFont(f);
//	remove.addActionListener(this);
//	l1.add(remove);
//	
//	update=new JButton("Update Employee");
//	update.setBounds(420,220,200,40);
//	update.setForeground(Color.BLACK);
//	update.setBackground(Color.YELLOW);
//	update.setFont(f);
//	update.addActionListener(this);
//	l1.add(update);
//	
//	attendance=new JButton("View Attendance");
//	attendance.setBounds(140,290,200,40);
//	attendance.setForeground(Color.BLACK);
//	attendance.setBackground(Color.YELLOW);
//	attendance.setFont(f);
//	attendance.addActionListener(this);
//	l1.add(attendance);
//	
//	salary=new JButton("Salary");
//	salary.setBounds(420,290,200,40);
//	salary.setForeground(Color.BLACK);
//	salary.setBackground(Color.YELLOW);
//	salary.setFont(f);
//	salary.addActionListener(this);
//	l1.add(salary);
//	
//	department=new JButton("Add Department");
//	department.setBounds(140,360,200,40);
//	department.setForeground(Color.BLACK);
//	department.setBackground(Color.YELLOW);
//	department.setFont(f);
//	department.addActionListener(this);
//	l1.add(department);
//	
//	leave=new JButton("Check Leave");
//	leave.setBounds(420,360,200,40);
//	leave.setForeground(Color.BLACK);
//	leave.setBackground(Color.YELLOW);
//	leave.setFont(f);
//	leave.addActionListener(this);
//	l1.add(leave);
//
//	exit=new JButton("Exit");
//	exit.setBounds(570,512,130,40);
//	exit.setForeground(Color.WHITE);
//	exit.setBackground(new Color(255,10,10));
//	exit.setFont(f);
//	exit.addActionListener(this);
//	l1.add(exit);
//	
//	setSize(800,630);
//	setVisible(true);
//	setLocationRelativeTo(null);
//	setTitle("Home");
//	setLayout(null);
//	setResizable(false);
	
	
	
	ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("hrback.png"));
	Image i2=i1.getImage().getScaledInstance(1550,824,Image.SCALE_SMOOTH);
	ImageIcon i3=new ImageIcon(i2);
	JLabel l=new JLabel(i3);
	l.setBounds(0,0,1550,824);
	getContentPane().add(l);
	
	JMenuBar menuBar = new JMenuBar();
	menuBar.setMargin(new Insets(0, 10, 0, 20));
	menuBar.setBorderPainted(false);
//	menuBar.setBounds(5, 10, 155, 50);

	
	add = new JMenuItem("Add Employee");
	add.setFont(new Font("Arial",Font.BOLD,15));
	Image i=new ImageIcon(ClassLoader.getSystemResource("add new emp.png")).getImage().getScaledInstance(35, 48, Image.SCALE_SMOOTH);
	add.setIcon(new ImageIcon(i));
	add.setBorder(new EmptyBorder(5,0,5,105));
	add.addActionListener(this);
	menuBar.add(add);
	
	update = new JMenuItem("Update Employee");
	update.setFont(new Font("Arial",Font.BOLD,15));
	i=new ImageIcon(ClassLoader.getSystemResource("Update emp.png")).getImage().getScaledInstance(35, 48, Image.SCALE_SMOOTH);
	update.setIcon(new ImageIcon(i));
	update.setBorder(new EmptyBorder(5,5,5,105));
	update.addActionListener(this);
	menuBar.add(update);
	
	Viewall= new JMenuItem("View Employees");
	i=new ImageIcon(ClassLoader.getSystemResource("search.png")).getImage().getScaledInstance(35, 40, Image.SCALE_SMOOTH);
	Viewall.setIcon(new ImageIcon(i));
	Viewall.setFont(new Font("Arial",Font.BOLD,15));
	Viewall.setBorder(new EmptyBorder(5,5,5,105));
	Viewall.addActionListener(this);
	menuBar.add(Viewall);
	
	delete = new JMenuItem("Remove Employee");
	i=new ImageIcon(ClassLoader.getSystemResource("delete Question.png")).getImage().getScaledInstance(48, 48, Image.SCALE_SMOOTH);
	delete.setIcon(new ImageIcon(i));
	delete.setFont(new Font("Arial",Font.BOLD,15));
	delete.setBorder(new EmptyBorder(5,0,5,117));
	delete.addActionListener(this);
	menuBar.add(delete);
	
	viewAttendance = new JMenuItem("Attendance");
	i=new ImageIcon(ClassLoader.getSystemResource("attendance.png")).getImage().getScaledInstance(45, 48, Image.SCALE_SMOOTH);
	viewAttendance.setIcon(new ImageIcon(i));
	viewAttendance.setFont(new Font("Arial",Font.BOLD,15));
	viewAttendance.setBorder(new EmptyBorder(5,5,5,80));
	viewAttendance.addActionListener(this);
	menuBar.add(viewAttendance);
	
	salary = new JMenuItem("Salary");
	i=new ImageIcon(ClassLoader.getSystemResource("s.png")).getImage().getScaledInstance(35, 48, Image.SCALE_SMOOTH);
	salary.setIcon(new ImageIcon(i));
	salary.setFont(new Font("Arial",Font.BOLD,15));
	salary.setBorder(new EmptyBorder(5,0,5,20));
	salary.addActionListener(this);
	menuBar.add(salary);
	
	addDepartment = new JMenuItem("Department");
	i=new ImageIcon(ClassLoader.getSystemResource("addd.png")).getImage().getScaledInstance(35, 48, Image.SCALE_SMOOTH);
	addDepartment.setIcon(new ImageIcon(i));
	addDepartment.setFont(new Font("Arial",Font.BOLD,15));
	addDepartment.setBorder(new EmptyBorder(5,0,5,99));
	addDepartment.addActionListener(this);
	menuBar.add(addDepartment);
	
	leave = new JMenuItem("Leave");
	i=new ImageIcon(ClassLoader.getSystemResource("leave.png")).getImage().getScaledInstance(35, 48, Image.SCALE_SMOOTH);
	leave.setIcon(new ImageIcon(i));
	leave.setFont(new Font("Arial",Font.BOLD,15));
	leave.setBorder(new EmptyBorder(5,0,5,30));
	leave.addActionListener(this);
	menuBar.add(leave);
	
	close = new JMenuItem("Exit");
	i=new ImageIcon(ClassLoader.getSystemResource("Close.png")).getImage().getScaledInstance(35, 48, Image.SCALE_SMOOTH);
	close.setIcon(new ImageIcon(i));
	close.setFont(new Font("Arial",Font.BOLD,15));
	close.setBorder(new EmptyBorder(5,0,5,30));
	close.addActionListener(this);
	menuBar.add(close);
	
	setJMenuBar(menuBar);
	setSize(1550,824);
	setUndecorated(true);
	setVisible(true);
	}
@Override
public void actionPerformed(ActionEvent e) {
	if(e.getSource()==close) {
		setVisible(false);
		new Login();
	}
	else if(e.getSource()==add) {
		new AddEmployee();
		close.setEnabled(false); delete.setEnabled(false);update.setEnabled(false);salary.setEnabled(false); 
		viewAttendance.setEnabled(false);Viewall.setEnabled(false);addDepartment.setEnabled(false);leave.setEnabled(false);
	}
	else if(e.getSource()==delete) {
		new RemoveEmployee();
		add.setEnabled(false); close.setEnabled(false);update.setEnabled(false);salary.setEnabled(false); 
		viewAttendance.setEnabled(false);Viewall.setEnabled(false);addDepartment.setEnabled(false);leave.setEnabled(false);
	}
	else if(e.getSource()==update) {
		new UpdateEmployee();
		add.setEnabled(false); delete.setEnabled(false);close.setEnabled(false);salary.setEnabled(false); 
		viewAttendance.setEnabled(false);Viewall.setEnabled(false);addDepartment.setEnabled(false);leave.setEnabled(false);
	}
	else if(e.getSource()==salary) {
		new ShowSalaryByHR();
		add.setEnabled(false); delete.setEnabled(false);update.setEnabled(false);close.setEnabled(false); 
		viewAttendance.setEnabled(false);Viewall.setEnabled(false);addDepartment.setEnabled(false);leave.setEnabled(false);
	}
	else if(e.getSource()==viewAttendance) {
		new ViewAttendanceByHr();
		add.setEnabled(false); delete.setEnabled(false);update.setEnabled(false);salary.setEnabled(false); 
		close.setEnabled(false);Viewall.setEnabled(false);addDepartment.setEnabled(false);leave.setEnabled(false);
	}
	else if(e.getSource()==Viewall) {
		new ViewEmployee();
		add.setEnabled(false); delete.setEnabled(false);update.setEnabled(false);salary.setEnabled(false); 
		viewAttendance.setEnabled(false);close.setEnabled(false);addDepartment.setEnabled(false);leave.setEnabled(false);
	}
	
	else if(e.getSource()==addDepartment) {
		new AddDepartment();
		add.setEnabled(false); delete.setEnabled(false);update.setEnabled(false);salary.setEnabled(false); 
		viewAttendance.setEnabled(false);Viewall.setEnabled(false);close.setEnabled(false);leave.setEnabled(false);
	}
	else if(e.getSource()==leave) {
		new CheckLeave();
		add.setEnabled(false); delete.setEnabled(false);update.setEnabled(false);salary.setEnabled(false); 
		viewAttendance.setEnabled(false);Viewall.setEnabled(false);addDepartment.setEnabled(false);close.setEnabled(false);
	}
}

	public static void main(String[] args) {
		new Home();
	}
	
}
