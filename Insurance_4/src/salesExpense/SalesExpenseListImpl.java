package salesExpense;

import java.util.List;

import dao.SalesExpenseDao;

public class SalesExpenseListImpl implements SalesExpenseList{

	private SalesExpenseDao salesExpenseDao = new SalesExpenseDao();
	@Override
	public boolean createSalesExpense(SalesExpense salesExpense) {
		return salesExpenseDao.addSalesExpense(salesExpense);
	}

	@Override
	public List<SalesExpense> getSalesExpenseList() {
		return salesExpenseDao.getSalesExpenseList();
	}

}
