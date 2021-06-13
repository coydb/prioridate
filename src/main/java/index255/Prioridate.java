package index255;

import java.util.ArrayList;
import java.util.HashMap;
/**
 * A facade that holds useful methods for the UI to use
 * @author CSCE 247 Team Index 255
 */
public class Prioridate {
    private AccountList accountList;
    private CourseList courseList;
    private AssignmentList assignmentList;

    /**
     * Constructs the facade with the singletons
     */
    public Prioridate() {
        this.accountList = AccountList.getInstance();
        this.courseList = CourseList.getInstance();
        this.assignmentList = AssignmentList.getInstance();
    }
    /**
     * Logs in a user based on their username and password. Finds username
     * in the accountList and returns the account
     * @param username The username associated with the account
     * @param password The password associated with the account
     * @return The account attached to that username and password
     */
    public Account login(String username, String password) {
        return accountList.login(username, password);
    }
    /**
     * Adds a new student account to the account list
     * @param username The username for the student account
     * @param password The password for the student account
     * @param type The type of account (student/teacher)
     * @param studentId The ID number for the student
     * @param studentName The student's name
     * @param assignments The ArrayList of assignments for this student
     * @param courses The ArrayList of courses for this student
     */
    public void addStudent(String username, String password, String type, int studentId, String studentName, HashMap<Assignment, Boolean> assignments, ArrayList<Course> courses) {
        Student newStudent = new Student(username, password, type, studentId, studentName, assignments, courses);
        accountList.addStudent(newStudent);
    }
    /**
     * Adds a new teacher account to the account list
     * @param username The username for the teacher account
     * @param password The password for the teacher account
     * @param type They type of account (student/teacher)
     * @param teacherId The ID number for the teacher
     * @param teacherName The teacher's name
     * @param courses The ArrayList of courses for this teacher
     */
    public void addTeacher(String username, String password, String type, int teacherId, String teacherName, ArrayList<Course> courses) {
        Teacher newTeacher = new Teacher(username, password, type, teacherId, teacherName, courses);
        accountList.addTeacher(newTeacher);
    }
    // for students
    /**
     * Calls the getAssignmentsDue method from assignmentList to return the assignments that are due
     * @return An ArrayList of assignments
     */
    public ArrayList<Assignment> getAssignmentsDue() {
        return assignmentList.getAssignments();
    }

    // for teachers
    /**
     * Creates a new Exam assignment
     * @param assignmentId The ID number for the exam
     * @param title The title of the exam
     * @param type The type of assignment (exam)
     * The following are for constructing the dueDate object within the assignment
     * @param dueYear The year the exam is due
     * @param dueMonth The month the exam is due
     * @param dueDay The day the exam is due
     * @param dueHour The hour the exam is due
     * @param dueMin The minute the exam is due
     * 
     * @param percentOfGrade The percentage of the total grade the exam is worth
     * @param timeLimit The amount of time given for the exam
     * @param numQuestions The number of questions on the exam
     * @param questionType The type of questions on the exam (i.e. "multiple choice")
     * @param location The location where the exam is taking place
     * @return An Exam
     */
    public Exam createExam(int assignmentId, String title, String type, int dueYear,
                        int dueMonth, int dueDay, int dueHour, int dueMin, 
                        double percentOfGrade, double timeLimit, int numQuestions, 
                        String questionType, String location) {
        Exam newExam = new Exam(assignmentId, title, type, dueYear, dueMonth, dueDay, dueHour, dueMin, percentOfGrade, timeLimit, numQuestions, questionType, location);
        return newExam;
    }
    /**
     * Creates a new Quiz assignment
     * @param assignmentId The ID number for the quiz
     * @param title The title of the quiz
     * @param type The type of assignment (quiz)
     * for dueDate object
     * @param dueYear The year the quiz is due
     * @param dueMonth The month the quiz is due
     * @param dueDay The day the quiz is due
     * @param dueHour The hour the quiz is due
     * @param dueMin The minute the quiz is due
     * 
     * @param percentOfGrade The percentage of total grade that the quiz is worth
     * @param timeLimit The time allotted for the quiz
     * @param numQuestions The number of questions on the quiz
     * @return A Quiz
     */
    public Quiz createQuiz(int assignmentId, String title, String type, int dueYear, int dueMonth, int dueDay, int dueHour, int dueMin, double percentOfGrade, double timeLimit, int numQuestions) {
        Quiz newQuiz = new Quiz(assignmentId, title, type, dueYear, dueMonth, dueDay, dueHour, dueMin, percentOfGrade, timeLimit, numQuestions);
        return newQuiz;
    }
    /**
     * Creates a new Homework assignment
     * @param assignmentId The ID number for the homework
     * @param title The title of the homework
     * @param type The type of assignment (homework)
     * for date object
     * @param dueYear The year the homework is due
     * @param dueMonth The month the homework is due
     * @param dueDay The day the homework is due
     * @param dueHour The hour the homework is due
     * @param dueMin The minute the homework is due
     * 
     * @param percentOfGrade The percentage of total grade that the homework is worth
     * @param numQuestions The number of questions
     * @return A Homework
     */
    public Homework createHomework(int assignmentId, String title, String type, int dueYear, int dueMonth, int dueDay, int dueHour, int dueMin, double percentOfGrade, int numQuestions) {
        Homework newHomework = new Homework(assignmentId, title, type, dueYear, dueMonth, dueDay, dueHour, dueMin, percentOfGrade, numQuestions);
        return newHomework;
    }
    /**
     * Creates a new Reading assignment
     * @param assignmentId The ID number for the reading
     * @param title The title of the reading
     * @param type The type of assignment (reading)
     * for date object
     * @param dueYear The year the reading is due
     * @param dueMonth The month the reading is due
     * @param dueDay The day the reading is due
     * @param dueHour The hour the reading is due
     * @param dueMin The minute the reading is due
     * 
     * @param percentOfGrade The percentage of total grade that the reading is worth
     * @param chapters A string array of the chapters to read
     * @param numPages The number of pages to read
     * @return A Reading
     */
    public Reading createReading(int assignmentId, String title, String type, int dueYear, int dueMonth, int dueDay, int dueHour, int dueMin, double percentOfGrade, String[] chapters, int numPages) {
        Reading newReading = new Reading(assignmentId, title, type, dueYear, dueMonth, dueDay, dueHour, dueMin, percentOfGrade, chapters, numPages);
        return newReading;
    }
    /**
     * Takes an assignment that has been created and adds it to the assignmentList
     * @param assignment A created assignment
     */
    public void addAssignment(Assignment assignment) {
        assignmentList.addAssignment(assignment);
    }
    /**
     * Takes a course that has been created and adds it to the courseList
     * @param course A created course
     */
    public void addCourse(Course course) {
        courseList.addCourse(course);
    }
    /**
     * Creates a new course
     * @param courseId The ID number of the course
     * @param className The name of the class
     * @param assignments The ArrayList of assignments associated with this course
     * @return A Course
     */
    public Course createCourse(int courseId, String className, ArrayList<Assignment> assignments) {
        Course newCourse = new Course(courseId, className, assignments);
        return newCourse;
    }

}
