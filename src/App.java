
import org.h2.tools.DeleteDbFiles;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import static spark.Spark.*;

public class App {

    public static void main(String[] args){
        //Gson gson = new Gson();
        Connection conn;
        String connectionString = "jdbc:h2:~/test.db;";
        //String connectionString = "jdbc:h2:~/test.db;INIT=RUNSCRIPT from 'classpath:script.sql';";
        Sql2o sql2o = new Sql2o(connectionString, "admin", "admin");
        conn = sql2o.open();
        get("/hello", (req, res) -> "Hello World");

    }

}
