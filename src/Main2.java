
import document.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main2 extends Application {
    public static TabulatedFunctionDoc tabFDoc;

    @Override
    public void start(Stage stage) throws Exception {
        tabFDoc = new TabulatedFunctionDoc();
        tabFDoc.newFunction(0,10,5);
        tabFDoc.saveFunctionAs("tabFDoc");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLMAINFORM.fxml"));
        Parent root = loader.load();
        FXMLMainFormController ctrl = loader.getController();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
