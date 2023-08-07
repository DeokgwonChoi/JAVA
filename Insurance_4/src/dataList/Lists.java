package dataList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import accident.Accident;
import accident.AccidentList;
import accident.AccidentListImpl;
import contract.Contract;
import contract.ContractList;
import contract.ContractListImpl;
import employee.Employee;
import employee.EmployeeList;
import employee.EmployeeListImpl;
import insurance.Car;
import insurance.Fire;
import insurance.Health;
import insurance.Insurance;
import insurance.InsuranceList;
import insurance.InsuranceListImpl;
import insurance.Travel;
import salesExpense.SalesExpense;
import salesExpense.SalesExpenseList;
import salesExpense.SalesExpenseListImpl;
import user.User;
import user.UserList;
import user.UserListImpl;
import uwRule.UwRule;
import uwRule.UwRuleList;
import uwRule.UwRuleListImpl;

public class Lists {

	private UserList userList;
	private EmployeeList employeeList;
	private AccidentList accidentList;
	private ContractList contractList;
	private InsuranceList insuranceList;
	private UwRuleList uwRuleList;
	private SalesExpenseList salesExpenseList;
	
	public Lists() {
		this.userList = new UserListImpl();
		this.employeeList = new EmployeeListImpl();
		this.accidentList = new AccidentListImpl();
		this.contractList = new ContractListImpl();
		this.insuranceList = new InsuranceListImpl();
		this.uwRuleList = new UwRuleListImpl();
		this.salesExpenseList = new SalesExpenseListImpl();
	}

	public List<User> getUserList() {
		return this.userList.getUserList();
	}
	public User getUser(long userIdx) {
		return this.userList.getUser(userIdx);
	}
	public List<Accident> getAccidentList() {
		return this.accidentList.getAccidentList();
	}
	public List<Accident> getAccidentNotCompensation() {
		List<Accident> temp = new ArrayList<Accident>();
		for(Accident accident : this.accidentList.getAccidentList()) {
			if(accident.getCompensationPrice() == 0) {
				temp.add(accident);
			}
		}
		return temp;
	}
	public List<Contract> getContractList() {
		return this.contractList.getContractList();
	}
	public List<UwRule> getUwRuleList() {
		return this.uwRuleList.getUwRuleList();
	}
	public List<SalesExpense> getSalesExpenseList() {
		return this.salesExpenseList.getSalesExpenseList();
	}
	
	// 보험 list 가져오기
	public List<Fire> getFireList() {
		return this.insuranceList.getFireInsuranceList();
	}

	public List<Car> getCarList() {
		return this.insuranceList.getCarInsuranceList();
	}

	public List<Health> getHealthList() {
		return this.insuranceList.getHealthInsuranceList();
	}

	public List<Travel> getTravelList() {
		return this.insuranceList.getTravelInsuranceList();
	}

	public List<Employee> getEmployeeList() {
		return this.employeeList.getEmployeeList();
	}

	// 회원가입
	public boolean addEmployee(Employee employee) {
		return this.employeeList.SignUp(employee);
	}

	public boolean addUser(User user) {
		return this.userList.SignUp(user);
	}

	// 사고추가
	public boolean addAccident(Accident accident) {
		return this.accidentList.createAccident(accident);
	}

	// 계약 추가
	public boolean addContract(Contract contract) {
		return this.contractList.createContract(contract);
	}

	// 보험 추가
	public boolean addCarInsurance(Car car) {
		return this.insuranceList.addCarInsurance(car);
	}

	public boolean addFireInsurance(Fire fire) {
		return this.insuranceList.addFireInsurance(fire);
	}

	public boolean addTravelInsurance(Travel travel) {
		return this.insuranceList.addTravelInsurnace(travel);
	}
	public boolean addHealthInsurance(Health health) {
		return this.insuranceList.addHealthInsurnace(health);
	}
	
