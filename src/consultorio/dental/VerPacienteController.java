/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consultorio.dental;

import com.google.api.client.util.DateTime;
import datos.Ajustes;
import datos.Citas;
import datos.DatosMedicosPaciente;
import datos.Paciente;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.Duration;
import jfxtras.scene.control.LocalTimePicker;

/**
 * FXML Controller class
 *
 * @author Ricardo
 */
public class VerPacienteController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    private int id;
    
    @FXML
    private Label label_nombre;
    
    @FXML
    private Label label_edad;
    
    @FXML
    private Label label_direccion;
    
    @FXML
    private Label label_ciudad;
    
    @FXML
    private Label label_tutor;
    
    @FXML
    private Label label_ocupacion;
    
    @FXML
    private Label label_telefono;
    
    @FXML
    private Label label_correo;

    
    @FXML
    private Button boton_borrar_paciente;
    
    @FXML
    private AnchorPane pane;
    
    @FXML
    private Button boton_atras;
    
    @FXML
    private ImageView imageView_fotoPerfil;
    
    
    
    private List<Paciente> pacientes = new ArrayList<>();
    
    private PacienteQuery query = new PacienteQuery();
    
    private Paciente p;
    
    private int edad;
    
    private List<DatosMedicosPaciente> lista_datos_medicos = new ArrayList<>();
    
    private DatosMedicosPaciente datos_medicos;
    
    
    @FXML
    public void actionBotonHistoriaClinica(ActionEvent e){
            Node node = (Node) e.getSource();
            Scene scene = node.getScene();
            scene.setCursor(Cursor.WAIT);
            Platform.runLater(new Runnable() {
              @Override
              public void run() {
                FXMLLoader loader = new FXMLLoader(getClass().getResource( "AgregaPaciente.fxml"));
            try {
                AnchorPane page = (AnchorPane) loader.load();
                pane.getChildren().clear();
                pane.getChildren().add(page);
                AgregaPacienteController controller = loader.<AgregaPacienteController>getController();
                controller.initData(p.getIdpaciente(),new FXMLLoader(getClass().getResource("verPaciente.fxml")),"paciente",p.getIdpaciente());

                FadeTransition ft = new FadeTransition(Duration.millis(1000), pane);
                ft.setFromValue(0.0);
                ft.setToValue(1.0);
                ft.play();
            } catch (IOException ex) {
                Logger.getLogger(PacientesController.class.getName()).log(Level.SEVERE, null, ex);
            } 
                scene.setCursor(Cursor.DEFAULT);
              }
            });
    }

    
    public void actionBotonRegistrarPago(ActionEvent e) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RegistraPagoPaciente.fxml"));
            Parent root1 = (Parent) loader.load();
            RegistraPagoPacienteController controller = loader.<RegistraPagoPacienteController>getController();
                controller.initData(p);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Registrar Pago");
            stage.setScene(new Scene(root1));  
            stage.show();
    }
    
    @FXML
    public void actionBotonCostoTratamiento(ActionEvent e) throws IOException{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("CostoTratamiento.fxml"));
            Parent root1 = (Parent) loader.load();
            CostoTratamientoController controller = loader.<CostoTratamientoController>getController();
                controller.initData(p);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Costo de tratamiento");
            stage.setScene(new Scene(root1));  
            stage.show();
    }
    
    @FXML
    public void actionBotonEditarPagos(ActionEvent e) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListaPagos.fxml"));
            Parent root1 = (Parent) loader.load();
            ListaPagosController controller = loader.<ListaPagosController>getController();
            controller.initData(p.getIdpaciente());
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Pagos");
            stage.setScene(new Scene(root1));  
            stage.show();
    }
    
    @FXML 
    public void actionBotonEditarCitas(ActionEvent e) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListaCitas.fxml"));
            Parent root1 = (Parent) loader.load();
            ListaCitasController controller = loader.<ListaCitasController>getController();
            controller.initData(p.getIdpaciente());
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Citas");
            stage.setScene(new Scene(root1));  
            stage.show();
    }
   /////////////////////////////////////////nuevaa citaaaaa 
    @FXML
    public void actionBotonNuevaCita(ActionEvent e){
         Stage primaryStage = new Stage();
       primaryStage.initStyle(StageStyle.UNDECORATED);
       primaryStage.initModality(Modality.APPLICATION_MODAL);
        
      
     
        
        GridPane gridpane = new GridPane();
        Scene scene = new Scene(gridpane, 440, 375);
         scene.getStylesheets().addAll(this.getClass().getResource("estilo.css").toExternalForm());
       
        gridpane.setStyle("-fx-background-color: #ffffff; -fx-border-color:#214D74;");
        gridpane.setPrefSize(440, 375);
        gridpane.setHgap(5);
        gridpane.setVgap(5);
        
        
        
        Label candidatesLbl = new Label("Nueva cita");
        GridPane.setHalignment(candidatesLbl, HPos.RIGHT);
        candidatesLbl.setStyle("-fx-text-fill: #214D74; -fx-font-size: 20pt;");
        gridpane.add(candidatesLbl, 0, 0);
        
  
        List<Ajustes> lista_ajustes = new ArrayList<>();
        lista_ajustes = query.ajustes();
        Ajustes ajustes = lista_ajustes.get(0);
        CheckBox checkbox_recordatorio = new CheckBox("Crear evento en Google Calendar");
        gridpane.add(checkbox_recordatorio,1,4);
     
        if(ajustes.getRecordatorioActivado()){
            checkbox_recordatorio.setSelected(true);
        }else{
            checkbox_recordatorio.setVisible(false);
        }
 
        Label label_descripcion = new Label("Motivo de la cita");
        gridpane.add(label_descripcion, 0, 6);
        label_descripcion.setStyle("-fx-text-fill: #214D74; -fx-font-size: 9pt;");
        GridPane.setHalignment(label_descripcion, HPos.RIGHT);
        
        TextField texto_descripcion = new TextField();
        gridpane.add(texto_descripcion, 1, 6);
        GridPane.setHalignment(texto_descripcion, HPos.LEFT);
        
        Label label_dia = new Label("Día");
        gridpane.add(label_dia, 0, 7);
        label_dia.setStyle("-fx-text-fill: #214D74; -fx-font-size: 9pt;");
        GridPane.setHalignment(label_dia, HPos.RIGHT);
        
        DatePicker dia = new DatePicker();
        gridpane.add(dia, 1, 7);
        
        Label label_hora = new Label("Desde");
        gridpane.add(label_hora, 0, 8);
        label_hora.setStyle("-fx-text-fill: #214D74; -fx-font-size: 9pt;");
        GridPane.setHalignment(label_hora, HPos.RIGHT);
        
        LocalTimePicker inicio = new LocalTimePicker(LocalTime.now().truncatedTo(ChronoUnit.HOURS));
        gridpane.add(inicio, 1, 8);
        
        Label label_duracion = new Label("Hasta");
        gridpane.add(label_duracion, 0, 9);
        label_duracion.setStyle("-fx-text-fill: #214D74; -fx-font-size: 9pt;");
        GridPane.setHalignment(label_duracion, HPos.RIGHT);
        
        LocalTimePicker fin = new LocalTimePicker(LocalTime.now().truncatedTo(ChronoUnit.HOURS).plusHours(1));
        gridpane.add(fin, 1, 9);

        
         ComboBox<String> cb = new ComboBox<String>();
         
         ObservableList<String> data = FXCollections.observableArrayList(
            "#AC725E",
            "#D06B64",
            "#F83A22",
            "#FA573C",
            "#FF7537",
            "#FFAD46",
            "#42D692",
            "#16A765",
            "#7BD148",
            "#B3DC6C",
            "#FBE983",
            "#FAD165",
            "#92E1C0",
            "#9FE1E7",
            "#9FC6E7",
            "#4986E7",
            "#9A9CFF",
            "#B99AFF",
            "#C2C2C2",
            "#CABDBF",
            "#CCA6AC",
            "#F691B2",
            "#CD74E6",
            "#A47AE2");
         
          cb.setItems(data);
          cb.setValue(data.get(new Random().nextInt(24)));
          gridpane.add(cb, 1, 10);
          GridPane.setHalignment(cb, HPos.LEFT);
          
           Callback<ListView<String>, ListCell<String>> factory = new Callback<ListView<String>, ListCell<String>>() {
        @Override
        public ListCell<String> call(ListView<String> list) {
            return new AgendaPacientesController.ColorRectCell();
        }
    };
           
           cb.setCellFactory(factory);
    cb.setButtonCell(factory.call(null));
    cb.setVisibleRowCount(5);

       Label label_color = new Label("Color en la agenda");
        gridpane.add(label_color, 0, 10);
        label_color.setStyle("-fx-text-fill: #214D74; -fx-font-size: 9pt;");
        GridPane.setHalignment(label_color, HPos.RIGHT);
   

        
        Button guardar = new Button("Guardar");
        Image img_guardar = new Image(getClass().getResourceAsStream("Save_Icon_Silhouette_24.png"));
        guardar.setGraphic(new ImageView(img_guardar));
        guardar.setOnAction(new EventHandler<ActionEvent>(){
            @Override 
            public void handle(ActionEvent event){
                if(dia.getValue() == null){
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Tienes que especificar un día para la cita");
                    alert.showAndWait();
                }else if(inicio.getLocalTime().compareTo(fin.getLocalTime())>0){
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Horas de la cita inválidas");
                    alert.setContentText("La hora de inicio de la cita tiene que ser menor que la hora de finalización");
                    alert.showAndWait();
                }else{
                    LocalTime  tiempo_inicio = inicio.getLocalTime(); 
                    int hora_inicio = tiempo_inicio.getHour();
                    int minuto_inicio = tiempo_inicio.getMinute();

                    LocalTime  tiempo_fin = fin.getLocalTime(); 
                    int hora_fin = tiempo_fin.getHour();
                    int minuto_fin = tiempo_fin.getMinute();

                    LocalDate lToday = dia.getValue();
                   int lTodayYear = lToday.getYear();
                    int lTodayMonth = lToday.getMonthValue();
                    int lTodayDay = lToday.getDayOfMonth();
                    String summary;
                    String id_calendar = new String();

                                summary = p.getNombre();
                                try {
                                    if(ajustes.getRecordatorioActivado() && checkbox_recordatorio.isSelected()){
                                        DateTime hora_inicio_recordatorio = new DateTime(new GregorianCalendar(lTodayYear,lTodayMonth-1,lTodayDay,hora_inicio,minuto_inicio).getTime());
                                        DateTime hora_fin_recordatorio = new DateTime(new GregorianCalendar(lTodayYear,lTodayMonth-1,lTodayDay,hora_fin,minuto_fin).getTime());
                                        TimeZone tz = Calendar.getInstance().getTimeZone();
                                        RecordatorioMail recordatorio = new RecordatorioMail();
                                        id_calendar = recordatorio.agregarRecordatorio(p.getMail(),ajustes.getRecordatorioTitulo(),ajustes.getRecordatorioDescripcion(),hora_inicio_recordatorio,hora_fin_recordatorio,tz.getID());
                                    }
      
                                } catch (Exception ex) {
                                    Logger.getLogger(AgendaPacientesController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                
                                Citas cita = new Citas();
                                cita.setAno(lTodayYear);
                                cita.setMes(lTodayMonth-1);
                                cita.setDia(lTodayDay);
                                cita.setHoraInicio(hora_inicio);
                                cita.setMinutoInicio(minuto_inicio);
                                cita.setHoraFin(hora_fin);
                                cita.setMinutoFin(minuto_fin);
                                cita.setGrupo(colorGrupo(cb.getValue()));
                                cita.setDescription(texto_descripcion.getText());
                                cita.setSummary(summary);
                                cita.setIdpaciente(p);
                                cita.setIdCalendar(id_calendar);
                                query.saveCita(cita);
                                
                                primaryStage.close(); 
                }
            }
        }
    );
        
        
        
       
        
        
        Button cancelar = new Button("Cancelar");
        Image img_cancelar = new Image(getClass().getResourceAsStream("Cancel_Button_24.png"));
        cancelar.setGraphic(new ImageView(img_cancelar));
         cancelar.setOnAction(new EventHandler<ActionEvent>(){
            @Override 
            public void handle(    ActionEvent event){
                primaryStage.close();  
            }
         });
        
         
         
         GridPane gridpane2 = new GridPane();
        
        gridpane2.setStyle("-fx-background-color: #e7f3f8;");
        
        gridpane2.setHgap(5);
        gridpane2.setVgap(0);
        gridpane.add(gridpane2, 1, 15);
        gridpane2.add(guardar, 0, 0);
        gridpane2.add(cancelar, 2, 0);
        GridPane.setHalignment(guardar, HPos.RIGHT);

        
        final ObservableList<String> rights = FXCollections.observableArrayList();
        final ListView<String> rightListView = new ListView<String>(rights);
        rightListView.setPrefWidth(150);
        rightListView.setPrefHeight(150);

       primaryStage.setScene(scene);
        
        
        FadeTransition ft = new FadeTransition(Duration.millis(1000), gridpane);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.play();
        primaryStage.show();
    }
  ///////////////////////////////////////////////////////////////////////////////////////////////////
    
    @FXML
    public void actionBotonImagenes(ActionEvent e) throws Exception{
        Galeria galeria = new Galeria();
        galeria.initData(p.getIdpaciente());
        galeria.start(new Stage());
    }
    
    @FXML
    public void actionBotonBorrar(ActionEvent e) throws IOException{
        Alert alert = new Alert(AlertType.CONFIRMATION);
       
        alert.setHeaderText("¿Seguro que quieres borrar el paciente?");
       

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            // ... user chose OK
            query.delete(p);
            
            AnchorPane page = (AnchorPane) FXMLLoader.load(getClass().getResource("pacientes.fxml"));
            pane.getChildren().clear();///name of pane where you want to put the fxml.
            pane.getChildren().add(page);
            FadeTransition ft = new FadeTransition(Duration.millis(1000), pane);
            ft.setFromValue(0.0);
            ft.setToValue(1.0);
            ft.play();
        } 
    }
    
    @FXML
    public void actionBotonAtras(ActionEvent e) throws IOException{
        AnchorPane page = (AnchorPane) FXMLLoader.load(getClass().getResource("pacientes.fxml"));
            pane.getChildren().clear();///name of pane where you want to put the fxml.
            pane.getChildren().add(page);
            FadeTransition ft = new FadeTransition(Duration.millis(1000), pane);
            ft.setFromValue(0.0);
            ft.setToValue(1.0);
            ft.play();
    }
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
    }
    
    void initData(int id_nuevo) {
        Image img_borrar_paciente = new Image(getClass().getResourceAsStream("Rubbish_bin_24.png"));
        Image img_atras = new Image(getClass().getResourceAsStream("Back_left_arrow_32.png"));
        boton_borrar_paciente.setGraphic(new ImageView(img_borrar_paciente));
         imageView_fotoPerfil.setImage(new Image(getClass().getResourceAsStream("imagen_no_disponible.jpg")));
        boton_atras.setGraphic(new ImageView(img_atras));
        id=id_nuevo;
        pacientes = query.pacientePorId(id);
        p = pacientes.get(0);
        lista_datos_medicos = query.datosMedicosPacientePorPaciente(p);
        datos_medicos = lista_datos_medicos.get(0);
        label_nombre.setText(p.getNombre());

        LocalDate today = LocalDate.now();

        if(p.getFechanac()!=null){
            LocalDate date = p.getFechanac().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            Period period = Period.between(date, today);
            edad = period.getYears();
            label_edad.setText(edad + " años");
        }
        
        try{
            imageView_fotoPerfil.setImage(new Image(new File(p.getRutaImagen()).toURI().toString()));    
        }catch(Exception ex){
                
        }


        label_direccion.setText(p.getDireccion());
        label_ciudad.setText(p.getCiudad());
        label_tutor.setText(p.getTutor());
        label_ocupacion.setText(p.getOcupacion());
        label_telefono.setText(p.getTelefono());
        label_correo.setText(p.getMail());
    
  }
    
    
  public String colorGrupo(String a){
      String b = new String();
      switch (a){
           case "#AC725E":
                b = "group00";
                    break;
           case "#D06B64":
               b = "group01";
                    break;
           case "#F83A22":
               b = "group02";
                    break;
           case "#FA573C":
               b = "group03";
                    break;
           case "#FF7537":
               b = "group04";
                    break;
           case "#FFAD46":
               b = "group05";
                    break;
           case "#42D692":
               b = "group06";
                    break;
           case "#16A765":
               b = "group07";
                    break;
           case "#7BD148":
               b = "group08";
                    break;
           case "#B3DC6C":
               b = "group09";
                    break;
           case "#FBE983":
               b = "group10";
                    break;
           case "#FAD165":
               b = "group11";
                    break;
           case "#92E1C0":
               b = "group12";
                    break;
           case "#9FE1E7":
               b = "group13";
                    break;
           case "#9FC6E7":
               b = "group14";
                    break;
           case "#4986E7":
               b = "group15";
                    break;
           case "#9A9CFF":
               b = "group16";
                    break;
           case "#B99AFF":
               b = "group17";
                    break;
           case "#C2C2C2":
               b = "group18";
                    break;
           case "#CABDBF":
               b = "group19";
                    break;
           case "#CCA6AC":
               b = "group20";
                    break;
           case "#F691B2":
               b = "group21";
                    break;
           case "#CD74E6":
               b = "group22";
                    break;
           case "#A47AE2":
               b = "group23";
                    break;
    }
      return b;
  }
   
    
}
