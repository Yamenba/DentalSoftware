/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consultorio.dental;

import datos.Citas;
import datos.Paciente;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
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
public class ListaCitasController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    TableView tabla_citas;
    @FXML
    TableColumn columna_fecha;
    @FXML
    TableColumn columna_hora;
    @FXML
    TableColumn columna_tratamiento;
    @FXML
    TableColumn columna_editar;
    @FXML
    TableColumn columna_borrar;
    @FXML
    AnchorPane pane;
    PacienteQuery query = new PacienteQuery();
    List<Citas> lista_citas;
    ObservableList<Citas> lista_tabla;
    Paciente p;
    
    @FXML
    public void actionBotonAceptar(ActionEvent e){
        Node node = (Node) e.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    public void actionBotonReporte(ActionEvent e){

                Node node = (Node) e.getSource();
                Scene scene = node.getScene();
              
                Task longTask = new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        scene.setCursor(Cursor.WAIT);
                        try{
                           Map parameters = new HashMap();
                            parameters.put("id_paciente", p.getIdpaciente());
                            parameters.put("nombre", p.getNombre());
                            
                            JasperReport jr = (JasperReport)JRLoader.loadObject(new File(System.getenv("ProgramFiles")+"\\Dental Software\\reportes\\evolucionTratamiento.jasper"));

                            //JasperReport jr = (JasperReport) JRLoader.loadObjectFromFile(getClass().getResource("reportePaciente.jrxml"));
                            query.em.getTransaction().begin();
                            JasperPrint jp = JasperFillManager.fillReport(jr, parameters, query.em.unwrap(java.sql.Connection.class));
                            query.em.getTransaction().commit();

                            JasperViewer jv = new JasperViewer(jp,false); 
                            jv.setVisible(true);  
                            jv.setTitle("Evolución de tratamiento de " + p.getNombre());
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
                        scene.setCursor(Cursor.DEFAULT);
                    }
                });
                new Thread(longTask).start();   
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void initData(int idPaciente){
        p = query.pacientePorId(idPaciente).get(0);
        lista_citas = query.citasPorPaciente(idPaciente);
        lista_tabla = FXCollections.observableArrayList(lista_citas);
        if(lista_tabla.isEmpty()){
            tabla_citas.setPlaceholder(new Label("Este paciente aún no tiene citas registradas"));
        }
        tabla_citas.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tabla_citas.setItems(lista_tabla);
        columna_fecha.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Citas, String>, ObservableValue<String>>() {
                                            public ObservableValue<String> call(TableColumn.CellDataFeatures<Citas, String> p) {
                                                Citas cita = p.getValue();
                                                Calendar fecha = new GregorianCalendar(cita.getAno(),cita.getMes(),cita.getDia());
                                                SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");
                                                return new SimpleStringProperty(sdf.format(fecha.getTime()));
                                            }
                                         });
        columna_hora.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Citas, String>, ObservableValue<String>>() {
                                            public ObservableValue<String> call(TableColumn.CellDataFeatures<Citas, String> p) {
                                                Citas cita = p.getValue();
                                                return new SimpleStringProperty(horaDigital(cita.getHoraInicio(),cita.getMinutoInicio()));
                                            }
                                         });
        columna_tratamiento.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Citas, String>, ObservableValue<String>>() {
                                            public ObservableValue<String> call(TableColumn.CellDataFeatures<Citas, String> p) {
                                                Citas cita = p.getValue();
                                                return new SimpleStringProperty(cita.getTratamientoDeLaSesion());
                                            }
                                         });
        Callback<TableColumn<Citas, String>, TableCell<Citas, String>> cellFactoryEditar = //
                new Callback<TableColumn<Citas, String>, TableCell<Citas, String>>()
                {
                    @Override
                    public TableCell call( final TableColumn<Citas, String> param )
                    {
                        final TableCell<Citas, String> cell = new TableCell<Citas, String>()
                        {

                            final Button btn = new Button( "Editar" );

                            @Override
                            public void updateItem( String item, boolean empty )
                            {
                                super.updateItem( item, empty );
                                if ( empty )
                                {
                                    setGraphic( null );
                                    setText( null );
                                }
                                else
                                {
                                    btn.setOnAction( ( ActionEvent event ) ->
                                            {
                                                
                                            try {
                                                Citas cita = getTableView().getItems().get( getIndex() );
                                                FXMLLoader loader = new FXMLLoader(getClass().getResource( "EditarCita.fxml"));
                                                AnchorPane page = (AnchorPane) loader.load();
                                                pane.getChildren().clear();
                                                pane.getChildren().add(page);
                                                EditarCitaController controller = loader.<EditarCitaController>getController();
                                                controller.initData(cita.getIdcita());
                      
                                            } catch (IOException ex) {
                                                Logger.getLogger(ListaPagosController.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                                
                                    } );
                                    setGraphic( btn );
                                    setText( null );
                                }
                            }
                        };
                        return cell;
                    }
                };

        columna_editar.setCellFactory( cellFactoryEditar );
        
        Callback<TableColumn<Citas, String>, TableCell<Citas, String>> cellFactoryBorrar = //
                new Callback<TableColumn<Citas, String>, TableCell<Citas, String>>()
                {
                    @Override
                    public TableCell call( final TableColumn<Citas, String> param )
                    {
                        final TableCell<Citas, String> cell = new TableCell<Citas, String>()
                        {

                            final Button btn = new Button( "Borrar" );

                            @Override
                            public void updateItem( String item, boolean empty )
                            {
                                super.updateItem( item, empty );
                                if ( empty )
                                {
                                    setGraphic( null );
                                    setText( null );
                                }
                                else
                                {
                                    btn.setOnAction( ( ActionEvent event ) ->
                                            {
                                             Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                            alert.setTitle("Confirmación");
                                            alert.setHeaderText("¿Seguro que quieres borrar la cita?");

                                            Optional<ButtonType> result = alert.showAndWait();
                                            if (result.get() == ButtonType.OK){
                                                try {
                                                    Citas cita = getTableView().getItems().get( getIndex() );
                                                    query.deleteCita(cita.getIdcita());
                                                    FXMLLoader loader = new FXMLLoader(getClass().getResource( "ListaCitas.fxml"));
                                                    AnchorPane page = (AnchorPane) loader.load();
                                                    pane.getChildren().clear();
                                                    pane.getChildren().add(page);
                                                    ListaCitasController controller = loader.<ListaCitasController>getController();
                                                    controller.initData(cita.getIdpaciente().getIdpaciente());
                      
                                                } catch (IOException ex) {
                                                    Logger.getLogger(ListaPagosController.class.getName()).log(Level.SEVERE, null, ex);
                                                } catch (Exception ex) {
                                                     Logger.getLogger(ListaCitasController.class.getName()).log(Level.SEVERE, null, ex);
                                                 }
                                            }    
 
                                    } );
                                    setGraphic( btn );
                                    setText( null );
                                }
                            }
                        };
                        return cell;
                    }
                };

        columna_borrar.setCellFactory( cellFactoryBorrar );
    }
    
    public String horaDigital(int hora, int minutos){
        String string_hora = Integer.toString(hora);
        String string_minutos = Integer.toString(minutos);
        if(hora<10)
            string_hora = "0" + string_hora;
        if(minutos<10)
            string_minutos = "0" + string_minutos;
        return string_hora + ":" + string_minutos;
    }
}
