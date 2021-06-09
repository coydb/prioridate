package prioridate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class TestDriver {
  static AccountList accountList = AccountList.getInstance();
  static CourseList courseList = CourseList.getInstance();
  static AssignmentList assignmentList = AssignmentList.getInstance();
  static Scanner keyboard = new Scanner(System.in);
  public static void main(String[] args) {

    while(true) {
      System.out.println("Please select an option below");
      System.out.println("[1] Display all students.");
      System.out.println("[2] Display all teachers.");
      System.out.println("[3] Display all courses.");
      System.out.println("[4] Display all assignments");
      System.out.println("[5] Create new assignment.");
      System.out.println("[6] Create new student.");
      System.out.println("[7] Create new teacher.");
      System.out.println("[8] Create new course.");
      System.out.println("[9] Enroll existing student in existing course.");
      int menuChoice = Integer.parseInt(keyboard.nextLine());
      switch(menuChoice) {
        case 1:
          displayAllCurrentStudents();
          break;
        case 2:
          displayAllCurrentTeachers();
          break;
        case 3:
          displayAllCurrentCourses();
          break;
        case 4:
          displayAllCurrentAssignments();
          break;
        case 5:
          createAndAddNewAssignment();
          break;
        case 6:
          createAndAddNewStudent();
          break;
        case 7:
          createAndAddNewTeacher();
          break;
        case 8:
          createAndAddNewCourse();
          break;
        case 9:
          enrollExistingStudentInExistingCourse();
          break;
        default:
          break;
      }
    }
    
  }

  public static void createAndAddNewAssignment() {
    System.out.println("Creating new assignment: ");
    int assignmentId = assignmentList.getHighestAssignmentId()+1;
    System.out.println("Enter a title for the assignment: ");
    String title = keyboard.nextLine();
    System.out.println("Enter the month the assignment is due (ex: 01, 09, 12)");
    int dueMonth = Integer.parseInt(keyboard.nextLine());
    System.out.println("Enter the day the assignment is due (ex: 01, 15, 31)");
    int dueDay = Integer.parseInt(keyboard.nextLine());
    System.out.println("Enter the year the assignment is due (ex: 2020)");
    int dueYear = Integer.parseInt(keyboard.nextLine());
    System.out.println("Enter the hour the assignment is due (ex: 01, 12)");
    int dueHour = Integer.parseInt(keyboard.nextLine());
    System.out.println("Enter the minute the assignment is due (ex: 01, 12)");
    int dueMin = Integer.parseInt(keyboard.nextLine());
    System.out.println("Enter the percentage of the grade (ex: 5.2");
    double percentOfGrade = Double.parseDouble(keyboard.nextLine());

    System.out.println("Choose a type for the assignment:\n" +
                        "[1] Homework\n" +
                        "[2] Quiz\n" +
                        "[3] Exam\n" +
                        "[4] Reading\n");
                        
    int choice = Integer.parseInt(keyboard.nextLine());
    String type = "";
    switch(choice) {
      case 1:
        type = "Homework";
        System.out.println("Enter the number of questions: ");
        int numQuestionsHW = Integer.parseInt(keyboard.nextLine());
        Homework homeworkToAdd = new Homework(assignmentId, title, type, dueYear, dueMonth, dueDay, dueHour, dueMin, percentOfGrade, numQuestionsHW);
        assignmentList.addAssignment(homeworkToAdd);
        break;
      case 2:
        type = "Quiz";
        System.out.println("Enter the number of questions: ");
        int numQuestionsQuiz = Integer.parseInt(keyboard.nextLine());
        System.out.println("Enter the time limit in minutes: ");
        double timeLimitQuiz = Double.parseDouble(keyboard.nextLine());
        Quiz quizToAdd = new Quiz(assignmentId, title, type, dueYear, dueMonth, dueDay, dueHour, dueMin, percentOfGrade, timeLimitQuiz, numQuestionsQuiz);
        assignmentList.addAssignment(quizToAdd);
        break;
      case 3:
        type = "Exam";
        System.out.println("Enter the number of questions: ");
        int numQuestionsExam = Integer.parseInt(keyboard.nextLine());
        System.out.println("Enter the time limit in minutes: ");
        double timeLimitExam = Double.parseDouble(keyboard.nextLine());
        System.out.println("Enter the question type (ex multiple choice, open response)");
        String questionType = keyboard.nextLine();
        System.out.println("Enter the location (ex Bldg 245, Rm 16)");
        String location = keyboard.nextLine();
        Exam examToAdd = new Exam(assignmentId, title, type, dueYear, dueMonth, dueDay, dueHour, dueMin, percentOfGrade, timeLimitExam, numQuestionsExam, questionType, location);
        assignmentList.addAssignment(examToAdd);
        break;
      case 4:
        type = "Reading";
        System.out.println("Please enter the number of chapters: ");
        int numChapters = Integer.parseInt(keyboard.nextLine());
        String[] chapters = new String[numChapters];
        for(int i = 0;i<numChapters;i++) {
          System.out.println("Chapter ["+(i+1)+"] of ["+numChapters+"]");
          System.out.println("Enter the chapter title: (ex Chapter 1 - Negative Effects of Stress on the Human Brain)");
          chapters[i] = keyboard.nextLine();
        }
        System.out.println("Please enter the number of pages: ");
        int numPages = Integer.parseInt(keyboard.nextLine());
        Reading readingToAdd = new Reading(assignmentId, title, type, dueYear, dueMonth, dueDay, dueHour, dueMin, percentOfGrade, chapters, numPages);
        assignmentList.addAssignment(readingToAdd);
        break;
      default:
        break;
    } 
    System.out.println("Finished creating new assignment.");
  }

  public static void createAndAddNewStudent() {
    System.out.println("Creating new student: ");
    ArrayList<Course> studentCourses = new ArrayList<Course>();
    HashMap<Assignment, Boolean> studentAssignments = new HashMap<Assignment, Boolean>();
    int studentId = accountList.getHighestUserId()+1;
    System.out.println("Enter the student's first and last name: ");
    String studentName = keyboard.nextLine();
    System.out.println("Enter the student's username: ");
    String username = keyboard.nextLine();
    System.out.println("Enter the student's password: ");
    String password = keyboard.nextLine();
    System.out.println("Enter number of courses to enroll student in: ");
    int numCourses = Integer.parseInt(keyboard.nextLine());
    for(int i = 1; i<=numCourses;i++) {
      System.out.println("Please select a course from the list below: ");
      System.out.println("Selection ["+i+"] of ["+numCourses+"]");
      displayAllCurrentCourses();
      int choice = Integer.parseInt(keyboard.nextLine());
      studentCourses.add(courseList.getCourses().get(choice-1));
      System.out.println("Enrolled in: "+courseList.getCourses().get(choice-1).getClassName());
    }
    for(int i = 0; i <studentCourses.size();i++) {
      Course currentCourse = studentCourses.get(i);
      for (int j = 0; j<currentCourse.getAssignments().size();j++) {
        Assignment currentAssignment = currentCourse.getAssignments().get(j);
        System.out.println("Adding assignment: "+currentAssignment.getTitle());
        studentAssignments.put(currentAssignment, false);
      }
    }
    accountList.addStudent(new Student(username, password, "Student", studentId, studentName, studentAssignments, studentCourses));
    System.out.println("Finished creating new student.");
  }
  public static void createAndAddNewTeacher() {
    System.out.println("Creating new teacher: ");
    ArrayList<Course> teacherCourses = new ArrayList<Course>();
    int teacherId = accountList.getHighestUserId()+1;
    System.out.println("Enter the teachers's first and last name: ");
    String teacherName = keyboard.nextLine();
    System.out.println("Enter the teachers's username: ");
    String username = keyboard.nextLine();
    System.out.println("Enter the teacher's password: ");
    String password = keyboard.nextLine();
    System.out.println("Enter number of new courses for the teacher: ");
    int numCourses = Integer.parseInt(keyboard.nextLine());
    for(int i = 1; i<=numCourses;i++) {
      System.out.println("Creating course ["+i+"] of ["+numCourses+"]");
      createAndAddNewCourse();
      teacherCourses.add(courseList.getCourse(courseList.getCourses().size()-1)); 
    }
    accountList.addTeacher(new Teacher(username, password, "Teacher", teacherId, teacherName, teacherCourses));
    System.out.println("Finished creating new teacher.");
  }

  
  public static void displayAllCurrentCourses() {
    System.out.println("All currently available courses: ");
    for(int i = 0; i<courseList.getCourses().size();i++) {
      System.out.println("["+(i+1)+"] "+courseList.getCourses().get(i).getClassName());
    }
  }

  public static void displayAllCurrentStudents() {
    System.out.println("All current students: ");
    for(int i = 0; i<accountList.getStudentList().size();i++) {
      System.out.println("["+(i+1)+"] "+accountList.getStudentList().get(i).getStudentName());
    }
  }

  public static void displayAllCurrentTeachers() {
    System.out.println("All current teachers: ");
    for(int i = 0; i<accountList.getTeacherList().size();i++) {
      System.out.println("["+(i+1)+"] "+accountList.getTeacherList().get(i).getTeacherName());
    }
  }

  public static void displayAllCurrentAssignments() {
    System.out.println("All current assignments: ");
    for (int i = 0; i<assignmentList.getAssignments().size();i++) {
      System.out.println("["+(i+1)+"] "+assignmentList.getAssignments().get(i).getTitle());
    }
  }

  public static void createAndAddNewCourse() {
    System.out.println("Creating new course: ");
    ArrayList<Assignment> assignments = new ArrayList<Assignment>();
    int courseId = courseList.getHighestCourseId()+1;
    System.out.println("Enter the course name: ");
    String courseName = keyboard.nextLine();
    System.out.println("Enter the number of assignments to create for the course: ");
    int numAssignments = Integer.parseInt(keyboard.nextLine());
    for (int i = 1; i<=numAssignments;i++) {
      System.out.println("Creating assignment ["+i+"] of ["+numAssignments+"]");
      createAndAddNewAssignment();
      Assignment lastAssignment = assignmentList.getAssignment(assignmentList.getHighestAssignmentId());
      int lastAssignmentId = lastAssignment.getAssignmentId();
      assignments.add(assignmentList.getAssignment(lastAssignmentId));
    }
    courseList.addCourse(new Course(courseId, courseName, assignments));
    System.out.println("Finished creating new course.");
  }

  public static void enrollExistingStudentInExistingCourse() {
    System.out.println("Enrolling an existing student: ");
    System.out.println("Please choose a student to enroll: ");
    displayAllCurrentStudents();
    int studentChoice = Integer.parseInt(keyboard.nextLine());
    Student studentToEnroll = accountList.getStudentList().get(studentChoice-1);
    System.out.println("Please choose the course to enroll the student in: ");
    displayAllCurrentCourses();
    int courseChoice = Integer.parseInt(keyboard.nextLine());
    Course courseToEnroll = courseList.getCourses().get(courseChoice-1);
    studentToEnroll.addCourse(courseToEnroll);
    accountList.addStudent(studentToEnroll);
    System.out.println("Enrolled "+studentToEnroll.getStudentName()+" in "+courseToEnroll.getClassName()+".");
    System.out.println("Finished enrolling student.");
  }
  
}
