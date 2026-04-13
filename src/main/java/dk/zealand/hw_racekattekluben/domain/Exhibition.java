package dk.zealand.hw_racekattekluben.domain;

import java.time.LocalDate;

public class Exhibition {
    private int id;
    private String name;
    private String location;
    private LocalDate date;

    public Exhibition(int id, String name, String location, LocalDate date) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.date = date;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getLocation() { return location; }
    public LocalDate getDate() { return date; }

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setLocation(String location) { this.location = location; }
    public void setDate(LocalDate date) { this.date = date; }

    public boolean isUpcoming() {
        return this.date.isAfter(LocalDate.now());
    }

    @Override
    public String toString() {
        return "";
    }
}