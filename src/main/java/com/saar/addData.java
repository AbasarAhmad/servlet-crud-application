package com.saar;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//@WebServlet("/insertData")
public class addData extends HttpServlet  {
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws  ServletException, IOException
	{
		PrintWriter pw=res.getWriter();
		
		String roll=req.getParameter("rollNumber");
		String name=req.getParameter("name");
		String branch=req.getParameter("branch");
		
		int rollNumber=Integer.parseInt(roll);
		String sql = "insert into student values(?,?,?)";
		
		try {
			//load driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			// We make connection
			Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/ducat","root","Riyaz@31200");
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setInt(1, rollNumber);
			ps.setString(2, name);
			ps.setString(3, branch);
			ps.executeUpdate();
			pw.println("<h1>Your data is recorded</h1>");
			ps.close();
		}
		catch(ClassNotFoundException e)
		{
			 e.printStackTrace();
	        
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}
}
