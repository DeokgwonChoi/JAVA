
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CourseList {
	protected ArrayList<Course> vCourse;

	public CourseList(String sCourseFileName) throws FileNotFoundException, IOException {
		BufferedReader objCourseFile = new BufferedReader(new FileReader(sCourseFileName));
		this.vCourse = new ArrayList<Course>();
		while (objCourseFile.ready()) {
			String stuInfo = objCourseFile.readLine();
			if (!stuInfo.equals("")) {
				this.vCourse.add(new Course(stuInfo));
			}
		}
		objCourseFile.close();
	}

	public ArrayList<Course> getAllCourseRecords() throws NullDataException {
		if(this.vCourse.size()==0) throw new NullDataException("~~~~~~~~~~~~~~Course data is null~~~~~~~~~~~~~~");
		return this.vCourse;
	}
	public boolean addCourseRecords(String courseInfo) {
		if(this.vCourse.add(new Course(courseInfo))) return true;
		else return false;
		
	}
	public boolean deleteCourseRecords(String studentId) {
		for (int i = 0; i < this.vCourse.size(); i++) {
			Course course = (Course) this.vCourse.get(i);
			if (course.match(studentId)) {
				if(this.vCourse.remove(course))return true;
				else return false;
			}
		}
		return false;
	}
	public boolean isRegisteredCourse(String sSID) {
		for (int i = 0; i < this.vCourse.size(); i++) {
			Course objCourse = (Course) this.vCourse.get(i);
			if (objCourse.match(sSID)) {
				return true;
			}
		}
		return false;
	}
	public void saveCourse(String fileAddress) {
		// TODO Auto-generated method stub
		File file = new File(fileAddress);
		file.delete();
		String list="";
		for(int i=0; i<this.vCourse.size(); i++) {
			list += this.vCourse.get(i) + "\n";
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
