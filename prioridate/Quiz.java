package prioridate;

public class Quiz extends Assignment {
    private double timeLimit;
    private int numQuestions;

    public Quiz(String title, String subject, String dueDate, double percentage, boolean isCompleted, int priority, double timeLimit, int numQuestions) {
        super(title, subject, dueDate, percentage, isCompleted, priority);
        this.timeLimit = timeLimit;
        this.numQuestions = numQuestions;
    }

    public double getPercentage() {
        return this.percentage;
    }

    public String getDueDate() {
        return this.dueDate;
    }

    public int getPriority() {
        int priority = 0;
        return priority;
    }
}
