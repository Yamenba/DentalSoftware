package consultorio.dental;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.stage.*;

/**
 * Example of displaying a splash page for a standalone JavaFX application
 */
public class FadeApp extends Application {

    private Pane splashLayout;
    private Label progressText;

    public static void main(String[] args) throws Exception {
        launch(args);
    }

  

    @Override
    public void start(final Stage initStage) throws Exception {
        ImageView splash = new ImageView(new Image(getClass().getResourceAsStream("logo_splash.png")));
        splash.setFitHeight(150);
        splash.setFitWidth(400);
        progressText = new Label("Cargando . . .");
        splashLayout = new VBox();
        splashLayout.getChildren().addAll(splash, progressText);
        splashLayout.setStyle("-fx-background-color: #ffffff; -fx-border-width:1; -fx-border-color:#000000");
        progressText.setAlignment(Pos.CENTER);
        splashLayout.setEffect(new DropShadow());
        Scene scene = new Scene(splashLayout);
        initStage.initStyle(StageStyle.UNDECORATED);
        initStage.setScene(scene);
        initStage.show();
        initStage.toFront();
        initStage.setIconified(false);
        
        Platform.runLater(new Runnable() {
              @Override
              public void run() {
                  try {
                      ConsultorioDental aplicacion = new ConsultorioDental();
                      aplicacion.start(new Stage());
                  } catch (Exception ex) {
                      Logger.getLogger(FadeApp.class.getName()).log(Level.SEVERE, null, ex);
                  }
                  initStage.close();
              }
            });   
    }
}