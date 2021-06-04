package prioridate;
// TO-DO:
// - setPriority (just stub rn)
public class Exam extends Assignment {
    private double timeLimit;
    private int numQuestions;
    private String questionType; //enumeration?
    private String location;

    public Exam(int assignmentId, String title, String type, int dueYear,
                int dueMonth, int dueDay, int dueHour, int dueMin, 
                double percentOfGrade, double timeLimit, int numQuestions, 
                String questionType, String location) {
        super(assignmentId, title, type, dueYear, dueMonth, dueDay, dueHour, dueMin, percentOfGrade);
        setTimeLimit(timeLimit);
        setNumQuestions(numQuestions);
        setQuestionType(questionType);
        setLocation(location);
    }

    public int calculatePriority() {
        return 0;
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
        + "\nLocation: " + getLocation() + "\nPriority: " + calculatePriority();
    }


}
