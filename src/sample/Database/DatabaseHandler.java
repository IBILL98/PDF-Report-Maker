package sample.Database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyEvent;
import sample.model.Admin;
import sample.model.Company;
import sample.model.Employee;
import sample.model.Equipment;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class DatabaseHandler extends Configs {

    private DatabaseHandler databaseHandler;
    private JPanel pane;

    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {

        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName + "?useSSL=false";
        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        return dbConnection;
    }

    //Adding employee into Database
    public void addEmployee(Employee employee) {
        String insert = "INSERT INTO " + Const.EMPLOYEE_TABLE + "(" + Const.EMPLOYEE_NAME + "," + Const.EMPLOYEE_LASTNAME
                + "," + Const.EMPLOYEE_USERNAME + ","
                + Const.EMPLOYEE_LEVEL + "," + Const.EMPLOYEE_PASSWORD + "," + Const.EMPLOYEE_WORK + "," + Const.EMPLOYEE_CDATE + ")" + "VALUES(?,?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);

            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setString(3, employee.getUsername());
            preparedStatement.setString(4, String.valueOf(employee.getLevel()));
            preparedStatement.setString(5, employee.getPassword());
            preparedStatement.setString(6, employee.getWork());

            preparedStatement.executeUpdate();
            if (employee.getLevel() != 100000) {
                done();
            }


        } catch (SQLException e) {
            e.printStackTrace();
            Frame parent = new JFrame();
            JOptionPane.showMessageDialog(parent, "this User Name is already used");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getEmployeeById(Employee employee) {
        ResultSet resultSet = null;
        String query = "SELECT * FROM " + Const.EMPLOYEE_TABLE + " WHERE "
                + Const.EMPLOYEE_ID + "=?";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
            preparedStatement.setInt(1, employee.getId());
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resultSet;
    }


    //Read Employee From the Database
    public ResultSet getEmployee(Employee employee) {
        ResultSet resultSet = null;

        if (!employee.getUsername().equals("") && !employee.getPassword().equals("")) {
            String query = "SELECT * FROM " + Const.EMPLOYEE_TABLE + " WHERE "
                    + Const.EMPLOYEE_USERNAME + "=?" + " AND "
                    + Const.EMPLOYEE_PASSWORD + "=?";

            try {
                PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
                preparedStatement.setString(1, employee.getUsername());
                preparedStatement.setString(2, employee.getPassword());

                resultSet = preparedStatement.executeQuery();

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } else {
            Frame parent = new JFrame();
            JOptionPane.showMessageDialog(parent, "Please fill your correct Username and Password");
        }
        return resultSet;
    }


    //Read Admin From The Database
    public ResultSet getAdmin(Admin admin) {
        ResultSet resultSet = null;

        if (!admin.getUsername().equals("") && !admin.getPassword().equals("")) {
            String query = "SELECT * FROM " + Const.ADMINS_TABLE + " WHERE "
                    + Const.ADMINS_USERNAME + "=?" + " AND "
                    + Const.ADMINS_PASSWORD + "=?";

            try {
                PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
                preparedStatement.setString(1, admin.getUsername());
                preparedStatement.setString(2, admin.getPassword());

                resultSet = preparedStatement.executeQuery();

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } else {
            Frame parent = new JFrame();
            JOptionPane.showMessageDialog(parent, "Please fill your correct Username and Password");
        }
        return resultSet;
    }


    public void addAdmin(Admin admin) {

        String insert = "INSERT INTO " + Const.ADMINS_TABLE + "(" + Const.ADMINS_NAME + "," + Const.ADMINS_LASTNAME
                + "," + Const.ADMINS_USERNAME + ","
                + Const.ADMINS_PASSWORD + ")" + "VALUES(?,?,?,?)";

        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);

            preparedStatement.setString(1, admin.getName());
            preparedStatement.setString(2, admin.getLastName());
            preparedStatement.setString(3, admin.getUsername());
            preparedStatement.setString(4, admin.getPassword());

            preparedStatement.executeUpdate();
            done();

        } catch (SQLException e) {
            e.printStackTrace();
            Frame parent = new JFrame();
            JOptionPane.showMessageDialog(parent, "This Username is registered before");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public ResultSet getEmployeeByUsername(Employee employee) {

        ResultSet resultSet = null;

        if (!employee.getUsername().equals("")) {
            String query = "SELECT * FROM " + Const.EMPLOYEE_TABLE + " WHERE "
                    + Const.EMPLOYEE_USERNAME + "=?";
            try {
                PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
                preparedStatement.setString(1, employee.getUsername());
                resultSet = preparedStatement.executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("please entere your correct infos");
        }
        return resultSet;
    }


    //UPDATE `my_database`.`employee` SET `LastName` = 'sssss' WHERE  (`Username` = 'Username12');
    //Update
    public void editEmployeeWindow(ResultSet employeeRow) throws SQLException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Select your Info");
        alert.setHeaderText("Which Information would you like to edit");
        alert.setContentText("when you edit you cant have your old infos back");

        ButtonType buttonTypeOne = new ButtonType("Name");
        ButtonType buttonTypeTwo = new ButtonType("LastName");
        ButtonType buttonTypeThree = new ButtonType("Level");
        ButtonType buttonTypeFour = new ButtonType("Password");
        ButtonType buttonTypeFive = new ButtonType("Work");
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeThree, buttonTypeFour, buttonTypeFive, buttonTypeCancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne) {
            // ... Edit Name
            String Name = showNewInfos(employeeRow, "Name");
            String update = "UPDATE " + Const.EMPLOYEE_TABLE + " SET " + Const.EMPLOYEE_NAME + "=?" + " WHERE "
                    + Const.EMPLOYEE_USERNAME + "=?";
            try {
                PreparedStatement preparedStatement = getDbConnection().prepareStatement(update);

                preparedStatement.setString(1, Name);
                preparedStatement.setString(2, employeeRow.getString("Username"));

                preparedStatement.executeUpdate();
                done();

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } else if (result.get() == buttonTypeTwo) {
            // ... Edite LastName
            String LastName = showNewInfos(employeeRow, "LastName");
            String update = "UPDATE " + Const.EMPLOYEE_TABLE + " SET " + Const.EMPLOYEE_LASTNAME + "=?" + " WHERE "
                    + Const.EMPLOYEE_USERNAME + "=?";


            try {
                PreparedStatement preparedStatement = getDbConnection().prepareStatement(update);

                preparedStatement.setString(1, LastName);
                preparedStatement.setString(2, employeeRow.getString("Username"));

                preparedStatement.executeUpdate();
                done();

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } else if (result.get() == buttonTypeThree) {
            // ... user chose "Three"
            String Level = showNewInfos(employeeRow, "Level");
            String update = "UPDATE " + Const.EMPLOYEE_TABLE + " SET " + Const.EMPLOYEE_LEVEL + "=?" + " WHERE " + Const.EMPLOYEE_USERNAME + "=?";
            try {
                PreparedStatement preparedStatement = getDbConnection().prepareStatement(update);

                preparedStatement.setString(1, Level);
                preparedStatement.setString(2, employeeRow.getString("Username"));

                preparedStatement.executeUpdate();
                done();

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } else if (result.get() == buttonTypeFour) {
            //
            String Password = showNewInfos(employeeRow, "Password");
            String update = "UPDATE " + Const.EMPLOYEE_TABLE + " SET " + Const.EMPLOYEE_PASSWORD + "=?" + " WHERE "
                    + Const.EMPLOYEE_USERNAME + "=?";
            try {
                PreparedStatement preparedStatement = getDbConnection().prepareStatement(update);

                preparedStatement.setString(1, Password);
                preparedStatement.setString(2, employeeRow.getString("Username"));

                preparedStatement.executeUpdate();
                done();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } else if (result.get() == buttonTypeFive) {
            String Work = showNewInfos(employeeRow, "Work");
            String update = "UPDATE " + Const.EMPLOYEE_TABLE + " SET " + Const.EMPLOYEE_WORK + "=?" + " WHERE "
                    + Const.EMPLOYEE_USERNAME + "=?";
            try {
                PreparedStatement preparedStatement = getDbConnection().prepareStatement(update);

                preparedStatement.setString(1, Work);
                preparedStatement.setString(2, employeeRow.getString("Username"));

                preparedStatement.executeUpdate();
                done();

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } else {
            // ... user chose CANCEL or closed the dialog
        }


    }

    public String showNewInfos(ResultSet employeeRow, String target) throws SQLException {
        String currentname = employeeRow.getString(target);
        JFrame frame = new JFrame();
        Object result = JOptionPane.showInputDialog(frame, "Your current '" + target + "' is : " + employeeRow.getString(target) + " Entere the new value");
        return (String) result;
    }


    public void done() {
        Frame parent = new JFrame();
        JOptionPane.showMessageDialog(parent, "Done");
    }


    //Delete

    //DELETE FROM `my_database`.`companies` WHERE (`Username` = 'Username1');
    public void deleteEmployee(Employee employee) {
        String delete = "DELETE FROM " + Const.EMPLOYEE_TABLE + " WHERE " + "(" + Const.EMPLOYEE_USERNAME + " =?" + ")";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(delete);
            preparedStatement.setString(1, employee.getUsername());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

/*
    public void viewAllEmployee() {
        ResultSet resultSet = null;
        String query = "SELECT * FROM " + Const.EMPLOYEE_TABLE;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = getDbConnection().prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setId(resultSet.getInt("id"));
                employee.setName(resultSet.getString("Name"));
                employee.setLastName(resultSet.getString("LastName"));
                employee.setLevel(resultSet.getInt("Level"));
                employee.setWork(resultSet.getString("Work"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
*/

    //Adding Company into Database
    public void addCompany(Company company) {
        String insert = "INSERT INTO " + Const.COMPANYS_TABLE + "(" + Const.COMPANY_NAME + "," + Const.COMPANY_PLACE + ")" + "VALUES(?,?,?)";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);

            preparedStatement.setString(1, company.getName());
            preparedStatement.setString(2, company.getPlace());
            preparedStatement.setString(3, company.getCustomer());


            preparedStatement.executeUpdate();
            done();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addJobOrderNo(Company company) {
        String insert = "INSERT INTO " + Const.JOBORDERS_TABLE + "(" + Const.COMPANY_NAMEJ + "," + Const.JOBORDERNO_NO + ")" +
                "VALUES ((SELECT " + Const.COMPANY_NAME + " FROM " +
                Const.COMPANYS_TABLE + " WHERE " + Const.COMPANY_NAME + " =?),?)";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);

            preparedStatement.setString(1, company.getName());
            preparedStatement.setString(2, company.getJobOrderNo());

            preparedStatement.executeUpdate();
            done();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addOfferNo(Company company) {
        String insert = "INSERT INTO " + Const.OFFERNO_TABLE + "(" + Const.COMPANY_NAMEO + "," + Const.JOBOFFERNO_NO + ")" +
                "VALUES ((SELECT " + Const.COMPANY_NAME + " FROM " +
                Const.COMPANYS_TABLE + " WHERE " + Const.COMPANY_NAME + " =?),?)";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);

            preparedStatement.setString(1, company.getName());
            preparedStatement.setString(2, company.getOfferNo());

            preparedStatement.executeUpdate();
            done();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ObservableList allCompaniesList() {
        ObservableList<String> companies = FXCollections.observableArrayList();
        ResultSet resultSet = null;
        String query = "SELECT " + Const.COMPANY_NAME + " FROM " + Const.COMPANYS_TABLE;

        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Company company = new Company();
                company.setName(resultSet.getString("Name"));
                companies.add(company.getName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return companies;
    }


    public Company getCompany(Company company) {
        ResultSet resultSet = null;
        if (!company.getName().equals("")) {
            String query = "SELECT * FROM " + Const.COMPANYS_TABLE + " WHERE "
                    + Const.COMPANY_NAME + "=?";
            try {
                PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
                preparedStatement.setString(1, company.getName());
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    company.setName(resultSet.getString("Name"));
                    company.setCustomer(resultSet.getString("Customer"));
                    company.setPlace(resultSet.getString("Place"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("please entere your correct infos");
        }
        return company;
    }

    public ObservableList allEquipment() {
        Equipment equipment = new Equipment();
        ObservableList<String> equipments = FXCollections.observableArrayList();
        ResultSet resultSet = null;
        String query = "SELECT " + Const.EQUIPMENTS_EQUIPMENT + " FROM " + Const.EQUIPMENTS_TABLE;
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                equipment.setName(resultSet.getString("Equipment"));
                equipments.add(equipment.getName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return equipments;
    }


    public ObservableList viewAllEmployee() {
        ObservableList<Employee> employees = FXCollections.observableArrayList();
        employees.clear();
        ResultSet resultSet = null;
        String query = "SELECT * FROM " + Const.EMPLOYEE_TABLE;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = getDbConnection().prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setId(resultSet.getInt("id"));
                employee.setName(resultSet.getString("Name"));
                employee.setLastName(resultSet.getString("LastName"));
                employee.setLevel(resultSet.getInt("Level"));
                employee.setWork(resultSet.getString("Work"));
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return employees;
    }



}
