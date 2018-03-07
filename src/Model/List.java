package Model;

import Model.Element;

import java.util.ArrayList;

public class List {
    private String title;
    private String description;
    private ArrayList<Element> elements;
    private ArrayList<User> users;

    public List() {
    }

    public List(String title, String description, ArrayList<Element> elements, ArrayList<User> users) {
        this.title = title;
        this.description = description;
        this.elements = elements;
        this.users = users;
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
}
