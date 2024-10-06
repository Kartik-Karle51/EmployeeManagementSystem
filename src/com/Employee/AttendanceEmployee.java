package com.Employee;

import java.awt.*;
import javax.swing.*;
import java.time.*;
import java.time.format.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;

public class AttendanceEmployee extends JFrame implements ActionListener{
	
	JButton submit,back,checkin,checkout,view;
	Choice ch;
	String id;
	int flag=0;
	JLabel lcheckin2,lcheckout2,ldate,lcheckin1,lcheckout1;
	static int inminutes,inhours,outminutes,outhours;
	private double checkouttime,checkintime,workinghours;
	private DateTimeFormatter formatter=DateTimeFormatter.ofPattern("HH:mm:ss");
	Conn c=new Conn();
	PreparedStatement ps;
	
	public AttendanceEmployee(String id) {
	this.id=id;
		setTitle("Attendance");
		setLayout(null);
		
		JLabel empid = new JLabel("Employee Id :");
		empid.setFont(new Font("Monospaced", Font.BOLD, 18));
		empid.setBounds(107, 59, 171, 29);
		add(empid);
		
		JLabel lempid = new JLabel(id);
		lempid.setFont(new Font("Monospaced", Font.BOLD, 18));
		lempid.setBounds(330, 59, 156, 29);
	    add(lempid);
		
		JLabel date = new JLabel("Date :");
		date.setFont(new Font("Monospaced", Font.BOLD, 18));
		date.setBounds(107, 109, 171, 29);
		add(date);
		
		 ldate=new JLabel();
		ldate.setBounds(330,109,150,29);
		ldate.setFont(new Font("Monospaced", Font.BOLD, 18));
		LocalDate obj=LocalDate.now();
		ldate.setText(""+obj);
		add(ldate);
		
		 lcheckin1 = new JLabel("Check IN Time :");
		lcheckin1.setFont(new Font("Monospaced", Font.BOLD, 18));
		lcheckin1.setBounds(107, 199, 171, 29);
		add(lcheckin1);
		
		 lcheckout1 = new JLabel("Check Out Time : ");
		lcheckout1.setFont(new Font("Monospaced", Font.BOLD, 18));
		lcheckout1.setBounds(107, 244, 195, 29);
		add(lcheckout1);
		
		checkin = new JButton("Check In");
		checkin.setFont(new Font("Monospaced", Font.BOLD, 18));
		checkin.setBounds(509, 199, 128, 28);
		checkin.addActionListener(this);
		add(checkin);
		
	    checkout = new JButton("Check Out");
		checkout.setFont(new Font("Monospaced", Font.BOLD, 18));
		checkout.setBounds(509, 244, 144, 29);
		checkout.addActionListener(this);
		add(checkout);
		
		JLabel status = new JLabel("Status :");
		status.setFont(new Font("Monospaced", Font.BOLD, 18));
		status.setBounds(107, 154, 99, 25);
		add(status);
		
		
		ch=new Choice();
		ch.setFont(new Font("Monospaced", Font.BOLD, 18));
		ch.setBounds(330, 155, 144, 29);
		ch.add("Present");ch.add("Absent");ch.add("Leave");
	    add(ch);
	    ch.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
			if(ch.getSelectedItem().equals("Absent")) {
				checkin.setEnabled(false);
				checkout.setEnabled(false);
				lcheckin1.setVisible(true);
				 lcheckout1.setVisible(true);
				 lcheckin2.setVisible(true);
				 lcheckout2.setVisible(true);
				 checkin.setVisible(true);
				 checkout.setVisible(true);
			}
			if(ch.getSelectedItem().equals("Leave")) {

				setVisible(false);
				new leave(id,ldate.getText());
				
				 }
			if(ch.getSelectedItem().equals("Present")) {
				try {
				 String query="select check_in from Attendance where Emp_Id=? and date=?";
					ps=c.con.prepareStatement(query);
					ps.setString(1, id);
					ps.setString(2, ldate.getText());
					ResultSet rs=ps.executeQuery();
					if(rs.next()) {
						JOptionPane.showMessageDialog(null, "Your entry is already present but still you are clicking check in button");
						setVisible(false);
						new EmployeeHome(id);
					}
					else {
				checkin.setEnabled(true);
				checkout.setEnabled(true);
				lcheckin1.setVisible(true);
				 lcheckout1.setVisible(true);
				 lcheckin2.setVisible(true);
				 lcheckout2.setVisible(true);
				 checkin.setVisible(true);
				 checkout.setVisible(true);
			}
			}
			catch(Exception E) {
				E.printStackTrace();
			}
			}
			}
		});

		 lcheckin2 = new JLabel("Not CheckIn");
		lcheckin2.setFont(new Font("Monospaced", Font.BOLD, 18));
		lcheckin2.setBounds(330, 199, 154, 28);
		add(lcheckin2);
		
		 lcheckout2 = new JLabel("Not CheckOut");
		lcheckout2.setFont(new Font("Monospaced", Font.BOLD, 18));
		lcheckout2.setBounds(330, 244, 154, 29);
		add(lcheckout2);
		
		submit = new JButton("Submit");
		submit.setForeground(Color.BLACK);
		submit.setBackground(new Color(0,183,190));
		submit.setFont(new Font("Monospaced", Font.BOLD, 18));
		submit.setBounds(233, 314, 106, 35);
		submit.addActionListener(this);
		add(submit);
		
		view = new JButton("View My Attendance");
		view.setForeground(Color.BLACK);
		view.setBackground(new Color(0,183,190));
		view.setFont(new Font("Monospaced", Font.BOLD, 18));
		view.setBounds(260, 368, 236, 35);
		view.addActionListener(this);
		add(view);
		
		
		back = new JButton("Back");
		back.setForeground(Color.WHITE);
		back.setBackground(new Color(255, 0, 0));
		back.setFont(new Font("Monospaced", Font.BOLD, 18));
	    back.setBounds(418, 314, 106, 35);
	    back.addActionListener(this);
		add(back);
		
		ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("print.jpg"));
		Image i22=i1.getImage().getScaledInstance(732,457, Image.SCALE_DEFAULT);
		ImageIcon i33=new  ImageIcon(i22);
		JLabel l=new JLabel(i33);
		l.setBounds(0,0,732,457);
		add(l);
		
		setUndecorated(true);
		setSize(732,457);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		try {
		if(e.getSource()==back) {
			setVisible(false);
			EmployeeHome.back.setEnabled(true); EmployeeHome.details.setEnabled(true);EmployeeHome.salary.setEnabled(true);EmployeeHome.logout.setEnabled(true);
		}
		 if(e.getSource()==checkin) {
			 try {
					String query="select check_in from Attendance where Emp_Id=? and date=?";
					ps=c.con.prepareStatement(query);
					ps.setString(1, id);
					ps.setString(2, ldate.getText());
					ResultSet rs=ps.executeQuery();
					if(rs.next()) {
						JOptionPane.showMessageDialog(null,"Your entry is already present but still you are clicking check in button");
						setVisible(false);
						new EmployeeHome(id);
					}else {
			inhours=LocalTime.now().getHour();
			inminutes=LocalTime.now().getMinute();
			lcheckin2.setText(""+inhours+":"+inminutes);
			markAttendance("In");
		}
			 }
			 catch(Exception ae) {
					ae.printStackTrace();
			 }
			 }
		 
		 if(e.getSource()==checkout) {
			 String query1="select check_out from Attendance where Emp_Id=? and date=?";
				ps=c.con.prepareStatement(query1);
				ps.setString(1, id);
				ps.setString(2, ldate.getText());
				ResultSet rs=ps.executeQuery();
				if(!rs.next()) {
					JOptionPane.showMessageDialog(null, "Please check in first");
					setVisible(false);
					new EmployeeHome(id);
					
				}
				else {
					String query2="select* from Attendance where status='Requested For Half Day'and Emp_Id='"+id+"'and date='"+ldate.getText()+"'";
					 rs=c.stmt.executeQuery(query2);
					if(rs.next()) {
						JOptionPane.showMessageDialog(null, "Sorry you can't check out now");
						
					}
					else {
					 String query="select check_in from Attendance where Emp_Id=? and date=?";
						ps=c.con.prepareStatement(query);
						ps.setString(1, id);
						ps.setString(2, ldate.getText());
						 rs=ps.executeQuery();
						if(rs.next()) {
					outhours=LocalTime.now().getHour();
					outminutes=LocalTime.now().getMinute();
					lcheckout2.setText(""+outhours+":"+outminutes);	
					workinghours=(outhours-inhours)+((outminutes-inminutes)/60.0);
					markAttendance("Out");
				}
					}
			}	
		}
		 
		 
		 if(e.getSource()==submit) {
			 if(ch.getSelectedItem().equals("Present")){
				
					
				 if(lcheckin2.getText().equals("Not CheckIn") && lcheckout2.getText().equals("Not CheckOut")) {
					 JOptionPane.showMessageDialog(null, "Please Make your entry first");
				
				 }
				 else if(lcheckout2.getText().equals("Not CheckOut")&& !lcheckin2.getText().equals("Not CheckIn")) {
				
					 JOptionPane.showMessageDialog(null, "Please Check out first");
				 }
					
			 } else if(ch.getSelectedItem().equals("Absent")){
				 String query1="select check_in from Attendance where Emp_Id=? and date=?";
					ps=c.con.prepareStatement(query1);
					ps.setString(1, id);
					ps.setString(2, ldate.getText());
					ResultSet rs=ps.executeQuery();
					if(rs.next()) {
						JOptionPane.showMessageDialog(null, "Your entry is already present but still you are clicking check in button");
						setVisible(false);
						new EmployeeHome(id);
					}
					else {
					String query="insert into Attendance values(?,?,?,?,?,?)";
					ps=c.con.prepareStatement(query);
					ps.setString(1,id);
					ps.setString(2, ldate.getText());
					ps.setString(3, ch.getSelectedItem());
					ps.setString(4, null);
					ps.setString(5, null);
					ps.setDouble(6, 0);
					ps.executeUpdate();
					}
			 }
			 }
		 
		}catch(Exception E) {
			E.printStackTrace();
		}
		
		if(e.getSource()==view) {
			setVisible(false);
			new ViewAttendance(id);
		}
	}
	
	public void markAttendance(String status) {
		try {
			
			if(status.equals("In")) {
			String query="insert into Attendance(Emp_Id,date,check_in,status)values(?,?,?,?)";
			ps=c.con.prepareStatement(query);
			ps.setString(1,id);
			ps.setString(2, ldate.getText());
			ps.setString(3, lcheckin2.getText());
			ps.setString(4, ch.getSelectedItem());
			ps.executeUpdate();
		
			}
			else {
				String query="update Attendance set check_out=?,workinghours=? where Emp_Id=? and date=?";
				ps=c.con.prepareStatement(query);
				ps.setString(1, lcheckout2.getText());
				ps.setDouble(2, workinghours);
				ps.setString(3, id);
				ps.setString(4,ldate.getText());
				ps.executeUpdate();
			}
			
			
		}catch(Exception E) {
			E.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
	new AttendanceEmployee("");
	}

	
}
