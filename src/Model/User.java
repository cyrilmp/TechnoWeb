package Model;

import Model.Role;

public class User {
    private int id;
    private String Name;
    private String Firstname;
    private Role role;


    public User(){

    }

    public User(String name, String firstname, Role role) {
        Name = name;
        Firstname = firstname;
        this.role = role;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getFirstname() {
        return Firstname;
    }

    public void setFirstname(String firstname) {
        Firstname = firstname;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setId(int id) {
        this.id = id;
    }
}
