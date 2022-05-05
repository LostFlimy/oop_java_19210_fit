import java.net.URL;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

import DBController.DBExecutor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Competition;
import model.Group;

public class ChangeTabController {
    private final ObservableList<String> tables = FXCollections.observableArrayList(
            "competition",
            "group"
    );

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addbtn;

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private Label label1;

    @FXML
    private Label label2;

    @FXML
    private Label label3;

    @FXML
    private Label label4;

    @FXML
    private Label label5;

    @FXML
    private Label label6;

    @FXML
    private Label errorlabel;

    @FXML
    private TextField textfield1;

    @FXML
    private TextField textfield2;

    @FXML
    private TextField textfield3;

    @FXML
    private TextField textfield4;

    @FXML
    private TextField textfield5;

    @FXML
    private TextField textfield6;

    @FXML
    void initialize() {
        DBExecutor executor = DBExecutor.getInstance();
        comboBox.setItems(tables);
        label1.setVisible(false);
        label2.setVisible(false);
        label3.setVisible(false);
        label4.setVisible(false);
        label5.setVisible(false);
        label6.setVisible(false);
        textfield1.setVisible(false);
        textfield2.setVisible(false);
        textfield3.setVisible(false);
        textfield4.setVisible(false);
        textfield5.setVisible(false);
        textfield6.setVisible(false);
        comboBox.setOnAction(actionEvent -> {
            switch (comboBox.getValue()) {
                case "group": {
                    label4.setVisible(false);
                    label5.setVisible(false);
                    label6.setVisible(false);
                    textfield4.setVisible(false);
                    textfield5.setVisible(false);
                    textfield6.setVisible(false);
                    label1.setText("group number");
                    label2.setText("section number");
                    label3.setText("coach card code");
                    label1.setVisible(true);
                    label2.setVisible(true);
                    label3.setVisible(true);
                    textfield1.setVisible(true);
                    textfield2.setVisible(true);
                    textfield3.setVisible(true);
                    addbtn.setOnAction(actionEvent1 -> {
                        Group newGroup = new Group(
                                Integer.parseInt(textfield1.getText()),
                                Integer.parseInt(textfield2.getText()),
                                "",
                                "",
                                textfield3.getText()
                        );
                        try {
                            executor.getGroupManager().addGroup(newGroup);
                            errorlabel.setText("successfull added new field");
                        } catch (SQLException throwables) {
                            errorlabel.setText(throwables.getMessage());
                        }
                    });
                    break;
                }
                case "competition" : {
                    label4.setVisible(false);
                    label5.setVisible(false);
                    label6.setVisible(false);
                    textfield4.setVisible(false);
                    textfield5.setVisible(false);
                    textfield6.setVisible(false);
                    label1.setText("title");
                    label2.setText("start_date");
                    label3.setText("finish_date");
                    label1.setVisible(true);
                    label2.setVisible(true);
                    label3.setVisible(true);
                    textfield1.setVisible(true);
                    textfield2.setText("yyyy-mm-dd");
                    textfield3.setText("yyyy-mm-dd");
                    textfield2.setVisible(true);
                    textfield3.setVisible(true);
                    addbtn.setOnAction(actionEvent1 -> {
                        Competition newComp = new Competition(
                                textfield1.getText(),
                                textfield2.getText(),
                                textfield3.getText()
                        );
                        try {
                            executor.getCompetitionManager().addCompetition(newComp);
                            errorlabel.setText("successfull added new field");
                        } catch (SQLException throwables) {
                            errorlabel.setText(throwables.getMessage());
                        }
                    });
                    break;
                }
            }
        });
    }

}
