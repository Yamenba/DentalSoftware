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
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Ricardo
 */
public class EditarCitaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    TextField textfield_tratamiento;
    @FXML
    DatePicker datepicker_fecha;
    @FXML
    AnchorPane pane;
    PacienteQuery query = new PacienteQuery();
    Citas cita;
    
    @FXML
    public void actionBotonGuardar(ActionEvent e) throws IOException{
        if(datepicker_fecha.getValue() != null){
            query.updateCita(cita.getIdcita(), datepicker_fecha.getValue(), textfield_tratamiento.getText());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ListaCitas.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            pane.getChildren().clear();
            pane.getChildren().add(page);
            ListaCitasController controller = loader.<ListaCitasController>getController();
            controller.initData(cita.getIdpaciente().getIdpaciente());
        }else{
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Tienes que ingresar una fecha");
            alert.setHeaderText("Tienes que ingresar una fecha.");
            alert.showAndWait();
        }
    }
    
    @FXML
    public void actionBotonCancelar(ActionEvent e) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListaCitas.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        pane.getChildren().clear();
        pane.getChildren().add(page);
        ListaCitasController controller = loader.<ListaCitasController>getController();
        controller.initData(cita.getIdpaciente().getIdpaciente());
    }
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void initData(int id_cita){
        cita = query.citaPorId(id_cita).get(0);
        datepicker_fecha.setValue(LocalDate.of(cita.getAno(),cita.getMes()+1,cita.getDia()));
        textfield_tratamiento.setText(cita.getTratamientoDeLaSesion());
    }
}
