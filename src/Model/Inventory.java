/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author brown
 */

// Question ------------------- Does everything need to be static in this class? (The UML has it underlined)
public class Inventory {
    
    private ArrayList<Part> allParts;
    private ArrayList<Product> allProducts;
    private ArrayList<Part> filteredParts;
    private ArrayList<Product> filteredProducts;
    
    // Constructor
    public Inventory() {
        allParts = new ArrayList<>();
        allProducts = new ArrayList<>();
    }
    
    public void addPart(Part newPart) {
        if (newPart != null) {
            allParts.add(newPart);
        }
    }
    
    public void addProduct(Product newProduct) {
        if (newProduct != null) {
            allProducts.add(newProduct);
        }
    }
    
    public Part lookUpPart(int partID){
        if(!allParts.isEmpty()) {
            for (int i = 0; i < allParts.size(); ++i) {
                if (allParts.get(i).getID() == partID) {
                    return allParts.get(i);
                }
            }
        }
        return null;        
    }
    
    public Product lookUpProduct(int productID){
        if(!allProducts.isEmpty()) {
            for (int i = 0; i < allProducts.size(); ++i) {
                if (allProducts.get(i).getID() == productID) {
                    return allProducts.get(i);
                }
            }
        }
        return null;
    }
    
    public ArrayList<Part> lookUpPart(String partName) {
        if (!filteredParts.isEmpty()){
            filteredParts.clear(); 
        }
            for(int i = 0; i < allParts.size(); ++i) {
                if (allParts.get(i).getName() == partName) {
                    filteredParts.add(allParts.get(i));
                }
            }
            return filteredParts;
    }
    
    public ArrayList<Product> lookUpProduct(String productName) {
        if (!filteredProducts.isEmpty()){
            filteredProducts.clear(); 
        }
            for(int i = 0; i < allProducts.size(); ++i) {
                if (allProducts.get(i).getName() == productName) {
                    filteredProducts.add(allProducts.get(i));
                }
            }
            return filteredProducts;       
    }
    
    public void updatePart(int index, Part selectedPart) {
        for (int i = 0; i < allParts.size(); ++i) {
            if (allParts.get(i).getID() == selectedPart.getID()) {
                allParts.set(i, selectedPart);
                break;
            }        
        }
    }
    
    public void updateProduct(int index, Product selectedProduct) {
        for (int i = 0; i < allProducts.size(); ++i) {
            if (allProducts.get(i).getID() == selectedProduct.getID()) {
                allProducts.set(i, selectedProduct);
                break;
            }
        }
    }
    
    public void deletePart(Part selectedPart) {
        for (int i = 0; i < allParts.size(); ++i) {
            if (allParts.get(i) == selectedPart) {
                allParts.remove(i);
                break;
            }
        }
        
    }
    
    public void deleteProduct(Product selectedProduct) {
        for (int i = 0; i < allProducts.size(); ++i) {
            if (allProducts.get(i) == selectedProduct) {
                allProducts.remove(i);
                break;
            }   
        }
    }
    
    public ArrayList<Part> getAllParts() {
        return allParts;
    }
    
    public ArrayList<Product> getAllProducts() {
        return allProducts;
    }
   
    
    // TEST --------------------------------------------------------------------
    public ArrayList<Integer> retrievePartsIDList() {
        ArrayList<Integer> allPartsID = new ArrayList<>();
        Part tempPart;
        
        for(int i = 0; i < allParts.size(); ++i) {
            tempPart = allParts.get(i);
            allPartsID.add(tempPart.getID());
        }
        
        return allPartsID;    
    }
    
    public ArrayList<Integer> retrieveProductsIDList() {
        ArrayList<Integer> allProductsID = new ArrayList<>();
        Product tempProduct;
        
        for(int i = 0; i < allProducts.size(); ++i) {
            tempProduct = allProducts.get(i);
            allProductsID.add(tempProduct.getID());
        }
        
        return allProductsID;    
    }
    
}
