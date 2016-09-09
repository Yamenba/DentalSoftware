/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consultorio.dental;

import datos.Paciente;
import datos.Pago;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ricardo
 */
public class RegistraPagoPacienteController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    DatePicker fecha_pago_picker;
    @FXML
    TextField textfield_cantidad;
    @FXML
    TextField textfield_descripcion;
    private Paciente p;
    private List<Paciente> pacientes = new ArrayList<>();
    private PacienteQuery query = new PacienteQuery();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void initData(Paciente p){
     this.p = p;   
    }
    public void actionBotonGuardar(ActionEvent actionEvent){
        LocalDate fecha = fecha_pago_picker.getValue();
        Pago pago = new Pago();
        pago.setIdPaciente(p);
        if(fecha==null){
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("Tienes que ingresar una fecha");
            alert.showAndWait();
        }else{
            pago.setDiaPago(fecha.getDayOfMonth());
            pago.setMesPago(fecha.getMonthValue());
            pago.setAnoPago(fecha.getYear());
            try{
                pago.setCantidad(Double.parseDouble(textfield_cantidad.getText()));
                pago.setDescripcion(textfield_descripcion.getText());
                query.savePago(pago);
                 Node  source = (Node)  actionEvent.getSource(); 
                Stage stage  = (Stage) source.getScene().getWindow();
                stage.close();
            }catch(Exception e){
                Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("Cantidad inválida");
            alert.showAndWait();
            }
        }
    }
}
