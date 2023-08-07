package insurance;

import accident.Accident;
import contract.Contract;
import employee.Employee;


public abstract class Insurance {

    private Long insuranceIdx; //pk
    private String content;
    private int fee; 
    private int maxReward;
    private int period;
    private int rate;
    private Long employeeIdx; //fk

	public Long getInsuranceIdx() {
		return insuranceIdx;
	}

	public void setInsuranceIdx(Long insuranceIdx) {
		this.insuranceIdx = insuranceIdx;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getFee() {
		return fee;
	}

	public void setFee(int fee) {
		this.fee = fee;
	}

	public int getMaxReward() {
		return maxReward;
	}

	public void setMaxReward(int maxReward) {
		this.maxReward = maxReward;
	}

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public Long getEmployeeIdx() {
		return employeeIdx;
	}

	public void setEmployeeIdx(Long employeeIdx) {
		this.employeeIdx = employeeIdx;
	}

}
