package prioridate;

import java.io.FileReader;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DataLoader {
  /**
   * TESTING PURPOSES ONLY
   */
  public static void main(String[] args) {
    getCourses();
  }

  public static ArrayList<Student> getStudents() {
    ArrayList<Student> students = new ArrayList<Student>();
    // default behavior to allow compilation
    return new ArrayList<Student>();
  }

  public static ArrayList<Teacher> getTeachers() {
    // default behavior to allow compilation
    return new ArrayList<Teacher>();
  }

  /**
   * ***IN-PROGRESS*** RETURN IS NON-FUNCTIONAL
   * UNTIL CONSTRUCTORS ARE IMPLEMENTED
   * @return NON-FUNCTIONAL***
   */
  public static ArrayList<Course> getCourses() {
    ArrayList<String> courses = new ArrayList<String>();
    try {
      FileReader reader = new FileReader("prioridate/courses.json");
      JSONParser parser = new JSONParser();
      JSONArray coursesJSON = (JSONArray)new JSONParser().parse(reader);

      for(int i=0; i< coursesJSON.size(); i++) {
        JSONObject courseJSON = (JSONObject)coursesJSON.get(i);
        long classId = (long)courseJSON.get("classId");
        String className = (String)courseJSON.get("className");
        long professorId = (long)courseJSON.get("professorId");
        System.out.println("Class Id: "+classId);
        System.out.println("Class Name: "+className);
        System.out.println("Professor Id: "+professorId);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    // default behavior to allow compilation
    return new ArrayList<Course>();
  }
  
  public ArrayList<Assignment> getAssignments() {
    // default behavior to allow compilation
    return new ArrayList<Assignment>();   
  }   
}
