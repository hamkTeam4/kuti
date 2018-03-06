/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kuti_S2;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 *
 * @author hamkTeam4/deeqkko
 */
public class tcpconnection extends querytcp {
    
    String query;
    String response;

    @Override
    public String getQuery() {
        return query;
    }

    @Override
    public void setQuery(String query) {
        //query = "";
        this.query = query;
    }

    @Override
    public String getResponse() {
        return response;
    }

    @Override
    public void setResponse(String response) {
        this.response = response;
    }
    
    @Override
    public String sendTCP(String query) throws IOException {
        
        //Lukee syötteen
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

        try (Socket clientSocket = new Socket("localhost", 6789)) {
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            setQuery(query); 
         
            outToServer.writeBytes(getQuery() + '\n');
            setResponse(inFromServer.readLine());
            clientSocket.close();
            return response;
            
        }
    }

    
}
