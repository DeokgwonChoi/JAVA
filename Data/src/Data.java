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
			//Data�� Server�� �����ϱ� ���� "Data"��� �̸����� �����ε� �Ѵ�.
			System.out.println("Data is ready !!!");		
			studentList = new StudentList("Students.txt");
			courseList = new CourseList("Courses.txt");
			reservationList = new ReservationList("Reservations.txt");
			//����� Text���Ͽ��� ������ �ҷ��� ������ �������� ����Ʈ�� �����Ѵ�.
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
			//StudentList�� addStudentRecords�� ���� ������ ���� �л������� �л� ����Ʈ�� �����ϰ�,
			//���μ������� ������ ���� �ߴٸ� ���Ͽ��� �����ϴ� saveStudent�� �����Ͽ� �����Ѵ�.
			return true;
		}		
		else return false;
	}
	@Override
	public boolean deleteStudent(String studentId) throws RemoteException {
		if(studentList.deleteStudentRecords(studentId)) {
			//������ ���� ���� �л� ID�� StuentList�� deleteStudent�� ���� ������ �����ϰ�,
			studentList.saveStudent("Students.txt");		
			//�����ϴµ� ���������� ����� ������ �����Ѵ�
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