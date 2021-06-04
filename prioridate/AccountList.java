package prioridate;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Stores all student and teacher accounts in array lists
 */
public class AccountList {
  private static AccountList accountList = null;
  private static ArrayList<Account> studentsList;
  private static ArrayList<Account> teachersList;

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
      if (student.getUserName().equalsIgnoreCase(username) && student.getPassword().equals(password))
        return student;
    }
    for (Teacher teacher : teachersList) {
      if (teacher.getUserName().equalsIgnoreCase(username) && teacher.getPassword().equals(password))
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
      studentsList.add(accountToAdd);
    if(accountToAdd.getType().equalsIgnoreCase("teacher")) 
      teachersList.add(accountToAdd);
  }

  /**
   * Returns an account from the course list 
   * @param username The username for the desired account
   * @return The matching account, returns null if the account
   * does not exist in the list
   */
  public Account getAccount(String username) {
    for (Student student : studentsList) {
      if (student.getUserName().equalsIgnoreCase(username))
        return student;
    }
    for (Teacher teacher : teachersList) {
      if (teacher.getUserName().equalsIgnoreCase(username))
        return teacher;
    }
    return null;
  }

  /**
   * Access the currently loaded list of students
   * @return An ArrayList of Students
   */
  public ArrayList<Account> getStudentList() {
    return studentsList;
  }

  /**
   * Access the currently loaded list of teachers
   * @return An ArrayList of Teachers
   */
  public ArrayList<Account> getTeacherList() {
    return teachersList;
  }
}
