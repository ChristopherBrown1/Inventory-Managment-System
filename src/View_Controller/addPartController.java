/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Model.InHouse;
import Model.OutSourced;
import Model.Part;
import static View_Controller.MainScreenController.inv;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author brown
 */
public class addPartController implements Initializable {

    static public int partID = 1000;

    
    
    @FXML
    private Button Cancel;
    @FXML
    private RadioButton InHouse;
    @FXML
    private RadioButton Outsourced;
    @FXML
    private Button Save;
    @FXML
    private TextField PartId;
    @FXML
    private TextField Min;
    @FXML
    private TextField PartName;
    @FXML
    private TextField Inv;
    @FXML
    private TextField priceCost;
    @FXML
    private TextField Max;
    @FXML
    private TextField CompanyName;
    @FXML
    private GridPane grid1Handler;
    @FXML
    private ToggleGroup Group_InHouse_Outsourced;
    @FXML
    private Label CompanyNameLabel;
    @FXML
    private Label MachineIDLabel;
    
    private Part savedPart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        PartId.setText(String.valueOf(partID));
    }    


    @FXML
    private void inHouseHandler(ActionEvent event) {
        // Makes machineID feild available (visible)
        MachineIDLabel.setVisible(true);
        CompanyName.setPromptText("Machine ID");
        grid1Handler.getChildren().removeAll(MachineIDLabel, CompanyNameLabel);
        grid1Handler.add(MachineIDLabel, 0, 0, 1, 1);
        
    }

    @FXML
    private void outsourcedHandler(ActionEvent event) {
        // Makes companyName field avaliable (visible)
        CompanyNameLabel.setVisible(true);
        CompanyName.setPromptText("Comp Nm");
        grid1Handler.getChildren().removeAll(MachineIDLabel, CompanyNameLabel);
        grid1Handler.add(CompanyNameLabel, 0, 0, 1, 1);
        
    }


    @FXML
    private void partIDHandler(ActionEvent event) {
    }

    @FXML
    private void minHandler(ActionEvent event) {
    }

    @FXML
    private void partNameHandler(ActionEvent event) {
    }

    @FXML
    private void invHandler(ActionEvent event) {
    }

    @FXML
    private void priceCostHandler(ActionEvent event) {
    }

    @FXML
    private void maxHandler(ActionEvent event) {
    }

    @FXML
    private void companyNameHandler(ActionEvent event) {
    }

    @FXML
    private void cancelHandler(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel");
        alert.setHeaderText("Cancel part creation...");
        alert.setContentText("Would you like to continue?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            // ... user chose OK               
        
        
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/View_Controller/mainScreen.fxml")); // loads our main screen.
                View_Controller.MainScreenController controller = new View_Controller.MainScreenController(inv);
                loader.setController(controller);
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();   



            System.out.println("You clicked cancel");
            }
            catch (IOException e){
                       System.out.println("Cancel exception - you need to fix this...");

            }
        }
    }

    @FXML
    private void saveHandler(MouseEvent event) throws IOException {
        int min = Integer.parseInt(Min.getText());
        String partName = PartName.getText();
        int inventory = Integer.parseInt(Inv.getText());
        double cost = Double.parseDouble(priceCost.getText());
        int max = Integer.parseInt(Max.getText());
        String compName = CompanyName.getText();
        
        
        if (min < max) {
        
            if(InHouse.isSelected()) {
                int machineNumber = Integer.parseInt(compName);
                savedPart = new InHouse(partID, partName, cost, inventory, min, max, machineNumber);
                MainScreenController.inv.addPart(savedPart);
                partID = partID+1;


            }
            else if (Outsourced.isSelected()) {
                savedPart = new OutSourced(partID, partName, cost, inventory, min, max, compName);
                MainScreenController.inv.addPart(savedPart);
                partID = partID+1;

            }

            else {
                // Print an error message to user "All fields must be used."
            }


            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/View_Controller/mainScreen.fxml")); // loads our main screen.
                View_Controller.MainScreenController controller = new View_Controller.MainScreenController(inv);
                loader.setController(controller);
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();   


            }
            catch (IOException e){

            }
        }
        
        else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Value Error");
            alert.setContentText("Min must be less than Max!");

            alert.showAndWait();
        }

    }
    
}
