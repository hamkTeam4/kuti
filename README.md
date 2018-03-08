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

Virheilmoitus:  "Connection lost"
   a) Tarkasta, että kuti_server -ohjelma on käynnissä
   b) Tarkasta, että tcpconnection.java:ssa tcp-portti on määritetty oikein (sama, kuin  kuti_serverillä)
   c) Tarkasta tietokoneen palomuurin asetukset (liikenne tcp-porttiin sallittu)
