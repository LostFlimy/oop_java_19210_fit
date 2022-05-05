package DBController;

import model.Coach;
import model.GroupMember;
import model.Tourist;

import java.sql.*;
import java.util.LinkedList;

public class TouristManager {
    private final Connection con;

    private final String selectAllCoaches = "select t.*, s.section_number " +
            "from tourist t cross join section s " +
            "where tourist_type = \"coach\" and t.section_id = s.id";
    private final String selectAllTourists = "select * from tourist";
    private final String selectCoachesBySection = "select t.*, s.section_number " +
            "from tourist t cross join section s " +
            "where tourist_type = \"coach\" and t.section_id = s.id and s.section_number = ?";
    private final String selectCoachesBySport = "select t.*, s.section_number " +
            "from tourist t cross join (section s cross join sport sp) " +
            "where tourist_type = \"coach\" and t.section_id = s.id and sp.id = s.sport_id and sp.title = ?";
    private final String selectTouristsByGender = "select * from tourist where gender = ?";

    public TouristManager(Connection con) {
        this.con = con;
    }

    public LinkedList<Coach> getAllCoaches() {
        LinkedList<Coach> result = new LinkedList<>();

        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(selectAllCoaches);
            result = buildCoaches(rs);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return result;
    }

    public LinkedList<Tourist> getAllTourists() {
        LinkedList<Tourist> result = new LinkedList<>();

        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(selectAllTourists);
            result = buildTourist(rs);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public LinkedList<Coach> getAllCoachesBySection(Integer section_num) {
        LinkedList<Coach> result = new LinkedList<>();

        try {
            PreparedStatement stmt = con.prepareStatement(selectCoachesBySection);
            stmt.setInt(1, section_num);
            ResultSet rs = stmt.executeQuery();
            result = buildCoaches(rs);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public LinkedList<Coach> getAllCoachesBySport(String sport) {
        LinkedList<Coach> result = new LinkedList<>();

        try {
            PreparedStatement stmt = con.prepareStatement(selectCoachesBySport);
            stmt.setString(1,sport);
            ResultSet rs = stmt.executeQuery();
            result = buildCoaches(rs);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return result;
    }

    public LinkedList<Tourist> getAllTouristsByGender(String gender) {
        LinkedList<Tourist> result = new LinkedList<>();

        try {
            PreparedStatement stmt = con.prepareStatement(selectTouristsByGender);
            stmt.setString(1,gender);
            ResultSet rs = stmt.executeQuery();
            result = buildTourist(rs);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    private LinkedList<Tourist> buildTourist(ResultSet rs) {
        LinkedList<Tourist> result = new LinkedList<>();
        try{
            while(rs.next()) {
                result.add(new Tourist(
                        rs.getString("first_name"),
                        rs.getString("second_name"),
                        rs.getString("last_name"),
                        rs.getString("gender"),
                        rs.getDate("birthday").toString(),
                        rs.getString("card_code")
                ));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
    private LinkedList<Coach> buildCoaches(ResultSet rs) {
        LinkedList<Coach> result = new LinkedList<>();
        try {
            while (rs.next()) {
                result.add(new Coach(
                        rs.getString("first_name"),
                        rs.getString("second_name"),
                        rs.getString("last_name"),
                        rs.getString("gender"),
                        rs.getDate("birthday").toString(),
                        rs.getString("card_code"),
                        rs.getDate("start_date").toString(),
                        rs.getInt("section_number"),
                        rs.getInt("salary")
                ));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
}
