import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import DBController.DBExecutor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.*;

public class ShowTabController {
    private final ObservableList<String> tables = FXCollections.observableArrayList(
            "all coaches",
            "coaches by section",
            "coaches by sport",
            "all tourists",
            "tourists by gender",
            "all hikes",
            "all groups",
            "groups by section",
            "groups by coach",
            "all competitions",
            "all routes",
            "routes by point",
            "all sections",
            "sections by sport",
            "sections by director",
            "all directors",
            "director by section"
    );

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView table;

    @FXML
    private ComboBox<String> choiseBox;

    @FXML
    private Label labelToShow;

    @FXML
    private TextField textArea;

    @FXML
    void initialize() {
        DBExecutor executor = DBExecutor.getInstance();
        choiseBox.setItems(tables);
        choiseBox.setValue("tourists");
        choiseBox.setOnAction(actionEvent -> {
            switch (choiseBox.getValue()) {
                case "all tourists" : {
                    labelToShow.setText("");
                    textArea.setOnAction(actionEvent1 -> {});
                    ObservableList<Tourist> tourists = FXCollections.observableList(executor.getTouristManager().getAllTourists());
                    initTableTourists();
                    table.setItems(tourists);
                    break;
                }
                case "tourists by gender" : {
                    labelToShow.setText("enter gender (man/woman)");
                    textArea.setOnAction(actionEvent1 -> {
                        String gender = textArea.getText();
                        ObservableList<Tourist> tourists =
                                FXCollections.observableList(
                                        executor
                                                .getTouristManager()
                                                .getAllTouristsByGender(gender)
                                );
                        initTableTourists();
                        table.setItems(tourists);
                    });
                    break;
                }
                case "all coaches" : {
                    labelToShow.setText("");
                    textArea.setOnAction(actionEvent1 -> {});
                    ObservableList<Coach> coaches = FXCollections.observableList(executor.getTouristManager().getAllCoaches());
                    initTableCoaches();
                    table.setItems(coaches);
                    break;
                }
                case "coaches by section" : {
                    labelToShow.setText("enter section number");
                    textArea.setOnAction(actionEvent1 -> {
                        Integer sectionNumber = Integer.parseInt(textArea.getText());
                        ObservableList<Coach> coaches = FXCollections.observableList(executor.getTouristManager().getAllCoachesBySection(sectionNumber));
                        initTableCoaches();
                        table.setItems(coaches);
                    });
                    break;
                }
                case "coaches by sport" : {
                    labelToShow.setText("enter sport title");
                    textArea.setOnAction(actionEvent1 -> {
                        String sport = textArea.getText();
                        ObservableList<Coach> coaches =
                                FXCollections.observableList(
                                        executor
                                                .getTouristManager()
                                                .getAllCoachesBySport(sport)
                                );
                        initTableCoaches();
                        table.setItems(coaches);
                    });
                    break;
                }
                case "all hikes" : {
                    labelToShow.setText("");
                    textArea.setOnAction(actionEvent1 -> {});
                    ObservableList<Hike> hikes = FXCollections.observableList(executor.getHikeManager().getAllHikes());
                    initTableHikes();
                    table.setItems(hikes);
                    break;
                }
                case "all groups" : {
                    labelToShow.setText("");
                    textArea.setOnAction(actionEvent1 -> {});
                    ObservableList<Group> groups = FXCollections.observableList(executor.getGroupManager().getAllGroups());
                    initTableGroups();
                    table.setItems(groups);
                    break;
                }
                case "groups by section" : {
                    labelToShow.setText("enter section number");
                    textArea.setOnAction(actionEvent1 -> {
                        Integer sectionNum = Integer.parseInt(textArea.getText());
                        ObservableList<Group> groups =
                                FXCollections.observableList(
                                        executor
                                                .getGroupManager()
                                                .getGroupsBySection(sectionNum)
                                );
                        initTableGroups();
                        table.setItems(groups);
                    });
                    break;
                }
                case "groups by coach" : {
                    labelToShow.setText("enter coach card code");
                    textArea.setOnAction(actionEvent1 -> {
                        String code = textArea.getText();
                        ObservableList<Group> groups =
                                FXCollections.observableList(
                                        executor
                                                .getGroupManager()
                                                .getGroupsByCoach(code)
                                );
                        initTableGroups();
                        table.setItems(groups);
                    });
                    break;
                }
                case "all competitions" : {
                    labelToShow.setText("");
                    textArea.setOnAction(actionEvent1 -> {});
                    ObservableList<Competition> competitions = FXCollections.observableList(executor.getCompetitionManager().getAllCompetitions());
                    initTableCompet();
                    table.setItems(competitions);
                    break;

                }
                case "all routes" : {
                    labelToShow.setText("");
                    textArea.setOnAction(actionEvent1 -> {});
                    ObservableList<Route> routes = FXCollections.observableList(executor.getRouteManager().getAllRoutes());
                    initTableRoutes();
                    table.setItems(routes);
                    break;
                }
                case "routes by point" : {
                    labelToShow.setText("enter title of point");
                    textArea.setOnAction(actionEvent1 -> {
                        String title = textArea.getText();
                        ObservableList<Route> groups =
                                FXCollections.observableList(
                                        executor
                                        .getRouteManager().getRoutesByPoint(title)
                                );
                        initTableRoutes();
                        table.setItems(groups);
                    });
                    break;
                }
                case "all sections" : {
                    labelToShow.setText("");
                    textArea.setOnAction(actionEvent1 -> {});
                    ObservableList<Section> sections = FXCollections.observableList(executor.getSectionManager().getAllSections());
                    initTableSections();
                    table.setItems(sections);
                    break;
                }
                case "sections by sport" : {
                    labelToShow.setText("enter sport title");
                    textArea.setOnAction(actionEvent1 -> {
                        String sport = textArea.getText();
                        ObservableList<Section> sections =
                                FXCollections.observableList(
                                        executor
                                                .getSectionManager()
                                                .getSectionsBySport(sport)
                                );
                        initTableSections();
                        table.setItems(sections);
                    });
                    break;
                }
                case "sections by director" : {
                    labelToShow.setText("enter director number");
                    textArea.setOnAction(actionEvent1 -> {
                        Integer director = Integer.parseInt(textArea.getText());
                        ObservableList<Section> sections =
                                FXCollections.observableList(
                                        executor
                                                .getSectionManager()
                                                .getSectionsByDirector(director)
                                );
                        initTableSections();
                        table.setItems(sections);
                    });
                    break;
                }
                case "all directors" : {
                    labelToShow.setText("");
                    textArea.setOnAction(actionEvent1 -> {});
                    ObservableList<Director> directors = FXCollections.observableList(executor.getDirectorManager().getAllDirectors());
                    initTableDirectors();
                    table.setItems(directors);
                    break;
                }
                case "director by section" : {
                    labelToShow.setText("enter section number");
                    textArea.setOnAction(actionEvent1 -> {
                        Integer sectionNum = Integer.parseInt(textArea.getText());
                        ObservableList<Director> groups =
                                FXCollections.observableList(
                                        executor
                                                .getDirectorManager()
                                                .getDirectorsForSection(sectionNum)
                                );
                        initTableDirectors();
                        table.setItems(groups);
                    });
                    break;
                }
             }
        });
    }

