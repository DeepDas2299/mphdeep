package com.mph.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import com.mph.model.Employee;
import com.mph.model.Salary;
import com.mph.util.MyDBConnection;

public class EmployeeDao {
	Connection con;
	Statement st;
	PreparedStatement ps;
	ResultSet rs;

	public void insertEmp(Employee e, Salary s)
	{
		con = MyDBConnection.getDBConnection();
		try {
			
			ps = con.prepareStatement("insert into mphemp(eid, ename,sal) values(?,?,?)");
			ps.setInt(1, e.getEid());
			ps.setString(2, e.getEname());
			ps.setDouble(3, s.getNet());
			int numRows = ps.executeUpdate();
			System.out.println(numRows + " row(s) inserted successfully");
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	public void viewEmp()
	{
		con = MyDBConnection.getDBConnection();
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from mphemp order by eid");
			while(rs.next())
			{
				System.out.println("EID : " + rs.getInt(1) + ", EName : " +  rs.getString(2) + ", Salary : " + rs.getDouble(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//insert using proc
	public void insertUsingProc(Employee e, Salary s)
	{
		con = MyDBConnection.getDBConnection();
		try {
			CallableStatement cs = con.prepareCall("{call insertRecord(?,?,?)}");
			cs.setInt(1,  e.getEid());
			cs.setString(2,  e.getEname());
			cs.setDouble(3, s.getNet());
			cs.execute();
			System.out.println("Procedure successfully Executed and record inserted!");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	//result set metadata
	public void rsmd()
	{
		con = MyDBConnection.getDBConnection();
		HashMap<Long, HashMap<String, Object>> hmap = new HashMap<Long, HashMap<String, Object>>();
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from mphemp");
			ResultSetMetaData md = rs.getMetaData();
			while(rs.next())
			{
				HashMap<String, Object> row = new HashMap<String, Object>();
				//System.out.println(md.getColumnCount());
				for(int i = 1; i <= md.getColumnCount(); i++)
				{
					row.put(md.getColumnName(i), rs.getObject(i));
				}
				hmap.put(rs.getLong("eid"), row);
			}
			System.out.println(hmap);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//rs types :-
	public void type_forward_only_rs()
	{
		con = MyDBConnection.getDBConnection();
		try {
			ps = con.prepareStatement("select * from mphemp", ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			while(rs.next())
			{
				System.out.println("EID : " + rs.getInt(1) + ", EName : " +  rs.getString(2) + ", Salary : " + rs.getDouble(3));
			}
			System.out.println(rs.getType());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void type_scroll_insensitive_rs()
	{
		con = MyDBConnection.getDBConnection();
		try {
			ps = con.prepareStatement("select * from mphemp", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			while(rs.next())
			{
				System.out.println("EID : " + rs.getInt(1) + ", EName : " +  rs.getString(2) + ", Salary : " + rs.getDouble(3));
			}
			System.out.println(rs.getType());
			System.out.println("Move cursor to First Position -> ");
			rs.first();
			System.out.println(rs.getString("ename"));
			System.out.println("Move cursor to Last Position -> ");
			rs.last();
			System.out.println(rs.getString("ename"));
			System.out.println("Move cursor to Previous Position -> ");
			rs.previous();
			System.out.println(rs.getString("ename"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void type_scroll_sensitive_rs_insert()
	{
		con = MyDBConnection.getDBConnection();
		try {
			ps = con.prepareStatement("select eid,ename,sal from mphemp", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			//rs.first();
			rs.moveToInsertRow();
			rs.updateInt("eid",105);
			rs.updateString("ename", "ADAM");
			rs.updateDouble("sal", 32000.00);
			rs.insertRow();
			System.out.println("After Inserting : ");
			viewEmp();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public void type_scroll_sensitive_rs_update()
	{
		con = MyDBConnection.getDBConnection();
		try {
			ps = con.prepareStatement("select eid,ename,sal from mphemp where eid = 105", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			rs.next();
			rs.updateInt("eid",101);
			rs.updateString("ename", "RIYA");
			rs.updateDouble("sal", 31000.00);
			rs.updateRow();
			System.out.println("After Updating : ");
			viewEmp();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void batchUpdate()
	{
		con = MyDBConnection.getDBConnection();
		try {
			st = con.createStatement();
			st.addBatch("update mphemp set ename = 'MOHAN' where eid = 101");
			st.addBatch("update mphemp set eid  = 106 where ename = 'YASH'");
			st.addBatch("update mphemp set ename = 'VIRAJ' where eid = 103");
			//st.addBatch("insert into mphemp values(110,'ALEX', 21000)");
			int[] count = st.executeBatch();
			for(int i = 0; i < count.length; i++)
				System.out.println(i + " " + count[i] + " times");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
