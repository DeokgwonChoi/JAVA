import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class Client {

	public static void main(String[] args) throws NotBoundException, IOException{
		ServerIF server;
		BufferedReader objReader = new BufferedReader(new InputStreamReader(System.in));
		try {			
			server = (ServerIF)Naming.lookup("Server");
			while(true){
				pringMenu();	
				String sChoice= objReader.readLine().trim();
				switch(sChoice) {
				case "1":
					showList(server.getAllStudentData());
					//Server�� getAllStudentData()������ ArrayList<Student>�� �����ؿ´�.
					break;
				case "2":
					showList(server.getAllCourseData());
					break;	
				case "3":
					addStudent(server, objReader);		
					break;
				case "4":
					deleteStudent(server, objReader);
					break;
				case "5":
					addCourse(server, objReader);
					break;
				case "6":
					deleteCourse(server, objReader);
					break;
				case "7":
					reservation(server, objReader);				
					break;
				case "8":
					showList(server.getAllReservationData());
					break;
				case "x":
					
					return;
				default:
					System.out.println("Invalid Choice !!!");
				}
			}								
		} catch (RemoteException e) {
			e.printStackTrace();	
		} catch (NullDataException e) {
				e.printStackTrace();	
		}
	}
 

	private static void reservation(ServerIF server, BufferedReader objReader) throws IOException, RemoteException {
		System.out.println("Student ID:"); String studentId=objReader.readLine().trim();
		System.out.println("Course ID:"); String courseId=objReader.readLine().trim();	
		//�л� ID�� ���� ID�� �޴´�.
		System.out.println(server.reservation(studentId+" "+courseId));
		//���� �����͸� Server�� reservation�� �����ָ� ���� ��� ���� ���Ϲ޾� ����Ѵ�.
	}


	private static void deleteCourse(ServerIF server, BufferedReader objReader) throws RemoteException, IOException {
		System.out.print("Coruse ID: ");
		if(server.deleteCourse( objReader.readLine().trim())) System.out.println("SUCCESS");
		else System.out.println("FAIL");
	}


	private static void addCourse(ServerIF server, BufferedReader objReader) throws IOException, RemoteException {
		System.out.println("=============Course Information");
		System.out.println("Course ID:"); String courseId=objReader.readLine().trim();
		System.out.println("Professor Name:"); String professorName=objReader.readLine().trim();
		System.out.println("Course Department:"); String courseDept=objReader.readLine().trim();
		System.out.println("Course Completed Course:"); String preCourse=objReader.readLine().trim();
		
		if(server.addCourse(courseId+" "+professorName+" "+courseDept+" "+preCourse))System.out.println("SUCCESS");
		else System.out.println("FAIL");
	}


	private static void addStudent(ServerIF server, BufferedReader objReader) throws IOException, RemoteException {
		System.out.println("=============Student Information");
		System.out.println("Student ID:"); String studentId=objReader.readLine().trim();
		System.out.println("Student Name:"); String studentName=objReader.readLine().trim();
		System.out.println("Student Department:"); String studentDept=objReader.readLine().trim();
		System.out.println("Student Completed Course List:"); String completedCourse=objReader.readLine().trim();
		//����ڷκ��� �Է¹��� ������ ���������� �����Ѵ�.
		if(server.addStudent(studentId+" "+studentName+" "+studentDept+" "+completedCourse))System.out.println("SUCCESS");
		//����� ������ �ϳ��� ���ڿ��� �ٲپ� �ش� ���ڿ��� �־� Server�� addStudent�� �����Ѵ�.
		//�������� ���� ���������� �߰��Ǹ� "SUCCESS"�� ��µȴ�.
		else System.out.println("FAIL");
	}


	private static void deleteStudent(ServerIF server, BufferedReader objReader) throws RemoteException, IOException {
		System.out.print("Student ID: ");
		if(server.deleteStudent( objReader.readLine().trim())) System.out.println("SUCCESS");
		//�ش� �������� ���� ���������� �����Ǹ� "SUCCESS"�� ��µȴ�.
		else System.out.println("FAIL");
	}

	private static void showList(ArrayList<?> dataList){
		String list="";
		for(int i=0; i<dataList.size(); i++) {
			list += dataList.get(i) + "\n";
		}
		//�Է¹��� ArrayList�� ���ڿ��� �ٲپ� ����Ѵ�.
		System.out.println(list);
	}
	private static void pringMenu() {
		System.out.println("-------------MENU----------------");
		System.out.println("1.List Students");
		System.out.println("2.List Courses");
		System.out.println("3.Add Student");
		System.out.println("4.Delete Student");
		System.out.println("5.Add Courses");
		System.out.println("6.Delete Courses");
		System.out.println("7.Make Reservation");
		System.out.println("8.List Reservation");
		System.out.println("X.Exit");
	}

}
