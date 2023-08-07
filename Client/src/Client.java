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
					//Server의 getAllStudentData()실행후 ArrayList<Student>를 리턴해온다.
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
		//학생 ID와 수업 ID를 받는다.
		System.out.println(server.reservation(studentId+" "+courseId));
		//받은 데이터를 Server의 reservation에 보내주며 실행 결과 값을 리턴받아 출력한다.
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
		//사용자로부터 입력받은 내용을 순차적으로 저장한다.
		if(server.addStudent(studentId+" "+studentName+" "+studentDept+" "+completedCourse))System.out.println("SUCCESS");
		//저장된 내용을 하나의 문자열로 바꾸어 해당 문자열을 넣어 Server의 addStudent를 실행한다.
		//데이터의 값이 성공적으로 추가되면 "SUCCESS"가 출력된다.
		else System.out.println("FAIL");
	}


	private static void deleteStudent(ServerIF server, BufferedReader objReader) throws RemoteException, IOException {
		System.out.print("Student ID: ");
		if(server.deleteStudent( objReader.readLine().trim())) System.out.println("SUCCESS");
		//해당 데이터의 값이 성공적으로 삭제되면 "SUCCESS"가 출력된다.
		else System.out.println("FAIL");
	}

	private static void showList(ArrayList<?> dataList){
		String list="";
		for(int i=0; i<dataList.size(); i++) {
			list += dataList.get(i) + "\n";
		}
		//입력받은 ArrayList를 문자열로 바꾸어 출력한다.
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
