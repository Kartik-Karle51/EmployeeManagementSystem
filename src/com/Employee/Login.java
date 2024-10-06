package com.Employee;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class Login extends JFrame implements ActionListener{
	JLabel heading;
	JButton hr,employee,close;
	JPanel p1;
public Login() {
	
	getContentPane().setLayout(null);
	
	heading=new JLabel("Login As...?");
	heading.setFont(new Font("Lucida Console", Font.BOLD, 27));
	heading.setForeground(Color.BLACK);
	heading.setBounds(160,64,200,30);
	getContentPane().add(heading);
	
	 hr=new JButton("HR");
	hr.setFont(new Font("Courier New",Font.BOLD,18));
	hr.setBounds(191,150,120,50);
	hr.setBackground(new Color(0,0,0));
	hr.setForeground(Color.WHITE);
	getContentPane().add(hr);
	
	employee=new JButton("EMPLOYEE");
	employee.setFont(new Font("Courier New",Font.BOLD,18));
	employee.setBounds(183,250,150,50);
	employee.setForeground(Color.WHITE);
	employee.setBackground(new Color(0,0,0));
	getContentPane().add(employee);
	
	close=new JButton("CLOSE");
	close.setFont(new Font("Courier New",Font.BOLD,17));
	close.setBounds(337,351,120,50);
	close.setBackground(new Color(255, 10, 10));
	close.setForeground(Color.WHITE);
	getContentPane().add(close);
	
	
	hr.addActionListener(this);
	employee.addActionListener(this);
	close.addActionListener(this);
	
	ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("olors.png"));
	JLabel image1=new JLabel(i1);
	image1.setBounds(0,0,500,450);
	getContentPane().add(image1);
	
	setVisible(true);
	setSize(500,450);
	setResizable(false);
	setTitle("Login As");
	setLocation(550,250);
	
}
	
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==hr) {
			setVisible(false);
			new HRLogin();
		}
		else if(ae.getSource()==employee) {
			setVisible(false);
			new EmployeeLogin();
		}
		else if(ae.getSource()==close) {
			System.exit(0);
		}
		
	}
	public static void main(String[] args) {
		new Login();
	}
}
