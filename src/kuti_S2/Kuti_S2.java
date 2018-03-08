/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kuti_S2;

import java.io.IOException;
import java.sql.SQLException;
import java.util.InputMismatchException;
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
     * @throws java.lang.InterruptedException
     * @Rename SQLTest to kutiDoor (...or something)
     */
    public static void main(String[] args) throws IOException, SQLException, InterruptedException {

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
        int rfid = 0;
        int pin = 0;
        boolean loopCond;

        String doorID = "S2";

        mainloop:
        while (true) {
            innerMain:
            do {
                System.out.println("KUTI_Ovilukija v1.0\nOvi " + doorID);
                Event.checkConnection();
                if (Event.isConnectionStatus() == false) {
                    System.out.println("Connection lost");
                    Thread.sleep(10000);
                    break;
                }
                Event.setOviID(doorID); //Asettaa ovitunnukset Event-oliolle

                do { //Silmukassa tarkastetaan ja tarvittaessa vaaditaan uusi syöte (Jos syöte ei ole pelkkiä numeroita)
                    loopCond = false;
                    try {
                        System.out.println("Enter RFID: ");
                        rfid = rfid_in.nextInt(); //Kysyy käyttäjältä RFID:n ja muuntaa sen koknaisluvuksi (int)
                        Event.checkConnection();
                        if (Event.isConnectionStatus() == false) {
                            System.out.println("Connection lost");
                            break innerMain;
                        }

                    } catch (InputMismatchException inputMismatch) {
                        System.out.println("ERROR: Corrupted RFID");
                        rfid_in.nextLine();
                        loopCond = true;
                    }
                } while (loopCond == true);

                Event.setUserID(rfid); // Antaa tiedon event-olion user_ID muuttujalle
                compRfidPin.queryRfid(rfid); //RFID:n haku tietokannasta.
                //System.out.println(compRfidPin.getRfid());

                if (compRfidPin.getRfid() == 0) {   //Jos RFID:tä ei löydy tietokannasta compRfidPin.getRfid() palauttaa arvon 0.
                    if (rfid == 0) {
                        System.out.println("Terminated by user");
                        break mainloop;
                    } else {
                        System.out.println("Invalid RFID");
                        Event.setName("Invalid user");
                        Event.setError(1);
                    }
                } else {
                    //username.queryName(rfid);
                    Event.setName(compRfidPin.getName());
                    do {
                        loopCond = false;
                        try {
                            System.out.println("Enter PIN:");
                            pin = pin_in.nextInt(); //Kysyy käyttäjältä PIN:n ja muuntaa sen koknaisluvuksi (int)
                            Event.checkConnection();
                            if (Event.isConnectionStatus() == false) {
                                System.out.println("Connection lost");
                                break innerMain;
                            }
                        } catch (InputMismatchException inputMismatch) {
                            System.out.println("ERROR: Enter a four digit number");
                            pin_in.nextLine();
                            loopCond = true;
                        }
                    } while (loopCond == true);

                    if (compRfidPin.getPin() != pin || compRfidPin.getPin() == 0) {
                        System.out.println("Invalid PIN");
                        Event.setError(2); // Antaa tiedon event-olion error muuttujalle jos pin syötetään väärin
                    } else {
                        System.out.println("Opening...");
                        Event.setError(0); // Antaa tiedon event-olion muuttujalle (ei virhettä, avaa ovi)
                    }

                }
                Event.sendEvent(Event.getOviID(), Event.getUserID(), Event.getName(), Event.getError(), Event.errorMessage(Event.getError()));
                compRfidPin.setRfid(0);
                compRfidPin.setPin(0);
            } while (true);
        }
    }

}
