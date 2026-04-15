# Racekatteklubben

---

## Beskrivelse
TODO: Beskriv kort hvad Racekatteklubben er og hvad systemet løser (Zealand, 2. semester, DAT-2025)
Racekatteklubben er et program hvor man kan oprette sig som medlem samt sin kat og se udstillinger
af katte og inden under dette se dens race,opdrætter.
---

## Teknologier
TODO: List teknologier (Java, Spring Boot, MySQL, Thymeleaf, BCrypt osv.)
- Java i dette program bruges som det primær kodning sprog da dette er hele "grunden"
for dette program for at det hele kan hænge sammen og køre.
- Spring boot er med til at køre controllerne i programmet men også at køre HTML delen
i sammenhæng med at når programmet køre starter vores hjemmeside og gør at vi kan tilgå 
diverse HTML'er som er blevet lavet.
- MySQL er med til at styrre vores databaser for programmet således at det data som
skal bruges indenfor programmet bliver vist korrekt da uden MySQL vil dette ikke fungere.
- Thymeleaf er med til at "indsætte" dataen til HTML og er med til at retuner siden
- BCrypt er en hashing-algoritme som i dette program bliver brugt til sikker lagring af passwords
som brugerne i vores program kan have, dette gør at brugernes data bliver beskyttet.
- CSS(Style.css) bliver i dette sammenhæng bruger til vores style elementer i forhold til
farver og layout og gør at hjemmesiden ser bedre ud og mere brugervenlig.
---

## Funktionalitet
TODO: List hvad systemet kan (member CRUD, cat CRUD, exhibition, login/logout, roller osv.)

Systemet er et springboot baseret administrationssystem til en racekatteklub. Det understøtter håndtering af katten, medlemmer og udstillinger samt m ed brugerlogin med forskellige roller som admin og guest.

- Member håndtere oprettelse af nye medlemmer, liste over medlemmer, redigere medlemdsoplysninger og slette medlemmer

- Cat håndtere oprettelse af nye katte med information(navn, race, opdrætter), liste over katte, redigere katteoplysninger og slette katte

- Exhibition håndtere oprettelse af udstillinger, tilmeding af katte til udstillinger og liste over deltagende katte


---

## Opsætning
TODO: Beskriv trin for trin hvordan man kører projektet lokalt (database, application.properties, kør applikationen)
- Åben IntelliJ
- går ned i resources filen
- Opretter en MySQL Connection
- Derefter åbner man schema.sql og kopiere den ind i din MySQL Workbench/connection
- derefter åbner du data.sql og kopiere den ind i din MySQL Workbench/connection
- derefter opretter du en application.properties i IntelliJ
- i application.properties indsætter du dit MySQL username og password
- Åbner main package
- derefter åbner du java/dk/zealand/hw_racekattekluben
- så åbner du HwRacekatteklubenApplication.java
- og trykker op i venstre hjørne på "kør"/"run"
- nu åbner du google eller hvad din browser nu er.
- og skriver localhost:8080
---

## Test
Uni tests køres i IntelliJ med grøn play-knap på testklasserne.

Tests dækker CatService, MemberService og ExhibitionService med Mockito, samt domain-tests for Cat, Member og Exhibition.

---

## Gruppe
Nicki, Goncalo, Mattias
