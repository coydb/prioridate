package index255;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CourseListTest {
  private CourseList courseList = CourseList.getInstance();
  private ArrayList<Course> courses = courseList.getCourses();

  @BeforeEach
  void setup() {
    courses.clear();
    DataWriter.saveCourses();
  }

  @AfterEach
  void teardown() {
    courses.clear();
    DataWriter.saveCourses();
  }

  @Test
  void testGetNonExistentCourse() {
    assertEquals(null, courseList.getCourse(1));
  }

  @Test
  void testAddSingleCourse() {
    ArrayList<Assignment> assignmentsEmpty = new ArrayList<Assignment>();
    Course course = new Course(1, "New Course", assignmentsEmpty);
    courseList.addCourse(course);
    assertEquals("New Course", courseList.getCourse(1).getClassName());
  }

  @Test
  void testAddLargeNumberOfCourses() {
    for(int i = 1; i <= 1000; i++) {
      ArrayList<Assignment> assignments = new ArrayList<Assignment>();
      Course course = new Course(i, "New Course #"+i, assignments);
      courseList.addCourse(course);
    }

    for(int i = 1000; i > 0; i--) {
      assertEquals("New Course #"+i, courseList.getCourse(i).getClassName());
    }
  }

  @Test
  void testCourseIdsOutOfOrder() {
    int arraySize = 3;
    Course[] newCourses = new Course[arraySize];
    for(int i = 0; i < arraySize; i++) {
      ArrayList<Assignment> assignments = new ArrayList<Assignment>();
      Course newCourse = new Course(i+1, "New Course #"+(i+1), assignments);
      newCourses[i] = newCourse;
    }
    for(int i = arraySize-1; i >= 0; i--) {
      courseList.addCourse(newCourses[i]);
    }
    int highestId = courseList.getHighestCourseId();
    assertEquals(3, highestId);
  }

  @Test
  void testGetHighestCourseIdEmptyList() {
    int highestId = courseList.getHighestCourseId()*0;
    // comparison below should be true as long as
    // an int is return above
    assertEquals(0, highestId);
  }

  @Test
  void testMakeDuplicateCourses() {
    for(int i = 1; i <= 2; i++) {
      ArrayList<Assignment> assignments = new ArrayList<Assignment>();
      Course course = new Course(i, "New Course", assignments);
      courseList.addCourse(course);
    }

    for(int i = 2; i > 0; i--) {
      assertEquals("New Course", courseList.getCourse(i).getClassName());
    }
  }

  @Test
  void testAddLargeCourseName() {
    String largeName = "a";
    for(int i = 1; i <= 1000; i++) {
      largeName = largeName + "a";
    }
    ArrayList<Assignment> assignments = new ArrayList<Assignment>();
    Course course = new Course(1, largeName, assignments);
    courseList.addCourse(course);
    assertEquals(largeName, courseList.getCourse(1).getClassName());
  }

  @Test
  void testAddLargeNumofAssignments() {
    ArrayList<Assignment> assignments = new ArrayList<Assignment>();
    for(int i = 1; i <= 1000; i++) {
      Homework homework = new Homework(i, "Assignment title"+i, "Homework", 2022, 11, 6, 11, 59, 5.0, 20);
      assignments.add(homework);
    }
    Course course = new Course(1, "New Course", assignments);
    courseList.addCourse(course);
    for(int i = 1; i <= 1000; i++) {
      assertEquals(assignments.get(i), courseList.getCourse(1).getAssignments().get(i));
    }
    
  }
  
  @Test
  void testSkipCourseId() {
    ArrayList<Assignment> assignmentsEmpty = new ArrayList<Assignment>();
    Course course = new Course(1, "New Course", assignmentsEmpty);
    Course course2 = new Course(2, "New Course", assignmentsEmpty);
    Course course3 = new Course(3, "New Course", assignmentsEmpty);
    Course course5 = new Course(5, "New Course", assignmentsEmpty);
    courseList.addCourse(course);
    courseList.addCourse(course2);
    courseList.addCourse(course3);
    courseList.addCourse(course5);
    int highestId = courseList.getHighestCourseId();
    assertEquals(5, highestId);
  }

}
