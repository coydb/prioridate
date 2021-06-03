package prioridate;
public abstract class Account {
    private String type;
    private String username;
    private String password;

    public Account(String type, String username, String password) {
        this.type = type;
        this.username = username;
        this.password = password;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }
}
