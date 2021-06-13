package index255;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
public class DataTest {
  AssignmentList assignmentList = AssignmentList.getInstance();
  CourseList courseList = CourseList.getInstance();
  AccountList accountList = AccountList.getInstance();
  ArrayList<Assignment> assignments = assignmentList.getAssignments();
  ArrayList<Course> courses = courseList.getCourses();
  ArrayList<Student> students = accountList.getStudentList();
  ArrayList<Teacher> teachers = accountList.getTeacherList();
  
  @BeforeEach
  void setup() {
    assignments.clear();
    courses.clear();
    students.clear();
    teachers.clear();
    DataWriter.saveAssignments();
    DataWriter.saveCourses();
    DataWriter.saveStudents();
    DataWriter.saveTeachers();
  }
  
  @AfterEach
  void teardown() {
    assignments.clear();
    courses.clear();
    students.clear();
    teachers.clear();
    DataWriter.saveAssignments();
    DataWriter.saveCourses();
    DataWriter.saveStudents();
    DataWriter.saveTeachers();
  }

  @Test
  void testLoadEmptyAssignmentFile() {
    assignments = DataLoader.getAssignments();
    assertEquals(0, assignments.size());
  }

  @Test
  void testLoadEmptyCoursesFile() {
    courses = DataLoader.getCourses();
    assertEquals(0, courses.size());
  }

  @Test
  void testLoadEmptyStudentsFile() {
    students = DataLoader.getStudents();
    assertEquals(0, students.size());
  }

  @Test
  void testLoadEmptyTeachersFile() {
    teachers = DataLoader.getTeachers();
    assertEquals(0, teachers.size());
  }

  @Test
  void testWriteEmptyAssignmentList() {
    DataWriter.saveAssignments();
    assignments = DataLoader.getAssignments();
    assertEquals(0, assignments.size());
  }

  @Test
  void testWriteEmptyCourseList() {
    DataWriter.saveCourses();
    courses = DataLoader.getCourses();
    assertEquals(0, courses.size());
  }

  @Test
  void testWriteEmptyStudentsList() {
    DataWriter.saveStudents();
    students = DataLoader.getStudents();
    assertEquals(0, students.size());
  }

  @Test
  void testWriteEmptyTeachersList() {
    DataWriter.saveTeachers();
    teachers = DataLoader.getTeachers();
    assertEquals(0, teachers.size());
  }

  @Test
  void testWriteAssignmentEmptyTitle() {
    Assignment newAssignment = new Homework(1, "", "Homework",
                                            2021, 06, 25, 23, 59, 5.5, 15);
    assignmentList.addAssignment(newAssignment);
    assertNotEquals("", assignmentList.getAssignment(1).getTitle());
  }

  @Test
  void testWriteAssignmentEmptyType() {
    Assignment newAssignment = new Homework(1, "New Assignment 1", "",
                                            2021, 06, 25, 23, 59, 5.5, 15);
    assignmentList.addAssignment(newAssignment);
    assertNotEquals("", assignmentList.getAssignment(1).getType());
  }

  @Test
  void testWriteCourseEmptyCourseName() {
    ArrayList<Assignment> assignments = new ArrayList<Assignment>();
    Course course = new Course(1, "", assignments);
    courseList.addCourse(course);
    assertNotEquals("", courseList.getCourse(1).getClassName());
  }

  @Test
  void testWriteAccountEmptyUsername() {
    ArrayList<Course> courses = new ArrayList<Course>();
    HashMap<Assignment, Boolean> assignments = new HashMap<Assignment, Boolean>();
    Student student = new Student("", "password", "Student", 1,
                                  "New User", assignments, courses);
    accountList.addStudent(student);
    assertNotEquals("", accountList.getAccount(1).getUsername());
  }

  @Test
  void testWriteAccountEmptyPassword() {
    ArrayList<Course> courses = new ArrayList<Course>();
    HashMap<Assignment, Boolean> assignments = new HashMap<Assignment, Boolean>();
    Student student = new Student("username1", "", "Student", 1,
                                  "New User", assignments, courses);
    accountList.addStudent(student);
    assertNotEquals("", accountList.getAccount(1).getPassword());
  }

  @Test
  void testWriteAccountEmptyName() {
    ArrayList<Course> courses = new ArrayList<Course>();
    HashMap<Assignment, Boolean> assignments = new HashMap<Assignment, Boolean>();
    Student student = new Student("username1", "password", "Student", 1,
                                  "", assignments, courses);
    accountList.addStudent(student);
    assertNotEquals("", students.get(1).getStudentName());
  }

  @Test
  void testWriteAssignmentInvalidId() {
    Assignment newAssignment = new Homework(-1, "New Assignment 1", "Homework",
                                            2021, 06, 25, 23, 59, 5.5, 15);
    assignmentList.addAssignment(newAssignment);
    boolean isInvalidId = assignments.get(0).getAssignmentId() <= 0;
    assertFalse(isInvalidId);
  }

  @Test
  void testWriteCourseInvalidId() {
    ArrayList<Assignment> assignments = new ArrayList<Assignment>();
    Course course = new Course(-1, "New Course 1", assignments);
    courseList.addCourse(course);
    boolean isInvalidId = courses.get(0).getCourseId() <= 0;
    assertFalse(isInvalidId);
  }

  @Test
  void testWriteAccountInvalidId() {
    ArrayList<Course> courses = new ArrayList<Course>();
    HashMap<Assignment, Boolean> assignments = new HashMap<Assignment, Boolean>();
    Student student = new Student("username1", "password", "Student", -1,
                                  "New User", assignments, courses);
    accountList.addStudent(student);
    boolean isInvalidId = students.get(0).getStudentId() <=0;
    assertFalse(isInvalidId);
  }

  @Test
  void testWriteDuplicateUsername() {
    ArrayList<Course> courses = new ArrayList<Course>();
    HashMap<Assignment, Boolean> assignments = new HashMap<Assignment, Boolean>();
    Student student1 = new Student("duplicate", "password", "Student", 1,
                                   "New User 1", assignments, courses);
    Student student2 = new Student("duplicate", "password", "Student", 2,
                                   "New User 2", assignments, courses);
    accountList.addStudent(student1);
    accountList.addStudent(student2);
    assertNotEquals("duplicate", accountList.getAccount(2).getUsername());
  }
}
