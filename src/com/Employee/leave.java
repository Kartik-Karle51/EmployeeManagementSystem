package com.Employee;

import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.*;

public class leave extends JFrame implements ActionListener{

	JButton back ,applyleave;
	String id;
	String date;
	Choice ch1;
	
	
	public leave(String empid,String ldate) {
		getContentPane().setLayout(null);
		setSize(400,400);
		id=empid;
		date=ldate;

		 JLabel Empid = new JLabel("Employee Id :");
			Empid.setFont(new Font("Monospaced", Font.BOLD, 18));
			Empid.setBounds(10, 30, 151, 29);
			add(Empid);
			
			JLabel Empidvalue = new JLabel("");
			Empidvalue.setFont(new Font("Monospaced", Font.BOLD, 18));
			Empidvalue.setBounds(171, 30, 100, 29);
			Empidvalue.setText(""+id);
			add(Empidvalue);
		
		 JLabel leavetype = new JLabel("Leave Type :");
			leavetype.setFont(new Font("Monospaced", Font.BOLD, 18));
			leavetype.setBounds(10, 93, 151, 29);
			add(leavetype);
			
			String s[]= {"Half Day","Sick Leave","Maternity Leave","Bereavement Leave","Casual Leave","Paternity Leave","Marriage Leave","Other"};
			
			ch1=new Choice();
			for(String s1:s) {
			ch1.add(s1);
			}
				ch1.setFont(new Font("Monospaced", Font.BOLD, 18));
				ch1.setBounds(161, 93, 214, 28);
				add(ch1);
				
				applyleave = new JButton("Apply For Leave");
				applyleave.setBackground(new Color(0, 204, 153));
				applyleave.setFont(new Font("Tahoma", Font.BOLD, 15));
				applyleave.setBounds(101, 193, 201, 45);
				applyleave.addActionListener(this);
				add(applyleave);
				
				back= new JButton("Back");
				back.setForeground(new Color(255, 255, 255));
				back.setBackground(new Color(255, 0, 51));
				back.setFont(new Font("Tahoma", Font.BOLD, 15));
				back.setBounds(158, 262, 85, 45);
				back.addActionListener(this);
				add(back);
	
	setVisible(true);
	setLocationRelativeTo(null);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
	if(e.getSource()==back) {
		setVisible(false);
		new AttendanceEmployee(""+id);
	}
	else if(e.getSource()==applyleave) {
		Conn con=new Conn();
		try {
			//to check whether employee is present in company or already check outed
			ResultSet rs1=con.stmt.executeQuery("select * from Attendance where Emp_Id ='"+id+"'and date='"+date+"' and not check_out='00:00:00'");
			 if(rs1.next()) {
				JOptionPane.showMessageDialog(null, "Your Entry was already present in the system...You can't apply again for the leave on the same day");
			}
			 else {
			
		ResultSet rs=con.stmt.executeQuery("Select * from LeaveRecords where Emp_Id='"+id+"'and leave_date='"+date+"'");
		
		if(rs.next()) {
			JOptionPane.showMessageDialog(null, "Your Entry was already present in the system...You can't apply again for the leave on the same day");
		rs.close();
		}
		
		else{
			String query;
			PreparedStatement ps;
			rs=con.stmt.executeQuery("Select * from Attendance where Emp_Id='"+id+"'and date='"+date+"' and status='Present'");
			
			if(rs.next()) {
				
				
				//If employee is present and he want half day 
				JOptionPane.showMessageDialog(null,"Applied successfully");
				query="update Attendance set status='Requested For Half Day' where Emp_Id='"+id+"'and date='"+date+"'";
				int  a=con.stmt.executeUpdate(query);
				query="insert into LeaveRecords values(?,?,?,?)";
				ps = con.con.prepareStatement(query);
				ps.setString(1, ""+id);
				ps.setString(2,ch1.getSelectedItem());
				ps.setString(3,date);
				ps.setString(4,"Applied");
				ps.executeUpdate();
				
			}
			else {
				//for updating leave records table after requesting leave
		JOptionPane.showMessageDialog(null,"Applied for Leave");
		query="insert into LeaveRecords values(?,?,?,?)";
		ps = con.con.prepareStatement(query);
		ps.setString(1, ""+id);
		ps.setString(2,ch1.getSelectedItem());
		ps.setString(3,date);
		ps.setString(4,"Applied");
		ps.executeUpdate();
		
		//For updating the attendance status
		String query1="insert into Attendance(Emp_Id,date,check_in,check_out,status,workinghours)values(?,?,?,?,?,?)";
		ps=con.con.prepareStatement(query1);
		ps.setString(1,id);
		ps.setString(2,date);
		ps.setInt(3, 0);
		ps.setInt(4,0);
		ps.setString(5, "Applied for leave");
		ps.setInt(6, 0);
		ps.executeUpdate();
			}
		}
			 }
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		setVisible(false);
		new AttendanceEmployee(id);
		
	}
	
	}
	
	public static void main(String[] args) {
		new leave("empid","ldate");

	}
	
}
