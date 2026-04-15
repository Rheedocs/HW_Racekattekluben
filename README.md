# Racekatteklubben

---

## Beskrivelse
Racekatteklubben er et program hvor man kan oprette sig som medlem og derefter tilføje sin(e) kat(te) til hjemmesiden
som andre kan se og derunder tilhører dette at andre medlemmer kan se kattens informationer og hvem den tilhører.
Derudover kan medlemmer tilmelde deres katte til udstillinger og se resultater.
(Zealand, 2. semester, DAT-2025)

---

## Teknologier
- Java er det primære kodningssprog da dette er hele grunden for at programmet kan hænge sammen og køre.
- Spring Boot er med til at køre controllerne i programmet men også at køre HTML delen i sammenhæng med at når programmet kører starter vores hjemmeside og gør at vi kan tilgå diverse HTML'er som er blevet lavet.
- MySQL er med til at styre vores databaser for programmet således at det data som skal bruges indenfor programmet bliver vist korrekt da uden MySQL vil dette ikke fungere.
- Thymeleaf er med til at indsætte dataen til HTML og er med til at returnere siden.
- BCrypt er en hashing-algoritme som i dette program bliver brugt til sikker lagring af passwords som brugerne i vores program kan have, dette gør at brugernes data bliver beskyttet.
- CSS (Style.css) bliver i dette sammenhæng brugt til vores style elementer i forhold til farver og layout og gør at hjemmesiden ser bedre ud og mere brugervenlig.

---

## Funktionalitet
Systemet er et Spring Boot baseret administrationssystem til en racekatteklub. Det understøtter håndtering af katte, medlemmer og udstillinger samt brugerlogin med forskellige roller (ADMIN og USER).

- Member håndterer oprettelse af nye medlemmer, liste over medlemmer, redigere medlemsoplysninger og slette medlemmer
- Cat håndterer oprettelse af nye katte med information (navn, race, opdrætter), liste over katte, redigere katteoplysninger og slette katte
- Exhibition håndterer oprettelse af udstillinger, tilmelding af katte til udstillinger og liste over deltagende katte
- Login og logout med email og adgangskode

---

## Opsætning
- Åben IntelliJ
- Gå ned i resources filen
- Kopier `application.properties.example` til `application.properties` og udfyld dit MySQL username og password
- Opret en MySQL Connection
- Åbn main package
- Åbn java/dk/zealand/hw_racekattekluben
- Åbn HwRacekatteklubenApplication.java
- Tryk på kør/run i øverste venstre hjørne
- Åbn din browser og skriv localhost:8080

---

## Test
Unit tests køres i IntelliJ med grøn play-knap på testklasserne.
Tests dækker CatService, MemberService og ExhibitionService med Mockito, samt domain-tests for Cat, Member og Exhibition og repository-tests for SQLCatRepository, SQLMemberRepository og SQLExhibitionRepository.

---

## Gruppe
Hello World:
Nicki, Goncalo, Mattias
