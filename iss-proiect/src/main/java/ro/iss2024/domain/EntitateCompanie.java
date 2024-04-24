package ro.iss2024.domain;

public class EntitateCompanie {


    private String username;

    private String password;

    private RoleUser role;

    public EntitateCompanie() {
    }

    public EntitateCompanie( String username, String password, RoleUser role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleUser getRole() {
        return role;
    }

    public void setRole(RoleUser role) {
        this.role = role;
    }


}
