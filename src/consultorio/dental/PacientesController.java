/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consultorio.dental;

import datos.DatosMedicosPaciente;
import datos.Paciente;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Ricardo
 */
public class PacientesController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Button boton_nuevo_paciente;
    
    //@FXML
    //private Button boton_editar_paciente;
    
    //@FXML
    //private Button boton_borrar_paciente;
    
    @FXML
    private ListView<Paciente> listView_pacientes;
    
    @FXML
    private AnchorPane pane;
    
    @FXML
    private TextField textfield_buscar;
    
    
    
    
    private List<Paciente> pacientes = new ArrayList<>();
    
    private PacienteQuery query = new PacienteQuery();
    
    private ObservableList<Paciente> pacientes_list; 
    
    
    
   
    
    
    @FXML
    public void actionBotonAgregarPaciente(ActionEvent e) throws IOException{
        Node node = (Node) e.getSource();
        Scene scene = node.getScene();
        scene.setCursor(Cursor.WAIT);
        Platform.runLater(new Runnable() {
          @Override
          public void run() {
              try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AgregaPaciente.fxml"));

                AnchorPane page = (AnchorPane) loader.load();
                    pane.getChildren().clear();
                    pane.getChildren().add(page);
                    AgregaPacienteController controller = loader.<AgregaPacienteController>getController();
                    controller.initData(-980451413,new FXMLLoader(getClass().getResource("pacientes.fxml")),"pacientes",0);
                    FadeTransition ft = new FadeTransition(Duration.millis(1000), pane);
                    ft.setFromValue(0.0);
                    ft.setToValue(1.0);
                    ft.play();
                    scene.setCursor(Cursor.DEFAULT);
              }catch(Exception ex){
                  ex.printStackTrace();
              }
          }
        });
        }
    
    public void search(String oldVal, String newVal) {
       
        // If the number of characters in the text box is less than last time
        // it must be because the user pressed delete
        //if ( oldVal != null && (newVal.length() < oldVal.length()) ) {
            // Restore the lists original set of entries 
            // and start from the beginning
            listView_pacientes.setItems( pacientes_list );
        //}
         
        // Change to upper case so that case is not an issue
        newVal = newVal.toUpperCase();
        
        // Filter out the entries that don't contain the entered text
        ObservableList<Paciente> subentries = FXCollections.observableArrayList();
        for (Object entry : listView_pacientes.getItems()){
            Paciente p = (Paciente) entry;
            String entryText = p.getNombre();
           
            if ( entryText.toUpperCase().contains(newVal) ) {
                
                subentries.add(p);
                
                
            }
            
        }
        listView_pacientes.setItems(subentries);
        for ( Object entry: subentries.toArray() ){
            Paciente p = (Paciente) entry;
        
        }
    }
    
     
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image img_nuevo_paciente = new Image(getClass().getResourceAsStream("Add_User_24.png"));
        
        boton_nuevo_paciente.setGraphic(new ImageView(img_nuevo_paciente));
        
        
        textfield_buscar.textProperty().addListener(new ChangeListener() {
      public void changed(ObservableValue observable, Object oldVal,
          Object newVal) {
        search((String) oldVal, (String) newVal);
      }
    });
        
        
        
        listView_pacientes.setOnMouseClicked(new EventHandler<MouseEvent>() {

    @Override
    public void handle(MouseEvent click) {

        if (click.getClickCount() == 2) {
           
          Paciente p;
          p = (Paciente)listView_pacientes.getSelectionModel().getSelectedItem();
          if(p!=null){
              FXMLLoader loader = new FXMLLoader(getClass().getResource( "verPaciente.fxml"));
            try {
                AnchorPane page = (AnchorPane) loader.load();
                pane.getChildren().clear();
                pane.getChildren().add(page);
                VerPacienteController controller = loader.<VerPacienteController>getController();
                controller.initData(p.getIdpaciente());

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
        
        
        pacientes = query.listPaciente();
        
        
       
        if(pacientes.size()==0){
            listView_pacientes.setPlaceholder(new Label("No hay pacientes registrados"));
        }else{
            pacientes_list = FXCollections.observableList(pacientes);
            listView_pacientes.setItems(pacientes_list);
            listView_pacientes
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
            }; // ListCell
            return cell;
          }
        });
        }
        
       
        
    }    
    
   
   
}






