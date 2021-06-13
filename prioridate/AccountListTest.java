package prioridate;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.HashMap;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AccountListTest {
  private AccountList accountList = AccountList.getInstance(); 
  private ArrayList<Student> students = accountList.getStudentList();
  private ArrayList<Teacher> teachers = accountList.getTeacherList();

  @BeforeEach
  void setup() {
    students.clear();
    teachers.clear();
    DataWriter.saveStudents();
    DataWriter.saveTeachers();
  }

  @AfterEach
  void teardown() {
    students.clear();
    teachers.clear();
    DataWriter.saveStudents();
    DataWriter.saveTeachers();
  }

  @Test
  void testGetNonExistentAccout() {
    assertEquals(null, accountList.getAccount(1));
  }

  @Test
  void testAddSingleStudent() {
    ArrayList<Course> courses = new ArrayList<Course>();
    HashMap<Assignment, Boolean> assignments = new HashMap<Assignment, Boolean>();
    Student student = new Student("newstudent1", "password", "Student", 1,
                                  "New User", assignments, courses);
    accountList.addStudent(student);
    assertEquals("newstudent1", accountList.getAccount(1).getUsername());
  }

  @Test
  void testAddSingleTeacher() {
    ArrayList<Course> courses = new ArrayList<Course>();
    Teacher teacher = new Teacher("newteacher1", "password", "Teacher", 1,
                                  "New Teacher", courses);
    accountList.addTeacher(teacher);
    assertEquals("newteacher1", accountList.getAccount(1).getUsername());
  }

  @Test
  void testAddLargeNumberOfStudents() {
    for(int i = 1; i <= 1000; i++) {
      ArrayList<Course> courses = new ArrayList<Course>();
      HashMap<Assignment, Boolean> assignments = new HashMap<Assignment, Boolean>();
      Student student = new Student("newstudent"+i, "password", "Student", i,
                                    "New User #"+i, assignments, courses);
      accountList.addStudent(student);
    }
    for(int i = 1000; i > 0; i--) {
    assertEquals("newstudent"+i, accountList.getAccount(i).getUsername());
    }
  }

  @Test
  void testAddLargeNumberOfTeachers() {
    for(int i = 1; i <= 1000; i++) {
      ArrayList<Course> courses = new ArrayList<Course>();
      Teacher teacher = new Teacher("newteacher"+i, "password", "Teacher", i,
                                    "New Teacher #"+i, courses);
      accountList.addTeacher(teacher);
    }
    for(int i = 1000; i > 0; i--) {
      assertEquals("newteacher"+i, accountList.getAccount(i).getUsername());
    }
  }

  @Test
  void testAccountIdsOutOfOrder() {
    int arraySize = 3;
    Student[] newStudents = new Student[arraySize];
    for(int i = 0; i < arraySize; i++) {
      HashMap<Assignment, Boolean> assignments = new HashMap<Assignment, Boolean>();
      ArrayList<Course> courses = new ArrayList<Course>();
      Student newStudent = new Student("username", "password", "Student", i+1,
                                       "New Student", assignments, courses);
      newStudents[i] = newStudent;
    }
    for(int i = arraySize-1; i >= 0; i--) {
      accountList.addStudent(newStudents[i]);
    }
    int highestId = accountList.getHighestUserId();
    assertEquals(3, highestId);
  }


  @Test
  void testGetHighestAccountIdEmptyList() {
    int highestId = accountList.getHighestUserId()*0;
    // comparison below should be true as long as
    // an int is return above
    assertEquals(0, highestId);
  }

  @Test
  void testSucessfulLogin() {
    ArrayList<Course> courses = new ArrayList<Course>();
    HashMap<Assignment, Boolean> assignments = new HashMap<Assignment, Boolean>();
    Student studentIn = new Student("newstudent1", "password", "Student", 1,
                                    "New User", assignments, courses);
    accountList.addStudent(studentIn);
    Account studentOut = accountList.login("newstudent1", "password");
    assertEquals(studentIn.getUsername(), studentOut.getUsername());
  }

  @Test
  void testIncorrectUsername() {
    ArrayList<Course> courses = new ArrayList<Course>();
    HashMap<Assignment, Boolean> assignments = new HashMap<Assignment, Boolean>();
    Student studentIn = new Student("newstudent1", "password", "Student", 1,
                                    "New User", assignments, courses);
    accountList.addStudent(studentIn);
    assertEquals(null, accountList.login("newstudent2", "password"));
  }

  @Test
  void testIncorrectPassword() {
    ArrayList<Course> courses = new ArrayList<Course>();
    HashMap<Assignment, Boolean> assignments = new HashMap<Assignment, Boolean>();
    Student studentIn = new Student("newstudent1", "password", "Student", 1,
                                    "New User", assignments, courses);
    accountList.addStudent(studentIn);
    assertEquals(null, accountList.login("newstudent1", "Password"));
  }

  @Test
  void testAddAssignmentToExistingStudent() {
    // - passes a student to the addStudent method with an empty assignment list
    // - then adds an assignment to the student's assignment list, 
    //   and passes the same student to the addStudent method again, overwriting
    //   the empty assignment list with the new one. 
    ArrayList<Course> courses = new ArrayList<Course>();
    HashMap<Assignment, Boolean> assignments = new HashMap<Assignment, Boolean>();
    Student student = new Student("newstudent1", "password", "Student1", 1,
                                  "New User", assignments, courses);
    accountList.addStudent(student);
    Assignment newAssignment = new Homework(1, "New Homework 1", "Homework",
                                            2021, 06, 25, 23, 59, 5.5, 15);
    student.addAssignment(newAssignment);
    accountList.addStudent(student);
    Student studentAfterEdit = (Student)accountList.getAccount(1);
    // newly added assignment should now be present in the student's assignments list
    assertEquals(false, studentAfterEdit.getAssignments().get(newAssignment));
  }
}
