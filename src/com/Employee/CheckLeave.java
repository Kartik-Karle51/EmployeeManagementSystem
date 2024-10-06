package com.Employee;

import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.*;

import net.proteanit.sql.DbUtils;

public class CheckLeave extends JFrame implements ActionListener{
	String id;
	Choice choiceempid;
	private JTable table;
	private JButton back,approve,reject,view;
	Font f=new Font("Tahoma",Font.BOLD,17);
	JLabel empid1,type1,date1,name1;
	Conn con=new Conn();
	ResultSet rs;
	private DateTimeFormatter formatter=DateTimeFormatter.ofPattern("HH:mm:ss");
	
	
	public CheckLeave() {
		JLabel selectid=new JLabel("Select Id :");
		selectid.setBounds(10,10,100,30);
		selectid.setFont(new Font("Tahoma",Font.BOLD,15));
		add(selectid);
		
		JLabel empid = new JLabel("Employee Id :");
		empid.setBounds(65, 100, 145, 20);
		empid.setFont(f);
		add(empid);
		
		JLabel name = new JLabel("Name :");
		name.setBounds(65, 150, 145, 20);
		name.setFont(f);
		add(name);
		
		JLabel date = new JLabel("Date :");
		date.setBounds(65, 200, 145, 20);
		date.setFont(f);
		add(date);
		
		JLabel type = new JLabel("Leave Type : ");
		type.setBounds(65, 250, 145, 20);
		type.setFont(f);
		add(type);
		
		 empid1 = new JLabel("");
		empid1.setBounds(220, 100, 145, 20);
		empid1.setFont(f);
		add(empid1);
		
		name1 = new JLabel("");
		name1.setBounds(220, 150, 145, 20);
		name1.setFont(f);
		add(name1);
		
		date1 = new JLabel("");
		date1.setBounds(220, 200, 145, 20);
		date1.setFont(f);
		add(date1);
		
		type1 = new JLabel("");
		type1.setBounds(220, 250, 145, 20);
		type1.setFont(f);
		add(type1);
		
		
		choiceempid=new Choice();
		choiceempid.setBounds(120,13,150,30);
		choiceempid.setFont(f);
		
		/*To fetch Employee id's from table and add to the dropdown*/
		try {
		
			 rs=con.stmt.executeQuery("select * from EmployeeLogin");
			while(rs.next()) {
				choiceempid.add(rs.getString("Emp_Id"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		choiceempid.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent i) {
				try {
					
					 rs=con.stmt.executeQuery("select * from LeaveRecords where Emp_Id ='"+choiceempid.getSelectedItem()+"' and status='Applied'");
					if(rs.next()) {
						empid1.setText(rs.getString("Emp_Id"));
						date1.setText(rs.getString("leave_date"));
						type1.setText(rs.getString("type"));
						rs=con.stmt.executeQuery("Select Emp_Name from EmployeeLogin where Emp_Id='"+choiceempid.getSelectedItem()+"'");
						if(rs.next()) {
							name1.setText(rs.getString("Emp_Name"));
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "Record does not found");
					}
					
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
		add(choiceempid);
		
		

		
		approve=new JButton("Approve");
		approve.setBounds(45,347,110,30);
		approve.setFont(f);
		approve.setBackground(new Color(0,255,150));
		approve.addActionListener(this);
		approve.setForeground(Color.BLACK);
		add(approve);
		
	    reject = new JButton("Reject");
		reject.setBackground(new Color(0, 255, 150));
		reject.addActionListener(this);
		reject.setFont(f);
		reject.setForeground(Color.BLACK);
		reject.setBounds(193, 347, 110, 30);
		add(reject);
		
		view=new JButton("View Leave Records");
		view.setBounds(340,347,200,30);
		view.setFont(f);
		view.setBackground(new Color(0,255,150));
		view.setForeground(Color.BLACK);
		view.addActionListener(this);
		add(view);
		
		back=new JButton("BACK");
		back.setBounds(470,410,80,30);
		back.setFont(f);
		back.setBackground(new Color(255,30,30));
		back.setForeground(Color.WHITE);
		back.addActionListener(this);
		add(back);
		
		
	    setUndecorated(true);
		setTitle("View Leave Records");
		setSize(600,500);
		setLayout(null);
		setLocationRelativeTo(null);
		setVisible(true);
	
	}
	
	
	
	public static void main(String[] args) {
	new CheckLeave();	
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==back) {
			setVisible(false);
			Home.close.setEnabled(true); Home.delete.setEnabled(true);Home.update.setEnabled(true);Home.salary.setEnabled(true); 
			Home.viewAttendance.setEnabled(true);Home.Viewall.setEnabled(true);Home.addDepartment.setEnabled(true);Home.add.setEnabled(true);
			
		}
		
		else if(e.getSource()==view) {
			setVisible(false);
			new ViewLeave();
		}
		
		
		else if(e.getSource()==approve) {
			if(empid1.getText().length()>0 && type1.getText().length()>0 && date1.getText().length()>0 && name1.getText().length()>0) {
			JOptionPane.showMessageDialog(null, "Leave Approved");
			try {
				String query;
				if(type1.getText().equals("Half Day")) {
					query="update Attendance set status='"+type1.getText()+"',check_out='"+LocalTime.now()+"' workinghours='0' where Emp_Id='"+empid1.getText()+"' and date='"+date1.getText()+"' ";
				}
				else {
				query="update Attendance set status='"+type1.getText()+"' where Emp_Id='"+empid1.getText()+"' and date='"+date1.getText()+"' ";
				
				}
				PreparedStatement ps=con.con.prepareStatement(query);
				
				ps.executeUpdate();
				
				
				
				String query1="update LeaveRecords set status='Approved' where Emp_Id='"+empid1.getText()+"'and leave_date='"+date1.getText()+"'";
			    ps=con.con.prepareStatement(query1);
				ps.executeUpdate();
				
				empid1.setText("");
				name1.setText("");
				type1.setText("");
				date1.setText("");
			}catch(Exception E) {
				E.printStackTrace();
			}
			}
			else {
				JOptionPane.showMessageDialog(name1, "You didn't selected any id ");
			}
		}

		else if(e.getSource()==reject) {
				if(empid1.getText().length()>0 && type1.getText().length()>0 && date1.getText().length()>0 && name1.getText().length()>0) {
				JOptionPane.showMessageDialog(null, "Leave Rejected");
				try {
					String query;
					PreparedStatement ps;
					if(type1.getText().equals("Half Day")) {
						query="update Attendance set status='Present' where Emp_Id='"+empid1.getText()+"' and date='"+date1.getText()+"' ";
						ps=con.con.prepareStatement(query);
						ps.executeUpdate();
					}
					else {
					query="insert into Attendance values(?,?,?,?,?,?)";
					ps=con.con.prepareStatement(query);
					ps.setString(1,empid1.getText());
					ps.setString(2,date1.getText());
					ps.setString(3,"Absent");
					ps.setInt(4,0);
					ps.setInt(5,0);
					ps.setInt(6,0);
					
					ps.executeUpdate();
					
					}
					
					query="update LeaveRecords set status='Rejected' where Emp_Id='"+empid1.getText()+"'and leave_date='"+date1.getText()+"'";
				    ps=con.con.prepareStatement(query);
					ps.executeUpdate();
					
					empid1.setText("");
					name1.setText("");
					type1.setText("");
					date1.setText("");
				
				}catch(Exception E) {
					E.printStackTrace();
				}
				}else {
					JOptionPane.showMessageDialog(name1, "You didn't selected any id ");
				}
		}
	}
}
