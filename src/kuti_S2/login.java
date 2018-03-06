/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kuti_S2;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author hamkTeam4/deeqkko
 */
public class login extends query {

    String loginData = "";
    InputStream inputStream;
    private String db_connection;
    private String username;
    private String password;

    public void getLoginValues() throws IOException {
       

        try {
            Properties prop = new Properties();
            String propFileName = "login.properties";

            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }
            // get the property value and print it out
            db_connection = prop.getProperty("db_connection");
            username = prop.getProperty("username");
            password = prop.getProperty("password");
            

        } catch (IOException e) {
            System.out.println("Exception: " + e);
        } finally {
            inputStream.close();
        }
       

    }
    public String getDB(){
        return db_connection;
    }
    public String getUser(){
        return username;
    }
    public String getPassword(){
        return password;
    }
}
