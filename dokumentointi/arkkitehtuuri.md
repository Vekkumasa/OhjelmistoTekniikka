## Arkkitehtuuri

#### Rakenne

![Image of pakkages](https://github.com/Vekkumasa/OhjelmistoTekniikka/blob/master/dokumentointi/kuvat/Pakkauskaavio.png)

Ohjelmassa on kolmitasoinen pakkausrakenne

### Käyttöliittymä

Sovellukseen on toteutettu graafinen käyttöliittymä käyttämällä javaFX kirjastoa.
Sovelluksessa on vain yksi mahdollinen käyttäjätyyppi.

- Näkymä käyttäjän kirjautumista varten
- Näkymä uuden käyttäjän luomista varten
- Näkymä HOPS:n luomiselle ja tarkastelulle
  - Mahdollisesti luomiselle ja tarkastelulle tulee omat näkymät


### Sovelluslogiikka
![Image of classes](https://github.com/Vekkumasa/OhjelmistoTekniikka/blob/master/dokumentointi/kuvat/Luokkakaavio.png)

** Kurssin lisääminen **

Hops:n muokkausnäkymässä asetetaan kurssinnimi ja laajuus ja klikataan painiketta.
Kurssin lisääminen suoritetaan seuraavasti:
![Image of add course](https://github.com/Vekkumasa/OhjelmistoTekniikka/blob/master/dokumentointi/kuvat/Sekvenssikaavio_addCourse.jpg)

### Tietojen pysyväistallennus

Tiedot tallennetaan tietokantaan ja niitä käsitellään Service-luokan avulla
