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
    // default behavior to allow compilation
    return new ArrayList<Course>();
  }
  
  public static ArrayList<Assignment> getAssignments() {
    ArrayList<Assignment> assignments = new ArrayList<Assignment>();
    try {
      FileReader reader = new FileReader("prioridate/assignments.json");
      JSONParser parser = new JSONParser();
      JSONArray coursesJSON = (JSONArray)new JSONParser().parse(reader);

      for(int i=0; i< coursesJSON.size(); i++) {
        JSONObject courseJSON = (JSONObject)coursesJSON.get(i);
        String type = (String)courseJSON.get("type");
        switch(type.toLowerCase()) {
          case "homework":
            assignments.add(processHomework(courseJSON));
            break;
          case "quiz":
            assignments.add(processQuiz(courseJSON));
            break;
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return new ArrayList<Assignment>();   
  }
  public static Homework processHomework(JSONObject courseJSON) {
    int assignmentId = (int)courseJSON.get("assignmentId");
    String title = (String)courseJSON.get("title");
    String type = (String)courseJSON.get("type");
    int numQuestions = (int)courseJSON.get("numQuestions");
    String dueDate = (String)courseJSON.get("dueDate");
    String dueTime = (String)courseJSON.get("dueTime");
    double percentOfGrade = (double)courseJSON.get("percentOfGrade");
    return new Homework();
  }  
  public static Quiz processQuiz(JSONObject courseJSON) {
    int assignmentId = (int)courseJSON.get("assignmentId");
    String title = (String)courseJSON.get("title");
    String type = (String)courseJSON.get("type");
    String timeLimit = (String)courseJSON.get("timeLimit");
    int numQuestions = (int)courseJSON.get("numQuestions");
    String dueDate = (String)courseJSON.get("dueDate");
    String dueTime = (String)courseJSON.get("dueTime");
    double percentOfGrade = (double)courseJSON.get("percentOfGrade");
    return new Quiz();
  } 
}

