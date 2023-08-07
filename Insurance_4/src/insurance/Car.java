package insurance;

public class Car extends Insurance {


    private Long insuranceIdx;
    private int accidentRecord;
    private int carCount;
    private String carNo;
    private int price;
    
	public Car() {
	}
	
	public Long getInsuranceIdx() {
		return insuranceIdx;
	}
	public void setInsuranceIdx(Long insuranceIdx) {
		this.insuranceIdx = insuranceIdx;
	}
	public int getAccidentRecord() {
		return accidentRecord;
	}
	public void setAccidentRecord(int accidentRecord) {
		this.accidentRecord = accidentRecord;
	}
	public int getCarCount() {
		return carCount;
	}
	public void setCarCount(int carCount) {
		this.carCount = carCount;
	}
	public String getCarNo() {
		return carNo;
	}
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
    
}
