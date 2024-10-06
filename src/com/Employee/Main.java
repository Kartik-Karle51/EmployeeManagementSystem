package com.Employee;

import java.awt.*;
import javax.swing.*;

public class Main extends JFrame {
public Main(){
	
	ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("EMP.gif"));
	Image i2=i1.getImage().getScaledInstance(1200, 990, Image.SCALE_DEFAULT);
	ImageIcon i3=new ImageIcon(i2);
	JLabel l1=new JLabel(i3);
	l1.setBounds(0,0,1170,650);
	add(l1);
	
	setSize(1170,650);
	setLayout(null);
	setVisible(true);
	setLocation(180,80);
	setResizable(false);
	
	try {
		Thread.sleep(5000);
		setVisible(false);
		new Demo();
		
	}catch(Exception e) {
		e.printStackTrace();
	}
	
}
	
	public static void main(String[] args) {
	new Main();
	}
}
