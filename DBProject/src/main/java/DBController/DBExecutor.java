package DBController;

import conf.DBConfiguration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBExecutor extends DBConfiguration {
    private Connection con;
    private RouteManager routeManager;
    private TouristManager touristManager;
    private DirectorManager directorManager;
    private CompetitionManager competitionManager;
    private GroupManager groupManager;
    private SectionManager sectionManager;
    private HikeManager hikeManager;
    private static DBExecutor executor;

    public static DBExecutor getInstance() {
        if (executor == null) {
            executor = new DBExecutor();
        }
        return executor;
    }

    private DBExecutor() {
        try {
            con = DriverManager.getConnection(url,user,password);
            routeManager = new RouteManager(con);
            touristManager = new TouristManager(con);
            directorManager = new DirectorManager(con);
            competitionManager = new CompetitionManager(con);
            groupManager =  new GroupManager(con);
            sectionManager = new SectionManager(con);
            hikeManager = new HikeManager(con);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public RouteManager getRouteManager() {
        return routeManager;
    }

    public TouristManager getTouristManager() {
        return touristManager;
    }

    public DirectorManager getDirectorManager() {
        return directorManager;
    }

    public CompetitionManager getCompetitionManager() {
        return competitionManager;
    }

    public GroupManager getGroupManager() {
        return groupManager;
    }

    public SectionManager getSectionManager() {
        return sectionManager;
    }

    public HikeManager getHikeManager() {
        return hikeManager;
    }
}
