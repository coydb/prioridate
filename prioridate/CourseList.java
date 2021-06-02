package prioridate;

import java.util.ArrayList;

public class CourseList {
  private static CourseList courseList;
  private ArrayList<Course> courses;

  private CourseList() {
    
  }
  public CourseList getInstance() {
    return this;
  }
  public void addCourse(Course courseToAdd) {

  }
  public Course getCourse(String courseName) {
    // default behavior to allow compilation
    return new Course();
  }
  
}
