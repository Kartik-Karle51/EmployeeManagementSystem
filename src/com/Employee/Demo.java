package com.Employee;

import java.awt.Image;

import javax.swing.*;

public class Demo extends JFrame {
	
	Demo()
	{
		
		ImageIcon  i1=new ImageIcon(ClassLoader.getSystemResource("back.jpg"));
	    Image i2=i1.getImage().getScaledInstance(1540,900,Image.SCALE_DEFAULT);
	  ImageIcon  i3=new ImageIcon(i2);
	    JLabel l1=new JLabel(i3);
	    l1.setBounds(0,0,1540,900);
	    add(l1);
		setVisible(true);
		setSize(1540,900);
		new Login();
	}
	public static void main(String[] args) {
		new Demo();
	}
}
