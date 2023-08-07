package insurance;

public class Health extends Insurance {

    private Long insuranceIdx;
    private int healthGrade;
    
	
	public Long getInsuranceIdx() {
		return insuranceIdx;
	}
	public void setInsuranceIdx(Long insuranceIdx) {
		this.insuranceIdx = insuranceIdx;
	}
	public int getHealthGrade() {
		return healthGrade;
	}
	public void setHealthGrade(int healthGrade) {
		this.healthGrade = healthGrade;
	}
    

}
