package com.Employee;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;

import com.toedter.calendar.JDateChooser;
import java.util.Date;

public class AddEmployee extends JFrame implements ActionListener{
	
JTextField tname,tempid,temail,tsalary,tdepartment,tphone,taadhar;
JTextArea taddress;
Font f=new Font("SAN_SERIF",Font.BOLD,20);
JDateChooser cdob ;
Choice ch;
JButton add,back;

Date d=new Date();


public AddEmployee() {
	getContentPane().setBackground(new Color(100,255,120));
	
	JLabel heading=new JLabel("Add Employee Details");
	heading.setBounds(320,30,500,50);
	heading.setFont(new Font("serif",Font.BOLD,25));
	add(heading);
	
	JLabel empid=new JLabel("Employee Id : ");
	empid.setBounds(320,100,150,30);
	empid.setFont(f);
	add(empid);
	
	tempid=new JTextField();
	tempid.setBounds(490,100,250,30);
	tempid.setBackground(new Color(100,255,120));
	tempid.setForeground(Color.RED);
	tempid.setEditable(false);
	tempid.setFont(f);
	tempid.setText(""+(d.getTime()/10000));
	add(tempid);
	
	JLabel name=new JLabel("Name : ");
	name.setBounds(50,160,150,30);
	name.setFont(f);
	add(name);
	
	tname=new JTextField();
	tname.setBounds(170,160,250,30);
	tname.setBackground(new Color(177,252,197));
	tname.setFont(f);
	add(tname);
	
	JLabel email=new JLabel("Email : ");
	email.setBounds(50,210,150,30);
	email.setFont(f);
	add(email);
	
	temail=new JTextField();
	temail.setBounds(170,210,250,30);
	temail.setBackground(new Color(177,252,197));
	temail.setFont(f);
	add(temail);
	
	JLabel dob=new JLabel("Birth Date : ");
	dob.setBounds(50,260,150,30);
	dob.setFont(f);
	add(dob);
	
	cdob=new JDateChooser();
	cdob.setBounds(170,260,150,30);
	cdob.setBackground(new Color(177,252,197));
	cdob.setForeground(Color.BLACK);
	cdob.setDateFormatString("yyyy-MM-dd");
	cdob.setFont(new Font("serif",Font.BOLD,17));
	add(cdob);
	
	JLabel address=new JLabel("Address : ");
	address.setBounds(50,310,150,30);
	address.setFont(f);
	add(address);
	
	taddress=new JTextArea();
	taddress.setBounds(170,310,250,70);
	taddress.setBackground(new Color(177,252,197));
	taddress.setFont(f);
	add(taddress);
	
	JLabel aadhar=new JLabel("Aadhar No : ");
	aadhar.setBounds(440,160,150,30);
	aadhar.setFont(f);
	add(aadhar);
	
	taadhar=new JTextField();
	taadhar.setBounds(590,160,250,30);
	taadhar.setBackground(new Color(177,252,197));
	taadhar.setFont(f);
	add(taadhar);
	
	JLabel salary=new JLabel("Salary : ");
	salary.setBounds(440,210,150,30);
	salary.setFont(f);
	add(salary);
	
	tsalary=new JTextField();
	tsalary.setBounds(590,210,250,30);
	tsalary.setBackground(new Color(177,252,197));
	tsalary.setFont(f);
	add(tsalary);
	
	JLabel phone=new JLabel("Phone : ");
	phone.setBounds(440,260,150,30);
	phone.setFont(f);
	add(phone);
	
	tphone=new JTextField();
	tphone.setBounds(590,260,250,30);
	tphone.setBackground(new Color(177,252,197));
	tphone.setFont(f);
	add(tphone);
	
	JLabel department=new JLabel("Department : ");
	department.setBounds(440,310,150,30);
	department.setFont(f);
	add(department);
	
	ch=new Choice();
	ch.setBounds(590,310,270,35);
	ch.setBackground(new Color(177,252,197));
	ch.setFont(new Font("serif",Font.BOLD,17));
	add(ch);
	try {
		Conn con=new Conn();
		ResultSet rs=con.stmt.executeQuery("Select Dep_Name from Department");
		while(rs.next()) {
			ch.add(rs.getString(1));
		}
	}catch(Exception e) {
		e.printStackTrace();
	}
	
	add=new JButton("ADD");
	add.setBounds(290,450,150,40);
	add.setBackground(Color.BLACK);
	add.setForeground(Color.WHITE);
	add(add);
	
	back=new JButton("BACK");
	back.setBounds(490,450,150,40);
	back.setBackground(Color.RED);
	back.setForeground(Color.WHITE);
	add(back);
	
	add.addActionListener(this);
	back.addActionListener(this);
	
	
    setUndecorated(true);
	setTitle("Add New Empoyee");
	setSize(900,600);
	setLayout(null);
	setLocation(300,50);
	setVisible(true);
	
}
@Override
public void actionPerformed(ActionEvent ae) {
	if(ae.getSource()==back) {
		setVisible(false);
		Home.close.setEnabled(true); Home.delete.setEnabled(true);Home.update.setEnabled(true);Home.salary.setEnabled(true); 
		Home.viewAttendance.setEnabled(true);Home.Viewall.setEnabled(true);Home.addDepartment.setEnabled(true);Home.leave.setEnabled(true);
		
	}
	else if(ae.getSource()==add) {
		
		if(tempid.getText().length()>0 && tname.getText().length()>0 && tsalary.getText().length()>0 && taddress.getText().length()>0 &&
		   tphone.getText().length()>0 && temail.getText().length()>0 && ((JTextField)cdob.getDateEditor().getUiComponent()).getText().length()>0 && taadhar.getText().length()>0) {
		
			int i,cnt=0;
			String name=tname.getText();
			for(i=0;i<name.length();i++) {
				if(name.charAt(i)>=48 && name.charAt(i)<=57) {
				cnt++;	
				}
			}
			if(cnt!=0) {
				JOptionPane.showMessageDialog(null, "You can't enter numbers in name");
				tname.setText("");
			}
			boolean result=true;
			try {
				long num=Integer.parseInt(tphone.getText());
			}catch(Exception e) {
				result=false;
			}
			boolean result1=true;
			try {
				//to check aadhar is numeric or not
				long num=Integer.parseInt(taadhar.getText());
			}catch(Exception e) {
				result1=false;
			}
			boolean result2=true;
			try {
				//to check salary is numeric or not
				long num=Integer.parseInt(tsalary.getText());
			}catch(Exception e) {
				result2=false;
			}
			
			if(temail.getText().contains("@gmail.com")) {
				if(result1) {
					if(result2) {
			if(result) {
			
		 name=tname.getText();
		String email=temail.getText();
		String address=taddress.getText();
		String dob=((JTextField)cdob.getDateEditor().getUiComponent()).getText();
		String salary=tsalary.getText();
		String department=(String)ch.getSelectedItem();
		String phone=tphone.getText();
		String empid=tempid.getText();
		String aadhar=taadhar.getText();
		try {
			Conn c=new Conn();
			String query="insert into EmployeeLogin values('"+empid+"','"+name+"','"+dob+"','"+address+"','"+email+"','"+salary+"','"+phone+"','"+aadhar+"','"+department+"')";
		c.stmt.executeUpdate(query);
		JOptionPane.showMessageDialog(null, "Data Added Successfully");
		setVisible(false);
		new Home();
		}catch(Exception e) {
			e.printStackTrace();
		}
		}
			else {
				JOptionPane.showMessageDialog(null, "Please check your phone number");
			}
					}else {
						JOptionPane.showMessageDialog(null, "Please check your Salary");
					}
				}else {
					JOptionPane.showMessageDialog(null, "Please check your Aadhar number");

				}
			}
		else {
			JOptionPane.showMessageDialog(null, "Please check your email(missing @gmail.com)");
		}
		}
	else {
		JOptionPane.showMessageDialog(null,"Please Fill All the Details...!");
	}
	}
}

	public static void main(String[] args) {
		new AddEmployee();
	}
	

}
