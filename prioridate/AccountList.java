package prioridate;

import java.util.ArrayList;
import java.util.HashMap;

public class AccountList {
  private static AccountList accountList = null;
  private static ArrayList<Account> studentsList;
  private static ArrayList<Account> teachersList;

  private AccountList() {
    studentsList = DataLoader.getStudents();
    teachersList = DataLoader.getTeachers();
  }
  public AccountList getInstance() {
    if(accountList == null) {
      accountList = new AccountList();
    }
    return accountList;
  }
  public Account login(String username, String password) {
    // default behavior to allow compilation
    return new Account();
  }
  public void addAccount(String type, String username, String password) {
    
  }
  public Account getAccount(String username) {
    // default behavior to allow compilation
    return new Account();
  }
    
}
