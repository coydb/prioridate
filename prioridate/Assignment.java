package prioridate;

import java.util.Calendar;
import java.util.Locale;

// TO-DO:
// - flesh out setters with checks
public abstract class Assignment {
    protected int assignmentId;
    protected String title;
    protected String type;
    protected Calendar dueDate;
    protected double percentOfGrade;
    //protected int priority; //calculated with getPriority, every assignment needs this

    public Assignment(int assignmentId, String title, String type, int dueYear, int dueMonth, int dueDay, int dueHour, int dueMin, double percentOfGrade) {
        setAssignmentId(assignmentId);
        setTitle(title);
        setType(type);
        setDueDate(dueYear, dueMonth, dueDay, dueHour, dueMin);
        setPercentOfGrade(percentOfGrade);
    }

    public String toString() {
        String s = (getDueDate().get(Calendar.AM_PM) == 0) ? "AM" : "PM";
        return "AssignmentID: " + getAssignmentId() 
        + "\nTitle: " + getTitle() + "\nType: " + getType()
        + "\nDue: " + getDueDate().getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()) 
        + " " + getDueDate().get(Calendar.DAY_OF_MONTH) + ", " + getDueDate().get(Calendar.YEAR) + " at "
        + getDueDate().get(Calendar.HOUR) + ":" + getDueDate().get(Calendar.MINUTE) + " " + s
        + "\nPercent of Grade: " + getPercentOfGrade();
    }

    public void setDueDate(int dueYear, int dueMonth, int dueDay, int dueHour, int dueMin) {
        Calendar dueDate = Calendar.getInstance();
        dueDate.set(setDueYear(dueYear), setDueMonth(dueMonth), setDueDay(dueDay), setDueHour(dueHour), setDueMin(dueMin), 00);  // the last param is the seconds
        this.dueDate = dueDate;
    }

    public Calendar getDueDate() {
        return this.dueDate;
    }

    public int calculatePriority() {
// calc some here to call super in others
        return 0;
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

    public int setDueYear(int year) {
        Calendar today = Calendar.getInstance();
        int dueYear;
        if(year < today.get(Calendar.YEAR))  // makes sure due date is not in the past
            dueYear = today.get(Calendar.YEAR);  // default year is this year
        else
            dueYear = year;
        return dueYear;
    }

    public int setDueMonth(int month) {
        Calendar today = Calendar.getInstance();
        int dueMonth;
        if(month < today.get(Calendar.MONTH) || month > 12 || month < 1)  // makes sure due date is not in the past and is a valid month
            dueMonth = today.get(Calendar.MONTH);  // default year is this year    
        else
            dueMonth = month;
        return dueMonth;
    }

    public int setDueDay(int day) {
        Calendar today = Calendar.getInstance();
        int dueDay;
        if(day < 1 || day > 31)  // assuming they will make the correct day for months, just accounting for stuff definitely wrong
            dueDay = today.get(Calendar.DAY_OF_MONTH);  // default day is today
        else
            dueDay = day;
        return dueDay;
    }

    public int setDueHour(int hour) {
        Calendar today = Calendar.getInstance();
        int dueHour;
        if(hour < 0 || hour > 24)
            dueHour = today.get(Calendar.HOUR_OF_DAY);
        else
            dueHour = hour;
        return dueHour;
    }

    public int setDueMin(int min) {
        Calendar today = Calendar.getInstance();
        int dueMin;
        if(min < 0 || min > 59)
            dueMin = today.get(Calendar.MINUTE);
        else
            dueMin = min;
        return dueMin;
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
