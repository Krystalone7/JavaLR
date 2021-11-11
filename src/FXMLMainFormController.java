import functions.FunctionPoint;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class FXMLMainFormController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        columnX.setCellValueFactory(new PropertyValueFactory<>("X"));
        columnY.setCellValueFactory(new PropertyValueFactory<>("Y"));
        for (int i = 0; i < Main2.tabFDoc.getPointsCount(); i++) {
            table.getItems().add(new FunctionPointT(Main2.tabFDoc.getPointX(i), Main2.tabFDoc.getPointY(i)));
        }
    }

    @FXML
    private TableColumn<FunctionPointT, Double> columnX;

    @FXML
    private TableColumn<FunctionPointT, Double> columnY;

    @FXML
    private TextField edX;

    @FXML
    private TextField edY;

    @FXML
    private TableView<FunctionPointT> table;

    public void redraw(){
        Main2.tabFDoc.saveFunction();
    }

    @FXML
    void btNewClick1(ActionEvent event) {
        FunctionPointT functionPointT = new FunctionPointT(Double.parseDouble(edX.getText()), Double.parseDouble(edY.getText()));
        table.getItems().add(functionPointT);
        edX.clear();
        edY.clear();
        redraw();
    }

    @FXML
    void btNewClick2(ActionEvent event) {
        table.getItems().removeAll(table.getSelectionModel().getSelectedItem());
        redraw();
    }

}
