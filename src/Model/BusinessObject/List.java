package Model.BusinessObject;

import java.util.ArrayList;

public class List {
    private int id;
    private String title;
    private String description;
    private java.util.List<Element> elements;
    private java.util.List<User> users;

    public List() {
        elements = new ArrayList<>();
        users= new ArrayList<>();
    }

    public List(String title, String description) {
        this.title = title;
        this.description = description;
        elements = new ArrayList<>();
        users= new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public java.util.List<Element> getElements() {
        return elements;
    }

    public void setElements(java.util.List<Element> elements) {
        this.elements = elements;
    }

    public java.util.List<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("List{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                        ", elements= [" );
        for (Element e:elements) {
            sb.append(e.toString());
        }
        sb.append("]," +
                "\", users=[\"");

        for (User u:users) {
            sb.append(u.toString());
        }
        sb.append("]");
        return sb.toString();

    }
}
