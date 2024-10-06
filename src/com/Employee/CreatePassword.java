package com.Employee;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class CreatePassword extends JFrame implements ActionListener{

	JLabel password,empid,confirmpassword;
	JTextField tempid,tpassword,tconfirmpassword;
	Font f=new Font("Courier New",Font.BOLD,19);
	Choice c;
	JButton create,back;
	
	public CreatePassword() {
		
		
		
		getContentPane().setBackground(Color.YELLOW);
		
		empid=new JLabel("Select Id :");
		empid.setBounds(114,130,160,30);
		empid.setFont(f);
		getContentPane().add(empid);
		
		c=new Choice();
		c.setFont(f);
		c.setBounds(319,130,150,30);
		c.setBackground(Color.PINK);
		try {
			Conn con=new Conn();
			String query="Select * from EmployeeLogin ";
		ResultSet rs=con.stmt.executeQuery(query);
		while(rs.next()) {
			c.add(rs.getString("Emp_Id"));
		}
		}catch(Exception E) {
			E.printStackTrace();
		}
		c.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				try {
					Conn con=new Conn();
					String query="Select password from NewEmployee where empid= '"+c.getSelectedItem()+"'";
				ResultSet rs=con.stmt.executeQuery(query);
				if(rs.next()) {
					JOptionPane.showMessageDialog(null,"Employee with this Id is already exists");
					tpassword.setEditable(false);
					tconfirmpassword.setEditable(false);
				}
				else {
					tpassword.setEditable(true);
					tconfirmpassword.setEditable(true);
				}
				}catch(Exception E) {
					E.printStackTrace();
				}
			}
		});
		getContentPane().add(c);
		
		password=new JLabel("Password :");
		password.setBounds(114,180,140,30);
		password.setFont(f);
		getContentPane().add(password);
		
		tpassword=new JTextField();
		tpassword.setBounds(319,180,150,30);
		tpassword.setFont(f);
		getContentPane().add(tpassword);
		
		confirmpassword=new JLabel("ReType-Password :");
		confirmpassword.setBounds(114,230,190,30);
		confirmpassword.setFont(f);
		getContentPane().add(confirmpassword);
		
		tconfirmpassword=new JTextField();
		tconfirmpassword.setBounds(319,230,150,30);
		tconfirmpassword.setFont(f);
		getContentPane().add(tconfirmpassword);
		
		create=new JButton("CREATE");
		create.setBounds(159,290,120,30);
		create.setFont(f);
		create.setBackground(new Color(255,153,204));
		create.addActionListener(this);
		getContentPane().add(create);
		
		back=new JButton("BACK");
		back.setBounds(299,290,120,30);
		back.setFont(f);
		back.setForeground(Color.WHITE);
		back.setBackground(Color.RED);
		back.addActionListener(this);
		getContentPane().add(back);

		setSize(600,500);
		setTitle("Create Password");
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==create) {
			
			String pass=tpassword.getText();
			String confirmpass=tconfirmpassword.getText();
			if(pass.length()>0&&confirmpass.length()>0) {
			if(pass.equals(confirmpass)) {
				
			try {
				Conn con=new Conn();
				
				String query="Select password from NewEmployee where empid= '"+c.getSelectedItem()+"'";
				ResultSet rs=con.stmt.executeQuery(query);
				if(rs.next()) {
					JOptionPane.showMessageDialog(null,"Employee with this password is already exists");
				}
				else {
				PreparedStatement ps=con.con.prepareStatement("insert into NewEmployee values (?,?)");
				ps.setString(1, confirmpass);
				ps.setInt(2,Integer.parseInt(c.getSelectedItem()));
				ps.executeUpdate();
				JOptionPane.showMessageDialog(null, "Password Created Successfully");
				setVisible(false);
				new EmployeeLogin();
				}
			}catch(Exception E) {
				E.printStackTrace();
			}
			}
			else {
				JOptionPane.showMessageDialog(null,"Check Your Password Again");
			}
			}else {
				JOptionPane.showMessageDialog(null, "Please Enter password first and then click here");
			}
		}
		else if(e.getSource()==back) {
			setVisible(false);
			new EmployeeLogin();
		}
		
	}
	
	public static void main(String[] args) {
		new CreatePassword();
	}

}
