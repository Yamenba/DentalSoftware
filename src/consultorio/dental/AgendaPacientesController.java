/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consultorio.dental;

import com.google.api.client.util.DateTime;
import com.sun.javafx.event.RedirectedEvent;
import datos.Ajustes;
import datos.Citas;
import datos.Paciente;
import java.awt.MouseInfo;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.Duration;
import jfxtras.scene.control.LocalDateTimePicker;
import jfxtras.scene.control.LocalTimePicker;
import jfxtras.scene.control.agenda.Agenda;
import jfxtras.scene.control.agenda.Agenda.Appointment;


/**
 * FXML Controller class
 *
 * @author Ricardo
 */
public class AgendaPacientesController implements Initializable {

    @FXML
    Agenda lAgenda;
    
    @FXML
    private AnchorPane pane2;
    
    @FXML
    private Button boton_agregar_cita;
  
    private List<Paciente> pacientes = new ArrayList<>();
    
    private PacienteQuery query = new PacienteQuery();
    
    private ObservableList<Paciente> pacientes_list;
    
    //final Agenda lAgenda = new Agenda().withDisplayedLocalDateTime((LocalDateTime.now()));
   
    final Map<String, Agenda.AppointmentGroup> lAppointmentGroupMap = new TreeMap<String, Agenda.AppointmentGroup>();
    
    private List<Citas> citas = new ArrayList<>();

    
    @FXML
    public void actionAgregarCita(ActionEvent e){
        
        ventanaCita();
        
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lAgenda.setSkin(new jfxtras.internal.scene.control.skin.agenda.AgendaWeekSkin(lAgenda));
        lAgenda.setDisplayedLocalDateTime(LocalDateTime.now());
        Image img_agregar = new Image(getClass().getResourceAsStream("Event_date_and_time_symbol_32.png"));
        boton_agregar_cita.setGraphic(new ImageView(img_agregar));
		
		// create calendar picker
		LocalDateTimePicker lCalendarPicker = new LocalDateTimePicker();
      
		// bind picker to agenda
                
                lCalendarPicker.localDateTimeProperty().bindBidirectional(lAgenda.displayedLocalDateTime());	
		lAgenda.setActionCallback( (appointment) -> {
                    try{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("InformacionCita.fxml"));
                        Parent root1 = (Parent) loader.load();
                        InformacionCitaController controller = loader.<InformacionCitaController>getController();
                        controller.initData(Integer.parseInt(appointment.getDescription()));
                        Stage stage = new Stage();
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.setTitle("Detalle de cita");
                        stage.setScene(new Scene(root1));  
                        stage.show();
                    }catch(Exception ex){
                        ex.printStackTrace();
                    }
			return null;
		});
        pane2.getChildren().add(lCalendarPicker);
        
   
        
        

