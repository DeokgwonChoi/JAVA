package employee;


import java.util.List;

public interface EmployeeList {
	public boolean SignUp(Employee employee);
	public List<Employee> getEmployeeList();
	public boolean deleteEmployee(Long employeeIdx);
//	public boolean updateEmployee();
	public boolean modifyEmployeeGrade(Long employeeIdx, String grade);

}