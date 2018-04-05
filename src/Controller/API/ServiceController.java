package Controller.API;

import Controller.DatabaseConnection.Sql2oConnection;
import Model.DataAccess.*;


public class ServiceController {

    public ServiceController(Sql2oConnection sql2oConnection) {


        IUserDAO daoUser = new UserDAO(sql2oConnection);
        IElementDAO elementDao = new ElementDAO(sql2oConnection);
        IListDAO listDao = new ListDAO(sql2oConnection);

        new UserServiceController(daoUser);
        new ElementServiceController(elementDao);
        new ListServiceController(listDao);

    }
}
