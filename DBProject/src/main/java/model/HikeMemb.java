package model;

import javafx.beans.property.SimpleStringProperty;

public class HikeMemb {
    private SimpleStringProperty card_code;
    private SimpleStringProperty first_name;
    private SimpleStringProperty second_name;

    public HikeMemb(
            String card_code,
            String first_name,
            String second_name
    ) {
        this.card_code = new SimpleStringProperty(card_code);
        this.first_name = new SimpleStringProperty(first_name);
        this.second_name = new SimpleStringProperty(second_name);

    }

    public String getCard_code() {
        return card_code.get();
    }

    public SimpleStringProperty card_codeProperty() {
        return card_code;
    }

    public void setCard_code(String card_code) {
        this.card_code.set(card_code);
    }

    public String getFirst_name() {
        return first_name.get();
    }

    public SimpleStringProperty first_nameProperty() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name.set(first_name);
    }

    public String getSecond_name() {
        return second_name.get();
    }

    public SimpleStringProperty second_nameProperty() {
        return second_name;
    }

    public void setSecond_name(String second_name) {
        this.second_name.set(second_name);
    }
}
