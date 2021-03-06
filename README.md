# OT-harjoitustyö

Kyseessä on opintojen seurantajärjestelmä.
Opiskelija voi itse luoda henkilökohtaisen opintosuunnitelman eli hopsin. 
Hopsia voi muokata koska vain ja opiskelija voi tehdä myös useamman hopsin halutessaan.
Kurssit merkitään itse manuaalisesti suoritetuiksi, keskeytyneiksi tai hylätyiksi ja ne talletetaan tietokantaan. 
Sovellus laskee suoritettujen opintopisteiden määrän ja suoritusten keskiarvon.

### Releaset

[1. Release](https://github.com/Vekkumasa/OhjelmistoTekniikka/releases/tag/viikko5)

[Lopullinen](https://github.com/Vekkumasa/OhjelmistoTekniikka/releases/tag/Viikko7)

### Dokumentaatio

[Määrittely](https://github.com/Vekkumasa/OhjelmistoTekniikka/blob/master/dokumentointi/maarittely.md)

[Työaikakirjanpito](https://github.com/Vekkumasa/OhjelmistoTekniikka/blob/master/dokumentointi/kirjanpito.md)

[Arkkitehtuuri](https://github.com/Vekkumasa/OhjelmistoTekniikka/blob/master/dokumentointi/arkkitehtuuri.md)

[Käyttöohje](https://github.com/Vekkumasa/OhjelmistoTekniikka/blob/master/dokumentointi/kayttoohje.md)

[Testausdokumentti](https://github.com/Vekkumasa/OhjelmistoTekniikka/blob/master/dokumentointi/testaus.md)

### Komentorivitoiminnot

Sovellus käynnistetään komentoriviltä komennolla:

`mvn compile exec:java -Dexec.mainClass=studyrecord.main.Main`

Jar tiedoston generointi:

`mvn package`

##### Testaus

Testit suoritetaan komennolla
 
`mvn test`

Testikattavuus raportin saa komennolla

`mvn jacoco:report`

Kattavuusraporttia voi tarkastella selaimella target/site/jacoco/index.html

##### Checkstyle

Tarkistukset suoritetaan komennolla

`mvn jxr:jxr checkstyle:checkstyle`

Virheilmoitukset voi tarkistaa avaamalla tiedoston target/site/checkstyle.html

#### JavaDoc

JavaDocin saa generoitua komennolla

`mvn javadoc:javadoc`

