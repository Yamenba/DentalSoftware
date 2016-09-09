/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consultorio.dental;

import datos.Paciente;
import java.io.IOException;
import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import javafx.util.Duration;

/**
 *
 * @author Ricardo
 */
public class FXMLDocumentController implements Initializable {
    
    //@FXML
    //private Tab tab_home;
    
    //@FXML
    //private Tab tab_pacientes;
    
    //@FXML
    //private ListView pacientes_listView;
    
    //@FXML
    //private Button boton_borrar_paciente;
    
    @FXML
    private AnchorPane izq_pane;
    
    @FXML
    private AnchorPane pane_izq_arriba;
    
    @FXML
    private ToggleButton boton_inicio;
    
    @FXML
    private ToggleButton boton_pacientes;
    
    @FXML 
    private ToggleButton boton_agenda;
    
    @FXML
    private ToggleButton boton_pagos;
    
    @FXML
    private ToggleButton boton_configuracion;
    
    @FXML
    private AnchorPane pane;
    
    final ToggleGroup group = new ToggleGroup();
    
    private List<Paciente> pacientes = new ArrayList<>();
    
    private PacienteQuery query = new PacienteQuery();
    
    private ObservableList<Paciente> pacientes_list; 
    
    @FXML
    public void actionBotonInformacion(ActionEvent e){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText("Desarrollado por: \nRicardo Sosa (ricardo.sosa.1993@gmail.com)");
        alert.showAndWait();
    }
    
    @FXML
    public void actionBotonPacientes(ActionEvent e) throws IOException{
        AnchorPane page = (AnchorPane) FXMLLoader.load(getClass().getResource("pacientes.fxml"));
        pane.getChildren().clear();///name of pane where you want to put the fxml.
        pane.getChildren().add(page);
        FadeTransition ft = new FadeTransition(Duration.millis(1000), pane);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.play();
    }
    
    @FXML
    public void actionBotonInicio(ActionEvent e) throws IOException{
        AnchorPane page = (AnchorPane) FXMLLoader.load(getClass().getResource("inicio.fxml"));
        pane.getChildren().clear();///name of pane where you want to put the fxml.
        pane.getChildren().add(page);
        FadeTransition ft = new FadeTransition(Duration.millis(1000), pane);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.play();
    }
    
    @FXML
    public void actionBotonAgenda(ActionEvent e) throws IOException{
        Node node = (Node) e.getSource();
        Scene scene = node.getScene();
        scene.setCursor(Cursor.WAIT);
        Platform.runLater(new Runnable() {
          @Override
          public void run() {
            AnchorPane page;
              try {
                     page = (AnchorPane) FXMLLoader.load(getClass().getResource("agendaPacientes.fxml"));
                    pane.getChildren().clear();///name of pane where you want to put the fxml.
                    pane.getChildren().add(page);
                    FadeTransition ft = new FadeTransition(Duration.millis(1000), pane);
                    ft.setFromValue(0.0);
                    ft.setToValue(1.0);
                    ft.play();
              } catch (IOException ex) {
                  Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
              }
            scene.setCursor(Cursor.DEFAULT);
          }
        });
    }
    
    @FXML
    public void actionBotonPagos(ActionEvent e) throws IOException{
        AnchorPane page = (AnchorPane) FXMLLoader.load(getClass().getResource("Pagos.fxml"));
        pane.getChildren().clear();///name of pane where you want to put the fxml.
        pane.getChildren().add(page);
        FadeTransition ft = new FadeTransition(Duration.millis(1000), pane);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.play();
    }
    
    @FXML
    public void actionBotonConfiguracion(ActionEvent e) throws IOException{
        AnchorPane page = (AnchorPane) FXMLLoader.load(getClass().getResource("Configuracion.fxml"));
        pane.getChildren().clear();///name of pane where you want to put the fxml.
        pane.getChildren().add(page);
        FadeTransition ft = new FadeTransition(Duration.millis(1000), pane);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.play();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image img_inicio = new Image(getClass().getResourceAsStream("Home_Icon_Silhouette_32.png"));
        Image img_pacientes = new Image(getClass().getResourceAsStream("Users_group_32.png"));
        Image img_agenda = new Image(getClass().getResourceAsStream("Spring_desktop_calendar_variant_32.png"));
        Image img_pagos = new Image(getClass().getResourceAsStream("give-money.png"));
        Image img_configuracion = new Image(getClass().getResourceAsStream("settings-gears.png"));
        boton_inicio.setGraphic(new ImageView(img_inicio));
        boton_pacientes.setGraphic(new ImageView(img_pacientes));
        boton_agenda.setGraphic(new ImageView(img_agenda));
        boton_pagos.setGraphic(new ImageView(img_pagos));
        boton_configuracion.setGraphic(new ImageView(img_configuracion));
        
        izq_pane.setStyle("-fx-background-color: #214D74;");
        pane_izq_arriba.setStyle("-fx-background-color: #214D74;");
        
        boton_inicio.setToggleGroup(group);
        boton_pacientes.setToggleGroup(group);
        boton_agenda.setToggleGroup(group);
        boton_pagos.setToggleGroup(group);
        boton_configuracion.setToggleGroup(group);
        boton_inicio.setSelected(true);
        
        
        try {
            AnchorPane page = (AnchorPane) FXMLLoader.load(getClass().getResource("inicio.fxml"));
            pane.getChildren().add(page);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    
  }
}
