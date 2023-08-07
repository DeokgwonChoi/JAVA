package dao;

 
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import employee.Employee;

public class EmployeeDao extends Dao{
	public EmployeeDao() {
		super.connect();
	}

	public boolean addEmployee(Employee employee) {
			String sql = "INSERT INTO insurance.employee (id, name, pw, role, tel, grade, accountNumber) VALUES("+
							"'" + employee.getId()+"', "+
							"'" + employee.getName()+"', "+
							"'" + employee.getPw()+"', "+
							"'" + employee.getRole()+"', "+
							"'" + employee.getTel()+"', "+
							"'" + "1"+"', "+ //grade part
							"'" + employee.getAccountNumber()+"');";
			return super.create(sql);
	}	

	public List<Employee> getEmployeeList() {
		String sql = "SELECT * FROM insurance.employee";

		ResultSet rs = super.retrieve(sql);
		List<Employee> employeeList = new ArrayList<>();
		try {
			while(rs.next()) {
			    String idx = rs.getString(1);
			    String id = rs.getString(2);
			    String name = rs.getString(3);
			    String pw = rs.getString(4);
			    String role = rs.getString(5);
			    String tel = rs.getString(6);
			    String grade = rs.getString(7);
			    String accountNumber = rs.getString(8);
			    
				Employee employee = new Employee();
			    employee.setEmployeeIdx(Long.parseLong(idx));
			    employee.setId(id);
			    employee.setName(name);
			    employee.setPw(pw);
			    employee.setRole(role);
			    employee.setTel(tel);
			    employee.setGrade(grade);
			    employee.setAccountNumber(accountNumber);
			    employeeList.add(employee);
			}
		} 
		 catch (SQLException e) {
			e.printStackTrace();
		}
		return employeeList;	
	}

	

	public boolean deleteEmployee(Long employeeIdx ) {
		String sql = "delete from insurance.employee where employeeIdx=" +
				"'"+ employeeIdx +"'";
		return super.delete(sql);
	}

	

//	public boolean updateEmployee() {
//		String sql = "update insurance.employee set checkSug="
//				+ 1 + " where contractIdx =" +"'" +employeeIdx + "' and " +  "userIdx="
//				+ "'" + userIdx + "';";

//    	System.out.p

//	}
	
	public boolean modifyEmployeeGrade(Long employeeIdx, String grade) {
		String sql = "update insurance.employee set grade= "+ "'"+ grade  +"'  where employeeIdx = " + employeeIdx + ";";
		System.out.println(sql);
		return super.update(sql);
	}


}