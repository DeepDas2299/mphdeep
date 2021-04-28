package com.mph.model;

public class Manager extends Employee {
	private String dept;
	
	public Manager() {}
	public Manager(int eid, String ename, Salary sal, String dept)
	{
		super(eid, ename, sal);
		this.dept = dept;
	}
	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	@Override
	public String toString() {
		return super.toString() + ", dept = " + dept;
	}
	
}
