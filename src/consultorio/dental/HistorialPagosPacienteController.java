/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consultorio.dental;

import datos.CostoTratamiento;
import datos.Paciente;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.util.Callback;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author Ricardo
 */

public class HistorialPagosPacienteController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    ComboBox comboBox_paciente;
    private List<Paciente> pacientes = new ArrayList<>();
    private PacienteQuery query = new PacienteQuery();
  
    @FXML
    public void actionBotonAceptar(ActionEvent actionEvent) throws JRException{
        Paciente p = (Paciente)AgendaPacientesController.FxUtil.getComboBoxValue(comboBox_paciente);
        List<CostoTratamiento> lista = new ArrayList();
        Node node = (Node) actionEvent.getSource();
        Scene scene = node.getScene();
        Stage stage  = (Stage) node.getScene().getWindow();
        if(p!=null){
             lista = query.costoTratamientoPorPaciente(p.getIdpaciente());
        }
        if(lista.isEmpty() && p!=null){
                Task longTask = new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        
                        scene.setCursor(Cursor.WAIT);
                        try{
                            Map parameters = new HashMap();
                            parameters.put("id_paciente", p.getIdpaciente());
                            parameters.put("nombre", p.getNombre());
                            
                            JasperReport jr = (JasperReport)JRLoader.loadObject(new File(System.getenv("ProgramFiles")+"\\Dental Software\\reportes\\pagosSinCosto.jasper"));
                            query.em.getTransaction().begin();
                            JasperPrint jp = JasperFillManager.fillReport(jr, parameters, query.em.unwrap(java.sql.Connection.class));
                            query.em.getTransaction().commit();
                            JasperViewer jv = new JasperViewer(jp,false); 
                            jv.setVisible(true);  
                            jv.setTitle("Historial de pagos de " + p.getNombre());  
                            jv.setAlwaysOnTop(true);
             
                          }catch(Exception ex){
                              ex.printStackTrace();
                          }
                        return null;
                    }
                };

                longTask.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
                    @Override
                    public void handle(WorkerStateEvent t) {
                        stage.close();
                        scene.setCursor(Cursor.WAIT);
                    }
                });
                new Thread(longTask).start();
        }else if(p==null){
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Tienes que elegir un paciente");
                alert.showAndWait();
        }else{
                Task longTask = new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        
                        scene.setCursor(Cursor.WAIT);
                        try{
                            Map parameters = new HashMap();
                            parameters.put("id_paciente", p.getIdpaciente());
                            parameters.put("nombre", p.getNombre());                          
                            JasperReport jr = (JasperReport)JRLoader.loadObject(new File(System.getenv("ProgramFiles")+"\\Dental Software\\reportes\\pagos.jasper"));
                            query.em.getTransaction().begin();
                            JasperPrint jp = JasperFillManager.fillReport(jr, parameters, query.em.unwrap(java.sql.Connection.class));
                            query.em.getTransaction().commit();
                            JasperViewer jv = new JasperViewer(jp,false); 
                            jv.setVisible(true);  
                            jv.setTitle("Historial de pagos de " + p.getNombre());  
                            jv.setAlwaysOnTop(true);
                          }catch(Exception ex){
                              ex.printStackTrace();
                          }
                        return null;
                    }
                };

                longTask.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
                    @Override
                    public void handle(WorkerStateEvent t) {
                        stage.close();
                        scene.setCursor(Cursor.WAIT);
                    }
                });
                new Thread(longTask).start();
        }
        
      

        
        
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pacientes = query.listPaciente();
        ObservableList<Paciente> pacientes_list; 
        if(pacientes.size()==0){
            comboBox_paciente.setPromptText("No hay pacientes registrados");
        }else{
            pacientes_list = FXCollections.observableList(pacientes);
            comboBox_paciente.setItems(pacientes_list);
        AgendaPacientesController.FxUtil.autoCompleteComboBox(comboBox_paciente, AgendaPacientesController.FxUtil.AutoCompleteMode.STARTS_WITH);
            comboBox_paciente
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
