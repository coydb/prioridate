package prioridate;

import java.util.ArrayList;

public class AssignmentList {
  private static AssignmentList assignmentList = null;
  private static ArrayList<Assignment> assignments;

  /**
   * For Testing Purposes Only
   * @param args
   */
  public static void main(String[] args) {
    AssignmentList assignmentList = AssignmentList.getInstance();
    AssignmentList.printAssignmentList();
  }


  private AssignmentList() {
    assignments = DataLoader.getAssignments();
  }
  public static AssignmentList getInstance() {
    if (assignmentList == null) {
      return new AssignmentList();
    } else {
      return assignmentList;
    }
  }
  public Assignment getAssignment(int assignmentId) {
    // default behavior to allow compilation
    return new Homework(0, "test", "test", "test", "test", 0.0, 0);
    
  }
  public void addAssignment(Assignment assignmentToAdd) {

  }
  public static void printAssignmentList() {
    for (int i = 0;i < assignments.size();i++) {
      Assignment currentAssignment = assignments.get(i);
      if(currentAssignment.getType().equalsIgnoreCase("homework")) {
        Homework currentHomework = (Homework)assignments.get(i);
        System.out.println("Assignment Id: "+currentHomework.getAssignmentId());
        System.out.println("Title: "+currentHomework.getTitle());
        System.out.println("Type: "+currentHomework.getType());
        System.out.println("Number of Questions: "+currentHomework.getNumQuestions());
        System.out.println("Due Date: "+currentHomework.getDueDate());
        System.out.println("Due Time: "+currentHomework.getDueTime());
        System.out.println("Percent of Grade: "+currentHomework.getPercentOfGrade()+"\n");
      }
      if(currentAssignment.getType().equalsIgnoreCase("quiz")) {
        Quiz currentQuiz = (Quiz)assignments.get(i);
        System.out.println("Assignment Id: "+currentQuiz.getAssignmentId());
        System.out.println("Title: "+currentQuiz.getTitle());
        System.out.println("Type: "+currentQuiz.getType());
        System.out.println("Time Limit: "+currentQuiz.getTimeLimit());
        System.out.println("Number of Questions: "+currentQuiz.getNumQuestions());
        System.out.println("Due Date: "+currentQuiz.getDueDate());
        System.out.println("Due Time: "+currentQuiz.getDueTime());
        System.out.println("Percent of Grade: "+currentQuiz.getPercentOfGrade());
      }
    }
  }
}
