package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import insurance.Car;
import insurance.Fire;
import insurance.Health;
import insurance.Insurance;
import insurance.Travel;

public class InsuranceDao extends Dao{
	
	
	public InsuranceDao() {
		super.connect();
	}
	
   public boolean addInsurance(Insurance insurance) {
		String sql = "INSERT INTO insurance.insurance(content, fee, maxReward, period, rate, employeeIdx) VALUES("+
				"'" + insurance.getContent()+"', "+
				"'" + insurance.getFee()+"', "+
				"'" + insurance.getMaxReward()+"', "+
				"'" + insurance.getPeriod()+"', "+
				"'" + insurance.getRate()+"'," + 
				"'" + insurance.getEmployeeIdx()+"')"
						+ ";";
		return super.create(sql);
	}

   public boolean addCarInsurance(Car car , Long insuranceIdx) {
		String sql = "INSERT INTO insurance.car VALUES("+
				"'" + insuranceIdx+"', "+
				"'" + car.getAccidentRecord()+"', "+
				"'" + car.getCarCount()+"', "+
				"'" + car.getCarNo()+"', "+
				"'" + car.getPrice()+"');";
		return super.create(sql);
	}
   
   public boolean addFireInsurance(Fire fire, Long insuranceIdx) {
		String sql = "INSERT INTO insurance.fire VALUES("+
				"'" + insuranceIdx+"', "+
				"'" + fire.getBuildingCount()+"', "+
				"'" + fire.getBuildingPrice()+"');";
		return super.create(sql);
	}
   
   public boolean addHealthInsurance(Health health, Long insuranceIdx) {
		String sql = "INSERT INTO insurance.health VALUES("+
				"'" + insuranceIdx+"', "+
				"'" + health.getHealthGrade()+"');";
		return super.create(sql);
	}
   
   public boolean addTravelInsurance(Travel travel, Long insuranceIdx) {
		String sql = "INSERT INTO insurance.travel VALUES("+
				"'" + insuranceIdx+"', "+
				"'" + travel.getCountry()+"', "+
				"'" + travel.getPeriod()+"');";
		return super.create(sql);
	}
   
