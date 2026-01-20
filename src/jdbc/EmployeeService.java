package jdbc;

import exception.EmployeeNotFoundException;

public class EmployeeService {

    public static void addEmployee(
            String name,
            String email,
            int deptId,
            int roleId,
            double salary) {

        // Basic validations
        if (name == null || name.isBlank()) {
            System.out.println("❌ Name cannot be empty");
            return;
        }

        if (!email.contains("@")) {
            System.out.println("❌ Invalid email format");
            return;
        }

        if (salary <= 0) {
            System.out.println("❌ Salary must be greater than 0");
            return;
        }

        // Call DAO only after validation
        EmployeeDAO.addEmployee(name, email, deptId, roleId, salary);
    }

    public static void viewAllEmployees() {
        EmployeeDAO.getAllEmployees();
    }
    public static void updateSalary(int empId, double newSalary)
            throws EmployeeNotFoundException {

        if (newSalary <= 0) {
            System.out.println("❌ Salary must be greater than 0");
            return;
        }

        if (!EmployeeDAO.employeeExists(empId)) {
            throw new EmployeeNotFoundException(
                    "Employee with ID " + empId + " not found");
        }

        EmployeeDAO.updateSalary(empId, newSalary);
    }
    public static void deleteEmployee(int empId)
            throws EmployeeNotFoundException {

        if (!EmployeeDAO.employeeExists(empId)) {
            throw new EmployeeNotFoundException(
                    "Employee with ID " + empId + " not found");
        }

        EmployeeDAO.deleteEmployee(empId);
    }public static void searchEmployeeById(int empId)
            throws EmployeeNotFoundException {

        if (!EmployeeDAO.employeeExists(empId)) {
            throw new EmployeeNotFoundException(
                    "Employee with ID " + empId + " not found");
        }

        EmployeeDAO.getEmployeeById(empId);
    }public static void viewEmployeesPaged(int page, int size) {
        if (page < 1 || size < 1) {
            System.out.println("❌ Invalid page or size");
            return;
        }
        int offset = (page - 1) * size;
        EmployeeDAO.getEmployeesPaged(size, offset);
    }
}