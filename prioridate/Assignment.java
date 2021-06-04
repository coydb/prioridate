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

    public String toString() {
        return "AssignmentID: " + getAssignmentId() 
        + "\nTitle: " + getTitle() + "\nType: " + getType()
        + "\nDue Date: " + getDueDate() + "\nDue Time: "
        + getDueTime() + "\nPercent of Grade: " + getPercentOfGrade();
    }

    public abstract void setPriority();

    public int getPriority() {
        return this.priority;
    }

    public int getAssignmentId() {
        return this.assignmentId;
    }

    public void setAssignmentId(int assignmentId) {
        if(assignmentId >= 0)
            this.assignmentId = assignmentId;
        else
            this.assignmentId = 0;  // default if left blank
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        if(title != null)
            this.title = title;
        else
            this.title = "New Assignment";  // default if no title given
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        if(type != null)
            this.type = type;
        else
            this.type = "Homework";  // default type of assignment
    }

    public String getDueDate() {
        return this.dueDate;
    }
//DUE DATE AND TIME NEED TO BE DATE OBJECT
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
        if(percentOfGrade >= 0.0)
            this.percentOfGrade = percentOfGrade;
        else
            this.percentOfGrade = 0.0;  // default has no effect on grade
    }
}
