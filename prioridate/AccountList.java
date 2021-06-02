package prioridate;

import java.util.HashMap;

public class AccountList {
  private static AccountList accountList;
  private HashMap<String, Account> students;
  private HashMap<String, Account> teachers;

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
