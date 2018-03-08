package DAO;

import Model.Element;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

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
            int id=(int) con.createQuery(sql,true).bind(element).executeUpdate().getKey();
            element.setId(id);
        }catch (Sql2oException ex){
            System.out.println(ex);
            System.out.println("error message");
        }
    }

    @Override
    public List<Element> getAll() {
        return null;
    }

    @Override
    public Element findById(int id) {
        return null;
    }

    @Override
    public List<Element> findByIdList(int idList) {
        return null;
    }

    @Override
    public void update(int id, String title, String description) {

    }

    @Override
    public void deleteById(int id) {

    }
}
