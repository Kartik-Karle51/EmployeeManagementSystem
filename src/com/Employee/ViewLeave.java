package com.Employee;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import net.proteanit.sql.DbUtils;

public class ViewLeave extends JFrame{

	String id;
	Choice choiceempid;
	private JTable table;
	private JButton back,print,search;
	Font f=new Font("Tahoma",Font.BOLD,17);
	
	public ViewLeave() {
		
		
		JLabel selectid=new JLabel("Select Id :");
		selectid.setBounds(10,10,100,30);
		selectid.setFont(new Font("Tahoma",Font.BOLD,15));
		getContentPane().add(selectid);
		
		
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
				table=new JTable();
				try {
					Conn con=new Conn();
					ResultSet rs=con.stmt.executeQuery("select * from LeaveRecords");
					//To convert data of resultset into the table
					table.setModel(DbUtils.resultSetToTableModel(rs));
				}catch(Exception E) {
					E.printStackTrace();
				}
				
				JScrollPane jp=new JScrollPane(table);
				jp.setBounds(20,60,500,400);
				getContentPane().add(jp);
				
			
		
		back=new JButton("BACK");
		back.setBounds(455,10,80,30);
		back.setFont(new Font("Tahoma",Font.BOLD,17));
		back.setBackground(new Color(245,30,70));
		back.setForeground(Color.WHITE);
		back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			setVisible(false);
			new CheckLeave();
			}
		});
		getContentPane().add(back);
		
		setTitle("View Leave Records");
		setSize(600,500);
		getContentPane().setLayout(null);
		
	    search = new JButton("Search");
		search.setBackground(new Color(128, 128, 192));
		search.setFont(new Font("Tahoma", Font.BOLD, 15));
		search.setBounds(300, 10, 85, 30);
		search.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String query="select *from LeaveRecords where Emp_Id='"+choiceempid.getSelectedItem()+"'";
				try {
					Conn con=new Conn();
					ResultSet rs=con.stmt.executeQuery(query);
					table.setModel(DbUtils.resultSetToTableModel(rs));
				}catch(Exception E) {
					E.printStackTrace();
				}
			}
		});
		add(search);
		
		
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	
	public static void main(String[] args) {
		new ViewLeave();
	}
}
