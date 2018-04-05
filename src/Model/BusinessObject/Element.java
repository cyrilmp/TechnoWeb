package Model.BusinessObject;

import java.sql.Date;
import java.time.LocalDate;

public class Element {
    private int id;
    private String title;
    private String description;
    private String tag;
    private int status;
    private Date creation_date;
    private Date updating_date;
    private int idList;

    public Element() {
    }

    public Element(String title, String description, String tag, int status, Date creation_date, Date updating_date) {
        this.title = title;
        this.description = description;
        this.tag = tag;
        this.status = status;
        this.creation_date = creation_date;
        this.updating_date = updating_date;
    }

    public Element(int id,String title, String description, String tag, int status, Date creation_date, Date updating_date, int idList) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.tag = tag;
        this.status = status;
        this.creation_date = creation_date;
        this.updating_date = updating_date;
        this.idList=idList;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(Date creation_date) {
        this.creation_date = creation_date;
    }

    public Date getUpdating_date() {
        return updating_date;
    }

    public void setUpdating_date(Date updating_date) {
        this.updating_date = updating_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdList() {
        return idList;
    }

    public void setIdList(int idList) {
        this.idList = idList;
    }

    @Override
    public String toString() {
        return "Element{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", tag='" + tag + '\'' +
                ", status=" + status +
                ", creation_date=" + creation_date +
                ", updating_date=" + updating_date +
                ", idList=" + idList +
                '}';
    }
}
