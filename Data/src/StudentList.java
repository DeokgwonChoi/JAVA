
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class StudentList {
	protected ArrayList<Student> vStudent;
	String allStudentId;

	public StudentList(String sStudentFileName) throws FileNotFoundException, IOException {
		BufferedReader objStudentFile = new BufferedReader(new FileReader(sStudentFileName));
		this.vStudent = new ArrayList<Student>();
		while (objStudentFile.ready()) {
			String stuInfo = objStudentFile.readLine();
			//파일의 내용을 한줄씩 읽는다.
			if (!stuInfo.equals("")) {
				this.vStudent.add(new Student(stuInfo));
				//읽은 내용을 특정 형식(Student)으로 저장한다.
			}
		}
		objStudentFile.close();
	}

	public ArrayList<Student> getAllStudentRecords() throws NullDataException {
		if(this.vStudent.size()==0) throw new NullDataException("~~~~~~~~~~~~~~Student data is null~~~~~~~~~~~~~~");
		return this.vStudent;
	}
	
	public boolean addStudentRecords(String studentInfo) {
		if(this.vStudent.add(new Student(studentInfo))) return true;
		//Data에서 받은 값을 ArrayList<Student>형식에 맞추어vStudent에 추가한다.
		else return false;
		
	}
	public boolean deleteStudentRecords(String studentId) {
		for (int i = 0; i < this.vStudent.size(); i++) {
			Student student = (Student) this.vStudent.get(i);
			if (student.match(studentId)) {
				//학생정보를 하나씩 뽑아내어 같은 학생정보를 찾아낸다.
				if(this.vStudent.remove(student))return true;
				//해당 학생 정보를 전체 리스트에서 삭제한다.
				//삭제하는데 성공했다면 해당 True를 리턴하여 Server를 거쳐 Client에게 알려준다.
				else return false;
			}
		}
		return false;
	}
	public boolean isRegisteredStudent(String sSID) {
		for (int i = 0; i < this.vStudent.size(); i++) {
			Student objStudent = (Student) this.vStudent.get(i);
			if (objStudent.match(sSID)) {
				return true;
			}
		}
		return false;
	}

	public void saveStudent(String fileAddress) {
		File file = new File(fileAddress);
		file.delete();//기존에 있던 데이터를 지운다.
		String list="";
		for(int i=0; i<this.vStudent.size(); i++) {
			list += this.vStudent.get(i) + "\n";
		}
		//현재까지의 vStudent내용을 Text파일에 들어갈 내용의 문자열로 변환한다.
		try {
			BufferedWriter writer= new BufferedWriter(new FileWriter(file));		
			writer.write(list);
			writer.close();
			//변환한 내용을 파일에 다시 넣는다.
		} catch (IOException e) {
			e.printStackTrace();
		}			
	}
}
