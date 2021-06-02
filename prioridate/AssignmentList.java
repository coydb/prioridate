package prioridate;

import java.util.ArrayList;

public class AssignmentList {
  private static AssignmentList assignmentList;
  private ArrayList<Assignment> assignments;

  private AssignmentList() {

  }
  public AssignmentList getInstance() {
    return this;
  }
  public Assignment getAssignment(String assignmentName) {
    // default behavior to allow compilation
    return new Homework("title", "subject", "dueDate", 100.00, false, 3, 100);
  }
  public void addAssignment(Assignment assignmentToAdd) {

  }
}
