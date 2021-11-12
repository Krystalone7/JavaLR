package document;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class FunctionParametersController implements Initializable {

    @FXML
    private TextField leftDomainTextField;

    @FXML
    private Spinner<Integer> pointsCountSpinner;

    @FXML
    private TextField rightDomainTextField;

    private TabFParameters tabFParameters;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SpinnerValueFactory.IntegerSpinnerValueFactory pointsCountValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(2, Integer.MAX_VALUE);
        pointsCountSpinner.setValueFactory(pointsCountValueFactory);
    }

    boolean okAction() {
        double left = Double.parseDouble(leftDomainTextField.getText());
        double right = Double.parseDouble(rightDomainTextField.getText());
        int pointsCount = pointsCountSpinner.getValue();
        tabFParameters = new TabFParameters(left, right, pointsCount);
        return true;
    }

    void cancelAction() {
        tabFParameters = null;
    }

    public TabFParameters getParameters() {
        return tabFParameters;
    }
}