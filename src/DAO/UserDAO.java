package DAO;

import Model.User;

import java.util.List;

public interface UserDAO {

    //create
    void add(User user);

    //read
    List<User> getAll();

    //update
    void update(int id, String name, String firstname,String role);

    //delete
    void deleteById(int id);

}
