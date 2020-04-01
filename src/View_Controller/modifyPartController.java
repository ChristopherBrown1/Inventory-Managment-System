/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Model.InHouse;
import Model.Inventory;
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
public class modifyPartController implements Initializable {

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
    private Label MachineIDLabel;
    @FXML
    private Label CompanyNameLabel;
    @FXML
    private ToggleGroup Group_InHouse_Outsourced;
    @FXML
    private GridPane grid1Handler;
    
    private Inventory inv;
    
    private Part selected;

    public modifyPartController(Inventory inv, Part part) {
        this.inv = inv;
        this.selected = part;
    }
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        PartId.setText(String.valueOf(selected.getID()));
        Min.setText(String.valueOf(selected.getMin()));
        PartName.setText(String.valueOf(selected.getName()));
        Inv.setText(String.valueOf(selected.getStock()));
        priceCost.setText(String.valueOf(selected.getPrice()));
        Max.setText(String.valueOf(selected.getMax()));

        
// Cast selected from Part to OutSourced part
        if(selected instanceof OutSourced) {
            OutSourced selectedPart = (OutSourced) selected;
            CompanyName.setText(String.valueOf(selectedPart.getCompanyName()));
            Outsourced.setSelected(true);
            CompanyNameLabel.setVisible(true);
            CompanyName.setPromptText("Comp Nm");
            grid1Handler.getChildren().removeAll(MachineIDLabel, CompanyNameLabel);
            grid1Handler.add(CompanyNameLabel, 0, 0, 1, 1);
        }

//        
        if(selected instanceof InHouse) {
            InHouse selectedPart = (InHouse) selected;
            CompanyName.setText(String.valueOf(selectedPart.getMachineID()));
        }

    }    


    @FXML
    private void inHouseHandler(ActionEvent event) {
        // MachineID becomes available when inHouse is selected
        // Makes machineID feild available (visible)
        MachineIDLabel.setVisible(true);
        CompanyName.setPromptText("Machine ID");
        grid1Handler.getChildren().removeAll(MachineIDLabel, CompanyNameLabel);
        grid1Handler.add(MachineIDLabel, 0, 0, 1, 1);
        

    }

    @FXML
    private void outsourcedHandler(ActionEvent event) {
        // When outsourced is selected companyName feild becomses available
        CompanyNameLabel.setVisible(true);
        CompanyName.setPromptText("Company Name");
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
    private void cancelHandler(MouseEvent event) throws IOException {
        
    
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel");
        alert.setHeaderText("Cancel modifying " + selected.getName() + "...");
        alert.setContentText("Would you like to continue?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
                // ... user chose OK               

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
        
    }

    @FXML
    private void saveHandler(MouseEvent event) throws IOException {
        
        // Saves the part object
        // Saves text fields

        selected.setID(Integer.parseInt(PartId.getText()));
        selected.setMin(Integer.parseInt(Min.getText()));
        selected.setName(PartName.getText());
        selected.setStock(Integer.parseInt(Inv.getText()));
        selected.setPrice(Double.parseDouble(priceCost.getText()));
        selected.setMax(Integer.parseInt(Max.getText()));
        
        if (Integer.parseInt(Min.getText()) < Integer.parseInt(Max.getText())) {


            if(InHouse.isSelected()) {
                InHouse selectedPart = (InHouse) selected;
                selectedPart.setMachineID(Integer.parseInt(CompanyName.getText()));

            }
            else if (Outsourced.isSelected()) {
                OutSourced selectedPart = (OutSourced) selected;
                selectedPart.setCompanyName(CompanyName.getText());
            }

            else {
                System.out.println("All fields must be used.");
            }

            // Set the scene back to the MainScreenFXML
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
        
        else {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Value Error");
        alert.setContentText("Min must be less than Max!");

        alert.showAndWait();
        }
        
    }
    
}
