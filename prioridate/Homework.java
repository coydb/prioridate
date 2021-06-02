package prioridate;

public class Homework extends Assignment {
    private int numQuestions;

    public Homework(String title, String subject, String dueDate, double percentage, boolean isCompleted, int priority, int numQuestions) {
        super(title, subject, dueDate, percentage, isCompleted, priority);
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
