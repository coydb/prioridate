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
public class DataLoader {
  /**
   * Fetches all students from the JSON database 
   * and returns them as an ArrayList
   * @return An ArrayList of all students from the database.
   */
  public static ArrayList<Student> getStudents() {
    AssignmentList assignmentList = AssignmentList.getInstance();
    ArrayList<Assignment> existingAssignments = assignmentList.getAssignments();
    CourseList courseList = CourseList.getInstance();
    ArrayList<Course> existingCourses = courseList.getCourses();

    // create new ArrayList of Students and populate from JSON
    ArrayList<Student> students = new ArrayList<Student>();
    try {
      FileReader reader = new FileReader("prioridate/json/students.json");
      JSONParser parser = new JSONParser();
      JSONArray studentsJSON = (JSONArray)parser.parse(reader);

      for(int i=0; i< studentsJSON.size(); i++) {
        JSONObject studentJSON = (JSONObject)studentsJSON.get(i);
        int studentId = Integer.parseInt((String)studentJSON.get("studentId").toString());
        String studentName = (String)studentJSON.get("studentName");
        String username = (String)studentJSON.get("username");
        String password = (String)studentJSON.get("password");

        // Populates the Courses Array for the current student
        JSONArray coursesFromJSON = (JSONArray)studentJSON.get("courses");
        ArrayList<Course> coursesForStudent = new ArrayList<Course>();
        for(int j = 0; j < coursesFromJSON.size();j++) {
          int lookingFor = Integer.parseInt((String)coursesFromJSON.get(j).toString());
          for (int k = 0; k < existingCourses.size();k++) {
            int currentCourseId = existingCourses.get(k).getCourseId();
            boolean belongsToStudent = currentCourseId == lookingFor; 
            if(belongsToStudent) {
              coursesForStudent.add(existingCourses.get(k));
            }
          }
        }
        // Populates the Assignments Hashmap for the current student
        HashMap<Course, Boolean> assignmentsForStudent = new HashMap<Course, Boolean>();
        // Fetches due assignments
        JSONArray dueFromJSON = (JSONArray)studentJSON.get("assignmentsDue");
        for(int j = 0; j < dueFromJSON.size();j++) {
          int lookingFor = Integer.parseInt((String)dueFromJSON.get(j).toString());
          for (int k = 0; k < existingAssignments.size();k++) {
            int currentAssignmentId = existingAssignments.get(k).getAssignmentId();
            boolean belongsToStudent = currentAssignmentId == lookingFor; 
            if(belongsToStudent) {
              assignmentsForStudent.put(existingCourses.get(k), false);
            }
          }
        }
        // Fetches complete assignments
        JSONArray completeFromJSON = (JSONArray)studentJSON.get("assignmentsComplete");
        for(int j = 0; j < completeFromJSON.size();j++) {
          int lookingFor = Integer.parseInt((String)completeFromJSON.get(j).toString());
          for (int k = 0; k < existingAssignments.size();k++) {
            int currentAssignmentId = existingAssignments.get(k).getAssignmentId();
            boolean belongsToStudent = currentAssignmentId == lookingFor; 
            if(belongsToStudent) {
              assignmentsForStudent.put(existingCourses.get(k), true);
            }
          }
        }
        // To-do: Pass to Paramaterized Constructor
        students.add(new Student());
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
    ArrayList<Course> existingCourses = courseList.getCourses();
    ArrayList<Teacher> teachers = new ArrayList<Teacher>();
    try {
      FileReader reader = new FileReader("prioridate/json/teachers.json");
      JSONParser parser = new JSONParser();
      JSONArray teachersJSON = (JSONArray)parser.parse(reader);

      for(int i = 0;i < teachersJSON.size();i++) {
        JSONObject teacherJSON = (JSONObject)teachersJSON.get(i);
        int teacherId = Integer.parseInt((String)teacherJSON.get("teacherId").toString());
        String teacherName = (String)teacherJSON.get("teacherName");
        String username = (String)teacherJSON.get("username");
        String password = (String)teacherJSON.get("password");

        // Populates courses array for current teacher
        JSONArray coursesFromJSON = (JSONArray)teacherJSON.get("courses");
        ArrayList<Course> coursesForTeacher = new ArrayList<Course>();
        for(int j = 0; j < coursesFromJSON.size();j++) {
          int lookingFor = Integer.parseInt((String)coursesFromJSON.get(j).toString());
          for (int k = 0; k < existingCourses.size();k++) {
            int currentCourseId = existingCourses.get(k).getCourseId();
            boolean belongsToTeacher = currentCourseId == lookingFor; 
            if(belongsToTeacher) {
              coursesForTeacher.add(existingCourses.get(k));
            }
          }
        }
        // To-do: Pass to paramaterized constructor
        teachers.add(new Teacher());
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
    ArrayList<Assignment> existingAssignments = assignmentList.getAssignments();
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
      FileReader reader = new FileReader("prioridate/json/test.json");
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

