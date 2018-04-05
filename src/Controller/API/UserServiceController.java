package Controller.API;

import Model.DataAccess.IUserDAO;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.put;

public class UserServiceController
{
    final static Logger logger = LoggerFactory.getLogger(UserServiceController.class);


    public UserServiceController(IUserDAO userDAO) {

        Gson gson = new Gson();

        get("/protected/users", (req, res) ->{
            String json;
            try{
                res.status(200);
                json = gson.toJson(userDAO.getAll());
            }
            catch (Exception e){
                logger.error(e.getMessage());
                res.status(500);
                json = gson.toJson(e.getMessage());
            }
            return json;
        });

        /////// Acces une ressources
        get("/protected/user/:id", (req, res) -> {
            String json;
            try{
                res.type("application/json");
                res.status(200);
                int userId = Integer.parseInt(req.params("id"));
                json = gson.toJson(userDAO.findById(userId));
            }
            catch (Exception e){
                logger.error(e.getMessage());
                res.status(500);
                json = gson.toJson(e.getMessage());
            }
            return json;
        });

        put("/protected/user/","application/json", (req, res) -> {
            String json;
            try{
                int id = Integer.parseInt(req.queryParams("id"));
                String name = req.queryParams("name");
                String firstname = req.queryParams("firstname");
                String role = req.queryParams("role");
                if(userDAO.findById(id) != null){
                    userDAO.update(id,name,firstname,role);
                    res.status(200);
                }else{
                    res.status(400);
                }
                json = gson.toJson(id);
            }
            catch (Exception e){
                logger.error(e.getMessage());
                res.status(500);
                json = gson.toJson(e.getMessage());
            }
            return json;
        });

        //post user is in AuthController = /register

        delete("/protected/user/:id", "application/json", (req, res) -> {
            boolean ok;
            try{
                int userId = Integer.parseInt(req.params("id"));
                userDAO.deleteById(userId);
                res.status(200);
                res.type("application/json");
                ok = true;
            }
            catch (Exception e){
                logger.error(e.getMessage());
                res.status(500);
                ok = false;
            }
            return ok;
        });
    }




}
