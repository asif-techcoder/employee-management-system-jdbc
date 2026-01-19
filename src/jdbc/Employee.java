package jdbc;

public class Employee {
    private int empID;
    private String firstName;
    private String email;
    private int deptId;
    private int roleId;
    private double salary;

    public Employee(int empID, String firstName, String email, int deptId, int roleId, double salary) {
        this.empID = empID;
        this.firstName = firstName;
        this.email = email;
        this.deptId = deptId;
        this.roleId = roleId;
        this.salary = salary;
    }
    public int getEmpID(){
        return empID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getEmail() {
        return email;
    }

    public int getDeptId() {
        return deptId;
    }

    public int getRoleId() {
        return roleId;
    }

    public double getSalary() {
        return salary;
    }
}


