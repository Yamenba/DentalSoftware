/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consultorio.dental;

import datos.Ajustes;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * FXML Controller class
 *
 * @author Ricardo
 */
public class ConfiguracionController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    ComboBox comboBox_activado;
    @FXML
    TextField textfield_titulo;
    @FXML
    TextArea textArea_descripcion;
    @FXML
    Label label_correo;
    PacienteQuery query = new PacienteQuery();
    List<Ajustes> lista_ajustes = new ArrayList<>();
    Ajustes ajustes;
    String correo;
    String titulo;
    String descripcion;
    Boolean activado;
    Boolean nuevo;
    @FXML
    AnchorPane  pane;
    @FXML
    Button boton;
 
    @FXML
    public void actionBotonGuardar(ActionEvent e) throws IOException{
        titulo = textfield_titulo.getText();
        descripcion = textArea_descripcion.getText();
        if(titulo==null || descripcion==null){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No puedes dejar campos vacíos");
            alert.showAndWait();
        }else{
            String activado_string = (String) comboBox_activado.getValue();
            if(label_correo.getText().equals("No hay una cuenta vinculada") && activado_string.equals("Activado")){
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("No hay una cuenta vinculada");
                alert.setHeaderText("Tienes que vincular una cuenta de Google");
                alert.setContentText("Tienes que haber vinculado una cuenta de Google para poder activar "
                        + "los recordatorios por e-mail.");
                alert.showAndWait();
            }else{
                if(activado_string.equals("Activado")){
                    activado = true;
                    
                }else{
                    activado = false;
                }
                correo = label_correo.getText();
                query.updateAjustes(activado, titulo, descripcion,correo);
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Se guardaron los datos");
                alert.setHeaderText("Se guardaron los datos con éxito");
                alert.showAndWait();
                AnchorPane page = new AnchorPane();
                try {
                    page = (AnchorPane) FXMLLoader.load(getClass().getResource("Configuracion.fxml"));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                pane.getChildren().clear();///name of pane where you want to put the fxml.
                pane.getChildren().add(page);
            }
 
        }   
    }
    @FXML
    public void actionBotonCambiar(ActionEvent e) throws InterruptedException, Exception{
        Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Es necesaria tu autorización");
                alert.setHeaderText("Es necesario tu autorización");
                alert.setContentText("Es necesario que autorices a Dental Software para que tenga "
                                    + "acceso a tu cuenta de Google, a continuación se abrirá una pestaña "
                                    + "en tu navegador para que realices esta tarea.");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    taskCambia();
                } 
           
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       // TODO
        comboBox_activado.getItems().addAll(
            "Activado",
            "Desactivado"
        );
        lista_ajustes = query.ajustes();
        ajustes = lista_ajustes.get(0);
        if(ajustes.getRecordatorioActivado()){
            comboBox_activado.setValue("Activado");
        }else{
            comboBox_activado.setValue("Desactivado");
        }
        textfield_titulo.setText(ajustes.getRecordatorioTitulo());
        textArea_descripcion.setText(ajustes.getRecordatorioDescripcion());
        label_correo.setText(ajustes.getCorreo());
        if(label_correo.getText().equals("No hay una cuenta vinculada")){
            boton.setText("Vincular cuenta");
        }
        
    }
    
 
    
    private void taskCambia() {

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
                File f = new File(System.getProperty("user.home")+ "/.credentials/gmail-java-quickstart.json/StoredCredential");
                if(f.exists() && !f.isDirectory()) { 
                    correo = correoUsuario("https://www.googleapis.com/oauth2/v1/userinfo?access_token="+RecordatorioMail.renovar().getAccessToken());
                }else{
                    correo = correoUsuario("https://www.googleapis.com/oauth2/v1/userinfo?access_token="+RecordatorioMail.authorize().getAccessToken());     
                }
                    
                query.updateAjustesCorreo(correo);
                return null;
            }
        };

        longTask.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent t) {
                taskUpdateStage.hide();
            
                Alert alert = new Alert(AlertType.INFORMATION);
                if(label_correo.getText().equals("No hay una cuenta vinculada")){
                    alert.setTitle("Se vinculó la cuenta de Google");
                    alert.setHeaderText("Se vinculó la cuenta de Google exitósamente");
                }else{
                    alert.setTitle("Se cambió la cuenta de Google");
                    alert.setHeaderText("Se cambió la cuenta de Google exitósamente");  
                }
                alert.showAndWait();
                
                AnchorPane page = new AnchorPane();
                try {
                    page = (AnchorPane) FXMLLoader.load(getClass().getResource("Configuracion.fxml"));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                pane.getChildren().clear();
                pane.getChildren().add(page);
        
            }
        });

        taskUpdateStage.show();
        new Thread(longTask).start();
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
     
     
     
     
    
}
