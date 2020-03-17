# Määrittely

### Projektin tarkoitus
Kyseessä on opintojen seurantajärjestelmä. Opiskelija voi itse luoda henkilökohtaisen opintosuunnitelman
eli hopsin. Hopsia voi muokata koska vain ja opiskelija voi tehdä myös useamman hopsin halutessaan.
Kurssit merkitään itse manuaalisesti suoritetuiksi, keskeytyneiksi tai hylätyiksi ja ne talletetaan tietokantaan.
Sovellus laskee suoritettujen opintopisteiden määrän ja suoritusten keskiarvon.

### Käyttöliittymä
Sovellukseen kirjaudutaan omilla tunnuksilla, sovelluksessa on vain yhtä käyttäjätyyppiä. 
Kirjautumisen jälkeen aloitusruudulla on painikkeet hopsin listaamiselle ja kurssien lisäämiselle.
Käyttöliittymä on graafinen eikä muita käyttöliittymiä ole.

### Toiminnallisuudet
* Uuden käyttäjän luominen
* Kirjautuminen sovellukseen omilla tunnuksilla
* Mahdollisuus oman hopsin luomiseen
  * Hopsia voi muokata myöhemmin tai luoda kokonaan uuden
  * Hopsiin suunnitellun suoritusajankohdan
  * Hopsissa lukee opiskeluoikeusaika ja tutkinnon laajuus, läsnä- ja poissaolo tiedot
* Merkitä kursseja suoritetuksi, keskeytetyksi tai hylätyksi
* Suoritusten automaattinen seuranta
  * Keskiarvo
  * Opintopisteiden määrä
