
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ReservationList {
	protected ArrayList<Reservation> vReservation;
	String allStudentId;

	public ReservationList(String sReservationFileName) throws FileNotFoundException, IOException {
		BufferedReader objStudentFile = new BufferedReader(new FileReader(sReservationFileName));
		this.vReservation = new ArrayList<Reservation>();
		while (objStudentFile.ready()) {
			String reserInfo = objStudentFile.readLine();
			if (!reserInfo.equals("")) {
				this.vReservation.add(new Reservation(reserInfo));
			}
		}
		objStudentFile.close();
	}

	public ArrayList<Reservation> getAllReservationRecords() throws NullDataException {
		if(this.vReservation.size()==0) throw new NullDataException("~~~~~~~~~~~~~~Student data is null~~~~~~~~~~~~~~");
		return this.vReservation;
	}
	
	public boolean addReservationRecords(String reservationInfo) {
		if(this.vReservation.add(new Reservation(reservationInfo))) return true;
		else return false;
		
	}
	public void saveReservation(String fileAddress) {
		// TODO Auto-generated method stub
		File file = new File(fileAddress);
		file.delete();
		String list="";
		for(int i=0; i<this.vReservation.size(); i++) {
			list += this.vReservation.get(i) + "\n";
		}
		try {
			BufferedWriter writer= new BufferedWriter(new FileWriter(file));
			
			writer.write(list);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}