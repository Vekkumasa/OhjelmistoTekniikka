# Testausdokumentti

Ohjelmaa on testattu automatisoiduin yksikkö- ja integraatiotestein JUnitilla.


### Yksikkö- ja integraatiotestaus

Käyttäjiä ja kursseja koskevat yksikkötestit käydään kattavasti läpi luokissa [CourseTest](https://github.com/Vekkumasa/OhjelmistoTekniikka/blob/master/studyRecord/src/test/java/studyrecord/CourseTest.java) ja [UserTest](https://github.com/Vekkumasa/OhjelmistoTekniikka/blob/master/studyRecord/src/test/java/studyrecord/UserTest.java).
Tietokantaa testaava [DataBaseTest](https://github.com/Vekkumasa/OhjelmistoTekniikka/blob/master/studyRecord/src/test/java/studyrecord/DataBaseTest.java) luokka hyödyntää domain paketista Service luokan oliota. Tietokanta
testien aluksi DataBaseTest luokka luo oman tietokannan testaamista varten (studiesTest.db) ja testien päätyttyä
poistetaan mahdollsiet luodut tietokanta taulut. Testauksessa pääpaino on ollut datan lisäämisessä ja hakemisessa
tietokannasta.  

#### Testauskattavuus

Käyttöliittymä on ohjeistuksen mukaisesti jätetty testien ulkopuolelle. 

Sovelluksen rivikattavuus on 86% ja haarautumakattavuus on 85%

![Image of jacoco](https://github.com/Vekkumasa/OhjelmistoTekniikka/blob/master/dokumentointi/kuvat/jacoco.png)

Tietokantatesteistä on jätetty pois kaikki Catch sqlexception haarat minkä vuoksi haarautumakattavuus on vain 85%


#### Järjestelmätestaus

Järjestelmätestaus on tehty manuaalisesti


#### Toiminnallisuudet

Kaikki määrittelydokumentissa olevat toiminnallisuudet on testattu.
