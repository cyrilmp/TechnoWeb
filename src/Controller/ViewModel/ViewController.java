package Controller.ViewModel;

import Controller.ManageRequest.CreateRequest;
import Model.BusinessObject.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;

public class ViewController
{

    final static Logger logger = LoggerFactory.getLogger(ViewController.class);

    public static final String TEMPLATE_DIRECTORY ="/templates";

    private static final String NUM_VERSION ="2.3.23";

    public ViewController() {

        get("/", (req, res) -> index());
        get("/protected/index", (req, res) -> index());

        new ViewUser();
        new ViewElement();
        new ViewList();
    }

    private String index()
    {
        Configuration confFreeMarker = getConfiguration();
        confFreeMarker.setClassForTemplateLoading(ViewController.class, ViewController.TEMPLATE_DIRECTORY);
        Gson gson = new Gson();
        Type listType = new TypeToken<java.util.List<List>>(){}.getType();
        java.util.List<List> lists = gson.fromJson(CreateRequest.get("/lists").body, listType);
        try {
            Template template = confFreeMarker.getTemplate("index.ftl");
            Map<String, Object> templateData = new HashMap<>();
            templateData.put("title", "Keep List");
            templateData.put("lists", lists);

            return ViewController.AddDataToView(template,templateData);
        } catch (IOException e) {
            logger.error(e.getLocalizedMessage());
            return null;
        }
    }

    public static Configuration getConfiguration(){
        Configuration confFreeMarker = new Configuration(new Version(NUM_VERSION));
        confFreeMarker.setDefaultEncoding("UTF-8");
        return confFreeMarker;
    }

    public static String AddDataToView(Template template,Map<String, Object> templateData)
    {
        String text= "";
        try (StringWriter out = new StringWriter())
        {
            template.process(templateData, out);
            text = text+ out.getBuffer().toString();
            out.flush();
        }
        catch (Exception e)
        {
            logger.error(e.getMessage());
        }
        return text;
    }
}
