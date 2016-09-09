/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consultorio.dental;

import datos.Pago;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Calendar;
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
public class EditarPagoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    TextField textfield_cantidad;
    @FXML
    TextField textfield_concepto;
    @FXML
    DatePicker datepicker_fecha;
    @FXML
    AnchorPane pane;
    Pago pago;
    PacienteQuery query = new PacienteQuery();
    
    @FXML
    public void actionBotonGuardar(ActionEvent e){
        if(textfield_cantidad.getText() != null && datepicker_fecha.getValue() != null){
            try{
                double cantidad = Double.parseDouble(textfield_cantidad.getText());
                LocalDate fecha = datepicker_fecha.getValue();
                String concepto = textfield_concepto.getText();
                query.updatePago(pago.getIdPago(), cantidad, fecha, concepto);
                FXMLLoader loader = new FXMLLoader(getClass().getResource( "ListaPagos.fxml"));
                AnchorPane page = (AnchorPane) loader.load();
                pane.getChildren().clear();
                pane.getChildren().add(page);
                ListaPagosController controller = loader.<ListaPagosController>getController();
                controller.initData(pago.getIdPaciente().getIdpaciente());
            }catch(Exception ex){
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Error");
                alert.setHeaderText("Tienes que ingresar una cantidad válida");
                alert.showAndWait();
            }
        }else{
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("Tienes que ingresar por lo menos una cantidad y una fecha");
            alert.showAndWait();
        }
    }
    
    @FXML
    public void actionBotonCancelar(ActionEvent e) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource( "ListaPagos.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        pane.getChildren().clear();
        pane.getChildren().add(page);
        ListaPagosController controller = loader.<ListaPagosController>getController();
        controller.initData(pago.getIdPaciente().getIdpaciente());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void initData(int id_pago){
        pago = query.pagoPorId(id_pago);
        LocalDate fecha = LocalDate.of(pago.getAnoPago(),pago.getMesPago(),pago.getDiaPago());
        textfield_cantidad.setText(Double.toString(pago.getCantidad()));
        datepicker_fecha.setValue(fecha);
        textfield_concepto.setText(pago.getDescripcion());
    }
}
