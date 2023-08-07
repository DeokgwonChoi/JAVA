package insurance;

public class Fire extends Insurance {

    private Long insuranceIdx;
    private int buildingCount;
    private int buildingPrice;
    
	
	public Long getInsuranceIdx() {
		return insuranceIdx;
	}
	public void setInsuranceIdx(Long insuranceIdx) {
		this.insuranceIdx = insuranceIdx;
	}
	public int getBuildingCount() {
		return buildingCount;
	}
	public void setBuildingCount(int buildingCount) {
		this.buildingCount = buildingCount;
	}
	public int getBuildingPrice() {
		return buildingPrice;
	}
	public void setBuildingPrice(int buildingPrice) {
		this.buildingPrice = buildingPrice;
	}


}
