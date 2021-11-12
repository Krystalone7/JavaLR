package document;

import functions.FunctionPoint;
import functions.FunctionPointT;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class FXMLMainFormController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        funParametersDialog = new FunParametersDialog();
        fileChooser = new FileChooser();
        initializeMenu();
        columnX.setCellValueFactory(new PropertyValueFactory<>("X"));
        columnY.setCellValueFactory(new PropertyValueFactory<>("Y"));
        labelForPoints.setText("Point 0 of " + Main2.tabFDoc.getPointsCount());
    }

    @FXML
    private Label labelForPoints;

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

    @FXML
    private MenuItem newFileMenuItem;

    @FXML
    private MenuItem openFileMenuItem;

    @FXML
    private MenuItem saveFileAsNewItem;

    @FXML
    private MenuItem saveFileMenuItem;

    @FXML
    private MenuItem exitMenuItem;

    @FXML
    private Button addPointButton;

    private FunParametersDialog funParametersDialog;
    private FileChooser fileChooser;

    public void redraw(){
        table.getItems().clear();
        for (int i = 0; i < Main2.tabFDoc.getPointsCount(); i++) {
            table.getItems().add(new FunctionPointT(Main2.tabFDoc.getPointX(i), Main2.tabFDoc.getPointY(i)));
        }
        labelForPoints.setText("Point " + (table.getSelectionModel().getSelectedIndex() + 1) + " of " + Main2.tabFDoc.getPointsCount());
    }

    @FXML
    void btNewClick1(ActionEvent event) {
        FunctionPointT functionPointT = new FunctionPointT(Double.parseDouble(edX.getText()), Double.parseDouble(edY.getText()));
        Main2.tabFDoc.addPoint(new FunctionPoint(Double.parseDouble(edX.getText()), Double.parseDouble(edY.getText())));
        edX.clear();
        edY.clear();
    }

    @FXML
    void btNewClick2(ActionEvent event) {
        int index = table.getSelectionModel().getSelectedIndex();
        Main2.tabFDoc.deletePoint(index);
    }

    @FXML
    public void btNewClick3(javafx.scene.input.MouseEvent mouseEvent) {
        labelForPoints.setText("Point " + (table.getSelectionModel().getSelectedIndex() + 1) + " of " + Main2.tabFDoc.getPointsCount());
    }

    private void initializeMenu() {
        newFileMenuItem.setOnAction( event -> {
            if (cancelBecauseNotSaved()) return;
            Optional<TabFParameters> params = funParametersDialog.showAndWait();
            params.ifPresent(tabFParameters -> {
                Main2.tabFDoc.newFunction(
                        tabFParameters.leftBorderX,
                        tabFParameters.rightBorderX,
                        tabFParameters.pointCount
                );
                redraw();
            });
        });
        saveFileMenuItem.setOnAction(event -> Main2.tabFDoc.saveFunction());
        saveFileAsNewItem.setOnAction(event -> saveFileAsAction());
        openFileMenuItem.setOnAction(event -> {
            if (cancelBecauseNotSaved()) return;
            File selectedFile = fileChooser.showOpenDialog(getWindow());
            if (selectedFile != null) {
                Main2.tabFDoc.loadFunction(selectedFile.getAbsolutePath());
                redraw();
            }
        });
        exitMenuItem.setOnAction(event -> getWindow().close());

    }

    private void saveFileAsAction() {
        File saveFile = fileChooser.showSaveDialog(getWindow());
        Main2.tabFDoc.saveFunctionAs(saveFile.getAbsolutePath());
    }

    private Stage getWindow() {
        return (Stage) addPointButton.getScene().getWindow();
    }

    private boolean cancelBecauseNotSaved() {
        if (Main2.tabFDoc.isModified()) {
            Optional<ButtonType> result = new Alert(
                    Alert.AlertType.CONFIRMATION,
                    "Сохранить?",
                    ButtonType.CANCEL, ButtonType.NO, ButtonType.YES
            ).showAndWait();

            if (result.isPresent()) {
                ButtonType buttonType = result.get();

                if (ButtonType.CANCEL == buttonType) return true;

                if (ButtonType.YES == buttonType) {
                    Main2.tabFDoc.saveFunction();
                }
            } else {
                return true;
            }
        }
        return false;
    }
}
