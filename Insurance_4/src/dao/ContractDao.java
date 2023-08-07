package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import contract.Contract;

public class ContractDao extends Dao {
	public ContractDao() {
		super.connect();
	}

	public boolean addContract(Contract contract) {
		int checkSub = 0;
		int checkSug = 0;
		int checkUw = 0;
		int checkPay = 0;

		String sql = "INSERT INTO insurance.contract(created, subScription, suggestion, userIdx, employeeIdx, insuranceIdx, fee) VALUES("
				+ "'" + contract.getCreated() + "', " + "'" + contract.getSubscription() + "', " + "'"
				+ contract.getSuggestion() + "', " + "'" + contract.getUserIdx() + "', " + "'"
				+ contract.getEmployeeIdx() + "', " + "'" + contract.getInsuranceIdx() + "', " + "'" + contract.getFee()
				+ "');";
		return super.create(sql);
	}

	public List<Contract> findContractList() {
		String sql = "SELECT * FROM insurance.contract";
		ResultSet rs = super.retrieve(sql);
		List<Contract> contractList = new ArrayList<>();
		boolean bolCheckSub = true;
		boolean bolCheckSug = true;
		boolean bolCheckUw = true;
		boolean bolCheckpay = true;
		boolean bolCompleted = true;
		try {
			while (rs.next()) {
				String contractIdx = rs.getString(1);
				String created = rs.getString(2);
				String subscription = rs.getString(3);
				String suggestion = rs.getString(4);
				String checkSub = rs.getString(5);
				String checkSug = rs.getString(6);
				String checkUw = rs.getString(7);
				String checkpay = rs.getString(8);
				String userIdx = rs.getString(9);
				String employeeIdx = rs.getString(10);
				String insuranceIdx = rs.getString(11);
				String completed = rs.getString(12);
				String fee = rs.getString(13);
				if (checkSub.equals("0"))
					bolCheckSub = false;
				if (checkSug.equals("0"))
					bolCheckSug = false;
				if (checkUw.equals("0"))
					bolCheckUw = false;
				if (checkpay.equals("0"))
					bolCheckpay = false;
				if (completed.equals("0"))
					bolCompleted = false;

				Contract contract = new Contract();
				contract.setContractIdx(Long.parseLong(contractIdx));
				
				LocalDate date = LocalDate.parse(created, DateTimeFormatter.ISO_DATE);
				
				contract.setCreated(date);
				contract.setSubscription(subscription);
				contract.setSuggestion(suggestion);
				contract.setCheckSub(bolCheckSub);
				contract.setCheckSug(bolCheckSug);
				contract.setCheckUw(bolCheckUw);
				contract.setCheckPay(bolCheckpay);
				if (userIdx == null)
					contract.setUserIdx(null);
				else
					contract.setUserIdx(Long.parseLong(userIdx));
				if (employeeIdx == null)
					contract.setEmployeeIdx(null);
				else
					contract.setEmployeeIdx(Long.parseLong(employeeIdx));
				if (insuranceIdx == null)
					contract.setInsuranceIdx(null);
				else
					contract.setInsuranceIdx(Long.parseLong(insuranceIdx));

				contract.setEmployeeIdx(Long.parseLong(employeeIdx));
				contract.setInsuranceIdx(Long.parseLong(insuranceIdx));
				contract.setCompleted(bolCompleted);
				contract.setFee(Integer.parseInt(fee));
				contractList.add(contract);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return contractList;
	}

	public boolean deleteContract(Long contractIdx) {
		String sql = "delete from insurance.contract where contractIdx=" + "'" + contractIdx + "'";
		return super.delete(sql);
	}

	public boolean modifyCheckSug(Long contractIdx) {
		String sql = "update insurance.contract set checkSug=1 where contractIdx = " + contractIdx + ";";
		return super.update(sql);
	}

	public boolean modifyCheckSub(Long contractIdx) {
		String sql = "update insurance.contract set checkSub=1 where contractIdx = " + contractIdx + ";";
		return super.update(sql);
	}

	public boolean modifyCheckUw(Long contractIdx) {
		String sql = "update insurance.contract set checkUw=1 where contractIdx = " + contractIdx + ";";
		return super.update(sql);
	}

	public boolean modifyCompleted(Long contractIdx) {
		String sql = "update insurance.contract set completed=1 where contractIdx = " + contractIdx + ";";
		return super.update(sql);
	}

	public boolean modifyCheckPay(Long contractIdx) {
		String sql = "update insurance.contract set checkpay=1 where contractIdx = " + contractIdx + ";";
		return super.update(sql);
	}
}