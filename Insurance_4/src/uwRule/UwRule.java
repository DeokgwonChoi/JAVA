package uwRule;

public class UwRule {

	private Long uwRuleIdx;
	private String title;
	private String content;
	private Long employeeIdx; //foreign key

	public Long getUwRuleIdx() {
		return uwRuleIdx;
	}

	public void setUwRuleIdx(Long uwRuleIdx) {
		this.uwRuleIdx = uwRuleIdx;
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
