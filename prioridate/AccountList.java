package prioridate;

import java.util.ArrayList;

/**
 * Stores all student and teacher accounts in array lists
 */
public class AccountList {
  private static AccountList accountList = null;
  private static ArrayList<Student> studentsList;
  private static ArrayList<Teacher> teachersList;

  /**
   * For testing purposes only
   * @param args
   */
  public static void main(String[] args) {
    AccountList accountList = AccountList.getInstance();
    accountList.printAccountList();
  }

  /**
   * Private constructor (user AccountList.getInstance() for instantiation)
   */
  private AccountList() {
    studentsList = DataLoader.getStudents();
    teachersList = DataLoader.getTeachers();
  }

  /**
   * Returns the current instance of AccountList if one exists,
   * or creates and returns an instance of AccountList if one doesn't exist
   * @return The current instance of AccountList
   */
  public static AccountList getInstance() {
    if(accountList == null) {
      accountList = new AccountList();
    }
    return accountList;
  }

  /**
   * Returns the account with a matching username and password
   * @param username 
   * @param password
   * @return Returns the matching account on a successful login,
   * returns null if the login fails (no match for username/password)
   */
  public Account login(String username, String password) {
    for (Student student : studentsList) {
      if (student.getUsername().equalsIgnoreCase(username) && student.getPassword().equals(password))
        return student;
    }
    for (Teacher teacher : teachersList) {
      if (teacher.getUsername().equalsIgnoreCase(username) && teacher.getPassword().equals(password))
        return teacher;
    }
    return null;
  }

  /**
   * Adds an account to the current list of accounts
   * @param accountToAdd
   */
  public void addAccount(Account accountToAdd) {
    if(accountToAdd.getType().equalsIgnoreCase("student"))
      studentsList.add((Student)accountToAdd);
    if(accountToAdd.getType().equalsIgnoreCase("teacher")) 
      teachersList.add((Teacher)accountToAdd);
  }

  /**
   * Returns an account from the course list 
   * @param accountId The id for the desired account
   * @return The matching account, returns null if the account
   * does not exist in the list
   */
  public Account getAccount(int accountId) {
    for (Student student : studentsList) {
      if (student.getStudentId() == accountId)
        return student;
    }
    for (Teacher teacher : teachersList) {
      if (teacher.getTeacherId() == accountId)
        return teacher;
    }
    return null;
  }

  /**
   * Access the currently loaded list of students
   * @return An ArrayList of Students
   */
  public ArrayList<Student> getStudentList() {
    return studentsList;
  }

  /**
   * Access the currently loaded list of teachers
   * @return An ArrayList of Teachers
   */
  public ArrayList<Teacher> getTeacherList() {
    return teachersList;
  }

  /**
   * Helper method for testing.
   */
  private void printAccountList() {
    for (int i = 0; i< studentsList.size();i++) {
      System.out.println("==============Student #" + (i + 1) + "=============");
      System.out.println(studentsList.get(i).toString());
    }
    for (int i = 0; i< teachersList.size();i++) {
      System.out.println("==============Teacher #" + (i + 1) + "=============");
      System.out.println(teachersList.get(i).toString());
    }
  }
}
