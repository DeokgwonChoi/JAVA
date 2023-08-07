package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import salesExpense.SalesExpense;
import uwRule.UwRule;

public class SalesExpenseDao extends Dao{
	public SalesExpenseDao() {
		super.connect();
	}

	public boolean addSalesExpense(SalesExpense salesExpense) {
		String sql = "INSERT INTO insurance.salesExpense(title, content, employeeIdx)"
				+ " VALUES("+
						"'" + salesExpense.getTitle()+"', "+
						"'" + salesExpense.getContent()+"', "+
						"'" + salesExpense.getEmployeeIdx()+"');";
		return super.create(sql);
	}
	
	public List<SalesExpense> getSalesExpenseList() {
		String sql = "SELECT * FROM insurance.salesExpense";
		ResultSet rs = super.retrieve(sql);
		List<SalesExpense> uwSalesExpenseList = new ArrayList<>();
		try {
			while(rs.next()) {
			    String idx = rs.getString(1);
			    String title = rs.getString(2);
			    String content = rs.getString(3);
			    String employeeIdx = rs.getString(4);
			   
			    SalesExpense salesExpense = new SalesExpense();
			    salesExpense.setSalesExpenseIdx(Long.parseLong(idx));
			    salesExpense.setTitle(title);
			    salesExpense.setContent(content);
			    salesExpense.setEmployeeIdx(Long.parseLong(employeeIdx));
			    uwSalesExpenseList.add(salesExpense);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return uwSalesExpenseList;

	}

}
