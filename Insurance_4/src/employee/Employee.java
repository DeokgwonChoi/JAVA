package employee;

public class Employee {

    private Long employeeIdx; //primary key
    private String id;
	private String name;
    private String pw;
    private String role;
    private String tel;
    private String grade;
    private String  accountNumber;
   
	public Long getEmployeeIdx() {
		return employeeIdx;
	}
	public void setEmployeeIdx(Long employeeIdx) {
		this.employeeIdx = employeeIdx;
	}
	
     public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	@Override
	public String toString() {
		return "[직원 번호=" + employeeIdx + ", id=" + id + ", name=" + name + ", pw=" + pw + ", role="
				+ role + ", 전화번호=" + tel + ", 등급=" + grade + ", 계좌번호=" + accountNumber + "]";
	}
}
