import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Data extends UnicastRemoteObject implements DataIF{
	private static final long serialVersionUID = 1L;
	protected static StudentList studentList;
	protected static CourseList courseList;
	protected static ReservationList reservationList;
	protected Data() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws FileNotFoundException, IOException {
		try {
			Data data=new Data();
			Naming.rebind("Data", data);
			//Data를 Server와 연결하기 위해 "Data"라는 이름으로 리바인드 한다.
			System.out.println("Data is ready !!!");		
			studentList = new StudentList("Students.txt");
			courseList = new CourseList("Courses.txt");
			reservationList = new ReservationList("Reservations.txt");
			//저장된 Text파일에서 정보를 불러와 설정한 형식으로 리스트에 저장한다.
		}catch(RemoteException e) {
			e.printStackTrace();
		}catch(MalformedURLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<Student> getAllStudentData() throws RemoteException, NullDataException {
		
		return studentList.getAllStudentRecords();
	}
	public ArrayList<Course> getAllCourseData() throws RemoteException, NullDataException {
		
		return courseList.getAllCourseRecords();
	}
	public ArrayList<Reservation> getAllReservationData() throws RemoteException, NullDataException {
		return reservationList.getAllReservationRecords();
	}
	@Override
	public boolean addStudent(String studentInfo) throws RemoteException {
		if(studentList.addStudentRecords(studentInfo)) {
			studentList.saveStudent("Students.txt");
			//StudentList의 addStudentRecords를 통해 서버로 받은 학생정보를 학생 리스트에 저장하고,
			//프로세스내에 저장이 성공 했다면 파일에도 저장하는 saveStudent를 실행하여 저장한다.
			return true;
		}		
		else return false;
	}
	@Override
	public boolean deleteStudent(String studentId) throws RemoteException {
		if(studentList.deleteStudentRecords(studentId)) {
			//서버를 통해 받은 학생 ID를 StuentList의 deleteStudent를 통해 삭제를 진행하고,
			studentList.saveStudent("Students.txt");		
			//삭제하는데 성공했으면 변경된 내용을 저장한다
			return true;
		}
		else return false;
	}
	public boolean addCourse(String courseInfo) throws RemoteException {
		if(courseList.addCourseRecords(courseInfo)) {
			courseList.saveCourse("Courses.txt");
			return true;
		}
		else return false;
	}

	public boolean deleteCourse(String courseId) throws RemoteException {
		if(courseList.deleteCourseRecords(courseId)) {
			courseList.saveCourse("Courses.txt");
			return true;
		}
		else return false;
	}

	@Override
	public boolean addReservation(String reservationInfo) throws RemoteException {
		if(reservationList.addReservationRecords(reservationInfo)) {
			reservationList.saveReservation("Reservations.txt");
			return true;
		}
		else return false;
	}


}