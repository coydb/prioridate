package prioridate;

public class Exam extends Assignment {
    private double timeLimit;
    private int numQuestions;
    private String questionType; //enumeration?
    private String location;
    private String dateAndTime; //need method to parse?

    public Exam(String title, String subject, String dueDate, double percentage, 
                boolean isCompleted, int priority, double timeLimit, int numQuestions, 
                String questionType, String location, String dateAndTime) {
        super(title, subject, dueDate, percentage, isCompleted, priority);
        this.timeLimit = timeLimit;
        this.numQuestions = numQuestions;
        this.questionType = questionType;
        this.location = location;
        this.dateAndTime = dateAndTime;
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
