package prioridate;
// TO-DO:
// - setPriority - do actual calc.
// - flesh out setters with checks
public class Homework extends Assignment {
    private int numQuestions;

    public Homework(int assignmentId, String title, String type, String dueDate, String dueTime, double percentOfGrade, int numQuestions) {
        super(assignmentId, title, type, dueDate, dueTime, percentOfGrade);
        setNumQuestions(numQuestions);
        setPriority();
    }

    public void setPriority() {
        this.priority = 0;
    }

    public int getPriority() {
        return this.priority;
    }

    public void setNumQuestions(int numQuestions) {
        this.numQuestions = numQuestions;
    }

    public int getNumQuestions() {
        return this.numQuestions;
    }

    public String toString() {
        return "AssignmentID: " + this.assignmentId 
        + "\nTitle: " + this.title + "\nType: " + this.type
        + "\nDue Date: " + this.dueDate + "\nDue Time: "
        + this.dueTime + "\nPercent of Grade: " + this.percentOfGrade
        + "\nNumber of Questions: " + numQuestions
        + "\nPriority: " + this.priority;
    }
}