	//인수정책추가
	public boolean addUwRule(UwRule uwRule) {
		return this.uwRuleList.createUwRule(uwRule);
	}
	//영업비기준정책추가
	public boolean addSalesExpense(SalesExpense salesExpense) {
		return this.salesExpenseList.createSalesExpense(salesExpense);
	}
		
	public boolean deleteEmployee(Long employeeIdx) {
		return this.employeeList.deleteEmployee(employeeIdx);
	}

	public boolean deleteAccident(Long accidentIdx) {
		return this.accidentList.deleteAccident(accidentIdx);
	}

	public boolean deleteContract(Long contractIdx) {
		return this.contractList.deleteContract(contractIdx);
	}

	public boolean deleteUser(Long userIdx) {
		return this.userList.deleteUser(userIdx);
	}

	// Contract method 수정
	public void modifyCheckSug(Long contractIdx) {
		this.contractList.modifyCheckSug(contractIdx);
	}
	public void modifyCheckSub(Long contractIdx) {
		this.contractList.modifyCheckSub(contractIdx);
	}
	public void modifyCheckUw(Long contractIdx) {
		this.contractList.modifyCheckUw(contractIdx);
	}
	public void modifyCheckPay(Long contractIdx) {
		this.contractList.modifyCheckPay(contractIdx);
	}
	public void modifyCompleted(Long contractIdx) {
		this.contractList.modifyCompleted(contractIdx);
	}

	public boolean modifyCheckAccident(Long accidentIdx) {
		return this.accidentList.modifyCheckAccident(accidentIdx);
	}
	public boolean modifyLawsuitStatus(Long accidentIdx) {
		return this.accidentList.modifyLawsuitStatus(accidentIdx);
	}
	public boolean modifyCompensationPrice(Long accidentIdx, int price) {
		return this.accidentList.modifyCompensationPrice(accidentIdx, price);
	}
	public boolean modifyEmployeeGrade(Long employeeIdx, String grade) {
		return this.employeeList.modifyEmployeeGrade(employeeIdx, grade);
	}

	// Contract 가져오는 method
	public List<Contract> getAlmostContractList() {
		//만기계약은 가입일로부터 30일 이후
		LocalDate time = LocalDate.now();
		List<Contract> temp = new ArrayList<Contract>();
		for(Contract contract : this.contractList.getContractList()) {
			LocalDate tempCreated = contract.getCreated().plusDays(30);
			if(time.isAfter(tempCreated)) {
				temp.add(contract);
			}
			System.out.println("--------------------");
		}
		return temp;
	}
	public List<Contract> getUserSugContract(Long userIdx) {
		List<Contract> temp = new ArrayList<Contract>();
		for (Contract contract : this.contractList.getContractList()) {
			if (!contract.isCheckSug() && contract.getUserIdx() == userIdx)
				temp.add(contract);
		}
		return temp;
	}
	public List<Contract> getUserSubContract(Long userIdx) {
		List<Contract> temp = new ArrayList<Contract>();
		for (Contract contract : this.contractList.getContractList()) {
			
			if (!contract.isCheckSub() && contract.getUserIdx() == userIdx)
				temp.add(contract);
		}
		return temp;
	}
	public List<Contract> getUwContract() {
		List<Contract> temp = new ArrayList<Contract>();
		for (Contract contract : this.contractList.getContractList()) {
			if (contract.isCheckSub() && contract.isCheckSub() && !contract.isCheckUw())
				temp.add(contract);
		}
		return temp;
	}

	public List<Contract> getFinalContract() {
		List<Contract> temp = new ArrayList<Contract>();
		for (Contract contract : this.contractList.getContractList()) {
			if (contract.isCheckUw() && !contract.isCompleted())
				temp.add(contract);
		}
		return temp;
	}

