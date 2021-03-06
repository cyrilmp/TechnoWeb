package Model.DataAccess;

import Model.BusinessObject.Element;

import java.sql.Date;

public interface IElementDAO {

    //create
    void add(Element element, int idList);

    //read
    java.util.List<Element> getAll();
    Element findById(int id);
    java.util.List<Element>  findByIdList(int idList);

    //update
    void update(int id, String title, String description, String tag, String status, Date updating_date, int idList);
    void update(int id, String status, Date updating_date);
    //delete
    void deleteById(int id);

}
