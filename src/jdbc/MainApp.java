package jdbc;

import jdbc.EmployeeService;
import exception.EmployeeNotFoundException;
import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {

            System.out.println("\n=================================");
            System.out.println("  EMPLOYEE MANAGEMENT SYSTEM");
            System.out.println("=================================");
            System.out.println("1. Add Employee");
            System.out.println("2. View Employees");
            System.out.println("3. Update Salary");
            System.out.println("4. Delete Employee");
            System.out.println("5. Search Employee by ID");
            System.out.println("6. View Employees(Paged)");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Name: ");
                    String name = sc.nextLine();

                    System.out.print("Email: ");
                    String email = sc.nextLine();

                    System.out.print("Dept ID: ");
                    int deptId = sc.nextInt();

                    System.out.print("Role ID: ");
                    int roleId = sc.nextInt();

                    if (deptId <= 0 || roleId <= 0){
                        System.out.println("❌ Invalid Department or Role ID");
                        break;
                    }

                    System.out.print("Salary: ");
                    double salary = sc.nextDouble();

                    if (salary <= 0){
                        System.out.println("❌ Salary must be positive");
                        break;
                    }

                    EmployeeService.addEmployee(
                            name, email, deptId, roleId, salary
                    );
                    break;

                case 2:
                    EmployeeService.viewAllEmployees();
                    break;

                case 3:
                    System.out.print("Employee ID: ");
                    int empId = sc.nextInt();

                    System.out.print("New Salary: ");
                    double newSalary = sc.nextDouble();

                    try {
                        EmployeeService.updateSalary(empId, newSalary);
                    }
                    catch (EmployeeNotFoundException e){
                        System.out.println("❌" + e.getMessage());
                    }
                    break;

                case 4:
                    System.out.print("Employee ID: ");
                    int delId = sc.nextInt();

                    try {
                        EmployeeService.deleteEmployee(delId);
                    }
                    catch (EmployeeNotFoundException e){
                        System.out.println("❌" + e.getMessage());
                    }
                    break;

                case 5:
                    System.out.println("Employee ID: ");
                    int searchID = sc.nextInt();

                    try {
                        EmployeeService.searchEmployeeById(searchID);
                    }
                    catch (EmployeeNotFoundException e){
                        System.out.println("❌" + e.getMessage());
                    }
                    break;



                case 6:
                    System.out.println("Page number: ");
                    int page = sc.nextInt();

                    System.out.println("Page size: ");
                    int size = sc.nextInt();

                    EmployeeService.viewEmployeesPaged(page,size);
                    break;

                case 7:
                    running = false;
                    System.out.println("👋 Exiting application");
                    break;

                default:
                    System.out.println("❌ Invalid choice");
            }
        }

        sc.close();
    }
}