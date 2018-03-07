/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kuti_S2;

import java.io.IOException;
import java.sql.SQLException;

/**
 *
 * @author hamkTeam4/deeqkko
 */
public abstract class serverquery {
    
    public abstract boolean isConnectionStatus();

    public abstract int getRfid();

    public abstract void setRfid(int rfid);

    public abstract int getPin();

    public abstract void setPin(int pin);

    public abstract String getName();

    public abstract void setPinQuery(int pinQuery);

    public abstract void queryRfid(int rfid_in) throws IOException;

    public abstract void queryPin(int pin_in) throws IOException;

    //Metodit tietokantaan kirjoittamiselle
    public abstract String getOviID();

    public abstract void setOviID(String oviID);

    public abstract int getUserID();

    public abstract void setUserID(int userID);

    public abstract void setName(String name);

    public abstract int getError();

    public abstract void setError(int error);

    public abstract String getErrormsg();

    public abstract void sendEvent(String oviID, int userID, String name, int error, String errormsg) throws SQLException, IOException;

    /*Muuttujat ovi_ID:lle, user_ID:lle, nimelle ja virheille*/

    public abstract String errorMessage(int error);

    public abstract String getQuery();

    public abstract void setQuery(String query);

    public abstract void checkConnection() throws IOException;
}
