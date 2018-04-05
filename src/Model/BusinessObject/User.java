package Model.BusinessObject;

/**
 * Created by previousdeveloper on 14.09.2015.
 */
public class User
{
    private int id;
    private String name;
    private String firstname;
    private String role;

    private String username;
    private String password;

    public User(String name, String firstname, String role, String username, String password) {
        this.name = name;
        this.firstname = firstname;
        this.role = role;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getRole() {
        return role;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", firstname='" + firstname + '\'' +
                ", role='" + role + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
