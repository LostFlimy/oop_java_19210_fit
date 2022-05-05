package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;

public class Hike {
    private SimpleStringProperty route;
    private SimpleIntegerProperty days_count;
    private SimpleStringProperty start_date;
    private SimpleStringProperty coach_first_name;
    private SimpleStringProperty coach_second_name;
    private SimpleStringProperty sport;
    private ArrayList<HikeMemb> membs;

    public Hike(
            String route,
            Integer days_count,
            String start_date,
            String coach_first_name,
            String coach_second_name,
            String sport,
            ArrayList<HikeMemb> membs
    ) {
        this.membs = membs;
        this.route = new SimpleStringProperty(route);
        this.days_count = new SimpleIntegerProperty(days_count);
        this.start_date = new SimpleStringProperty(start_date);
        this.coach_first_name = new SimpleStringProperty(coach_first_name);
        this.coach_second_name = new SimpleStringProperty(coach_second_name);
        this.sport = new SimpleStringProperty(sport);
    }

    public String getRoute() {
        return route.get();
    }

    public SimpleStringProperty routeProperty() {
        return route;
    }

    public void setRoute(String route) {
        this.route.set(route);
    }

    public int getDays_count() {
        return days_count.get();
    }

    public SimpleIntegerProperty days_countProperty() {
        return days_count;
    }

    public void setDays_count(int days_count) {
        this.days_count.set(days_count);
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

    public String getCoach_first_name() {
        return coach_first_name.get();
    }

    public SimpleStringProperty coach_first_nameProperty() {
        return coach_first_name;
    }

    public void setCoach_first_name(String coach_first_name) {
        this.coach_first_name.set(coach_first_name);
    }

    public String getCoach_second_name() {
        return coach_second_name.get();
    }

    public SimpleStringProperty coach_second_nameProperty() {
        return coach_second_name;
    }

    public void setCoach_second_name(String coach_second_name) {
        this.coach_second_name.set(coach_second_name);
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

    public ArrayList<HikeMemb> getMembs() {
        return membs;
    }

    public void setMembs(ArrayList<HikeMemb> membs) {
        this.membs = membs;
    }
}
