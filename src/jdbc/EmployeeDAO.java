package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EmployeeDAO {

    public static void getAllEmployees() {

        String sql = """
                SELECT e.emp_id, e.first_name, e.email,
                       d.dept_name, r.role_name, e.salary
                FROM employee e
                JOIN department d ON e.dept_id = d.dept_id
                JOIN role r ON e.role_id = r.role_id
                """;

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.println(
                    "ID | Name | Email | Dept | Role | Salary");
            System.out.println("------------------------------------------");

            while (rs.next()) {
                System.out.println(
                        rs.getInt("emp_id") + " | " +
                                rs.getString("first_name") + " | " +
                                rs.getString("email") + " | " +
                                rs.getString("dept_name") + " | " +
                                rs.getString("role_name") + " | " +
                                rs.getDouble("salary")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void addEmployee(
            String name,
            String email,
            int deptId,
            int roleId,
            double salary) {

        String sql = """
        INSERT INTO employee
        (first_name, email, dept_id, role_id, salary)
        VALUES (?, ?, ?, ?, ?)
        """;

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setInt(3, deptId);
            ps.setInt(4, roleId);
            ps.setDouble(5, salary);

            ps.executeUpdate();
            System.out.println("✅ Employee added successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void updateSalary(int empId, double newSalary) {

        String sql = "UPDATE employee SET salary = ? WHERE emp_id = ?";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDouble(1, newSalary);
            ps.setInt(2, empId);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("✅ Salary updated successfully");
            } else {
                System.out.println("❌ Employee not found");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void deleteEmployee(int empId) {

        String sql = "DELETE FROM employee WHERE emp_id = ?";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, empId);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("✅ Employee deleted successfully");
            } else {
                System.out.println("❌ Employee not found");
            }

        } catch (Exception e) {
            e.printStackTrace();

        }

    }
    public static boolean employeeExists(int empId) {

        String sql = "SELECT 1 FROM employee WHERE emp_id = ?";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, empId);
            ResultSet rs = ps.executeQuery();

            return rs.next(); // true if employee exists

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }public static void getEmployeeById(int empId) {

        String sql = """
            SELECT e.emp_id, e.first_name, e.email,
                   d.dept_name, r.role_name, e.salary
            FROM employee e
            JOIN department d ON e.dept_id = d.dept_id
            JOIN role r ON e.role_id = r.role_id
            WHERE e.emp_id = ?
            """;

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, empId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("ID    : " + rs.getInt("emp_id"));
                System.out.println("Name  : " + rs.getString("first_name"));
                System.out.println("Email : " + rs.getString("email"));
                System.out.println("Dept  : " + rs.getString("dept_name"));
                System.out.println("Role  : " + rs.getString("role_name"));
                System.out.println("Salary: " + rs.getDouble("salary"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }public static void getEmployeesPaged(int limit, int offset) {
        String sql = """
        SELECT e.emp_id, e.first_name, e.email,
               d.dept_name, r.role_name, e.salary
        FROM employee e
        JOIN department d ON e.dept_id = d.dept_id
        JOIN role r ON e.role_id = r.role_id
        LIMIT ? OFFSET ?
        """;

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, limit);
            ps.setInt(2, offset);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(
                        rs.getInt("emp_id") + " | " +
                                rs.getString("first_name") + " | " +
                                rs.getString("email") + " | " +
                                rs.getString("dept_name") + " | " +
                                rs.getString("role_name") + " | " +
                                rs.getDouble("salary")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}