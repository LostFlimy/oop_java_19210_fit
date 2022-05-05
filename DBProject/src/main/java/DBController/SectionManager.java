package DBController;

import model.Section;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class SectionManager {
    private final Connection con;

    private final String selectAllSections = "select * " +
            "from section s cross join (sport sp cross join director d) " +
            "where d.id = s.director_id and s.sport_id = sp.id";

    private final String selectSectionsBySport = "select * " +
            "from section s cross join (sport sp cross join director d) " +
            "where d.id = s.director_id and s.sport_id = sp.id and sp.title = ?";

    private final String selectSectionsByDirector = "select * " +
            "from section s cross join (sport sp cross join director d) " +
            "where d.id = s.director_id and s.sport_id = sp.id and d.director_number = ?";

    public SectionManager(Connection con) {
        this.con = con;
    }

    public LinkedList<Section> getAllSections() {
        LinkedList<Section> result = new LinkedList<>();
        try{
            PreparedStatement stmt = con.prepareStatement(selectAllSections);
            result = buildSections(stmt.executeQuery());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public LinkedList<Section> getSectionsByDirector(Integer directNum) {
        LinkedList<Section> result = new LinkedList<>();
        try{
            PreparedStatement stmt = con.prepareStatement(selectSectionsByDirector);
            stmt.setInt(1, directNum);
            result = buildSections(stmt.executeQuery());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public LinkedList<Section> getSectionsBySport(String sport) {
        LinkedList<Section> result = new LinkedList<>();
        try{
            PreparedStatement stmt = con.prepareStatement(selectSectionsBySport);
            stmt.setString(1, sport);
            result = buildSections(stmt.executeQuery());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    private LinkedList<Section> buildSections(ResultSet rs) {
        LinkedList<Section> result = new LinkedList<>();

        try{
            while(rs.next()) {
                result.add(new Section(
                        rs.getString("sp.title"),
                        rs.getInt("s.section_number"),
                        rs.getInt("d.director_number")
                ));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
}
