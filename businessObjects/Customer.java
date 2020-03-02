package Business;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Customer extends PersonObj {
    
    //Properties
    int custID;
    int cardInfo;
    
    //Constructors
    public Customer() {
        
        //call overloaded constructor
        this(0, "", "", "", "", 0);
    }    
    //
    public Customer(int custID, String fname, String lname, String address, String password, int cardInfo) {
        
        //call super constructor
        super(fname, lname, address, password);
        this.custID = custID;
        this.cardInfo = cardInfo;
        
    }             

    //Getters and Setters    
    public int getCustID() {
        return custID;
    }
    //
    public void setCustID(int custID) {    
        this.custID = custID;
    }
    //
    public int getCardInfo() {
        return cardInfo;
    }
    //
    public void setCardInfo(int cardInfo) {
        this.cardInfo = cardInfo;
    }
           
    //Database access methods
    //
    //Select Customer from database and populate object with it
    @Override
    public void selectDB(int ID) {
        try{
            Access databaseAccess = new Access();
            
            //execute statment
            String sql = "select * from Customers WHERE CustID = '" + ID + "'";             
            ResultSet result = databaseAccess.getStatement().executeQuery(sql);            

            result.next();
            
            //set properties
            setCustID(result.getInt(2));            
            setFname(result.getString(1));
            setLname(result.getString(3));
            setAddress(result.getString(4));
            setPw(result.getString(6));
            setCardInfo(result.getInt(5));                        
            
            System.out.println("Customer " + ID + " Successfully selected" + System.lineSeparator());
            
            databaseAccess.close();            
        } 
        catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Exception caught - " + ex + System.lineSeparator());
        }
    }    
    //Insert populated Customer Object into database
    @Override
    public void insertDB() {
        try{
            Access databaseAccess = new Access();
            
            //setup statment
            String sql = "INSERT INTO Customers " +
                         "VALUES ('" + getFname()+ "', '" + getCustID()+ "', '" + getLname()+ "', '" + getAddress()+ "', '" + getPw()+ "', '" + getCardInfo()+ "')";             
            
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
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Exception caught - " + ex + System.lineSeparator());
        }
    }
    //Delete populated Customer Object into database
    @Override
    public void deleteDB() {
       try{
            Access databaseAccess = new Access();
            
            //setup statment
            String sql = "DELETE FROM Customers WHERE CustID = " + getCustID(); 
                     
            //execute Deletion                         
            int num = databaseAccess.getStatement().executeUpdate(sql);
            
            //deal with result
            if (num == 1){
                //debug to console
                System.out.println("Deletion successful!" + System.lineSeparator());
                
                //reset properties
                this.setCustID(0);
                this.setPw("");
                this.setFname("");
                this.setLname("");
                this.setAddress("");
                this.setCardInfo(0);
            }else {
                //debug to console
                System.out.println("Deletion failed!" + System.lineSeparator());
            }
            
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Exception caught - " + ex + System.lineSeparator());
        }
    }
    
    //Utility
    @Override
    public void display(){
        System.out.println("   Customer Information   " + System.lineSeparator() +
                           "=========================" + System.lineSeparator() +
                           "Customer ID: " + getCustID()+ System.lineSeparator() +
                           "Password: " + getPw()+ System.lineSeparator() +
                           "First Name: " + getFname()+ System.lineSeparator() +
                           "Last Name: " + getLname()+ System.lineSeparator() +
                           "Address: " + getAddress()+ System.lineSeparator() +
                           "Card Information: " + getCardInfo()+ System.lineSeparator() +
                           "=========================");
    }
    


}
