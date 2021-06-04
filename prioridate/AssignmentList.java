package prioridate;
import java.util.ArrayList;

/**
 * Stores all assignments in an ArrayList<Assignment>
 */
public class AssignmentList {
  private static AssignmentList assignmentList = null;
  private static ArrayList<Assignment> assignments = new ArrayList<Assignment>();

  /**
   * For Testing Purposes Only
   * @param args
   */
  public static void main(String[] args) {
    AssignmentList assignmentList = AssignmentList.getInstance();
    System.out.println("Made it past getInstance()");
    assignmentList.printAssignmentList();
  }

  /**
   * Private Constructor (use AssignmentList.getInstance() for instantiation)
   */
  private AssignmentList() {
    assignments = DataLoader.getAssignments();
  }

  /**
   * Returns the current instance of AssignmentList if one exists,
   * or creates and returns an instance of AssignmentList if one doesn't exist
   * @return The current instance of AssignmentList
   */
  public static AssignmentList getInstance() {
    if (assignmentList == null) {
      assignmentList = new AssignmentList();
    }
    return assignmentList;
  }

  /**
   * Accessor for the currently loaded list of assignments
   * @return An ArrayList of Assignments
   */
  public ArrayList<Assignment> getAssignments() {
    return assignments;
    
  }
  /**
   * Get a single Assignment from the assignment list
   * @param assignmentId An int representing the Id of the assignment
   * @return The assignment with the corresponding Id
   */
  public Assignment getAssignment(int assignmentId) {
    for (int i = 0; i < assignments.size();i++) {
      if (assignments.get(i).getAssignmentId() == assignmentId) {
        return assignments.get(i);
      }
    }
    return null;
  }

  /**
   * Adds an assignment to the current list of assignments
   * @param assignmentToAdd The Assignment to be added to the list
   */
  public void addAssignment(Assignment assignmentToAdd) {
    assignments.add(assignmentToAdd);
  }

  /**
   * Helper method for testing
   */
  private void printAssignmentList() {
    for (int i = 0;i < assignments.size();i++) {
      System.out.println("Made it here");
      System.out.println("==============Assignment #" + (i + 1) + "=============");
      Assignment currentAssignment = assignments.get(i);
      String typeOfCurrentAssignment = currentAssignment.getType();
      switch (typeOfCurrentAssignment.toLowerCase()) {
        case "homework":
          Homework currentHomework = (Homework)assignments.get(i);
          System.out.println(currentHomework.toString());
          break;
        case "quiz":
          Quiz currentQuiz = (Quiz)assignments.get(i);
          System.out.println(currentQuiz.toString());
          break;
        case "exam":
          Exam currentExam = (Exam)assignments.get(i);
          System.out.println(currentExam.toString());
          break;
        case "reading":
          Reading currentReading = (Reading)assignments.get(i);
          System.out.println(currentReading.toString());
          break;
        default:
          break;
      }
      System.out.println("========================================");
    }
  }
}