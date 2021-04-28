package com.mph.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.mph.dao.EmployeeDao;
import com.mph.model.Employee;
import com.mph.model.Manager;
import com.mph.model.Salary;

public class EmployeeController implements EmployeeInterface {	//implementing to expose/restrict functionality
	
	List<Employee> emp = new ArrayList<Employee>();
	List<Manager> m = new ArrayList<Manager>();
	Salary sal;
	EmployeeDao empdao = new EmployeeDao();
	
	//Add Employee
	public void addEmployee(int choice)
	{
		Scanner sc = new Scanner(System.in);
		System.out.print("Eid: ");
		int eid = sc.nextInt();
		
		System.out.print("Ename : ");
		String ename = sc.next();	
		
		System.out.println("Basic Salary : ");
		int basic = sc.nextInt();
		

		sal = new Salary(basic);
		
		if(choice == 2)
		{
			System.out.println("Department : ");
			String dept = sc.next();
			Manager m1 = new Manager(eid, ename, sal, dept);
			m.add(m1);
			System.out.println("EMP : " + m1.getEid() + " succesfully added");
		}
		else
		{	
			Employee e1 = new Employee(eid, ename, sal);
			empdao.insertEmp(e1,sal);
			System.out.println("EMP : " + e1.getEid() + " succesfully added");
		}
		
	}
	//insert using procedure
	public void insertUsingProc()
	{
		Employee e = new Employee();
		Scanner sc1 = new Scanner(System.in);
		System.out.print("Eid: ");
		int eid = sc1.nextInt();
		
		System.out.print("Ename : ");
		String ename = sc1.next();	
		
		System.out.println("Basic Salary : ");
		int basic = sc1.nextInt();
		

		sal = new Salary(basic);
		
		e.setEid(eid);
		e.setEname(ename);
		e.setSalary(sal);
		
		empdao.insertEmp(e, sal);
		
	}
	//View Employee
	public void viewEmployee(int choice)
	{
		if(choice == 1)
			empdao.viewEmp();
		else
			if(m.isEmpty()) 
				System.out.println("--No data to display--");
			else
				iterateEmpList(m);
	}
	
	//result set metadata
	public void rsmd()
	{
		empdao.rsmd();
	}
	
	//type in resultsets
	public void type_forward_only_rs()
	{
		empdao.type_forward_only_rs();
	}
	
	public void type_scroll_insensitive_rs()
	{
		empdao.type_scroll_insensitive_rs();
	}
	public void type_scroll_sensitive_rs_insert()
	{
		empdao.type_scroll_sensitive_rs_insert();
	}
	public void type_scroll_sensitive_rs_update()
	{
		empdao.type_scroll_sensitive_rs_update();
	}
	//batch update
	public void batchUpdate()
	{
		empdao.batchUpdate();
	}
	//------------------------------------------------//
	
	public void iterateEmpList(List<? extends Employee> l)
	{
		l.forEach(System.out::println);
	}
	public void sortEmpNames(int choice)
	{
		
		if(choice == 1)
			emp = emp.stream().sorted(Comparator.comparing(Employee::getEname)).collect(Collectors.toList());
		else
			m = m.stream().sorted(Comparator.comparing(Employee::getEname)).collect(Collectors.toList());;
	}
	public void getNamesStartingWithA()
	{
		emp.stream().filter((emp) -> emp.getEname().toLowerCase().startsWith("a")).sorted(Comparator.comparing(Employee::getEname)).forEach(System.out::println);
		
	}
	public void serializeList() throws IOException
	{
		 FileOutputStream fos = new FileOutputStream("empFile.txt");
	     ObjectOutputStream outputStream = new ObjectOutputStream(fos);
	     outputStream.writeObject(emp);
	     outputStream.close();
	     System.out.println("\nFile Serialized");
	}
	public void deSerializeList() throws IOException, ClassNotFoundException
	{
		FileInputStream fis = new FileInputStream("empFile.txt");
        ObjectInputStream inputStream = new ObjectInputStream(fis);
        List<Employee> deSerialisedEmpList = (List<Employee>) inputStream.readObject();
        inputStream.close();
	    
        System.out.println("\nDeserialized File : ");
        deSerialisedEmpList.forEach(System.out::println);
	}
}

