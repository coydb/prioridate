package prioridate;
// TO-DO:
// - setPriority calculation
public class Reading extends Assignment {
    private String[] chapters;
    private int numPages;

    public Reading(int assignmentId, String title, String type, int dueYear, int dueMonth, int dueDay, int dueHour, int dueMin, double percentOfGrade, String[] chapters, int numPages) {
        super(assignmentId, title, type, dueYear, dueMonth, dueDay, dueHour, dueMin, percentOfGrade);
        setChapters(chapters);
        setNumPages(numPages);
    }

    public int calculatePriority() {
        return 0;
    }

    public void setChapters(String[] chapters) {
        this.chapters = chapters;
    }

    public String[] getChapters() {
        return this.chapters;
    }

    public void setNumPages(int numPages) {
        if(numPages > 0)
            this.numPages = numPages;
        else
            this.numPages = 1;  // default
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
                     +"\nPriority: " + calculatePriority();
        return outString;
    }
}
