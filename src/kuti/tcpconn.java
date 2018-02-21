/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kuti;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 *
 * @author hamkTeam4/deeqkko
 */
public class tcpconn extends system {

    String sentence;
    String modifiedSentence;

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public String getModifiedSentence() {
        return modifiedSentence;
    }

    public void setModifiedSentence(String modifiedSentence) {
        this.modifiedSentence = modifiedSentence;
    }

    public String sendTCP(String sentence) throws IOException {

        //Lukee syötteen
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

        try (Socket clientSocket = new Socket("localhost", 6789)) {
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            setSentence(sentence);
            outToServer.writeBytes(getSentence() + '\n');
            setModifiedSentence(inFromServer.readLine());
            clientSocket.close();
            return modifiedSentence;

        }
    }
}
