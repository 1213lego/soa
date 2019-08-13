package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BikeProjectMain extends Application {
    private static Scene scene;
    @Override
    public void start(Stage primaryStage) throws IOException{
        scene = new Scene(loadFXML("main"));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void setRoot(String fxml){
        try{
            scene.setRoot(loadFXML(fxml));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    private static Parent loadFXML(String fxml) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(BikeProjectMain.class.getResource("./view/"+fxml+".fxml"));
        return fxmlLoader.load();
    }
    public static void launchNewWindows(String fxml){
        try {
            Scene scene = new Scene(loadFXML(fxml));
            Stage stage = new Stage();
            stage.setTitle("New Window");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch();
    }
}
