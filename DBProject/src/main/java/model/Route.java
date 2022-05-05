package model;

import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;
import java.util.Objects;

public class Route {
    private SimpleStringProperty route_title;
    private ArrayList<RoutePoint> points;

    public Route(String route_title, ArrayList<RoutePoint> points) {
        this.route_title = new SimpleStringProperty(route_title);
        this.points = points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return Objects.equals(route_title, route.route_title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(route_title);
    }

    public String getRoute_title() {
        return route_title.get();
    }

    public SimpleStringProperty route_titleProperty() {
        return route_title;
    }

    public void setRoute_title(String route_title) {
        this.route_title.set(route_title);
    }

    public ArrayList<RoutePoint> getPoints() {
        return points;
    }

    public void setPoints(ArrayList<RoutePoint> points) {
        this.points = points;
    }
}