    private void initTableDirectors() {
        table.getColumns().clear();
        TableColumn<Director, String> first_name = new TableColumn<>("first_name");
        TableColumn<Director, String> second_name = new TableColumn<>("second_name");
        TableColumn<Director, String> last_name = new TableColumn<>("last_name");
        TableColumn<Director, Integer> age = new TableColumn<>("age");
        TableColumn<Director, String> birthday = new TableColumn<>("birthday");
        TableColumn<Director, Integer> salary = new TableColumn<>("salary");
        TableColumn<Director, String> start_date = new TableColumn<>("start_date");
        first_name.setCellValueFactory(new PropertyValueFactory<Director, String>("first_name"));
        second_name.setCellValueFactory(new PropertyValueFactory<Director, String>("second_name"));
        last_name.setCellValueFactory(new PropertyValueFactory<Director, String>("last_name"));
        age.setCellValueFactory(new PropertyValueFactory<Director, Integer>("age"));
        birthday.setCellValueFactory(new PropertyValueFactory<Director, String>("birthday"));
        salary.setCellValueFactory(new PropertyValueFactory<Director, Integer>("salary"));
        start_date.setCellValueFactory(new PropertyValueFactory<Director, String>("start_date"));
        table.getColumns().add(first_name);
        table.getColumns().add(second_name);
        table.getColumns().add(last_name);
        table.getColumns().add(age);
        table.getColumns().add(birthday);
        table.getColumns().add(salary);
        table.getColumns().add(start_date);
    }

