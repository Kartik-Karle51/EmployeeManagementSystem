package com.Employee;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class EmployeeLogin extends JFrame implements ActionListener{
	
	ImageIcon i1,i3,i11,i33;
	JTextField tempid,tpass;
	JLabel lempid,lpass;
	JButton login,back,createPassword;
	Font f=new Font("Courier New",Font.BOLD,16);
	Font f1=new Font("Courier New",Font.BOLD,16);
	String empid,pass;
	
	public EmployeeLogin(){ 

    lempid=new JLabel("Employee Id : ");
    lempid.setBounds(0,10,140,30);
    lempid.setFont(f1);
    add(lempid);
    
    tempid=new JTextField();
    tempid.setBounds(140,10,220,30);
    tempid.setBackground(Color.LIGHT_GRAY);
    tempid.setFont(f);
    add(tempid);
    
    lpass=new JLabel("Password : ");
    lpass.setBounds(0,50,120,30);
    lpass.setFont(f1);
    add(lpass);
    
    tpass=new JPasswordField();
    tpass.setBounds(140,50,220,30);
    tpass.setBackground(Color.LIGHT_GRAY);
    add(tpass);
    
    login=new JButton("LOGIN");
    login.setBounds(160,100,80,30);
    login.setBackground(Color.BLACK);
    login.setForeground(Color.WHITE);
    login.setFont(new Font("Courier New",Font.BOLD,14));
    add(login);
   
    back=new JButton("BACK");
    back.setBounds(260,100,80,30);
    back.setBackground(Color.RED);
    back.setForeground(Color.WHITE);
    back.setFont(new Font("Courier New",Font.BOLD,14));
    add(back);
    
    JLabel newuser=new JLabel("<html>"+"If You Are a New Employee...Then Create Password First "+"</html>");
	newuser.setBounds(10,170,400,50);
	newuser.setFont(new Font("Courier New",Font.BOLD,16));
	add(newuser);
	
	createPassword=new JButton("Create Password");
	createPassword.setBounds(150,220,170,30);
	createPassword.setBackground(new Color(0,255,150));
    createPassword.setForeground(Color.BLACK);
    createPassword.setFont(new Font("Courier New",Font.BOLD,13));
    createPassword.addActionListener(this);
	add(createPassword);
	
	   
	    
	    i1=new ImageIcon(ClassLoader.getSystemResource("LoginB.jpg"));
	    Image i2=i1.getImage().getScaledInstance(600,300,Image.SCALE_DEFAULT);
	    i3=new ImageIcon(i2);
	    JLabel image1=new JLabel(i3);
	    image1.setBounds(0,0,600,300);
	    add(image1);
	    
	    
	    i11=new ImageIcon(ClassLoader.getSystemResource("second.jpg"));
	    Image i22=i11.getImage().getScaledInstance(600,400,Image.SCALE_DEFAULT);
	    i33=new ImageIcon(i22);
	    JLabel image=new JLabel(i33);
	    image.setBounds(365,7,600,400);
	    image1.add(image);//here we use object of previous image because we already set a background and we want to add one more image on 
	    				  //frame therefore with the help of background image object we add another image.
	
	 
	    login.addActionListener(this);
	    back.addActionListener(this);
	  
	  //  getContentPane().setBackground(Color.WHITE);
	    setTitle("Employee Login");
	    setSize(600,335);
	    setLayout(null);
	   setResizable(false);
		setLocation(450,200);
		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==login) {
			
			if(tempid.getText().length()>0) {
			try {
				 empid=tempid.getText();
		    	 pass=tpass.getText();
				Conn conn=new Conn();
				String query="select * from NewEmployee where empid ='"+empid+"'and password='"+pass+"'";
				ResultSet rs=conn.stmt.executeQuery(query);
				if(rs.next()) {     //if Employee belongs or present in system i.e id and password is correct
				setVisible(false);
					new EmployeeHome(empid);
				}
				//if either id or password is wrong
				else {
					JOptionPane.showMessageDialog(null, "Invalid Credentials");
					tempid.setText("");
				    tpass.setText("");
					}
			}catch(Exception e) {
				e.printStackTrace();
			}
			}
			//if employee didn't fills the details like id and password
			else {
				JOptionPane.showMessageDialog(null,"Please Fill All Details");
			}
		}
		else if(ae.getSource()==back) {
			setVisible(false);
			new Login();
			
		}
		
		//if employee was recently joined and he didn't have any password then he need to create password first
		else if(ae.getSource()==createPassword) {
			setVisible(false);
			new CreatePassword();
			
		}
	}
	
	
public static void main(String[] args) {
	new EmployeeLogin();
}
}
