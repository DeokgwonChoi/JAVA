
import java.io.Serializable;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Course implements Serializable{

	private static final long serialVersionUID = 1L;
	
	protected String coursetId;
    protected String name;
    protected String department;
    protected ArrayList<String> preCoursesList;
 
    public Course(String inputString) {
        StringTokenizer stringTokenizer = new StringTokenizer(inputString);
    	this.coursetId = stringTokenizer.nextToken();
    	this.name = stringTokenizer.nextToken();
    	//this.name = this.name + " " + stringTokenizer.nextToken();
    	this.department = stringTokenizer.nextToken();
    	this.preCoursesList = new ArrayList<String>();
    	while (stringTokenizer.hasMoreTokens()) {
    		this.preCoursesList.add(stringTokenizer.nextToken());
    	}
    }

    public boolean match(String coursetId) {
        return this.coursetId.equals(coursetId);
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<String> getpreCourses() {
        return this.preCoursesList;
    }

    public String toString() {
        String stringReturn = this.coursetId + " " + this.name + " " + this.department;
        for (int i = 0; i < this.preCoursesList.size(); i++) {
            stringReturn = stringReturn + " " + this.preCoursesList.get(i).toString();
        }
        return stringReturn;
    }

}
