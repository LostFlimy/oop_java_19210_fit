package model;

import javafx.beans.property.SimpleStringProperty;

public class RoutePoint {
    private SimpleStringProperty point_name;

    public RoutePoint(String point_name) {
        this.point_name = new SimpleStringProperty(point_name);
    }

    public String getPoint_name() {
        return point_name.get();
    }

    public SimpleStringProperty point_nameProperty() {
        return point_name;
    }

    public void setPoint_name(String point_name) {
        this.point_name.set(point_name);
    }
}
