package prioridate;

import java.util.ArrayList;

public class Teacher extends Account
{
    private int teacherId;
    private String teacherName;
    private ArrayList<Course> courses;

    public Teacher(String username, String password, String type, int teacherId, String teacherName, ArrayList<Course> courses)
    {
        super(type, username, password);
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.courses = new ArrayList<Course>(courses);
    }

    public void viewStudents(Course course)
    {
        
    }

    public void viewCourses()
    {

    }

    public void checkStudentProgress(String name)
    {

    }

    public void addAssignment(Assignment assignment)
    {

    }

    public void addCourse(Course course)
    {

    }

    public int getTeacherId()
    {
        return this.teacherId;
    }

    public String getTeacherName()
    {
        return this.teacherName;
    }

    public ArrayList<Course> getCourses()
    {
        return this.courses;
    }

    public String toString()
    {
        String outString = "\nTeacher Id: " + teacherId;
        outString += "\nTeacher Name: " + teacherName;
        outString += "\nCourses: \n";
        for(Course course : courses)
        {
            outString += course.toString();
        }
        return outString;
        
        
    }


    
}