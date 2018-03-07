/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kuti_S2;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

/**
 *
 * @author hamkTeam4/deeqkko
 *
 * Tämä luokka korvaa query.javan metodit. Tieto haetaan java-palvelimelta
 * String-muotoisena
 */
public abstract class querytcp extends serverquery {

    int rfid_in;
    int rfid;
    int pin_in;
    int pin;
    int pinQuery;
    int error;
    int userID;
    String errormsg;
    String oviID;
    String name;
    String[] resultFromServer;
    String eventToServer ="";
    boolean connectionStatus;

    public boolean isConnectionStatus() {
        return connectionStatus;
    }

    public void setConnectionStatus(boolean connectionStatus) {
        this.connectionStatus = connectionStatus;
    }
    private final HashMap<Integer, String> errorMessage = new HashMap<>();
    
    
    public int getRfid_in() {
        return rfid_in;
    }

    public void setRfid_in(int rfid_in) {
        this.rfid_in = rfid_in;
    }

    @Override
    public int getRfid() {
        return rfid;
    }

    @Override
    public void setRfid(int rfid) {
        this.rfid = rfid;
    }

    public int getPin_in() {
        return pin_in;
    }

    public void setPin_in(int pin_in) {
        this.pin_in = pin_in;
    }

    @Override
    public int getPin() {
        return pin;
    }

    @Override
    public void setPin(int pin) {
        this.pin = pin;
    }

    public int getPinQuery() {
        return pinQuery;
    }

    @Override
    public void setPinQuery(int pinQuery) {
        this.pinQuery = pinQuery;
    }

    @Override
    public String getOviID() {
        return oviID;
    }

    @Override
    public void setOviID(String oviID) {
        this.oviID = oviID;
    }

    @Override
    public String getName() {
        return name;
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

    @Override
    public void queryRfid(int rfid_in) throws IOException {
        if (rfid_in == 0) {
            rfid = 0;
        } else {
            setQuery("kyselyRfidPin," + Integer.toString(rfid_in));
            resultFromServer = sendTCP(getQuery()).split(",");
            try {
                rfid = Integer.parseInt(resultFromServer[0]);
                pin = Integer.parseInt(resultFromServer[1]);
                name = resultFromServer[2];
            } catch (NumberFormatException e) {
                rfid = 0;
                pin = 0;
                name ="";
            }
        }

    }
    // Tarpeeton
    @Override
    public void queryPin(int pin_in) throws IOException {
        if (pin_in == 0) {
            pin = 0;
        } else {
            setQuery("kyselyRfidPin," + Integer.toString(getRfid()));
            resultFromServer = sendTCP(getQuery()).split(",");
            try {
                pin = Integer.parseInt(resultFromServer[1]);
            } catch (NumberFormatException e) {
                pin = 0;
            }
        }

    } // Tarpeeton
    
    @Override //Tässä on vielä vähän laittamista
    public void sendEvent(String oviID, int userID, String name, int error, String errormsg) throws SQLException, IOException {
        eventToServer += oviID
                .concat(",")
                .concat(Integer.toString(userID))
                .concat(",")
                .concat(name)
                .concat(",")
                .concat(Integer.toString(error))
                .concat(",")
                .concat(errormsg);
        
        sendTCP(eventToServer);
        eventToServer="";
        
    }
    
    @Override
    public String errorMessage(int error){
        errorMessage.put(0, "Open");
        errorMessage.put(1, "Invalid RFID");
        errorMessage.put(2, "Invalid PIN");
        return errorMessage.get(error);
    }

    //Abstraktit luokat tcpconnectionista
    @Override
    public abstract String getQuery();

    @Override
    public abstract void setQuery(String query);

    public abstract String getResponse();

    public abstract void setResponse(String response);

    public abstract String sendTCP(String query) throws IOException;

   

}
