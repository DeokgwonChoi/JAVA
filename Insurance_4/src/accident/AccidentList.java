package accident;

import java.util.List;

public interface AccidentList {

	public boolean createAccident(Accident accident);
	public List<Accident> getAccidentList();
	public boolean deleteAccident(Long accidentIdx);
	public boolean modifyCheckAccident(Long accidentIdx);
	public boolean modifyCompensationPrice(Long accidentIdx, int price);
	public boolean modifyLawsuitStatus(Long accidentidx);
}

 

 