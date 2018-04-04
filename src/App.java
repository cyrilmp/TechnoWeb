import DAO.*;
import Model.Element;
import Model.List;
import com.google.gson.Gson;
import Model.User;
import freemarker.template.TemplateException;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import spark.Session;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

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
        String connectionString = "jdbc:h2:~/test.db;";
        //String connectionString = "jdbc:h2:~/test.db;INIT=RUNSCRIPT from 'classpath:script.sql';";
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
        get("/element/:id", (req, res) -> {
            res.type("application/json");
            int elementId = Integer.parseInt(req.params("id"));
            res.type("application/json");
            return gson.toJson(elementDAO.findById(elementId));
        });
        ////////Modification
        put("/user/","application/json", (req, res) -> {
            int id = Integer.parseInt(req.queryParams("id"));
            String name = req.queryParams("name");
            String firstname = req.queryParams("firstname");
            String role = req.queryParams("role");
            userDAO.update(id,name,firstname,role);
            return gson.toJson(userDAO.findById(id));
        });
        put("/element/","application/json", (req, res) -> {
            int id = Integer.parseInt(req.queryParams("id"));
            String title = req.queryParams("title");
            String description = req.queryParams("description");
            String tag = req.queryParams("tag");
            String status = req.queryParams("status");
            Date updating_date = Date.valueOf(LocalDate.now());
            int idList = Integer.parseInt(req.queryParams("idList"));
            elementDAO.update(id,title,description,tag,status,updating_date,idList);
            return gson.toJson(elementDAO.findById(id));
        });
        put("/element/status","application/json", (req, res) -> {
            int id = Integer.parseInt(req.queryParams("id"));
            String status = req.queryParams("status");
            Date updating_date = Date.valueOf(LocalDate.now());
            elementDAO.update(id,status,updating_date);
            return gson.toJson(elementDAO.findById(id));
        });
        put("/list/","application/json", (req, res) -> {
            int id = Integer.parseInt(req.queryParams("id"));
            String title = req.queryParams("title");
            String description = req.queryParams("description");
            listDAO.update(id,title,description);
            return gson.toJson(listDAO.findById(id));
        });
        ///// Creation
        post("/user/", "application/json", (req, res) -> {
            String name = req.queryParams("name");
            String firstname = req.queryParams("firstname");
            String role = req.queryParams("role");
            User user = new User(name,firstname,role);
            userDAO.add(user);
            res.status(201);
            res.type("application/json");
            return gson.toJson(user);
        });
        post("/list/", "application/json", (req, res) -> {
            String title = req.queryParams("title");
            String description = req.queryParams("description");
            List list = new List(title,description);
            list.getUsers().add(new User("admin","admin","Admin"));
            listDAO.add(list);
            res.status(201);
            res.type("application/json");
            return gson.toJson(list);
        });
        post("/element/", "application/json", (req, res) -> {
            int idList = Integer.parseInt(req.queryParams("idList"));
            String title = req.queryParams("title");
            String description = req.queryParams("description");
            String tag = req.queryParams("tag");
            int status = Integer.parseInt(req.queryParams("status"));
            Element element = new Element(title,description,tag,status, Date.valueOf(LocalDate.now()),null);
            elementDAO.add(element,idList);
            res.status(201);
            res.type("application/json");
            return gson.toJson(element);
        });
        ///////Supression
        delete("/user/:id", "application/json", (req, res) -> {
            int userId = Integer.parseInt(req.params("id"));
            userDAO.deleteById(userId);
            res.status(201);
            res.type("application/json");
            return true;
        });
        delete("/list/:id", "application/json", (req, res) -> {
            int listId = Integer.parseInt(req.params("id"));
            listDAO.deleteById(listId);
            res.status(201);
            res.type("application/json");
            return true;
        });
        delete("/element/:id", "application/json", (req, res) -> {
            int elementId = Integer.parseInt(req.params("id"));
            elementDAO.deleteById(elementId);
            res.status(201);
            res.type("application/json");
            return true;
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
        get("/new-user", (req, res) -> views.new_user());
        get("/new-list", (req, res) -> views.new_list());
        get("/new-element", (req, res) -> views.new_element());
        get("/modify-user/:id", (req, res) -> views.modify_user(Integer.parseInt(req.params("id"))));
        get("/modify-list/:id", (req, res) -> views.modify_list(Integer.parseInt(req.params("id"))));
        get("/modify-element/:id", (req, res) -> views.modify_element(Integer.parseInt(req.params("id"))));
        get("/list-users", (req, res) -> views.list_users());
        get("/list-lists", (req, res) -> views.list_lists());
        get("/list-elements", (req, res) -> views.list_elements());
        get("/sign-in", (req, res) -> views.signIn());
        get("/", (req, res) -> views.index(req,res));
    }

}
