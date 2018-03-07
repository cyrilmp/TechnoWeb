package DAO;

import Model.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oUserDAO implements UserDAO{

    private final Sql2o sql2o;

    public Sql2oUserDAO(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(User user) {
        String sql ="INSERT INTO user (name,firstname,role) VALUES (:name, :firstname, :role)";
        try(Connection con = sql2o.open()){
            int id=(int) con.createQuery(sql,true).bind(user).executeUpdate().getKey();
            user.setId(id);
        }catch (Sql2oException ex){
            System.out.println(ex);
            System.out.println("error message");
        }
    }

    @Override
    public List<User> getAll() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM user")
                    .executeAndFetch(User.class);
        }
    }

    @Override
    public void update(int id, String name, String firstname, String role) {

    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from user WHERE id = :id";
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
