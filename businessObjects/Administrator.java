package Business;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Administrator extends PersonObj {
    
    //Properties
    int AdminID;
    
    //Constructors
    public Administrator() {
        //call overloaded constructor
        this(0, "", "", "", "");
    }    
    //    
    public Administrator(int AdminID, String fname, String lname, String password, String address) {
       
        //call super constructor
        super(fname, lname, password, address);
        this.AdminID = AdminID;
    }
    
    //Getters and Setters
    public int getAdminID() {
        return AdminID;
    }
    //
    public void setAdminID(int AdminID) {
        this.AdminID = AdminID;
    }
    

    //Database access methods
    //
    //Select Admin from database and populate object with it
    @Override
    public void selectDB(int ID) {
        
        try {
            Access databaseAccess = new Access();
            
            //setup statement and execute it
            String sql = "select * from Administrators WHERE ID = '" + ID + "'";
            ResultSet rs = databaseAccess.getStatement().executeQuery(sql);
            
            rs.next();
            
            //set properties
            setAdminID(rs.getInt(1));
            setFname(rs.getString(2));
            setLname(rs.getString(3));
            setAddress(rs.getString(4));
            setPw(rs.getString(5));
            
            //debug some info to console
            System.out.println("Administrator " + ID + " Successfully selected" + System.lineSeparator());
            
            //close connection
            databaseAccess.close();                        
        } 
        catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Exception caught - " + ex + System.lineSeparator());
        }
    }
    //
    //Insert populated Admin Object into database
    @Override
    public void insertDB() {
        
        try {
             Access databaseAccess = new Access();
            
            //setup statment
            String sql = "INSERT INTO Administrators " +
                         "VALUES ('" + getAdminID()+ "', '" + getFname()+ "', '" + getLname()+ "', '" + getAddress()+ "', '" + getPw()+ "')";             
            
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
    //
    //Delete populated Admin Object from database
    @Override
    public void deleteDB() {
        
        try{
            Access databaseAccess = new Access();
            
            //setup statment
            String sql = "DELETE FROM Administrators WHERE ID = " + getAdminID();          
            
            //execute Deletion                                               
            int num = databaseAccess.getStatement().executeUpdate(sql);
            
            //deal with result
            if (num == 1){
                //debug to console
                System.out.println("Deletion successful!" + System.lineSeparator());
                //reset properties to blank values
                this.setAdminID(0);
                this.setFname("");
                this.setLname("");
                this.setAddress("");
                this.setPw("");
            }else {
                //debug to console
                System.out.println("Deletion failed!" + System.lineSeparator());
            }                        
        } 
        catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Exception caught - " + ex + System.lineSeparator());
        }
        
    }
    
    //Utility
    @Override
    public void display(){
        System.out.println("   Administrator Information   " + System.lineSeparator() +
                           "=========================" + System.lineSeparator() +
                           "Administrator ID: " + getAdminID()+ System.lineSeparator() +
                           "Password: " + getPw()+ System.lineSeparator() +
                           "First Name: " + getFname()+ System.lineSeparator() +
                           "Last Name: " + getLname()+ System.lineSeparator() +
                           "Address: " + getAddress()+ System.lineSeparator() +
                           "=========================");
    }
  

}
