package prioridate;
// TO-DO:
// - setPriority (just stub rn)
// - flesh out setters, need to have checks for setting. basic rn for compilation
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
        this.timeLimit = timeLimit;
    }

    public int getNumQuestions() {
        return this.numQuestions;
    }

    public void setNumQuestions(int numQuestions) {
        this.numQuestions = numQuestions;
    }

    public String getQuestionType() {
        return this.questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String toString() {
        return "AssignmentID: " + this.assignmentId 
        + "\nTitle: " + this.title + "\nType: " + this.type
        + "\nDue Date: " + this.dueDate + "\nDue Time: "
        + this.dueTime + "\nPercent of Grade: " + this.percentOfGrade
        + "\nTime Limit: " + this.timeLimit + "\nNumber of Questions: "
        + this.numQuestions + "\nQuestion Type: " + this.questionType
        + "\nLocation: " + this.location + "\nPriority: " + this.priority;
    }


}
