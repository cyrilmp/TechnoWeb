package Model.DataAccess;

import Controller.ViewModel.ViewController;
import Model.BusinessObject.Element;
import Model.BusinessObject.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sql2o.Connection;
import Controller.DatabaseConnection.Sql2oConnection;
import org.sql2o.Sql2oException;

import java.util.ArrayList;

public class ListDAO implements IListDAO {

    final static Logger logger = LoggerFactory.getLogger(ListDAO.class);

    private final Sql2oConnection sql2o;

    public ListDAO(Sql2oConnection sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(List list) {
        String sql ="INSERT INTO list (title,description) VALUES (:title, :description)";
        try(Connection con = sql2o.open()){
            int id=(int) con.createQuery(sql,true).bind(list).executeUpdate().getKey();
            list.setId(id);
        }catch (Sql2oException ex){
            logger.error(ex.getMessage());
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
        }catch (Exception e){
            logger.error(e.getMessage());
            return new ArrayList<List>();
        }
    }

    @Override
    public List findById(int id) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM list WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(List.class);
        }
        catch (Exception e){
            logger.error(e.getMessage());
            return null;
        }

    }

    @Override
    public java.util.List<List> findByIdUser(int idUser) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT l.* FROM (list l INNER JOIN listuser lu on l.id=lu.idList) INNER JOIN user u on lu.idUser=u.id WHERE u.id = :idUser")
                    .addParameter("idUser", idUser)
                    .executeAndFetch(List.class);
        }
        catch (Exception e){
            logger.error(e.getMessage());
            return new java.util.ArrayList<List>();
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
        } catch (Exception e){
            logger.error(e.getMessage());
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from listuser WHERE idlist = :id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Exception e){
            logger.error(e.getMessage());
        }
        sql = "DELETE from list WHERE id = :id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Exception e){
            logger.error(e.getMessage());
        }
    }
}
