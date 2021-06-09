package prioridate;

import java.util.ArrayList;

/**
 * Template for teacher classes. 
 */
public class Teacher extends Account
{
    private int teacherId;
    private String teacherName;
    private ArrayList<Course> courses;

    /**
     * @param username Teacher's username
     * @param password Teacher's password
     * @param type
     * @param teacherId Teacher's ID
     * @param teacherName Teacher's Name
     * @param courses Teacher's Courses
     */
    public Teacher(String username, String password, String type, int teacherId, String teacherName, ArrayList<Course> courses)
    {
        super(type, username, password);
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.courses = new ArrayList<Course>(courses);
    }

    /**
     * Gets teacher's ID
     * @return returns ID of teacher
     */
    public int getTeacherId()
    {
        return this.teacherId;
    }

    /**
     * Gets teacher's name
     * @return returns name of teacher. 
     */
    public String getTeacherName()
    {
        return this.teacherName;
    }

    /**
     * Gets course list
     * @return returns list of teacher's courses.
     */
    public ArrayList<Course> getCourses()
    {
        return this.courses;
    }

    /**
     * Loop which returns data from the teacher class. 
     */
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