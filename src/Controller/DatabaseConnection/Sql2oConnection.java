package Controller.DatabaseConnection;

import com.google.inject.Inject;
import org.sql2o.Sql2o;

public class Sql2oConnection extends Sql2o implements ISql2oConnection
{

    @Inject
    public Sql2oConnection(String url, String user, String pass) {
        super(url, user, pass);
    }
}
