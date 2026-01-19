package jdbc;

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
            System.out.println("5. Exit");
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

                    EmployeeDAO.addEmployee(
                            name, email, deptId, roleId, salary
                    );
                    break;

                case 2:
                    EmployeeDAO.getAllEmployees();
                    break;

                case 3:
                    System.out.print("Employee ID: ");
                    int empId = sc.nextInt();

                    System.out.print("New Salary: ");
                    double newSalary = sc.nextDouble();

                    EmployeeDAO.updateSalary(empId, newSalary);
                    break;

                case 4:
                    System.out.print("Employee ID: ");
                    int delId = sc.nextInt();

                    EmployeeDAO.deleteEmployee(delId);
                    break;

                case 5:
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