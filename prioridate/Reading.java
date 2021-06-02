package prioridate;

public class Reading extends Assignment {
    private String[] chapters;
    private int numPages;

    public Reading(String title, String subject, String dueDate, double percentage, boolean isCompleted, int priority, String[] chapters, int numPages) {
        super(title, subject, dueDate, percentage, isCompleted, priority);
        this.chapters = chapters;
        this.numPages = numPages;
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
