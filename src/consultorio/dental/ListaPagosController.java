/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consultorio.dental;

import datos.CostoTratamiento;
import datos.Paciente;
import datos.Pago;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
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
public class ListaPagosController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    TableView tabla_pagos;
    @FXML
    TableColumn columna_fecha;
    @FXML
    TableColumn columna_concepto;
    @FXML
    TableColumn columna_cantidad;
    @FXML
    TableColumn columna_editar;
    @FXML
    TableColumn columna_borrar;
    @FXML
    AnchorPane pane;
    @FXML
    Label label_costo;
    @FXML
    Label label_restante;
    List<Pago> lista_pagos;
    List<CostoTratamiento> lista_costo;
    List<Paciente> lista_paciente;
    Paciente p;
    PacienteQuery query = new PacienteQuery();
    ObservableList<Pago> lista_tabla;
    double total_pagos = 0;
    
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
            List<CostoTratamiento> lista = query.costoTratamientoPorPaciente(p.getIdpaciente());
            if(lista.isEmpty()){
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
                        scene.setCursor(Cursor.DEFAULT);
                    }
                });
                new Thread(longTask).start();  
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
    
    public void initData(int idPaciente){
        lista_pagos = query.pagosPorPaciente(idPaciente);
        lista_costo = query.costoTratamientoPorPaciente(idPaciente);
        lista_paciente = query.pacientePorId(idPaciente);
        p = lista_paciente.get(0);
        if(!lista_pagos.isEmpty()){
            for(Pago a : lista_pagos){
                total_pagos += a.getCantidad();
            }
        }
        if(!lista_costo.isEmpty()){
            CostoTratamiento costo = lista_costo.get(0);
            double total = (costo.getNumeroMensualidades() * costo.getMontoMensualidad()) + costo.getPagoInicial();
            double restante = total - total_pagos;
            label_costo.setText("Costo del tratamiento: $"+total);
            label_restante.setText("Cantidad restante: $"+restante);
        }
        columna_fecha.setCellValueFactory(new Callback<CellDataFeatures<Pago, String>, ObservableValue<String>>() {
                                            public ObservableValue<String> call(CellDataFeatures<Pago, String> p) {
                                                Pago pago = p.getValue();
                                                Calendar fecha = new GregorianCalendar(pago.getAnoPago(),pago.getMesPago()-1,pago.getDiaPago());
                                                SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");
                                                return new SimpleStringProperty(sdf.format(fecha.getTime()));
                                            }
                                         });
        columna_concepto.setCellValueFactory(new Callback<CellDataFeatures<Pago, String>, ObservableValue<String>>() {
                                            public ObservableValue<String> call(CellDataFeatures<Pago, String> p) {
                                                Pago pago = p.getValue();    
                                                return new SimpleStringProperty(pago.getDescripcion());
                                            }
                                         });
        columna_cantidad.setCellValueFactory(new Callback<CellDataFeatures<Pago, String>, ObservableValue<String>>() {
                                            public ObservableValue<String> call(CellDataFeatures<Pago, String> p) {
                                                Pago pago = p.getValue();
                                                return new SimpleStringProperty(Double.toString(pago.getCantidad()));
                                            }
                                         });
        Callback<TableColumn<Pago, String>, TableCell<Pago, String>> cellFactoryEditar = //
                new Callback<TableColumn<Pago, String>, TableCell<Pago, String>>()
                {
                    @Override
                    public TableCell call( final TableColumn<Pago, String> param )
                    {
                        final TableCell<Pago, String> cell = new TableCell<Pago, String>()
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
                                                Pago pago = getTableView().getItems().get( getIndex() );
                                                FXMLLoader loader = new FXMLLoader(getClass().getResource( "EditarPago.fxml"));
                                                AnchorPane page = (AnchorPane) loader.load();
                                                pane.getChildren().clear();
                                                pane.getChildren().add(page);
                                                EditarPagoController controller = loader.<EditarPagoController>getController();
                                                controller.initData(pago.getIdPago());
                      
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
        
        Callback<TableColumn<Pago, String>, TableCell<Pago, String>> cellFactoryBorrar = //
                new Callback<TableColumn<Pago, String>, TableCell<Pago, String>>()
                {
                    @Override
                    public TableCell call( final TableColumn<Pago, String> param )
                    {
                        final TableCell<Pago, String> cell = new TableCell<Pago, String>()
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
                                             Alert alert = new Alert(AlertType.CONFIRMATION);
                                            alert.setTitle("Confirmación");
                                            alert.setHeaderText("¿Seguro que quieres borrar el pago?");

                                            Optional<ButtonType> result = alert.showAndWait();
                                            if (result.get() == ButtonType.OK){
                                                try {
                                                    Pago pago = getTableView().getItems().get( getIndex() );
                                                    query.deletePago(pago.getIdPago());
                                                    FXMLLoader loader = new FXMLLoader(getClass().getResource( "ListaPagos.fxml"));
                                                    AnchorPane page = (AnchorPane) loader.load();
                                                    pane.getChildren().clear();
                                                    pane.getChildren().add(page);
                                                    ListaPagosController controller = loader.<ListaPagosController>getController();
                                                    controller.initData(pago.getIdPaciente().getIdpaciente());
                      
                                                } catch (IOException ex) {
                                                    Logger.getLogger(ListaPagosController.class.getName()).log(Level.SEVERE, null, ex);
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
        lista_tabla = FXCollections.observableArrayList(lista_pagos);
        tabla_pagos.setItems(lista_tabla);
        if(lista_tabla.isEmpty()){
            tabla_pagos.setPlaceholder(new Label("Este paciente aún no ha realizado ningún pago"));
        }
        tabla_pagos.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }
    
}
