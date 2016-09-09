/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consultorio.dental;

import consultorio.dental.AgendaPacientesController.FxUtil;
import datos.Paciente;
import datos.Pago;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Ricardo
 */
public class RegistraPagoController implements Initializable {
    
    @FXML
    private DatePicker fecha_pago_picker;
    @FXML
    private TextField textfield_cantidad;
    @FXML
    private ComboBox combobox_paciente;
    @FXML
    private TextField textfield_descripcion;
    private List<Paciente> pacientes = new ArrayList<>();
    private PacienteQuery query = new PacienteQuery();
    /**
     * Initializes the controller class.
     */
    @FXML
    public void actionBotonGuardar(ActionEvent actionEvent){
        Pago pago = new Pago();
        LocalDate fecha = fecha_pago_picker.getValue();
        pago.setIdPaciente((Paciente)FxUtil.getComboBoxValue(combobox_paciente));
        if(fecha == null){
            Alert alert = new Alert(AlertType.ERROR);
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
        }catch(Exception ex){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Cantidad errónea");
            alert.showAndWait();
        }
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pacientes = query.listPaciente();
        ObservableList<Paciente> pacientes_list; 
        if(pacientes.size()==0){
            combobox_paciente.setPromptText("No hay pacientes registrados");
        }else{
            pacientes_list = FXCollections.observableList(pacientes);
            combobox_paciente.setItems(pacientes_list);
        AgendaPacientesController.FxUtil.autoCompleteComboBox(combobox_paciente, FxUtil.AutoCompleteMode.STARTS_WITH);
            combobox_paciente
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
    }
    
}
