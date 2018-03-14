package Model;

import Model.Element;

import java.util.ArrayList;

public class List {
    private int id;
    private String title;
    private String description;
    private ArrayList<Element> elements;
    private ArrayList<User> users;

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

    public ArrayList<Element> getElements() {
        return elements;
    }

    public void setElements(ArrayList<Element> elements) {
        this.elements = elements;
    }

    public ArrayList<User> getUsers() {
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

}
