package prioridate;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * A DataLoader for fetching data from JSON
 */
public class DataLoader extends DataConstants {
  /**
   * Fetches all students from the JSON database 
   * and returns them as an ArrayList
   * @return An ArrayList of all students from the database.
   */
  public static ArrayList<Student> getStudents() {
    AssignmentList assignmentList = AssignmentList.getInstance();
    CourseList courseList = CourseList.getInstance();

    // create new ArrayList of Students and populate from JSON
    ArrayList<Student> students = new ArrayList<Student>();
    try {
      FileReader reader = new FileReader(studentsFile);
      JSONParser parser = new JSONParser();
      JSONArray studentsJSON = (JSONArray)parser.parse(reader);

      for(int i=0; i< studentsJSON.size(); i++) {
        JSONObject studentJSON = (JSONObject)studentsJSON.get(i);
        int studentId = Integer.parseInt((String)studentJSON.get("studentId").toString());
        String studentName = (String)studentJSON.get("studentName");
        String username = (String)studentJSON.get("username");
        String password = (String)studentJSON.get("password");
        String type = (String)studentJSON.get("type");

        // Populates the Courses Array for the current student
        JSONArray coursesFromJSON = (JSONArray)studentJSON.get("courses");
        ArrayList<Course> coursesForStudent = new ArrayList<Course>();
        for(int j = 0; j < coursesFromJSON.size();j++) {
          int lookingFor = Integer.parseInt((String)coursesFromJSON.get(j).toString());
          Course found = courseList.getCourse(lookingFor);
          if(found != null) {
            coursesForStudent.add(found);
          }
        }
        // Populates the Assignments Hashmap for the current student
        HashMap<Assignment, Boolean> assignmentsForStudent = new HashMap<Assignment, Boolean>();
        // Fetches due assignments
        JSONArray dueFromJSON = (JSONArray)studentJSON.get("assignmentsDue");
        for(int j = 0; j < dueFromJSON.size();j++) {
          int lookingFor = Integer.parseInt((String)dueFromJSON.get(j).toString());
          Assignment found = assignmentList.getAssignment(lookingFor);
          if(found != null) {
            assignmentsForStudent.put(found, false);
          }
        }
        // Fetches complete assignments
        JSONArray completeFromJSON = (JSONArray)studentJSON.get("assignmentsComplete");
        for(int j = 0; j < completeFromJSON.size();j++) {
          int lookingFor = Integer.parseInt((String)completeFromJSON.get(j).toString());
          Assignment found = assignmentList.getAssignment(lookingFor);
          if(found != null) {
            assignmentsForStudent.put(found, true);
          }
        }
        students.add(new Student(username, password, type, studentId, studentName, assignmentsForStudent, coursesForStudent));
      }
      return students;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * Fetches all teachers from the JSON database 
   * and returns them as an ArrayList
   * @return An ArrayList of all teachers from the database.
   */
  public static ArrayList<Teacher> getTeachers() {
    CourseList courseList = CourseList.getInstance();
    ArrayList<Teacher> teachers = new ArrayList<Teacher>();
    try {
      FileReader reader = new FileReader(teachersFile);
      JSONParser parser = new JSONParser();
      JSONArray teachersJSON = (JSONArray)parser.parse(reader);

      for(int i = 0;i < teachersJSON.size();i++) {
        JSONObject teacherJSON = (JSONObject)teachersJSON.get(i);
        int teacherId = Integer.parseInt((String)teacherJSON.get("teacherId").toString());
        String type = (String)teacherJSON.get("type");
        String teacherName = (String)teacherJSON.get("teacherName");
        String username = (String)teacherJSON.get("username");
        String password = (String)teacherJSON.get("password");

        // Populates courses array for current teacher
        JSONArray coursesFromJSON = (JSONArray)teacherJSON.get("courses");
        ArrayList<Course> coursesForTeacher = new ArrayList<Course>();
        for(int j = 0; j < coursesFromJSON.size();j++) {
          int lookingFor = Integer.parseInt((String)coursesFromJSON.get(j).toString());
          Course found = courseList.getCourse(lookingFor);
          if (found != null) {
              coursesForTeacher.add(found);
          }
        }
        // To-do: Pass to paramaterized constructor
        teachers.add(new Teacher(username, password, type, teacherId, teacherName, coursesForTeacher));
      }
      return teachers;
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null; 
  }

  /**
   * Fetches all courses from the JSON database 
   * and returns them as an ArrayList
   * @return An ArrayList of all courses from the database.
   */
  public static ArrayList<Course> getCourses() {
    AssignmentList assignmentList = AssignmentList.getInstance();
    ArrayList<Course> courses = new ArrayList<Course>();
    try {
      FileReader reader = new FileReader(coursesFile);
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
          Assignment found = assignmentList.getAssignment(lookingFor);
          if(found != null) {
          assignmentsForCourse.add(found);
          }
        }
        courses.add(new Course(courseId, className, assignmentsForCourse));
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
      FileReader reader = new FileReader(assignmentsFile);
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
    int dueYear = Integer.parseInt((String)assignmentJSON.get("dueYear").toString());
    int dueMonth = Integer.parseInt((String)assignmentJSON.get("dueMonth").toString());
    int dueDay = Integer.parseInt((String)assignmentJSON.get("dueDay").toString());
    int dueHour = Integer.parseInt((String)assignmentJSON.get("dueHour").toString());
    int dueMin = Integer.parseInt((String)assignmentJSON.get("dueMin").toString());
    double percentOfGrade = Double.parseDouble((String)assignmentJSON.get("percentOfGrade").toString());
    int numQuestions = Integer.parseInt((String)assignmentJSON.get("numQuestions").toString());
    return new Homework(assignmentId, title, type, dueYear, dueMonth, dueDay, dueHour,
                        dueMin, percentOfGrade, numQuestions);
  }
  
  /**
   * Helper method for getAssignments()
   */
  private static Quiz processQuiz(JSONObject assignmentJSON) {
    int assignmentId = Integer.parseInt((String)assignmentJSON.get("assignmentId").toString());
    String title = (String)assignmentJSON.get("title");
    String type = (String)assignmentJSON.get("type");
    int dueYear = Integer.parseInt((String)assignmentJSON.get("dueYear").toString());
    int dueMonth = Integer.parseInt((String)assignmentJSON.get("dueMonth").toString());
    int dueDay = Integer.parseInt((String)assignmentJSON.get("dueDay").toString());
    int dueHour = Integer.parseInt((String)assignmentJSON.get("dueHour").toString());
    int dueMin = Integer.parseInt((String)assignmentJSON.get("dueMin").toString());
    double percentOfGrade = Double.parseDouble((String)assignmentJSON.get("percentOfGrade").toString());
    double timeLimit = Double.parseDouble((String)assignmentJSON.get("timeLimit").toString());
    int numQuestions = Integer.parseInt((String)assignmentJSON.get("numQuestions").toString());
    return new Quiz(assignmentId, title, type, dueYear, dueMonth, dueDay, dueHour,
                    dueMin, percentOfGrade, timeLimit, numQuestions);
  } 

  /**
   * Helper method for getAssignments()
   */
  private static Exam processExam(JSONObject assignmentJSON) {
    int assignmentId = Integer.parseInt((String)assignmentJSON.get("assignmentId").toString());
    String title = (String)assignmentJSON.get("title");
    String type = (String)assignmentJSON.get("type");
    int dueYear = Integer.parseInt((String)assignmentJSON.get("dueYear").toString());
    int dueMonth = Integer.parseInt((String)assignmentJSON.get("dueMonth").toString());
    int dueDay = Integer.parseInt((String)assignmentJSON.get("dueDay").toString());
    int dueHour = Integer.parseInt((String)assignmentJSON.get("dueHour").toString());
    int dueMin = Integer.parseInt((String)assignmentJSON.get("dueMin").toString());
    double percentOfGrade = Double.parseDouble((String)assignmentJSON.get("percentOfGrade").toString());
    double timeLimit = Double.parseDouble((String)assignmentJSON.get("timeLimit").toString());
    int numQuestions = Integer.parseInt((String)assignmentJSON.get("numQuestions").toString());
    String questionType = (String)assignmentJSON.get("questionType");
    String location = (String)assignmentJSON.get("location");
    return new Exam(assignmentId, title, type, dueYear, dueMonth, dueDay, dueHour,
                    dueMin, percentOfGrade, timeLimit, numQuestions, questionType, location);
  }

  /**
   * Helper method for getAssignments()
   */
  private static Reading processReading(JSONObject assignmentJSON) {
    int assignmentId = Integer.parseInt((String)assignmentJSON.get("assignmentId").toString());
    String title = (String)assignmentJSON.get("title");
    String type = (String)assignmentJSON.get("type");
    int dueYear = Integer.parseInt((String)assignmentJSON.get("dueYear").toString());
    int dueMonth = Integer.parseInt((String)assignmentJSON.get("dueMonth").toString());
    int dueDay = Integer.parseInt((String)assignmentJSON.get("dueDay").toString());
    int dueHour = Integer.parseInt((String)assignmentJSON.get("dueHour").toString());
    int dueMin = Integer.parseInt((String)assignmentJSON.get("dueMin").toString());
    double percentOfGrade = Double.parseDouble((String)assignmentJSON.get("percentOfGrade").toString());
    JSONArray arrayFromJSON = (JSONArray)assignmentJSON.get("chapters");
    String[] chapters = new String[arrayFromJSON.size()];
    for(int i = 0; i < arrayFromJSON.size();i++) {
      chapters[i] = (String)arrayFromJSON.get(i);
    }
    int numPages = Integer.parseInt((String)assignmentJSON.get("numPages").toString());
    return new Reading(assignmentId, title, type, dueYear, dueMonth, dueDay,
                       dueHour, dueMin, percentOfGrade, chapters, numPages);
  }
}

