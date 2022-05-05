package DBController;

import model.Director;

import java.sql.*;
import java.util.LinkedList;

public class DirectorManager {
    private final Connection con;

    private final String selectAllDirectors = "select * from director";
    private final String selectDirectorsBySection = "select * " +
            "from director d cross join section s " +
            "where d.id = s.director_id and s.section_number = ?";

    public DirectorManager(Connection con) {
        this.con = con;
    }

    public LinkedList<Director> getAllDirectors() {
        LinkedList<Director> result = new LinkedList<>();

        try {
            Statement stmt = con.createStatement();
            result = buildDirectors(stmt.executeQuery(selectAllDirectors));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return result;
    }

    public LinkedList<Director> getDirectorsForSection(Integer section_number) {
        LinkedList<Director> result = new LinkedList<>();
        try{
            PreparedStatement stmt = con.prepareStatement(selectDirectorsBySection);
            stmt.setInt(1, section_number);
            result = buildDirectors(stmt.executeQuery());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    private LinkedList<Director> buildDirectors(ResultSet rs) {
        LinkedList<Director> result = new LinkedList<>();
        try {
            while (rs.next()) {
                result.add(new Director(
                        rs.getString("first_name"),
                        rs.getString("second_name"),
                        rs.getString("last_name"),
                        rs.getInt("age"),
                        rs.getDate("birthday").toString(),
                        rs.getInt("salary"),
                        rs.getDate("start_date").toString()
                ));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
}
