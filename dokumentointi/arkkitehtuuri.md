## Arkkitehtuuri

#### Rakenne

![Image of pakkages](https://github.com/Vekkumasa/OhjelmistoTekniikka/blob/master/dokumentointi/kuvat/Pakkauskaavio.png)

Ohjelmassa on kolmitasoinen pakkausrakenne

### Käyttöliittymä

Sovellukseen on toteutettu graafinen käyttöliittymä käyttämällä javaFX kirjastoa.
Sovelluksessa on vain yksi mahdollinen käyttäjätyyppi.

- Näkymä käyttäjän kirjautumista varten
- Näkymä uuden käyttäjän luomista varten
- Näkymä kurssien luomiselle ja tarkastelulle


### Sovelluslogiikka
![Image of classes](https://github.com/Vekkumasa/OhjelmistoTekniikka/blob/master/dokumentointi/kuvat/Luokkakaavio.png)

**Kurssin lisääminen**

Hops:n muokkausnäkymässä asetetaan kurssinnimi ja laajuus ja klikataan painiketta.
Kurssin lisääminen suoritetaan seuraavasti:
![Image of add course](https://github.com/Vekkumasa/OhjelmistoTekniikka/blob/master/dokumentointi/kuvat/Sekvenssikaavio_addCourse.jpg)


**Käyttäjän lisääminen**

Uuden käyttäjän luomista varten olevassa näkymässä lisätään kenttiin käyttäjänimi ja salasana ja painetaan
create user nappia

![Image of create user](https://github.com/Vekkumasa/OhjelmistoTekniikka/blob/master/dokumentointi/kuvat/Sekvenssikaavio_createUser.jpg)

### Tietojen pysyväistallennus

Tiedot talletetaan tietokantaan ja siitä pitää huolen DBUserDao ja DBCourseDao luokat.
Sovellus on toteutettu noudattamalla Data Access Object (DAO) mallia. DBUserDao toteuttaa UserDao rajapinnan ja 
DBCourseDao toteuttaa CourseDao rajapinnan. Domain paketin Service luokka käsittelee DBUserDao ja DBCourseDao luokkia
UI paketista käsin.
