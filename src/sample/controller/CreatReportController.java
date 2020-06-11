package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Database.Const;
import sample.Database.DatabaseHandler;
import sample.model.Company;
import sample.model.Employee;
import sample.model.Equipment;

public class CreatReportController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField reportCustomer;

    @FXML
    private TextField reportProjectName;

    @FXML
    private TextField reportInspectionPlace;

    @FXML
    private TextField reportInspectionStandart;

    @FXML
    private TextField reportEvaluationStandart;

    @FXML
    private TextField reportInspectionProcedure;

    @FXML
    private TextField reportDrawingNo;

    @FXML
    private TextField reportPage;

    @FXML
    private TextField reportReportNo;

    @FXML
    private TextField reportReportDate;

    @FXML
    private JFXComboBox<?> reportInspectionScope;

    @FXML
    private TextField reportSurfaceCondition;

    @FXML
    private TextField reportStageOfExamination;

    @FXML
    private ComboBox<String> reportOfferNo;

    @FXML
    private ComboBox<String> reportJobOrderNo;

    @FXML
    private TextField reportPoleDistance;

    @FXML
    private TextField reportEquipment;

    @FXML
    private TextField reportMPCarrierMedium;

    @FXML
    private TextField reportMagTech;

    @FXML
    private TextField reportUVLightIntensity;

    @FXML
    private TextField reportDistanceofLight;

    @FXML
    private TextField reportExaminationArea;

    @FXML
    private TextField reportLuxmeter;

    @FXML
    private TextField reportTestMedium;

    @FXML
    private TextField reportDemagnetization;

    @FXML
    private TextField reportHeatTreatment;

    @FXML
    private JFXComboBox<?> reportCurrentType;

    @FXML
    private TextField reportSurfaceTemperature;

    @FXML
    private TextField reportGaussFieldStrength;

    @FXML
    private TextField reportSurfaceCondition1;

    @FXML
    private TextField reportIdentificationofLightEquip;

    @FXML
    private TextField reportLiftingTestDateNumber;

    @FXML
    private CheckBox reportButtWeld;

    @FXML
    private CheckBox reportFilletWeld;

    @FXML
    private TextField reportStandardDeviations;

    @FXML
    private TextField reportInspectionDates;

    @FXML
    private TextField reportDescriptionandAttachments;

    @FXML
    private TextField reportWeld1;

    @FXML
    private TextField reportTestLenght1;

    @FXML
    private TextField reportWeldingProcess1;

    @FXML
    private TextField reportThickness1;

    @FXML
    private TextField reportDiameter1;

    @FXML
    private TextField reportDefectType1;

    @FXML
    private TextField reportDefectLoc1;

    @FXML
    private TextField reportWeld2;

    @FXML
    private TextField reportTestLenght2;

    @FXML
    private TextField reportWeldingProcess2;

    @FXML
    private TextField reportThickness2;

    @FXML
    private TextField reportDiameter2;

    @FXML
    private TextField reportDefectType2;

    @FXML
    private TextField reportDefectLoc2;

    @FXML
    private TextField reportWeld3;

    @FXML
    private TextField reportTestLenght3;

    @FXML
    private TextField reportWeldingProcess3;

    @FXML
    private TextField reportThickness3;

    @FXML
    private TextField reportDiameter3;

    @FXML
    private TextField reportDefectType3;

    @FXML
    private TextField reportDefectLoc3;

    @FXML
    private TextField reportWeld4;

    @FXML
    private TextField reportTestLenght4;

    @FXML
    private TextField reportWeldingProcess4;

    @FXML
    private TextField reportThickness4;

    @FXML
    private TextField reportDiameter4;

    @FXML
    private TextField reportDefectType4;

    @FXML
    private TextField reportDefectLoc4;

    @FXML
    private TextField reportWeld5;

    @FXML
    private TextField reportTestLenght5;

    @FXML
    private TextField reportWeldingProcess5;

    @FXML
    private TextField reportThickness5;

    @FXML
    private TextField reportDiameter5;

    @FXML
    private TextField reportDefectType5;

    @FXML
    private TextField reportDefectLoc5;

    @FXML
    private TextField reportWeld6;

    @FXML
    private TextField reportTestLenght6;

    @FXML
    private TextField reportWeldingProcess6;

    @FXML
    private TextField reportThickness6;

    @FXML
    private TextField reportDiameter6;

    @FXML
    private TextField reportDefectType6;

    @FXML
    private TextField reportDefectLoc6;

    @FXML
    private TextField reportWeld7;

    @FXML
    private TextField reportTestLenght7;

    @FXML
    private TextField reportWeldingProcess7;

    @FXML
    private TextField reportThickness7;

    @FXML
    private TextField reportDiameter7;

    @FXML
    private TextField reportDefectType7;

    @FXML
    private TextField reportDefectLoc7;

    @FXML
    private TextField reportWeld8;

    @FXML
    private TextField reportTestLenght8;

    @FXML
    private TextField reportWeldingProcess8;

    @FXML
    private TextField reportThickness8;

    @FXML
    private TextField reportDiameter8;

    @FXML
    private TextField reportDefectType8;

    @FXML
    private TextField reportDefectLoc8;

    @FXML
    private TextField reportWeld9;

    @FXML
    private TextField reportTestLenght9;

    @FXML
    private TextField reportWeldingProcess9;

    @FXML
    private TextField reportThickness9;

    @FXML
    private TextField reportDiameter9;

    @FXML
    private TextField reportDefectType9;

    @FXML
    private TextField reportDefectLoc9;

    @FXML
    private ComboBox<?> reportResult1;

    @FXML
    private ComboBox<?> reportResult2;

    @FXML
    private ComboBox<?> reportResult3;

    @FXML
    private ComboBox<?> reportResult4;

    @FXML
    private ComboBox<?> reportResult5;

    @FXML
    private ComboBox<?> reportResult6;

    @FXML
    private ComboBox<?> reportResult7;

    @FXML
    private ComboBox<?> reportResult8;

    @FXML
    private ComboBox<?> reportResult9;

    @FXML
    private TextField reportOperatorName;

    @FXML
    private TextField reportOperatorLevel;

    @FXML
    private TextField reportOperatorDate;

    @FXML
    private TextField reportOperatorSignature;

    @FXML
    private TextField reportRaterName;

    @FXML
    private TextField reportRaterLevel;

    @FXML
    private TextField reportRaterDate;

    @FXML
    private TextField reportRaterSignature;

    @FXML
    private TextField reportApproverName;

    @FXML
    private TextField reportApproverLevel;

    @FXML
    private TextField reportApproverDate;

    @FXML
    private TextField reportApproverSignature;

    @FXML
    private JFXButton reportExportExcelButton;

    @FXML
    private JFXButton reportExportPDFButton;

    DatabaseHandler databaseHandler = new DatabaseHandler();
    ObservableList procent = FXCollections.observableArrayList();
    ObservableList acdc = FXCollections.observableArrayList("AC","DC");
    ObservableList result = FXCollections.observableArrayList("RED","OK");
    private ObservableList<String> joborders = FXCollections.observableArrayList();
    private ObservableList<String> offers = FXCollections.observableArrayList();



    Equipment equipment = new Equipment();



    @FXML
    void initialize() {
        for(int i = 0;i<101;i++){
            procent.add(i);
        }
        reportInspectionScope.setItems(procent);
        reportNumberGenerator();
        reportReportDate.setText(java.time.LocalDate.now().toString());

        reportCurrentType.setItems(acdc);
        reportCurrentType.getSelectionModel().selectFirst();

        reportInspectionDates.setText(java.time.LocalDate.now().toString());

        reportResult1.setItems(result);
        reportResult2.setItems(result);
        reportResult3.setItems(result);
        reportResult4.setItems(result);
        reportResult5.setItems(result);
        reportResult6.setItems(result);
        reportResult7.setItems(result);
        reportResult8.setItems(result);
        reportResult9.setItems(result);

        viewAllOffers();
        viewAllJobOrders();
    }

    public void autoPicked(Company company ,Employee rater, Employee approver,Employee operator){

        reportCustomer.setText(company.getCustomer());
        reportInspectionPlace.setText(company.getPlace());

        reportOperatorName.setText(operator.getName()+" "+operator.getLastName());
        reportOperatorLevel.setText(String.valueOf(operator.getLevel()));
        reportOperatorDate.setText((java.time.LocalDate.now().toString()));


        reportRaterName.setText(rater.getName()+" "+rater.getLastName());
        reportRaterLevel.setText(String.valueOf(rater.getLevel()));
        reportRaterDate.setText((java.time.LocalDate.now().toString()));


        reportApproverName.setText(approver.getName()+" "+approver.getLastName());
        reportApproverLevel.setText(String.valueOf(approver.getLevel()));
        reportApproverDate.setText((java.time.LocalDate.now().toString()));



        offers.clear();
        ResultSet resultSet = null;
        String query = "SELECT offerno.Id FROM offerno WHERE "+ Const.COMPANY_NAMEO +"=?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = databaseHandler.getDbConnection().prepareStatement(query);
            preparedStatement.setString(1, company.getName());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                offers.add(resultSet.getString("Id"));
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        reportOfferNo.setItems(offers);



        joborders.clear();
        ResultSet resultSet1 = null;
        String query1 = "SELECT  joborderno.Id FROM joborderno WHERE "+ Const.COMPANY_NAMEJ +"=?";
        PreparedStatement preparedStatement1 = null;
        try {
            preparedStatement1 = databaseHandler.getDbConnection().prepareStatement(query1);
            preparedStatement1.setString(1, company.getName());
            resultSet1 = preparedStatement1.executeQuery();
            while (resultSet1.next()) {
                joborders.add(resultSet1.getString("Id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        reportJobOrderNo.setItems(joborders);
    }



    public void reportNumberGenerator(){
        String reportnumber = "" ;
        for(int i=0;i<10;i++){
            Random random = new Random();
            String n = String.valueOf(random.nextInt(9));
            reportnumber = reportnumber + n ;
        }
        reportReportNo.setText(reportnumber);
    }

    public void viewAllOffers() {

    }


    public void viewAllJobOrders() {

    }











}