        lAgenda.editAppointmentCallbackProperty().set(new Callback<Agenda.Appointment, Void>()
		{
			@Override
			public Void call(final Appointment appointment)
			{
                           
                            final Popup lPopup = new Popup();
                            GridPane gridpane = new GridPane();
                            gridpane.getStylesheets().addAll(this.getClass().getResource("estilo.css").toExternalForm());
                            lPopup.getContent().add(gridpane);
                            lPopup.getScene().getWindow().setEventDispatcher((event, tail) -> {
                            if (event.getEventType() == RedirectedEvent.REDIRECTED) {
                                    //  RedirectedEvent is a box that contains original event from other target
                                    RedirectedEvent ev = (RedirectedEvent) event;
                                    if (ev.getOriginalEvent().getEventType() == MouseEvent.MOUSE_PRESSED) {
                                         lPopup.hide();
                                     }
                                    }else {
                                        //  if click in the popup window. handle the event by default
                                            tail.dispatchEvent(event);
                                    }
                                    return null;
                                });
				Button boton_eliminar = new Button("Eliminar");
                                 Image img_borrar_cita = new Image(getClass().getResourceAsStream("Cross_remove_sign_16.png"));
                                 boton_eliminar.setGraphic(new ImageView(img_borrar_cita));
                                
                                boton_eliminar.setOnAction(new EventHandler<ActionEvent>()
				{
					@Override
					public void handle(ActionEvent evt)
					{
                                                lPopup.hide();
                                                Alert alert = new Alert(AlertType.CONFIRMATION);
                                                alert.setHeaderText("¿Seguro que quieres borrar la cita?");
                                                Optional<ButtonType> result = alert.showAndWait();
                                                if (result.get() == ButtonType.OK){
                                                    try {
                                                        // ... user chose OK
                                                        query.deleteCita(Integer.parseInt(appointment.getDescription()));
                                                    } catch (Exception ex) {
                                                        Logger.getLogger(AgendaPacientesController.class.getName()).log(Level.SEVERE, null, ex);
                                                    }
                                                    lAgenda.appointments().remove(appointment);
                                                    lAgenda.refresh();
                                                }
                                           }
				});
				gridpane.add(boton_eliminar,0,0);
                                Button boton_editar = new Button("Editar");
                                Image img_editar_cita = new Image(getClass().getResourceAsStream("Edit_interface_symbol_16.png"));
                                 boton_editar.setGraphic(new ImageView(img_editar_cita));
                                 
                                 boton_editar.setOnAction(new EventHandler<ActionEvent>()
				{
					@Override
					public void handle(ActionEvent evt)
					{
                                                lPopup.hide();
                                                ventanaEditarCita(appointment);
                                               
                                           }
				});
                                 
                                boton_eliminar.setMaxWidth(Double.MAX_VALUE);
                                boton_editar.setMaxWidth(Double.MAX_VALUE);
                                gridpane.add(boton_editar,0,1);
				lPopup.show(lAgenda,MouseInfo.getPointerInfo().getLocation().getX() ,MouseInfo.getPointerInfo().getLocation().getY());
				return null;
			}
		});
        
		// setup appointment groups
		
		lAppointmentGroupMap.put("group00", new Agenda.AppointmentGroupImpl().withStyleClass("group0"));
		lAppointmentGroupMap.put("group01", new Agenda.AppointmentGroupImpl().withStyleClass("group1"));
		lAppointmentGroupMap.put("group02", new Agenda.AppointmentGroupImpl().withStyleClass("group2"));
		lAppointmentGroupMap.put("group03", new Agenda.AppointmentGroupImpl().withStyleClass("group3"));
		lAppointmentGroupMap.put("group04", new Agenda.AppointmentGroupImpl().withStyleClass("group4"));
		lAppointmentGroupMap.put("group05", new Agenda.AppointmentGroupImpl().withStyleClass("group5"));
		lAppointmentGroupMap.put("group06", new Agenda.AppointmentGroupImpl().withStyleClass("group6"));
		lAppointmentGroupMap.put("group07", new Agenda.AppointmentGroupImpl().withStyleClass("group7"));
		lAppointmentGroupMap.put("group08", new Agenda.AppointmentGroupImpl().withStyleClass("group8"));
		lAppointmentGroupMap.put("group09", new Agenda.AppointmentGroupImpl().withStyleClass("group9"));
		lAppointmentGroupMap.put("group10", new Agenda.AppointmentGroupImpl().withStyleClass("group10"));
		lAppointmentGroupMap.put("group11", new Agenda.AppointmentGroupImpl().withStyleClass("group11"));
		lAppointmentGroupMap.put("group12", new Agenda.AppointmentGroupImpl().withStyleClass("group12"));
		lAppointmentGroupMap.put("group13", new Agenda.AppointmentGroupImpl().withStyleClass("group13"));
		lAppointmentGroupMap.put("group14", new Agenda.AppointmentGroupImpl().withStyleClass("group14"));
		lAppointmentGroupMap.put("group15", new Agenda.AppointmentGroupImpl().withStyleClass("group15"));
		lAppointmentGroupMap.put("group16", new Agenda.AppointmentGroupImpl().withStyleClass("group16"));
		lAppointmentGroupMap.put("group17", new Agenda.AppointmentGroupImpl().withStyleClass("group17"));
		lAppointmentGroupMap.put("group18", new Agenda.AppointmentGroupImpl().withStyleClass("group18"));
		lAppointmentGroupMap.put("group19", new Agenda.AppointmentGroupImpl().withStyleClass("group19"));
		lAppointmentGroupMap.put("group20", new Agenda.AppointmentGroupImpl().withStyleClass("group20"));
		lAppointmentGroupMap.put("group21", new Agenda.AppointmentGroupImpl().withStyleClass("group21"));
		lAppointmentGroupMap.put("group22", new Agenda.AppointmentGroupImpl().withStyleClass("group22"));
		lAppointmentGroupMap.put("group23", new Agenda.AppointmentGroupImpl().withStyleClass("group23"));
		for (String lId : lAppointmentGroupMap.keySet())
		{
			Agenda.AppointmentGroup lAppointmentGroup = lAppointmentGroupMap.get(lId);
			lAppointmentGroup.setDescription(lId);
			lAgenda.appointmentGroups().add(lAppointmentGroup);
		}
                
