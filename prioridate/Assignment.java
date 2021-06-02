package prioridate;

public abstract class Assignment {
    protected String title;
    protected String subject;
    protected String dueDate;
    protected double percentage;
    protected boolean isCompleted; //subject to change? --> [String: tag (to-do, doing, done)]?
    protected int priority; //calculated with getPriority, every assignment needs this

    public Assignment(String title, String subject, String dueDate, double percentage, boolean isCompleted, int priority) {
        this.title = title;
        this.subject = subject;
        this.dueDate = dueDate;
        this.percentage = percentage;
        this.isCompleted = false;
        this.priority = this.getPriority();
    }

    public abstract double getPercentage();

    public abstract String getDueDate();

    public abstract int getPriority();


}
