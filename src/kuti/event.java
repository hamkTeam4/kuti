/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kuti;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;

/**
 *
 * @author hamkTeam4/deeqkko
 */
public class event extends query {
    
    private String oviID;
    private int userID;
    private String name;
    private int error;
    private String errormsg;
    private final HashMap<Integer, String> errorMessage = new HashMap<>();

    public String getOviID() {
        return oviID;
    }

    public void setOviID(String oviID) {
        this.oviID = oviID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getErrormsg() {
        return errormsg;
    }
    
      /*Metodi, joka lähettää tietokantapalvelimeen aikaleiman, oviID:n, RFID:n ja Tapahtumailmoituksen*/
   
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
    
    public String errorMessage(int error){
        errorMessage.put(0, "Open");
        errorMessage.put(1, "Invalid RFID");
        errorMessage.put(2, "Invalid PIN");
        return errorMessage.get(error);
    }
}
