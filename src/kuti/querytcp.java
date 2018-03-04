/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kuti;

import java.io.IOException;
import java.sql.SQLException;

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
    String oviID;
    String name;
    String[] resultFromServer;

    public int getRfid_in() {
        return rfid_in;
    }

    public void setRfid_in(int rfid_in) {
        this.rfid_in = rfid_in;
    }

    public int getRfid() {
        return rfid;
    }

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

    public String getOviID() {
        return oviID;
    }

    public void setOviID(String oviID) {
        this.oviID = oviID;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
                //pin = Integer.parseInt(resultFromServer[1]);
            } catch (NumberFormatException e) {
                rfid = 0;
                //pin = 0;
            }
        }

    }

    @Override
    public void queryPin(int pin_in) throws IOException {
        if (pin_in == 0) {
            pin = 0;
        } else {
            setQuery("kyselyRfidPin," + Integer.toString(getRfid()));
            resultFromServer = sendTCP(getQuery()).split(",");
        try {
            pin = Integer.parseInt(resultFromServer[1]);
        } catch (NumberFormatException e){
            pin = 0;
        }
        }

    }
    //Abstraktit luokat tcpconnectionista

    public abstract String getQuery();

    public abstract void setQuery(String query);

    public abstract String getResponse();

    public abstract void setResponse(String response);

    public abstract String sendTCP(String query) throws IOException;

    @Override
    public String getErrors() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadDriver() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void queryName(int rfid) throws IOException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void queryDoorInfo() throws IOException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
