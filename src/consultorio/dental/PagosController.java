/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consultorio.dental;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ricardo
 */
public class PagosController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    public void actionBotonRegistrarPago(ActionEvent e) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RegistraPago.fxml"));
            Parent root1 = (Parent) loader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Registrar Pago");
            stage.setScene(new Scene(root1));  
            stage.show();
    }
    
    @FXML
    public void actionBotonHistorialPagosGeneral(ActionEvent e) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HistorialPagosGeneral.fxml"));
            Parent root1 = (Parent) loader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Registrar Pago");
            stage.setScene(new Scene(root1));  
            stage.show();
    }
    
    @FXML
    public void actionBotonHistorialPagosPaciente(ActionEvent e) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HistorialPagosPaciente.fxml"));
            Parent root1 = (Parent) loader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Historial de pagos de paciente");
            stage.setScene(new Scene(root1));  
            stage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
