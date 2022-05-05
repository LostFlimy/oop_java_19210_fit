package DBController;

import model.Competition;
import model.CompetitionMember;

import java.sql.*;
import java.util.LinkedList;

public class CompetitionManager {
    private final Connection con;

    private final String selectAllCompetitions = "select * from competition";
    private final String selectAllCompMemb = "select * " +
            "from (comp_memb cm cross join tourist t) cross join competition c " +
            "where cm.comp_id = c.id and t.id = cm.tourist_id";
    private final String selectAllCompMembByCompetition = "select *" +
            "from (comp_memb cm cross join tourist t) cross join competition c " +
            "where cm.comp_id = c.id and c.title = ? and c.start_date = ? and t.id = cm.tourist_id";
    private final String selectAllCompetitionsBetweenDates = "select *" +
            "from competition c " +
            "where c.start_date >= ? and c.finish_date <= ?";
    private final String addNewCompetition = "insert into competition(title, start_date, finish_date) value (?,?,?)";

    public CompetitionManager(Connection con) {
        this.con = con;
    }

    public LinkedList<Competition> getAllCompetitions() {
        LinkedList<Competition> result = new LinkedList<>();
        try {
            PreparedStatement stmt = con.prepareStatement(selectAllCompetitions);
            result = buildCompetition(stmt.executeQuery());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public void addCompetition(Competition competition) throws SQLException {
        PreparedStatement stmt = con.prepareStatement(addNewCompetition);
        stmt.setString(1,competition.getTitle());
        stmt.setString(2,competition.getStart_date().toString());
        stmt.setString(3,competition.getFinish_date().toString());
        stmt.executeUpdate();
    }

    public boolean addNewCompetition(Competition competition) {
        int result = 0;
        try{
            PreparedStatement stmt = con.prepareStatement(
                    "insert into competition (title, start_date, finish_date) value " +
                            "(?,?,?)"
            );
            stmt.setString(1,competition.getTitle());
            stmt.setString(2,competition.getStart_date());
            stmt.setString(3,competition.getFinish_date());
            result = stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (result == 0) {
            return false;
        }
        return true;
    }

    public boolean addNewCompMemb(CompetitionMember competitionMember) {
        int result = 0;
        try {
            PreparedStatement selectTouriststmt = con.prepareStatement(
                    "select id from tourist where card_code = ?"
            );
            PreparedStatement selectHikestmt = con.prepareStatement(
                    "select id from competition where title = ? and start_date = ?"
            );
            PreparedStatement stmt = con.prepareStatement(
                    "insert into comp_memb(reg_num, tourist_id, comp_id) value " +
                            "(?,?,?)"
            );
            selectTouriststmt.setString(1, competitionMember.getCard_code());
            ResultSet tourid = selectTouriststmt.executeQuery();
            selectHikestmt.setString(1,competitionMember.getComp_title());
            selectHikestmt.setString(2,competitionMember.getComp_start_date());
            ResultSet compid = selectHikestmt.executeQuery();
            stmt.setString(1, competitionMember.getReg_num());
            stmt.setInt(2,tourid.getInt(1));
            stmt.setInt(3, compid.getInt(1));
            result = stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (result == 0) {
            return false;
        }
        return true;
    }

    public LinkedList<Competition> getCompetitionsBetweenDates(Date start, Date finish) {
        LinkedList<Competition> result = new LinkedList<>();
        try{
            PreparedStatement stmt = con.prepareStatement(selectAllCompetitionsBetweenDates);
            stmt.setDate(1, start);
            stmt.setDate(2, finish);
            result = buildCompetition(stmt.executeQuery());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return result;
    }

    public LinkedList<CompetitionMember> getAllCompMembers() {
        LinkedList<CompetitionMember> result = new LinkedList<>();
        try{
            PreparedStatement stmt = con.prepareStatement(selectAllCompMemb);
            result = buildCompMemb(stmt.executeQuery());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public LinkedList<CompetitionMember> getCompMembersByCompetition(String title, Date start) {
        LinkedList<CompetitionMember> result = new LinkedList<>();
        try{
            PreparedStatement stmt = con.prepareStatement(selectAllCompMembByCompetition);
            stmt.setString(1,title);
            stmt.setDate(2,start);
            result = buildCompMemb(stmt.executeQuery());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return result;
    }

    private LinkedList<CompetitionMember> buildCompMemb(ResultSet rs) {
        LinkedList<CompetitionMember> result = new LinkedList<>();
        try{
            while(rs.next()) {
                result.add(new CompetitionMember(
                        rs.getString("card_code"),
                        rs.getString("reg_num"),
                        rs.getString("title"),
                        rs.getDate("start_date").toString()
                ));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
    private LinkedList<Competition> buildCompetition(ResultSet rs) {
        LinkedList<Competition> result = new LinkedList<>();
        try {
            while (rs.next()) {
                result.add(new Competition(
                        rs.getString("title"),
                        rs.getDate("start_date").toString(),
                        rs.getDate("finish_date").toString()
                ));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
}
