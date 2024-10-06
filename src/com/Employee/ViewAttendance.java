package com.Employee;

import java.awt.*;
import javax.swing.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;
import java.sql.*;

public class ViewAttendance extends JFrame{
	String id;
	private JTable table;
	private JButton back,print;
	public ViewAttendance(String id) {
		this.id=id;
		
		table=new JTable();
		try {
			Conn con=new Conn();
			ResultSet rs=con.stmt.executeQuery("select * from Attendance where Emp_Id='"+id+"'");
			
			//To convert data of resultset into the table
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}catch(Exception E) {
			E.printStackTrace();
		}
		
		JScrollPane jp=new JScrollPane(table);
		jp.setBounds(20,60,500,400);
		add(jp);
		
		back=new JButton("BACK");
		back.setBounds(455,10,80,30);
		back.setFont(new Font("Tahoma",Font.BOLD,17));
		back.setBackground(new Color(245,30,70));
		back.setForeground(Color.WHITE);
		back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			setVisible(false);
			new AttendanceEmployee(id);
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
		setTitle("View Attendance");
		setSize(600,500);
		setLayout(null);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public static void main(String[] args) {
		new ViewAttendance("");
	}

}
