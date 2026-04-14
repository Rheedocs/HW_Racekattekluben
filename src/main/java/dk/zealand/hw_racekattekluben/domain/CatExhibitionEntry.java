package dk.zealand.hw_racekattekluben.domain;

public class CatExhibitionEntry {
    private final Cat cat;
    private final String placement;

    public CatExhibitionEntry(Cat cat, String placement) {
        this.cat = cat;
        this.placement = placement;
    }

    public Cat getCat() { return cat; }
    public String getPlacement() { return placement; }
}