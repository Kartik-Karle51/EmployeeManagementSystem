package com.Employee;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class AddDepartment extends JFrame implements ActionListener {

	JTextField tdep;
	JButton add,back,view;
	JLabel ldep;
	int n;
	
	public AddDepartment() {
		
	
	    
		getContentPane().setBackground(new Color(240,255,255));
		ldep=new JLabel("Enter Department Name : ");
		ldep.setBounds(20,85,250,30);
		ldep.setFont(new Font("Courier New ",Font.BOLD,17));
		getContentPane().add(ldep);
		
		tdep=new JTextField();
		tdep.setBounds(230,87,200,30);
		tdep.setFont(new Font("Courier New ",Font.BOLD,17));
		getContentPane().add(tdep);
		
		add=new JButton("ADD");
		add.setBounds(50,155,100,30);
		add.setBackground(new Color(0,255,150));
		add.setFont(new Font("Courier New ",Font.BOLD,17));
		add.addActionListener(this);
		getContentPane().add(add);
		
		view=new JButton("VIEW");
		view.setBounds(180,155,100,30);
		view.setBackground(new Color(0,255,150));
		view.setFont(new Font("Courier New ",Font.BOLD,17));
		view.addActionListener(this);
		getContentPane().add(view);
		
		back=new JButton("BACK");
		back.setBounds(117,225,100,30);
		back.setBackground(Color.RED);
		back.setForeground(Color.WHITE);
		back.setFont(new Font("Courier New ",Font.BOLD,17));
		back.addActionListener(this);
		getContentPane().add(back);
		
		ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("Add.gif"));
	    Image i2=i1.getImage().getScaledInstance(300,300,Image.SCALE_DEFAULT);
	ImageIcon i3=new ImageIcon(i2);
	    JLabel image1=new JLabel(i3);
	    image1.setBounds(190,100,570,300);
	    getContentPane().add(image1);
		 
	    setUndecorated(true);
		setTitle("Add New Department");  
		setSize(600,500);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==add) {
			String name=tdep.getText();
			Conn con=new Conn();
			try {
				ResultSet rs=con.stmt.executeQuery("Select max(Dep_Id) from department");
				if(rs.next()) {
				n=rs.getInt(1)+1;
				}
				if(tdep.getText().length()>0) {
					 rs=con.stmt.executeQuery("Select Dep_Name from department where Dep_Name='"+tdep.getText()+"'");
					if(rs.next()) {
						JOptionPane.showMessageDialog(null, "Department already Exist");
						tdep.setText("");
					}
					else {
				con.stmt.executeUpdate("insert into department values ('"+n+"','"+tdep.getText()+"')");
				JOptionPane.showMessageDialog(null,"Department Added Successfully");
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Please enter Department");
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
		}
		else if(e.getSource()==view) {
			setVisible(false);
			new ViewDepartment();
		}
		else if(e.getSource()==back) {
			setVisible(false);
			Home.close.setEnabled(true); Home.delete.setEnabled(true);Home.update.setEnabled(true);Home.salary.setEnabled(true); 
			Home.viewAttendance.setEnabled(true);Home.Viewall.setEnabled(true);Home.add.setEnabled(true);Home.leave.setEnabled(true);
			
		}
	}

	
	public static void main(String[] args) {
	new AddDepartment();
	}

	
}
