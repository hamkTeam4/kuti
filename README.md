Kulunvalvonnan tietokantasovellus

Ovilukijasovellus versio 0.6 Experimental





Ottaa sisään käyttäjän syöttämän RFID:n ja PIN:in ja osaa verrata sitä tietokannasta kyselemään RFID:hen ja PIN:iin

Osaa lukea SQL-yhteyden parametrit (url, user, password) ulkoisesta konfiguraatiotiedostosta.


Tiedostot:
Source packages:
    Kuti.java -> pääohjelma
    login.java -> luokka, jolla luetaan yhteysparametrit ulkoisesta tiedostosta
    query.java -> luokka, jossa on metodit sql-yhteyden avaamiselle ja tietojen noudolle tietokannasta

resources:
    login.properties -> sql-yhteyden konfiguraatiotiedosto


Dokumentaatio tiedostoissa keskeneräinen! /28.1.2018 deeqkko
 

Ajantasainen TODO-lista löytyy ko. haaran Readme.md:stä
=======
TODO:

    !TIETOKANTAKYSELYJEN SAATTAMINEN TIETOKANNAN TAULUJA SOPIVIKSI! -> TEHTY

    kuti.java:
        -"Header" tieto ohjelman auetessa (esim. "KUTI_ovilukija v0.5") -> TEHTY
        -Ohjelman silmukointi -> TEHTY
        -Avaustiedon vieminen tietokantaan -> TEHTY
        -ovitiedon haku paikallisiin muuttujiin (joka kerta, kun RFID luetaan)
        
    query.java:
        -metodi avaustiedon viemiselle tietokantaan -> TEHTY 
        -metodi, jolla haetaan oviID:tä vastaan vyöhyketieto ja pin-koodikyselytieto paikallisiksi muuttujiksi -> TEHTY
        -metodi, joka sulkee tietokantayhteyden
        -metodi, joka hakee tietokannalta ovitiedon -> TEHTY


