package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sample.Database.DatabaseHandler;
import sample.model.Equipment;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddEquipmentController {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXTextField addEquipmentEquipment;

    @FXML
    private JFXTextField addEquipmentPole;

    @FXML
    private JFXTextField addEquipmentMp;

    @FXML
    private ImageView deleteBack;

    @FXML
    private JFXTextField addEquipmentMag;

    @FXML
    private JFXTextField addEquipmentUv;

    @FXML
    private JFXTextField addEquipmentDistance;

    @FXML
    private JFXButton addEquipmentAdd;

    @FXML
    void initialize() {
        addEquipmentAdd.setOnAction(event -> {
            if ((addEquipmentEquipment.equals(""))||(addEquipmentDistance.equals(""))
                    ||(addEquipmentMp.equals(""))
                    ||(addEquipmentPole.equals(""))
                    ||(addEquipmentUv.equals(""))){
                Frame parent = new JFrame();
                JOptionPane.showMessageDialog(parent, "Please fill all the fields");
            }else {
                createEquipment();
            }
        });

        deleteBack.setOnMouseClicked(mouseEvent -> {
            deleteBack.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/main.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        });
    }


    private void createEquipment(){
        try {
            String name = addEquipmentEquipment.getText();
            int poledistance = Integer.parseInt(addEquipmentPole.getText());
            String mp = addEquipmentMp.getText();
            String mag = addEquipmentMag.getText();
            String uv = addEquipmentUv.getText();
            String distance = addEquipmentDistance.getText();
            Equipment equipment = new Equipment(name,poledistance,mp,mag,uv,distance);

            DatabaseHandler.addEquipment(equipment);
        }catch (NumberFormatException e){
            Frame parent = new JFrame();
            JOptionPane.showMessageDialog(parent, "Pole Distance should be a number");
        }
    }

}
