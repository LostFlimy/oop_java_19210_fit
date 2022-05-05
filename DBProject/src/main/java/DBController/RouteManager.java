package DBController;

import model.Route;
import model.RoutePoint;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class RouteManager {
    private final Connection con;

    private final String selectRouteTitles = "select route_title, id from route";
    private final String selectRoutePointsByRouteTitle = "select rp.point_name, rp.point_number " +
            "from route_point rp cross join route r " +
            "where rp.route_id = r.id and r.route_title = ?";
    private final String selectRoutesWhatExistPoint = "select r.route_title " +
            "from route r cross join route_point rp " +
            "where r.id = rp.route_id and rp.point_name = ?";

    public RouteManager(Connection con) {
        this.con = con;
    }

    public LinkedList<Route> getAllRoutes() {
        LinkedList<Route> result = new LinkedList<>();
        Map<String, ArrayList<RoutePoint>> pointsByRoutes = getAllRoutePointsFromDb();

        for (String s : pointsByRoutes.keySet()) {
            result.add(new Route(s, pointsByRoutes.get(s)));
        }

        return result;
    }

    public LinkedList<Route> getRoutesByPoint(String route_point) {
        LinkedList<Route> result = new LinkedList<>();

        try {
            PreparedStatement stmt = con.prepareStatement(selectRoutesWhatExistPoint);
            stmt.setString(1, route_point);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                String route_name = rs.getString(1);
                result.add(new Route(route_name, getRoutePointsFromDb(route_name)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return result;
    }

    private Map<String,ArrayList<RoutePoint>> getAllRoutePointsFromDb() {
        Map<String, ArrayList<RoutePoint>> resMap = new HashMap<String, ArrayList<RoutePoint>>();

        //Получаем названия всех маршрутов
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(selectRouteTitles);
            while(rs.next()) {
                String label = rs.getString("route_title");
                resMap.put(label, getRoutePointsFromDb(label));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resMap;
    }

    private ArrayList<RoutePoint> getRoutePointsFromDb(String route_title) {
        ArrayList<RoutePoint> result = new ArrayList<>();

        try {
            PreparedStatement stmt = con.prepareStatement(selectRoutePointsByRouteTitle);
            stmt.setString(1, route_title);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                Integer number = rs.getInt(2);
                result.add(number, new RoutePoint(rs.getString(1)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return result;
    }
}
