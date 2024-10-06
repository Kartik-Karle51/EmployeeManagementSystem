package com.Employee;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;


public class UpdateEmployee extends JFrame implements ActionListener {
	
Choice choiceempid,ch;
JTextField empid,tname,temail,tdob,taadhar,tsalary,tphone,tdep;
JTextArea taddress;
JButton update,back,changedep;
Font f=new Font("SAN_SERIF",Font.BOLD,20);

String name,dob,address,email,department;
Long salary,aadhar,phone;
	public UpdateEmployee() {

		getContentPane().setBackground(new Color(0,204,204));
		
		JLabel 	heading=new JLabel("Update Employee Details");
		heading.setBounds(320,20,500,50);
		heading.setFont(new Font("serif",Font.BOLD,25));
		add(heading);
		
		JLabel lempid=new JLabel("Select Employee Id : ");
		lempid.setBounds(250,90,200,30);
		lempid.setFont(f);
		add(lempid);
		
		choiceempid =new Choice();
		choiceempid.setBounds(470,90,150,25);
		choiceempid.setFont(f);
		choiceempid.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent ie) {
				String ch=choiceempid.getSelectedItem();
				try {
					Conn con=new Conn();
					ResultSet rs=con.stmt.executeQuery("Select * from employeelogin where Emp_Id='"+ch+"'");
					if(rs.next()) {
					name=rs.getString(2);
					dob=rs.getString(3);
					address=rs.getString(4);
					email=rs.getString(5);
					salary =rs.getLong(6);
					phone=rs.getLong(7);
					aadhar=rs.getLong(8);
				    department=rs.getString(9);
				    
				    tname.setText(""+name);
				    temail.setText(""+email);
				    tdob.setText(""+dob);
					taddress.setText(""+address);
					taadhar.setText(""+aadhar);
					tsalary.setText(""+salary);
					tphone.setText(""+phone);
					tdep.setText(""+department);
					}
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
		add(choiceempid);
		
		
		try {
			Conn con=new Conn();
			ResultSet rs=con.stmt.executeQuery("Select Emp_Id from EmployeeLogin ");
			while(rs.next()) {
				choiceempid.add(rs.getString(1));
			}
			con.con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		JLabel lname=new JLabel("Name : ");
		lname.setBounds(50,160,150,30);
		lname.setFont(f);
		add(lname);
		
		tname=new JTextField();
		tname.setBounds(170,160,250,30);
		tname.setBackground(Color.GRAY);
		tname.setFont(f);
		tname.setEditable(false);
		add(tname);
		
		JLabel lemail=new JLabel("Email : ");
		lemail.setBounds(50,210,150,30);
		lemail.setFont(f);
		add(lemail);
		
		temail=new JTextField();
		temail.setBounds(170,210,250,30);
		temail.setBackground(Color.GRAY);
		temail.setFont(f);
		temail.setEditable(false);
		add(temail);
		
		
		JLabel ldob=new JLabel("Birth Date : ");
		ldob.setBounds(50,260,150,30);
		ldob.setFont(f);
		add(ldob);
		
		tdob=new JTextField();
		tdob.setBounds(170,260,250,30);
		tdob.setBackground(Color.GRAY);
		tdob.setFont(f);
		tdob.setEditable(false);
		add(tdob);
		
		JLabel laddress=new JLabel("Address : ");
		laddress.setBounds(50,310,150,30);
		laddress.setFont(f);
		add(laddress);
		
		taddress=new JTextArea();
		taddress.setBounds(170,310,250,70);
		taddress.setBackground(new Color(177,252,197));
		taddress.setFont(f);;
		add(taddress);
		
		JLabel laadhar=new JLabel("Aadhar No : ");
		laadhar.setBounds(440,160,150,30);
		laadhar.setFont(f);
		add(laadhar);
		
		taadhar=new JTextField();
		taadhar.setBounds(590,160,250,30);
		taadhar.setBackground(Color.GRAY);
		taadhar.setFont(f);
		taadhar.setEditable(false);
		add(taadhar);
		
		JLabel lsalary=new JLabel("Salary : ");
		lsalary.setBounds(440,210,150,30);
		lsalary.setFont(f);
		add(lsalary);
		
		tsalary=new JTextField();
		tsalary.setBounds(590,210,250,30);
		tsalary.setBackground(new Color(177,252,197));
		tsalary.setFont(f);
		add(tsalary);
		
		JLabel lphone=new JLabel("Phone : ");
		lphone.setBounds(440,260,150,30);
		lphone.setFont(f);
		add(lphone);
		
		tphone=new JTextField();
		tphone.setBounds(590,260,250,30);
		tphone.setBackground(new Color(177,252,197));
		tphone.setFont(f);
		add(tphone);
		
		JLabel ldepartment=new JLabel("Department : ");
		ldepartment.setBounds(440,310,150,30);
		ldepartment.setFont(f);
		add(ldepartment);
		
		tdep=new JTextField();
		tdep.setBounds(590,310,250,30);
		tdep.setBackground(new Color(177,252,197));
		tdep.setFont(f);
		add(tdep);
		
		changedep=new JButton("Change Department");
		changedep.setBounds(620,350,200,30);
		changedep.setBackground(new Color(177,252,197));
		changedep.setFont(new Font("Tohma",Font.BOLD,14));
		add(changedep);;

		
		update=new JButton("UPDATE");
		update.setBounds(290,450,150,40);
		update.setBackground(Color.BLACK);
		update.setForeground(Color.WHITE);
		add(update);
		
		back=new JButton("BACK");
		back.setBounds(490,450,150,40);
		back.setBackground(Color.RED);
		back.setForeground(Color.WHITE);
		add(back);
		
		changedep.addActionListener(this);
		update.addActionListener(this);
		back.addActionListener(this);
		
		setUndecorated(true);
		
		setSize(900,600);
		setLayout(null);
		setLocation(300,50);
		setVisible(true);
	    
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String dep;
		if(e.getSource()==back) {
			setVisible(false);
			Home.close.setEnabled(true); Home.delete.setEnabled(true);Home.add.setEnabled(true);Home.salary.setEnabled(true); 
			Home.viewAttendance.setEnabled(true);Home.Viewall.setEnabled(true);Home.addDepartment.setEnabled(true);Home.leave.setEnabled(true);
			
		}
		else if(e.getSource()==update) {
			if(taddress.getText().length()<=0 || tsalary.getText().length()<=0 || tphone.getText().length()<=0) {
				JOptionPane.showMessageDialog(null, "Something went wrong ...Please check all fields");
				
			}
			else {
				if(e.getSource()==changedep) 
				{
					dep=ch.getSelectedItem();
				}
				else {
					dep=tdep.getText();
				}
				
				boolean result=true;
				try {
					long num=Integer.parseInt(tphone.getText());
				}catch(Exception E) {
					result=false;
				}
				
				
				boolean result2=true;
				try {
					//to check salary is numeric or not
					long num=Integer.parseInt(tsalary.getText());
				}catch(Exception E) {
					result2=false;
				}
				
				if(result2) {
					if(result) {
			try {
				Conn con=new Conn();
				String query="UPDATE EmployeeLogin set address=?,salary=?,phone=?,department=? where Emp_Id=?";
				PreparedStatement ps=con.con.prepareStatement(query);
				ps.setString(1, taddress.getText());
				ps.setString(2, tsalary.getText());
				ps.setString(3,tphone.getText());
				ps.setString(4, dep);
				ps.setString(5, choiceempid.getSelectedItem());
				ps.executeUpdate();
				JOptionPane.showMessageDialog(null, "Record Updated Successfully...!");
				taddress.setText("");
				tsalary.setText("");
				tphone.setText("");
				taadhar.setText("");
				tdob.setText("");
				tname.setText("");
				temail.setText("");
			}catch(Exception E) {
				E.printStackTrace();
			}
					}else {
						JOptionPane.showMessageDialog(null, "Please check your Phone number");
					}
				}else {
					JOptionPane.showMessageDialog(null, "Please check your Salary");
				}
			}
		}
		else if(e.getSource()==changedep) {
			tdep.setVisible(false);
		ch=new Choice();
			ch.setBounds(590,310,270,35);
			ch.setBackground(new Color(177,252,197));
			ch.setFont(new Font("serif",Font.BOLD,17));
			
			try {
				Conn con=new Conn();
				ResultSet rs=con.stmt.executeQuery("Select Dep_Name from Department");
				while(rs.next()) {
					ch.add(rs.getString(1));
				}
				add(ch);
			}catch(Exception E) {
				E.printStackTrace();
			}
			
		}
	}
	
	
	
	
	
	public static void main(String[] args){
		new UpdateEmployee();
	}
}
