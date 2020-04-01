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
public class Product {
    
    private ArrayList<Part> associatedParts = new ArrayList<Part>();
    private int productID;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;
    
    public Product(int id, String name, double price, int stock, int min, int max) {
        setID(id);
        setName(name);
        setPrice(price);
        setStock(stock);
        setMin(min);
        setMax(max);
    }
    
    public void setID(int id){
        productID = id;
    }
    public int getID(){
        return productID;
    }
    
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    
    public void setPrice(double price){
        this.price = price;
    }
    public double getPrice(){
        return price;
    }
    
    public void setStock(int stock){
        this.stock = stock;
    }
    public int getStock(){
        return stock;
    }
    
    public void setMin(int min){
        this.min = min;
    }
    public int getMin(){
        return min;
    }
    
    public void setMax(int max) {
        this.max = max;
    }
    public int getMax(){
        return max;
    }
    
    // AssociatedPart array list
    public void addAssociatedPart(Part part) {
        if (part != null){
            associatedParts.add(part);
        }
    }
    
    public void deleteAssociatedPart(Part associatedPart) {
        associatedParts.remove(associatedPart);
    }
    
   public ArrayList<Part> getAllAssociatedParts() {
        return associatedParts;
    }
   
    
}
