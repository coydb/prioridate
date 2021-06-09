package prioridate;
/**
 * A child class of Assignment specifically for Homework assignments
 * @author CSCE 247 Team Index 255
 */
public class Homework extends Assignment {
    private int numQuestions;
    /**
     * The constructor for a Homework assignment
     * @param assignmentId The ID number of the homework
     * @param title The title of the homework
     * @param type The type of homework
     * for date object
     * @param dueYear The year the homework is due
     * @param dueMonth The month the homework is due
     * @param dueDay The day the homework is due
     * @param dueHour The hour the homework is due
     * @param dueMin The min the homework is due
     * 
     * @param percentOfGrade The percentOfGrade the percentage of total grade the homework is worth
     * @param numQuestions The number of questions
     */
    public Homework(int assignmentId, String title, String type, int dueYear, int dueMonth, int dueDay, int dueHour, int dueMin, double percentOfGrade, int numQuestions) {
        super(assignmentId, title, type, dueYear, dueMonth, dueDay, dueHour, dueMin, percentOfGrade);
        setNumQuestions(numQuestions);
    }
    /**
     * Calls super to calculate base priority, then adds 5 points for the type of assignment 
     * and adds points based on the number of questions assigned
     * @return The int value for the priority
     */
    public int calculatePriority() {
        int priority = 0;
        priority += super.calculatePriority();
        priority += 5;  // type points
        int questions = getNumQuestions();
        if(questions <= 5) {
            priority += 1;
        }
        else if(questions > 5 && questions < 10) {
            priority += 3;
        }
        else if(questions >= 10) {
            priority += 7;
        }
        return priority;
    }
    /**
     * Mutator for the number of questions, if the num of questions is less than 0 it is set to the default of 1 question
     * @param numQuestions The number of questions
     */
    public void setNumQuestions(int numQuestions) {
        if(numQuestions > 0)
            this.numQuestions = numQuestions;
        else
            this.numQuestions = 1;  // default num questions
    }
    /**
     * Accessor for the number of questions
     * @return The number of questions
     */
    public int getNumQuestions() {
        return this.numQuestions;
    }
    /**
     * Converts the homework to a string
     * @return The string representation of the homework
     */
    public String toString() {
        return super.toString()
        + "\nNumber of Questions: " + getNumQuestions()
        + "\nPriority: " + priorityToString();
    }
}
