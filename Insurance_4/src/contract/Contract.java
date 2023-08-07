package contract;

import java.time.LocalDate;

public class Contract {
   
    private Long contractIdx; //primary key
    private LocalDate created;

    private String subscription;
    private String suggestion;

    private boolean checkSub;
    private boolean checkSug;
    private boolean checkUw;
    
    //미납자
    private boolean checkpay;
    
    private Long userIdx; //foreign key
    private Long employeeIdx; //foreign key
    private Long insuranceIdx; //foreign key
   
    private boolean completed;
    private int fee;
    
   public Long getContractIdx() {
      return contractIdx;
   }
   public void setContractIdx(Long contractIdx) {
      this.contractIdx = contractIdx;
   }
   public LocalDate getCreated() {
      return created;
   }
   public void setCreated(LocalDate created) {
      this.created = created;
   }
   public String getSubscription() {
      return subscription;
   }
   public void setSubscription(String subscription) {
      this.subscription = subscription;
   }
   public String getSuggestion() {
      return suggestion;
   }
   public void setSuggestion(String suggestion) {
      this.suggestion = suggestion;
   }
   public boolean isCheckSub() {
      return checkSub;
   }
   public void setCheckSub(boolean checkSub) {
      this.checkSub = checkSub;
   }
   public boolean isCheckSug() {
      return checkSug;
   }
   public void setCheckSug(boolean checkSug) {
      this.checkSug = checkSug;
   }
   public boolean isCheckUw() {
      return checkUw;
   }
   public void setCheckUw(boolean checkUw) {
      this.checkUw = checkUw;
   }
   public boolean isCheckPay() {
      return checkpay;
   }
   public void setCheckPay(boolean checkpay) {
      this.checkpay = checkpay;
   }
   public Long getUserIdx() {
      return userIdx;
   }
   public void setUserIdx(Long userIdx) {
      this.userIdx = userIdx;
   }
   public Long getEmployeeIdx() {
      return employeeIdx;
   }
   public void setEmployeeIdx(Long employeeIdx) {
      this.employeeIdx = employeeIdx;
   }
   public Long getInsuranceIdx() {
      return insuranceIdx;
   }
   public void setInsuranceIdx(Long insuranceIdx) {
      this.insuranceIdx = insuranceIdx;
   }
   public int getFee() {
      return fee;
   }
   public void setFee(int fee) {
      this.fee = fee;
   }
   public boolean isCompleted() {
      return completed;
   }
   public void setCompleted(boolean completed) {
      this.completed = completed;
   } 
   public String toStringAll() {
        String stringReturn =  this.contractIdx + " " + this.created+ " " + this.fee + " "+
    this.checkpay+ " " + this.userIdx+ " " + this.employeeIdx+ " " + this.insuranceIdx;
        return stringReturn;
    }
}