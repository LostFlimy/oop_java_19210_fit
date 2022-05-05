package model;

import javafx.beans.property.SimpleStringProperty;

public class Competition {
    private SimpleStringProperty title;
    private SimpleStringProperty start_date;
    private SimpleStringProperty finish_date;
    public Competition(
            String title,
            String start_date,
            String finish_date){
        this.title = new SimpleStringProperty(title);
        this.start_date = new SimpleStringProperty(start_date);
        this.finish_date = new SimpleStringProperty(finish_date);
    }

    public String getTitle() {
        return title.get();
    }

    public SimpleStringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
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

    public String getFinish_date() {
        return finish_date.get();
    }

    public SimpleStringProperty finish_dateProperty() {
        return finish_date;
    }

    public void setFinish_date(String finish_date) {
        this.finish_date.set(finish_date);
    }
}
