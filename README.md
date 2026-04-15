# Racekatteklubben
---
## Beskrivelse
Racekatteklubben er et program hvor man kan oprette sig som medlem og derefter tilføje sine katte til hjemmesiden. Herunder kan andre medlemmer se kattens informationer og hvem den tilhører. Derudover kan medlemmer tilmelde deres katte til udstillinger og se resultater.

(Zealand, 2. semester, DAT-2025)

---
## Teknologier
- **Java** er det primære programmeringssprog og grundlaget for hele programmet.
- **Spring Boot** håndterer controllers og starter en indbygget webserver, så hjemmesiden er tilgængelig når programmet kører.
- **MySQL** bruges til at gemme og hente data i programmet via en relationel database.
- **JdbcTemplate** bruges til at kommunikere med databasen fra Java-koden.
- **Thymeleaf** bruges til at indsætte data i HTML-siderne og returnere dem til brugeren.
- **BCrypt** er en hashing-algoritme der bruges til sikker lagring af adgangskoder, så brugerdata beskyttes.
- **CSS (Style.css)** bruges til styling af hjemmesiden i forhold til farver og layout, hvilket gør siden mere overskuelig og brugervenlig.

---
## Arkitektur
Projektet følger Clean Architecture med en lagdelt struktur:
- **Controller** modtager input fra brugeren
- **Service** håndterer forretningslogikken
- **Repository-interface** definerer kontrakten for dataadgang
- **SQL-repository** kommunikerer med databasen

---
## Funktionalitet
Systemet er et Spring Boot baseret administrationssystem til en racekatteklub. Det understøtter håndtering af katte, medlemmer og udstillinger samt brugerlogin med forskellige roller (ADMIN og USER).
- **Member** håndterer oprettelse af nye medlemmer, liste over medlemmer, redigering af medlemsoplysninger og sletning af medlemmer
- **Cat** håndterer oprettelse af nye katte med information (navn, race, opdrætter), liste over katte, redigering af katteoplysninger og sletning af katte
- **Exhibition** håndterer oprettelse af udstillinger, tilmelding af katte til udstillinger og liste over deltagende katte
- **Login og logout** med email og adgangskode

---
## Opsætning
- Åbn projektet i IntelliJ
- Kopier `application.properties.example` til `application.properties` og udfyld dit MySQL username og password
- Kør `HwRacekatteklubenApplication.java`
- Åbn din browser og gå til `localhost:8080`

---
## Test
Unit tests køres i IntelliJ med grøn play-knap på testklasserne.
Tests dækker CatService, MemberService og ExhibitionService med Mockito, samt domain-tests for Cat, Member og Exhibition og repository-tests for SQLCatRepository, SQLMemberRepository og SQLExhibitionRepository.

---
## Gruppe
Hello World:
Nicki, Goncalo, Mattias