    private void initTableSections() {
        table.getColumns().clear();
        TableColumn<Section, String> sport = new TableColumn<>("sport");
        TableColumn<Section, Integer> section_number = new TableColumn<>("section_number");
        TableColumn<Section, Integer> director_number = new TableColumn<>("director_number");
        sport.setCellValueFactory(new PropertyValueFactory<Section, String>("sport"));
        section_number.setCellValueFactory(new PropertyValueFactory<Section, Integer>("section_number"));
        director_number.setCellValueFactory(new PropertyValueFactory<Section, Integer>("director_number"));
        table.getColumns().add(sport);
        table.getColumns().add(section_number);
        table.getColumns().add(director_number);
    }

    private void initTableRoutes() {
        table.getColumns().clear();
        TableColumn<Route, String> title = new TableColumn<>("title");
        title.setCellValueFactory(new PropertyValueFactory<Route, String>("route_title"));
        table.getColumns().add(title);
    }

    private void initTableGroups() {
        table.getColumns().clear();
        TableColumn<Group, Integer> group_num = new TableColumn<>("group_num");
        TableColumn<Group, Integer> section_num = new TableColumn<>("section_num");
        TableColumn<Group, String> first_name_coach = new TableColumn<>("first_name_coach");
        TableColumn<Group, String> second_name_coach = new TableColumn<>("second_name_coach");
        TableColumn<Group, String> coach_card_code = new TableColumn<>("coach_card_code");

        group_num.setCellValueFactory(new PropertyValueFactory<Group, Integer>("group_num"));
        section_num.setCellValueFactory(new PropertyValueFactory<Group, Integer>("section_num"));
        first_name_coach.setCellValueFactory(new PropertyValueFactory<Group, String>("first_name_coach"));
        second_name_coach.setCellValueFactory(new PropertyValueFactory<Group, String>("second_name_coach"));
        coach_card_code.setCellValueFactory(new PropertyValueFactory<Group, String>("coach_card_code"));

        table.getColumns().add(group_num);
        table.getColumns().add(section_num);
        table.getColumns().add(first_name_coach);
        table.getColumns().add(second_name_coach);
        table.getColumns().add(coach_card_code);
    }

    private void initTableHikes() {
        table.getColumns().clear();
        TableColumn<Hike, String> route = new TableColumn<>("route");
        TableColumn<Hike, Integer> days_count = new TableColumn<>("days_count");
        TableColumn<Hike, String> start_date = new TableColumn<>("start_date");
        TableColumn<Hike, String> coach_first_name = new TableColumn<>("coach_first_name");
        TableColumn<Hike, String> coach_second_name = new TableColumn<>("coach_second_name");
        TableColumn<Hike, String> sport = new TableColumn<>("sport");
        route.setCellValueFactory(new PropertyValueFactory<Hike, String>("route"));
        start_date.setCellValueFactory(new PropertyValueFactory<Hike, String>("start_date"));
        days_count.setCellValueFactory(new PropertyValueFactory<Hike, Integer>("days_count"));
        coach_first_name.setCellValueFactory(new PropertyValueFactory<Hike, String>("coach_first_name"));
        coach_second_name.setCellValueFactory(new PropertyValueFactory<Hike, String>("coach_second_name"));
        sport.setCellValueFactory(new PropertyValueFactory<Hike, String>("sport"));
        table.getColumns().add(route);
        table.getColumns().add(days_count);
        table.getColumns().add(start_date);
        table.getColumns().add(coach_first_name);
        table.getColumns().add(coach_second_name);
        table.getColumns().add(sport);
    }

