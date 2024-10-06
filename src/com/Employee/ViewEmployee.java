package com.Employee;

import java.awt.*;
import javax.swing.*;
import net.proteanit.sql.*;
import java.awt.event.*;
import java.sql.*;


public class ViewEmployee extends JFrame implements ActionListener{
	Choice choiceempid;
	JTable table;
	JButton bsearch,print,update,back;
	Font f1=new Font("SAN_SERIF",Font.BOLD,15);
	Font f=new Font("SAN_SERIF",Font.BOLD,13);
public ViewEmployee() {
	
	getContentPane().setBackground(new Color(100,131,122));
	
	JLabel search=new JLabel("Search By Employee Id : ");
	search.setBounds(20,20,190,20);
	search.setFont(f1);
	add(search);
	
	choiceempid=new Choice();
	choiceempid.setBounds(230,20,150,25);
	choiceempid.setFont(f1);
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
		ResultSet rs=con.stmt.executeQuery("select * from EmployeeLogin");
		//To convert data of resultset into the table
		table.setModel(DbUtils.resultSetToTableModel(rs));
	}catch(Exception e) {
		e.printStackTrace();
	}
	
	
JScrollPane jp=new JScrollPane(table);
jp.setBounds(0,100,1100,600);
add(jp);
	
bsearch=new JButton("Search");
bsearch.setBounds(20,70,80,25);
bsearch.setBackground(new Color(255,50,100));
bsearch.setFont(f);
add(bsearch);

update=new JButton("Update");
update.setBounds(120,70,80,25);
update.setBackground(new Color(255,50,100));
update.setFont(f);
add(update);

print=new JButton("Print");
print.setBounds(220,70,80,25);
print.setBackground(new Color(255,50,100));
print.setFont(f);
add(print);

back=new JButton("Back");
back.setBounds(320,70,80,25);
back.setBackground(new Color(255,50,100));
back.setFont(f);
add(back);

bsearch.addActionListener(this);
back.addActionListener(this);
update.addActionListener(this);
print.addActionListener(this);

setUndecorated(true);
	setTitle("View Employee Details");
	setSize(1100,700);
	setLayout(null);
	setLocationRelativeTo(null);
	setVisible(true);
}

@Override
public void actionPerformed(ActionEvent e) {
	
	 if(e.getSource()==bsearch) {
		String query="select *from EmployeeLogin where Emp_Id='"+choiceempid.getSelectedItem()+"'";
		try {
			Conn con=new Conn();
			ResultSet rs=con.stmt.executeQuery(query);
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}catch(Exception E) {
			E.printStackTrace();
		}
	}
	else if(e.getSource()==print) {
		try {
			table.print();
		}catch(Exception E) {
			E.printStackTrace();
		}
	}
	else if(e.getSource()==update) {
		setVisible(false);
		new UpdateEmployee();
	}
	else if(e.getSource()==back) {
			setVisible(false);
			Home.close.setEnabled(true); Home.delete.setEnabled(true);Home.update.setEnabled(true);Home.salary.setEnabled(true); 
			Home.viewAttendance.setEnabled(true);Home.add.setEnabled(true);Home.addDepartment.setEnabled(true);Home.leave.setEnabled(true);
					}
	
}

	public static void main(String[] args) {
	new ViewEmployee();	
	}
	

}