    public List<Car> getCarInsuranceList() {
		String sql = "SELECT * FROM insurance.car natural join insurance.insurance";
		ResultSet rs = super.retrieve(sql);
		List<Car> carInsuranceList = new ArrayList<>();
		
		try {
			while(rs.next()) {
				String insuranceIdx = rs.getString(1);
				String accidentRecord = rs.getString(2);
				String carCount = rs.getString(3);
				String carNo = rs.getString(4);
				String price = rs.getString(5);
				String content = rs.getString(6);
				String fee = rs.getString(7);
				String maxReward = rs.getString(8);			    	
				String period = rs.getString(9);			    
				String rate = rs.getString(10);			    
				String employeeIdx = rs.getString(11);			    

				Car carInsurance = new Car();
				
				carInsurance.setInsuranceIdx(Long.parseLong(insuranceIdx));
				carInsurance.setAccidentRecord(Integer.parseInt(accidentRecord));
				carInsurance.setCarCount(Integer.parseInt(carCount));
				carInsurance.setCarNo(carNo);
				carInsurance.setPrice(Integer.parseInt(price));
				carInsurance.setContent(content);
				carInsurance.setFee(Integer.parseInt(fee));
				carInsurance.setMaxReward(Integer.parseInt(maxReward));
				carInsurance.setPeriod(Integer.parseInt(period));
				carInsurance.setRate(Integer.parseInt(rate));
				carInsurance.setEmployeeIdx(Long.parseLong(employeeIdx));
				carInsuranceList.add(carInsurance);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return carInsuranceList;
	    }
    
    
    public List<Fire> getFireInsuranceList() {
		String sql = "SELECT * FROM insurance.Fire natural join insurance.insurance";
		ResultSet rs = super.retrieve(sql);
		List<Fire> fireInsuranceList = new ArrayList<>();
		
		try {
			while(rs.next()) {
				String insuranceIdx = rs.getString(1);
				String buildingCount = rs.getString(2);
				String buildingPrice = rs.getString(3);
				String content = rs.getString(4);
				String fee = rs.getString(5);
				String maxReward = rs.getString(6);			    	
				String period = rs.getString(7);			    
				String rate = rs.getString(8);			    
				String employeeIdx = rs.getString(9);			    

				Fire fireInsurance = new Fire();
				
				fireInsurance.setInsuranceIdx(Long.parseLong(insuranceIdx));
				fireInsurance.setBuildingCount(Integer.parseInt(buildingCount));
				fireInsurance.setBuildingPrice(Integer.parseInt(buildingPrice));
				fireInsurance.setContent(content);
				fireInsurance.setFee(Integer.parseInt(fee));
				fireInsurance.setMaxReward(Integer.parseInt(maxReward));
				fireInsurance.setPeriod(Integer.parseInt(period));
				fireInsurance.setRate(Integer.parseInt(rate));
				fireInsurance.setEmployeeIdx(Long.parseLong(employeeIdx));
				fireInsuranceList.add(fireInsurance);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return fireInsuranceList;
	    }
    public List<Health> getHealthnsuranceList() {
		String sql = "SELECT * FROM insurance.Health natural join insurance.insurance";
		ResultSet rs = super.retrieve(sql);
		List<Health> healthInsuranceList = new ArrayList<>();
		try {
			while(rs.next()) {
				String insuranceIdx = rs.getString(1);
				String healthGrade = rs.getString(2);
				String content = rs.getString(3);
				String fee = rs.getString(4);
				String maxReward = rs.getString(5);			    	
				String period = rs.getString(6);			    
				String rate = rs.getString(7);			    
				String employeeIdx = rs.getString(8);			    

				Health healthInsurance = new Health();
				
				healthInsurance.setInsuranceIdx(Long.parseLong(insuranceIdx));
				healthInsurance.setHealthGrade(Integer.parseInt(healthGrade));
				healthInsurance.setContent(content);
				healthInsurance.setFee(Integer.parseInt(fee));
				healthInsurance.setMaxReward(Integer.parseInt(maxReward));
				healthInsurance.setPeriod(Integer.parseInt(period));
				healthInsurance.setRate(Integer.parseInt(rate));
				healthInsurance.setEmployeeIdx(Long.parseLong(employeeIdx));
				healthInsuranceList.add(healthInsurance);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return healthInsuranceList;
	    }

    public List<Travel> getTravelInsuranceList() {
		String sql = "SELECT * FROM insurance.Travel natural join insurance.insurance";
		ResultSet rs = super.retrieve(sql);
		List<Travel> travelInsuranceList = new ArrayList<>();
		
		try {
			while(rs.next()) {
				String insuranceIdx = rs.getString(1);
				String country = rs.getString(2);
				String travelperiod = rs.getString(3);
				String content = rs.getString(4);
				String fee = rs.getString(5);
				String maxReward = rs.getString(6);			    	
				String period = rs.getString(7);			    
				String rate = rs.getString(8);			    
				String employeeIdx = rs.getString(9);			    

				Travel travelInsurance = new Travel();
				
				travelInsurance.setInsuranceIdx(Long.parseLong(insuranceIdx));
				travelInsurance.setCountry(country);
				travelInsurance.setTravelPeriod(Integer.parseInt(travelperiod));
				travelInsurance.setContent(content);
				travelInsurance.setFee(Integer.parseInt(fee));
				travelInsurance.setMaxReward(Integer.parseInt(maxReward));
				travelInsurance.setPeriod(Integer.parseInt(period));
				travelInsurance.setRate(Integer.parseInt(rate));
				travelInsurance.setEmployeeIdx(Long.parseLong(employeeIdx));
				travelInsuranceList.add(travelInsurance);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return travelInsuranceList;
	    }

	public long findInsuranceIdx() {
		String sql = "select insuranceIdx FROM insurance.insurance order by insuranceIdx desc LIMIT 1;";
		ResultSet rs = super.retrieve(sql);
		long idx = -1;
		try {
			while(rs.next()) {
				idx = rs.getLong(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return idx;
	}

}
