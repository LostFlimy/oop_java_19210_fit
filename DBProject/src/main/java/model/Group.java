package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Group {
    private SimpleIntegerProperty group_num;
    private SimpleIntegerProperty section_num;
    private SimpleStringProperty first_name_coach;
    private SimpleStringProperty second_name_coach;
    private SimpleStringProperty coach_card_code;
    public Group(
            Integer group_num,
            Integer section_num,
            String first_name_coach,
            String second_name_coach,
            String coach_card_code
    ) {
        this.group_num = new SimpleIntegerProperty(group_num);
        this.section_num = new SimpleIntegerProperty(section_num);
        this.first_name_coach = new SimpleStringProperty(first_name_coach);
        this.second_name_coach = new SimpleStringProperty(second_name_coach);
        this.coach_card_code = new SimpleStringProperty(coach_card_code);
    }

    public int getGroup_num() {
        return group_num.get();
    }

    public SimpleIntegerProperty group_numProperty() {
        return group_num;
    }

    public void setGroup_num(int group_num) {
        this.group_num.set(group_num);
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

    public String getFirst_name_coach() {
        return first_name_coach.get();
    }

    public SimpleStringProperty first_name_coachProperty() {
        return first_name_coach;
    }

    public void setFirst_name_coach(String first_name_coach) {
        this.first_name_coach.set(first_name_coach);
    }

    public String getSecond_name_coach() {
        return second_name_coach.get();
    }

    public SimpleStringProperty second_name_coachProperty() {
        return second_name_coach;
    }

    public void setSecond_name_coach(String second_name_coach) {
        this.second_name_coach.set(second_name_coach);
    }

    public String getCoach_card_code() {
        return coach_card_code.get();
    }

    public SimpleStringProperty coach_card_codeProperty() {
        return coach_card_code;
    }

    public void setCoach_card_code(String coach_card_code) {
        this.coach_card_code.set(coach_card_code);
    }
}
