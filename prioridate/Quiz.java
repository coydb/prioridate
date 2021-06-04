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
        if(timeLimit > 0.0)
            this.timeLimit = timeLimit;
        else
            this.timeLimit = 1.0;  // default time limit
    }

    public double getTimeLimit() {
        return this.timeLimit;
    }

    public void setNumQuestions(int numQuestions) {
        if(numQuestions > 0)
            this.numQuestions = numQuestions;
        else
            this.numQuestions = 1;  // default
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