	public List<Contract> getCompleteContract() {
		List<Contract> temp = new ArrayList<Contract>();
		for (Contract contract : this.contractList.getContractList()) {
			if (contract.isCompleted())
				temp.add(contract);
		}
		return temp;
	}
	public List<Contract> getCheckPayContract(long userIdx) {
		List<Contract> temp = new ArrayList<Contract>();
		for(Contract contract : this.getCompleteContract()) {
			if(!contract.isCheckPay() && contract.getUserIdx() == userIdx) {
				temp.add(contract);
			}
		}
		return temp;
	}
	public List<Contract> getCheckPayContract() {
		List<Contract> temp = new ArrayList<Contract>();
		for(Contract contract : this.getCompleteContract()) {
			if(!(contract.isCheckPay())) {
				//System.out.println(contract.isCheckPay());
				temp.add(contract);
			}
		}
		return temp;
	}
	public List<Accident> getCheckAccidentList() {
		List<Accident> temp = new ArrayList<Accident>();
		for (Accident accident : this.accidentList.getAccidentList()) {
			if (!accident.isCheckAccident()) {
				temp.add(accident);
			}
		}
		return temp;
	}
	public Accident getAccident(long accidentIdx) {
		Accident temp = null;
		for (Accident accident : this.accidentList.getAccidentList()) {
			if (accident.getAccidentIdx() == accidentIdx) {
				temp = accident;
			}
		}
		return temp;
	}
	public Employee getEmployee(long employeeIdx) {
		Employee temp = null;
		for (Employee employee : this.employeeList.getEmployeeList()) {
			if (employee.getEmployeeIdx() == employeeIdx) {
				temp = employee;
			}
		}
		return temp;
	}
	public long getUserFireList(Long userIdx) {
		List<Fire> res = new ArrayList<Fire>();
		List<Contract> list = this.contractList.getContractList();
		for (Contract contract : list) {
			if (contract.getUserIdx() == userIdx) {
				List<Fire> insuranceList = this.insuranceList.getFireInsuranceList();
				for (Fire fire : insuranceList) {
					if (fire.getInsuranceIdx() == contract.getInsuranceIdx()) {
						res.add(fire);
					}
				}
			}
		}
		if(res.size() == 0) {
			return -1;
		}
		return res.get(0).getInsuranceIdx();
	}
	public long getUserCarList(Long userIdx) {
		List<Car> res = new ArrayList<Car>();
		List<Contract> list = this.contractList.getContractList();
		for (Contract contract : list) {
			if (contract.getUserIdx() == userIdx) {
				List<Car> insuranceList = this.insuranceList.getCarInsuranceList();
				for (Car car : insuranceList) {
					if (car.getInsuranceIdx() == contract.getInsuranceIdx()) {
						res.add(car);
					}
				}
			}
		}
		if(res.size() == 0) {
			return -1;
		}
		return res.get(0).getInsuranceIdx();
	}
	public long getUserHealthList(Long userIdx) {
		List<Health> res = new ArrayList<Health>();
		List<Contract> list = this.contractList.getContractList();
		for (Contract contract : list) {
			if (contract.getUserIdx() == userIdx) {
				List<Health> insuranceList = this.insuranceList.getHealthInsuranceList();
				for (Health health : insuranceList) {
					if (health.getInsuranceIdx() == contract.getInsuranceIdx()) {
						res.add(health);
					}
				}
			}
		}
		if(res.size() == 0) {
			return -1;
		}
		return res.get(0).getInsuranceIdx();
	}
	public long getUserTravelList(Long userIdx) {
		List<Travel> res = new ArrayList<>();
		List<Contract> list = this.contractList.getContractList();
		for (Contract contract : list) {
			if (contract.getUserIdx() == userIdx) {
				List<Travel> insuranceList = this.insuranceList.getTravelInsuranceList();
				for (Travel travel : insuranceList) {
					if (travel.getInsuranceIdx() == contract.getInsuranceIdx()) {
						res.add(travel);
					}
				}
			}
		}
		if(res.size() == 0) {
			return -1;
		}
		return res.get(0).getInsuranceIdx();
	}





}
