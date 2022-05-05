package DBController;

import model.Hike;
import model.HikeMemb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

public class HikeManager {
    private final Connection con;

    private final String selectAllHikes = "select * " +
            "from hike h cross join ((tourist t cross join sport s) cross join route r) " +
            "where r.id = h.route_id and t.id = h.trainer_id and s.id = h.sport_id";
    private final String selectHikeMembsByHike = "select * " +
            "from hike_memb hm cross join (hike h cross join tourist t) " +
            "where hm.hike_id = h.id and t.id = hm.tourist_id and h.id = ?";

    public HikeManager(Connection con) {
        this.con = con;
    }

    public LinkedList<Hike> getAllHikes() {
        LinkedList<Hike> result = new LinkedList<>();
        try{
            PreparedStatement stmt = con.prepareStatement(selectAllHikes);
            result = buildHikes(stmt.executeQuery());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }


    private ArrayList<HikeMemb> getHikeMembsByHike(Integer hike_id) {
        ArrayList<HikeMemb> result = new ArrayList<>();
        try{
            PreparedStatement stmt = con.prepareStatement(selectHikeMembsByHike);
            stmt.setInt(1, hike_id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                result.add(new HikeMemb(
                        rs.getString("t.card_code"),
                        rs.getString("t.first_name"),
                        rs.getString("t.second_name")
                ));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    private LinkedList<Hike> buildHikes(ResultSet rs) {
        LinkedList<Hike> result = new LinkedList<>();
        try {
            while(rs.next()) {
                result.add(new Hike(
                        rs.getString("r.route_title"),
                        rs.getInt("h.days_count"),
                        rs.getDate("h.start_date").toString(),
                        rs.getString("t.first_name"),
                        rs.getString("t.second_name"),
                        rs.getString("s.title"),
                        getHikeMembsByHike(rs.getInt("h.id"))
                ));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
}
