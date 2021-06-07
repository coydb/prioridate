package prioridate;

import java.util.HashMap;

public class Student extends Account
{
    private int studentId;
    private String studentName;
    private HashMap<Assignment, Boolean> assignments;

    public Student(String username, String password, String type, int studentId, String studentName, HashMap<Assignment, Boolean> assignments)
    {
        super(type, username, password);
        this.studentId = studentId;
        this.studentName = studentName;
        this.assignments = assignments;
          
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
        assignments.put(assignment, true);
   }

   public int getStudentId()
   {
        return this.studentId;
   }

   public String getStudentName()
   {
       return this.studentName;
   }

   public HashMap<Assignment, Boolean> getAssignments()
   {
       return this.assignments;
   }

   public String toString()
   {
       String outString = "\nStudent Id: " + studentId;
       outString += "\nStudent Name: " + studentName;
       outString += "\nAssignments: \n";
       for(Assignment assignment : assignments.keySet())
       {
            outString += assignment.toString();           
       }
       return outString;

   }

}