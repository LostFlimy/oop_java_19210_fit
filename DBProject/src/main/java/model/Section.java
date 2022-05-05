package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Section {
    private SimpleStringProperty sport;
    private SimpleIntegerProperty section_number;
    private SimpleIntegerProperty director_number;

    public Section(
            String sport,
            Integer section_number,
            Integer director_number
    ) {
        this.sport = new SimpleStringProperty(sport);
        this.section_number = new SimpleIntegerProperty(section_number);
        this.director_number = new SimpleIntegerProperty(director_number);
    }

    public String getSport() {
        return sport.get();
    }

    public SimpleStringProperty sportProperty() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport.set(sport);
    }

    public int getDirector_number() {
        return director_number.get();
    }

    public SimpleIntegerProperty director_numberProperty() {
        return director_number;
    }

    public void setDirector_number(int director_number) {
        this.director_number.set(director_number);
    }

    public int getSection_number() {
        return section_number.get();
    }

    public SimpleIntegerProperty section_numberProperty() {
        return section_number;
    }

    public void setSection_number(int section_number) {
        this.section_number.set(section_number);
    }
}
