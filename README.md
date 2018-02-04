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

KÄYTÄ kehittämiseen v0.5_Experimental - haaraa. 

Ajantasainen TODO-lista löytyy ko. haaran Readme.md:stä