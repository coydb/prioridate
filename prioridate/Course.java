package prioridate;

import java.util.ArrayList;

public class Course 
{
    private int courseId;
    private String className;
    private ArrayList<Assignment> assignments;
    private ArrayList<Student> students;

    public Course()
    {
        this.courseId = courseId;
        this.className = className;
        this.assignments = new ArrayList<Assignment>();
        this.students = new ArrayList<Student>();
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



    
}