package Business;

class Item {
	
    //Properties
    int SKU;
    String name;
    double price;
    int stock;
   
    //Constuctors
    public Item() {
        this(0, "", 0.00, 0);
    }
    //
    public Item(int SKU, String name, double price, int stock) {
        
        this.SKU = SKU;
        this.name = name;
        this.price = price;
        this.stock = stock;                
    }
    
    //Getters and Setters
    public int getSKU() {
        return SKU;
    }
    //
    public void setSKU(int SKU) {
        this.SKU = SKU;
    }
    //
    public String getName() {
        return name;
    }
    //
    public void setName(String name) {
        this.name = name;
    }
    //
    public double getPrice() {
        return price;
    }
    //  
    public void setPrice(double price) {
        this.price = price;
    }
    //
    public int getStock() {
        return stock;
    }
    //
    public void setStock(int stock) {
        this.stock = stock;
    }
    
    //DB Methods go here
            
    
}