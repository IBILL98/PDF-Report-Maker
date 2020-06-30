package sample.Database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.model.Admin;
import sample.model.Company;
import sample.model.Employee;
import sample.model.Equipment;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.time.LocalDate;

public class DatabaseHandler extends Configs {

    private static Connection dbConnection;

    public static void connect() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName + "?useSSL=false";
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
    }

    public static Connection getDbConnection() {
        return dbConnection;
    }

    //Adding employee into Database
    public static void addEmployee(Employee employee) {
        String insert = "INSERT INTO " + Const.EMPLOYEE_TABLE + "(" + Const.EMPLOYEE_NAME + "," + Const.EMPLOYEE_LASTNAME
                + "," + Const.EMPLOYEE_USERNAME + ","
                + Const.EMPLOYEE_LEVEL + "," + Const.EMPLOYEE_PASSWORD + "," + Const.EMPLOYEE_WORK + "," + Const.EMPLOYEE_CDATE + ")" + "VALUES(?,?,?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement = DatabaseHandler.getDbConnection().prepareStatement(insert);

            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setString(3, employee.getUsername());
            preparedStatement.setString(4, String.valueOf(employee.getLevel()));
            preparedStatement.setString(5, employee.getPassword());
            preparedStatement.setString(6, employee.getWork());
            preparedStatement.setDate(7, Date.valueOf(employee.getCdate()));

            preparedStatement.executeUpdate();

            if (employee.getLevel() != 100000) {
                done();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            Frame parent = new JFrame();
            JOptionPane.showMessageDialog(parent, "this User Name is already used");
        }
    }

    //return a resultset of an employee with a specific id
    public static ResultSet getEmployeeById(Employee employee) {
        ResultSet resultSet = null;
        String query = "SELECT * FROM " + Const.EMPLOYEE_TABLE + " WHERE "
                + Const.EMPLOYEE_ID + "=?";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
            preparedStatement.setInt(1, employee.getId());
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }


    //Read Employee From the Database for login window
    public static ResultSet getEmployee(Employee employee) {
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
            }
        } else {
            Frame parent = new JFrame();
            JOptionPane.showMessageDialog(parent, "Please fill your correct Username and Password");
        }
        return resultSet;
    }

    //Read Admin From The Database for login window
    public static ResultSet getAdmin(Admin admin) {
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
            }

        } else {
            Frame parent = new JFrame();
            JOptionPane.showMessageDialog(parent, "Please fill your correct Username and Password");
        }
        return resultSet;
    }


    ///adding a new Admin to the Database
    public static void addAdmin(Admin admin) {

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
        }
    }

    public static void done() {
        Frame parent = new JFrame();
        JOptionPane.showMessageDialog(parent, "Done");
    }


    //Delete


    //Adding Company into Database
    public void addCompany(Company company) {
        String insert = "INSERT INTO " + Const.COMPANYS_TABLE + "(" + Const.COMPANY_NAME + "," + Const.COMPANY_PLACE + "," + Const.COMPANY_CUSTOMER + ")" + "VALUES(?,?,?)";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);

            preparedStatement.setString(1, company.getName());
            preparedStatement.setString(2, company.getPlace());
            preparedStatement.setString(3, company.getCustomer());


            preparedStatement.executeUpdate();
            done();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //adding Job order number for existing company
    public static void addJobOrderNo(Company company) {
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
        }
    }

    //adding offer number for existing company
    public static void addOfferNo(Company company) {
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
        }
    }

    //return a list full of companies Name to select from
    public static ObservableList allCompaniesList() {
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
        }
        return companies;
    }

    //return a company with this Name
    public static Company getCompany(Company company) {
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
            }
        } else {
            System.out.println("please entere your correct infos");
        }
        return company;
    }

    //return an equipment with this Name
    public static ResultSet getEquipment(Equipment equipment) {
        ResultSet resultSet = null;
        if (!equipment.getName().equals("")) {
            String query = "SELECT * FROM " + Const.EQUIPMENTS_TABLE + " WHERE "
                    + Const.EQUIPMENTS_EQUIPMENT + "=?";
            try {
                PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
                preparedStatement.setString(1, equipment.getName());
                resultSet = preparedStatement.executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("please entere your correct infos");
        }
        return resultSet;
    }


    //return a list full of equipments Name to select from
    public static ObservableList allEquipment() {
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
        }
        return equipments;
    }


    //return a list for the search employee
    public static ObservableList searchemployeeList(String searchWord) {
        ObservableList<Employee> employees = FXCollections.observableArrayList();
        if (!searchWord.equals("")) {
            employees.clear();
            ResultSet resultSet = null;
            PreparedStatement preparedStatement = null;
            try {
                String query = "SELECT * FROM " + Const.EMPLOYEE_TABLE + " WHERE " + "(" + Const.EMPLOYEE_NAME + " LIKE ?" + ") OR ("
                        + Const.EMPLOYEE_LASTNAME + " LIKE ?) OR (" + Const.EMPLOYEE_ID + " LIKE ?)";
                preparedStatement = getDbConnection().prepareStatement(query);
                preparedStatement.setString(1, searchWord);
                preparedStatement.setString(2, searchWord);
                preparedStatement.setString(3, searchWord);

                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Employee employee = new Employee();
                    employee.setId(resultSet.getInt("id"));
                    employee.setName(resultSet.getString("Name"));
                    employee.setLastName(resultSet.getString("LastName"));
                    employee.setLevel(resultSet.getInt("Level"));
                    employee.setWork(resultSet.getString("Work"));
                    employee.setCdate(resultSet.getDate("Cdate").toLocalDate());
                    employees.add(employee);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            viewAllEmployee();
        }
        return employees;
    }

    private static ObservableList<Employee> employees = FXCollections.observableArrayList();

    //return a list with all employees name for the Table in employee window
    public static ObservableList viewAllEmployee() {
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
                employee.setCdate(resultSet.getDate("CertificateDate").toLocalDate());
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }


    public static void renewCD(Employee employee) {
        LocalDate localDate = employee.getCdate().plusYears(1);
        String update = "UPDATE " + Const.EMPLOYEE_TABLE + " SET " + Const.EMPLOYEE_CDATE + "=?" + " WHERE " + Const.EMPLOYEE_ID + "=?";
        try {
            PreparedStatement preparedStatement = DatabaseHandler.getDbConnection().prepareStatement(update);
            preparedStatement.setDate(1, Date.valueOf(localDate));
            preparedStatement.setString(2, String.valueOf(employee.getId()));
            preparedStatement.executeUpdate();
            Frame parent = new JFrame();
            JOptionPane.showMessageDialog(parent, "Done");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int getindex(Employee employee) {
        int index = employees.indexOf(employee);
        return index;
    }

    public static void deleteEmployee(Employee employee) {
        String delete = "DELETE FROM " + Const.EMPLOYEE_TABLE + " WHERE " + "(" + Const.EMPLOYEE_ID + " =?" + ")";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(delete);
            preparedStatement.setInt(1, employee.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void editWork(Employee employee) {

        String update = "UPDATE " + Const.EMPLOYEE_TABLE + " SET " + Const.EMPLOYEE_WORK + "=?" + " WHERE " + Const.EMPLOYEE_ID + "=?";
        try {
            PreparedStatement preparedStatement = DatabaseHandler.getDbConnection().prepareStatement(update);
            preparedStatement.setString(1, employee.getWork());
            preparedStatement.setString(2, String.valueOf(employee.getId()));
            preparedStatement.executeUpdate();
            Frame parent = new JFrame();
            JOptionPane.showMessageDialog(parent, "Done");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void editLevel(Employee employee) {
        String update = "UPDATE " + Const.EMPLOYEE_TABLE + " SET " + Const.EMPLOYEE_LEVEL + "=?" + " WHERE " + Const.EMPLOYEE_ID + "=?";
        try {
            PreparedStatement preparedStatement = DatabaseHandler.getDbConnection().prepareStatement(update);
            preparedStatement.setString(1, String.valueOf(employee.getLevel()));
            preparedStatement.setString(2, String.valueOf(employee.getId()));
            preparedStatement.executeUpdate();
            Frame parent = new JFrame();
            JOptionPane.showMessageDialog(parent, "Done");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void editLastName(Employee employee){
        String update = "UPDATE " + Const.EMPLOYEE_TABLE + " SET " + Const.EMPLOYEE_LASTNAME + "=?" + " WHERE " + Const.EMPLOYEE_ID + "=?";
        try {
            PreparedStatement preparedStatement = DatabaseHandler.getDbConnection().prepareStatement(update);
            preparedStatement.setString(1, employee.getLastName());
            preparedStatement.setString(2, String.valueOf(employee.getId()));
            preparedStatement.executeUpdate();
            Frame parent = new JFrame();
            JOptionPane.showMessageDialog(parent, "Done");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void editName(Employee employee){
        String update = "UPDATE " + Const.EMPLOYEE_TABLE + " SET " + Const.EMPLOYEE_NAME + "=?" + " WHERE " + Const.EMPLOYEE_ID + "=?";
        try {
            PreparedStatement preparedStatement = DatabaseHandler.getDbConnection().prepareStatement(update);
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, String.valueOf(employee.getId()));
            preparedStatement.executeUpdate();
            Frame parent = new JFrame();
            JOptionPane.showMessageDialog(parent, "Done");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
