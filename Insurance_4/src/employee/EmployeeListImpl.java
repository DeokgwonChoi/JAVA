package employee;

import java.util.List;

import dao.EmployeeDao;

public class EmployeeListImpl implements EmployeeList {
	private EmployeeDao employeeDao = new EmployeeDao();
	
	public EmployeeListImpl() {
	}

	
	@Override
	public List<Employee> getEmployeeList() {
		List<Employee> employeeList = employeeDao.getEmployeeList();
		return employeeList;
	}

	@Override
	public boolean SignUp(Employee employee) {
		return employeeDao.addEmployee(employee);
	}

	@Override	
	public boolean deleteEmployee(Long employeeIdx) {
		return employeeDao.deleteEmployee(employeeIdx);
	}


	@Override
	public boolean modifyEmployeeGrade(Long employeeIdx, String grade) {
		return employeeDao.modifyEmployeeGrade(employeeIdx, grade);
	}
}
