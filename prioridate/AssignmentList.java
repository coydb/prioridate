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
    assignmentList.printAssignmentList();
    Homework newHomework = new Homework(0, "Test Assignment", "Homework", "Never", "Never", 0.0, 8000000);
    assignmentList.addAssignment(newHomework);
    System.out.println("\n");
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
    // default behavior to allow compilation
    return assignments;
    
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
      Assignment currentAssignment = assignments.get(i);
      if(currentAssignment.getType().equalsIgnoreCase("homework")) {
        Homework currentHomework = (Homework)assignments.get(i);
        System.out.println(currentHomework.toString());
      }
      if(currentAssignment.getType().equalsIgnoreCase("quiz")) {
        Quiz currentQuiz = (Quiz)assignments.get(i);
        System.out.println(currentQuiz.toString());
      }
    }
  }
}