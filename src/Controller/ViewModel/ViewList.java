package Controller.ViewModel;

import Controller.ManageRequest.CreateRequest;
import Model.BusinessObject.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;

public class ViewList
{
    final static Logger logger = LoggerFactory.getLogger(ViewElement.class);

    public ViewList()
    {
        Configuration confFreeMarker = ViewController.getConfiguration();
        confFreeMarker.setClassForTemplateLoading(ViewList.class, ViewController.TEMPLATE_DIRECTORY);

        get("/protected/modify-list/:id", (req, res) -> {
            try{
                int id = Integer.parseInt(req.params("id"));
                Type listType = new TypeToken<List>(){}.getType();
                List list = new Gson().fromJson(CreateRequest.get("/list/"+id).body, listType);
                Template template = confFreeMarker.getTemplate("modify-list.ftl");
                Map<String, Object> templateData = new HashMap<>();
                templateData.put("list",list);
                return ViewController.AddDataToView(template,templateData);
            }catch (Exception e){
                logger.error(e.getMessage());
                return e.getMessage();
            }

        });
        get("/protected/new-list", (req, res) -> {
            try{
                Template template = confFreeMarker.getTemplate("new-list.ftl");

                Map<String, Object> templateData = new HashMap<>();
                templateData.put("userConnected", null);

                return ViewController.AddDataToView(template,templateData);
            }catch (Exception e){
                logger.error(e.getMessage());
                return e.getMessage();
            }
        });
        get("/protected/list-lists", (req, res) -> {
            try{
                Type listType = new TypeToken<java.util.List<List>>(){}.getType();
                Gson gson =new Gson();
                java.util.List<List> list = gson.fromJson(CreateRequest.get("/lists").body, listType);
                Template template = confFreeMarker.getTemplate("list-lists.ftl");
                Map<String, Object> templateData = new HashMap<>();
                templateData.put("lists", list);
                return ViewController.AddDataToView(template,templateData);
            }catch (Exception e){
                logger.error(e.getMessage());
                return e.getMessage();
            }
        });
    }
}
