package prioridate;

public class Quiz extends Assignment {
    private double timeLimit;
    private int numQuestions;

    //TESTING
    public static void main(String[] args) {
        Quiz TEST = new Quiz(1, "Test Quiz", "Quiz", 2021, 8, 25, 14, 20, 30.0, 2.0, 5);
        System.out.println(TEST.toString());
    }

    public Quiz(int assignmentId, String title, String type, int dueYear, int dueMonth, int dueDay, int dueHour, int dueMin, double percentOfGrade, double timeLimit, int numQuestions) {
        super(assignmentId, title, type, dueYear, dueMonth, dueDay, dueHour, dueMin, percentOfGrade);
        setTimeLimit(timeLimit);
        setNumQuestions(numQuestions);
    }

    public int calculatePriority() {
        int priority = 0;
        priority += super.calculatePriority();
        priority += 10;  // points for type
        return priority;
    }

    public void setTimeLimit(double timeLimit) {
        if(timeLimit > 0.0)
            this.timeLimit = timeLimit;
        else
            this.timeLimit = 1.0;  // default time limit
    }

    public double getTimeLimit() {
        return this.timeLimit;
    }

    public void setNumQuestions(int numQuestions) {
        if(numQuestions > 0)
            this.numQuestions = numQuestions;
        else
            this.numQuestions = 1;  // default
    }

    public int getNumQuestions() {
        return this.numQuestions;
    }

    public String toString(){
        return super.toString()
        + "\nTime Limit: " + getTimeLimit() + "\nNumber of Questions: "
        + getNumQuestions() + "\nPriority: " + calculatePriority() + priorityToString();
    }
}
