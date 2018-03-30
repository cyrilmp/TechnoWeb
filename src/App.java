import DAO.*;
import Model.Element;
import Model.List;
import com.google.gson.Gson;
import Model.User;
import freemarker.template.TemplateException;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import spark.Session;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import static spark.Spark.*;

public class App {

    public static User userConnected;

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
        //String connectionString = "jdbc:h2:~/test.db;";
        String connectionString = "jdbc:h2:~/test.db;INIT=RUNSCRIPT from 'classpath:script.sql';";
        Sql2o sql2o = new Sql2o(connectionString, "admin", "admin");
        conn = sql2o.open();
        userDAO = new Sql2oUserDAO(sql2o);
        listDAO = new Sql2oListDAO(sql2o);
        elementDAO = new Sql2oElementDAO(sql2o);
        ////////Acces liste
        get("/users", (req, res) -> gson.toJson(userDAO.getAll()));
        get("/lists", (req, res) -> gson.toJson(listDAO.getAll()));
        get("/elements", (req, res) -> gson.toJson(elementDAO.getAll()));
        /////// Acces une ressources
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
        get("/elements/:id", (req, res) -> {
            res.type("application/json");
            int elementId = Integer.parseInt(req.params("id"));
            res.type("application/json");
            return gson.toJson(elementDAO.findById(elementId));
        });
        ////////Modification
        put("/user/modify","application/json", (req, res) -> {
            int id = Integer.parseInt(req.queryParams("id"));
            String name = req.queryParams("name");
            String firstname = req.queryParams("firstname");
            String role = req.queryParams("role");
            userDAO.update(id,name,firstname,role);
            return gson.toJson(userDAO.findById(id));
        });
        put("/element/modify","application/json", (req, res) -> {
            int id = Integer.parseInt(req.queryParams("id"));
            String title = req.queryParams("title");
            String description = req.queryParams("description");
            String tag = req.queryParams("tag");
            String status = req.queryParams("status");
            LocalDate updating_date = LocalDate.now();
            elementDAO.update(id,title,description,tag,status,updating_date);
            return gson.toJson(elementDAO.findById(id));
        });
        put("/list/modify","application/json", (req, res) -> {
            int id = Integer.parseInt(req.queryParams("id"));
            String title = req.queryParams("title");
            String description = req.queryParams("description");
            listDAO.update(id,title,description);
            return gson.toJson(listDAO.findById(id));
        });
        ///// Creation
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
        post("/list/add", "application/json", (req, res) -> {
            String title = req.queryParams("title");
            String description = req.queryParams("description");
            List list = new List(title,description);
            list.getUsers().add(new User("admin","admin","Admin"));
            listDAO.add(list);
            res.status(201);
            res.type("application/json");
            return gson.toJson(list);
        });
        post("/element/add", "application/json", (req, res) -> {
            int idList = Integer.parseInt(req.queryParams("idList"));
            String title = req.queryParams("title");
            String description = req.queryParams("description");
            String tag = req.queryParams("tag");
            String status = req.queryParams("status");
            Element element = new Element(title,description,tag,status,LocalDate.now(),null);
            elementDAO.add(element,idList);
            res.status(201);
            res.type("application/json");
            return gson.toJson(element);
        });
        ///////Supression
        delete("/user/delete/:id", "application/json", (req, res) -> {
            int userId = Integer.parseInt(req.params("id"));
            userDAO.deleteById(userId);
            res.status(201);
            res.type("application/json");
            return null;
        });
        delete("/list/delete/:id", "application/json", (req, res) -> {
            int listId = Integer.parseInt(req.params("id"));
            listDAO.deleteById(listId);
            res.status(201);
            res.type("application/json");
            return null;
        });
        delete("/element/delete/:id", "application/json", (req, res) -> {
            int elementId = Integer.parseInt(req.params("id"));
            elementDAO.deleteById(elementId);
            res.status(201);
            res.type("application/json");
            return null;
        });
        ///// Acces particulier
        get("/list/user/:id", (req, res) -> {
            res.type("application/json");
            int userId = Integer.parseInt(req.params("id"));
            res.type("application/json");
            return gson.toJson(listDAO.findByIdUser(userId));
        });
        get("/element/list/:id", (req, res) -> {
            res.type("application/json");
            int listId = Integer.parseInt(req.params("id"));
            res.type("application/json");
            return gson.toJson(elementDAO.findByIdList(listId));
        });

        ///////Connection Deconnection
        post("/signIn", "application/json", (req, res) -> {
            String name = req.queryParams("name");
            String firstname = req.queryParams("firstname");
            userConnected = userDAO.findByNameAndFirstName(name,firstname);
            Session session = req.session();
            session.attribute("userConnected",userConnected.getId());
            if(userConnected!=null){
                res.status(200);
                res.type("application/json");
                return gson.toJson(userConnected);
            }else{
                res.status(401);
                res.type("application/json");
                return null;
            }
        });
        get("/signOut", (req, res) -> {
            res.type("application/json");
            int userId = Integer.parseInt(req.params("id"));
            res.type("application/json");
            return gson.toJson(listDAO.findByIdUser(userId));
        });

    }

    public static void views(){
        Views views= new Views();
        get("/index", (req, res) -> views.index(req,res));
        get("/newuser", (req, res) -> views.user());
        get("/list-users", (req, res) -> views.list_users());
        get("/newlist", (req, res) -> views.newlist());
        get("/sign-in", (req, res) -> views.signIn());

    }

}
