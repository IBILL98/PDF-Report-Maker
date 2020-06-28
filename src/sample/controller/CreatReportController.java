package sample.controller;


import javafx.print.*;

import javafx.scene.layout.VBox;
import javafx.scene.transform.Scale;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;


import java.awt.*;
import java.io.*;
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
import sample.Database.Const;
import sample.Database.DatabaseHandler;
import sample.model.Company;
import sample.model.Employee;
import sample.model.Equipment;

import javax.swing.*;

public class CreatReportController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private VBox vbox;


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
    private ComboBox<String> reportOfferNo;

    @FXML
    private ComboBox<String> reportJobOrderNo;

    @FXML
    private ComboBox<String> reportSurfaceCondition;

    @FXML
    private ComboBox<String> reportStageOfExamination;

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
    ObservableList result = FXCollections.observableArrayList("-","RED","OK");
    private ObservableList<String> joborders = FXCollections.observableArrayList();
    private ObservableList<String> offers = FXCollections.observableArrayList();
    private ObservableList<String> StageOfExaminationList = FXCollections.observableArrayList("-","Untreated");
    private ObservableList<String> SurfaceConditionList = FXCollections.observableArrayList("-","After Welding");





    @FXML
    void initialize() {
        for(int i = 0;i<101;i++){
            procent.add(i);
        }
        reportInspectionScope.setItems(procent);
        reportInspectionScope.getSelectionModel().selectFirst();
        reportNumberGenerator();
        reportReportDate.setText(java.time.LocalDate.now().toString());

        reportCurrentType.setItems(acdc);
        reportCurrentType.getSelectionModel().selectFirst();

        reportStageOfExamination.setItems(StageOfExaminationList);
        reportStageOfExamination.getSelectionModel().selectFirst();

        reportSurfaceCondition.setItems(SurfaceConditionList);
        reportSurfaceCondition.getSelectionModel().selectFirst();


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

        reportResult1.getSelectionModel().selectFirst();
        reportResult2.getSelectionModel().selectFirst();
        reportResult3.getSelectionModel().selectFirst();
        reportResult4.getSelectionModel().selectFirst();
        reportResult5.getSelectionModel().selectFirst();
        reportResult6.getSelectionModel().selectFirst();
        reportResult7.getSelectionModel().selectFirst();
        reportResult8.getSelectionModel().selectFirst();
        reportResult9.getSelectionModel().selectFirst();

        reportExportExcelButton.setOnAction(event -> {

            if (((!reportResult1.getValue().toString().equals("-")
                    && (reportWeld1.getText().equals("")
                    || reportTestLenght1.getText().equals("")
                    || reportWeldingProcess1.getText().equals("")
                    || reportDiameter1.getText().equals("")
                    || reportThickness1.getText().equals("")))
                    ||(reportResult1.getValue().equals("RED")&&(reportDefectType1.getText().equals("")
                    ||reportDefectLoc1.getText().equals(""))))||
                    ((!reportResult2.getValue().toString().equals("-")
                            && (reportWeld2.getText().equals("")
                            || reportTestLenght2.getText().equals("")
                            || reportWeldingProcess2.getText().equals("")
                            || reportDiameter2.getText().equals("")
                            || reportThickness2.getText().equals("")))
                            ||(reportResult2.getValue().equals("RED")&&(reportDefectType2.getText().equals("")
                            ||reportDefectLoc2.getText().equals(""))))||
                    ((!reportResult2.getValue().toString().equals("-")
                            && (reportWeld2.getText().equals("")
                            || reportTestLenght2.getText().equals("")
                            || reportWeldingProcess2.getText().equals("")
                            || reportDiameter2.getText().equals("")
                            || reportThickness2.getText().equals("")))
                            ||(reportResult2.getValue().equals("RED")&&(reportDefectType2.getText().equals("")
                            ||reportDefectLoc2.getText().equals(""))))||
                    ((!reportResult3.getValue().toString().equals("-")
                            && (reportWeld3.getText().equals("")
                            || reportTestLenght3.getText().equals("")
                            || reportWeldingProcess3.getText().equals("")
                            || reportDiameter3.getText().equals("")
                            || reportThickness3.getText().equals("")))
                            ||(reportResult3.getValue().equals("RED")&&(reportDefectType3.getText().equals("")
                            ||reportDefectLoc3.getText().equals(""))))||
                    ((!reportResult4.getValue().toString().equals("-")
                            && (reportWeld4.getText().equals("")
                            || reportTestLenght4.getText().equals("")
                            || reportWeldingProcess4.getText().equals("")
                            || reportDiameter4.getText().equals("")
                            || reportThickness4.getText().equals("")))
                            ||(reportResult4.getValue().equals("RED")&&(reportDefectType4.getText().equals("")
                            ||reportDefectLoc4.getText().equals(""))))||
                    ((!reportResult5.getValue().toString().equals("-")
                            && (reportWeld5.getText().equals("")
                            || reportTestLenght5.getText().equals("")
                            || reportWeldingProcess5.getText().equals("")
                            || reportDiameter5.getText().equals("")
                            || reportThickness5.getText().equals("")))
                            ||(reportResult5.getValue().equals("RED")&&(reportDefectType5.getText().equals("")
                            ||reportDefectLoc5.getText().equals(""))))||
                    ((!reportResult6.getValue().toString().equals("-")
                            && (reportWeld6.getText().equals("")
                            || reportTestLenght6.getText().equals("")
                            || reportWeldingProcess6.getText().equals("")
                            || reportDiameter6.getText().equals("")
                            || reportThickness6.getText().equals("")))
                            ||(reportResult6.getValue().equals("RED")&&(reportDefectType6.getText().equals("")
                            ||reportDefectLoc6.getText().equals(""))))||
                    ((!reportResult7.getValue().toString().equals("-")
                            && (reportWeld7.getText().equals("")
                            || reportTestLenght7.getText().equals("")
                            || reportWeldingProcess7.getText().equals("")
                            || reportDiameter7.getText().equals("")
                            || reportThickness7.getText().equals("")))
                            ||(reportResult7.getValue().equals("RED")&&(reportDefectType7.getText().equals("")
                            ||reportDefectLoc7.getText().equals(""))))||
                    ((!reportResult8.getValue().toString().equals("-")
                            && (reportWeld8.getText().equals("")
                            || reportTestLenght8.getText().equals("")
                            || reportWeldingProcess8.getText().equals("")
                            || reportDiameter8.getText().equals("")
                            || reportThickness8.getText().equals("")))
                            ||(reportResult8.getValue().equals("RED")&&(reportDefectType8.getText().equals("")
                            ||reportDefectLoc8.getText().equals(""))))||
                    ((!reportResult9.getValue().toString().equals("-")
                            && (reportWeld9.getText().equals("")
                            || reportTestLenght9.getText().equals("")
                            || reportWeldingProcess9.getText().equals("")
                            || reportDiameter9.getText().equals("")
                            || reportThickness9.getText().equals("")))
                            ||(reportResult9.getValue().equals("RED")&&(reportDefectType9.getText().equals("")
                            ||reportDefectLoc9.getText().equals(""))))
            ){
                Frame parent = new JFrame();
                JOptionPane.showMessageDialog(parent, "Please fill all ");
            }else {
                toxl();
            }
        });
        reportExportPDFButton.setOnAction(event -> {
            topdf(vbox);
        });
    }

    public void autoPicked(Company company ,Employee rater, Employee approver,Employee operator,Equipment equipment){

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


        reportPoleDistance.setText(equipment.getPrivatePoleDistance());
        reportEquipment.setText(equipment.getName());
        reportMPCarrierMedium.setText(equipment.getMPCarrierMedium());
        reportMagTech.setText(equipment.getMagTech());
        reportUVLightIntensity.setText(equipment.getUVLightIntensity());
        reportDistanceofLight.setText(equipment.getDistanceOfLight());
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


    public void toxl(){

        try {
            FileInputStream inputStream = new FileInputStream(new File("C:\\Users\\MONSTER\\Desktop\\Bericht2_MitKommentaren.xlsx"));
            XSSFWorkbook workbook = null;
            workbook = new XSSFWorkbook(inputStream);

            //////the first Table
            XSSFSheet sheet = workbook.getSheetAt(0);
            XSSFRow row = sheet.getRow(2);
            XSSFCell cell = row.getCell(3);
            cell.setCellValue(reportCustomer.getText());
            XSSFCell cell1 = row.getCell(19);
            cell1.setCellValue(reportInspectionProcedure.getText());
            XSSFCell cell2 = row.getCell(26);
            cell2.setCellValue(reportPage.getText());

            XSSFRow row1 = sheet.getRow(3);
            XSSFCell cell3 = row1.getCell(3);
            cell3.setCellValue(reportProjectName.getText());
            XSSFCell cell4 = row1.getCell(19);
            cell4.setCellValue(reportInspectionScope.getValue().toString() + "%");
            XSSFCell cell5 = row1.getCell(26);
            cell5.setCellValue(reportReportNo.getText());

            XSSFRow row2 = sheet.getRow(4);
            XSSFCell cell6 = row2.getCell(3);
            cell6.setCellValue(reportInspectionPlace.getText());
            XSSFCell cell7 = row2.getCell(19);
            cell7.setCellValue(reportDrawingNo.getText());
            XSSFCell cell8 = row2.getCell(26);
            cell8.setCellValue(reportReportDate.getText());

            XSSFRow row3 = sheet.getRow(5);
            XSSFCell cell9 = row3.getCell(3);
            cell9.setCellValue(reportInspectionStandart.getText());
            XSSFCell cell10 = row3.getCell(19);
            cell10.setCellValue(reportSurfaceCondition.getValue().toString());
            XSSFCell cell11 = row3.getCell(26);
            cell11.setCellValue(reportJobOrderNo.getValue());

            XSSFRow row4 = sheet.getRow(6);
            XSSFCell cell12 = row4.getCell(3);
            cell12.setCellValue(reportEvaluationStandart.getText());
            XSSFCell cell13 = row4.getCell(19);
            cell13.setCellValue(reportStageOfExamination.getValue().toString());
            XSSFCell cell14 = row4.getCell(26);
            cell14.setCellValue(reportOfferNo.getValue());



            //////the second Table
            XSSFRow row5 = sheet.getRow(8);
            XSSFCell cell15 = row5.getCell(4);
            cell15.setCellValue(reportPoleDistance.getText());
            XSSFCell cell16 = row5.getCell(16);
            cell16.setCellValue(reportExaminationArea.getText());
            XSSFCell cell17 = row5.getCell(25);
            cell17.setCellValue(reportSurfaceTemperature.getText());

            XSSFRow row6 = sheet.getRow(9);
            XSSFCell cell18 = row6.getCell(4);
            cell18.setCellValue(reportEquipment.getText());
            XSSFCell cell19 = row6.getCell(16);
            cell19.setCellValue(reportCurrentType.getValue().toString());
            XSSFCell cell20 = row6.getCell(25);
            cell20.setCellValue(reportGaussFieldStrength.getText());

            XSSFRow row7 = sheet.getRow(10);
            XSSFCell cell21 = row7.getCell(4);
            cell21.setCellValue(reportMPCarrierMedium.getText());
            XSSFCell cell22 = row7.getCell(16);
            cell22.setCellValue(reportLuxmeter.getText());

            XSSFRow row8 = sheet.getRow(11);
            XSSFCell cell24 = row8.getCell(4);
            cell24.setCellValue(reportMagTech.getText());
            XSSFCell cell25 = row8.getCell(16);
            cell25.setCellValue(reportTestMedium.getText());
            XSSFCell cell26 = row8.getCell(25);
            cell26.setCellValue(reportSurfaceCondition1.getText());

            XSSFRow row9 = sheet.getRow(12);
            XSSFCell cell27 = row9.getCell(4);
            cell27.setCellValue(reportUVLightIntensity.getText());
            XSSFCell cell28 = row9.getCell(16);
            cell28.setCellValue(reportDemagnetization.getText());
            XSSFCell cell29 = row9.getCell(25);
            cell29.setCellValue(reportIdentificationofLightEquip.getText());

            XSSFRow row10 = sheet.getRow(13);
            XSSFCell cell30 = row10.getCell(4);
            cell30.setCellValue(reportDistanceofLight.getText());
            XSSFCell cell31 = row10.getCell(16);
            cell31.setCellValue(reportHeatTreatment.getText());
            XSSFCell cell32 = row10.getCell(25);
            cell32.setCellValue(reportLiftingTestDateNumber.getText());

            XSSFRow rowpic = sheet.getRow(14);
            XSSFCell cellpic1 = rowpic.getCell(4);
            XSSFCell cellpic2 = rowpic.getCell(7);

            if (reportButtWeld.isSelected()){
                cellpic1.setCellValue(true);
            }
            if (reportFilletWeld.isSelected()){
                cellpic2.setCellValue(true);
            }



//////////////////////////////////////////////////////////////////////////////////////
            XSSFRow row11 = sheet.getRow(19);
            XSSFCell cell33 = row11.getCell(7);
            cell33.setCellValue(reportStandardDeviations.getText());
//////////////////////////////////////////////////////////////////////////////////////

            XSSFRow row12 = sheet.getRow(20);
            XSSFCell cell36 = row12.getCell(7);
            cell36.setCellValue(reportInspectionDates.getText());
//////////////////////////////////////////////////////////////////////////////////////
            XSSFRow row13 = sheet.getRow(21);
            XSSFCell cell39 = row13.getCell(7);
            cell39.setCellValue(reportDescriptionandAttachments.getText());
//////////////////////////////////////////////////////////////////////////////////////




//////////////////////////////////////////////////////////////////////////////////////


            XSSFRow row14 = sheet.getRow(24);
            XSSFCell cell42 = row14.getCell(1);
            cell42.setCellValue(reportWeld1.getText());
            XSSFCell cell43 = row14.getCell(8);
            cell43.setCellValue(reportTestLenght1.getText());
            XSSFCell cell44 = row14.getCell(11);
            cell44.setCellValue(reportWeldingProcess1.getText());
            XSSFCell cell45 = row14.getCell(17);
            cell45.setCellValue(reportThickness1.getText());
            XSSFCell cell46 = row14.getCell(18);
            cell46.setCellValue(reportDiameter1.getText());
            XSSFCell cell47 = row14.getCell(22);
            cell47.setCellValue(reportDefectType1.getText());
            XSSFCell cell48 = row14.getCell(24);
            cell48.setCellValue(reportDefectLoc1.getText());
            XSSFCell cell49 = row14.getCell(27);
            cell49.setCellValue(reportResult1.getValue().toString());


            XSSFRow row15 = sheet.getRow(25);
            XSSFCell cell50 = row15.getCell(1);
            cell50.setCellValue(reportWeld2.getText());
            XSSFCell cell51 = row15.getCell(8);
            cell51.setCellValue(reportTestLenght2.getText());
            XSSFCell cell52 = row15.getCell(11);
            cell52.setCellValue(reportWeldingProcess2.getText());
            XSSFCell cell53 = row15.getCell(17);
            cell53.setCellValue(reportThickness2.getText());
            XSSFCell cell54 = row15.getCell(18);
            cell54.setCellValue(reportDiameter2.getText());
            XSSFCell cell55 = row15.getCell(22);
            cell55.setCellValue(reportDefectType2.getText());
            XSSFCell cell56 = row15.getCell(24);
            cell56.setCellValue(reportDefectLoc2.getText());
            XSSFCell cell57 = row15.getCell(27);
            cell57.setCellValue(reportResult2.getValue().toString());


            XSSFRow row16 = sheet.getRow(26);
            XSSFCell cell58 = row16.getCell(1);
            cell58.setCellValue(reportWeld3.getText());
            XSSFCell cell59 = row16.getCell(8);
            cell59.setCellValue(reportTestLenght3.getText());
            XSSFCell cell60 = row16.getCell(11);
            cell60.setCellValue(reportWeldingProcess3.getText());
            XSSFCell cell61 = row16.getCell(17);
            cell61.setCellValue(reportThickness3.getText());
            XSSFCell cell62 = row16.getCell(18);
            cell62.setCellValue(reportDiameter3.getText());
            XSSFCell cell63 = row16.getCell(22);
            cell63.setCellValue(reportDefectType3.getText());
            XSSFCell cell64 = row16.getCell(24);
            cell64.setCellValue(reportDefectLoc3.getText());
            XSSFCell cell65 = row16.getCell(27);
            cell65.setCellValue(reportResult3.getValue().toString());



            XSSFRow row17 = sheet.getRow(27);
            XSSFCell cell66 = row17.getCell(1);
            cell66.setCellValue(reportWeld4.getText());
            XSSFCell cell67 = row17.getCell(8);
            cell67.setCellValue(reportTestLenght4.getText());
            XSSFCell cell68 = row17.getCell(11);
            cell68.setCellValue(reportWeldingProcess4.getText());
            XSSFCell cell69 = row17.getCell(17);
            cell69.setCellValue(reportThickness4.getText());
            XSSFCell cell70 = row17.getCell(18);
            cell70.setCellValue(reportDiameter4.getText());
            XSSFCell cell71 = row17.getCell(22);
            cell71.setCellValue(reportDefectType4.getText());
            XSSFCell cell72 = row17.getCell(24);
            cell72.setCellValue(reportDefectLoc4.getText());
            XSSFCell cell73 = row17.getCell(27);
            cell73.setCellValue(reportResult4.getValue().toString());



            XSSFRow row18 = sheet.getRow(28);
            XSSFCell cell74 = row18.getCell(1);
            cell74.setCellValue(reportWeld5.getText());
            XSSFCell cell75 = row18.getCell(8);
            cell75.setCellValue(reportTestLenght5.getText());
            XSSFCell cell76 = row18.getCell(11);
            cell76.setCellValue(reportWeldingProcess5.getText());
            XSSFCell cell77 = row18.getCell(17);
            cell77.setCellValue(reportThickness5.getText());
            XSSFCell cell78 = row18.getCell(18);
            cell78.setCellValue(reportDiameter5.getText());
            XSSFCell cell79 = row18.getCell(22);
            cell79.setCellValue(reportDefectType5.getText());
            XSSFCell cell80 = row18.getCell(24);
            cell80.setCellValue(reportDefectLoc5.getText());
            XSSFCell cell81 = row18.getCell(27);
            cell81.setCellValue(reportResult5.getValue().toString());



            XSSFRow row19 = sheet.getRow(29);
            XSSFCell cell82 = row19.getCell(1);
            cell82.setCellValue(reportWeld6.getText());
            XSSFCell cell83 = row19.getCell(8);
            cell83.setCellValue(reportTestLenght6.getText());
            XSSFCell cell84 = row19.getCell(11);
            cell84.setCellValue(reportWeldingProcess6.getText());
            XSSFCell cell85 = row19.getCell(17);
            cell85.setCellValue(reportThickness6.getText());
            XSSFCell cell86 = row19.getCell(18);
            cell86.setCellValue(reportDiameter6.getText());
            XSSFCell cell87 = row19.getCell(22);
            cell87.setCellValue(reportDefectType6.getText());
            XSSFCell cell88 = row19.getCell(24);
            cell88.setCellValue(reportDefectLoc6.getText());
            XSSFCell cell89 = row19.getCell(27);
            cell89.setCellValue(reportResult6.getValue().toString());



            XSSFRow row20 = sheet.getRow(30);
            XSSFCell cell90 = row20.getCell(1);
            cell90.setCellValue(reportWeld7.getText());
            XSSFCell cell91 = row20.getCell(8);
            cell91.setCellValue(reportTestLenght7.getText());
            XSSFCell cell92 = row20.getCell(11);
            cell92.setCellValue(reportWeldingProcess7.getText());
            XSSFCell cell93 = row20.getCell(17);
            cell93.setCellValue(reportThickness7.getText());
            XSSFCell cell94 = row20.getCell(18);
            cell94.setCellValue(reportDiameter7.getText());
            XSSFCell cell95 = row20.getCell(22);
            cell95.setCellValue(reportDefectType7.getText());
            XSSFCell cell96 = row20.getCell(24);
            cell96.setCellValue(reportDefectLoc7.getText());
            XSSFCell cell97 = row20.getCell(27);
            cell97.setCellValue(reportResult7.getValue().toString());



            XSSFRow row21 = sheet.getRow(31);
            XSSFCell cell98 = row21.getCell(1);
            cell98.setCellValue(reportWeld8.getText());
            XSSFCell cell99 = row21.getCell(8);
            cell99.setCellValue(reportTestLenght8.getText());
            XSSFCell cell100 = row21.getCell(11);
            cell100.setCellValue(reportWeldingProcess8.getText());
            XSSFCell cell101 = row21.getCell(17);
            cell101.setCellValue(reportThickness8.getText());
            XSSFCell cell102 = row21.getCell(18);
            cell102.setCellValue(reportDiameter8.getText());
            XSSFCell cell103 = row21.getCell(22);
            cell103.setCellValue(reportDefectType8.getText());
            XSSFCell cell104 = row21.getCell(24);
            cell104.setCellValue(reportDefectLoc8.getText());
            XSSFCell cell105 = row21.getCell(27);
            cell105.setCellValue(reportResult8.getValue().toString());



            XSSFRow row22 = sheet.getRow(32);
            XSSFCell cell106 = row22.getCell(1);
            cell106.setCellValue(reportWeld9.getText());
            XSSFCell cell107 = row22.getCell(8);
            cell107.setCellValue(reportTestLenght9.getText());
            XSSFCell cell108 = row22.getCell(11);
            cell108.setCellValue(reportWeldingProcess9.getText());
            XSSFCell cell109 = row22.getCell(17);
            cell109.setCellValue(reportThickness9.getText());
            XSSFCell cell110 = row22.getCell(18);
            cell110.setCellValue(reportDiameter9.getText());
            XSSFCell cell111 = row22.getCell(22);
            cell111.setCellValue(reportDefectType9.getText());
            XSSFCell cell112 = row22.getCell(24);
            cell112.setCellValue(reportDefectLoc9.getText());
            XSSFCell cell113 = row22.getCell(27);
            cell113.setCellValue(reportResult9.getValue().toString());


            XSSFRow row23 = sheet.getRow(39);
            XSSFCell cell114 = row23.getCell(5);
            cell114.setCellValue(reportOperatorName.getText());
            XSSFCell cell115 = row23.getCell(15);
            cell115.setCellValue(reportRaterName.getText());
            XSSFCell cell116 = row23.getCell(20);
            cell116.setCellValue(reportApproverName.getText());


            XSSFRow row24 = sheet.getRow(40);
            XSSFCell cell117 = row24.getCell(5);
            cell117.setCellValue(reportOperatorLevel.getText());
            XSSFCell cell118 = row24.getCell(15);
            cell118.setCellValue(reportRaterLevel.getText());
            XSSFCell cell119 = row24.getCell(20);
            cell119.setCellValue(reportApproverLevel.getText());


            XSSFRow row25 = sheet.getRow(41);
            XSSFCell cell120 = row25.getCell(5);
            cell120.setCellValue(reportOperatorDate.getText());
            XSSFCell cell121 = row25.getCell(15);
            cell121.setCellValue(reportRaterDate.getText());
            XSSFCell cell122 = row25.getCell(20);
            cell122.setCellValue(reportApproverDate.getText());

  // important to save the changes we made to the new file

            workbook.write(new FileOutputStream("C:\\Users\\MONSTER\\Desktop\\"+ reportReportNo.getText()+".xlsx"));
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void topdf(VBox vbox) {
        Printer printer = Printer.getDefaultPrinter();
        PageLayout pageLayout = printer.createPageLayout(Paper.A4, PageOrientation.PORTRAIT, Printer.MarginType.HARDWARE_MINIMUM);
        PrinterJob job = PrinterJob.createPrinterJob();
        double scaleX = pageLayout.getPrintableWidth() / vbox.getBoundsInParent().getWidth();
        double scaleY = pageLayout.getPrintableHeight() / vbox.getBoundsInParent().getHeight();
        Scale scale = new Scale(scaleX, scaleY);
        vbox.getTransforms().add(scale);
        if (job != null) {
            boolean success = job.printPage(pageLayout, vbox);
            if (success) {
                job.endJob();
            }
        }
        vbox.getTransforms().remove(scale);
    }
}

