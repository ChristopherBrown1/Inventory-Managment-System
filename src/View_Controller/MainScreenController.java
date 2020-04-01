/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;
import Model.Inventory;
import Model.Part;
import Model.Product;
import java.io.IOException;


import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author brown
 */
public class MainScreenController implements Initializable {
    
    static Inventory inv;
    static ArrayList<Integer> partIDList;
    static ArrayList<Integer> productIDList;
    
    private ObservableList<Part> partInventory = FXCollections.observableArrayList();
    private ObservableList<Product> productInventory = FXCollections.observableArrayList();
    private ObservableList<Part> partInventorySearch = FXCollections.observableArrayList();
    private ObservableList<Product> productInventorySearch = FXCollections.observableArrayList();


    
    @FXML
    private TableView<Part> PartsTable;
    @FXML
    private Button Exit;
    @FXML
    private TableView<Product> ProductsTable;
    @FXML
    private Button SearchPart;
    @FXML
    private TextField SearchPartText;
    @FXML
    private TableColumn<Part, Integer> PartID;
    @FXML
    private TableColumn<Part, String> PartName;
    @FXML
    private TableColumn<Part, Integer> PartStock;
    @FXML
    private TableColumn<Part, Double> PartCost;
    @FXML
    private Button AddPart;
    @FXML
    private Button ModifyPart;
    @FXML
    private Button DeletePart;
    @FXML
    private Button SearchProduct;
    @FXML
    private TextField SearchProductText;
    @FXML
    private TableColumn<Product, Integer> ProductID;
    @FXML
    private TableColumn<Product, String> ProductName;
    @FXML
    private TableColumn<Product, Integer> ProductStock;
    @FXML
    private TableColumn<Product, Double> ProductCost;
    @FXML
    private Button AddProduct;
    @FXML
    private Button ModifyProduct;
    @FXML
    private Button DeleteProduct;
    
    public MainScreenController(Inventory inv) {
        this.inv = inv;
        partIDList = inv.retrievePartsIDList();
        productIDList = inv.retrieveProductsIDList();
        
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        generatePartsTable();
        generateProductsTable();
    }    
    
    private void generatePartsTable() {
        if(!partIDList.isEmpty()) {
            for(int i = 0; i < partIDList.size(); ++i) {
                partInventory.add(inv.lookUpPart(partIDList.get(i)));
            }
        }
        
        
        PartsTable.setItems(partInventory);
        PartID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        PartName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        PartStock.setCellValueFactory(new PropertyValueFactory<>("Stock"));
        PartCost.setCellValueFactory(new PropertyValueFactory<>("Price"));


        
        PartsTable.refresh();
    }
    
    private void generateProductsTable() {
        if(!productIDList.isEmpty()) {
            for(int i = 0; i < productIDList.size(); ++i) {
                productInventory.add(inv.lookUpProduct(productIDList.get(i)));
            }
        }
        ProductsTable.setItems(productInventory);
        ProductID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        ProductName.setCellValueFactory(new PropertyValueFactory<>("name"));
        ProductStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        ProductCost.setCellValueFactory(new PropertyValueFactory<>("price"));

        ProductsTable.refresh();
    }
    
    


    @FXML
    private void searchPartHandler(MouseEvent event) {
        String partToSearch = SearchPartText.getText();
        if(!partToSearch.trim().isEmpty()) {
            System.out.println("You clicked the search button");
            partInventorySearch.clear();
            
            for (int i = 0; i < partIDList.size(); i++) {
                if(inv.lookUpPart(partIDList.get(i)).getName().contains(partToSearch.trim())) {
                    System.out.println("You found a part that contains that string!!!");
                    partInventorySearch.add(inv.lookUpPart(partIDList.get(i)));
                }
            }
            PartsTable.setItems(partInventorySearch);
        }
        
        else {
            System.out.println("Error: User must enter something");
        }
        
    }

    @FXML
    private void addPartHandler(MouseEvent event) {
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View_Controller/addPart.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        }
        catch (IOException e){
           
        }
    }

    @FXML
    private void modifyPartHandler(MouseEvent event) {
        
        try {
            Part selected = PartsTable.getSelectionModel().getSelectedItem();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("modifyPart.fxml"));
            modifyPartController controller = new modifyPartController(inv, selected);
            loader.setController(controller);
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        }
        catch (IOException e){
           
        }
    }

    @FXML
    private void deletePartHandler(MouseEvent event) {
        
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Delete Part");
        alert.setHeaderText("Deleting " + PartsTable.getSelectionModel().getSelectedItem().getName() + " from table");
        alert.setContentText("Would you like to continue?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
                // ... user chose OK                            
            Part partToDelete = PartsTable.getSelectionModel().getSelectedItem();
            System.out.print(partToDelete.getName());
            System.out.print(" deleted");

            inv.deletePart(partToDelete);
            PartsTable.getItems().remove(partToDelete);
        }

    }

    @FXML
    private void exitHandler(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    private void searchProductHandler(MouseEvent event) {
        String productToSearch = SearchProductText.getText(); 
        if(!productToSearch.trim().isEmpty()) {
            System.out.println("You clicked the search button");
            productInventorySearch.clear();
            
            for (int i = 0; i < productIDList.size(); i++) {
                if(inv.lookUpProduct(productIDList.get(i)).getName().contains(productToSearch.trim())) {
                    System.out.println("You found a product that contains that string!!!");
                    productInventorySearch.add(inv.lookUpProduct(productIDList.get(i)));
                }
            }
            ProductsTable.setItems(productInventorySearch);
        }
        
        else {
            System.out.println("Error: User must enter something");
        }
    }

    @FXML
    private void addProductHandler(MouseEvent event) {
        
        try {


            FXMLLoader loader = new FXMLLoader(getClass().getResource("addProduct.fxml"));
            addProductController controller = new addProductController(inv);
            loader.setController(controller);
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        }
        catch (IOException e){
           
        }
    }

    @FXML
    private void modifyProductHandler(MouseEvent event) {
        
        try {
            Product selected = ProductsTable.getSelectionModel().getSelectedItem();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("modifyProduct.fxml"));
            modifyProductController controller = new modifyProductController(inv, selected);
            loader.setController(controller);
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        }
        catch (IOException e){
           
        }
    }

    @FXML
    private void deleteProductHandler(MouseEvent event) {
        
        
            Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Delete Product");
        alert.setHeaderText("Deleting " + ProductsTable.getSelectionModel().getSelectedItem().getName() + " from table");
        alert.setContentText("Would you like to continue?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            // ... user chose OK           
            Product productToDelete = ProductsTable.getSelectionModel().getSelectedItem();
            System.out.print(productToDelete.getName());
            System.out.print("deleted");

            inv.deleteProduct(productToDelete);
            ProductsTable.getItems().remove(productToDelete); 
        }
    }
    
}

