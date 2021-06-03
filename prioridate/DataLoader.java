package prioridate;

import java.io.FileReader;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * A DataLoader for fetching data from JSON
 */
public class DataLoader {
  /**
   * Fetches all students from the JSON database 
   * and returns them as an ArrayList
   * @return An ArrayList of all students from the database.
   */
  public static ArrayList<Student> getStudents() {
    // default behavior to allow compilation
    return new ArrayList<Student>();
  }

  /**
   * Fetches all teachers from the JSON database 
   * and returns them as an ArrayList
   * @return An ArrayList of all teachers from the database.
   */
  public static ArrayList<Teacher> getTeachers() {
    // default behavior to allow compilation
    return new ArrayList<Teacher>();
  }

  /**
   * Fetches all courses from the JSON database 
   * and returns them as an ArrayList
   * @return An ArrayList of all courses from the database.
   */
  public static ArrayList<Course> getCourses() {
    // default behavior to allow compilation
    return new ArrayList<Course>();
  }
  
  /**
   * Fetches all assignments from the JSON database 
   * and returns them as an ArrayList
   * @return An ArrayList of all assignments from the database.
   */
  public static ArrayList<Assignment> getAssignments() {
    ArrayList<Assignment> assignments = new ArrayList<Assignment>();
    try {
      FileReader reader = new FileReader("prioridate/json/assignments.json");
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
          case "exam":
            assignments.add(processExam(courseJSON));
            break;
          case "reading":
            assignments.add(processReading(courseJSON));
            break;
          default:
            // prevents malformed JSON entries from being added to list
            break;
        }
      }
      return assignments;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;   
  }

  /**
   * Helper method for getAssignments()
   */
  private static Homework processHomework(JSONObject courseJSON) {
    int assignmentId = Integer.parseInt((String)courseJSON.get("assignmentId").toString());
    String title = (String)courseJSON.get("title");
    String type = (String)courseJSON.get("type");
    String dueDate = (String)courseJSON.get("dueDate");
    String dueTime = (String)courseJSON.get("dueTime");
    double percentOfGrade = Double.parseDouble((String)courseJSON.get("percentOfGrade").toString());
    int numQuestions = Integer.parseInt((String)courseJSON.get("numQuestions").toString());
    return new Homework(assignmentId, title, type, dueDate, dueTime, percentOfGrade, numQuestions);
  }
  
  /**
   * Helper method for getAssignments()
   */
  private static Quiz processQuiz(JSONObject courseJSON) {
    int assignmentId = Integer.parseInt((String)courseJSON.get("assignmentId").toString());
    String title = (String)courseJSON.get("title");
    String type = (String)courseJSON.get("type");
    String dueDate = (String)courseJSON.get("dueDate");
    String dueTime = (String)courseJSON.get("dueTime");
    double percentOfGrade = Double.parseDouble((String)courseJSON.get("percentOfGrade").toString());
    double timeLimit = Double.parseDouble((String)courseJSON.get("timeLimit").toString());
    int numQuestions = Integer.parseInt((String)courseJSON.get("numQuestions").toString());
    return new Quiz(assignmentId, title, type, dueDate, dueTime, percentOfGrade, timeLimit, numQuestions);
  } 

  /**
   * Helper method for getAssignments()
   */
  private static Exam processExam(JSONObject courseJSON) {
    int assignmentId = Integer.parseInt((String)courseJSON.get("assignmentId").toString());
    String title = (String)courseJSON.get("title");
    String type = (String)courseJSON.get("type");
    String dueDate = (String)courseJSON.get("dueDate");
    String dueTime = (String)courseJSON.get("dueTime");
    double percentOfGrade = Double.parseDouble((String)courseJSON.get("percentOfGrade").toString());
    double timeLimit = Double.parseDouble((String)courseJSON.get("timeLimit").toString());
    int numQuestions = Integer.parseInt((String)courseJSON.get("numQuestions").toString());
    String questionType = (String)courseJSON.get("questionType");
    String location = (String)courseJSON.get("location");
    String dateAndTime = (String)courseJSON.get("dateAndTime");
    return new Exam(assignmentId, title, type, dueDate, dueTime, percentOfGrade,
                   timeLimit, numQuestions, questionType, location, dateAndTime);

  }

  /**
   * Helper method for getAssignments()
   */
  private static Reading processReading(JSONObject courseJSON) {
    int assignmentId = Integer.parseInt((String)courseJSON.get("assignmentId").toString());
    String title = (String)courseJSON.get("title");
    String type = (String)courseJSON.get("type");
    String dueDate = (String)courseJSON.get("dueDate");
    String dueTime = (String)courseJSON.get("dueTime");
    double percentOfGrade = Double.parseDouble((String)courseJSON.get("percentOfGrade").toString());
    JSONArray arrayFromJSON = (JSONArray)courseJSON.get("chapters");
    String[] chapters = new String[arrayFromJSON.size()];
    for(int i = 0; i < arrayFromJSON.size();i++) {
      chapters[i] = (String)arrayFromJSON.get(i);
    }
    int numPages = Integer.parseInt((String)courseJSON.get("numPages").toString());
    return new Reading(assignmentId, title, type, dueDate, dueTime, percentOfGrade, chapters, numPages);
  }
}

