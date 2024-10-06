package com.Employee;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.util.Date;
import javax.swing.*;
import com.toedter.calendar.JDateChooser;

public class ShowSalaryByHR extends JFrame {

	private JButton print,back,check;
	JDateChooser cdob,cdob1 ;
	Date d=new Date();
	String id,name;
	float hours=730.001f,fsalary,finalsalary,workhours;
	int present,absent;
	private JLabel workinghours ,presentdays,absentdays,salary;
	Conn con=new Conn();
	ResultSet rs;
	Choice choiceempid;
	
	public ShowSalaryByHR() {
		
		setSize(500,630);
	    setLayout(null);
		
		JLabel lheading = new JLabel("Salary Details");
		lheading.setForeground(new Color(165, 42, 42));
		lheading.setFont(new Font("Modern No. 20", Font.BOLD, 25));
		lheading.setBounds(174, 0, 203, 57);
		add(lheading);
		
	    
		JLabel lempid = new JLabel("Employee Id  :");
		lempid.setFont(new Font("Modern No. 20", Font.BOLD, 22));
		lempid.setBounds(20, 60, 197, 40);
		add(lempid);
		
		JLabel empname = new JLabel("");
		empname.setFont(new Font("Modern No. 20", Font.BOLD, 22));
		empname.setBounds(240, 110, 197, 40);
		add(empname);
		
		choiceempid=new Choice();
		choiceempid.setBounds(240, 60, 159, 40);
		choiceempid.setFont(new Font("Modern No. 20", Font.BOLD, 25));
		add(choiceempid);
	
		
		
		try {
		String query="Select * from EmployeeLogin ";
		ResultSet rs=con.stmt.executeQuery(query);
		while(rs.next()) {
			choiceempid.add(rs.getString("Emp_Id"));
		}
		}catch(Exception E) {
			E.printStackTrace();
		}
       
		
		JLabel lempname = new JLabel("Employee Name  :");
		lempname.setFont(new Font("Modern No. 20", Font.BOLD, 22));
		lempname.setBounds(20, 110, 197, 40);
		add(lempname);
		
		
		
		empname.setText(name);
		
		JLabel lfrom = new JLabel("From  :");
		lfrom.setFont(new Font("Modern No. 20", Font.BOLD, 22));
		lfrom.setBounds(20, 160, 159, 40);
		add(lfrom);
		
		cdob=new JDateChooser();
		cdob.setBounds(240,160,197,30);
		cdob.setBackground(new Color(177,252,197));
		cdob.setDateFormatString("yyyy-MM-dd");
		cdob.setFont(new Font("Modern No. 20", Font.BOLD, 22));
		add(cdob);
		
		JLabel lto = new JLabel("To  :");
		lto.setFont(new Font("Modern No. 20", Font.BOLD, 22));
		lto.setBounds(20, 210, 128, 40);
		add(lto);
		
		cdob1=new JDateChooser();
		cdob1.setBounds(240,210,197,30);
		cdob1.setBackground(new Color(177,252,197));
		cdob1.setDateFormatString("yyyy-MM-dd");
		cdob1.setFont(new Font("Modern No. 20", Font.BOLD, 22));
		add(cdob1);
		
		check = new JButton("Check");
		check.setBounds(177, 260, 144, 40);
		check.setBackground(new Color(0, 255, 127));
		check.setFont(new Font("Modern No. 20", Font.BOLD, 22));
		add(check);

		check.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(((JTextField)cdob.getDateEditor().getUiComponent()).getText().length()>0 && ((JTextField)cdob1.getDateEditor().getUiComponent()).getText().length()>0) {
					String dob=((JTextField)cdob.getDateEditor().getUiComponent()).getText();
					String dob1=((JTextField)cdob1.getDateEditor().getUiComponent()).getText();
					try {
				rs=con.stmt.executeQuery("Select * from Attendance where emp_id='"+choiceempid.getSelectedItem()+"'and date between '"+dob+"' and '"+dob1+"'");
			while(rs.next()) {
				workhours+=Float.parseFloat(rs.getString("workinghours"));
				if(rs.getString("status").equals("Absent")||rs.getString("status").equals("Leave")) {
					absent++;
				}
				else if(rs.getString("status").equals("Present")) {
					present++;
				}
			}
			
			rs=con.stmt.executeQuery("Select salary from EmployeeLogin where emp_id='"+choiceempid.getSelectedItem()+"'");
			if(rs.next()) {
			fsalary=Float.parseFloat(rs.getString("salary"));
			finalsalary=(fsalary/hours)*workhours;
			}
			
			workinghours.setText(""+workhours);
			presentdays.setText(""+present);
			absentdays.setText(""+absent);
			salary.setText(""+finalsalary);
				}catch(Exception E) {
					E.printStackTrace();
				}
				}else {
					JOptionPane.showMessageDialog(null,"Please Select Date ");
				}
				}
			});
					
		 choiceempid.addItemListener(new ItemListener() {
				
				@Override
				public void itemStateChanged(ItemEvent e) {
					try {
						rs=con.stmt.executeQuery("Select Emp_name from EmployeeLogin where Emp_id='"+choiceempid.getSelectedItem()+"'");
						if(rs.next()) {
						empname.setText(rs.getString("Emp_name"));
						}
					}catch(Exception E) {
						E.printStackTrace();
					}	
				}
			});
			
		JLabel lworkinghours = new JLabel("Total Working Hours  :");
		lworkinghours.setFont(new Font("Modern No. 20", Font.BOLD, 22));
		lworkinghours.setBounds(20, 310, 233, 40);
		add(lworkinghours);
		
		workinghours= new JLabel("");
		workinghours.setFont(new Font("Modern No. 20", Font.BOLD, 22));
		workinghours.setBounds(297, 310, 135, 40);
		add(workinghours);
		
		JLabel lpresentdays = new JLabel("Total Present Days :");
		lpresentdays.setFont(new Font("Modern No. 20", Font.BOLD, 22));
		lpresentdays.setBounds(20, 360, 233, 40);
		add(lpresentdays);
		
		presentdays = new JLabel("");
		presentdays.setFont(new Font("Modern No. 20", Font.BOLD, 22));
		presentdays.setBounds(297, 360, 135, 40);
		add(presentdays);
		
		JLabel labsentdays = new JLabel("Total Absent Days  :");
		labsentdays.setFont(new Font("Modern No. 20", Font.BOLD, 22));
		labsentdays.setBounds(20, 410, 233, 40);
		add(labsentdays);
		
		absentdays = new JLabel("");
		absentdays.setFont(new Font("Modern No. 20", Font.BOLD, 22));
		absentdays.setBounds(297, 410, 135, 40);
		add(absentdays);
		
		JLabel lsalary = new JLabel("Total Salary  :");
		lsalary.setFont(new Font("Modern No. 20", Font.BOLD, 22));
		lsalary.setBounds(20, 460, 233, 40);
		add(lsalary);
		
		
		
		salary = new JLabel("");
		salary.setFont(new Font("Modern No. 20", Font.BOLD, 22));
		salary.setBounds(297, 460, 135, 40);
		add(salary);
		
	    back = new JButton("Back");
		back.setBounds(177, 525, 144, 40);
		back.setBackground(new Color(255, 40, 70));
		back.setForeground(Color.WHITE);
		back.setFont(new Font("Modern No. 20", Font.BOLD, 22));
		back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Home.close.setEnabled(true); Home.delete.setEnabled(true);Home.update.setEnabled(true);Home.add.setEnabled(true); 
				Home.viewAttendance.setEnabled(true);Home.Viewall.setEnabled(true);Home.addDepartment.setEnabled(true);Home.leave.setEnabled(true);

			}
		});
		add(back);
		
		ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("print.jpg"));
		Image i22=i1.getImage().getScaledInstance(500, 680, Image.SCALE_DEFAULT);
		ImageIcon i33=new  ImageIcon(i22);
		JLabel l=new JLabel(i33);
		l.setBounds(0,0,500,590);
		add(l);
		
		setUndecorated(true);
		setLocationRelativeTo(null);
		setVisible(true);
	    
	}
	public static void main(String[] args) {
	new ShowSalaryByHR();
	}

}
