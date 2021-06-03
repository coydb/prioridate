package prioridate;

import java.util.ArrayList;
import java.util.HashMap;

public class AccountList {
  private static AccountList accountList;
  private ArrayList<Account> studentsList;
  private ArrayList<Account> teachersList;

  private AccountList() {

  }
  public AccountList getInstance() {
    return this;
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
