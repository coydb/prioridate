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
    getAssignments();
  }

  public static ArrayList<Student> getStudents() {
    // default behavior to allow compilation
    return new ArrayList<Student>();
  }

  public static ArrayList<Teacher> getTeachers() {
    // default behavior to allow compilation
    return new ArrayList<Teacher>();
  }

  /**
   * Loads and returns the list of courses.
   * @return An ArrayList of Courses
   */
  public static ArrayList<Course> getCourses() {
    // default behavior to allow compilation
    return new ArrayList<Course>();
  }
  
  /**
   * Loads and returns the list of assignments.
   * @return An ArrayList of Assignments
   */
  public static ArrayList<Assignment> getAssignments() {
    ArrayList<Assignment> assignments = new ArrayList<Assignment>();
    try {
      FileReader reader = new FileReader("prioridate/assignments.json");
      JSONParser parser = new JSONParser();
      JSONArray coursesJSON = (JSONArray)parser.parse(reader);

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
    return assignments;   
  }
  public static Homework processHomework(JSONObject courseJSON) {
    int assignmentId = Integer.parseInt((String)courseJSON.get("assignmentId").toString());
    //int assignmentId = (int)courseJSON.get("assignmentId");
    String title = (String)courseJSON.get("title");
    String type = (String)courseJSON.get("type");
    int numQuestions = Integer.parseInt((String)courseJSON.get("numQuestions").toString());
    //int numQuestions = (int)courseJSON.get("numQuestions");
    String dueDate = (String)courseJSON.get("dueDate");
    String dueTime = (String)courseJSON.get("dueTime");
    double percentOfGrade = Double.parseDouble((String)courseJSON.get("percentOfGrade").toString());
    return new Homework(assignmentId, title, type, dueDate, dueTime, percentOfGrade, numQuestions);
  }  
  public static Quiz processQuiz(JSONObject courseJSON) {
    int assignmentId = Integer.parseInt((String)courseJSON.get("assignmentId").toString());
    //int assignmentId = courseJSON.get("assignmentId");
    String title = (String)courseJSON.get("title");
    String type = (String)courseJSON.get("type");
    double timeLimit = Double.parseDouble((String)courseJSON.get("timeLimit").toString());
    int numQuestions = Integer.parseInt((String)courseJSON.get("numQuestions").toString());
    //int numQuestions = (int)courseJSON.get("numQuestions");
    String dueDate = (String)courseJSON.get("dueDate");
    String dueTime = (String)courseJSON.get("dueTime");
    double percentOfGrade = Double.parseDouble((String)courseJSON.get("percentOfGrade").toString());
    return new Quiz(assignmentId, title, type, dueDate, dueTime, percentOfGrade, timeLimit, numQuestions);
  } 
}

