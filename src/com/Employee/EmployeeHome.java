package com.Employee;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.*;

public class EmployeeHome extends JFrame implements ActionListener {
	static JMenuItem details,salary,attendance,logout,back;
	Font f=new Font("Courier New ",Font.BOLD,17);
	String id;
	JMenuBar menuBar;
public EmployeeHome(String id) {
	this.id=id;
	setLayout(null);
	
	ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("empback.png"));
	Image i2=i1.getImage().getScaledInstance(1550,824,Image.SCALE_SMOOTH);
	ImageIcon i3=new ImageIcon(i2);
	JLabel l=new JLabel(i3);
	l.setBounds(0,0,1550,824);
	getContentPane().add(l);
	
	JMenuBar menuBar = new JMenuBar();
	menuBar.setMargin(new Insets(0, 10, 0, 20));
	menuBar.setBorderPainted(false);
	
	
	details = new JMenuItem("My Profile");
	details.setFont(new Font("Arial",Font.BOLD,18));
	Image i=new ImageIcon(ClassLoader.getSystemResource("leave.png")).getImage().getScaledInstance(35, 48, Image.SCALE_SMOOTH);
	details.setIcon(new ImageIcon(i));
	details.setBorder(new EmptyBorder(5,50,5,105));
	details.addActionListener(this);
	menuBar.add(details);
	
	salary = new JMenuItem("Check Salary");
	salary.setFont(new Font("Arial",Font.BOLD,18));
	i=new ImageIcon(ClassLoader.getSystemResource("s.png")).getImage().getScaledInstance(35, 48, Image.SCALE_SMOOTH);
	salary.setIcon(new ImageIcon(i));
	salary.setBorder(new EmptyBorder(5,5,5,105));
	salary.addActionListener(this);
	menuBar.add(salary);
	
	attendance= new JMenuItem("Attendance");
	i=new ImageIcon(ClassLoader.getSystemResource("attendance.png")).getImage().getScaledInstance(35, 40, Image.SCALE_SMOOTH);
	attendance.setIcon(new ImageIcon(i));
	attendance.setFont(new Font("Arial",Font.BOLD,18));
	attendance.setBorder(new EmptyBorder(5,5,5,105));
	attendance.addActionListener(this);
	menuBar.add(attendance);
	
	
	logout = new JMenuItem("Logout");
	i=new ImageIcon(ClassLoader.getSystemResource("Logout.png")).getImage().getScaledInstance(48, 48, Image.SCALE_SMOOTH);
	logout.setIcon(new ImageIcon(i));
	logout.setFont(new Font("Arial",Font.BOLD,18));
	logout.setBorder(new EmptyBorder(5,0,5,117));
	logout.addActionListener(this);
	menuBar.add(logout);
	
	back = new JMenuItem("Close");
	i=new ImageIcon(ClassLoader.getSystemResource("Close.png")).getImage().getScaledInstance(48, 48, Image.SCALE_SMOOTH);
	back.setIcon(new ImageIcon(i));
	back.setFont(new Font("Arial",Font.BOLD,18));
	back.setBorder(new EmptyBorder(5,0,5,117));
	back.addActionListener(this);
	menuBar.add(back);
    
	
	//This code is for those who don't want menubar just want simple buttons
	
	
//	details=new JButton("My Profile");
//	details.setBounds(143,53,200,53);
//	details.setForeground(Color.WHITE);
//	details.setBackground(Color.BLACK);
//	details.setFont(f);
//	details.addActionListener(this);
//	add(details);
//	
//	salary=new JButton("Salary");
//	salary.setBounds(143,156,200,53);
//	salary.setForeground(Color.WHITE);
//	salary.setBackground(Color.BLACK);
//	salary.setFont(f);
//	salary.addActionListener(this);
//	add(salary);
//	
//	attendance=new JButton("Attendance");
//	attendance.setBounds(143,260,200,53);
//	attendance.setForeground(Color.WHITE);
//	attendance.setBackground(Color.BLACK);
//	attendance.setFont(f);
//	attendance.addActionListener(this);
//	add(attendance);
//	
//	back=new JButton("Back");
//	back.setBounds(189,359,119,53);
//	back.setForeground(Color.WHITE);
//	back.setBackground(Color.RED);
//	back.setFont(f);
//	back.addActionListener(this);
//	add(back);
	
	
//	ImageIcon  i1=new ImageIcon(ClassLoader.getSystemResource("emphome.jpg"));
//    Image i2=i1.getImage().getScaledInstance(500,500,Image.SCALE_DEFAULT);
//  ImageIcon  i3=new ImageIcon(i2);
//    JLabel l1=new JLabel(i3);
//    l1.setBounds(0,0,500,500);
//    add(l1);
//    
//	setTitle("Employee Home ");
//	setVisible(true);
//	setSize(500,500);
//	setLocationRelativeTo(null);
	
	setJMenuBar(menuBar);
	setSize(1550,824);
	setUndecorated(true);
	setVisible(true);
	
}
@Override
public void actionPerformed(ActionEvent e) {
	if(e.getSource()==back){
		setVisible(false);
		new Login();
}
	else if(e.getSource()==logout) {
		setVisible(false);
		new EmployeeLogin();
	}
	else if(e.getSource()==details) {
		back.setEnabled(false); attendance.setEnabled(false);salary.setEnabled(false);logout.setEnabled(false);
		new ShowPersonalDetails( id);
	}
	else if(e.getSource()==attendance) {
		back.setEnabled(false); details.setEnabled(false);salary.setEnabled(false);logout.setEnabled(false);
		new AttendanceEmployee(id);
	}
	else if(e.getSource()==salary) {
		back.setEnabled(false); attendance.setEnabled(false);details.setEnabled(false);logout.setEnabled(false);
		new SalaryEmployee(id);
	}
}

	public static void main(String[] args) {
		new EmployeeHome("");
	}
	
}
