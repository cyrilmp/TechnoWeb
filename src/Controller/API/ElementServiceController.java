package Controller.API;

import Controller.ViewModel.ViewController;
import Model.DataAccess.IElementDAO;
import Model.BusinessObject.Element;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.time.LocalDate;

import static spark.Spark.*;

public class ElementServiceController
{

    final static Logger logger = LoggerFactory.getLogger(ElementServiceController.class);

    public ElementServiceController(IElementDAO elementDAO)
    {
        Gson gson = new Gson();

        get("/protected/elements", (req, res) ->
        {
            String json;
            try{
                json =  gson.toJson(elementDAO.getAll());
                res.status(200);
            }catch (Exception e){
                logger.error(e.getMessage());
                res.status(500);
                json = gson.toJson(e.getMessage());
            }
            return json;
        });

        get("/protected/element/:id", (req, res) -> {
            String json;
            try{
                res.type("application/json");
                int elementId = Integer.parseInt(req.params("id"));
                res.status(200);
                json = gson.toJson(elementDAO.findById(elementId));
            }catch (Exception e){
                logger.error(e.getMessage());
                res.status(500);
                json = gson.toJson(e.getMessage());
            }
            return json;
        });

        put("/protected/element/","application/json", (req, res) -> {
            String json;
            try{
                int id = Integer.parseInt(req.queryParams("id"));
                String title = req.queryParams("title");
                String description = req.queryParams("description");
                String tag = req.queryParams("tag");
                String status = req.queryParams("status");
                Date updating_date = Date.valueOf(LocalDate.now());
                int idList = Integer.parseInt(req.queryParams("idList"));
                elementDAO.update(id,title,description,tag,status,updating_date,idList);
                json =  gson.toJson(elementDAO.findById(id));
                res.status(200);
            }catch (Exception e){
                logger.error(e.getMessage());
                res.status(500);
                json = gson.toJson(e.getMessage());
            }
            return json;
        });

        put("/protected/element/status","application/json", (req, res) -> {
            String json;
            try{
                int id = Integer.parseInt(req.queryParams("id"));
                String status = req.queryParams("status");
                Date updating_date = Date.valueOf(LocalDate.now());
                elementDAO.update(id,status,updating_date);
                json = gson.toJson(elementDAO.findById(id));
                res.status(200);
            }catch (Exception e){
                logger.error(e.getMessage());
                res.status(500);
                json = gson.toJson(e.getMessage());
            }
            return json;
        });


        post("/protected/element/", "application/json", (req, res) -> {
            String json;
            try{
                int idList = Integer.parseInt(req.queryParams("idList"));
                String title = req.queryParams("title");
                String description = req.queryParams("description");
                String tag = req.queryParams("tag");
                int status = Integer.parseInt(req.queryParams("status"));
                Element element = new Element(title,description,tag,status, Date.valueOf(LocalDate.now()),null);
                elementDAO.add(element,idList);
                res.status(201);
                res.type("application/json");
                json = gson.toJson(element);
            }catch (Exception e){
                logger.error(e.getMessage());
                res.status(500);
                json = gson.toJson(e.getMessage());
            }
            return json;
        });

        delete("/protected/element/:id", "application/json", (req, res) -> {
            boolean ok;
            try{
                int elementId = Integer.parseInt(req.params("id"));
                elementDAO.deleteById(elementId);
                res.status(200);
                res.type("application/json");
                ok = true;
            }catch (Exception e){
                logger.error(e.getMessage());
                res.status(500);
                ok = false;
            }
            return ok;
        });


        get("/protected/element/list/:id", (req, res) -> {
            String json;
            try{
                res.type("application/json");
                int listId = Integer.parseInt(req.params("id"));
                res.status(200);
                json = gson.toJson(elementDAO.findByIdList(listId));
            }catch (Exception e){
                logger.error(e.getMessage());
                res.status(500);
                json = gson.toJson(e.getMessage());
            }
            return json;
        });
    }
}
