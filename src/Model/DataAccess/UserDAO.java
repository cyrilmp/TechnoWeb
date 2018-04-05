package Model.DataAccess;

import Controller.DatabaseConnection.Sql2oConnection;
import Model.BusinessObject.User;
import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sql2o.Connection;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;

public class UserDAO implements IUserDAO {

    final static Logger logger = LoggerFactory.getLogger(UserDAO.class);

    private Sql2oConnection sql2o;

    @Inject
    public UserDAO(Sql2oConnection connection) {
        this.sql2o = connection;
    }

    @Override
    public void add(User user) {
        String sql ="INSERT INTO user (name,firstname,role) VALUES (:name, :firstname, :role)";
        try(Connection con = sql2o.open()){
            int id=(int) con.createQuery(sql,true).bind(user).executeUpdate().getKey();
            user.setId(id);
        }catch (Exception ex){
            logger.error(ex.getMessage());
        }
    }

    @Override
    public List<User> getAll() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM user")
                    .executeAndFetch(User.class);
        }catch (Exception ex){
            logger.error(ex.getMessage());
            return new ArrayList<>();
        }
    }


    @Override
    public void update(int id, String name, String firstname, String role) {
        String sql = "UPDATE user SET (name, firstname, role) = (:name, :firstname, :role) WHERE id=:id";
        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("name", name)
                    .addParameter("firstname", firstname)
                    .addParameter("role", role)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Exception ex){
            logger.error(ex.getMessage());
        }
    }

    @Override
    public User findById(int id) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM user WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(User.class);
        }catch (Exception ex){
            logger.error(ex.getMessage());
            return null;
        }
    }


    @Override
    public void deleteById(int id) {
        String sql = "DELETE from user WHERE id = :id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Exception ex){
            logger.error(ex.getMessage());
        }
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        try(Connection con = sql2o.open())
        {
            return con.createQuery("SELECT * FROM user WHERE username = :username AND password = :password")
                    .addParameter("username", username)
                    .addParameter("password", password)
                    .executeAndFetchFirst(User.class);
        }catch (Exception ex){
            logger.error(ex.getMessage());
            return null;
        }
    }
}