    private void initTableCompet() {
        table.getColumns().clear();
        TableColumn<Competition, String> title = new TableColumn<>("title");
        TableColumn<Competition, String> start_date = new TableColumn<>("start_date");
        TableColumn<Competition, String> finish_date = new TableColumn<>("finish_date");
        title.setCellValueFactory(new PropertyValueFactory<Competition, String>("title"));
        start_date.setCellValueFactory(new PropertyValueFactory<Competition, String>("start_date"));
        finish_date.setCellValueFactory(new PropertyValueFactory<Competition, String>("finish_date"));
        table.getColumns().add(title);
        table.getColumns().add(start_date);
        table.getColumns().add(finish_date);
    }

    private void initTableCoaches() {
        table.getColumns().clear();
        TableColumn<Coach, String> first_name = new TableColumn<>("first_name");
        first_name.setCellValueFactory(new PropertyValueFactory<Coach, String>("first_name"));
        TableColumn<Coach, String> second_name = new TableColumn<>("second_name");
        second_name.setCellValueFactory(new PropertyValueFactory<Coach, String>("second_name"));
        TableColumn<Coach, String> last_name = new TableColumn<>("last_name");
        last_name.setCellValueFactory(new PropertyValueFactory<Coach, String>("last_name"));
        TableColumn<Coach, String> gender = new TableColumn<>("gender");
        gender.setCellValueFactory(new PropertyValueFactory<Coach,String>("gender"));
        TableColumn<Coach, String> birthday = new TableColumn<>("birthday");
        birthday.setCellValueFactory(new PropertyValueFactory<Coach,String>("birthday"));
        TableColumn<Coach, String> card_code = new TableColumn<>("card_code");
        card_code.setCellValueFactory(new PropertyValueFactory<Coach,String>("card_code"));
        TableColumn<Coach, String> start_date = new TableColumn<>("start_date");
        start_date.setCellValueFactory(new PropertyValueFactory<Coach,String>("start_date"));
        TableColumn<Coach, Integer> section_num = new TableColumn<>("section_num");
        section_num.setCellValueFactory(new PropertyValueFactory<Coach,Integer>("section_num"));
        TableColumn<Coach, Integer> salary = new TableColumn<>("salary");
        salary.setCellValueFactory(new PropertyValueFactory<Coach,Integer>("salary"));
        table.getColumns().add(first_name);
        table.getColumns().add(second_name);
        table.getColumns().add(last_name);
        table.getColumns().add(gender);
        table.getColumns().add(birthday);
        table.getColumns().add(card_code);
        table.getColumns().add(start_date);
        table.getColumns().add(section_num);
        table.getColumns().add(salary);

    }

    private void initTableTourists() {
        table.getColumns().clear();
        TableColumn<Tourist, String> first_name = new TableColumn<>("first_name");
        first_name.setCellValueFactory(new PropertyValueFactory<Tourist, String>("first_name"));
        TableColumn<Tourist, String> second_name = new TableColumn<>("second_name");
        second_name.setCellValueFactory(new PropertyValueFactory<Tourist, String>("second_name"));
        TableColumn<Tourist, String> last_name = new TableColumn<>("last_name");
        last_name.setCellValueFactory(new PropertyValueFactory<Tourist, String>("last_name"));
        TableColumn<Tourist, String> gender = new TableColumn<>("gender");
        gender.setCellValueFactory(new PropertyValueFactory<Tourist,String>("gender"));
        TableColumn<Tourist, String> birthday = new TableColumn<>("birthday");
        birthday.setCellValueFactory(new PropertyValueFactory<Tourist,String>("birthday"));
        TableColumn<Tourist, String> card_code = new TableColumn<>("card_code");
        card_code.setCellValueFactory(new PropertyValueFactory<Tourist,String>("card_code"));
        table.getColumns().add(first_name);
        table.getColumns().add(second_name);
        table.getColumns().add(last_name);
        table.getColumns().add(gender);
        table.getColumns().add(birthday);
        table.getColumns().add(card_code);
    }

}
