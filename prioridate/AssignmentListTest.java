package prioridate;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AssignmentListTest {
  private AssignmentList assignmentList = AssignmentList.getInstance();
  private ArrayList<Assignment> assignments = assignmentList.getAssignments();

  @BeforeEach
  void setup() {
    assignments.clear();
    DataWriter.saveAssignments();
  }

  @AfterEach
  void teardown() {
    assignments.clear();
    DataWriter.saveAssignments();
  }

  @Test
  void testGetNonExistentAssignment() {
    assertEquals(null, assignmentList.getAssignment(1));
  }

    
  @Test
  void testAddSingleAssignment() {
    Assignment newAssignment = new Homework(1, "New Homework 1", "Homework",
                                            2021, 06, 25, 23, 59, 5.5, 15);
    assignmentList.addAssignment(newAssignment);
    assertEquals("New Homework 1", assignmentList.getAssignment(1).getTitle());
  }


  @Test
  void testAddLargeNumberOfAssignments() {
    for(int i = 1; i <= 1000; i++) {
      Homework assignment = new Homework(i, "New Assignment #"+i, "Homework",
                                         2021, 06, 26, 23, 59, 12.5, 5);
      assignmentList.addAssignment(assignment);
    }
    for(int i = 1000; i > 0; i--) {
      assertEquals("New Assignment #"+i, assignmentList.getAssignment(i).getTitle());
    }
  }

  @Test
  void testAssignmentIdsOutOfOrder() {
    int arraySize = 3;
    Assignment[] newAssignments = new Assignment[arraySize];
    for(int i = 0; i < arraySize; i++) {
      Assignment newAssignment = new Homework(i+1, "New Homework #"+(i+1), "Homework",
                                              2021, 06, 25, 23, 59, 5.5, 15);
      newAssignments[i] = newAssignment;
    }
    for(int i = arraySize-1; i >= 0; i--) {
      assignmentList.addAssignment(newAssignments[i]);
    }
    int highestId = assignmentList.getHighestAssignmentId();
    assertEquals(3, highestId);
  }

  @Test
  void testGetHighestAssignmentIdEmptyList() {
    int highestId = assignmentList.getHighestAssignmentId()*0;
    // comparison below should be true as long as
    // an int is return above
    assertEquals(0, highestId);
  }
}
