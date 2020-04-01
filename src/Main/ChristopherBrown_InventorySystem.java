/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Model.InHouse;
import Model.Inventory;
import Model.OutSourced;
import Model.Part;
import Model.Product;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Christopher Brown
 * Class: C482 
 * Project Inventory System
 */
public class ChristopherBrown_InventorySystem extends Application {
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        Inventory inv = new Inventory();
        addTestData(inv);
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View_Controller/mainScreen.fxml")); // loads our main screen.
        View_Controller.MainScreenController controller = new View_Controller.MainScreenController(inv);
        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        
    }

    
    
    void addTestData(Inventory inv) {
        // InHouse
        Part a1 = new InHouse(01, "wheel", 10.97, 5, 2, 80, 389);
        Part a2 = new InHouse(02, "seat", 18.23, 10, 5, 100,390);
        Part b = new InHouse(03, "handle bars", 10.97, 5, 2, 80, 290);
        
        inv.addPart(a1);
        inv.addPart(a2);
        inv.addPart(b);
        
        inv.addPart(new InHouse(04, "horn", 5.19, 48, 15, 200, 178));
        inv.addPart(new InHouse(05, "frame", 5.19, 48, 15, 200, 178));
        
        // OutSourced
        Part c1 = new OutSourced(6, "chain", 10.97, 5, 2, 80, "MyCompany");
        Part c2 = new OutSourced(7, "front tire", 10.97, 5, 2, 80, "YourCompany");
        Part c3 = new OutSourced(8, "rear tire", 10.97, 5, 2, 80, "MyCompany");
        
        inv.addPart(c1);
        inv.addPart(c2);
        inv.addPart(c3);
        
        inv.addPart(new OutSourced(9, "front brake", 8.00, 29, 4, 200, "HisCompany"));
        inv.addPart(new OutSourced(10, "rear brake", 10.00, 22, 4, 200, "HerCompany"));
        
        //Products
        
        Product product1 = new Product(20, "bike", 89.99, 44, 25, 200);
        product1.addAssociatedPart(a1);
        product1.addAssociatedPart(c1);
        inv.addProduct(product1);
        
        Product product2 = new Product(21, "motorcycle", 1419.98, 39, 154, 250);
        product2.addAssociatedPart(a2);
        product2.addAssociatedPart(c2);
        inv.addProduct(product2);
        
        Product product3 = new Product(22, "atv", 2888.01, 23, 38, 299);
        product3.addAssociatedPart(b);
        product3.addAssociatedPart(c3);
        inv.addProduct(product3);
        
        inv.addProduct(new Product(23, "car", 20000, 18, 2, 20));
        inv.addProduct(new Product(24, "boat", 15000, 12, 4, 25));

        
        
    }
   
}

