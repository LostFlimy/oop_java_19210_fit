package DBController;

import model.Group;
import model.GroupMember;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class GroupManager {
    private final Connection con;

    private final String selectAllGroups = "select * " +
            "from party p cross join (tourist t cross join section s) " +
            "where p.section_id = s.id and t.id = p.coach_id";
    private final String selectAllGroupsBySection = "select * " +
            "from party p cross join (tourist t cross join section s) " +
            "where p.section_id = s.id and t.id = p.coach_id and s.section_number = ?";
    private final String selectAllGroupsByCoach = "select * " +
            "from party p cross join (tourist t cross join section s) " +
            "where p.section_id = s.id and t.id = p.coach_id and t.card_code = ?";
    private final String selectAllGroupMembers = "select * " +
            "from group_memb gm cross join (tourist t1 " +
            "cross join ((party p cross join tourist t2) cross join section s)) " +
            "where gm.tourist_id = t1.id and gm.group_id = p.id and s.id = p.section_id " +
            "and t2.id = p.coach_id";
    private final String selectAllGroupMembersByGroup = "select * " +
            "from group_memb gm cross join (tourist t1 " +
            "cross join ((party p cross join tourist t2) cross join section s)) " +
            "where gm.tourist_id = t1.id and gm.group_id = p.id and s.id = p.section_id " +
            "and t2.id = p.coach_id and p.group_num = ?";
    private final String selectAllGroupMembersBySection = "select * " +
            "from group_memb gm cross join (tourist t1 " +
            "cross join ((party p cross join tourist t2) cross join section s)) " +
            "where gm.tourist_id = t1.id and gm.group_id = p.id and s.id = p.section_id " +
            "and t2.id = p.coach_id and s.section_number = ?";
    private final String selectCoachIdByCardCode = "select id from tourist where tourist.card_code = ?";
    private final String selectSectionIdByNum = "select id from section where section_number = ?";
    private PreparedStatement sectionIdstmt;


    public GroupManager(Connection con) {
        this.con = con;
    }

    public boolean addGroup(Group group) throws SQLException {
        int result = 0;
        PreparedStatement coachIdstmt = con.prepareStatement(selectCoachIdByCardCode);
        PreparedStatement sectionIdstmt = con.prepareStatement(selectSectionIdByNum);
        sectionIdstmt.setInt(1, group.getSection_num());
        coachIdstmt.setString(1, group.getCoach_card_code());

        ResultSet crs = coachIdstmt.executeQuery();
        ResultSet srs = sectionIdstmt.executeQuery();
        PreparedStatement stmt = con.prepareStatement("insert into party(coach_id, section_id, group_num) value " +
                "(?,?,?)");
        crs.next();
        srs.next();
        stmt.setInt(1, crs.getInt(1));
        stmt.setInt(2, srs.getInt(1));
        stmt.setInt(3, group.getGroup_num());
        result = stmt.executeUpdate();

        if (result == 0) {
            return false;
        }
        return true;
    }

    public LinkedList<Group> getAllGroups() {
        LinkedList<Group> result = new LinkedList<>();
        try {
            PreparedStatement stmt = con.prepareStatement(selectAllGroups);
            result = buildGroups(stmt.executeQuery());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public LinkedList<Group> getGroupsBySection(Integer section_num) {
        LinkedList<Group> result = new LinkedList<>();
        try {
            PreparedStatement stmt = con.prepareStatement(selectAllGroupsBySection);
            stmt.setInt(1, section_num);
            result = buildGroups(stmt.executeQuery());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public LinkedList<Group> getGroupsByCoach(String card_code) {
        LinkedList<Group> result = new LinkedList<>();
        try {
            PreparedStatement stmt = con.prepareStatement(selectAllGroupsByCoach);
            stmt.setString(1, card_code);
            result = buildGroups(stmt.executeQuery());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public LinkedList<GroupMember> getAllGroupMembers() {
        LinkedList<GroupMember> result = new LinkedList<>();
        try {
            PreparedStatement stmt = con.prepareStatement(selectAllGroupMembers);
            result = buildGroupMembers(stmt.executeQuery());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public LinkedList<GroupMember> getGroupMembersBySection(Integer section_num) {
        LinkedList<GroupMember> result = new LinkedList<>();
        try {
            PreparedStatement stmt = con.prepareStatement(selectAllGroupMembersBySection);
            stmt.setInt(1, section_num);
            result = buildGroupMembers(stmt.executeQuery());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public LinkedList<GroupMember> getGroupMembersByGroup(Integer group_num) {
        LinkedList<GroupMember> result = new LinkedList<>();
        try {
            PreparedStatement stmt = con.prepareStatement(selectAllGroupMembersByGroup);
            stmt.setInt(1, group_num);
            result = buildGroupMembers(stmt.executeQuery());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    private LinkedList<Group> buildGroups(ResultSet rs) {
        LinkedList<Group> result = new LinkedList<>();
        try {
            while (rs.next()) {
                result.add(new Group(
                        rs.getInt("group_num"),
                        rs.getInt("section_number"),
                        rs.getString("first_name"),
                        rs.getString("second_name"),
                        rs.getString("card_code")
                ));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    private LinkedList<GroupMember> buildGroupMembers(ResultSet rs) {
        LinkedList<GroupMember> result = new LinkedList<>();
        try {
            while (rs.next()) {
                result.add(new GroupMember(
                        rs.getString("card_code"),
                        rs.getInt("group_num")
                ));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
}
