package prioridate;

import java.util.ArrayList;

public class CourseList {
  private static CourseList courseList = null;
  private static ArrayList<Course> courses;

    /**
   * For Testing Purposes Only
   * @param args
   */
  public static void main(String[] args) {
    CourseList courseList = CourseList.getInstance();
    courseList.printCourseList();
  }

  private CourseList() {
    courses = DataLoader.getCourses();
  }
  public static CourseList getInstance() {
    if(courseList == null) {
      courseList = new CourseList();
    }
      return courseList;
  }
  public void addCourse(Course courseToAdd) {

  }
  public void getCourse(String courseName) {
    // default behavior to allow compilation
  }
  public static ArrayList<Course> getCourses() {
    return courses;
  }

  public void printCourseList() {
    for (int i = 0; i < courses.size();i++) {
      System.out.println(courses.get(i).toString());
    }
  }
}
