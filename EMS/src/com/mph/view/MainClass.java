package com.mph.view;

import java.io.IOException;
import java.util.InputMismatchException;
import com.mph.exception.*;
import java.util.Scanner;
import java.util.function.BiPredicate;

import com.mph.controller.EmployeeController;
import com.mph.controller.EmployeeInterface;

public class MainClass {

	private static EmployeeInterface ec = new EmployeeController();

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		Scanner sc = new Scanner(System.in);
		String user = "ABC";
		String password = "123";
		validateUser(user, password, sc);
		displayLoading();
		displayMenu(sc);
		System.exit(0);
	}
	//Validate User Credentials
	public static void validateUser(String user, String password, Scanner sc)
	{
		BiPredicate<String, String> validation = (test, valid) -> test.equals(valid);
		boolean validated;
		do {
			validated = false;
			System.out.print("Enter Username : ");
			String u = sc.next();
			System.out.print("Enter Password : ");
			String p = sc.next();

			try {
				if(validation.test(u, user) && validation.test(p, password))
					validated = true;
				else
					throw new UserNotFoundException();
			}
			catch(UserNotFoundException e)
			{
				System.out.println(e);
				System.out.println("\nPlease try again!\n");
			}

		}while(!validated);
	}
	//Loading Screen
	public static void displayLoading()
	{
		System.out.print("Loading");
		for(int i = 0; i < 3; i++)
			try {
				Thread.sleep(300);
				System.out.print(".");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		System.out.println();
	}
	//Display Menu
	public static void displayMenu(Scanner sc) throws ClassNotFoundException, IOException
	{
		int choice = -1;
		do {
			System.out.println("\n--Options-- ");
			System.out.println("1. Add Employee");
			//System.out.println("2. Add Manager");
			System.out.println("3. View Employee");
			System.out.println("4. Insert Using Proc");
			System.out.println("5. View ResultSet Metadata");
			System.out.println("6. View Results TYPE_FORWARD_ONLY");
			System.out.println("7. View Results TYPE_SCROLL_INSENSITIVE");
			System.out.println("8. View Results TYPE_SCROLL_SENSITIVE_INSERT");
			System.out.println("9. View Results TYPE_SCROLL_SENSITIVE_UPDATE");
			System.out.println("10. Perform batch update");
			/*System.out.println("4. View Manager");
			System.out.println("5. Sort Employees on Name");
			System.out.println("6. Sort Managers on Name");
			System.out.println("7. View Names Starting with A");
			System.out.println("8. Serialize EMP");
			System.out.println("9. Deserialize and View EMP");*/
			System.out.println("0. Exit\n");
			System.out.print("Enter your choice : ");

			try {
				choice = sc.nextInt();
				if(choice != 0)
					switch (choice) {
					case 1 : {
						ec.addEmployee(1);
						break;
					}
					/*case 2 : {
						ec.addEmployee(2);
						break;
					}*/
					case 3 : {
						ec.viewEmployee(1);
						break;
					}
					case 4 : {
						ec.insertUsingProc();
						break;
					}
					case 5 : {
						ec.rsmd();
						break;
					}
					case 6 : {
						ec.type_forward_only_rs();
						break;
					}
					case 7 : {
						ec.type_scroll_insensitive_rs();
						break;
					}
					case 8 : {
						ec.type_scroll_sensitive_rs_insert();
						break;
					}
					case 9 : {
						ec.type_scroll_sensitive_rs_update();
						break;
					}
					case 10 : {
						ec.batchUpdate();
						break;
					}
					/*case 4 : {
						ec.viewEmployee(2);
						break;
					}
					case 5 : {
						ec.sortEmpNames(1);
						break;
					}
					case 6 : {
						ec.sortEmpNames(2);
						break;
					}
					case 7 : {
						ec.getNamesStartingWithA();
						break;
					}
					case 8 : {
						ec.serializeList();
						break;
					}
					case 9 : {
						ec.deSerializeList();
						break;
					}*/
					default : {
						System.out.println("--Invalid Choice--");
						break;
					}
					}
			}
			catch(InputMismatchException e) {
				System.out.println("\n----Please Enter valid choice----");
				sc.next();
			}
		}while(choice != 0);
		System.out.println("Exited. Thank-you for using our system.");
	}
}
