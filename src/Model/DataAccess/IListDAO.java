package Model.DataAccess;


import Model.BusinessObject.List;

public interface IListDAO {

    //create
    void add(List list);

    //read
    java.util.List<List> getAll();
    List findById(int id);
    java.util.List<List>  findByIdUser(int idUser);

    //update
    void update(int id, String title, String description);

    //delete
    void deleteById(int id);
}
