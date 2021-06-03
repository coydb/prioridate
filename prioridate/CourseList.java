package prioridate;

import java.util.ArrayList;

public class CourseList {
  private static CourseList courseList = null;
  private static ArrayList<Course> courses;

  private CourseList() {
    courses = DataLoader.getCourses();
  }
  public static CourseList getInstance() {
    if(courseList == null) {
      return new CourseList();
    } else {
      return courseList;
    }
  }
  public void addCourse(Course courseToAdd) {

  }
  public Course getCourse(String courseName) {
    // default behavior to allow compilation
    return new Course();
  }
  public static ArrayList<Course> getCourses() {
    return courses;
  }
}
