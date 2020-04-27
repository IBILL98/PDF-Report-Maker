package sample.Database;

import sample.model.Admin;
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
    public void addEmployee(Employee employee){
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

    public void addAdmin(Admin admin) {

        String insert = "INSERT INTO "+Const.ADMINS_TABLE+"("+Const.ADMINS_NAME+","+Const.ADMINS_LASTNAME
                +","+Const.ADMINS_USERNAME+","
                +Const.ADMINS_PASSWORD+")"+"VALUES(?,?,?,?)";

        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);

            preparedStatement.setString(1,admin.getName());
            preparedStatement.setString(2,admin.getLastName());
            preparedStatement.setString(3,admin.getUsername());
            preparedStatement.setString(4,admin.getPassword());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    //Read


    //Update


    //Delete
    //DELETE FROM `my_database`.`companies` WHERE (`Id` = '1');
    //DELETE FROM `my_database`.`employee` WHERE (`id` = '11120') and (`Username` = 'Username1');

    public void deleteEmployee(Employee employee){
        String delete = "DELETE FROM "+Const.EMPLOYEE_TABLE+" WHERE "+"(" +Const.EMPLOYEE_USERNAME +" =?"+ ")";
        try {
                PreparedStatement preparedStatement = getDbConnection().prepareStatement(delete);
                preparedStatement.setString(1,employee.getUsername());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
