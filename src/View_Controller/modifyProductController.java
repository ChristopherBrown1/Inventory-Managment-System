/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Model.Inventory;
import Model.Part;
import Model.Product;
import static View_Controller.MainScreenController.inv;
import static View_Controller.MainScreenController.partIDList;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author brown
 */
public class modifyProductController implements Initializable {
    
    private ObservableList<Part> partInventory = FXCollections.observableArrayList();
    private ObservableList<Part> associatedPartsList = FXCollections.observableArrayList();
    private ObservableList<Part> partInventorySearch = FXCollections.observableArrayList();
    private ArrayList<Part> selectedPartList;


    @FXML
    private TextField Search;
    @FXML
    private Button Save;
    @FXML
    private TableColumn<Part, Integer> PartID1;
    @FXML
    private TableColumn<Part, String> PartName1;
    @FXML
    private TableColumn<Part, Integer> InventoryLevel1;
    @FXML
    private TableColumn<Part, Double> PricePerUnit1;
    @FXML
    private TextField PartName;
    @FXML
    private TextField PartId;
    @FXML
    private Button Add;
    @FXML
    private Button Cancel;
    @FXML
    private TextField Min;
    @FXML
    private TextField Inv;
    @FXML
    private TextField priceCost;
    @FXML
    private TextField Max;
    @FXML
    private TableColumn<Part, String> PartID;
    @FXML
    private TableColumn<Part, Integer> InventoryLevel;
    @FXML
    private TableColumn<Part, Double> PricePerUnit;
    @FXML
    private Button Delete;
    @FXML
    private TableView<Part> Table2;
    @FXML
    private TableColumn<Part, String> PartName2;
    @FXML
    private TableView<Part> Table1;
    
    private Inventory inv;
    
    private Product selected;
    @FXML
    private Button SearchBtn;

    public modifyProductController(Inventory inv, Product product) {
        this.inv = inv;
        this.selected = product;
    }

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        PartId.setText(String.valueOf(selected.getID()));
        Min.setText(String.valueOf(selected.getMin()));
        PartName.setText(String.valueOf(selected.getName()));
        Inv.setText(String.valueOf(selected.getStock()));
        priceCost.setText(String.valueOf(selected.getPrice()));
        Max.setText(String.valueOf(selected.getMax()));     
        
        
        generatePartsTable();
        
        generateAssociatedPartsTable();
        
    }    

    
    private void generatePartsTable() {
        if(!partIDList.isEmpty()) {
            for(int i = 0; i < partIDList.size(); ++i) {
                partInventory.add(inv.lookUpPart(partIDList.get(i)));
            }
        }
        
        
        Table1.setItems(partInventory);
        PartID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        PartName1.setCellValueFactory(new PropertyValueFactory<>("Name"));
        InventoryLevel.setCellValueFactory(new PropertyValueFactory<>("Stock"));
        PricePerUnit.setCellValueFactory(new PropertyValueFactory<>("Price"));


        
        Table1.refresh();
    }    

    private void generateAssociatedPartsTable() {
        
        selectedPartList = selected.getAllAssociatedParts();
        
        for (int i = 0; i < selectedPartList.size(); i++){
            associatedPartsList.add(selectedPartList.get(i));            
        }
                
        Table2.setItems(associatedPartsList);
        PartID1.setCellValueFactory(new PropertyValueFactory<>("ID"));
        PartName2.setCellValueFactory(new PropertyValueFactory<>("Name"));
        InventoryLevel1.setCellValueFactory(new PropertyValueFactory<>("Stock"));
        PricePerUnit1.setCellValueFactory(new PropertyValueFactory<>("Price"));  
        
        Table2.refresh();
        
    
        
    }
    
    @FXML
    private void searchHandler(ActionEvent event) {       
    }

    @FXML
    private void saveHandler(ActionEvent event) {
        
        int min = Integer.parseInt(Min.getText());
        String partName = PartName.getText();
        double cost = Double.parseDouble(priceCost.getText());
        int max = Integer.parseInt(Max.getText());
        int stock = Integer.parseInt(Inv.getText());
        
        if (min < max) {
        
            selected.setStock(stock);
            selected.setMax(max);
            selected.setMin(min);
            selected.setPrice(cost);
            selected.setName(partName);




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
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Value Error");
            alert.setContentText("Min must be less than Max!");

            alert.showAndWait();
        }        
        
    }

    @FXML
    private void partNameHandler(ActionEvent event) {
    }

    @FXML
    private void partIDHandler(ActionEvent event) {
    }

    @FXML
    private void addHandler(ActionEvent event) {
        Part selectedPart = Table1.getSelectionModel().getSelectedItem();
        selected.addAssociatedPart(selectedPart);

        associatedPartsList.clear();
        generateAssociatedPartsTable();
        
        
    }

    @FXML
    private void cancelHandler(ActionEvent event) throws IOException {
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Part");
        alert.setHeaderText("Cancel modifying " + selected.getName());
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
    private void minHandler(ActionEvent event) {
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
    private void deleteHandler(ActionEvent event) {
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Product");
        alert.setHeaderText("Deleting " + Table2.getSelectionModel().getSelectedItem().getName() + " from product");
        alert.setContentText("Would you like to continue?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
                    // ... user chose OK            
            Part partToDelete = Table2.getSelectionModel().getSelectedItem();
            System.out.print(partToDelete.getName());
            System.out.print(" deleted from Associated Part");
            selected.deleteAssociatedPart(partToDelete);

            associatedPartsList.clear();
            generateAssociatedPartsTable();
            
        }
        
    }

    @FXML
    private void searchBtnHandler(ActionEvent event) {
        String partToSearch = Search.getText();
        if(!partToSearch.trim().isEmpty()) {
            System.out.println("You clicked the search button");
            partInventorySearch.clear();
            
            for (int i = 0; i < partIDList.size(); i++) {
                if(inv.lookUpPart(partIDList.get(i)).getName().contains(partToSearch.trim())) {
                    System.out.println("You found a part that contains that string!!!");
                    partInventorySearch.add(inv.lookUpPart(partIDList.get(i)));
                }
            }
            Table1.setItems(partInventorySearch);
            Table1.refresh();
        }
        
        else {
            System.out.println("Error: User must enter something");
        }         
    }        
    
    
}
