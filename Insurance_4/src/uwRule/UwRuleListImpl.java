package uwRule;

import java.util.List;

import dao.UwRuleDao;

public class UwRuleListImpl implements UwRuleList{

	private UwRuleDao uwRuleDao = new UwRuleDao();
	
	@Override
	public boolean createUwRule(UwRule uwRule) {
		return uwRuleDao.addUwRule(uwRule);
	}

	@Override
	public List<UwRule> getUwRuleList() {
		return uwRuleDao.getUwRuleList();
	}

}
