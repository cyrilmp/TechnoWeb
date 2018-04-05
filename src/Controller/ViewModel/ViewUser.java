package Controller.ViewModel;

import Controller.ManageRequest.CreateRequest;
import Model.BusinessObject.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;

public class ViewUser
{
    final static Logger logger = LoggerFactory.getLogger(ViewUser.class);


    public ViewUser()
    {
        Configuration confFreeMarker = ViewController.getConfiguration();
        confFreeMarker.setClassForTemplateLoading(ViewUser.class, ViewController.TEMPLATE_DIRECTORY);

        get("/protected/new-user", (req, res) -> {
            Template template = confFreeMarker.getTemplate("new-user.ftl");
            Map<String, Object> templateData = new HashMap<>();
            templateData.put("userConnected", null);
            return ViewController.AddDataToView(template,templateData);
        });

        get("/protected/list-users", (req, res) -> {
            try{
                Gson gson = new Gson();
                Type listType = new TypeToken<List<User>>(){}.getType();
                List<User> users = gson.fromJson(CreateRequest.get("/users").body, listType);
                Template template = confFreeMarker.getTemplate("list-users.ftl");
                Map<String, Object> templateData = new HashMap<>();
                templateData.put("users",users);
                return ViewController.AddDataToView(template,templateData);
            }
            catch (Exception e)
            {
                logger.error(e.getMessage());
                return e.getMessage();
            }
        });
        get("/sign-in", (req, res) -> {
            try{
                Template template = confFreeMarker.getTemplate("signIn.ftl");
                Map<String, Object> templateData = new HashMap<>();
                return ViewController.AddDataToView(template,templateData);
            }catch (Exception e)
            {
                logger.error(e.getMessage());
                return e.getMessage();
            }
        });

        get("/protected/modify-user/:id", (req, res) -> {
            try
            {
                Gson gson = new Gson();
                Type listType = new TypeToken<User>() {
                }.getType();
                int id = Integer.parseInt(req.params("id"));
                User user = gson.fromJson(CreateRequest.get("/user/" + id).body, listType);
                Template template = confFreeMarker.getTemplate("modify-user.ftl");
                Map<String, Object> templateData = new HashMap<>();
                templateData.put("user",user);
                return ViewController.AddDataToView(template,templateData);
            }
            catch (Exception e)
            {
                logger.error(e.getMessage());
                return e.getMessage();
            }
        });
    }
}
