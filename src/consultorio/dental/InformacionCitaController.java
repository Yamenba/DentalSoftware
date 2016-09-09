/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consultorio.dental;

import datos.Citas;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ricardo
 */
public class InformacionCitaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Label label_paciente;
    @FXML
    private Label label_motivo;
    @FXML
    private Label label_hora_inicio;
    @FXML
    private Label label_hora_fin;
    @FXML
    private Label label_telefono;
    @FXML
    private Label label_correo;
    @FXML
    private ImageView imageView_fotoPerfil;
    
    @FXML
    public void actionBotonAceptar(ActionEvent e){
        Node node = (Node) e.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void initData(int id_cita){
        PacienteQuery query = new PacienteQuery();
        Citas cita = query.citaPorId(id_cita).get(0);
        label_paciente.setText(cita.getIdpaciente().getNombre());
        label_motivo.setText(cita.getDescription());
        label_hora_inicio.setText(horaString(cita.getHoraInicio(),cita.getMinutoInicio()));
        label_hora_fin.setText(horaString(cita.getHoraFin(),cita.getMinutoFin()));
        label_telefono.setText(cita.getIdpaciente().getTelefono());
        label_correo.setText(cita.getIdpaciente().getMail());
        imageView_fotoPerfil.setImage(new Image(getClass().getResourceAsStream("imagen_no_disponible.jpg")));
        try{
            imageView_fotoPerfil.setImage(new Image(new File(cita.getIdpaciente().getRutaImagen()).toURI().toString()));    
        }catch(Exception ex){
                
        }
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
