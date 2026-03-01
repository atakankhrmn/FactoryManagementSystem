public abstract class User {
    private PersonalInfo info;
    private String username;
    private String password;
    private String role;

    public User(String username, String password, String role, PersonalInfo info) {
        this.username = username;
        this.password = password;
        this.info = info;
        this.role = role;
    }

    public abstract void showGeneralInfo();

    public PersonalInfo getInfo() {
        return info;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
}
