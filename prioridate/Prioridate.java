package prioridate;

import java.util.ArrayList;
import java.util.HashMap;

public class Prioridate {
    static AccountList accountList = AccountList.getInstance();
    static CourseList courseList = CourseList.getInstance();
    static AssignmentList assignmentList = AssignmentList.getInstance();;

    public Prioridate() {
    
    }

    public Account login(String username, String password) {
        return accountList.login(username, password);
    }

    public void addStudent(String username, String password, String type, int studentId, String studentName, HashMap<Assignment, Boolean> assignments, ArrayList<Courses> courses) {
        Student newStudent = new Student(username, password, type, studentId, studentName, assignments, courses);
        accountList.addStudent(newStudent);
    }

    public void addTeacher(String username, String password, String type, int teacherId, String teacherName, ArrayList<Course> courses) {
        Teacher newTeacher = new Teacher(username, password, type, teacherId, teacherName, courses);
        accountList.addTeacher(newTeacher);
    }
    // for students
    public void viewAssignmentsCompleted(ArrayList<Assignment> assignmentsCompleted) {
        
    }

    public void viewAssignmentsDue(ArrayList<Assignment> assignmentsDue) {

    }

    public void checkOffAssignment(ArrayList<Assignment> assignmentsDue) {

    }
    // for teachers
    public void viewStudents(Course course) {

    }

    public void viewCourses() {

    }

    public void checkStudentProgress(String name) {

    }

    public Exam createExam(int assignmentId, String title, String type, int dueYear,
                        int dueMonth, int dueDay, int dueHour, int dueMin, 
                        double percentOfGrade, double timeLimit, int numQuestions, 
                        String questionType, String location) {
        Exam newExam = new Exam(assignmentId, title, type, dueYear, dueMonth, dueDay, dueHour, dueMin, percentOfGrade, timeLimit, numQuestions, questionType, location);
        return newExam;
    }

    public Quiz createQuiz(int assignmentId, String title, String type, int dueYear, int dueMonth, int dueDay, int dueHour, int dueMin, double percentOfGrade, double timeLimit, int numQuestions) {
        Quiz newQuiz = new Quiz(assignmentId, title, type, dueYear, dueMonth, dueDay, dueHour, dueMin, percentOfGrade, timeLimit, numQuestions);
        return newQuiz;
    }

    public Homework createHomework(int assignmentId, String title, String type, int dueYear, int dueMonth, int dueDay, int dueHour, int dueMin, double percentOfGrade, int numQuestions) {
        Homework newHomework = new Homework(assignmentId, title, type, dueYear, dueMonth, dueDay, dueHour, dueMin, percentOfGrade, numQuestions);
        return newHomework;
    }

    public Reading createReading(int assignmentId, String title, String type, int dueYear, int dueMonth, int dueDay, int dueHour, int dueMin, double percentOfGrade, String[] chapters, int numPages) {
        Reading newReading = new Reading(assignmentId, title, type, dueYear, dueMonth, dueDay, dueHour, dueMin, percentOfGrade, chapters, numPages);
        return newReading;
    }

    public void addAssignment(Assignment assignment) {
        assignmentList.addAssignment(assignment);
    }

    public void addCourse(Course course) {

    }

}
