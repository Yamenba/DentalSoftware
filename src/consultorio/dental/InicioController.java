/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consultorio.dental;

import datos.Citas;
import datos.Paciente;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Ricardo
 */
public class InicioController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TableView tabla_citas;
    
    @FXML
    private TableColumn nombre;
    
    @FXML
    private TableColumn hora;
    
    private List<Citas> citas = new ArrayList<>();
    
    private PacienteQuery query = new PacienteQuery();
     
    private ObservableList<Citas> citas_list = FXCollections.observableArrayList();
    
    @FXML
    private AnchorPane pane;
    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nombre.setCellValueFactory(new PropertyValueFactory<Citas, Paciente>("idpaciente"));
        //muestra datos
        hora.setCellValueFactory(new Callback<CellDataFeatures<Citas, String>, ObservableValue<String>>() {
                @Override public ObservableValue<String> call(CellDataFeatures<Citas, String> c) {
                    Citas cita = c.getValue();
                 
                    return new SimpleStringProperty(horaString(cita.getHoraInicio(),cita.getMinutoInicio()));
                }
        });

        citas = query.citaPorAno(Calendar.getInstance().get(Calendar.YEAR));
 
        
        for(Citas a: citas){
                if((a.getMes() == Calendar.getInstance().get(Calendar.MONTH)) && (a.getDia() == Calendar.getInstance().get(Calendar.DAY_OF_MONTH))){
                    citas_list.add(a);
                }
        }
        if(citas_list.size()==0)
                    tabla_citas.setPlaceholder(new Label("No hay citas programadas para este día"));
        
        SortedList<Citas> sortedList = new SortedList<>( citas_list, 
      (Citas stock1, Citas stock2) -> {
        if( stock1.getHoraInicio() < stock2.getHoraInicio() ) {
            return -1;
        } else if( stock1.getHoraInicio() > stock2.getHoraInicio() ) {
            return 1;
        } else {
            return 0;
        }
    });
       
     tabla_citas.setItems(sortedList);
     
     tabla_citas.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent click) {

                if (click.getClickCount() == 2) {
                    if(tabla_citas.getSelectionModel().getSelectedItem() != null){
                        try {
                            Citas c;
                            c = (Citas)tabla_citas.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader(getClass().getResource( "detalleCita.fxml"));
                            AnchorPane page = (AnchorPane) loader.load();
                            pane.getChildren().clear();
                            pane.getChildren().add(page);
                            DetalleCitaController controller = loader.<DetalleCitaController>getController();
                            controller.initData(c.getIdcita());



                            FadeTransition ft = new FadeTransition(Duration.millis(1000), pane);
                            ft.setFromValue(0.0);
                            ft.setToValue(1.0);
                            ft.play();
                        } catch (IOException ex) {
                            Logger.getLogger(PacientesController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                }
            }
        });
    }
    
    String horaString(int horas, int minutos){
        String hora = Integer.toString(horas);
        String minuto = Integer.toString(minutos);
        if(horas < 10)
            hora = "0" + hora;
        if(minutos < 10)
            minuto = "0" + minuto;
        return hora + ":" + minuto;
    }
    
}
