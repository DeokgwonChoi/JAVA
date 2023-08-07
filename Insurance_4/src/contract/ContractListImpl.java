package contract;

 

import java.util.List;

import dao.ContractDao;

 
public class ContractListImpl implements ContractList{

	private ContractDao contractDao = new ContractDao();
	public ContractListImpl( ) {
	}

	@Override
	public boolean createContract(Contract contract) {
		return contractDao.addContract(contract);
	}

	@Override	
	public List<Contract> getContractList() {
		List<Contract> contractList = contractDao.findContractList();
		return contractList;
	}

	@Override
	public boolean deleteContract(Long contractIdx) {
		return contractDao.deleteContract(contractIdx);
	}

	@Override
	public void modifyCheckSug(Long contractIdx) {
		contractDao.modifyCheckSug(contractIdx);
	}
	@Override
	public void modifyCheckSub(Long contractIdx) {
		contractDao.modifyCheckSub(contractIdx);
	}
	@Override
	public void modifyCheckUw(Long contractIdx) {
		contractDao.modifyCheckUw(contractIdx);
	}
	@Override
	public void modifyCompleted(Long contractIdx) {
		contractDao.modifyCompleted(contractIdx);
	}

	@Override
	public void modifyCheckPay(Long contractIdx) {
		contractDao.modifyCheckPay(contractIdx);
	}
}
