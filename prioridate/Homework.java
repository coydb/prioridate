package prioridate;
// TO-DO:
// - setPriority - do actual calc.
public class Homework extends Assignment {
    private int numQuestions;

    public Homework(int assignmentId, String title, String type, int dueYear, int dueMonth, int dueDay, int dueHour, int dueMin, double percentOfGrade, int numQuestions) {
        super(assignmentId, title, type, dueYear, dueMonth, dueDay, dueHour, dueMin, percentOfGrade);
        setNumQuestions(numQuestions);
    }

    public int calculatePriority() {
        return 0;
    }

    public void setNumQuestions(int numQuestions) {
        if(numQuestions > 0)
            this.numQuestions = numQuestions;
        else
            this.numQuestions = 1;  // default num questions
    }

    public int getNumQuestions() {
        return this.numQuestions;
    }

    public String toString() {
        return super.toString()
        + "\nNumber of Questions: " + getNumQuestions()
        + "\nPriority: " + calculatePriority();
    }
}
