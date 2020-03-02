package Business;

import java.sql.ResultSet;
import java.sql.SQLException;

class Order extends Cart{
    
    //Properties
    int orderNo;
    
    //Constructors
    //
    public Order() {
        super();
        orderNo = 0;
            
    } 
    //generates its orderNo from DB
    public Order(int custID, ItemList items){
        
        //calls super constructor and initiates that
        super(custID, items);
        //gets total from ItemList and fills that property
        this.total = items.getTotal();
        //fills orderNo from DB
        this.orderNo = generateOrderNumber();
        
    }
    //
    public Order(int orderNo, int custID, ItemList items) {
        
        //calls super constructor and initiates that
        super(custID, items);
        //gets total from ItemList and fills that property
        this.total = items.getTotal();       
        //fills orderNo from parameters
        this.orderNo = orderNo;
    }        

    //Getters and Setters
    //
    public int getOrderNo() {
        return orderNo;
    }
    //
    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }
    

    //DB methods  
    //
    //Select Order from database and populate object with it
    public void selectDB(int orderNo) {
        try {
            Access databaseAccess = new Access();
            
            //setup statement and execute it
            String sql = "select * from Orders WHERE OrderNo = '" + orderNo + "'";
            ResultSet rs = databaseAccess.getStatement().executeQuery(sql);
            
            rs.next();
            
            //set properties
            setOrderNo(rs.getInt(1));
            setCustID(rs.getInt(2));
            //**TODO Figure out how to get itemlists from db
            setTotal(rs.getDouble(4));
            
            //debug some info to console
            System.out.println("Order " + orderNo + " Successfully selected" + System.lineSeparator());
            
            //close connection
            databaseAccess.close();                        
        } 
        catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Exception caught - " + ex + System.lineSeparator());
        }
    }
    //Insert populated Order Object into database
    public void insertDB() {
        try {
             Access databaseAccess = new Access();
            
            //setup statment
            String sql = "INSERT INTO Orders " +
                         "VALUES ('" + getOrderNo()+ "', '" + getCustID()+ "', '" + getFname()+ "', '" + getTotal()+ "')";             
            
            //execute insertion                         
            int num = databaseAccess.getStatement().executeUpdate(sql);
            
            //deal with result
            if (num == 1){
                
                //debug to console
                System.out.println("Insert successful!" + System.lineSeparator());
                
            }else {
                //debug to console
                System.out.println("Insert failed!" + System.lineSeparator());
            }
            
            databaseAccess.close();

        }

        catch (ClassNotFoundException | SQLException e){
            System.out.println("Exception caught - " + e + System.lineSeparator());
        }
    }
    //Delete populated Order Object into database
    public void deleteDB() {
        try{
            Access databaseAccess = new Access();
            
            //setup statment
            String sql = "DELETE FROM Orders WHERE OrderNo = " + getOrderNo();          
            
            //execute Deletion                                               
            int num = databaseAccess.getStatement().executeUpdate(sql);
            
            //deal with result
            if (num == 1){
                //debug to console
                System.out.println("Deletion successful!" + System.lineSeparator());
                //reset properties to blank values
                this.setOrderNo(0);
                this.setCustID(0);
                this.setItems(new ItemList());
                this.setTotal(0);
   
            }else {
                //debug to console
                System.out.println("Deletion failed!" + System.lineSeparator());
            }                        
        } 
        catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Exception caught - " + ex + System.lineSeparator());
        }
    }
    
    //Utility Methods
    //
    //Generates Order Number from DB (Checks max order number and adds 1 to it), 5 digit number beginning with 1
    private int generateOrderNumber(){
        
        //return value
        int resultValue = 0;
        
        //access DB
        try {
            Access databaseAccess = new Access();
            
            //setup statement and execute it
            String sql = "select MAX(OrderNo) from Orders";
            ResultSet rs = databaseAccess.getStatement().executeQuery(sql);
            
            rs.next();
            
            //use result to generate return OrderNo
            resultValue = rs.getInt(1) + 1;
            
            //debug some info to console
            System.out.println("Orders Successfully selected and OrderNo generated" + System.lineSeparator());
                        
            //close connection
            databaseAccess.close();                        
        } 
        catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Exception caught - " + ex + System.lineSeparator());
        }
        
        return resultValue;
        
    }
}