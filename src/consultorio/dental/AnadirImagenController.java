/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consultorio.dental;

import datos.Imagen;
import datos.Paciente;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Ricardo
 */
public class AnadirImagenController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Label label_ruta;
    @FXML
    private ImageView imageView_imagen;
    @FXML
    private TextField textfield_descripcion;
    PacienteQuery query = new PacienteQuery();
    Paciente p;
    
    @FXML
    public void actionBotonBuscar(ActionEvent e){
        try{
            
                FileChooser fileChooser = new FileChooser();
                FileChooser.ExtensionFilter extFilter = new ExtensionFilter("Archivos de imagen", "*.png", "*.jpg", "*.gif");
                fileChooser.getExtensionFilters().add(extFilter);
                Node node = (Node) e.getSource();
                File file = fileChooser.showOpenDialog(node.getScene().getWindow());
                label_ruta.setText(file.getAbsolutePath());
                imageView_imagen.setImage(new Image(file.toURI().toString()));
        }catch(Exception ex){
        
        }
    }
    @FXML
    public void actionBotonGuardar(ActionEvent e) throws Exception{
        if(label_ruta.getText().equals("")){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Aún no has elegido una imagen");
                    alert.setHeaderText("Aún no has elegido una imagen");                  
                    alert.showAndWait();
                }else{
                    Imagen imagen = new Imagen();
                    imagen.setRuta(label_ruta.getText());
                    imagen.setIdPaciente(p);
                    imagen.setDescripcion(textfield_descripcion.getText());
                    query.saveImagen(imagen);
                    Galeria galeria= new Galeria(); 
                    galeria.initData(p.getIdpaciente());
                    galeria.start(new Stage());
                    Node node = (Node) e.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();
                }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void initData(int idPaciente){
        p = query.pacientePorId(idPaciente).get(0);
        imageView_imagen.setImage(new Image(getClass().getResourceAsStream("imagen_no_disponible.jpg")));
    }
    
}
