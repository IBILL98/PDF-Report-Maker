package sample.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHandler extends Configs {
    Connection dbConnection;
    public Connection getDbConnection() throws ClassNotFoundException, SQLException{
        String connectionString = "jdbc:mysql://"+ dbHost + ":"
                + dbPort + "/"
                + dbName;
        Class.forName("com.mysql.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString,dbUser,dbPass);

        return dbConnection;
    }
    //Add
    public void signUpUser(String Name,String LastName,String UserName,Integer Level,String Password){
        String insert = "INSERT INTO "+Const.

    }

    //Read


    //Update


    //Delete
}
