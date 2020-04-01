/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author brown
 */
public abstract class Part {
    
    protected int partID;
    protected String partName;
    protected double partPrice = 0.0;
    protected int partInStock;
    protected int min;
    protected int max;
    
    
    public void Part (int id, String name, double price, int stock, int min, int max) {
        partID = id;
        partName = name;
        partPrice = price;
        partInStock = stock;
        this.min = min;
        this.max = max;
    }
    
    public void setID(int id) {
        partID = id;
    }
    public int getID() {
        return partID;
    }
    
    public void setName(String name) {
        partName = name;
    }
    public String getName() {
        return partName;
    }    
    
    public void setPrice(double price) {
        partPrice = price;
    }
    public double getPrice(){
        return partPrice;
    }
    
    public void setStock(int stock) {
        partInStock = stock;
    }
    public int getStock(){
        return partInStock;
    }
    
    public void setMin(int min) {
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
}
