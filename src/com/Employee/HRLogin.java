package com.Employee;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;

public class HRLogin extends JFrame implements ActionListener,KeyListener{
	ImageIcon i1,i3,i11,i33;
	JTextField tuser;
	JPasswordField tpass;
	JLabel luser,lpass;
	JButton login,back;
	Font f=new Font("serif",Font.BOLD,13);
	Font f1=new Font("serif",Font.BOLD,15);
	public HRLogin(){
		getContentPane().setBackground(Color.WHITE);
		    luser=new JLabel("Username : ");
		    luser.setBounds(21,26,100,30);
		    luser.setFont(f1);
		    add(luser);
		    
		    lpass=new JLabel("Password : ");
		    lpass.setBounds(21,78,100,30);
		    lpass.setFont(f1);
		    add(lpass);
		    
		    tuser=new JTextField();
		    tuser.setBounds(100,30,220,30);
		    tuser.setFont(f);
		    tuser.setBackground(Color.LIGHT_GRAY);
		    add(tuser);
		    
		    tpass=new JPasswordField();
		    tpass.setBounds(100,80,220,30);
		    tpass.setBackground(Color.LIGHT_GRAY);
		    tpass.setFont(f);
		    add(tpass);
		    
		    login=new JButton("LOGIN");
		    login.setBounds(120,150,80,30);
		    login.setBackground(Color.BLACK);
		    login.setForeground(Color.WHITE);
		    login.setFont(f);
		    add(login);
		   
		    back=new JButton("BACK");
		    back.setBounds(220,150,80,30);
		    back.setBackground(Color.RED);
		    back.setForeground(Color.WHITE);
		    back.setFont(f);
		    add(back);
		    	    
	  
	    i11=new ImageIcon(ClassLoader.getSystemResource("lock.gif"));
	    Image i22=i11.getImage().getScaledInstance(330,300,Image.SCALE_DEFAULT);
	    i33=new ImageIcon(i22);
	    JLabel image=new JLabel(i33);
	    image.setBounds(290,5,300,220);
	    add(image);
	    
	    
	    login.addActionListener(this);
	    tpass.addKeyListener(this);
	    tuser.addKeyListener(this);
	    back.addActionListener(this);
	    
	    
	    setTitle("HR Login");
	    setSize(600,300);
	    setLayout(null);
	   setResizable(false);
		setLocation(450,200);
		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==login) {
			if(tuser.getText().length()>0 && tpass.getText().length()>0) {
		
			try {
				String username=tuser.getText();
				String password=tpass.getText();
				Conn conn=new Conn();
				String query="select * from HRLogin where username ='"+username+"' and password = '"+password+"'";
				ResultSet rs=conn.stmt.executeQuery(query);
				if(rs.next()) {
				setVisible(false);
					new Home();
				}
				else {
					JOptionPane.showMessageDialog(null, "Invalid Username and Password");
					tuser.setText("");
					tpass.setText("");
					}
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else {
			JOptionPane.showMessageDialog(null,"Please Fill Both Texfields");
		}
		}
		else if(ae.getSource()==back) {
			setVisible(false);
			new Login();
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode()==KeyEvent.VK_ENTER) {
			if(tuser.getText().length()>0 && tpass.getText().length()>0) {
			try {
				String username=tuser.getText();
				String password=tpass.getText();
				Conn conn=new Conn();
				String query="select * from HRLogin where username ='"+username+"' and password = '"+password+"'";
				ResultSet rs=conn.stmt.executeQuery(query);
				if(rs.next()) {
				setVisible(false);
					new Home();
				}
				else {
					JOptionPane.showMessageDialog(null, "Invalid Username and Password");
					tuser.setText("");
					tpass.setText("");
					}
				
			}catch(Exception ae) {
				ae.printStackTrace();
			}
		}
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	public static void main(String[] args) {
		new HRLogin();
	}
	
}
