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
		//Data에게 학생 정보를 받아온다.
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
		//Client에서 받은 Student Information으로 Data의 addStudent를 실행한다.
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
	   //checkStudent를 통해 사용자가 입력한 학생 ID가 없으면 Client에 "noStudent"를 리턴한다.
	   if(checkCourse(courseId)==false) return "noCourse";
	   //checkCourse를 통해 사용자가 입력한 수업 ID가 없으면 Client에 "noCourse"를 리턴한다.
	   if(checkpreCourse(studentId,courseId)==false) return "noComplete";
	   //해당 학생이 선 이수과목을 이수하지 않았다면 Client에 "noComplete"를 리턴한다.
	   data.addReservation(reservationInfo);
	   //위의 내용을 통과하면 Data의 addReservation을 통해 수강신청을 이어나간다.
		return reservationInfo+" 입력완료";	    
	}
	private boolean checkStudent(String studentId) throws RemoteException, NullDataException {
		ArrayList<Student> vStudent;
		vStudent=data.getAllStudentData();
		//현재의 모든 학생 정보를 불러온다.
		for (int i = 0; i < vStudent.size(); i++) {
			Student student = (Student) vStudent.get(i);
			if (student.match(studentId)) {
				//사용자로부터 입력받은 값이 있는지 확인한다.
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
		ArrayList<String> mPrecourse = new ArrayList<String>();//수강해야만 하는 강의
		ArrayList<String> completedCourse = new ArrayList<String>();//수강한 과목
		ArrayList<Student> vStudent;
		vStudent=data.getAllStudentData();
		for (int i = 0; i < vStudent.size(); i++) {
			Student student = (Student) vStudent.get(i);
			if (student.match(studentId)) {
				completedCourse = student.getCompletedCourses();
			}//해당 학생을 찾아내어 그 학생이 수강한 과목들을 저장한다.
		}
		ArrayList<Course> vCourse;
		vCourse=data.getAllCourseData();
		for (int i = 0; i < vCourse.size(); i++) {
			Course course = (Course) vCourse.get(i);
			if (course.match(courseId)) {
				mPrecourse=course.getpreCourses();
			}
		}//해당 수업을 찾아내어 그 수업의 선 이수과목을 저장한다.
		if(completedCourse.containsAll(mPrecourse))
			//선 이수과목이 학생이 수강한 과목에 모두 포한되어 있는지 containsAll함수를 통해 알아내었다.
			return true;
		else return false;				
	}





}
