package com.Employee;
import java.sql.*;
public class Conn {
Connection con;
static Statement stmt;

public Conn(){
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_management","root","Kartik@5151");
		stmt=con.createStatement();
	}catch(Exception e) {
		e.printStackTrace();
	}
}
}
