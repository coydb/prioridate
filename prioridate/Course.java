package prioridate;

import java.util.ArrayList;

public class Course 
{
    private int courseId;
    private String className;
    private ArrayList<Assignment> assignments;

    public Course(int courseId, String className, ArrayList<Assignment> assignments)
    {
        this.courseId = courseId;
        this.className = className;
        this.assignments = new ArrayList<Assignment>(assignments);
    }

    public void viewStudents()
    {
        
    }

    public void teacherInfo(Teacher teacher)
    {

    }

    public void viewStudentsCompletion()
    {

    }

    public void addStudent()
    {

    }

    //public Student getStudent(String name)
    //{

    //}
    
    public int getCourseId()
    {
        return this.courseId;
    }

    public String getClassName()
    {
        return this.className;
    }

    public ArrayList<Assignment> getAssignments()
    {
        return this.assignments;
    }

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