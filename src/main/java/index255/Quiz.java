package index255;
/**
 * A child class of Assignment specifically for a Quiz
 * @author CSCE 247 Team Index 255
 */
public class Quiz extends Assignment {
    private double timeLimit;
    private int numQuestions;
    /**
     * The constructor for a Quiz
     * @param assignmentId The ID number of the quiz
     * @param title The title of the quiz
     * @param type The type of quiz
     * for date object
     * @param dueYear The year the quiz is due
     * @param dueMonth The month the quiz is due
     * @param dueDay The day the quiz is due
     * @param dueHour The hour the quiz is due
     * @param dueMin The min the quiz is due
     * 
     * @param percentOfGrade The percentOfGrade the percentage of total grade the quiz is worth
     * @param timeLimit The time allotted for the quiz
     * @param numQuestions The number of questions on the quiz
     */
    public Quiz(int assignmentId, String title, String type, int dueYear, int dueMonth, int dueDay, int dueHour, int dueMin, double percentOfGrade, double timeLimit, int numQuestions) {
        super(assignmentId, title, type, dueYear, dueMonth, dueDay, dueHour, dueMin, percentOfGrade);
        setTimeLimit(timeLimit);
        setNumQuestions(numQuestions);
    }
    /**
     * Calls super to get base priority and then adds points for the type of assignment
     * @return The calculated int value of priority
     */
    public int calculatePriority() {
        int priority = 0;
        priority += super.calculatePriority();
        priority += 10;  // points for type
        return priority;
    }
    /**
     * Mutator for the time limit, if given is <= 0.0 it will be set to 1.0 as default
     * @param timeLimit The time limit being set
     */
    public void setTimeLimit(double timeLimit) {
        if(timeLimit > 0.0)
            this.timeLimit = timeLimit;
        else
            this.timeLimit = 1.0;  // default time limit
    }
    /**
     * Accessor for the time limit
     * @return The time limit
     */
    public double getTimeLimit() {
        return this.timeLimit;
    }
    /**
     * Mutator for the number of questions, if given is <= 0 it is set to 1 question as default
     * @param numQuestions
     */
    public void setNumQuestions(int numQuestions) {
        if(numQuestions > 0)
            this.numQuestions = numQuestions;
        else
            this.numQuestions = 1;  // default
    }
    /**
     * Accessor for the number of questions
     * @return The number of questions
     */
    public int getNumQuestions() {
        return this.numQuestions;
    }
    /**
     * Converts the quiz to a string
     * @return A string representation of the quiz
     */
    public String toString() {
        return super.toString()
        + "\nTime Limit: " + getTimeLimit() + "\nNumber of Questions: "
        + getNumQuestions() + "\nPriority: " + priorityToString();
    }
}
