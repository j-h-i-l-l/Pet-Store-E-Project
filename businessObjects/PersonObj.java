package Business;

public abstract class PersonObj {
    
    //Properties
    protected String fname;
    protected String lname;
    protected String pw;
    protected String address; 
    
    //Constructors
    protected PersonObj() {

        fname = "";
        lname = "";
        pw = "";
        address = "";   
    }
    //
    protected PersonObj(String fname, String lname, String password, String address){

    this.fname = fname;
    this.lname = lname;
    this.pw = password;
    this.address = address;        
    }
    
    //Getters and Setters
    public String getFname() {
        return fname;
    }
    //
    public void setFname(String fname) {
        this.fname = fname;
    }
    //
    public String getLname() {
        return lname;
    }
    //
    public void setLname(String lname) {
        this.lname = lname;
    }
    //
    public String getPw() {
        return pw;
    }
    //
    public void setPw(String pw) {
        this.pw = pw;
    }
    //
    public String getAddress() {
        return address;
    }
    //
    public void setAddress(String address) {
        this.address = address;
    }
        
    //Database access methods
    //
    //Select Customer from database and populate object with it
    public abstract void selectDB(int primaryKey);
    //
    //Insert populated Customer Object into database
    public abstract void insertDB();     
    //
    //Delete populated Customer Object from database
    public abstract void deleteDB();  
    
    //Utility Methods
    public void display(){
        
        System.out.println("   Person Information   " + System.lineSeparator() +
                           "=========================" + System.lineSeparator() +
                           "Password: " + getPw()+ System.lineSeparator() +
                           "First Name: " + getFname()+ System.lineSeparator() +
                           "Last Name: " + getLname()+ System.lineSeparator() +
                           "Address: " + getAddress()+ System.lineSeparator() +
                           "=========================");
    }

}
