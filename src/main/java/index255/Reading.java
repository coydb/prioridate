package index255;
/**
 * A child class of Assignment, this is a specific assignment for Reading
 * @author CSCE 247 Team Index 255
 */
public class Reading extends Assignment {
    private String[] chapters;
    private int numPages;
    /**
     * Constructor for the Reading
     * @param assignmentId The ID number of the Reading
     * @param title The title of the Reading
     * @param type The type of assignment ("Reading")
     * for date object
     * @param dueYear The year the Reading is due
     * @param dueMonth The month the Reading is due
     * @param dueDay The day the Reading is due
     * @param dueHour The hour the Reading is due
     * @param dueMin The min the Reading is due
     * 
     * @param percentOfGrade The percentOfGrade the percentage of total grade the Reading is worth
     * @param chapters The chapters assigned
     * @param numPages The number of pages assigned
     */
    public Reading(int assignmentId, String title, String type, int dueYear, int dueMonth, int dueDay, int dueHour, int dueMin, double percentOfGrade, String[] chapters, int numPages) {
        super(assignmentId, title, type, dueYear, dueMonth, dueDay, dueHour, dueMin, percentOfGrade);
        setChapters(chapters);
        setNumPages(numPages);
    }
    /**
     * Initializes priority as 0, then calls super.calculatePriority() to add the points based on the due date and the percentage,
     * then more points are added based on the number of pages assigned
     * @return The calculated int value of priority
     */
    public int calculatePriority() {
        int priority = 0;
        priority += super.calculatePriority();
        int pages = getNumPages();
        if(pages <= 5) {
            priority += 1;
        }
        else if(pages > 5 && pages < 10) {
            priority += 3;
        }
        else if(pages >= 10) {
            priority += 7;
        }
        return priority;
    }
    /**
     * Mutator for the chapters assigned
     * @param chapters The chapters array being set
     */
    public void setChapters(String[] chapters) {
        this.chapters = chapters;
    }
    /**
     * Accessor for the chapters
     * @return The string array of chapters
     */
    public String[] getChapters() {
        return this.chapters;
    }
    /**
     * Mutator for the number of pages, checks to make sure it is > 0 pages, if not then is set to default of 1 page
     * @param numPages The number of pages being set
     */
    public void setNumPages(int numPages) {
        if(numPages > 0)
            this.numPages = numPages;
        else
            this.numPages = 1;  // default
    }
    /**
     * Accessor for the number of pages
     * @return The number of pages
     */
    public int getNumPages() {
        return this.numPages;
    }
    /**
     * Converts the reading assignment to a string
     * @return A string representation of the reading
     */
    public String toString() {
        String outString = super.toString() + "\nChapters: ";
        String[] chapters = getChapters();
        for (int i = 0; i < chapters.length;i++) {
            outString += chapters[i];
            outString += (i == chapters.length-1) ? "" : ", ";
        }
        outString += "\nNumber of Pages: " + getNumPages()
                     +"\nPriority: " + priorityToString();
        return outString;
    }
}
