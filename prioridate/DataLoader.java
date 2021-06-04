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
    ArrayList<Assignment> existingAssignments = getAssignments();
    ArrayList<Course> courses = new ArrayList<Course>();
    try {
      FileReader reader = new FileReader("prioridate/json/courses.json");
      JSONParser parser = new JSONParser();
      JSONArray coursesJSON = (JSONArray)parser.parse(reader);

      for(int i=0; i< coursesJSON.size(); i++) {
        JSONObject courseJSON = (JSONObject)coursesJSON.get(i);
        int courseId = Integer.parseInt((String)courseJSON.get("courseId").toString());
        String className = (String)courseJSON.get("className");
        JSONArray arrayFromJSON = (JSONArray)courseJSON.get("assignments");
        ArrayList<Assignment> assignmentsForCourse = new ArrayList<Assignment>();
        for(int j = 0; j < arrayFromJSON.size();j++) {
          int lookingFor = Integer.parseInt((String)arrayFromJSON.get(j).toString());
          for (int k = 0; k < existingAssignments.size();k++) {
            int currentAssignmentId = existingAssignments.get(k).getAssignmentId();
            boolean belongsToCourse = currentAssignmentId == lookingFor; 
            if(belongsToCourse) {
              assignmentsForCourse.add(existingAssignments.get(k));
            }
          }
          courses.add(new Course(courseId, className, assignmentsForCourse));
        }
      }
      return courses;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;   
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
      JSONArray assignmentsJSON = (JSONArray)parser.parse(reader);

      for(int i=0; i< assignmentsJSON.size(); i++) {
        JSONObject assignmentJSON = (JSONObject)assignmentsJSON.get(i);
        String type = (String)assignmentJSON.get("type");
        switch(type.toLowerCase()) {
          case "homework":
            assignments.add(processHomework(assignmentJSON));
            break;
          case "quiz":
            assignments.add(processQuiz(assignmentJSON));
            break;
          case "exam":
            assignments.add(processExam(assignmentJSON));
            break;
          case "reading":
            assignments.add(processReading(assignmentJSON));
            break;
          default:
            // malformed JSON entries are caught by the default case and skipped
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
  private static Homework processHomework(JSONObject assignmentJSON) {
    int assignmentId = Integer.parseInt((String)assignmentJSON.get("assignmentId").toString());
    String title = (String)assignmentJSON.get("title");
    String type = (String)assignmentJSON.get("type");
    String dueDate = (String)assignmentJSON.get("dueDate");
    String dueTime = (String)assignmentJSON.get("dueTime");
    double percentOfGrade = Double.parseDouble((String)assignmentJSON.get("percentOfGrade").toString());
    int numQuestions = Integer.parseInt((String)assignmentJSON.get("numQuestions").toString());
    return new Homework(assignmentId, title, type, dueDate, dueTime, percentOfGrade, numQuestions);
  }
  
  /**
   * Helper method for getAssignments()
   */
  private static Quiz processQuiz(JSONObject assignmentJSON) {
    int assignmentId = Integer.parseInt((String)assignmentJSON.get("assignmentId").toString());
    String title = (String)assignmentJSON.get("title");
    String type = (String)assignmentJSON.get("type");
    String dueDate = (String)assignmentJSON.get("dueDate");
    String dueTime = (String)assignmentJSON.get("dueTime");
    double percentOfGrade = Double.parseDouble((String)assignmentJSON.get("percentOfGrade").toString());
    double timeLimit = Double.parseDouble((String)assignmentJSON.get("timeLimit").toString());
    int numQuestions = Integer.parseInt((String)assignmentJSON.get("numQuestions").toString());
    return new Quiz(assignmentId, title, type, dueDate, dueTime, percentOfGrade, timeLimit, numQuestions);
  } 

  /**
   * Helper method for getAssignments()
   */
  private static Exam processExam(JSONObject assignmentJSON) {
    int assignmentId = Integer.parseInt((String)assignmentJSON.get("assignmentId").toString());
    String title = (String)assignmentJSON.get("title");
    String type = (String)assignmentJSON.get("type");
    String dueDate = (String)assignmentJSON.get("dueDate");
    String dueTime = (String)assignmentJSON.get("dueTime");
    double percentOfGrade = Double.parseDouble((String)assignmentJSON.get("percentOfGrade").toString());
    double timeLimit = Double.parseDouble((String)assignmentJSON.get("timeLimit").toString());
    int numQuestions = Integer.parseInt((String)assignmentJSON.get("numQuestions").toString());
    String questionType = (String)assignmentJSON.get("questionType");
    String location = (String)assignmentJSON.get("location");
    String dateAndTime = (String)assignmentJSON.get("dateAndTime");
    return new Exam(assignmentId, title, type, dueDate, dueTime, percentOfGrade,
                   timeLimit, numQuestions, questionType, location, dateAndTime);

  }

  /**
   * Helper method for getAssignments()
   */
  private static Reading processReading(JSONObject assignmentJSON) {
    int assignmentId = Integer.parseInt((String)assignmentJSON.get("assignmentId").toString());
    String title = (String)assignmentJSON.get("title");
    String type = (String)assignmentJSON.get("type");
    String dueDate = (String)assignmentJSON.get("dueDate");
    String dueTime = (String)assignmentJSON.get("dueTime");
    double percentOfGrade = Double.parseDouble((String)assignmentJSON.get("percentOfGrade").toString());
    JSONArray arrayFromJSON = (JSONArray)assignmentJSON.get("chapters");
    String[] chapters = new String[arrayFromJSON.size()];
    for(int i = 0; i < arrayFromJSON.size();i++) {
      chapters[i] = (String)arrayFromJSON.get(i);
    }
    int numPages = Integer.parseInt((String)assignmentJSON.get("numPages").toString());
    return new Reading(assignmentId, title, type, dueDate, dueTime, percentOfGrade, chapters, numPages);
  }
}

