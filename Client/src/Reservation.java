import java.io.Serializable;
import java.util.StringTokenizer;

public class Reservation implements Serializable{

	private static final long serialVersionUID = 1L;
	
	protected String studentId;
    protected String courseId;
 
    public Reservation(String inputString) {
        StringTokenizer stringTokenizer = new StringTokenizer(inputString);
    	this.studentId = stringTokenizer.nextToken();
    	this.courseId = stringTokenizer.nextToken();
    }

    public boolean match(String studentId) {
        return this.studentId.equals(studentId);
    }


    public String getStudentId() {
        return this.studentId;
    }


    public String toString() {
        String stringReturn = this.studentId + " " + this.courseId; 
        return stringReturn;
    }
}
