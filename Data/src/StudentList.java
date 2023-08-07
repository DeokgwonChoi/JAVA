
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
			//������ ������ ���پ� �д´�.
			if (!stuInfo.equals("")) {
				this.vStudent.add(new Student(stuInfo));
				//���� ������ Ư�� ����(Student)���� �����Ѵ�.
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
		//Data���� ���� ���� ArrayList<Student>���Ŀ� ���߾�vStudent�� �߰��Ѵ�.
		else return false;
		
	}
	public boolean deleteStudentRecords(String studentId) {
		for (int i = 0; i < this.vStudent.size(); i++) {
			Student student = (Student) this.vStudent.get(i);
			if (student.match(studentId)) {
				//�л������� �ϳ��� �̾Ƴ��� ���� �л������� ã�Ƴ���.
				if(this.vStudent.remove(student))return true;
				//�ش� �л� ������ ��ü ����Ʈ���� �����Ѵ�.
				//�����ϴµ� �����ߴٸ� �ش� True�� �����Ͽ� Server�� ���� Client���� �˷��ش�.
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
		file.delete();//������ �ִ� �����͸� �����.
		String list="";
		for(int i=0; i<this.vStudent.size(); i++) {
			list += this.vStudent.get(i) + "\n";
		}
		//��������� vStudent������ Text���Ͽ� �� ������ ���ڿ��� ��ȯ�Ѵ�.
		try {
			BufferedWriter writer= new BufferedWriter(new FileWriter(file));		
			writer.write(list);
			writer.close();
			//��ȯ�� ������ ���Ͽ� �ٽ� �ִ´�.
		} catch (IOException e) {
			e.printStackTrace();
		}			
	}
}
