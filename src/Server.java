import Controller.DatabaseConnection.Sql2oConnection;
import Controller.Authentification.AuthController;
import Controller.API.ServiceController;
import Controller.ViewModel.ViewController;
import Model.DataAccess.UserDAO;
import Model.DataAccess.IUserDAO;

import static spark.Spark.get;
import static spark.SparkBase.staticFileLocation;

public class Server {

    public static void main(String[] args) {


        staticFileLocation("/public");
        String connectionString = "jdbc:h2:~/test.db;";
        //String connectionString = "jdbc:h2:~/test.db;INIT=RUNSCRIPT from 'classpath:script.sql';";
        Sql2oConnection sql2o = new Sql2oConnection(connectionString, "admin", "admin");
        new AuthController();
        new ServiceController(sql2o);
        new ViewController();




    }
}
