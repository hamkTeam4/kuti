Kulunvalvonnan tietokantasovellus

team4hamk

Ovilukijasovellus 1.0.

Sovellus kuvaa kulunvalvonnassa käytettävää ovilukijaa, jolle syötetään RFID-tunnus ja PIN-koodi.

Sovellus ottaa yhteyden kuti-serveriin käyttäen TCP-yhteyttä (vakioportti 6789).

Käyttöohje:
1. Käynnistys: Aja juurikansiossa oleva kuti.cmd
2. Syötä RFID (Enter RFID). Syötä nelinumeroinen RFID-tunnus. Numerot 0-9 kelpaa syötteeksi.
3. Syötä PIN (Enter PIN). Syötä nelinumeroinen PIN kuten kohdassa 1.
4. Ohjelma suoritus loppuu kun syötät RFID-kyselyyn numeron 0.

Asennusohje:

Tämä ohje olettaa, että käytössä on NetBeans 8.2 ja Java 8.
1. Valitse File -> Import Project -> From ZIP...
   a) Zip File: kuti_s2.zip
   b) Folder: [Asennuspolku]
   c) Import
2. TCP-portin asetus
   a) Avaa Source packages -> kuti_S2 -> tcpconnection.java
   b) Aseta TCP-portti tiedoston riville 51 "localhost", [port].
   c) Tallenna tiedosto (Ctrl + S)
3. Valitse Run -> Clean and Build Project
4. Ohjelman voi nyt ajaa juurikansion ../kuti_ovilukija/kuti.cmd -tiedostolla.


Ongelmatilanteet:

Virhetilanne. Ohjelma antaan vasteena "Connection lost" (yhteys kuti-palvelimeen katkennut).
   a) Tarkasta, että kuti_server -ohjelma on käynnissä
   b) Tarkasta, että tcpconnection.java:ssa tcp-portti on määritetty oikein (sama, kuin  kuti_serverillä)
   c) Tarkasta tietokoneen palomuurin asetukset (liikenne tcp-porttiin sallittu)

Testiohje:
a) Ohjelman peruskäyttö
   1. Käynnistä ohjelma.
      Odotettu tulos:
      Ohjelma käynnistyy ja näyttää konsoliviestin
      KUTI_Ovilukija v1.0
      Ovi S2
      Enter RFID: 
   
   2. Syötä validi RFID
      Odotettu tulos:
      Ohjelma kysyy PIN:iä
      Enter PIN
      
   3. Syötä validi PIN
      Odotettu tulos:
      Konsoliviesti: Opening...
      Ohjelma palaa alkutilaan.
      
   4. Lopetus
      Odotettu tulos:
      Ohjelma sammuu
      Konsoliviesti:
      Terminated by user.
      
b) Vikasieto
   1. Syötä väärä RFID (ei tietokannassa)
      Odotettu tulos:
      Ohjelma ilmoittaa väärästä RFID:stä
      Konsoliviesti:
      Invalid RFID
      Ohjelma palaa alkutilaan
     
   2. Syötä korruptoitunut RFID (muutkin kuin numeeriset merkit 0-9)
      Odotettu tulos:
      Ohjelma ilmoittaa korruptoituneesta RFID:stä
      Konsoliviesti:
      ERROR: Corrupted RFID
      Ohjelma palaa alkutilaan
      
   3. Syötä validi RFID ja väärä PIN
      Odotettu tulos:
      Ohjelma ilmoittaa väärästä PIN:istä
      Konsoliviesti:
      Invalid PIN
      
   4. Yhteys palvelimelle poikki
      Katkaise yhteys kuti-palvelimelle
      Odotettu tulos:
      Ohjelma antaa virheilmoituksen ja yrittää muodostaa yhteyttä 10s:n välein
      Konsoliviesti:
      Connection lost
      Ohjelma palaa alkutilaan
   
   5. Palvelinyhteyden palautuminen
      Palauta yhteys kuti-palvelimeen
      Odotettu tulos:
      Ohjelma palaa alkutilaan ja kysyy RFID:tä
      Konsoliviesti
      Enter RFID
   
      
