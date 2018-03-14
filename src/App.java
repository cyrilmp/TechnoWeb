import DAO.*;
import Model.Element;
import Model.List;
import com.google.gson.Gson;
import Model.User;
import freemarker.template.TemplateException;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.io.IOException;
import java.util.ArrayList;

import static spark.Spark.*;

public class App {

    private static User userConnected;

    public static void main(String[] args) throws IOException, TemplateException {
        staticFileLocation("/public");
        webservices();
        views();
    }

    public static void webservices(){
        UserDAO userDAO;
        ListDAO listDAO;
        ElementDAO elementDAO;

        Gson gson = new Gson();
        Connection conn;
        String connectionString = "jdbc:h2:~/test.db;";
        //String connectionString = "jdbc:h2:~/test.db;INIT=RUNSCRIPT from 'classpath:script.sql';";
        Sql2o sql2o = new Sql2o(connectionString, "admin", "admin");
        conn = sql2o.open();
        userDAO = new Sql2oUserDAO(sql2o);
        listDAO = new Sql2oListDAO(sql2o);
        elementDAO = new Sql2oElementDAO(sql2o);

        get("/users", (req, res) -> gson.toJson(userDAO.getAll()));
        get("/lists", (req, res) -> gson.toJson(listDAO.getAll()));
        get("/elements", (req, res) -> gson.toJson(elementDAO.getAll()));
        get("/user/:id", (req, res) -> {
            res.type("application/json");
            int userId = Integer.parseInt(req.params("id"));
            res.type("application/json");
            return gson.toJson(userDAO.findById(userId));
        });
        get("/list/:id", (req, res) -> {
            res.type("application/json");
            int listId = Integer.parseInt(req.params("id"));
            res.type("application/json");
            return gson.toJson(listDAO.findById(listId));
        });
        get("/list/user/:id", (req, res) -> {
            res.type("application/json");
            int userId = Integer.parseInt(req.params("id"));
            res.type("application/json");
            return gson.toJson(listDAO.findByIdUser(userId));
        });

        post("/user/add", "application/json", (req, res) -> {
            String name = req.queryParams("name");
            String firstname = req.queryParams("firstname");
            String role = req.queryParams("role");
            User user = new User(name,firstname,role);
            userDAO.add(user);
            res.status(201);
            res.type("application/json");
            return gson.toJson(user);
        });
        post("/signIn", "application/json", (req, res) -> {
            String name = req.queryParams("name");
            String firstname = req.queryParams("firstname");
            userConnected = userDAO.findByNameAndFirstName(name,firstname);
            if(userConnected!=null){
                res.status(200);
                res.type("application/json");
                return gson.toJson(userConnected);
            }else{
                res.status(401);
                res.type("application/json");
                return gson.toJson(userConnected);
            }
        });
        post("/list/add", "application/json", (req, res) -> {
            String title = req.queryParams("title");
            String description = req.queryParams("description");
            //ArrayList<Element> elementList= req.queryParams("element");
            List list = new List(title,description);
            list.getUsers().add(new User("admin","admin","Admin"));
            /*for(int i=0;i<elementList;i++){

            }*/
            listDAO.add(list);
            res.status(201);
            res.type("application/json");
            return gson.toJson(list);
        });
    }

    public static void views(){
        Views views= new Views();
        get("/index", (req, res) -> views.index());
        get("/newuser", (req, res) -> views.user());
        get("/list-users", (req, res) -> views.list_users());
        get("/newlist", (req, res) -> views.newlist());
        get("/sign-in", (req, res) -> views.signIn());

    }

}
