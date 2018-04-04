
import Model.Element;
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

    public String new_user() throws IOException,TemplateException {
        String text = "";
        Configuration cfg = new Configuration(new Version("2.3.23"));

        cfg.setClassForTemplateLoading(Views.class, "/");
        cfg.setDefaultEncoding("UTF-8");

        Template template = cfg.getTemplate("templates/new-user.ftl");

        Map<String, Object> templateData = new HashMap<>();
        templateData.put("userConnected", App.userConnected);
        try (StringWriter out = new StringWriter()) {
            template.process(templateData, out);
            text = text+ out.getBuffer().toString();
            out.flush();
        }
        return text;
    }

    public String modify_user(int id) throws IOException,TemplateException {
        Gson gson = new Gson();
        String text = "";
        Configuration cfg = new Configuration(new Version("2.3.23"));
        Type listType = new TypeToken<User>(){}.getType();
        cfg.setClassForTemplateLoading(Views.class, "/");
        cfg.setDefaultEncoding("UTF-8");

        Template template = cfg.getTemplate("templates/modify-user.ftl");

        Map<String, Object> templateData = new HashMap<>();
        User user = gson.fromJson(CreateRequest.get("/user/"+id).body, listType);
        templateData.put("user",user);
        try (StringWriter out = new StringWriter()) {
            template.process(templateData, out);
            text = text+ out.getBuffer().toString();
            out.flush();
        }
        return text;
    }
    public String modify_list(int id) throws IOException,TemplateException {//////TO DO
        Gson gson = new Gson();
        String text = "";
        Configuration cfg = new Configuration(new Version("2.3.23"));
        Type listType = new TypeToken<Model.List>(){}.getType();
        Model.List list = gson.fromJson(CreateRequest.get("/list/"+id).body, listType);
        cfg.setClassForTemplateLoading(Views.class, "/");
        cfg.setDefaultEncoding("UTF-8");

        Template template = cfg.getTemplate("templates/modify-list.ftl");

        Map<String, Object> templateData = new HashMap<>();

        templateData.put("list",list);
        try (StringWriter out = new StringWriter()) {
            template.process(templateData, out);
            text = text+ out.getBuffer().toString();
            out.flush();
        }
        return text;
    }

    public String modify_element(int id) throws IOException,TemplateException {
        Gson gson = new Gson();
        String text = "";
        Configuration cfg = new Configuration(new Version("2.3.23"));
        Type listType = new TypeToken<Element>(){}.getType();
        Element element = gson.fromJson(CreateRequest.get("/element/"+id).body, listType);
        listType = new TypeToken<List<Model.List>>(){}.getType();
        List<Model.List> list = (List<Model.List>) gson.fromJson(CreateRequest.get("/lists").body, listType);
        cfg.setClassForTemplateLoading(Views.class, "/");
        cfg.setDefaultEncoding("UTF-8");

        Template template = cfg.getTemplate("templates/modify-element.ftl");

        Map<String, Object> templateData = new HashMap<>();

        templateData.put("element",element);
        templateData.put("lists",list);
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

    public String list_lists() throws IOException {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Model.List>>(){}.getType();
        String text = "";
        Configuration cfg = new Configuration(new Version("2.3.23"));

        cfg.setClassForTemplateLoading(Views.class, "/");
        cfg.setDefaultEncoding("UTF-8");

        Template template = cfg.getTemplate("templates/list-lists.ftl");

        Map<String, Object> templateData = new HashMap<>();
        List<Model.List> list = (List<Model.List>) gson.fromJson(CreateRequest.get("/lists").body, listType);
        templateData.put("lists", list);
        try (StringWriter out = new StringWriter()) {
            template.process(templateData, out);
            text = text+ out.getBuffer().toString();
            out.flush();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        return text;
    }

    public String list_elements() throws IOException {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Element>>(){}.getType();
        String text = "";
        Configuration cfg = new Configuration(new Version("2.3.23"));

        cfg.setClassForTemplateLoading(Views.class, "/");
        cfg.setDefaultEncoding("UTF-8");

        Template template = cfg.getTemplate("templates/list-elements.ftl");

        Map<String, Object> templateData = new HashMap<>();
        List<Element> list = (List<Element>) gson.fromJson(CreateRequest.get("/elements").body, listType);
        templateData.put("elements", list);
        try (StringWriter out = new StringWriter()) {
            template.process(templateData, out);
            text = text+ out.getBuffer().toString();
            out.flush();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        return text;
    }

    public String new_list() throws IOException,TemplateException {
        String text = "";
        Configuration cfg = new Configuration(new Version("2.3.23"));

        cfg.setClassForTemplateLoading(Views.class, "/");
        cfg.setDefaultEncoding("UTF-8");

        Template template = cfg.getTemplate("templates/new-list.ftl");

        Map<String, Object> templateData = new HashMap<>();
        templateData.put("userConnected", App.userConnected);
        try (StringWriter out = new StringWriter()) {
            template.process(templateData, out);
            text = text+ out.getBuffer().toString();
            out.flush();
        }
        return text;
    }

    public String new_element() throws IOException,TemplateException {
        Gson gson = new Gson();
        String text = "";
        Type listType = new TypeToken<List<Model.List>>(){}.getType();
        Configuration cfg = new Configuration(new Version("2.3.23"));

        cfg.setClassForTemplateLoading(Views.class, "/");
        cfg.setDefaultEncoding("UTF-8");

        Template template = cfg.getTemplate("templates/new-element.ftl");
        List<Model.List> list = (List<Model.List>) gson.fromJson(CreateRequest.get("/lists").body, listType);
        Map<String, Object> templateData = new HashMap<>();
        templateData.put("lists", list);
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
