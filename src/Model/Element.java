package Model;

import java.time.LocalDate;

public class Element {
    private String title;
    private String description;
    private String tag;
    private Status status;
    private LocalDate creation_date;
    private LocalDate update_date;

    public Element() {
    }

    public Element(String title, String description, String tag, Status status, LocalDate creation_date, LocalDate update_date) {
        this.title = title;
        this.description = description;
        this.tag = tag;
        this.status = status;
        this.creation_date = creation_date;
        this.update_date = update_date;
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

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(LocalDate creation_date) {
        this.creation_date = creation_date;
    }

    public LocalDate getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(LocalDate update_date) {
        this.update_date = update_date;
    }
}
