Kulunvalvonnan tietokantasovellus

Ovilukijasovellus versio 0.hahhahhaa





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

TODO:

    kuti.java:
        -"Header" tieto ohjelman auetessa (esim. "KUTI_ovilukija v0.5")
        -Ohjelman silmukointi
        -Avaustiedon vieminen tietokantaan
        -ovitiedon haku paikallisiin muuttujiin (joka kerta, kun RFID luetaan
        
    query.java:
        -metodi avaustiedon viemiselle tietokantaan
        -metodi, jolla haetaan oviID:tä vastaan vyöhyketieto ja pin-koodikyselytieto paikallisiksi muuttujiksi

