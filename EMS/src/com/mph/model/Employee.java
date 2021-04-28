package com.mph.model;

import java.io.Serializable;
import java.util.Comparator;

public class Employee implements Serializable{

	private int eid;
	private String ename;
	private Salary salary;
	
	public Employee() {}
	public Employee(int eid, String ename, Salary sal) 
	{
		this.eid = eid;
		this.ename = ename;
		setSalary(sal);
	}
	public void setEid(int eid)
	{
		this.eid = eid;
	}
	public int getEid()
	{
		return this.eid;
	}
	public void setEname(String ename)
	{
		this.ename = ename;
	}
	public String getEname()
	{
		return this.ename;
	} 
	public void setSalary(Salary salary) {
		this.salary = salary;
	}

	public static Comparator<Employee> nameComparator = new Comparator<Employee>() {

		@Override
		public int compare(Employee e1, Employee e2) {
			
			return (e1.getEname().compareTo(e2.getEname()));
		}
	};
	@Override
	public String toString() {
		return "Employee eid=" + eid + ", ename=" + ename + ", " + salary;
	}
	
	
}
