package contract;

 

import java.util.List;

 

public interface ContractList {

	public boolean createContract(Contract contract);
	public List<Contract> getContractList();
	public boolean deleteContract(Long contractIdx);
//	public boolean updateContract();
	public void modifyCheckSug(Long contractIdx);
	public void modifyCheckSub(Long contractIdx);
	public void modifyCheckUw(Long contractIdx);
	public void modifyCompleted(Long contractIdx);
	public void modifyCheckPay(Long contractIdx);
	
}

 

 

 