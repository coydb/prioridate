package prioridate;

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
}
