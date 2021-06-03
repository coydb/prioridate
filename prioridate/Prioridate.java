package prioridate;
import java.util.ArrayList;

public class Prioridate {
    private AccountList accounts;
    private DataLoader dataAccessor;
    private DataWriter dataWriter;
    private Account currentAccount;

    public Prioridate() {
    
    }

    public void loadData(String filename) {

    }

    public Account login(String username, String password) {
        return accounts.login(username, password);
    }

    public void addAccount(String type, String username, String password) {
        accounts.addAccount(type, username, password);
    }
    // for students
    public void viewAssignmentsCompleted(ArrayList<Assignment> assignmentsCompleted) {
        
    }

    public void viewAssignmentsDue(ArrayList<Assignment> assignmentsDue) {

    }

    public double calculateCompletionPercent(ArrayList<Assignment> assignmentsDue, ArrayList<Assignment> assignmentsCompleted) {
        double percent = 100.0;
        return percent;
    }

    public void checkOffAssignment(ArrayList<Assignment> assignmentsDue) {

    }
    // for teachers
    public void viewStudents(Course course) {

    }

    public void viewCourses() {

    }

    public void checkStudentProgress(String name) {

    }

    public void assignAssignment(Assignment assignment) {

    }

    public void addCourse(Course course) {

    }

}
