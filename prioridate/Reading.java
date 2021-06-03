package prioridate;
// TO-DO:
// - flesh out setters with checks
// - setPriority calculation
public class Reading extends Assignment {
    private String[] chapters;
    private int numPages;

    public Reading(int assignmentId, String title, String type, String dueDate, String dueTime, double percentOfGrade, String[] chapters, int numPages) {
        super(assignmentId, title, type, dueDate, dueTime, percentOfGrade);
        setChapters(chapters);
        setNumPages(numPages);
        setPriority();
    }

    public void setPriority() {
        this.priority = 0;
    }

    public int getPriority() {
        return this.priority;
    }

    public void setChapters(String[] chapters) {
        this.chapters = chapters;
    }

    public String[] getChapters() {
        return this.chapters;
    }

    public void setNumPages(int numPages) {
        this.numPages = numPages;
    }

    public int getNumPages() {
        return this.numPages;
    }

    public String toString() {
        return super.toString()
        + "\nChapters: " + getChapters() + "\nNumber of Pages: "
        + getNumPages() + "\nPriority: " + getPriority();
    }
}
