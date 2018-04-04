package DAO;

import Model.Element;
import Model.List;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

public class Sql2oListDAO implements ListDAO{


    private final Sql2o sql2o;

    public Sql2oListDAO(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(List list) {
        String sql ="INSERT INTO list (title,description) VALUES (:title, :description)";
        try(Connection con = sql2o.open()){
            int id=(int) con.createQuery(sql,true).bind(list).executeUpdate().getKey();
            list.setId(id);
        }catch (Sql2oException ex){
            System.out.println(ex);
            System.out.println("error message");
        }
    }

    @Override
    public java.util.List<List> getAll() {
        try(Connection con = sql2o.open()){
            java.util.List<List> lists = con.createQuery("SELECT * FROM list").executeAndFetch(List.class);
            for(int i=0;i<lists.size();i++){
                java.util.List<Element> elements = con.createQuery("SELECT * FROM element where idList="+lists.get(i).getId()).executeAndFetch(Element.class);
                lists.get(i).setElements(elements);
            }
            return lists;
        }
    }

    @Override
    public List findById(int id) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM list WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(List.class);
        }
    }

    @Override
    public java.util.List<List> findByIdUser(int idUser) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT l.* FROM (list l INNER JOIN listuser lu on l.id=lu.idList) INNER JOIN user u on lu.idUser=u.id WHERE u.id = :idUser")
                    .addParameter("idUser", idUser)
                    .executeAndFetch(List.class);
        }
    }

    @Override
    public void update(int id, String title, String description) {
        String sql = "UPDATE list SET (title, description) = (:title, :description) WHERE id=:id";
        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("title", title)
                    .addParameter("description", description)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
            System.out.println("error message");
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from listuser WHERE idlist = :id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
            System.out.println("error message");
        }
        sql = "DELETE from list WHERE id = :id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
            System.out.println("error message");
        }
    }
}
