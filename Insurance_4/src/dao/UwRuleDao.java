package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import uwRule.UwRule;

public class UwRuleDao extends Dao{
	public UwRuleDao() {
		super.connect();
	}

	public boolean addUwRule(UwRule uwRule) {
		String sql = "INSERT INTO insurance.uwrule(title, content, employeeIdx)"
				+ " VALUES("+
						"'" + uwRule.getTitle()+"', "+
						"'" + uwRule.getContent()+"', "+
						"'" + uwRule.getEmployeeIdx()+"');";
		return super.create(sql);
	}
	
	public List<UwRule> getUwRuleList() {
		String sql = "SELECT * FROM insurance.uwrule";
		ResultSet rs = super.retrieve(sql);
		List<UwRule> uwRuleList = new ArrayList<>();
		try {
			while(rs.next()) {
			    String idx = rs.getString(1);
			    String title = rs.getString(2);
			    String content = rs.getString(3);
			    String employeeIdx = rs.getString(4);
			   
			    UwRule uwRule = new UwRule();
			    uwRule.setUwRuleIdx(Long.parseLong(idx));
			    uwRule.setTitle(title);
			    uwRule.setContent(content);
			    uwRule.setEmployeeIdx(Long.parseLong(employeeIdx));
			    uwRuleList.add(uwRule);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return uwRuleList;

	}

}
