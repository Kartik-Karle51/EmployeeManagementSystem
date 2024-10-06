package com.Employee;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class RemoveEmployee extends JFrame implements ActionListener {
	Font f=new Font("Tahoma",Font.BOLD,15);
	Choice choiceempid;
	JButton remove,back;
	JLabel tphone,tname,temail;
	String ch;
	public RemoveEmployee(){
		
		JLabel empid=new JLabel("Employee Id : ");
		empid.setBounds(50,50,150,40);
		empid.setFont(f);
		add(empid);
	
		
		choiceempid=new Choice();
		choiceempid.setBounds(200,50,150,40);
		choiceempid.setFont(f);
		add(choiceempid);
		choiceempid.add("Select");
		try {
			Conn con=new Conn();
			String query="Select * from EmployeeLogin ";
		ResultSet rs=con.stmt.executeQuery(query);
		while(rs.next()) {
			choiceempid.add(rs.getString("Emp_Id"));
		}
		}catch(Exception E) {
			E.printStackTrace();
		}
		
		JLabel name=new JLabel("Employee Name : ");
		name.setBounds(50,110,150,40);
		name.setFont(f);
		add(name);
		
	 tname=new JLabel();
	 tname.setBounds(220,110,200,40);
	 tname.setFont(f);
	 add(tname);
	
	JLabel phone=new JLabel("Phone");
	 phone.setBounds(50,160,150,40);
	 phone.setFont(f);
	 add(phone);
	 
	 tphone=new JLabel();
	 tphone.setBounds(220,160,150,40);
	 tphone.setFont(f);
	 add(tphone);
	 
	 JLabel email=new JLabel("Email");
	 email.setBounds(50,210,150,40);
	 email.setFont(f);
	 add(email);
	 
	 temail=new JLabel();
	 temail.setBounds(220,210,150,40);
	 temail.setFont(f);
	 add(temail);
	 
	 choiceempid.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent ie) {
				ch=choiceempid.getSelectedItem();
				try {
					Conn con=new Conn();
					ResultSet rs=con.stmt.executeQuery("Select * from employeelogin where Emp_Id='"+ch+"'");
					if(rs.next()) {
					tname.setText(rs.getString("Emp_Name"));
					tphone.setText(rs.getString("phone"));
					temail.setText(rs.getString("Email"));
					}
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
	 
	 remove=new JButton("REMOVE");
	 remove.setBounds(90,280,100,40);
	 remove.setFont(f);
	 remove.setBackground(Color.RED);
	 remove.setForeground(Color.WHITE);
	 add(remove);
	 remove.addActionListener(this);
	 
	 back=new JButton("BACK");
	 back.setBounds(220,280,100,40);
	 back.setBackground(Color.RED);
	 back.setForeground(Color.WHITE);
	 back.setFont(f);
	 add(back);
	 back.addActionListener(this);
		
	 ImageIcon i11=new ImageIcon(ClassLoader.getSystemResource("rback.png"));
		Image i22=i11.getImage().getScaledInstance(1120, 630, Image.SCALE_DEFAULT);
		ImageIcon i33=new  ImageIcon(i22);
		JLabel image1=new JLabel(i11);
		image1.setBounds(0,0,1120,630);
		add(image1);
		
		setTitle("Remove Employee");
		setSize(1000,400);
		setLocation(300,150);
		setLayout(null);
		setVisible(true);
	    setUndecorated(true);
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==back) {
			setVisible(false);
			Home.close.setEnabled(true); Home.add.setEnabled(true);Home.update.setEnabled(true);Home.salary.setEnabled(true); 
			Home.viewAttendance.setEnabled(true);Home.Viewall.setEnabled(true);Home.addDepartment.setEnabled(true);Home.leave.setEnabled(true);
			
		}
		else if(e.getSource()==remove) {
			if(choiceempid.getSelectedItem().equals("Select")) {
				JOptionPane.showMessageDialog(null, "You didn't selected any Id ...Please select employee Id  ");
			}
			else {
			try {
				int a=JOptionPane.showConfirmDialog(null,"Are You Sure To Remove ?");
				if(a==JOptionPane.YES_OPTION) {
				Conn con=new Conn();
				con.stmt.executeUpdate("Delete from NewEmployee where empid ='"+ch+"'");

				con.stmt.executeUpdate("Delete from EmployeeLogin where Emp_Id ='"+ch+"'");
								JOptionPane.showInternalMessageDialog(null,"Record Deleted Successfully");
				}
				else {
					tname.setText("");
					tphone.setText("");
					temail.setText("");
				}
			}catch(Exception E) {
				E.printStackTrace();
			}
			}
		}
	}
	
public static void main(String[] args) {
	new RemoveEmployee();
}

}
