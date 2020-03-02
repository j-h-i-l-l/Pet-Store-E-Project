package Business;

import java.util.ArrayList;

public class ItemList {
    
    //Properties
    int count = 0;
    double total = 0;
    ArrayList<Item> items = new ArrayList<>();

    //Constructors
    public ItemList() {
    }
        
    //Utility
    public void display(){
        System.out.println("   Item(s) Information   " + System.lineSeparator() +
                           "=========================");
        if (count == 0){
            System.out.println("No Items Found");
        }else {
            items.forEach((i) -> {
            System.out.println("SKU: " + i.getSKU()+ System.lineSeparator() +
                               "Name: " + i.getName()+ System.lineSeparator() +
                               "Price: " + i.getPrice()+ System.lineSeparator() +
                               "# in Stock: " + i.getStock()+ System.lineSeparator() +
                               "=========================");
        });
        }

        
    }
    
    public void add(Item item){
        items.add(item);
        count++;
        total += item.getPrice();
    }
}