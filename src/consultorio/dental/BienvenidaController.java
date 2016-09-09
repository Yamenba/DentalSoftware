/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consultorio.dental;

import com.google.api.client.json.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * FXML Controller class
 *
 * @author Ricardo
 */
public class BienvenidaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    PacienteQuery query = new PacienteQuery();
    @FXML
    public void actionBotonSi(ActionEvent actionEvent) throws Exception{
            Node  source = (Node)  actionEvent.getSource(); 
            Stage bienvenida  = (Stage) source.getScene().getWindow();
            bienvenida.close();
            runTask();
            
    }
    
    @FXML
    public void actionBotonNo(ActionEvent actionEvent) throws IOException{
            query.nuevosAjustes(false,"No hay una cuenta vinculada");
            Node  source = (Node)  actionEvent.getSource(); 
            Stage bienvenida  = (Stage) source.getScene().getWindow();
            bienvenida.close();
            Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.getIcons().add(new javafx.scene.image.Image(getClass().getResourceAsStream("dental6.png")));
            stage.setTitle("Dental Software"); 
            stage.setResizable(false);
            
            FadeTransition ft = new FadeTransition(Duration.millis(3000), root);
            ft.setFromValue(0.0);
            ft.setToValue(1.0);
            ft.play();
            stage.show();
            stage.setIconified(false);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
     public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
          BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
          String jsonText = readAll(rd);
          JSONObject json = new JSONObject(jsonText);
          return json;
        } finally {
          is.close();
        }
    }
     
     private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
          sb.append((char) cp);
        }
        return sb.toString();
      }
     
     private String correoUsuario(String url) throws IOException{
         JSONObject json = readJsonFromUrl(url);
         return json.getString("email"); // http://stackoverflow.com/questions/15104682/how-to-get-user-email-with-google-access-token
     }
     
     private void runTask() {

        final double wndwWidth = 300.0d;
        Label updateLabel = new Label("Esperando autorización de Google...");
        updateLabel.setPrefWidth(wndwWidth);
        ProgressBar progress = new ProgressBar();
        progress.setPrefWidth(wndwWidth);

        VBox updatePane = new VBox();
        updatePane.setPadding(new Insets(10));
        updatePane.setSpacing(5.0d);
        updatePane.getChildren().addAll(updateLabel, progress);

        Stage taskUpdateStage = new Stage();
        taskUpdateStage.initModality(Modality.APPLICATION_MODAL);
        taskUpdateStage.setScene(new Scene(updatePane));
        taskUpdateStage.show();

        Task longTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
               RecordatorioMail recordatorio = new RecordatorioMail();
                String correo = correoUsuario("https://www.googleapis.com/oauth2/v1/userinfo?access_token="+recordatorio.authorize().getAccessToken());
                query.nuevosAjustes(true,correo);
                return null;
            }
        };

        longTask.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent t) {
                taskUpdateStage.hide();
                Parent root;
                try {
                    root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                    Stage stage = new Stage();
                    stage.initModality(Modality.APPLICATION_MODAL);
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.getIcons().add(new javafx.scene.image.Image(getClass().getResourceAsStream("dental6.png")));
                    stage.setTitle("Dental Software"); 
                   stage.setResizable(false);

                    FadeTransition ft = new FadeTransition(Duration.millis(3000), root);
                    ft.setFromValue(0.0);
                    ft.setToValue(1.0);
                    ft.play();
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(BienvenidaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            }
        });

        taskUpdateStage.show();
        new Thread(longTask).start();
    }
}
