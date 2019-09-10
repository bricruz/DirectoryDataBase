
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author B_Cru
 */


public class AddressOptions {
    
    protected Connection connection = null;
    protected Statement statement = null;
    protected PreparedStatement preparedstatement = null;    
   
    
    public AddressOptions(){
        try {
           
            connection= DriverManager.getConnection(Database.dbUrl, Database.user, Database.pass);
            System.out.println("Connection is successfull");
            
            }catch (SQLException ex){
                System.out.println(ex);
            }
    }
    
        public void updateAddress(int id, String firstname, String lastname, String email, String phonenumber){
        String query;
        query = "update Addresses set FirstName = ?, LastName =?, Email = ?, PhoneNumber = ? WHERE AddressID = ?";
        try {preparedstatement= connection.prepareStatement(query);
        //preparedstatement.setInt(1, id);
        preparedstatement.setString(1, firstname);
        preparedstatement.setString(2, lastname);
        preparedstatement.setString(3, email);
        preparedstatement.setString(4, phonenumber);
        preparedstatement.setInt(5, id);
        preparedstatement.executeUpdate();
        } catch (SQLException ex) {Logger.getLogger(AddressOptions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /*public void updateAddress(int addressID, String FirstName, String LastName,String Email, String PhoneNumber){
        String query;
        query = "update Addresses setAddressID =?, setFirstName=?, LastName=?, Email= ?, PhoneNumber= ? where addressID= ?";
        try {preparedstatement= connection.prepareStatement(query);    
        preparedstatement.setInt(1, addressID);
        preparedstatement.setString(2, FirstName);
        preparedstatement.setString(3, LastName);
        preparedstatement.setString(4, Email);
        preparedstatement.setString(5, PhoneNumber);
        preparedstatement.executeUpdate();
        } catch (SQLException ex) {Logger.getLogger(AddressOptions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
    
    public ArrayList<Address> viewAddresses (){

    try {ArrayList<Address> address= new ArrayList<Address>();
    statement= connection.createStatement();
    String query= "select * from Addresses";ResultSet rs= statement.executeQuery(query);
    while(rs.next()) {
        int addressID= rs.getInt("AddressID");
        String firstname= rs.getString("FirstName");
        String lastname= rs.getString("LastName");
        String email= rs.getString("Email");
        String phonenumber = rs.getString("PhoneNumber");
        address.add(new Address(addressID, firstname, lastname, email, phonenumber));
            }
            return address; 
        }catch (SQLException ex) {
            Logger.getLogger(AddressOptions.class.getName()).log(Level.SEVERE, null, ex);
            return null; //array list dönme}}
        }
        }
public void addAddress (String firstname, String lastname,String email, String phonenumber)
    {
        String query= "insert into Addresses (FirstName,LastName, Email, PhoneNumber) values (?, ?, ?, ?)";
            try {
                preparedstatement = connection.prepareStatement(query);
                preparedstatement.setString(1, firstname);  
                preparedstatement.setString(2, lastname);
                preparedstatement.setString(3, email);
                preparedstatement.setString(4, phonenumber);
                preparedstatement.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(AddressOptions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

public void deleteAddress (int addressID){
        try {
            String query= "DELETE FROM Addresses where AddressID=?";
    
        preparedstatement= connection.prepareStatement(query);
        preparedstatement.setInt(1, addressID);
        preparedstatement.executeUpdate();
    }catch (SQLException ex){
        Logger.getLogger(AddressOptions.class.getName()).log(Level.SEVERE, null, ex);
    }
}
public void deleteAllAddresses(){
    try{
        String query = "DELETE FROM Addresses";
        preparedstatement = connection.prepareStatement(query);
        preparedstatement.executeUpdate();
        
    }catch (SQLException ex){
        Logger.getLogger(AddressOptions.class.getName()).log(Level.SEVERE, null, ex);
    }
}
    
    
}   

