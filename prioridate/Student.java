package prioridate;

import java.util.ArrayList;
import java.util.HashMap;

public class Student 
{
    private int studentId;
    private String studentName;
    private HashMap<Boolean, Assignment> assignments;

    public Student()
    {
        this.studentId = studentId;
        this.studentName = studentName;
        this.assignments = new HashMap<Boolean, Assignment>();
          
    }

    private void viewAssignmentsCompleted() 
    {
        for(Boolean key : assignments.keySet())
        {
            if(assignments.containsKey(true))
            {
                System.out.println(assignments.get(key).getTitle());
            }
        }
    }

    private void viewAssignmentsDue()
    {
        for(Boolean key : assignments.keySet())
        {
            if(assignments.containsKey(false))
            {
                System.out.println(assignments.get(key).getTitle());
            }
        }
    }

    public double calculateCompletionPercent()
    {
        int assignmentsCompleted = 0;
        int assignmentsDue = 0;

        for(Boolean key : assignments.keySet())
        {
            if(assignments.containsKey(true))
            {
                assignmentsCompleted++;
            }
            else if(assignments.containsKey(false))
            {
                assignmentsDue++;
            }
        }
        
        double completionPercent = ((double)assignmentsCompleted / (assignmentsCompleted + assignmentsDue));
        return completionPercent;
    }

   public void checkOffAssignment(Assignment assignment)
   {
        assignments.remove(assignment);
        assignments.put(true, assignment);
   }

   public int getStudentId()
   {
        return this.studentId;
   }

   public String getStudentName()
   {
       return this.studentName;
   }

   public HashMap<Boolean, Assignment> getAssignments()
   {
       return this.assignments;
   }

}