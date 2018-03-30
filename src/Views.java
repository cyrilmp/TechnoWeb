
import DAO.Sql2oUserDAO;
import DAO.UserDAO;
import Model.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;
import spark.Request;
import spark.Response;
import spark.Session;

import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Views {

    public Views() {
    }

    public String index(Request req, Response res) throws IOException,TemplateException {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Model.List>>(){}.getType();
        String text = "";
        Configuration cfg = new Configuration(new Version("2.3.23"));

        cfg.setClassForTemplateLoading(Views.class, "/");
        cfg.setDefaultEncoding("UTF-8");

        Template template = cfg.getTemplate("templates/index.ftl");

        Map<String, Object> templateData = new HashMap<>();
        templateData.put("title", "Keep List");
        List<Model.List> lists = gson.fromJson(CreateRequest.get("/lists").body, listType);
        templateData.put("lists", lists);
        Session session = req.session();
        templateData.put("userConnected", session.attribute("userConnected"));

        try (StringWriter out = new StringWriter()) {
            template.process(templateData, out);
            text = text+ out.getBuffer().toString();
            out.flush();
        }
        return text;
    }

    public String user() throws IOException,TemplateException {
        String text = "";
        Configuration cfg = new Configuration(new Version("2.3.23"));

        cfg.setClassForTemplateLoading(Views.class, "/");
        cfg.setDefaultEncoding("UTF-8");

        Template template = cfg.getTemplate("templates/newuser.ftl");

        Map<String, Object> templateData = new HashMap<>();
        templateData.put("userConnected", App.userConnected);
        try (StringWriter out = new StringWriter()) {
            template.process(templateData, out);
            text = text+ out.getBuffer().toString();
            out.flush();
        }
        return text;
    }

    public String list_users() throws IOException {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<User>>(){}.getType();
        String text = "";
        Configuration cfg = new Configuration(new Version("2.3.23"));

        cfg.setClassForTemplateLoading(Views.class, "/");
        cfg.setDefaultEncoding("UTF-8");

        Template template = cfg.getTemplate("templates/list-users.ftl");

        Map<String, Object> templateData = new HashMap<>();
        List<User> list = (List<User>) gson.fromJson(CreateRequest.get("/users").body, listType);
        templateData.put("users", list);
        templateData.put("userConnected", App.userConnected);
        try (StringWriter out = new StringWriter()) {
            template.process(templateData, out);
            text = text+ out.getBuffer().toString();
            out.flush();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        return text;
    }

    public String newlist() throws IOException,TemplateException {
        String text = "";
        Configuration cfg = new Configuration(new Version("2.3.23"));

        cfg.setClassForTemplateLoading(Views.class, "/");
        cfg.setDefaultEncoding("UTF-8");

        Template template = cfg.getTemplate("templates/newlist.ftl");

        Map<String, Object> templateData = new HashMap<>();
        templateData.put("userConnected", App.userConnected);
        try (StringWriter out = new StringWriter()) {
            template.process(templateData, out);
            text = text+ out.getBuffer().toString();
            out.flush();
        }
        return text;
    }

    public String signIn() throws IOException,TemplateException {
        String text = "";
        Configuration cfg = new Configuration(new Version("2.3.23"));

        cfg.setClassForTemplateLoading(Views.class, "/");
        cfg.setDefaultEncoding("UTF-8");

        Template template = cfg.getTemplate("templates/signIn.ftl");

        Map<String, Object> templateData = new HashMap<>();

        try (StringWriter out = new StringWriter()) {
            template.process(templateData, out);
            text = text+ out.getBuffer().toString();
            out.flush();
        }
        return text;
    }



}
