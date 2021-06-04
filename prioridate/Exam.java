package prioridate;
// TO-DO:
// - setPriority (just stub rn)
public class Exam extends Assignment {
    private double timeLimit;
    private int numQuestions;
    private String questionType; //enumeration?
    private String location;

    public Exam(int assignmentId, String title, String type, String dueDate, String dueTime, 
                double percentOfGrade, double timeLimit, int numQuestions, 
                String questionType, String location, String dateAndTime) {
        super(assignmentId, title, type, dueDate, dueTime, percentOfGrade);
        setTimeLimit(timeLimit);
        setNumQuestions(numQuestions);
        setQuestionType(questionType);
        setLocation(location);
        setPriority();
    }

    public void setPriority() {
        this.priority = 0;
    }

    public int getPriority() {
        return this.priority;
    }

    public double getTimeLimit() {
        return this.timeLimit;
    }

    public void setTimeLimit(double timeLimit) {
        if(timeLimit >= 0.0)
            this.timeLimit = timeLimit;
        else
            this.timeLimit = 1.0;  // default time limit of 1 hour
    }

    public int getNumQuestions() {
        return this.numQuestions;
    }

    public void setNumQuestions(int numQuestions) {
        if(numQuestions > 0)
            this.numQuestions = numQuestions;
        else
            this.numQuestions = 1;  // default num questions
    }

    public String getQuestionType() {
        return this.questionType;
    }

    public void setQuestionType(String questionType) {
        if(questionType != null)
            this.questionType = questionType;
        else
            this.questionType = "Multiple Choice";  // default if left empty
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        if(location != null)
            this.location = location;
        else
            this.location = "Classroom";  // default if empty
    }

    public String toString() {
        return super.toString()
        + "\nTime Limit: " + getTimeLimit() + "\nNumber of Questions: "
        + getNumQuestions() + "\nQuestion Type: " + getQuestionType()
        + "\nLocation: " + getLocation() + "\nPriority: " + getPriority();
    }


}
