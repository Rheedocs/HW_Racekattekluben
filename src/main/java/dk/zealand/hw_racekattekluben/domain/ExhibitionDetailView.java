package dk.zealand.hw_racekattekluben.domain;

import java.util.List;

public class ExhibitionDetailView {
    private final Exhibition exhibition;
    private final List<CatExhibitionEntry> entries;
    private final List<Cat> availableCats;

    public ExhibitionDetailView(Exhibition exhibition, List<CatExhibitionEntry> entries, List<Cat> availableCats) {
        this.exhibition = exhibition;
        this.entries = entries;
        this.availableCats = availableCats;
    }

    public Exhibition getExhibition() { return exhibition; }
    public List<CatExhibitionEntry> getEntries() { return entries; }
    public List<Cat> getAvailableCats() { return availableCats; }
}