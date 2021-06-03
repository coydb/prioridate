package prioridate;
// TO-DO:
// - flesh out setters with checks
public abstract class Assignment {
    protected int assignmentId;
    protected String title;
    protected String type;
    protected String dueDate;
    protected String dueTime;
    protected double percentOfGrade;
    protected int priority; //calculated with getPriority, every assignment needs this

    public Assignment(int assignmentId, String title, String type, String dueDate, String dueTime, double percentOfGrade) {
        setAssignmentId(assignmentId);
        setTitle(title);
        setType(type);
        setDueDate(dueDate);
        setDueTime(dueTime);
        setPercentOfGrade(percentOfGrade);
    }

    public abstract void setPriority();

    public int getPriority() {
        return this.priority;
    }

    public int getAssignmentId() {
        return this.assignmentId;
    }

    public void setAssignmentId(int assignmentId) {
        this.assignmentId = assignmentId;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDueDate() {
        return this.dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getDueTime() {
        return this.dueTime;
    }

    public void setDueTime(String dueTime) {
        this.dueTime = dueTime;
    }

    public double getPercentOfGrade() {
        return this.percentOfGrade;
    }

    public void setPercentOfGrade(double percentOfGrade) {
        this.percentOfGrade = percentOfGrade;
    }
}
