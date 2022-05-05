package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Director {
    private SimpleStringProperty first_name;
    private SimpleStringProperty second_name;
    private SimpleStringProperty last_name;
    private SimpleIntegerProperty age;
    private SimpleStringProperty birthday;
    private SimpleIntegerProperty salary;
    private SimpleStringProperty start_date;

    public Director(
            String first_name,
            String second_name,
            String last_name,
            Integer age,
            String birthday,
            Integer salary,
            String start_date) {
        this.first_name = new SimpleStringProperty(first_name);
        this.second_name = new SimpleStringProperty(second_name);
        this.last_name = new SimpleStringProperty(last_name);
        this.age = new SimpleIntegerProperty(age);
        this.birthday = new SimpleStringProperty(birthday);
        this.salary = new SimpleIntegerProperty(salary);
        this.start_date = new SimpleStringProperty(start_date);
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

    public int getAge() {
        return age.get();
    }

    public SimpleIntegerProperty ageProperty() {
        return age;
    }

    public void setAge(int age) {
        this.age.set(age);
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

    public int getSalary() {
        return salary.get();
    }

    public SimpleIntegerProperty salaryProperty() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary.set(salary);
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
}
