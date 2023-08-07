package salesExpense;

public class SalesExpense {
	private Long salesExpenseIdx;
	private String title;
	private String content;
	private Long employeeIdx; //foreign key
	
	public Long getSalesExpenseIdx() {
		return salesExpenseIdx;
	}
	public void setSalesExpenseIdx(Long salesExpenseIdx) {
		this.salesExpenseIdx = salesExpenseIdx;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Long getEmployeeIdx() {
		return employeeIdx;
	}
	public void setEmployeeIdx(Long employeeIdx) {
		this.employeeIdx = employeeIdx;
	}

}
