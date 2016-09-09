/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consultorio.dental;

import datos.CostoTratamiento;
import datos.Paciente;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ricardo
 */
public class CostoTratamientoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    TextField textfield_pago_inicial;
    @FXML
    TextField textfield_numero_mensualidades;
    @FXML
    TextField textfield_monto_mensualidad;
    @FXML
    Label label_total;
    PacienteQuery query = new PacienteQuery();
    List <CostoTratamiento> lista_costo_tratamiento = new ArrayList<>();
    CostoTratamiento costo;
    Paciente p;
    
    
   
    
    @FXML
    public void actionBotonGuardar(ActionEvent actionEvent){
        try{
            double pagoInicial = Double.parseDouble(textfield_pago_inicial.getText());
            double numeroMensualidades = Double.parseDouble(textfield_numero_mensualidades.getText());
            double montoMensualidad = Double.parseDouble(textfield_monto_mensualidad.getText());
            if(lista_costo_tratamiento.isEmpty()){
                CostoTratamiento costoNuevo = new CostoTratamiento();
                costoNuevo.setIdPaciente(p.getIdpaciente());
                costoNuevo.setPagoInicial(pagoInicial);
                costoNuevo.setNumeroMensualidades(numeroMensualidades);
                costoNuevo.setMontoMensualidad(montoMensualidad);
                query.saveCostoTratamiento(costoNuevo);
            }else{
                query.updateCostoTratamiento(costo.getIdPaciente(), pagoInicial, numeroMensualidades, montoMensualidad);
            }
            Node  source = (Node)  actionEvent.getSource(); 
            Stage stage  = (Stage) source.getScene().getWindow();
            stage.close();
        }catch(Exception ex){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Datos no válidos");
            alert.setContentText("Verifica que no dejaste ningún campo vacío, y que los datos que ingresaste son válidos.");
            alert.showAndWait();
        }
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
   
    void initData(Paciente paciente){
        p = paciente;
        lista_costo_tratamiento = query.costoTratamientoPorPaciente(p.getIdpaciente());
        if(!lista_costo_tratamiento.isEmpty()){
            costo = lista_costo_tratamiento.get(0);
            double pagoInicial = costo.getPagoInicial();
            double numeroMensualidades = costo.getNumeroMensualidades();
            double montoMensualidad= costo.getMontoMensualidad();
            double total = pagoInicial + (numeroMensualidades * montoMensualidad);
            textfield_pago_inicial.setText(Double.toString(pagoInicial));
            textfield_numero_mensualidades.setText(Double.toString(numeroMensualidades));
            textfield_monto_mensualidad.setText(Double.toString(montoMensualidad));
            label_total.setText("$ " + total);
        }
       
        ChangeListener pagoInicialListener = new ChangeListener() {

            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                try{
                    double pagoInicial = Double.parseDouble((String) newValue);
                    double numeroMensualidades = Double.parseDouble(textfield_numero_mensualidades.getText());
                    double montoMensualidad = Double.parseDouble(textfield_monto_mensualidad.getText());
                    double total = pagoInicial + (numeroMensualidades * montoMensualidad);
                    label_total.setText("$ " + total);
                    
                }catch(Exception ex){
                    
                }
            }
        };
        ChangeListener numeroMensualidadesListener = new ChangeListener() {

            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                try{
                    double pagoInicial = Double.parseDouble(textfield_pago_inicial.getText());
                    double numeroMensualidades = Double.parseDouble((String) newValue);
                    double montoMensualidad = Double.parseDouble(textfield_monto_mensualidad.getText());
                    double total = pagoInicial + (numeroMensualidades * montoMensualidad);
                    label_total.setText("$ " + total);
                    
                }catch(Exception ex){
                    
                }
            }
        };
        ChangeListener montoMensualidadListener = new ChangeListener() {

            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                try{
                    double pagoInicial = Double.parseDouble(textfield_pago_inicial.getText());
                    double numeroMensualidades = Double.parseDouble(textfield_numero_mensualidades.getText());
                    double montoMensualidad = Double.parseDouble((String) newValue);
                    double total = pagoInicial + (numeroMensualidades * montoMensualidad);
                    label_total.setText("$ " + total);
                    
                }catch(Exception ex){
                    
                }
            }
        };
        textfield_pago_inicial.textProperty().addListener(pagoInicialListener);
        textfield_numero_mensualidades.textProperty().addListener(numeroMensualidadesListener);
        textfield_monto_mensualidad.textProperty().addListener(montoMensualidadListener);
    }
}


