package prioridate;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Stores template for other student classes to be created. 
 */
public class Student extends Account
{
    private int studentId;
    private String studentName;
    private HashMap<Assignment, Boolean> assignments;
    private ArrayList<Course> courses;

    /**
     * @param username Username of the student  
     * @param password Student's password
     * @param type
     * @param studentId ID of student
     * @param studentName Name of student
     * @param assignments Assignments the student has
     * @param courses Courses the Student are in
     */
    public Student(String username, String password, String type, int studentId, String studentName, HashMap<Assignment, Boolean> assignments, ArrayList<Course> courses)
    {
        super(type, username, password);
        this.studentId = studentId;
        this.studentName = studentName;
        this.assignments = assignments;
        this.courses = courses;
          
    }

    /**
     * gets student ID
     * @return returns int which is student ID
     */
   public int getStudentId()
   {
        return this.studentId;
   }

   /**
    * Gets the student name
    * @return returns name of student
    */
   public String getStudentName()
   {
       return this.studentName;
   }

   /**
    * Gets the assignments of the student
    * @return returns the student's assignments
    */
   public HashMap<Assignment, Boolean> getAssignments()
   {
       return this.assignments;
   }

   /**
    * Adds inputted course to the Student class. 
    * @param courseToAdd course which is added
    */
   public void addCourse(Course courseToAdd) {
    courses.add(courseToAdd);
    for(int i = 0; i <courseToAdd.getAssignments().size();i++) {
        addAssignment(courseToAdd.getAssignments().get(i));
    }
   }

   /**
    * Adds inputted assignment to Student class.
    * @param assignmentToAdd assignment which is added.
    */
   public void addAssignment(Assignment assignmentToAdd) {
    assignments.put(assignmentToAdd, false);
   }

   /**
    * Gets list of courses assigned to the Student class. 
    * @return returns list of Student's courses
    */
   public ArrayList<Course> getCourses() {
       return this.courses;
   }

   /**
    * Loop which returns information from the Student class.
    */
   public String toString()
   {
       String outString = "\nStudent Id: " + studentId;
       outString += "\nStudent Name: " + studentName;
       outString += "\nAssignments: \n";
       for(Assignment assignment : assignments.keySet())
       {
            outString += assignment.toString();           
       }
       for(Course course : courses) {
           outString += course.toString();
       }
       return outString;
   }

   /**
    * Sets inputted assignments to Student class
    * @param assignments Hashmap of student assignments. 
    */
   public void setAssignments(HashMap<Assignment, Boolean> assignments) {
       this.assignments = assignments;
   }

   /**
    * Sets inputted courses to Student class
    * @param courses ArrayList of student courses.
    */
   public void setCourses(ArrayList<Course> courses) {
       this.courses = courses;
   }
}