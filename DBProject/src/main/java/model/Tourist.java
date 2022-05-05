package model;

import javafx.beans.property.*;

public class Tourist {
    private SimpleStringProperty first_name;
    private SimpleStringProperty second_name;
    private SimpleStringProperty last_name;
    private SimpleStringProperty gender;
    private SimpleStringProperty birthday;
    private SimpleStringProperty card_code;

    public Tourist(
            String first_name,
            String second_name,
            String last_name,
            String gender,
            String birthday,
            String card_code) {
        this.first_name = new SimpleStringProperty(first_name);
        this.second_name = new SimpleStringProperty(second_name);
        this.last_name = new SimpleStringProperty(last_name);
        this.gender = new SimpleStringProperty(gender);
        this.birthday = new SimpleStringProperty(birthday);
        this.card_code = new SimpleStringProperty(card_code);
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

    public String getLast_name() {
        return last_name.get();
    }

    public SimpleStringProperty last_nameProperty() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name.set(last_name);
    }

    public String getGender() {
        return gender.get();
    }

    public SimpleStringProperty genderProperty() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender.set(gender);
    }

    public String getBirthday() {
        return birthday.get();
    }

    public SimpleStringProperty birthdayProperty() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday.set(birthday);
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
}
