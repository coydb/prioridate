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
        return super.toString()
        + "\nTime Limit: " + getTimeLimit() + "\nNumber of Questions: "
        + getNumQuestions() + "\nPriority: " + getPriority();
    }
}
