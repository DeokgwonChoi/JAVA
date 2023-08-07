package salesExpense;

import java.util.List;

public interface SalesExpenseList {
	public boolean createSalesExpense(SalesExpense salesExpense);
	public List<SalesExpense> getSalesExpenseList();

}
