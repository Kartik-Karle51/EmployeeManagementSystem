package com.Employee;

import java.awt.*;
import javax.swing.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;
import java.sql.*;

public class ViewDepartment extends JFrame implements ActionListener {
	
	JTable table;
	JButton back;
	
	public ViewDepartment() {
		
		table=new JTable();
		//table.setBounds(0,0,390,270);
		try {
			Conn con=new Conn();
			ResultSet rs=con.stmt.executeQuery("select * from department order by Dep_Id ASC");
			//To convert data of resultset into the table
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		JScrollPane jp=new JScrollPane(table);
		jp.setBounds(20,70,400,430);
		add(jp);
		
		back=new JButton("BACK");
		back.setBounds(320,10,80,30);
		back.setFont(new Font("Tahoma",Font.BOLD,17));
		back.setBackground(new Color(254,145,153));
		back.addActionListener(this);
		add(back);
		
		setTitle("All Departments");
		setSize(450,550);
		setLayout(null);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==back) {
			setVisible(false);
			new AddDepartment();
		}
	}
	
	public static void main(String[] args) {
new ViewDepartment();
	}

}
