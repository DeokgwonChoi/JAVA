import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Server extends UnicastRemoteObject implements ServerIF{
	
	private static DataIF data;
	private static final long serialVersionUID = 1L;
	protected Server() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws NotBoundException {
		// TODO Auto-generated method stub
		try {
			Server server=new Server();
			Naming.rebind("Server", server);
			System.out.println("Server is ready !!!");
			data = (DataIF)Naming.lookup("Data");

		}catch(RemoteException e) {
			e.printStackTrace();
		}catch(MalformedURLException e) {
			e.printStackTrace();
		}

	}
	public ArrayList<Student> getAllStudentData() throws RemoteException, NullDataException {	
		return data.getAllStudentData();
		//Data���� �л� ������ �޾ƿ´�.
	}

	@Override
	public ArrayList<Course> getAllCourseData() throws RemoteException, NullDataException {
		// TODO Auto-generated method stub
		return data.getAllCourseData();
	}
	@Override
	public ArrayList<Reservation> getAllReservationData() throws RemoteException, NullDataException {
		// TODO Auto-generated method stub
		return data.getAllReservationData();
	}
	@Override
	public boolean addStudent(String studentInfo) throws RemoteException {
		if(data.addStudent(studentInfo)) return true;
		//Client���� ���� Student Information���� Data�� addStudent�� �����Ѵ�.
		else return false;
	}

	@Override
	public boolean deleteStudent(String studentId) throws RemoteException {
		if(data.deleteStudent(studentId)) return true;
		else return false;
	}

	@Override
	public boolean addCourse(String courseInfo) throws RemoteException {
		if(data.addCourse(courseInfo)) return true;
		else return false;
	}

	@Override
	public boolean deleteCourse(String courseId) throws RemoteException {
		if(data.deleteCourse(courseId)) return true;
		else return false;
	}

	@Override
	public String reservation(String reservationInfo) throws RemoteException, NullDataException {
		 StringTokenizer stringTokenizer = new StringTokenizer(reservationInfo);
	    	String studentId = stringTokenizer.nextToken();
	    	String courseId = stringTokenizer.nextToken();
	   if(checkStudent(studentId)==false) return "noStudent";
	   //checkStudent�� ���� ����ڰ� �Է��� �л� ID�� ������ Client�� "noStudent"�� �����Ѵ�.
	   if(checkCourse(courseId)==false) return "noCourse";
	   //checkCourse�� ���� ����ڰ� �Է��� ���� ID�� ������ Client�� "noCourse"�� �����Ѵ�.
	   if(checkpreCourse(studentId,courseId)==false) return "noComplete";
	   //�ش� �л��� �� �̼������� �̼����� �ʾҴٸ� Client�� "noComplete"�� �����Ѵ�.
	   data.addReservation(reservationInfo);
	   //���� ������ ����ϸ� Data�� addReservation�� ���� ������û�� �̾����.
		return reservationInfo+" �Է¿Ϸ�";	    
	}
	private boolean checkStudent(String studentId) throws RemoteException, NullDataException {
		ArrayList<Student> vStudent;
		vStudent=data.getAllStudentData();
		//������ ��� �л� ������ �ҷ��´�.
		for (int i = 0; i < vStudent.size(); i++) {
			Student student = (Student) vStudent.get(i);
			if (student.match(studentId)) {
				//����ڷκ��� �Է¹��� ���� �ִ��� Ȯ���Ѵ�.
				return true;
			}
		}
		return false;
	}

	private boolean checkCourse(String courseId) throws RemoteException, NullDataException {
		ArrayList<Course> vCourse;
		vCourse=data.getAllCourseData();
		for (int i = 0; i < vCourse.size(); i++) {
			Course course = (Course) vCourse.get(i);
			if (course.match(courseId)) {
				return true;
			}
		}
		return false;
	}
	private boolean checkpreCourse(String studentId,String courseId) throws RemoteException, NullDataException {
		ArrayList<String> mPrecourse = new ArrayList<String>();//�����ؾ߸� �ϴ� ����
		ArrayList<String> completedCourse = new ArrayList<String>();//������ ����
		ArrayList<Student> vStudent;
		vStudent=data.getAllStudentData();
		for (int i = 0; i < vStudent.size(); i++) {
			Student student = (Student) vStudent.get(i);
			if (student.match(studentId)) {
				completedCourse = student.getCompletedCourses();
			}//�ش� �л��� ã�Ƴ��� �� �л��� ������ ������� �����Ѵ�.
		}
		ArrayList<Course> vCourse;
		vCourse=data.getAllCourseData();
		for (int i = 0; i < vCourse.size(); i++) {
			Course course = (Course) vCourse.get(i);
			if (course.match(courseId)) {
				mPrecourse=course.getpreCourses();
			}
		}//�ش� ������ ã�Ƴ��� �� ������ �� �̼������� �����Ѵ�.
		if(completedCourse.containsAll(mPrecourse))
			//�� �̼������� �л��� ������ ���� ��� ���ѵǾ� �ִ��� containsAll�Լ��� ���� �˾Ƴ�����.
			return true;
		else return false;				
	}





}
