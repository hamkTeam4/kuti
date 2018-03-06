/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kuti_S2;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.HashMap;

/**
 *
 * @author hamkTeam4/deeqkko
 */
public class query extends serverquery{

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
    private String oviID;
    private int pinQuery;
    private String name;

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    //Tällä metodilla saa yhteyden tietokantaan. Käytä tätä luomiesi kyselymetodien sisällä.
    //
    @Override
    public void loadDriver() throws IOException {
        login logData = new login();
        logData.getLoginValues();

        //Tietokantayhteyden avaus. DriverManagerille haetaan logData-olion avulla yhteysnimi, käyttäjänimi ja salasana.
        //Login-luokan olio hakee nämä tiedot login.properties -tiedostosta.
        try {
            conn = DriverManager.getConnection(logData.getDB() + "user=" + logData.getUser() + "&password=" + logData.getPassword());

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    /**
     *
     * @param rfid_in (positive integer)
     * @throws java.io.IOException Metodi kysyy tietokannalta rfid:tä ja vertaa
     * sitä käyttäjän syöttämään rfid:hen (rfid_in)
     */
    @Override
    public void queryRfid(int rfid_in) throws IOException {
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
            while (resultRfid.next()) {
                rfid = resultRfid.getInt(1);
                if (rfid == rfid_in) {
                    break;
                } else {
                    rfid = 0;
                }
            }

        } catch (SQLException ex) {
            // handle any errors
            sqlMesg = ("SQLException: " + ex.getMessage());
            sqlState = ("SQLState: " + ex.getSQLState());
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

    @Override
    public void queryPin(int pin_in) throws IOException {
        loadDriver();
        try {
            //Seuraavat kolme riviä lähettävät tietokannalle kyselyn pin:stä
            //Hakee pin-saraketta kuti_users nimisestä taulusta sellaiseslta riviltä jonka rfid arvo täsmää luokan rfid-muuttujan arvon kanssa.
            //Tallentaa tietokannan pin-arvon paikalliseen resultPin-muuttujaan.
            PreparedStatement queryPin = conn.prepareStatement("SELECT pin FROM users WHERE user_ID=?");
            queryPin.setInt(1, rfid); // 1. parametri ja sen arvo
            ResultSet resultPin = queryPin.executeQuery();

            //Vertaa käyttäjän syöttämää pin:iä tietokannasta haettuun. Jos arvot eivät täsmää luokan pin-muuttuja nollataan.
            while (resultPin.next()) {
                pin = resultPin.getInt(1);
                if (pin != pin_in) {
                    pin = 0;
                } else {
                    pin = resultPin.getInt(1);
                }
            }

        } catch (SQLException ex) {
            // handle any errors
            sqlMesg = ("SQLException: " + ex.getMessage());
            sqlState = ("SQLState: " + ex.getSQLState());
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

    @Override
    public void queryName(int rfid) throws IOException, SQLException {
        try {
            loadDriver();
            PreparedStatement queryName = conn.prepareStatement("SELECT name FROM users WHERE user_ID = ?");
            queryName.setInt(1, rfid);
            ResultSet userName = queryName.executeQuery();
            while (userName.next()) {
                name = userName.getString(1);
            }
            
        } catch (SQLException ex) {
            // handle any errors
            sqlMesg = ("SQLException: " + ex.getMessage());
            sqlState = ("SQLState: " + ex.getSQLState());
            vendorError = ("VendorError: " + ex.getErrorCode());
        }
        
    }
    
   
    @Override
    public String getName() {
        return name;
    }

    //Metodi joka hakee tietokannasta oven tiedot ja tallettaa ne paikallisiin muuttujiin
    //Metodi, joka hakee ovi-taululta oven tiedot ja tallentaa ne muuttujiin
    //tämä metodi toteutetaan joka kerta kun RFID-luku syötetään
    @Override
    public void queryDoorInfo() throws IOException, SQLException {
        try {
            loadDriver();
            PreparedStatement queryDoor = conn.prepareStatement("SELECT pin_query FROM lukijat WHERE ovi_ID = ?");
            queryDoor.setString(1, "S2"); //Tähän oven tunnus
            ResultSet doorInfo = queryDoor.executeQuery();
            pinQuery = doorInfo.getInt(2);
        } catch (SQLException ex) {
            sqlMesg = ("SQLException: " + ex.getMessage());
            sqlState = ("SQLState: " + ex.getSQLState());
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

    @Override
    public int getRfid() {
        return rfid;
    }

    @Override
    public int getPin() {
        return pin;
    }

    @Override
    public String getErrors() {
        return "M " + sqlMesg + "| S " + sqlState + "| V " + vendorError;
    }

    @Override
    public void setPinQuery(int pinQuery) {
        this.pinQuery = pinQuery;
    }

    @Override
    public void setRfid(int rfid) {
        this.rfid = rfid;
    }

    @Override
    public void setPin(int pin) {
        this.pin = pin;
    }

    //Metodit tietokantaan kirjoittamiselle
    
    
    private int userID;
    private int error;
    private String errormsg;
    private final HashMap<Integer, String> errorMessage = new HashMap<>();

    @Override
    public String getOviID() {
        return oviID;
    }

    @Override
    public void setOviID(String oviID) {
        this.oviID = oviID;
    }

    @Override
    public int getUserID() {
        return userID;
    }

    @Override
    public void setUserID(int userID) {
        this.userID = userID;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getError() {
        return error;
    }

    @Override
    public void setError(int error) {
        this.error = error;
    }

    
    @Override
    public String getErrormsg() {
        return errormsg;
    }
    
      /*Metodi, joka lähettää tietokantapalvelimeen aikaleiman, oviID:n, RFID:n ja Tapahtumailmoituksen*/
   
    
    @Override
    public void sendEvent(String oviID, int userID, String name, int error, String errormsg) throws SQLException, IOException{ /*Muuttujat ovi_ID:lle, user_ID:lle, nimelle ja virheille*/
        //Tähän koodi
        loadDriver();
        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        java.sql.Timestamp eventTimestamp = new java.sql.Timestamp(now.getTime());
        String eventUpdate = "INSERT INTO tapahtumat (aika, ovi_ID, user_ID, name, event, eventtext) VALUES (?,?,?,?,?,?)";
        PreparedStatement insert = getConn().prepareStatement(eventUpdate);
        getConn().setAutoCommit(false);
        insert.setTimestamp(1, eventTimestamp);
        insert.setString(2, oviID);
        insert.setInt(3, userID);
        insert.setString(4, name);
        insert.setInt(5,error);
        insert.setString(6, errormsg);
        insert.addBatch();
        
        int[] count = insert.executeBatch(); 
        getConn().commit();
    }
    
    @Override
    public String errorMessage(int error){
        errorMessage.put(0, "Open");
        errorMessage.put(1, "Invalid RFID");
        errorMessage.put(2, "Invalid PIN");
        return errorMessage.get(error);
    }

    @Override
    public String getQuery() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setQuery(String query){    
    }
    

  
    

}

   
