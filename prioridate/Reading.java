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
        String outString = super.toString() + "\nChapters: ";
        String[] chapters = getChapters();
        for (int i = 0; i < chapters.length;i++) {
            outString += chapters[i];
            outString += (i == chapters.length-1) ? "" : ", ";
        }
        outString += "\nNumber of Pages: " + getNumPages()
                     +"\nPriority: " + getPriority();
        return outString;
    }
}
