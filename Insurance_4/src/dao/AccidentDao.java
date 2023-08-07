package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import accident.Accident;

public class AccidentDao extends Dao{

	public AccidentDao() {
		super.connect();
	}
	
	public boolean addAccident(Accident accident) {
		int isCheckAccident = 0;
		int isLawsuitStatus = 0;
		if(accident.isCheckAccident()) isCheckAccident=1;
		if(accident.isLawsuitStatus()) isLawsuitStatus=1;
		LocalDate now = LocalDate.now();
		String sql = "INSERT INTO insurance.accident(userIdx, insuranceIdx,accidenttype, content, accidentDate, damagePrice)"
				+ " VALUES("+
						"'" + accident.getUserIdx()+"', "+
						"'" + accident.getInsuranceIdx()+"', "+
						"'" + accident.getAccidenttype()+"', "+
						"'" + accident.getContent()+"', "+
						"'" + now +"', "+
						"'" + accident.getDamagePrice()+"');";
		return super.create(sql);
	}
	
	public List<Accident> getAccidentList() {
		String sql = "SELECT * FROM insurance.accident";
		ResultSet rs = super.retrieve(sql);
		List<Accident> accidentLists = new ArrayList<>();
		boolean bolcheckAccident = true;
		boolean bollawsuitStatus = true;

		try {
			while(rs.next()) {
			    String idx = rs.getString(1);
			    String type = rs.getString(2);
			    String content = rs.getString(3);
			    String accidentDate = rs.getString(4);
			    String damagePrice = rs.getString(5);
			    String compensationPrice = rs.getString(6);
			    String userIdx = rs.getString(7);
			    String insuranceIdx = rs.getString(8);
			    String employeeIdx = rs.getString(9);
			    String checkAccident = rs.getString(10);
			    String lawsuitStaus = rs.getString(11);
			    
			    if(checkAccident.equals("0")) {
			    	bolcheckAccident= false;
			    }
			    if(lawsuitStaus.equals("0")) {
			    	bollawsuitStatus= false;
			    }
			    String dateString = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
				LocalDate time =LocalDate.parse(dateString);
			    
			    Accident accident = new Accident();
			    accident.setAccidentIdx(Long.parseLong(idx));
			    accident.setAccidenttype(type);
			    accident.setContent(content);
			    accident.setAccidentDate(time);
			    accident.setDamagePrice(Integer.parseInt(damagePrice));
			    
				if(compensationPrice==null) accident.setCompensationPrice(0);
				else accident.setCompensationPrice(Integer.parseInt(compensationPrice));
			    
				if(userIdx ==null) accident.setUserIdx(null);
				else accident.setUserIdx(Long.parseLong(userIdx));
				if(employeeIdx ==null) accident.setEmployeeIdx(null);
				else accident.setEmployeeIdx(Long.parseLong(employeeIdx));
				if(insuranceIdx ==null) accident.setInsuranceIdx(null);
				else accident.setInsuranceIdx(Long.parseLong(insuranceIdx));
				
			    accident.setCheckAccident(bolcheckAccident);
			    accident.setLawsuitStatus(bollawsuitStatus);
			    
			    accidentLists.add(accident);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return accidentLists;
    }

	public boolean deleteAccident(Long accidentIdx ) {
		String sql = "delete from insurance.accident where accidentIdx=" +
				"'"+ accidentIdx +"'";
		return super.delete(sql);
	}
	public boolean modifyCheckAccident(Long accidentIdx) {
		String sql = "update insurance.accident set checkAccident=1 where accidentIdx = " + accidentIdx + ";";
		return super.update(sql);
	}
	public boolean modifyCompensationPrice(Long accidentIdx, int price) {
		String sql = "update insurance.accident set compensationPrice= "+ price  +" where accidentIdx = " + accidentIdx + ";";
		return super.update(sql);
	}

	public boolean modifyLawsuitStatus(Long accidentIdx) {
		String sql = "update insurance.accident set lawsuitStatus=1 where accidentIdx = " + accidentIdx + ";";
		return super.update(sql);
	}
}