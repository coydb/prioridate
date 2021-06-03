package prioridate;

public class Quiz extends Assignment {
    private double timeLimit;
    private int numQuestions;

    public Quiz(int assignmentId, String title, String type, String dueDate, String dueTime, double percentOfGrade, double timeLimit, int numQuestions) {
        super(assignmentId, title, type, dueDate, dueTime, percentOfGrade);
        setTimeLimit(timeLimit);
        setNumQuestions(numQuestions);
        setPriority();
    }

    public void setPriority() {
        this.priority = 0;
    }

    public int getPriority() {
        return this.priority;
    }

    public void setTimeLimit(double timeLimit) {
        this.timeLimit = timeLimit;
    }

    public double getTimeLimit() {
        return this.timeLimit;
    }

    public void setNumQuestions(int numQuestions) {
        this.numQuestions = numQuestions;
    }

    public int getNumQuestions() {
        return this.numQuestions;
    }

    public String toString(){
        return "AssignmentID: " + this.assignmentId 
        + "\nTitle: " + this.title + "\nType: " + this.type
        + "\nDue Date: " + this.dueDate + "\nDue Time: "
        + this.dueTime + "\nPercent of Grade: " + this.percentOfGrade
        + "\nTime Limit: " + this.timeLimit + "\nNumber of Questions: "
        + this.numQuestions + "\nPriority: " + this.priority;
    }
}
