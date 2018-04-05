package Controller.API;


import Model.DataAccess.IListDAO;
import Model.BusinessObject.List;
import Model.BusinessObject.User;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static spark.Spark.*;

public class ListServiceController
{

    final static Logger logger = LoggerFactory.getLogger(ElementServiceController.class);

    public ListServiceController(IListDAO listDao)
    {
        Gson gson = new Gson();

        get("/protected/lists", (req, res) -> {

            String json;
            try
            {
                res.type("application/json");
                res.status(200);
                json = gson.toJson(listDao.getAll());
            }
            catch (Exception e){
                logger.error(e.getMessage());
                res.status(500);
                json = gson.toJson(e.getMessage());
            }
            return json;

        });

        get("/protected/list/:id", (req, res) -> {
            String json;
            try
            {
                res.type("application/json");
                int listId = Integer.parseInt(req.params("id"));
                res.status(200);
                json = gson.toJson(listDao.findById(listId));
            }
            catch (Exception e){
                logger.error(e.getMessage());
                res.status(500);
                json = gson.toJson(e.getMessage());
            }
            return json;
        });

        put("/protected/protected/list/","application/json", (req, res) -> {
            String json;
            try
            {
                int id = Integer.parseInt(req.queryParams("id"));
                String title = req.queryParams("title");
                String description = req.queryParams("description");
                listDao.update(id,title,description);
                res.status(200);
                json =  gson.toJson(listDao.findById(id));
            }
            catch (Exception e){
                logger.error(e.getMessage());
                res.status(500);
                json = gson.toJson(e.getMessage());
            }
            return json;
        });


        post("/list/", "application/json", (req, res) -> {
            String json;
            try
            {
                String title = req.queryParams("title");
                String description = req.queryParams("description");
                List list = new List(title,description);
                list.getUsers().add(new User("admin","admin","Admin","",""));
                listDao.add(list);
                res.status(201);
                res.type("application/json");
                json = gson.toJson(list);
            }
            catch (Exception e){
                logger.error(e.getMessage());
                res.status(500);
                json = gson.toJson(e.getMessage());
            }
            return json;
        });

        delete("/protected/list/:id", "application/json", (req, res) -> {
            boolean ok;
            try
            {
                int listId = Integer.parseInt(req.params("id"));
                listDao.deleteById(listId);
                res.status(200);
                res.type("application/json");
                ok =  true;
            }
            catch (Exception e){
                logger.error(e.getMessage());
                res.status(500);
                ok = false;
            }
            return ok;
        });


        get("/protected/list/user/:id", (req, res) -> {
            String json;
            try
            {
                res.type("application/json");
                res.status(200);
                int userId = Integer.parseInt(req.params("id"));
                json = gson.toJson(listDao.findByIdUser(userId));
            }
            catch (Exception e){
                logger.error(e.getMessage());
                res.status(500);
                json = gson.toJson(e.getMessage());
            }
            return json;
        });
    }
}
