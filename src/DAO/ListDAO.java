package DAO;


import Model.List;

public interface ListDAO {

    //create
    void add(Model.List list);

    //read
    java.util.List<Model.List> getAll();
    Model.List findById(int id);
    java.util.List<Model.List>  findByIdUser(int idUser);

    //update
    void update(int id, String title, String description);

    //delete
    void deleteById(int id);
}
