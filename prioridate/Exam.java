package prioridate;
/**
 * A child class of Assignment specifically for an Exam
 * @author CSCE 247 Team Index 255
 */
public class Exam extends Assignment {
    private double timeLimit;
    private int numQuestions;
    private String questionType;
    private String location;
    /**
     * Constructor for the Exam
     * @param assignmentId The ID number of the exam
     * @param title The title of the exam
     * @param type The type of exam
     * for date object
     * @param dueYear The year the exam is due
     * @param dueMonth The month the exam is due
     * @param dueDay The day the exam is due
     * @param dueHour The hour the exam is due
     * @param dueMin The min the exam is due
     * 
     * @param percentOfGrade The percentOfGrade the percentage of total grade the exam is worth
     * @param timeLimit The time allotted for the exam
     * @param numQuestions The number of questions on the exam
     * @param questionType The type of questions on the exam
     * @param location The location where the exam is taking place
     */
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
    /**
     * Calls super to get the base priority and adds points based on the type of assignment
     */
    public int calculatePriority() {
        int priority = 0;
        priority += super.calculatePriority();
        priority += 15;  // type points
        return priority;
    }
    /**
     * Accessor for the time limit
     * @return The time limit
     */
    public double getTimeLimit() {
        return this.timeLimit;
    }
    /**
     * Mutator for the time limit, makes sure it is greater than 0.0, otherwise it is set to the default of 1.0
     * @param timeLimit The time limit to be set
     */
    public void setTimeLimit(double timeLimit) {
        if(timeLimit > 0.0)
            this.timeLimit = timeLimit;
        else
            this.timeLimit = 1.0;  // default time limit of 1 hour
    }
    /**
     * Accessor for the number of questions
     * @return The number of questions
     */
    public int getNumQuestions() {
        return this.numQuestions;
    }
    /**
     * Mutator for the number of questions, makes sure it is > 0, if not is set to default of 1
     * @param numQuestions The number of questions
     */
    public void setNumQuestions(int numQuestions) {
        if(numQuestions > 0)
            this.numQuestions = numQuestions;
        else
            this.numQuestions = 1;  // default num questions
    }
    /**
     * Accessor for the type of questions
     * @return The type of questions
     */
    public String getQuestionType() {
        return this.questionType;
    }
    /**
     * Mutator for the type of question, if no type is passed in it is set to "multiple choice" as default
     */
    public void setQuestionType(String questionType) {
        if(questionType != null)
            this.questionType = questionType;
        else
            this.questionType = "Multiple Choice";  // default if left empty
    }
    /**
     * Accessor for location
     * @return The location
     */
    public String getLocation() {
        return this.location;
    }
    /**
     * Mutator for the location, if none passed in it is set to "classroom" as default
     * @param location The location to be set
     */
    public void setLocation(String location) {
        if(location != null)
            this.location = location;
        else
            this.location = "Classroom";  // default if empty
    }
    /**
     * Converts the exam to a string
     * @return The string representation of the exam
     */
    public String toString() {
        return super.toString()
        + "\nTime Limit: " + getTimeLimit() + "\nNumber of Questions: "
        + getNumQuestions() + "\nQuestion Type: " + getQuestionType()
        + "\nLocation: " + getLocation() + "\nPriority: " + priorityToString();
    }


}