                citas = query.listCita();
                
        for(Citas a: citas){

            Agenda.AppointmentImpl lAppointment = new Agenda.AppointmentImpl()
				.withStartTime(new GregorianCalendar(a.getAno(), a.getMes(),a.getDia(),a.getHoraInicio() , a.getMinutoInicio()))
				.withEndTime(new GregorianCalendar(a.getAno(), a.getMes(),a.getDia(),a.getHoraFin() , a.getMinutoFin()))
				.withSummary(a.getSummary())
				.withDescription(Integer.toString(a.getIdcita()))
				.withAppointmentGroup(lAppointmentGroupMap.get(a.getGrupo()));
                                    
                                       
                                       lAppointment.startTimeProperty().addListener(new ChangeListener() {
                                       public void changed(ObservableValue observable, Object oldVal,Object newVal) {
                                             
                                           try {
                                               query.updateCita(lAppointment,a.getIdcita(),a.getGrupo(),a.getDescription(),a.getIdpaciente());
                                           } catch (Exception ex) {
                                               Logger.getLogger(AgendaPacientesController.class.getName()).log(Level.SEVERE, null, ex);
                                           }
                                           
                                           
                                        }
                                        });
                                        
                                        lAppointment.endTimeProperty().addListener(new ChangeListener() {
                                       public void changed(ObservableValue observable, Object oldVal,Object newVal) {
                                           try {
                                               query.updateCita(lAppointment,a.getIdcita(),a.getGrupo(),a.getDescription(),a.getIdpaciente());
                                           } catch (Exception ex) {
                                               Logger.getLogger(AgendaPacientesController.class.getName()).log(Level.SEVERE, null, ex);
                                           }
                                           
                                        }
                                        });
                                        
                                        
					lAgenda.appointments().add(lAppointment);
        }
       
    }
    
  
    private int mesCalendar(int i){
        
        int a=0;
        
        switch (i){
            case 1: a =Calendar.JANUARY;
                     break;
            case 2: a= Calendar.FEBRUARY;
                     break;
            case 3:  a = Calendar.MARCH;
                     break;
            case 4: a = Calendar.APRIL;
                     break;
            case 5:  a = Calendar.MAY;
                     break;
            case 6:  a = Calendar.JUNE;
                     break;
            case 7:  a = Calendar.JULY;
                     break;
            case 8:  a = Calendar.AUGUST;
                     break;
            case 9:  a = Calendar.SEPTEMBER;
                     break;
            case 10: a = Calendar.OCTOBER;
                     break;
            case 11: a = Calendar.NOVEMBER;
                     break;
            case 12: a = Calendar.DECEMBER;
                     break;
        }
        return a;
    }
    
    private Month mesMonth(int i){
        
        Month a = Month.JANUARY;
        
        switch (i){
            case 0: a =Month.JANUARY;
                     break;
            case 1: a= Month.FEBRUARY;
                     break;
            case 2:  a = Month.MARCH;
                     break;
            case 3: a = Month.APRIL;
                     break;
            case 4:  a = Month.MAY;
                     break;
            case 5:  a = Month.JUNE;
                     break;
            case 6:  a = Month.JULY;
                     break;
            case 7:  a = Month.AUGUST;
                     break;
            case 8:  a = Month.SEPTEMBER;
                     break;
            case 9: a = Month.OCTOBER;
                     break;
            case 10: a = Month.NOVEMBER;
                     break;
            case 11: a = Month.DECEMBER;
                     break;
        }
        return a;
    }
    
    
    
    static class ColorRectCell extends ListCell<String>{
      @Override
      public void updateItem(String item, boolean empty){
          super.updateItem(item, empty);
          Rectangle rect = new Rectangle(120,18);
          if(item != null){
            
               rect.setFill(Color.web(item));
              setGraphic(rect);
      }
  }
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
  
  
  public String grupoColor(String a){
      String b = new String();
      switch (a){
           case "group00":
                b = "#AC725E";
                    break;
           case "group01":
               b = "#D06B64";
                    break;
           case "group02":
               b = "#F83A22";
                    break;
           case "group03":
               b = "#FA573C";
                    break;
           case "group04":
               b = "#FF7537";
                    break;
           case "group05":
               b = "#FFAD46";
                    break;
           case "group06":
               b = "#42D692";
                    break;
           case "group07":
               b = "#16A765";
                    break;
           case "group08":
               b = "#7BD148";
                    break;
           case "group09":
               b = "#B3DC6C";
                    break;
           case "group10":
               b = "#FBE983";
                    break;
           case "group11":
               b = "#FAD165";
                    break;
           case "group12":
               b = "#92E1C0";
                    break;
           case "group13":
               b = "#9FE1E7";
                    break;
           case "group14":
               b = "#9FC6E7";
                    break;
           case "group15":
               b = "#4986E7";
                    break;
           case "group16":
               b = "#9A9CFF";
                    break;
           case "group17":
               b = "#B99AFF";
                    break;
           case "group18":
               b = "#C2C2C2";
                    break;
           case "group19":
               b = "#CABDBF";
                    break;
           case "group20":
               b = "#CCA6AC";
                    break;
           case "group21":
               b = "#F691B2";
                    break;
           case "group22":
               b = "#CD74E6";
                    break;
           case "group23":
               b = "#A47AE2";
                    break;
    }
      return b;
  }
  
  
  
 //////////////////////////////////////////////////////////////////////////////////////////// 
public String mapAgrupo(String a){
      String b = new String();
      switch (a){
           case "group0":
                b = "group00";
                    break;
           case "group1":
               b = "group01";
                    break;
           case "group2":
               b = "group02";
                    break;
           case "group3":
               b = "group03";
                    break;
           case "group4":
               b = "group04";
                    break;
           case "group5":
               b = "group05";
                    break;
           case "group6":
               b = "group06";
                    break;
           case "group7":
               b = "group07";
                    break;
           case "group8":
               b = "group08";
                    break;
           case "group9":
               b = "group09";
                    break;
           default:
               b=a;
               break;
           
    }
      return b;
  }
  
  
  
 public static class FxUtil {

    public enum AutoCompleteMode {
        STARTS_WITH,CONTAINING,;
    }

    public static<T> void autoCompleteComboBox(ComboBox<T> comboBox, AutoCompleteMode mode) {
        ObservableList<T> data = comboBox.getItems();

        comboBox.setEditable(true);
        comboBox.getEditor().focusedProperty().addListener(observable -> {
            if (comboBox.getSelectionModel().getSelectedIndex() < 0) {
                comboBox.getEditor().setText(null);
            }
        });
        comboBox.addEventHandler(KeyEvent.KEY_PRESSED, t -> comboBox.hide());
        comboBox.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {

            private boolean moveCaretToPos = false;
            private int caretPos;

            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.UP) {
                    caretPos = -1;
                    moveCaret(comboBox.getEditor().getText().length());
                    return;
                } else if (event.getCode() == KeyCode.DOWN) {
                    if (!comboBox.isShowing()) {
                        comboBox.show();
                    }
                    caretPos = -1;
                    moveCaret(comboBox.getEditor().getText().length());
                    return;
                } else if (event.getCode() == KeyCode.BACK_SPACE) {
                    moveCaretToPos = true;
                    caretPos = comboBox.getEditor().getCaretPosition();
                } else if (event.getCode() == KeyCode.DELETE) {
                    moveCaretToPos = true;
                    caretPos = comboBox.getEditor().getCaretPosition();
                }

                if (event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.LEFT
                        || event.isControlDown() || event.getCode() == KeyCode.HOME
                        || event.getCode() == KeyCode.END || event.getCode() == KeyCode.TAB) {
                    return;
                }

                ObservableList<T> list = FXCollections.observableArrayList();
                for (T aData : data) {
                    if (mode.equals(AutoCompleteMode.STARTS_WITH) && aData.toString().toLowerCase().startsWith(comboBox.getEditor().getText().toLowerCase())) {
                        list.add(aData);
                    } else if (mode.equals(AutoCompleteMode.CONTAINING) && aData.toString().toLowerCase().contains(comboBox.getEditor().getText().toLowerCase())) {
                        list.add(aData);
                    }
                }
                String t = comboBox.getEditor().getText();

                comboBox.setItems(list);
                comboBox.getEditor().setText(t);
                if (!moveCaretToPos) {
                    caretPos = -1;
                }
                moveCaret(t.length());
                if (!list.isEmpty()) {
                    comboBox.show();
                }
            }

            private void moveCaret(int textLength) {
                if (caretPos == -1) {
                    comboBox.getEditor().positionCaret(textLength);
                } else {
                    comboBox.getEditor().positionCaret(caretPos);
                }
                moveCaretToPos = false;
            }
        });
    }

    public static<T> T getComboBoxValue(ComboBox<T> comboBox){
        if (comboBox.getSelectionModel().getSelectedIndex() < 0) {
            return null;
        } else {
            return comboBox.getItems().get(comboBox.getSelectionModel().getSelectedIndex());
        }
    }
}
 //////////////////////////////////////////////////////////////////////////////////ventana citaaa   
    public void ventanaCita(){
        
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
        
         pacientes = query.listPaciente();
         
        ComboBox comboBox = new ComboBox();
          comboBox.setVisibleRowCount(5);
          comboBox.setMaxWidth(250);
         
          
         if(pacientes.size()==0){
            comboBox.setPromptText("No hay pacientes registrados");
        }else{
            pacientes_list = FXCollections.observableList(pacientes);
            comboBox.setItems(pacientes_list);
        FxUtil.autoCompleteComboBox(comboBox, FxUtil.AutoCompleteMode.STARTS_WITH);
            comboBox
        .setCellFactory(new Callback<ListView<Paciente>, ListCell<Paciente>>() {

          public ListCell<Paciente> call(ListView<Paciente> param) {
            
            final ListCell<Paciente> cell = new ListCell<Paciente>() {
              @Override
              public void updateItem(Paciente item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null) {
                  setText(item.getNombre() );
                  
                }else{
                    setText("");
                }
              }
            }; 
            return cell;
          }
        });
        }
     
        gridpane.add(comboBox, 1, 5);
        
        Label selecciona = new Label("Paciente");
        gridpane.add(selecciona, 0, 5);
        selecciona.setStyle("-fx-text-fill: #214D74; -fx-font-size: 9pt;");
        GridPane.setHalignment(selecciona, HPos.RIGHT);

        
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
            return new ColorRectCell();
        }
    };
           
           cb.setCellFactory(factory);
    cb.setButtonCell(factory.call(null));
    cb.setVisibleRowCount(5);

       Label label_color = new Label("Color en agenda");
        gridpane.add(label_color, 0, 10);
        label_color.setStyle("-fx-text-fill: #214D74; -fx-font-size: 9pt;");
        GridPane.setHalignment(label_color, HPos.RIGHT);
   

        
        Button guardar = new Button("Guardar");
        Image img_guardar = new Image(getClass().getResourceAsStream("Save_Icon_Silhouette_24.png"));
        guardar.setGraphic(new ImageView(img_guardar));
        guardar.setOnAction(new EventHandler<ActionEvent>(){
            @Override 
            public void handle(    ActionEvent event){          
                if(FxUtil.getComboBoxValue(comboBox) == null ){
                        Alert alert = new Alert(AlertType.WARNING);                
                        alert.setHeaderText("Falta especificar el paciente");
                        alert.showAndWait();
                }else if(dia.getValue() == null){
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
                Paciente p = new Paciente();
                  
                                p = (Paciente)FxUtil.getComboBoxValue(comboBox);
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
                        
		
                                Agenda.Appointment lAppointment = new Agenda.AppointmentImpl()
				.withStartTime(new GregorianCalendar(lTodayYear, mesCalendar(lTodayMonth),lTodayDay, hora_inicio, minuto_inicio))
				.withEndTime(new GregorianCalendar(lTodayYear, mesCalendar(lTodayMonth),lTodayDay, hora_fin, minuto_fin))
				.withSummary(summary)
				.withDescription("")
				.withAppointmentGroup(lAppointmentGroupMap.get(colorGrupo(cb.getSelectionModel().getSelectedItem())));					
				
                                
                                lAppointment.setDescription(Integer.toString(query.saveCita(lAppointment,colorGrupo(cb.getValue()),texto_descripcion.getText(),p,id_calendar)));
                                lAgenda.appointments().add(lAppointment);
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
        
        
         comboBox.setVisible(true);
         selecciona.setVisible(true);
        
        
       ;
        
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
    /////////////////////////////////////////////////////////////////////////////////////////editar citaaaaaa
    public void ventanaEditarCita(Appointment app){
        
        List<Citas> citas = new ArrayList<>();
        citas = query.citaPorId(Integer.parseInt(app.getDescription()));
        Citas a = citas.get(0);
         
         
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
        
 
         pacientes = query.listPaciente();
         
        ComboBox comboBox = new ComboBox();
          comboBox.setVisibleRowCount(5);
         comboBox.setMaxWidth(250);
          
         if(pacientes.size()==0){
            comboBox.setPromptText("No hay pacientes registrados");
        }else{
            pacientes_list = FXCollections.observableList(pacientes);
            comboBox.setItems(pacientes_list);
        FxUtil.autoCompleteComboBox(comboBox, FxUtil.AutoCompleteMode.STARTS_WITH);
            comboBox
        .setCellFactory(new Callback<ListView<Paciente>, ListCell<Paciente>>() {

          public ListCell<Paciente> call(ListView<Paciente> param) {
            
            final ListCell<Paciente> cell = new ListCell<Paciente>() {
              @Override
              public void updateItem(Paciente item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null) {
                  setText(item.getNombre() );
                  
                }else{
                    setText("");
                }
              }
            }; 
            return cell;
          }
        });
        }
         
         List<Paciente> pas = new ArrayList<>();
        pas = query.pacientePorId(a.getIdpaciente().getIdpaciente());
        Paciente p = pas.get(0);
         
         comboBox.setValue(p);
            
        

        gridpane.add(comboBox, 1, 5);
        
        Label selecciona = new Label("Paciente");
        gridpane.add(selecciona, 0, 5);
        selecciona.setStyle("-fx-text-fill: #214D74; -fx-font-size: 9pt;");
        GridPane.setHalignment(selecciona, HPos.RIGHT);
   
        Label label_descripcion = new Label("Motivo de la cita");
        gridpane.add(label_descripcion, 0, 6);
        label_descripcion.setStyle("-fx-text-fill: #214D74; -fx-font-size: 9pt;");
        GridPane.setHalignment(label_descripcion, HPos.RIGHT);
        
        TextField texto_descripcion = new TextField();
        gridpane.add(texto_descripcion, 1, 6);
        GridPane.setHalignment(texto_descripcion, HPos.LEFT);
        
        texto_descripcion.setText(a.getDescription());
        
        Label label_dia = new Label("Día");
        gridpane.add(label_dia, 0, 7);
        label_dia.setStyle("-fx-text-fill: #214D74; -fx-font-size: 9pt;");
        GridPane.setHalignment(label_dia, HPos.RIGHT);
        
        DatePicker dia = new DatePicker();
        gridpane.add(dia, 1, 7);
        
        LocalDate localDate = LocalDate.of(a.getAno(),mesMonth(a.getMes()) , a.getDia());
        
        
        dia.setValue(localDate);
        
        Label label_hora = new Label("Desde");
        gridpane.add(label_hora, 0, 8);
        label_hora.setStyle("-fx-text-fill: #214D74; -fx-font-size: 9pt;");
        GridPane.setHalignment(label_hora, HPos.RIGHT);
        
        LocalTimePicker inicio = new LocalTimePicker();
        gridpane.add(inicio, 1, 8);
        
        LocalTime horaInicio = LocalTime.of(a.getHoraInicio(),a.getMinutoInicio());
        inicio.setLocalTime(horaInicio);
        
        Label label_duracion = new Label("Hasta");
        gridpane.add(label_duracion, 0, 9);
        label_duracion.setStyle("-fx-text-fill: #214D74; -fx-font-size: 9pt;");
        GridPane.setHalignment(label_duracion, HPos.RIGHT);
        
        LocalTimePicker fin = new LocalTimePicker();
        gridpane.add(fin, 1, 9);
        
        LocalTime horaFin = LocalTime.of(a.getHoraFin(),a.getMinutoFin());
        fin.setLocalTime(horaFin);
        

        
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
          cb.setValue(grupoColor(a.getGrupo()));
          gridpane.add(cb, 1, 10);
          GridPane.setHalignment(cb, HPos.LEFT);
          
           Callback<ListView<String>, ListCell<String>> factory = new Callback<ListView<String>, ListCell<String>>() {
        @Override
        public ListCell<String> call(ListView<String> list) {
            return new ColorRectCell();
        }
    };
           
           cb.setCellFactory(factory);
    cb.setButtonCell(factory.call(null));
    cb.setVisibleRowCount(5);

       Label label_color = new Label("Color en la agenda");
        gridpane.add(label_color, 0, 10);
        label_color.setStyle("-fx-text-fill: #214D74; -fx-font-size: 9pt;");
        GridPane.setHalignment(label_color, HPos.RIGHT);
   

        
        Button guardar = new Button("Aceptar");
        Image img_guardar = new Image(getClass().getResourceAsStream("ok_img.png"));
        guardar.setGraphic(new ImageView(img_guardar));
        guardar.setOnAction(new EventHandler<ActionEvent>(){
            @Override 
            public void handle(    ActionEvent event){
                
             
                if(FxUtil.getComboBoxValue(comboBox) == null){
                        Alert alert = new Alert(AlertType.WARNING);
                        
                        alert.setHeaderText("Falta especificar el paciente");
                       
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
                Paciente pacient;
                  
                                 pacient = (Paciente)FxUtil.getComboBoxValue(comboBox);

                                summary = pacient.getNombre();
                               
				app.setStartTime(new GregorianCalendar(lTodayYear, mesCalendar(lTodayMonth),lTodayDay, hora_inicio, minuto_inicio));
				app.setEndTime(new GregorianCalendar(lTodayYear, mesCalendar(lTodayMonth),lTodayDay, hora_fin, minuto_fin));
				app.setSummary(summary);
				
				app.setAppointmentGroup(lAppointmentGroupMap.get(colorGrupo(cb.getSelectionModel().getSelectedItem())));					
				
                                
                    try {
                        //lAppointment.setDescription(Integer.toString(query.saveCita(lAppointment,colorGrupo(cb.getValue()),texto_descripcion.getText())));
                        query.updateCita(app,a.getIdcita(),colorGrupo(cb.getValue()),texto_descripcion.getText(),pacient);
                    } catch (Exception ex) {
                        Logger.getLogger(AgendaPacientesController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                                lAgenda.refresh();
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
         comboBox.setVisible(true);
         selecciona.setVisible(true);
        
        
        
        
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
    
}
