package com.Employee;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class ShowPersonalDetails extends JFrame{
String empname,address,dob,department,email,empid,salary,aadhar,phone;
JLabel lname,lempid,laddress,ldob,ldepartment,laadhar,lsalary,lemail,lphone;
JLabel lname1,lempid1,laddress1,ldob1,ldepartment1,laadhar1,lsalary1,lemail1,lphone1;
Font f=new Font("Courier New",Font.BOLD,17);
JButton back;
	public ShowPersonalDetails(String empid) {
		setLayout(null);
		setTitle("Personal Details");
		setSize(520,550);
		
		setLocationRelativeTo(null);
		setResizable(false);
		this.empid=empid;
		try {
			Conn con=new Conn();
			
			ResultSet rs=con.stmt.executeQuery("Select * from EmployeeLogin where emp_id='"+empid+"'");
			if(rs.next()) {
				empname=rs.getString("Emp_Name");
				address=rs.getString("Address");
				dob=rs.getString("DOB");
				department=rs.getString("Department");
				email=rs.getString("Email");
				aadhar=rs.getString("aadhar");
				phone=rs.getString("phone");
				salary=rs.getString("salary");
			}
		}catch(Exception E) {
			E.printStackTrace();
		}
		
		lempid=new JLabel("Employee Id : ");
		lempid.setBounds(10,10,160,30);
		lempid.setFont(f);
		add(lempid);
		
		lname=new JLabel("Name : ");
		lname.setBounds(10,50,160,30);
		lname.setFont(f);
		add(lname);
		
		lemail=new JLabel("Email : ");
		lemail.setBounds(10,100,160,30);
		lemail.setFont(f);
		add(lemail);
		
		ldob=new JLabel("Date Of Birth : ");
		ldob.setBounds(10,150,200,30);
		ldob.setFont(f);
		add(ldob);
		
		laddress=new JLabel("Address : ");
		laddress.setBounds(10,200,160,30);
		laddress.setFont(f);
		add(laddress);
		
		laadhar=new JLabel("Aadhar : ");
		laadhar.setBounds(10,250,160,30);
		laadhar.setFont(f);
		add(laadhar);
		
		lphone=new JLabel("Phone : ");
		lphone.setBounds(10,300,160,30);
		lphone.setFont(f);
		add(lphone);
		
		lsalary=new JLabel("Salary : ");
		lsalary.setBounds(10,350,160,30);
		lsalary.setFont(f);
		add(lsalary);
		
		ldepartment=new JLabel("Department : ");
		ldepartment.setBounds(10,400,160,30);
		ldepartment.setFont(f);
		add(ldepartment);
				
		lempid1=new JLabel(empid);
		lempid1.setBounds(220,10,180,30);
		lempid1.setFont(f);
		add(lempid1);
		
		lname1=new JLabel(empname);
		lname1.setBounds(220,50,180,30);
		lname1.setFont(f);
		add(lname1);
		
		lemail1=new JLabel(email);
		lemail1.setBounds(220,100,180,30);
		lemail1.setFont(f);
		add(lemail1);
		
		ldob1=new JLabel(dob);
		ldob1.setBounds(220,150,200,30);
		ldob1.setFont(f);
		add(ldob1);
		
		laddress1=new JLabel(address);
		laddress1.setBounds(220,200,180,30);
		laddress1.setFont(f);
		add(laddress1);
		
		laadhar1=new JLabel(aadhar);
		laadhar1.setBounds(220,250,180,30);
		laadhar1.setFont(f);
		add(laadhar1);
		
		lphone1=new JLabel(phone);
		lphone1.setBounds(220,300,180,30);
		lphone1.setFont(f);
		add(lphone1);
		
		lsalary1=new JLabel(salary);
		lsalary1.setBounds(220,350,180,30);
		lsalary1.setFont(f);
		add(lsalary1);
		
		ldepartment1=new JLabel(department);
		ldepartment1.setBounds(220,400,320,30);
		ldepartment1.setFont(f);
		add(ldepartment1);
		
		
		back=new JButton("Back");
		back.setBounds(360,450,100,35);
		back.setFont(f);
		back.setBackground(Color.RED);
		back.setForeground(Color.WHITE);
		add(back);
		back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				EmployeeHome.back.setEnabled(true); EmployeeHome.attendance.setEnabled(true);EmployeeHome.salary.setEnabled(true);EmployeeHome.logout.setEnabled(true);
			}
		});
		setUndecorated(true);
		setVisible(true);
	}
	public static void main(String[] args) {
	new ShowPersonalDetails("");
	}
}
