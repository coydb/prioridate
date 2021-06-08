package prioridate;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * A data writer for writing object files to JSON
 */
public class DataWriter extends DataConstants {
  /**
   * For testing purposes only
   * @param args
   */
  public static void main(String[] args) {
    saveAssignments();
    saveCourses();
    saveStudents();
    saveTeachers();
  }

  /**
   * Writes current student list to JSON file
   */
  public static void saveStudents() {
    AccountList accountList = AccountList.getInstance();
    ArrayList<Student> students = accountList.getStudentList();
    JSONArray jsonStudents = new JSONArray();

    for(int i = 0; i < students.size();i++) {
      jsonStudents.add(getJSONStudent(students.get(i)));
    }
    try (FileWriter outFile = new FileWriter(studentsFile)) {
      outFile.write(jsonStudents.toJSONString());
      outFile.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Writes current teacher list to JSON file
   */
  public static void saveTeachers() {
    AccountList accountList = AccountList.getInstance();
    ArrayList<Teacher> teachers = accountList.getTeacherList();
    JSONArray jsonTeachers = new JSONArray();

    for(int i = 0; i < teachers.size();i++) {
      jsonTeachers.add(getJSONTeacher(teachers.get(i)));
    }
    try (FileWriter outFile = new FileWriter(teachersFile)) {
      outFile.write(jsonTeachers.toJSONString());
      outFile.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Writes current course list to JSON file
   */
  public static void saveCourses() {
    CourseList courseList = CourseList.getInstance();
    ArrayList<Course> courses = courseList.getCourses();
    JSONArray jsonCourses = new JSONArray();

    for(int i = 0;i < courses.size();i++) {
      jsonCourses.add(getJSONCourse(courses.get(i)));
    }
    try (FileWriter outFile = new FileWriter(coursesFile)) {
      outFile.write(jsonCourses.toJSONString());
      outFile.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  /**
   * Writes current assignments to JSON file
   */
  public static void saveAssignments() {
    AssignmentList assignmentList = AssignmentList.getInstance();
    ArrayList<Assignment> assignments = assignmentList.getAssignments();
    JSONArray jsonAssignments = new JSONArray();

    for(int i = 0;i < assignments.size();i++) {
      jsonAssignments.add(getJSONAssignment(assignments.get(i)));
    }

    try (FileWriter outFile = new FileWriter(assignmentsFile)) {
      outFile.write(jsonAssignments.toJSONString());
      outFile.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Helper method for turning an Assignment object into
   * a JSONObject
   * @param assignment Assignment Object
   * @return JSONObject
   */
  private static JSONObject getJSONAssignment(Assignment assignment) {
    JSONObject assignmentJSONObject = new JSONObject();
    assignmentJSONObject.put("assignmentId",assignment.getAssignmentId());
    assignmentJSONObject.put("title", assignment.getTitle());
    assignmentJSONObject.put("type", assignment.getType());
    assignmentJSONObject.put("dueYear", assignment.getDueDate().get(Calendar.YEAR));
    assignmentJSONObject.put("dueMonth", assignment.getDueDate().get(Calendar.MONTH));
    assignmentJSONObject.put("dueDay", assignment.getDueDate().get(Calendar.DATE));
    assignmentJSONObject.put("dueHour", assignment.getDueDate().get(Calendar.HOUR));
    assignmentJSONObject.put("dueMin", assignment.getDueDate().get(Calendar.MINUTE));
    assignmentJSONObject.put("percentOfGrade", assignment.getPercentOfGrade());
    String assignmentType = assignment.getType();
    switch (assignmentType.toLowerCase()) {
      case "homework":
        Homework homeworkObject = (Homework)assignment;
        assignmentJSONObject.put("numQuestions", homeworkObject.getNumQuestions());
        break;
      case "quiz":
        Quiz quizObject = (Quiz)assignment;
        assignmentJSONObject.put("timeLimit", quizObject.getTimeLimit());
        assignmentJSONObject.put("numQuestions", quizObject.getNumQuestions());
        break;
      case "exam":
        Exam examObject = (Exam)assignment;
        assignmentJSONObject.put("timeLimit", examObject.getTimeLimit());
        assignmentJSONObject.put("numQuestions", examObject.getNumQuestions());
        assignmentJSONObject.put("questionType", examObject.getQuestionType());
        assignmentJSONObject.put("location", examObject.getLocation());
        break;
      case "reading":
        Reading readingObject = (Reading)assignment;
        JSONArray chaptersJSONArray = new JSONArray();
        for(int i = 0; i < readingObject.getChapters().length;i++) {
          chaptersJSONArray.add(readingObject.getChapters()[i]);
        }
        assignmentJSONObject.put("chapters", chaptersJSONArray);
        assignmentJSONObject.put("numPages", readingObject.getNumPages());
        break;
      default:
        break;
    }
    return assignmentJSONObject;
  }

  /**
   * Helper method for turning a Course object into
   * a JSONObject
   * @param course Course object
   * @return JSONObject
   */
  private static JSONObject getJSONCourse(Course course) {
    JSONObject courseJSONObject = new JSONObject();
    courseJSONObject.put("courseId", course.getCourseId());
    courseJSONObject.put("className", course.getClassName());
    ArrayList<Assignment> assignmentsFromObject = course.getAssignments();
    JSONArray assignmentsArray = new JSONArray();
    for(int i = 0;i < course.getAssignments().size();i++) {
      int assignmentId = assignmentsFromObject.get(i).getAssignmentId();
      assignmentsArray.add(assignmentId);
    }
    courseJSONObject.put("assignments", assignmentsArray);
    return courseJSONObject;
  }


  /**
   * Helper method for turning a Student object into
   * a JSONObject
   * @param account Student object
   * @return JSONObject
   */
  private static JSONObject getJSONStudent(Student account) {
    JSONObject studentJSONObject = new JSONObject();
    studentJSONObject.put("studentId", account.getStudentId());
    studentJSONObject.put("studentName", account.getStudentName());
    studentJSONObject.put("username", account.getUsername());
    studentJSONObject.put("password", account.getPassword());
    JSONArray coursesJSONArray = new JSONArray();
    ArrayList<Course> courses = account.getCourses();
    for (int i = 0; i < courses.size();i++) {
      coursesJSONArray.add(courses.get(i).getCourseId());
    }
    studentJSONObject.put("courses", coursesJSONArray);
    HashMap<Assignment, Boolean> hashmapFromAccount = account.getAssignments();
    JSONArray dueJsonArray = new JSONArray();
    JSONArray completeJSONArray = new JSONArray();
    for (Assignment assignment : hashmapFromAccount.keySet()) {
      if (hashmapFromAccount.get(assignment) == true) {
        completeJSONArray.add(assignment.getAssignmentId());
      }
      if (hashmapFromAccount.get(assignment) == false) {
        dueJsonArray.add(assignment.getAssignmentId());
      }
    }
    studentJSONObject.put("assignmentsDue", dueJsonArray);
    studentJSONObject.put("assignmentsComplete", completeJSONArray);
    return studentJSONObject;
  }

  /**
   * Helper method for turning a Teacher object into
   * a JSONObject
   * @param account Teacher object
   * @return JSONObject
   */
  private static JSONObject getJSONTeacher(Teacher account) {
    JSONObject teacherJSONObject = new JSONObject();
    teacherJSONObject.put("teacherId", account.getTeacherId());
    teacherJSONObject.put("teacherName", account.getTeacherName());
    teacherJSONObject.put("username", account.getUsername());
    teacherJSONObject.put("password", account.getPassword());
    JSONArray coursesJSONArray = new JSONArray();
    ArrayList<Course> courses = account.getCourses();
    for (int i = 0; i < courses.size();i++) {
      coursesJSONArray.add(courses.get(i).getCourseId());
    }
    teacherJSONObject.put("courses", coursesJSONArray);
    return teacherJSONObject;
  }
}
