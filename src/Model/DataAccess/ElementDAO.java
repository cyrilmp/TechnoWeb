package Model.DataAccess;

import Model.BusinessObject.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sql2o.Connection;
import Controller.DatabaseConnection.Sql2oConnection;
import org.sql2o.Sql2oException;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ElementDAO implements IElementDAO {

    final static Logger logger = LoggerFactory.getLogger(ElementDAO.class);

    private final Sql2oConnection sql2o;

    public ElementDAO(Sql2oConnection sql2o) {
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
        }catch (Exception ex){
            logger.error(ex.getMessage());
        }
    }

    @Override
    public List<Element> getAll() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM element")
                    .executeAndFetch(Element.class);
        }
        catch (Exception ex){
            logger.error(ex.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public Element findById(int id) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM element WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Element.class);
        }
        catch (Exception ex){
            logger.error(ex.getMessage());
            return null;
        }
    }

    @Override
    public List<Element> findByIdList(int idList) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM element WHERE idList = :idList")
                    .addParameter("idList", idList)
                    .executeAndFetch(Element.class);
        }
        catch (Exception ex){
            logger.error(ex.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public void update(int id, String title, String description, String tag, String status, Date updating_date, int idList) {
        String sql = "UPDATE element SET (title, description,tag,status,updating_date,idList) = (:title, :description,:tag,:status,:updating_date,:idList) WHERE id=:id";
        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("title", title)
                    .addParameter("description", description)
                    .addParameter("tag", tag)
                    .addParameter("status", status)
                    .addParameter("updating_date", Date.valueOf(LocalDate.now()))
                    .addParameter("id", id)
                    .addParameter("idList", idList)
                    .executeUpdate();
        } catch (Exception ex){
            logger.error(ex.getMessage());
        }
    }

    @Override
    public void update(int id, String status, Date updating_date) {
        String sql = "UPDATE element SET (status,updating_date) = (:status,:updating_date) WHERE id=:id";
        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("status", status)
                    .addParameter("updating_date", Date.valueOf(LocalDate.now()))
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Exception ex){
            logger.error(ex.getMessage());
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from element WHERE id = :id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Exception ex){
            logger.error(ex.getMessage());
        }
    }
}
