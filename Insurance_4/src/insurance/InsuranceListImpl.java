package insurance;

import java.util.List;

import dao.InsuranceDao;

public class InsuranceListImpl implements InsuranceList {
	
	private InsuranceDao insuranceDao = new InsuranceDao();

	@Override
	public boolean addInsurance(Insurance insurance) {
		return insuranceDao.addInsurance(insurance);
	}

	@Override
	public boolean addCarInsurance(Car car) {
		insuranceDao.addInsurance(car);
		long idx = insuranceDao.findInsuranceIdx();
		return insuranceDao.addCarInsurance(car, idx);
	}
	@Override
	public boolean addFireInsurance(Fire fire) {
		insuranceDao.addInsurance(fire);
		long idx = insuranceDao.findInsuranceIdx();
		return insuranceDao.addFireInsurance(fire , idx );
	}
	@Override
	public boolean addHealthInsurnace(Health health) {
		insuranceDao.addInsurance(health);
		long idx = insuranceDao.findInsuranceIdx();
		return insuranceDao.addHealthInsurance(health, idx);
	}
	@Override
	public boolean addTravelInsurnace(Travel travel) {
		insuranceDao.addInsurance(travel);
		long idx = insuranceDao.findInsuranceIdx();
		return insuranceDao.addTravelInsurance(travel, idx);
	}
	@Override
	public List<Car> getCarInsuranceList() {
		List<Car> carInsuranceLists = insuranceDao.getCarInsuranceList();
		return carInsuranceLists;
	}

	@Override
	public List<Fire> getFireInsuranceList() {
		List<Fire> fireInsuranceLists = insuranceDao.getFireInsuranceList();
		return fireInsuranceLists;
	}

	@Override
	public List<Health> getHealthInsuranceList() {
		List<Health> healthInsuranceLists = insuranceDao.getHealthnsuranceList();
		return healthInsuranceLists;
	}

	@Override
	public List<Travel> getTravelInsuranceList() {
		List<Travel> travelInsuranceLists = insuranceDao.getTravelInsuranceList();
		return travelInsuranceLists;
	}

}
