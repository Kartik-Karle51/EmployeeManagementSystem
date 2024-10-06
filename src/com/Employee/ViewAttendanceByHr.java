package com.Employee;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import javax.swing.*;
import net.proteanit.sql.DbUtils;

public class ViewAttendanceByHr extends JFrame{
	String id;
	Choice choiceempid;
	private JTable table;
	private JButton back,print;
	Font f=new Font("Tahoma",Font.BOLD,17);
	public ViewAttendanceByHr() {
		this.id=id;
		
		JLabel selectid=new JLabel("Select Id :");
		selectid.setBounds(10,10,100,30);
		selectid.setFont(new Font("Tahoma",Font.BOLD,15));
		add(selectid);
		
		
		choiceempid=new Choice();
		choiceempid.setBounds(120,13,150,30);
		choiceempid.setFont(f);
		add(choiceempid);
		
		
		/*To fetch Employee id's from table and add to the dropdown*/
		try {
			Conn con=new Conn();
			ResultSet rs=con.stmt.executeQuery("select * from EmployeeLogin");
			while(rs.next()) {
				choiceempid.add(rs.getString("Emp_Id"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		choiceempid.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				table=new JTable();
				try {
					Conn con=new Conn();
					ResultSet rs=con.stmt.executeQuery("select * from Attendance where Emp_Id='"+choiceempid.getSelectedItem()+"'");
					//To convert data of resultset into the table
					table.setModel(DbUtils.resultSetToTableModel(rs));
				}catch(Exception E) {
					E.printStackTrace();
				}
				
				JScrollPane jp=new JScrollPane(table);
				jp.setBounds(20,60,500,400);
				add(jp);
				
			}
		});
		
		back=new JButton("BACK");
		back.setBounds(455,10,80,30);
		back.setFont(new Font("Tahoma",Font.BOLD,17));
		back.setBackground(new Color(245,30,70));
		back.setForeground(Color.WHITE);
		back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			setVisible(false);
			Home.close.setEnabled(true); Home.delete.setEnabled(true);Home.update.setEnabled(true);Home.salary.setEnabled(true); 
			Home.add.setEnabled(true);Home.Viewall.setEnabled(true);Home.addDepartment.setEnabled(true);Home.leave.setEnabled(true);

			}
		});
		add(back);
		
		print=new JButton("PRINT");
		print.setBounds(340,10,90,30);
		print.setFont(new Font("Tahoma",Font.BOLD,17));
		print.setBackground(new Color(0,255,150));
		print.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					table.print();
				}catch(Exception E) {
					E.printStackTrace();
				}
			}
		});
		add(print);
		
	    setUndecorated(true);
		setTitle("View Attendance");
		setSize(600,500);
		setLayout(null);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public static void main(String[] args) {
		new ViewAttendanceByHr();
	}

}
