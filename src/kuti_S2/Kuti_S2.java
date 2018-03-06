/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kuti_S2;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author Urho Kekkonen
 */
public class Kuti_S2 {

    /**
     * @param args
     * @throws java.io.IOException
     * @throws java.sql.SQLException
     * @Rename SQLTest to kutiDoor (...or something)
     */
    public static void main(String[] args) throws IOException, SQLException {

        try {
            // The newInstance() call is a work around for some
            // broken Java implementations

            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException ex) {
            // handle the error
        }

        serverquery Event = new tcpconnection();
        serverquery compRfidPin = new tcpconnection();
        serverquery username = new tcpconnection();
        Scanner rfid_in = new Scanner(System.in);
        Scanner pin_in = new Scanner(System.in);
        int rfid;
        int pin;
        String doorID = "S2";
        //query sendTest = new query(); Testiobjekti tapahtuman lähettämiseksi tietokantaan

        //sendTest.sendEvent(); Testitapahtuman lähetys
        do {
            Event.setOviID(doorID); //Asettaa ovitunnukset Event-oliolle
            System.out.println("KUTI_Ovilukija v0.6\nOvi " + doorID);
            System.out.println("Enter RFID: ");
            
            

            rfid = rfid_in.nextInt(); //Kysyy käyttäjältä RFID:n ja muuntaa sen koknaisluvuksi (int)
            Event.setUserID(rfid); // Antaa tiedon event-olion user_ID muuttujalle
            compRfidPin.queryRfid(rfid); //RFID:n haku tietokannasta.
            //System.out.println(compRfidPin.getRfid());

            if (compRfidPin.getRfid() == 0) {   //Jos RFID:tä ei löydy tietokannasta compRfidPin.getRfid() palauttaa arvon 0.
                if (rfid == 0) {
                    System.out.println("Terminated by user");
                    break;
                } else {
                    System.out.println("Invalid RFID");
                    Event.setName("Invalid user");
                    Event.setError(1);
                }
            } else {
                //username.queryName(rfid);
                Event.setName(compRfidPin.getName());
                System.out.println("Enter PIN:");
                pin = pin_in.nextInt();
                //compRfidPin.queryPin(pin);      //Jos käyttäjän syöttämä PIN ei täsmää tietokannan arvon kanssa compRfidPin.getPin() palauttaa arvon 0.
                if (compRfidPin.getPin() != pin || compRfidPin.getPin() == 0) {
                    System.out.println("Invalid PIN");
                    Event.setError(2); // Antaa tiedon event-olion error muuttujalle jos pin syötetään väärin
                } else {
                    System.out.println("Opening...");
                    Event.setError(0); // Antaa tiedon event-olion muuttujalle (ei virhettä, avaa ovi)
                }

                // Tulostaa SQL-serverin antamat virheilmoitukset System.out.println(compRfidPin.getErrors());
                // Tulostaa PIN arvot pin-muuttujasta ja compRfid-objektista virheenmääritystä varten System.out.println(pin + "  " + compRfidPin.getPin());
            }
            //System.out.println(Event.getOviID() + " " + Event.getUserID() + " " + Event.getName() + " " + Event.getError() + " " + Event.errorMessage(Event.getError()));
            Event.sendEvent(Event.getOviID(), Event.getUserID(), Event.getName(), Event.getError(), Event.errorMessage(Event.getError()));
            System.out.println(Event.getQuery());
            compRfidPin.setRfid(0);
            compRfidPin.setPin(0);
        } while (true);

    }
}
