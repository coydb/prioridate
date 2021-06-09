package prioridate;

import java.util.ArrayList;

/**
 * Class that creates template for Course classes.  
 */
public class Course 
{
    private int courseId;
    private String className;
    private ArrayList<Assignment> assignments;

    /**
     * @param courseId ID of course
     * @param className Name of course
     * @param assignments assignments in course
     */
    public Course(int courseId, String className, ArrayList<Assignment> assignments)
    {
        this.courseId = courseId;
        this.className = className;
        this.assignments = new ArrayList<Assignment>(assignments);
    }
    
    /**
     * Gets ID of course
     * @return returns course ID
     */
    public int getCourseId()
    {
        return this.courseId;
    }

    /**
     * Gets name of class
     * @return returns class's name
     */
    public String getClassName()
    {
        return this.className;
    }

    /**
     * Gets list of assignments
     * @return returns array of assignments for course. 
     */
    public ArrayList<Assignment> getAssignments()
    {
        return this.assignments;
    }

    /**
     * Loop which returns information about the Course. 
     */
    public String toString() 
    {
        String outString = "\nCourse Id: " + courseId;
        outString += "\nClass Name: " + className;
        outString += "\nAssignments: \n";
        for (Assignment assignment : assignments) 
        {
            outString += assignment.toString();
        }
        return outString;
    }
}