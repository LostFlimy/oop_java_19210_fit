package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class GroupMember {
    private SimpleStringProperty card_code;
    private SimpleIntegerProperty group_num;

    public GroupMember(
            String card_code,
            Integer group_num
    ) {
        this.card_code = new SimpleStringProperty(card_code);
        this.group_num = new SimpleIntegerProperty(group_num);
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

    public int getGroup_num() {
        return group_num.get();
    }

    public SimpleIntegerProperty group_numProperty() {
        return group_num;
    }

    public void setGroup_num(int group_num) {
        this.group_num.set(group_num);
    }
}
