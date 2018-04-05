package Controller.ViewModel;

import Controller.ManageRequest.CreateRequest;
import Model.BusinessObject.Element;
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

public class ViewElement
{
    final static Logger logger = LoggerFactory.getLogger(ViewElement.class);

    public ViewElement()
    {

        Configuration confFreeMarker = ViewController.getConfiguration();
        confFreeMarker.setClassForTemplateLoading(ViewElement.class, ViewController.TEMPLATE_DIRECTORY);

        get("/protected/new-element", (req, res) -> {
            try{
                Type listType = new TypeToken< java.util.List<List>>(){}.getType();
                Gson gson = new Gson();
                java.util.List<List> list = gson.fromJson(CreateRequest.get("/lists").body, listType);
                Template template = confFreeMarker.getTemplate("new-element.ftl");
                Map<String, Object> templateData = new HashMap<>();
                templateData.put("lists", list);
                return ViewController.AddDataToView(template,templateData);
            }
            catch (Exception e)
            {
                logger.error(e.getMessage());
                return e.getMessage();
            }
        });

        get("/protected/modify-element/:id", (req, res) ->
        {
            try
            {
                int id = Integer.parseInt(req.params("id"));
                Gson gson = new Gson();
                Type listType = new TypeToken<Element>(){}.getType();
                Element element = gson.fromJson(CreateRequest.get("/element/"+id).body, listType);
                listType = new TypeToken<java.util.List<List>>(){}.getType();
                java.util.List<List> list = gson.fromJson(CreateRequest.get("/lists").body, listType);

                Template template = confFreeMarker.getTemplate("modify-element.ftl");
                Map<String, Object> templateData = new HashMap<>();
                templateData.put("element",element);
                templateData.put("lists",list);
                return ViewController.AddDataToView(template,templateData);
            }
            catch (Exception e)
            {
                logger.error(e.getMessage());
                return e.getMessage();
            }
        });

        get("/protected/list-elements", (req, res) -> {
            try
            {
                Gson gson = new Gson();
                Type listType = new TypeToken<java.util.List<Element>>(){}.getType();
                java.util.List<Element> list = gson.fromJson(CreateRequest.get("/elements").body, listType);
                Template template = confFreeMarker.getTemplate("list-elements.ftl");
                Map<String, Object> templateData = new HashMap<>();
                templateData.put("elements", list);
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
