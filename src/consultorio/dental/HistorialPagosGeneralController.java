/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consultorio.dental;

import java.io.File;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
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
public class HistorialPagosGeneralController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    DatePicker fecha_inicio_picker;
    @FXML
    DatePicker fecha_fin_picker;
    
     private PacienteQuery query = new PacienteQuery();
    @FXML
    public void actionBotonGenerar(ActionEvent actionEvent) throws JRException{
            LocalDate inicio = fecha_inicio_picker.getValue();
            LocalDate fin = fecha_fin_picker.getValue();
            if(inicio == null || fin==null){
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error ");
                alert.setHeaderText("Tienes que ingresar ambas fechas");
                alert.showAndWait();
            }else if(inicio.compareTo(fin) > 0){
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("La primera fecha no pueda ser mayor que la segunda");
                alert.showAndWait();
            }else{
                Node node = (Node) actionEvent.getSource();
                Scene scene = node.getScene();
                Stage stage  = (Stage) node.getScene().getWindow();
                Task longTask = new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        scene.setCursor(Cursor.WAIT);
                        try{
                            JasperReport jr = (JasperReport)JRLoader.loadObject(new File(System.getenv("ProgramFiles")+"\\Dental Software\\reportes\\historialPagosGeneral.jasper"));
                            Map parameters = new HashMap();
                            Instant instant = inicio.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
                            Date fecha_inicio = Date.from(instant);
                            instant = fin.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
                            Date fecha_fin = Date.from(instant);
                            parameters.put("fecha_inicio", fecha_inicio);
                            parameters.put("fecha_fin", fecha_fin);
                            
                            query.em.getTransaction().begin();
                            JasperPrint jp = JasperFillManager.fillReport(jr, parameters, query.em.unwrap(java.sql.Connection.class));
                            query.em.getTransaction().commit();
                            JasperViewer jv = new JasperViewer(jp,false); 
                            jv.setVisible(true);  
                            jv.setTitle("Historial de pagos general"); 
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
                        scene.setCursor(Cursor.DEFAULT);
                    }
                });
                new Thread(longTask).start();
            }
   
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
