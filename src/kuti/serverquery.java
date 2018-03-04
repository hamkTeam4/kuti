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
 */
public abstract class serverquery {
    
    
    public abstract int getRfid();
    
    public abstract void setRfid(int rfid);
    
    public abstract int getPin();
    
    public abstract void setPin(int pin);
    
    public abstract String getName();
    
    public abstract String getErrors();
    
    public abstract void setPinQuery(int pinQuery);
    
    public abstract void loadDriver() throws IOException;
    
    public abstract void queryRfid(int rfid_in) throws IOException;
    
    public abstract void queryPin(int pin_in) throws IOException;
    
    public abstract void queryName(int rfid)throws IOException, SQLException;
     
    public abstract void queryDoorInfo()throws IOException, SQLException;

    
    
    
}
