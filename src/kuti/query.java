/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kuti;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

/**
 *
 * @author hamkTeam4/deeqkko
 */
public class query extends login{
 
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private int rfid;
    private int pin;
    private String sqlMesg;
    private String sqlState;
    private String vendorError;
    private int rfid_in;
    private int pin_in;
  
        
        //Tällä metodilla saa yhteyden tietokantaan. Käytä tätä luomiesi kyselymetodien sisällä.
        //
    public void loadDriver() throws IOException{
        login logData = new login();
        logData.getLoginValues();
        
        //Tietokantayhteyden avaus. DriverManagerille haetaan logData-olion avulla yhteysnimi, käyttäjänimi ja salasana.
        //Login-luokan olio hakee nämä tiedot login.properties -tiedostosta.
        try {
            conn = DriverManager.getConnection(logData.getDB()+"user="+logData.getUser()+"&password="+logData.getPassword());
       
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    /**
     
     * @param rfid_in (positive integer)
     * @throws java.io.IOException
     * Metodi kysyy tietokannalta rfid:tä ja vertaa sitä käyttäjän syöttämään rfid:hen (rfid_in)   
     */
    public void queryRfid(int rfid_in) throws IOException{ 
        loadDriver();
         try {
            //Seuraavat kolme riviä lähettävät tietokannalle kyselyn rfid:stä
            //Hakee rfid-saraketta kuti_users nimisestä taulusta sellaiseslta riviltä jonka rfid arvo on toisen rivin rfid_in -muuttujassa
            //ja tallentaa sen resultRfid -muuttujaan. (Muuta tarvittavilta osin.)
            PreparedStatement queryRfid = conn.prepareStatement("SELECT user_ID FROM users WHERE user_ID=?"); 
            queryRfid.setInt(1, rfid_in); // 1. parametri ja sen arvo
            ResultSet resultRfid = queryRfid.executeQuery();
           
            //Silmukka vertaa tietokannalta saatua rfid-arvoa käyttäjän syöttämään rfid_in -arvoon. Jos arvot täsmää silmukka päättyy. Jos arvot eivät täsmää rfid-muuttuja
            //asetetaan 0:ksi.
            while (resultRfid.next()){ 
                rfid = resultRfid.getInt(1);
                if (rfid == rfid_in){
                    break;
                } else {
                    rfid = 0;
                }
            }
           
            
               
          
            
        } catch (SQLException ex) {
            // handle any errors
            sqlMesg = ("SQLException: " + ex.getMessage());
            sqlState =("SQLState: " + ex.getSQLState());
            vendorError = ("VendorError: " + ex.getErrorCode());
        } finally {
            // it is a good idea to release
            // resources in a finally{} block
            // in reverse-order of their creation
            // if they are no-longer needed

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) {
                } // ignore

                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                } // ignore

                stmt = null;
            }
        }
       
    } 
    
    public void queryPin(int pin_in) throws IOException{ 
        loadDriver();
         try {
             //Seuraavat kolme riviä lähettävät tietokannalle kyselyn pin:stä
            //Hakee pin-saraketta kuti_users nimisestä taulusta sellaiseslta riviltä jonka rfid arvo täsmää luokan rfid-muuttujan arvon kanssa.
            //Tallentaa tietokannan pin-arvon paikalliseen resultPin-muuttujaan.
            PreparedStatement queryPin = conn.prepareStatement("SELECT pin FROM users WHERE user_ID=?");  
            queryPin.setInt(1, rfid); // 1. parametri ja sen arvo
            ResultSet resultPin = queryPin.executeQuery();
            
            //Vertaa käyttäjän syöttämää pin:iä tietokannasta haettuun. Jos arvot eivät täsmää luokan pin-muuttuja nollataan.
            while(resultPin.next()){
                pin = resultPin.getInt(1);
                if (pin != pin_in){
                    pin = 0;
                } else {
                    pin = resultPin.getInt(1);
                }
            }
            
          
            
               
            
        } catch (SQLException ex) {
            // handle any errors
            sqlMesg = ("SQLException: " + ex.getMessage());
            sqlState =("SQLState: " + ex.getSQLState());
            vendorError = ("VendorError: " + ex.getErrorCode());
        } finally {
            // it is a good idea to release
            // resources in a finally{} block
            // in reverse-order of their creation
            // if they are no-longer needed

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) {
                } // ignore

                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                } // ignore

                stmt = null;
            }
        }
       
    } 
    
    public int getRfid(){
        return rfid;
    }
    
    public int getPin(){
        return pin;
    }

    public String getErrors(){
        return "M " + sqlMesg + "| S " + sqlState + "| V " + vendorError;
    }
    
    /*Metodi, joka lähettää tietokantapalvelimeen aikaleiman, oviID:n, RFID:n ja Tapahtumailmoituksen*/
    //Vielä keskeneräinen
    public void sendEvent(/*Muuttujat ovi_ID:lle, user_ID:lle, nimelle ja virheille*/) throws SQLException, IOException{
        //Tähän koodi
        loadDriver();
        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        java.sql.Timestamp eventTimestamp = new java.sql.Timestamp(now.getTime());
        String eventUpdate = "INSERT INTO tapahtumat (aika, ovi_ID, user_ID, nimi, virheet) VALUES (?,?,?,?,?)";
        PreparedStatement insert = conn.prepareStatement(eventUpdate);
        conn.setAutoCommit(false);
        insert.setTimestamp(1, eventTimestamp);
        insert.setString(2, "S2");
        insert.setInt(3, 9999);
        insert.setString(4, "Keskipörhölä Einari");
        insert.setInt(5,0);
        insert.addBatch();
        
        //int[] count = insert.executeBatch(); Mitä tämä tekee?
        conn.commit();
        
    }
    
    //Metodi, joka hakee ovi-taululta oven tiedot ja tallentaa ne muuttujiin
    //tämä metodi toteutetaan joka kerta kun RFID-luku syötetään
}

    //Metodi, joka sulkee tietokantayhteyden
