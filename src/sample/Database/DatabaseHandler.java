package sample.Database;

import sample.model.Employee;

import java.sql.*;

public class DatabaseHandler extends Configs {
    Connection dbConnection;
    public Connection getDbConnection() throws ClassNotFoundException, SQLException {

        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName + "?useSSL=false";
        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser , dbPass);

        return dbConnection;
    }
    //Add
    public void signUpEmployee(Employee employee){
        String insert = "INSERT INTO "+Const.EMPLOYEE_TABLE+"("+Const.EMPLOYEE_NAME+","+Const.EMPLOYEE_LASTNAME
                +","+Const.EMPLOYEE_USERNAME+","
                +Const.EMPLOYEE_LEVEL+","+Const.EMPLOYEE_PASSWORD+","+Const.EMPLOYEE_WORK+")"+"VALUES(?,?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);

            preparedStatement.setString(1,employee.getName());
            preparedStatement.setString(2,employee.getLastName());
            preparedStatement.setString(3,employee.getUsername());
            preparedStatement.setString(4,employee.getLevel());
            preparedStatement.setString(5,employee.getPassword());
            preparedStatement.setString(6,employee.getWork());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


    public ResultSet getEmployee(Employee employee){
        ResultSet resultSet = null;


        if(!employee.getUsername().equals("") || !employee.getPassword().equals("")){
            String query = "SELECT * FROM "+ Const.EMPLOYEE_TABLE + " WHERE "
                    +Const.EMPLOYEE_USERNAME + "=?"+" AND "
                    +Const.EMPLOYEE_PASSWORD + "=?";

            try {
                PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
                preparedStatement.setString(1,employee.getUsername());
                preparedStatement.setString(2,employee.getPassword());

                resultSet = preparedStatement.executeQuery();

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


        }else{
            System.out.println("please entere your correct infos");
        }



        return resultSet;
    }



    //Read


    //Update


    //Delete
}
