package com.mph.controller;

import java.io.IOException;

public interface EmployeeInterface {
	public void addEmployee(int choice);	//Exposes functionality to view Package.
	public void viewEmployee(int choice);
	public void sortEmpNames(int i);
	public void getNamesStartingWithA();
	public void serializeList() throws IOException;
	public void deSerializeList() throws IOException, ClassNotFoundException;
	public void insertUsingProc();
	public void rsmd();
	public void type_forward_only_rs();
	public void type_scroll_insensitive_rs();
	public void type_scroll_sensitive_rs_insert();
	public void type_scroll_sensitive_rs_update();
	public void batchUpdate();
}
