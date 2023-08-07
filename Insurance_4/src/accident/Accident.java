package accident;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Accident {

    private Long accidentIdx; //primary key
    private String accidenttype;
    private String content;
    private LocalDate accidentDate;
    private int damagePrice;
    private int compensationPrice;
    
 	private Long userIdx; //foreign key
    private Long insuranceIdx; //foreign key
    private Long employeeIdx; //foreign key
    
    private boolean checkAccident; // �궗怨좊줈�뜥 �옉�슜�븯�뒗吏� 1李� 寃�利�
    private boolean lawsuitStatus;

    
	public Long getAccidentIdx() {
		return accidentIdx;
	}

	public void setAccidentIdx(Long accidentIdx) {
		this.accidentIdx = accidentIdx;
	}

	public String getAccidenttype() {
		return accidenttype;
	}

	public void setAccidenttype(String accidenttype) {
		this.accidenttype = accidenttype;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDate getAccidentDate() {
		return accidentDate;
	}

	public void setAccidentDate(LocalDate accidentDate) {
		this.accidentDate = accidentDate;
	}

	public int getDamagePrice() {
		return damagePrice;
	}

	public void setDamagePrice(int damagePrice) {
		this.damagePrice = damagePrice;
	}

	public int getCompensationPrice() {
		return compensationPrice;
	}

	public void setCompensationPrice(int compensationPrice) {
		this.compensationPrice = compensationPrice;
	}

	public Long getUserIdx() {
		return userIdx;
	}

	public void setUserIdx(Long userIdx) {
		this.userIdx = userIdx;
	}

	public Long getInsuranceIdx() {
		return insuranceIdx;
	}

	public void setInsuranceIdx(Long insuranceIdx) {
		this.insuranceIdx = insuranceIdx;
	}

	public Long getEmployeeIdx() {
		return employeeIdx;
	}

	public void setEmployeeIdx(Long employeeIdx) {
		this.employeeIdx = employeeIdx;
	}

	public boolean isCheckAccident() {
		return checkAccident;
	}

	public void setCheckAccident(boolean checkAccident) {
		this.checkAccident = checkAccident;
	}

	public boolean isLawsuitStatus() {
		return lawsuitStatus;
	}

	public void setLawsuitStatus(boolean lawsuitStatus) {
		this.lawsuitStatus = lawsuitStatus;
	}

	public String toStringAll() {
        String stringReturn =  this.accidentIdx + " " + this.accidentDate+ " " +
    this.accidenttype+ " " + this.content+ " " + this.damagePrice+ " " + this.checkAccident;
        return stringReturn;
    }
   
}
