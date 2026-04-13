package dk.zealand.hw_racekattekluben.domain;

import dk.zealand.hw_racekattekluben.domain.exceptions.CatAlreadyDeadException;
import dk.zealand.hw_racekattekluben.domain.exceptions.InvalidDeathdateException;

import java.time.LocalDate;

public class Cat {
    private int id;
    private String name;
    private LocalDate birthdate;
    private LocalDate deathdate;
    private String emsCode;
    private String breederName;
    private int memberId;
    private Integer motherId;
    private Integer fatherId;

    public Cat() {
    }

    public Cat(String name, LocalDate birthdate, String emsCode, String breederName, int memberId) {
        this.name = name;
        this.birthdate = birthdate;
        this.emsCode = emsCode;
        this.breederName = breederName;
        this.memberId = memberId;
    }

    public Cat(int id, String name, LocalDate birthdate, LocalDate deathdate,
               String emsCode, String breederName, int memberId,
               Integer motherId, Integer fatherId) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
        this.deathdate = deathdate;
        this.emsCode = emsCode;
        this.breederName = breederName;
        this.memberId = memberId;
        this.motherId = motherId;
        this.fatherId = fatherId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public LocalDate getDeathdate() {
        return deathdate;
    }

    public String getEmsCode() {
        return emsCode;
    }

    public String getBreederName() {
        return breederName;
    }

    public int getMemberId() {
        return memberId;
    }

    public Integer getMotherId() {
        return motherId;
    }

    public Integer getFatherId() {
        return fatherId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public void setEmsCode(String emsCode) {
        this.emsCode = emsCode;
    }

    public void setBreederName(String breederName) {
        this.breederName = breederName;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public void markAsDead(LocalDate deathdate) {
        if (this.deathdate != null) throw new CatAlreadyDeadException(this.id);
        if (deathdate.isAfter(LocalDate.now())) throw new InvalidDeathdateException();
        this.deathdate = deathdate;
    }

    public void setParents(Integer motherId, Integer fatherId) {
        this.motherId = motherId;
        this.fatherId = fatherId;
    }

    @Override
    public String toString() {
        return "Cat{id=" + id + ", name='" + name + "', memberId=" + memberId + "}";
    }
}