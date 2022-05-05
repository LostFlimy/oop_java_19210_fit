package model;

import javafx.beans.property.SimpleStringProperty;

public class CompetitionMember {
    private SimpleStringProperty card_code;
    private SimpleStringProperty reg_num;
    private SimpleStringProperty comp_title;
    private SimpleStringProperty comp_start_date;

    public CompetitionMember(
            String card_code,
            String reg_num,
            String comp_title,
            String comp_start_date) {
        this.card_code = new SimpleStringProperty(card_code);
        this.reg_num = new SimpleStringProperty(reg_num);
        this.comp_title = new SimpleStringProperty(comp_title);
        this.comp_start_date = new SimpleStringProperty(comp_start_date);
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

    public String getReg_num() {
        return reg_num.get();
    }

    public SimpleStringProperty reg_numProperty() {
        return reg_num;
    }

    public void setReg_num(String reg_num) {
        this.reg_num.set(reg_num);
    }

    public String getComp_title() {
        return comp_title.get();
    }

    public SimpleStringProperty comp_titleProperty() {
        return comp_title;
    }

    public void setComp_title(String comp_title) {
        this.comp_title.set(comp_title);
    }

    public String getComp_start_date() {
        return comp_start_date.get();
    }

    public SimpleStringProperty comp_start_dateProperty() {
        return comp_start_date;
    }

    public void setComp_start_date(String comp_start_date) {
        this.comp_start_date.set(comp_start_date);
    }
}
