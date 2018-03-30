package DAO;

import Model.Element;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.time.LocalDate;
import java.util.List;

public class Sql2oElementDAO implements ElementDAO{

    private final Sql2o sql2o;

    public Sql2oElementDAO(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Element element, int idList) {
        String sql ="INSERT INTO Element (title,description,tag,status,idList,creation_date,updating_date) VALUES (:title, :description,:tag,:status,:idList,:creation_date,:updating_date)";
        try(Connection con = sql2o.open()){
            int id=(int) con.createQuery(sql)
                    .addParameter("title", element.getTitle())
                    .addParameter("description", element.getDescription())
                    .addParameter("tag", element.getTag())
                    .addParameter("status", element.getStatus())
                    .addParameter("updating_date", LocalDate.now())
                    .addParameter("creation_date", LocalDate.now())
                    .addParameter("idList", idList)
                    .executeUpdate().getKey();
            element.setId(id);
        }catch (Sql2oException ex){
            System.out.println(ex);
            System.out.println("error message");
        }
    }

    @Override
    public List<Element> getAll() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM element")
                    .executeAndFetch(Element.class);
        }
    }

    @Override
    public Element findById(int id) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM element WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Element.class);
        }
    }

    @Override
    public List<Element> findByIdList(int idList) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM element WHERE idList = :idList")
                    .addParameter("idList", idList)
                    .executeAndFetch(Element.class);
        }
    }

    @Override
    public void update(int id, String title, String description, String tag, String status, LocalDate updating_date) {
        String sql = "UPDATE element SET (title, description,tag,status,updating_date) = (:title, :description,:tag,:status,:updating_date) WHERE id=:id";
        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("title", title)
                    .addParameter("description", description)
                    .addParameter("tag", tag)
                    .addParameter("status", status)
                    .addParameter("updating_date", LocalDate.now())
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
            System.out.println("error message");
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from element WHERE id = :id";
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
