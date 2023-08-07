package insurance;

import java.util.List;

public interface InsuranceList {

	public boolean addInsurance(Insurance insurance);
	public boolean addCarInsurance(Car car);
	public boolean addFireInsurance(Fire fire);
	public boolean addHealthInsurnace(Health health);
	public boolean addTravelInsurnace(Travel travel);
	
	public List<Car> getCarInsuranceList();
	public List<Fire> getFireInsuranceList();
	public List<Health> getHealthInsuranceList();
	public List<Travel> getTravelInsuranceList();
}
