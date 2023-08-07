package insurance;

public class Travel extends Insurance {

    private Long insuranceIdx;
    private String country;
    private int travelPeriod;
	
	public Long getInsuranceIdx() {
		return insuranceIdx;
	}
	public void setInsuranceIdx(Long insuranceIdx) {
		this.insuranceIdx = insuranceIdx;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	public int getTravelPeriod() {
		return travelPeriod;
	}
	public void setTravelPeriod(int travelPeriod) {
		this.travelPeriod = travelPeriod;
	}

}
