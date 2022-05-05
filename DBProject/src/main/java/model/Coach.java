package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Coach{
    private SimpleStringProperty first_name;
    private SimpleStringProperty second_name;
    private SimpleStringProperty last_name;
    private SimpleStringProperty gender;
    private SimpleStringProperty birthday;
    private SimpleStringProperty card_code;
    private SimpleStringProperty start_date;
    private SimpleIntegerProperty section_num;
    private SimpleIntegerProperty salary;


    public Coach(
            String first_name,
            String second_name,
            String last_name,
            String gender,
            String birthday,
            String card_code,
            String start_date,
            Integer section_num,
            Integer salary
    ) {
        this.first_name = new SimpleStringProperty(first_name);
        this.second_name = new SimpleStringProperty(second_name);
        this.last_name = new SimpleStringProperty(last_name);
        this.gender = new SimpleStringProperty(gender);
        this.birthday = new SimpleStringProperty(birthday);
        this.card_code = new SimpleStringProperty(card_code);
        this.start_date = new SimpleStringProperty(start_date);
        this.section_num = new SimpleIntegerProperty(section_num);
        this.salary = new SimpleIntegerProperty(salary);
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

    public String getFirst_name() {
        return first_name.get();
    }

    public SimpleStringProperty first_nameProperty() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name.set(first_name);
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

    public String getStart_date() {
        return start_date.get();
    }

    public SimpleStringProperty start_dateProperty() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date.set(start_date);
    }

    public int getSection_num() {
        return section_num.get();
    }

    public SimpleIntegerProperty section_numProperty() {
        return section_num;
    }

    public void setSection_num(int section_num) {
        this.section_num.set(section_num);
    }

    public int getSalary() {
        return salary.get();
    }

    public SimpleIntegerProperty salaryProperty() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary.set(salary);
    }
}
