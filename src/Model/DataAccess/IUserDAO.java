package Model.DataAccess;

import Model.BusinessObject.User;

import java.util.List;

public interface IUserDAO {

    //create
    void add(User user);

    //read
    List<User> getAll();

    User findById(int id);

    //update
    void update(int id, String name, String firstname, String role);

    //delete
    void deleteById(int id);

    User findByUsernameAndPassword(String username, String password);



}
