/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consultorio.dental;

import datos.DatosMedicosPaciente;
import datos.Paciente;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
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
public class AgregaPacienteController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Label label_titulo_nombre;
    
    @FXML
    private Button boton_reporte;
    
    @FXML
    private Button boton_guardar;
    
    @FXML
    private ImageView imageView_fotoPerfil;
    
    @FXML
    private TextField textfield_nombre;
    
    @FXML
    private TextField textfield_direccion;
    
    @FXML
    private TextField textfield_ciudad;
    
    @FXML
    private TextField textfield_tutor;
    
    @FXML 
    private TextField textfield_ocupacion;
    
    @FXML
    private TextField textfield_telefono;
    
    @FXML
    private TextField textfield_correo_electronico;
    
    @FXML
    private TextField nacimiento_dia;
    
    @FXML
    private ChoiceBox nacimiento_mes;
    
    @FXML
    private TextField nacimiento_ano;
    
    @FXML
    private AnchorPane pane;
    
    
    @FXML
    private Button boton_atras;
    
    ///////////////////////////////////historia clinica medica
    @FXML
    private TextField historia_enfermedad_actual;
    
    @FXML
    private TextArea historia_accidentes;
    
    @FXML
    private TextArea historia_cirugias;
    
    @FXML
    private TextArea historia_enfermedades_infancia;
    
    @FXML
    private TextArea historia_antecedentes_patologicos;
    
    @FXML
    private TextArea historia_alergias;
    
    /////////////////examen craneofacial
    
    @FXML
    private ChoiceBox examen_craneofacial_proporciones_cefalicas;
    
    @FXML
    private ChoiceBox examen_craneofacial_simetria_facial;
    
    @FXML
    private ChoiceBox examen_craneofacial_altura_facial;
    
    @FXML 
    private ChoiceBox examen_craneofacial_convexividad_facial;
    
    @FXML
    private ChoiceBox examen_craneofacial_perfil_inferior;
    
    @FXML
    private ChoiceBox examen_craneofacial_portura_labial;
    
    @FXML
    private ChoiceBox examen_craneofacial_sonrira_gingival;
    
   ///////////// examen clinico y funcional
    @FXML
    private CheckBox examen_clinico_habitos_succion_digital;
    
    @FXML
    private CheckBox examen_clinico_habitos_deglucion_atipica;
    
    @FXML
    private CheckBox examen_clinico_habitos_postural;
    
    @FXML
    private CheckBox examen_clinico_habitos_otros;
    
    @FXML
    private TextField examen_clinico_habitos_otros_textfield;
    
    @FXML
    private ChoiceBox examen_clinico_respiracion;
    
    @FXML
    private ChoiceBox examen_clinico_fonacion;
    
    @FXML
    private ChoiceBox examen_clinico_frenillo_lingual;
    
    @FXML
    private ChoiceBox examen_clinico_frenillo_labial;
    
    ////////////////////////////////examen dental y de tejidos blandos 
    @FXML
    private ChoiceBox color_textura_gingival;
    
    @FXML
    private TextField deshiscencias;
    
    @FXML
    private TextField bolsas_periodontales;
    
    @FXML
    private RadioButton amigdalas_presentes_normales;
    
    @FXML
    private RadioButton amigdalas_presentes_infectados;
    
    @FXML
    private RadioButton amigdalas_hipertroficas;
    
    @FXML
    private RadioButton amigdalas_extirpadas;
    
    @FXML
    private TextField amigdalas_extirpadas_fecha_textfield;
    
    @FXML
    private TextField oclusion_molares;
    
    @FXML
    private TextField calse_canina;
    
    @FXML
    private TextField sobremordida_horizontal;
    
    @FXML
    private TextField sobremordida_vertical;
    
    @FXML
    private ChoiceBox linea_media_corresponde;
    
    @FXML 
    private ChoiceBox mordida_cruzada;
    
    @FXML
    private TextField higiene_oral;
    
    @FXML
    private TextField curva_spee;
    
    //////////////////////////analisis radiografico
    
    @FXML
    private TextField ausencia_congenita;
    
    @FXML
    private TextField supernumerarios;
    
    @FXML
    private TextField quistes;
    
    @FXML
    private TextField lesion_perapical;
    
    @FXML
    private TextField incluidos;
    
    @FXML
    private TextField raiz_dismorfica;
    
    @FXML
    private TextField resorcion_radicular;
    
    @FXML
    private TextField terceros_molares;
    
    @FXML
    private TextField trabeculado_oseo;
    
    @FXML
    private TextField vias_aereas;
    
    @FXML
    private TextField senos_maxilares;
    
    @FXML
    private TextField analisis_radiografico_otros;
    
    /////////////////////////////analisis de modelos
    
    @FXML
    private TextArea caracteristicas_arcos_superior;
    
    @FXML
    private TextArea caracteristicas_arcos_inferior;
    
    ////////////////////////////analisis de steiner
    
    @FXML 
    private TextField steiner_1_1;
    
    @FXML 
    private TextField steiner_1_2;
    
    @FXML 
    private TextField steiner_1_3;
    
    @FXML 
    private TextField steiner_1_4;
    
    @FXML 
    private TextField steiner_2_1;
    
    @FXML 
    private TextField steiner_2_2;
    
    @FXML 
    private TextField steiner_2_3;
    
    @FXML 
    private TextField steiner_2_4;
    
    @FXML 
    private TextField steiner_3_1;
    
    @FXML 
    private TextField steiner_3_2;
    
    @FXML 
    private TextField steiner_3_3;
    
    @FXML 
    private TextField steiner_3_4;
    
    @FXML 
    private TextField steiner_4_1;
    
    @FXML 
    private TextField steiner_4_2;
    
    @FXML 
    private TextField steiner_4_3;
    
    @FXML 
    private TextField steiner_4_4;
    
    @FXML 
    private TextField steiner_5_1;
    
    @FXML 
    private TextField steiner_5_2;
    
    @FXML 
    private TextField steiner_5_3;
    
    @FXML 
    private TextField steiner_5_4;
    
    @FXML 
    private TextField steiner_6_1;
    
    @FXML 
    private TextField steiner_6_2;
    
    @FXML 
    private TextField steiner_6_3;
    
    @FXML 
    private TextField steiner_6_4;
    
    @FXML 
    private TextField steiner_7_1;
    
    @FXML 
    private TextField steiner_7_2;
    
    @FXML 
    private TextField steiner_7_3;
    
    @FXML 
    private TextField steiner_7_4;
    
    @FXML 
    private TextField steiner_8_1;
    
    @FXML 
    private TextField steiner_8_2;
    
    @FXML 
    private TextField steiner_8_3;
    
    @FXML 
    private TextField steiner_8_4;
    
    @FXML 
    private TextField steiner_9_1;
    
    @FXML 
    private TextField steiner_9_2;
    
    @FXML 
    private TextField steiner_9_3;
    
    @FXML 
    private TextField steiner_9_4;
    
    @FXML 
    private TextField steiner_10_1;
    
    @FXML 
    private TextField steiner_10_2;
    
    @FXML 
    private TextField steiner_10_3;
    
    @FXML 
    private TextField steiner_10_4;
    
    @FXML 
    private TextField steiner_11_1;
    
    @FXML 
    private TextField steiner_11_2;
    
    @FXML 
    private TextField steiner_11_3;
    
    @FXML 
    private TextField steiner_11_4;
    
    @FXML 
    private TextField steiner_12_1;
    
    @FXML 
    private TextField steiner_12_2;
    
    @FXML 
    private TextField steiner_12_3;
    
    @FXML 
    private TextField steiner_12_4;
    
    @FXML 
    private TextField steiner_13_1;
    
    @FXML 
    private TextField steiner_13_2;
    
    @FXML 
    private TextField steiner_13_3;
    
    @FXML 
    private TextField steiner_13_4;
    
    @FXML 
    private TextField steiner_14_1;
    
    @FXML 
    private TextField steiner_14_2;
    
    @FXML 
    private TextField steiner_14_3;
    
    @FXML 
    private TextField steiner_14_4;
    
    @FXML 
    private TextField steiner_15_1;
    
    @FXML 
    private TextField steiner_15_2;
    
    @FXML 
    private TextField steiner_15_3;
    
    @FXML 
    private TextField steiner_15_4;
    
    //////////////////////////////////////analisis rickets
    
    @FXML
    private TextField maxilar_inferior_1_1;
    
    @FXML
    private TextField maxilar_inferior_1_2;
    
    @FXML
    private TextField maxilar_inferior_1_3;
    
    @FXML
    private TextField maxilar_inferior_1_4;
    
     @FXML
    private TextField maxilar_inferior_2_1;
    
    @FXML
    private TextField maxilar_inferior_2_2;
    
    @FXML
    private TextField maxilar_inferior_2_3;
    
    @FXML
    private TextField maxilar_inferior_2_4;
    
     @FXML
    private TextField maxilar_inferior_3_1;
    
    @FXML
    private TextField maxilar_inferior_3_2;
    
    @FXML
    private TextField maxilar_inferior_3_3;
    
    @FXML
    private TextField maxilar_inferior_3_4;
    
     @FXML
    private TextField maxilar_inferior_4_1;
    
    @FXML
    private TextField maxilar_inferior_4_2;
    
    @FXML
    private TextField maxilar_inferior_4_3;
    
    @FXML
    private TextField maxilar_inferior_4_4;
    
     @FXML
    private TextField maxilar_inferior_5_1;
    
    @FXML
    private TextField maxilar_inferior_5_2;
    
    @FXML
    private TextField maxilar_inferior_5_3;
    
    @FXML
    private TextField maxilar_inferior_5_4;
    
    @FXML
    private TextField maxilar_superior_1_1;
    
    @FXML
    private TextField maxilar_superior_1_2;
    
    @FXML
    private TextField maxilar_superior_1_3;
    
    @FXML
    private TextField maxilar_superior_1_4;
    
    @FXML
    private TextField maxilar_superior_2_1;
    
    @FXML
    private TextField maxilar_superior_2_2;
    
    @FXML
    private TextField maxilar_superior_2_3;
    
    @FXML
    private TextField maxilar_superior_2_4;
    
    @FXML
    private TextField dientes_1_1;
    
    @FXML
    private TextField dientes_1_2;
    
    @FXML
    private TextField dientes_1_3;
    
    @FXML
    private TextField dientes_1_4;
    
    @FXML
    private TextField dientes_2_1;
    
    @FXML
    private TextField dientes_2_2;
    
    @FXML
    private TextField dientes_2_3;
    
    @FXML
    private TextField dientes_2_4;
    
    @FXML
    private TextField dientes_3_1;
    
    @FXML
    private TextField dientes_3_2;
    
    @FXML
    private TextField dientes_3_3;
    
    @FXML
    private TextField dientes_3_4;
    
    @FXML
    private TextField dientes_4_1;
    
    @FXML
    private TextField dientes_4_2;
    
    @FXML
    private TextField dientes_4_3;
    
    @FXML
    private TextField dientes_4_4;
    
    @FXML
    private TextField dientes_5_1;
    
    @FXML
    private TextField dientes_5_2;
    
    @FXML
    private TextField dientes_5_3;
    
    @FXML
    private TextField dientes_5_4;
    
    @FXML
    private TextField perfil_blando_1_1;
    
    @FXML
    private TextField perfil_blando_1_2;
    
    @FXML
    private TextField perfil_blando_1_3;
    
    @FXML
    private TextField perfil_blando_1_4;
    
    //////////////////////////// conclusiones
    
    @FXML
    private TextArea conclusiones_esqueletales;
    
    @FXML
    private TextArea conclusiones_dentales;
    
    ///////////////////////////////diagnostico
    
    @FXML
    private TextField relacion_esqueletal;
    
    @FXML
    private TextField con_responsiva_de;
    
    @FXML
    private TextField tipo_de_crecimiento;
    
    @FXML
    private TextField clase_molar;
    
    @FXML
    private TextField clase_canina;
    
    @FXML
    private TextField inclinacion_de_inc_sup;
    
    @FXML
    private TextField inclinacion_de_inc_inf;
    
    @FXML
    private TextArea diagnostico_objetivos;
    
    @FXML
    private TextArea diagnostico_pronostico;
    
    ///////////////////////////////////plan de tratamiento
    
    @FXML
    private TextArea plan_de_tratamiento;
    
    @FXML
    private TextField tiempo_estimado_de_tratamiento;
    
    ////////////////////////////////////////////
    
    @FXML
    private Tab datos_generales;
    
    @FXML
    private TabPane tabPane;
    
    FXMLLoader loader_origen;
    String ventana_origen;
    int id_origen;
    
    String ruta_imagen = new String();
    
    private PacienteQuery query = new PacienteQuery();
    
    private Paciente paciente_final = new Paciente();
    private DatosMedicosPaciente datos_final = new DatosMedicosPaciente();
    
    Paciente p;
    
   @FXML 
   public void actionBotonCambiarImagen(ActionEvent e){
        try{
            
                FileChooser fileChooser = new FileChooser();
                FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Archivos de imagen", "*.png", "*.jpg", "*.gif");
                fileChooser.getExtensionFilters().add(extFilter);
                Node node = (Node) e.getSource();
                File file = fileChooser.showOpenDialog(node.getScene().getWindow());
                imageView_fotoPerfil.setImage(new Image(file.toURI().toString()));
                ruta_imagen = file.getAbsolutePath();
        }catch(Exception ex){
        
        }
   }
    
   @FXML
   public void actionBotonReporte(ActionEvent e) throws ParseException, IOException{
        Node node = (Node) e.getSource();
        Scene scene = node.getScene();
        Task longTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                scene.setCursor(Cursor.WAIT);
               int edad = 0;
                List<DatosMedicosPaciente> lista_datos_medicos = new ArrayList<>();
                DatosMedicosPaciente datos_medicos;
                LocalDate today = LocalDate.now();
                lista_datos_medicos = query.datosMedicosPacientePorPaciente(p);
                 datos_medicos = lista_datos_medicos.get(0);
                if(p.getFechanac()!=null){
                     LocalDate date = p.getFechanac().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                     Period period = Period.between(date, today);
                     edad = period.getYears();
                 }
                 try {
                      guardar();
                      Map parameters = new HashMap();
                      parameters.put("id_paciente", p.getIdpaciente());
                      parameters.put("edad", edad);
                      parameters.put("habitos", habitosBaseDatos(datos_medicos.getExamenClinicoHabitos()));
                      
                      JasperReport jr = (JasperReport)JRLoader.loadObject(new File(System.getenv("ProgramFiles")+"\\Dental Software\\reportes\\reportePaciente.jasper"));

                      
                      query.em.getTransaction().begin();
                      JasperPrint jp = JasperFillManager.fillReport(jr, parameters, query.em.unwrap(java.sql.Connection.class));
                      query.em.getTransaction().commit();

                      JasperViewer jv = new JasperViewer(jp,false); 
                      jv.setVisible(true);  
                      jv.setTitle("Historia clínica de " + p.getNombre());

                  } catch (JRException ex) {
                      Logger.getLogger(VerPacienteController.class.getName()).log(Level.SEVERE, null, ex);
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
    
    @FXML
    public void actionBotonGuardar(ActionEvent e) throws ParseException, IOException{
        guardar();
         AnchorPane page = (AnchorPane) loader_origen.load();
            pane.getChildren().clear();///name of pane where you want to put the fxml.
            pane.getChildren().add(page);
            FadeTransition ft = new FadeTransition(Duration.millis(1000), pane);
            ft.setFromValue(0.0);
            ft.setToValue(1.0);
            ft.play();
            if(ventana_origen.equals("cita")){
                DetalleCitaController controller = loader_origen.<DetalleCitaController>getController();
                controller.initData(id_origen);
            }else if(ventana_origen.equals("paciente")){
                VerPacienteController controller = loader_origen.<VerPacienteController>getController();
                controller.initData(id_origen);
            }
    }
    
    @FXML
    public void actionBotonAtras(ActionEvent e) throws IOException{
        AnchorPane page = (AnchorPane) loader_origen.load();
            pane.getChildren().clear();///name of pane where you want to put the fxml.
            pane.getChildren().add(page);
            FadeTransition ft = new FadeTransition(Duration.millis(1000), pane);
            ft.setFromValue(0.0);
            ft.setToValue(1.0);
            ft.play();
            if(ventana_origen.equals("cita")){
                DetalleCitaController controller = loader_origen.<DetalleCitaController>getController();
                controller.initData(id_origen);
            }else if(ventana_origen.equals("paciente")){
                VerPacienteController controller = loader_origen.<VerPacienteController>getController();
                controller.initData(id_origen);
            }
    }
    
    
   @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }
    
    void initData(int id_nuevo, FXMLLoader loader, String nombreVentana, int id){
        loader_origen = loader;
        ventana_origen = nombreVentana;
        id_origen = id;
        Image img_guardar = new Image(getClass().getResourceAsStream("Save_Icon_Silhouette_24.png"));
        Image img_atras = new Image(getClass().getResourceAsStream("back-left-arrow.png"));
        boton_guardar.setGraphic(new ImageView(img_guardar));
        boton_atras.setGraphic(new ImageView(img_atras));
        imageView_fotoPerfil.setImage(new Image(getClass().getResourceAsStream("imagen_no_disponible.jpg")));
        nacimiento_mes.setItems(FXCollections.observableArrayList(
            "Mes","Enero", "Febrero", 
            "Marzo", "Abril", "Mayo",
            "Junio", "Julio", "Agosto", "Septiembre",
            "Octubre", "Noviembre", "Diciembre")
        );
        nacimiento_mes.getSelectionModel().selectFirst();
        
        ///////////////////////////////////////////////////////////////// examen craneofacial
        
       examen_craneofacial_proporciones_cefalicas.setItems(FXCollections.observableArrayList(
            "Mesocéfalo","Braquicéfalo","Dolicocéfalo")
        );
        
       examen_craneofacial_simetria_facial.setItems(FXCollections.observableArrayList(
            "Simétrico","Asimétrico")
        );
       
       examen_craneofacial_altura_facial.setItems(FXCollections.observableArrayList(
            "Proporcionado","Corta","Larga")
        );
       
       examen_craneofacial_convexividad_facial.setItems(FXCollections.observableArrayList(
            "Recto","Cóncavo","Convexo")
        );
       
       examen_craneofacial_perfil_inferior.setItems(FXCollections.observableArrayList(
            "Recto","Cóncavo","Convexo")
        );
       
         examen_craneofacial_portura_labial.setItems(FXCollections.observableArrayList(
            "Competencia","Incompetencia","Labios Normales","Labio Superior Corto","Proquelia Sup.","Proquelia Inf.","Biproquelia")
        );
         
         examen_craneofacial_sonrira_gingival.setItems(FXCollections.observableArrayList(
            "Sí","No")
        );
         
        ////////////////////////////////examen clinico y funcional
         examen_clinico_habitos_otros_textfield.setVisible(false);
         examen_clinico_habitos_otros.selectedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(examen_clinico_habitos_otros.isSelected()){
                    examen_clinico_habitos_otros_textfield.setVisible(true);
                }else{
                    examen_clinico_habitos_otros_textfield.setVisible(false);
                }
            }
      
    });
         
         examen_clinico_respiracion.setItems(FXCollections.observableArrayList(
           "Normal","Oral")
        );
         
         examen_clinico_fonacion.setItems(FXCollections.observableArrayList(
           "Normal","Nasal","Gutural")
        );
         
         examen_clinico_frenillo_lingual.setItems(FXCollections.observableArrayList(
           "Normal","Largo","Corto")
        );
         
         examen_clinico_frenillo_labial.setItems(FXCollections.observableArrayList(
           "Normal","Largo","Corto")
        );
         
       ///////////////////////examen dental y de tejidos blancos
         final ToggleGroup grupo_amigdalas_adenoides = new ToggleGroup();
         
         color_textura_gingival.setItems(FXCollections.observableArrayList(
           "Normal","Inflamado")
        );
         
         amigdalas_presentes_normales.setToggleGroup(grupo_amigdalas_adenoides);
         amigdalas_presentes_infectados.setToggleGroup(grupo_amigdalas_adenoides);
         amigdalas_hipertroficas.setToggleGroup(grupo_amigdalas_adenoides);
         amigdalas_extirpadas.setToggleGroup(grupo_amigdalas_adenoides);
         amigdalas_extirpadas_fecha_textfield.setVisible(false);
         
         amigdalas_extirpadas.selectedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(amigdalas_extirpadas.isSelected()){
                    amigdalas_extirpadas_fecha_textfield.setVisible(true);
                    amigdalas_extirpadas.setText("Extirpadas Fecha:");
                }else{
                    amigdalas_extirpadas_fecha_textfield.setVisible(false);
                    amigdalas_extirpadas.setText("Extirpadas");
                }
            }
      
    });
         
         linea_media_corresponde.setItems(FXCollections.observableArrayList(
           "Si","No")
        );
         
        mordida_cruzada.setItems(FXCollections.observableArrayList(
           "Si","No")
        );
        
       ///////////////////////////////////////////////////////////////////// ///////////////////////////////////////// si no  es nuevo
        
        List<Paciente> pacientes = new ArrayList<>();
        pacientes = query.pacientePorId(id_nuevo);
        p = null;
        
        if(pacientes.size()!=0){
            p = pacientes.get(0);
        }else{
            boton_reporte.setVisible(false);
        }
        
        if(p != null){
            label_titulo_nombre.setText(p.getNombre());
            List<DatosMedicosPaciente> datos_lista = new ArrayList<>();
            datos_lista = query.datosMedicosPacientePorPaciente(p);
            DatosMedicosPaciente datos;
            if(datos_lista.size()==0){
                datos = new DatosMedicosPaciente();
                datos.setIdpaciente(p);
                datos_final.setIdDatosMedicosPaciente(query.saveDatosMedicosPaciente(datos));
            }else{
                datos = datos_lista.get(0);
            }
            
            
            
            
            paciente_final=p;
            datos_final=datos;
            
            textfield_nombre.setText(p.getNombre());
            textfield_direccion.setText(p.getDireccion());
            textfield_ciudad.setText(p.getCiudad());
            textfield_tutor.setText(p.getTutor());
            textfield_ocupacion.setText(p.getOcupacion());
            textfield_telefono.setText(p.getTelefono());
            textfield_correo_electronico.setText(p.getMail());
            try{
                imageView_fotoPerfil.setImage(new Image(new File(p.getRutaImagen()).toURI().toString()));
                ruta_imagen = p.getRutaImagen();
            }catch(Exception ex){
                
            }
            if(p.getFechanac()!=null){
                LocalDate date = p.getFechanac().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            
            nacimiento_dia.setText(Integer.toString(date.getDayOfMonth()));
            nacimiento_mes.setValue(mesTexto(date.getMonth()));
            nacimiento_ano.setText(Integer.toString(date.getYear()));
            }   
            
                
            
                
                historia_enfermedad_actual.setText(datos.getHistoriaEnfermedadActual());
                historia_accidentes.setText(datos.getHistoriaAccidentes());
                historia_cirugias.setText(datos.getHistoriaCirugias());
                historia_enfermedades_infancia.setText(datos.getHistoriaEnfermedadesInfancia());
                historia_antecedentes_patologicos.setText(datos.getHistoriaAntecedentesPatologicos());
                historia_alergias.setText(datos.getHistoriaAlergias());
                
                examen_craneofacial_proporciones_cefalicas.setValue(datos.getExamenCraneofacialProporcionesCefalicas());
                examen_craneofacial_simetria_facial.setValue(datos.getExamenCraneofacialSimetriaFacial());
                examen_craneofacial_altura_facial.setValue(datos.getExamenCraneofacialAlturaFacial());
                examen_craneofacial_convexividad_facial.setValue(datos.getExamenCraneofacialConvexividadFacial());
                examen_craneofacial_perfil_inferior.setValue(datos.getExamenCraneofacialPerfilInferior());
                examen_craneofacial_portura_labial.setValue(datos.getExamenCraneofacialPorturaLabial());
                examen_craneofacial_sonrira_gingival.setValue(datos.getExamenCraneofacialSonriraGingival());
                
                if(datos.getExamenClinicoHabitos()!=null){
                    String habitos = datos.getExamenClinicoHabitos();
                
                if(habitos.contains("6346048505")){
                    examen_clinico_habitos_succion_digital.setSelected(true);
                }
                if(habitos.contains("4066825042")){
                    examen_clinico_habitos_deglucion_atipica.setSelected(true);
                }
                if(habitos.contains("2754510641")){
                    examen_clinico_habitos_postural.setSelected(true);
                }
                if(habitos.contains("1666930104")){
                    examen_clinico_habitos_otros.setSelected(true);
                    //examen_clinico_habitos_otros_textfield.setText(habitos);
                    examen_clinico_habitos_otros_textfield.setText(habitos.substring(40));
                }
                }
                
                
                
                
                examen_clinico_respiracion.setValue(datos.getExamenClinicoRespiracion());
                examen_clinico_fonacion.setValue(datos.getExamenClinicoFonacion());
                examen_clinico_frenillo_lingual.setValue(datos.getExamenClinicoFrenilloLingual());
                examen_clinico_frenillo_labial.setValue(datos.getExamenClinicoFrenilloLabial());
    
                color_textura_gingival.setValue(datos.getColorTexturaGingival());
                deshiscencias.setText(datos.getDeshiscencias());
                bolsas_periodontales.setText(datos.getBolsasPeriodontales());
                
                
                if(datos.getAmigdalas()!= null){
                    String amigdalas = datos.getAmigdalas();
                
                if(amigdalas.contains("Presentes normales")){
                    amigdalas_presentes_normales.setSelected(true);
                }
                if(amigdalas.contains("Presentes infectados")){
                    amigdalas_presentes_infectados.setSelected(true);
                }
                if(amigdalas.contains("Hipertróficas")){
                    amigdalas_hipertroficas.setSelected(true);
                }
                if(amigdalas.contains("Extirpadas: ")){
                    amigdalas_extirpadas.setSelected(true);
                    amigdalas_extirpadas_fecha_textfield.setText(amigdalas.substring(12));
                }
                }
                
                
                
                
                
                oclusion_molares.setText(datos.getOclusionMolares());
                calse_canina.setText(datos.getCalseCanina());
                sobremordida_horizontal.setText(datos.getSobremordidaHorizontal());
                sobremordida_vertical.setText(datos.getSobremordidaVertical());
                linea_media_corresponde.setValue(datos.getLineaMediaCorresponde());
                mordida_cruzada.setValue(datos.getMordidaCruzada());
                higiene_oral.setText(datos.getHigieneOral());
                curva_spee.setText(datos.getCurvaSpee());
                
                ausencia_congenita.setText(datos.getAusenciaCongenita());
                supernumerarios.setText(datos.getSupernumerarios());
                quistes.setText(datos.getQuistes());
                lesion_perapical.setText(datos.getLesionPerapical());
                incluidos.setText(datos.getIncluidos());
                raiz_dismorfica.setText(datos.getRaizDismorfica());
                resorcion_radicular.setText(datos.getResorcionRadicular());
                terceros_molares.setText(datos.getTercerosMolares());
                trabeculado_oseo.setText(datos.getTrabeculadoOseo());
                vias_aereas.setText(datos.getViasAereas());
                senos_maxilares.setText(datos.getSenosMaxilares());
                analisis_radiografico_otros.setText(datos.getAnalisisRadiograficoOtros());
                
                caracteristicas_arcos_superior.setText(datos.getCaracteristicasArcosSuperior());
                caracteristicas_arcos_inferior.setText(datos.getCaracteristicasArcosInferior());
                
                steiner_1_1.setText(datos.getSteiner11());
                steiner_1_2.setText(datos.getSteiner12());
                steiner_1_3.setText(datos.getSteiner13());
                steiner_1_4.setText(datos.getSteiner14());
                steiner_2_1.setText(datos.getSteiner21());
                steiner_2_2.setText(datos.getSteiner22());
                steiner_2_3.setText(datos.getSteiner23());
                steiner_2_4.setText(datos.getSteiner24());
                steiner_3_1.setText(datos.getSteiner31());
                steiner_3_2.setText(datos.getSteiner32());
                steiner_3_3.setText(datos.getSteiner33());
                steiner_3_4.setText(datos.getSteiner34());
                steiner_4_1.setText(datos.getSteiner41());
                steiner_4_2.setText(datos.getSteiner42());
                steiner_4_3.setText(datos.getSteiner43());
                steiner_4_4.setText(datos.getSteiner44());
                steiner_5_1.setText(datos.getSteiner51());
                steiner_5_2.setText(datos.getSteiner52());
                steiner_5_3.setText(datos.getSteiner53());
                steiner_5_4.setText(datos.getSteiner54());
                steiner_6_1.setText(datos.getSteiner61());
                steiner_6_2.setText(datos.getSteiner62());
                steiner_6_3.setText(datos.getSteiner63());
                steiner_6_4.setText(datos.getSteiner64());
                steiner_7_1.setText(datos.getSteiner71());
                steiner_7_2.setText(datos.getSteiner72());
                steiner_7_3.setText(datos.getSteiner73());
                steiner_7_4.setText(datos.getSteiner74());
                steiner_8_1.setText(datos.getSteiner81());
                steiner_8_2.setText(datos.getSteiner82());
                steiner_8_3.setText(datos.getSteiner83());
                steiner_8_4.setText(datos.getSteiner84());
                steiner_9_1.setText(datos.getSteiner91());
                steiner_9_2.setText(datos.getSteiner92());
                steiner_9_3.setText(datos.getSteiner93());
                steiner_9_4.setText(datos.getSteiner94());
                steiner_10_1.setText(datos.getSteiner101());
                steiner_10_2.setText(datos.getSteiner102());
                steiner_10_3.setText(datos.getSteiner103());
                steiner_10_4.setText(datos.getSteiner104());
                steiner_11_1.setText(datos.getSteiner111());
                steiner_11_2.setText(datos.getSteiner112());
                steiner_11_3.setText(datos.getSteiner113());
                steiner_11_4.setText(datos.getSteiner114());
                steiner_12_1.setText(datos.getSteiner121());
                steiner_12_2.setText(datos.getSteiner122());
                steiner_12_3.setText(datos.getSteiner123());
                steiner_12_4.setText(datos.getSteiner124());
                steiner_13_1.setText(datos.getSteiner131());
                steiner_13_2.setText(datos.getSteiner132());
                steiner_13_3.setText(datos.getSteiner133());
                steiner_13_4.setText(datos.getSteiner134());
                steiner_14_1.setText(datos.getSteiner141());
                steiner_14_2.setText(datos.getSteiner142());
                steiner_14_3.setText(datos.getSteiner143());
                steiner_14_4.setText(datos.getSteiner144());
                steiner_15_1.setText(datos.getSteiner151());
                steiner_15_2.setText(datos.getSteiner152());
                steiner_15_3.setText(datos.getSteiner153());
                steiner_15_4.setText(datos.getSteiner154());
                
                maxilar_inferior_1_1.setText(datos.getMaxilarInferior11());
                maxilar_inferior_1_2.setText(datos.getMaxilarInferior12());
                maxilar_inferior_1_3.setText(datos.getMaxilarInferior13());
                maxilar_inferior_1_4.setText(datos.getMaxilarInferior14());
                maxilar_inferior_2_1.setText(datos.getMaxilarInferior21());
                maxilar_inferior_2_2.setText(datos.getMaxilarInferior22());
                maxilar_inferior_2_3.setText(datos.getMaxilarInferior23());
                maxilar_inferior_2_4.setText(datos.getMaxilarInferior24());
                maxilar_inferior_3_1.setText(datos.getMaxilarInferior31());
                maxilar_inferior_3_2.setText(datos.getMaxilarInferior32());
                maxilar_inferior_3_3.setText(datos.getMaxilarInferior33());
                maxilar_inferior_3_4.setText(datos.getMaxilarInferior34());
                maxilar_inferior_4_1.setText(datos.getMaxilarInferior41());
                maxilar_inferior_4_2.setText(datos.getMaxilarInferior42());
                maxilar_inferior_4_3.setText(datos.getMaxilarInferior43());
                maxilar_inferior_4_4.setText(datos.getMaxilarInferior44());
                maxilar_inferior_5_1.setText(datos.getMaxilarInferior51());
                maxilar_inferior_5_2.setText(datos.getMaxilarInferior52());
                maxilar_inferior_5_3.setText(datos.getMaxilarInferior53());
                maxilar_inferior_5_4.setText(datos.getMaxilarInferior54());
                maxilar_superior_1_1.setText(datos.getMaxilarSuperior11());
                maxilar_superior_1_2.setText(datos.getMaxilarSuperior12());
                maxilar_superior_1_3.setText(datos.getMaxilarSuperior13());
                maxilar_superior_1_4.setText(datos.getMaxilarSuperior14());
                maxilar_superior_2_1.setText(datos.getMaxilarSuperior21());
                maxilar_superior_2_2.setText(datos.getMaxilarSuperior22());
                maxilar_superior_2_3.setText(datos.getMaxilarSuperior23());
                maxilar_superior_2_4.setText(datos.getMaxilarSuperior24());
                dientes_1_1.setText(datos.getDientes11());
                dientes_1_2.setText(datos.getDientes12());
                dientes_1_3.setText(datos.getDientes13());
                dientes_1_4.setText(datos.getDientes14());
                dientes_2_1.setText(datos.getDientes21());
                dientes_2_2.setText(datos.getDientes22());
                dientes_2_3.setText(datos.getDientes23());
                dientes_2_4.setText(datos.getDientes24());
                dientes_3_1.setText(datos.getDientes31());
                dientes_3_2.setText(datos.getDientes32());
                dientes_3_3.setText(datos.getDientes33());
                dientes_3_4.setText(datos.getDientes34());
                dientes_4_1.setText(datos.getDientes41());
                dientes_4_2.setText(datos.getDientes42());
                dientes_4_3.setText(datos.getDientes43());
                dientes_4_4.setText(datos.getDientes44());
                dientes_5_1.setText(datos.getDientes51());
                dientes_5_2.setText(datos.getDientes52());
                dientes_5_3.setText(datos.getDientes53());
                dientes_5_4.setText(datos.getDientes54());
                perfil_blando_1_1.setText(datos.getPerfilBlando11());
                perfil_blando_1_2.setText(datos.getPerfilBlando12());
                perfil_blando_1_3.setText(datos.getPerfilBlando13());
                perfil_blando_1_4.setText(datos.getPerfilBlando14());
                
                conclusiones_esqueletales.setText(datos.getConclusionesEsqueletales());
                conclusiones_dentales.setText(datos.getConclusionesDentales());
                
                relacion_esqueletal.setText(datos.getRelacionEsqueletal());
                con_responsiva_de.setText(datos.getConResponsivaDe());
                tipo_de_crecimiento.setText(datos.getTipoDeCrecimiento());
                clase_molar.setText(datos.getClaseMolar());
                clase_canina.setText(datos.getClaseCanina());
                inclinacion_de_inc_sup.setText(datos.getInclinacionDeIncSup());
                inclinacion_de_inc_inf.setText(datos.getInclinacionDeIncInf());
                diagnostico_objetivos.setText(datos.getDiagnosticoObjetivos());
                diagnostico_pronostico.setText(datos.getDiagnosticoPronostico());
                
                plan_de_tratamiento.setText(datos.getPlanDeTratamiento());
                tiempo_estimado_de_tratamiento.setText(datos.getTiempoEstimadoDeTratamiento());
            
        }else{
            paciente_final=null;
            datos_final=null;
        }
        
    }
    
    private String textoMes(String mes){
        if(mes.equals("Enero")){
            return "1";
        }else if(mes.equals("Febrero")){
            return "2";
        }else if(mes.equals("Marzo")){
            return "3";
        }else if(mes.equals("Abril")){
            return "4";
        }else if(mes.equals("Mayo")){
            return "5";
        }else if(mes.equals("Junio")){
            return "6";
        }else if(mes.equals("Julio")){
            return "7";
        }else if(mes.equals("Agosto")){
            return "8";
        }else if(mes.equals("Septiembre")){
            return "9";
        }else if(mes.equals("Octubre")){
            return "10";
        }else if(mes.equals("Noviembre")){
            return "11";
        }else if(mes.equals("Diciembre")){
            return "12";
        }
        return null;
    }
    
    private String mesTexto(Month i){
        
        
        if(i == Month.JANUARY){
            return "Enero";
        }else if(i == Month.FEBRUARY){
            return "Febrero";
        }else if(i == Month.MARCH){
            return "Marzo";
        }else if(i == Month.APRIL){
            return "Abril";
        }else if(i == Month.MAY){
            return "Mayo";
        }else if(i == Month.JUNE){
            return "Junio";
        }else if(i == Month.JULY){
            return "Julio";
        }else if(i == Month.AUGUST){
            return "Agosto";
        }else if(i == Month.SEPTEMBER){
            return "Septiembre";
        }else if(i == Month.OCTOBER){
            return "Octubre";
        }else if(i == Month.NOVEMBER){
            return "Noviembre";
        }else if(i == Month.DECEMBER){
            return "Diciembre";
        }
         return null;
        
    }
    
    public void guardar() throws IOException{
        DatosMedicosPaciente datos;
        
        if(textfield_nombre.getText().equals("")){
            Alert alert = new Alert(AlertType.WARNING);
            alert.setHeaderText("No puedes dejar el nombre en blanco");
            tabPane.getSelectionModel().select(datos_generales);
            alert.showAndWait();
            
            
        }else{
            
                String date = nacimiento_dia.getText() + "/" + textoMes((String)nacimiento_mes.getSelectionModel().getSelectedItem()) + "/" + nacimiento_ano.getText();
                java.util.Date utilDate = null;
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                formatter.setLenient(false);
          try{
                utilDate = formatter.parse(date);
                
                
                if(paciente_final==null){
                    p = new Paciente();
                }else{
                    p = query.em.find(Paciente.class,paciente_final.getIdpaciente());
                    query.em.getTransaction().begin();
                }
                
               
                p.setNombre(textfield_nombre.getText());
                p.setDireccion(textfield_direccion.getText());
                p.setCiudad(textfield_ciudad.getText());
                p.setTutor(textfield_tutor.getText());
                p.setOcupacion(textfield_ocupacion.getText());
                p.setTelefono(textfield_telefono.getText());
                p.setMail(textfield_correo_electronico.getText());
                p.setRutaImagen(ruta_imagen);
                p.setFechanac(utilDate);
                //query.save(p);
                
                if(paciente_final==null){
                    List<Paciente> pacients = new ArrayList<>();
                    pacients = query.pacientePorId(query.save(p));    /////paciente es nuevo
                    p = pacients.get(0);
                }else{
                   
                    query.em.getTransaction().commit();   
                }
                
                
                
                /////////////////////////////////////////////////////////////////////////////////////////////////
                
                if(datos_final==null){                       ////datos son nuevos
                    datos = new DatosMedicosPaciente();
                    datos.setIdpaciente(p);
                }else{
                    datos = query.em.find(DatosMedicosPaciente.class, datos_final.getIdDatosMedicosPaciente()); 
                    query.em.getTransaction().begin();
                }
                
                
                
                datos.setHistoriaEnfermedadActual(historia_enfermedad_actual.getText());
                datos.setHistoriaAccidentes(historia_accidentes.getText());
                datos.setHistoriaCirugias(historia_cirugias.getText());
                datos.setHistoriaEnfermedadesInfancia(historia_enfermedades_infancia.getText());
                datos.setHistoriaAntecedentesPatologicos(historia_antecedentes_patologicos.getText());
                datos.setHistoriaAlergias(historia_alergias.getText());
                
                datos.setExamenCraneofacialProporcionesCefalicas((String)examen_craneofacial_proporciones_cefalicas.getSelectionModel().getSelectedItem());
                datos.setExamenCraneofacialSimetriaFacial((String)examen_craneofacial_simetria_facial.getSelectionModel().getSelectedItem());
                datos.setExamenCraneofacialAlturaFacial((String)examen_craneofacial_altura_facial.getSelectionModel().getSelectedItem());
                datos.setExamenCraneofacialConvexividadFacial((String)examen_craneofacial_convexividad_facial.getSelectionModel().getSelectedItem());
                datos.setExamenCraneofacialPerfilInferior((String)examen_craneofacial_perfil_inferior.getSelectionModel().getSelectedItem());
                datos.setExamenCraneofacialPorturaLabial((String)examen_craneofacial_portura_labial.getSelectionModel().getSelectedItem());
                datos.setExamenCraneofacialSonriraGingival((String)examen_craneofacial_sonrira_gingival.getSelectionModel().getSelectedItem());
                
                String habitos = new String();
                
                if(examen_clinico_habitos_succion_digital.isSelected()){
                    habitos += "6346048505";
                    
                    
                }else{
                    habitos += "          ";
                }
                if(examen_clinico_habitos_deglucion_atipica.isSelected()){
                    habitos += "4066825042";
                }else{
                    habitos += "          ";
                }
                if(examen_clinico_habitos_postural.isSelected()){
                    habitos += "2754510641";
                }else{
                    habitos += "          ";
                }
                if(examen_clinico_habitos_otros.isSelected()){
                    habitos += "1666930104" + examen_clinico_habitos_otros_textfield.getText();
                }
                
                datos.setExamenClinicoHabitos(habitos);
                
                datos.setExamenClinicoRespiracion((String)examen_clinico_respiracion.getSelectionModel().getSelectedItem());
                datos.setExamenClinicoFonacion((String)examen_clinico_fonacion.getSelectionModel().getSelectedItem());
                datos.setExamenClinicoFrenilloLingual((String)examen_clinico_frenillo_lingual.getSelectionModel().getSelectedItem());
                datos.setExamenClinicoFrenilloLabial((String)examen_clinico_frenillo_labial.getSelectionModel().getSelectedItem());
    
                datos.setColorTexturaGingival((String)color_textura_gingival.getSelectionModel().getSelectedItem());
                datos.setDeshiscencias(deshiscencias.getText());
                datos.setBolsasPeriodontales(bolsas_periodontales.getText());
                if(amigdalas_presentes_normales.isSelected()){
                    datos.setAmigdalas("Presentes normales");
                }
                if(amigdalas_presentes_infectados.isSelected()){
                    datos.setAmigdalas("Presentes infectados");
                }
                if(amigdalas_hipertroficas.isSelected()){
                    datos.setAmigdalas("Hipertróficas");
                }
                if(amigdalas_extirpadas.isSelected()){
                    datos.setAmigdalas("Extirpadas: " + amigdalas_extirpadas_fecha_textfield.getText());
                }
                
                datos.setOclusionMolares(oclusion_molares.getText());
                datos.setCalseCanina(calse_canina.getText());
                datos.setSobremordidaHorizontal(sobremordida_horizontal.getText());
                datos.setSobremordidaVertical(sobremordida_vertical.getText());
                datos.setLineaMediaCorresponde((String)linea_media_corresponde.getSelectionModel().getSelectedItem());
                datos.setMordidaCruzada((String)mordida_cruzada.getSelectionModel().getSelectedItem());
                datos.setHigieneOral(higiene_oral.getText());
                datos.setCurvaSpee(curva_spee.getText());
                
                datos.setAusenciaCongenita(ausencia_congenita.getText());
                datos.setSupernumerarios(supernumerarios.getText());
                datos.setQuistes(quistes.getText());
                datos.setLesionPerapical(lesion_perapical.getText());
                datos.setIncluidos(incluidos.getText());
                datos.setRaizDismorfica(raiz_dismorfica.getText());
                datos.setResorcionRadicular(resorcion_radicular.getText());
                datos.setTercerosMolares(terceros_molares.getText());
                datos.setTrabeculadoOseo(trabeculado_oseo.getText());
                datos.setViasAereas(vias_aereas.getText());
                datos.setSenosMaxilares(senos_maxilares.getText());
                datos.setAnalisisRadiograficoOtros(analisis_radiografico_otros.getText());
                
                datos.setCaracteristicasArcosSuperior(caracteristicas_arcos_superior.getText());
                datos.setCaracteristicasArcosInferior(caracteristicas_arcos_inferior.getText());
                
                datos.setSteiner11(steiner_1_1.getText());
                datos.setSteiner12(steiner_1_2.getText());
                datos.setSteiner13(steiner_1_3.getText());
                datos.setSteiner14(steiner_1_4.getText());
                datos.setSteiner21(steiner_2_1.getText());
                datos.setSteiner22(steiner_2_2.getText());
                datos.setSteiner23(steiner_2_3.getText());
                datos.setSteiner24(steiner_2_4.getText());
                datos.setSteiner31(steiner_3_1.getText());
                datos.setSteiner32(steiner_3_2.getText());
                datos.setSteiner33(steiner_3_3.getText());
                datos.setSteiner34(steiner_3_4.getText());
                datos.setSteiner41(steiner_4_1.getText());
                datos.setSteiner42(steiner_4_2.getText());
                datos.setSteiner43(steiner_4_3.getText());
                datos.setSteiner44(steiner_4_4.getText());
                datos.setSteiner51(steiner_5_1.getText());
                datos.setSteiner52(steiner_5_2.getText());
                datos.setSteiner53(steiner_5_3.getText());
                datos.setSteiner54(steiner_5_4.getText());
                datos.setSteiner61(steiner_6_1.getText());
                datos.setSteiner62(steiner_6_2.getText());
                datos.setSteiner63(steiner_6_3.getText());
                datos.setSteiner64(steiner_6_4.getText());
                datos.setSteiner71(steiner_7_1.getText());
                datos.setSteiner72(steiner_7_2.getText());
                datos.setSteiner73(steiner_7_3.getText());
                datos.setSteiner74(steiner_7_4.getText());
                datos.setSteiner81(steiner_8_1.getText());
                datos.setSteiner82(steiner_8_2.getText());
                datos.setSteiner83(steiner_8_3.getText());
                datos.setSteiner84(steiner_8_4.getText());
                datos.setSteiner91(steiner_9_1.getText());
                datos.setSteiner92(steiner_9_2.getText());
                datos.setSteiner93(steiner_9_3.getText());
                datos.setSteiner94(steiner_9_4.getText());
                datos.setSteiner101(steiner_10_1.getText());
                datos.setSteiner102(steiner_10_2.getText());
                datos.setSteiner103(steiner_10_3.getText());
                datos.setSteiner104(steiner_10_4.getText());
                datos.setSteiner111(steiner_11_1.getText());
                datos.setSteiner112(steiner_11_2.getText());
                datos.setSteiner113(steiner_11_3.getText());
                datos.setSteiner114(steiner_11_4.getText());
                datos.setSteiner121(steiner_12_1.getText());
                datos.setSteiner122(steiner_12_2.getText());
                datos.setSteiner123(steiner_12_3.getText());
                datos.setSteiner124(steiner_12_4.getText());
                datos.setSteiner131(steiner_13_1.getText());
                datos.setSteiner132(steiner_13_2.getText());
                datos.setSteiner133(steiner_13_3.getText());
                datos.setSteiner134(steiner_13_4.getText());
                datos.setSteiner141(steiner_14_1.getText());
                datos.setSteiner142(steiner_14_2.getText());
                datos.setSteiner143(steiner_14_3.getText());
                datos.setSteiner144(steiner_14_4.getText());
                datos.setSteiner151(steiner_15_1.getText());
                datos.setSteiner152(steiner_15_2.getText());
                datos.setSteiner153(steiner_15_3.getText());
                datos.setSteiner154(steiner_15_4.getText());
                
                datos.setMaxilarInferior11(maxilar_inferior_1_1.getText());
                datos.setMaxilarInferior12(maxilar_inferior_1_2.getText());
                datos.setMaxilarInferior13(maxilar_inferior_1_3.getText());
                datos.setMaxilarInferior14(maxilar_inferior_1_4.getText());
                datos.setMaxilarInferior21(maxilar_inferior_2_1.getText());
                datos.setMaxilarInferior22(maxilar_inferior_2_2.getText());
                datos.setMaxilarInferior23(maxilar_inferior_2_3.getText());
                datos.setMaxilarInferior24(maxilar_inferior_2_4.getText());
                datos.setMaxilarInferior31(maxilar_inferior_3_1.getText());
                datos.setMaxilarInferior32(maxilar_inferior_3_2.getText());
                datos.setMaxilarInferior33(maxilar_inferior_3_3.getText());
                datos.setMaxilarInferior34(maxilar_inferior_3_4.getText());
                datos.setMaxilarInferior41(maxilar_inferior_4_1.getText());
                datos.setMaxilarInferior42(maxilar_inferior_4_2.getText());
                datos.setMaxilarInferior43(maxilar_inferior_4_3.getText());
                datos.setMaxilarInferior44(maxilar_inferior_4_4.getText());
                datos.setMaxilarInferior51(maxilar_inferior_5_1.getText());
                datos.setMaxilarInferior52(maxilar_inferior_5_2.getText());
                datos.setMaxilarInferior53(maxilar_inferior_5_3.getText());
                datos.setMaxilarInferior54(maxilar_inferior_5_4.getText());
                datos.setMaxilarSuperior11(maxilar_superior_1_1.getText());
                datos.setMaxilarSuperior12(maxilar_superior_1_2.getText());
                datos.setMaxilarSuperior13(maxilar_superior_1_3.getText());
                datos.setMaxilarSuperior14(maxilar_superior_1_4.getText());
                datos.setMaxilarSuperior21(maxilar_superior_2_1.getText());
                datos.setMaxilarSuperior22(maxilar_superior_2_2.getText());
                datos.setMaxilarSuperior23(maxilar_superior_2_3.getText());
                datos.setMaxilarSuperior24(maxilar_superior_2_4.getText());
                datos.setDientes11(dientes_1_1.getText());
                datos.setDientes12(dientes_1_2.getText());
                datos.setDientes13(dientes_1_3.getText());
                datos.setDientes14(dientes_1_4.getText());
                datos.setDientes21(dientes_2_1.getText());
                datos.setDientes22(dientes_2_2.getText());
                datos.setDientes23(dientes_2_3.getText());
                datos.setDientes24(dientes_2_4.getText());
                datos.setDientes31(dientes_3_1.getText());
                datos.setDientes32(dientes_3_2.getText());
                datos.setDientes33(dientes_3_3.getText());
                datos.setDientes34(dientes_3_4.getText());
                datos.setDientes41(dientes_4_1.getText());
                datos.setDientes42(dientes_4_2.getText());
                datos.setDientes43(dientes_4_3.getText());
                datos.setDientes44(dientes_4_4.getText());
                datos.setDientes51(dientes_5_1.getText());
                datos.setDientes52(dientes_5_2.getText());
                datos.setDientes53(dientes_5_3.getText());
                datos.setDientes54(dientes_5_4.getText());
                datos.setPerfilBlando11(perfil_blando_1_1.getText());
                datos.setPerfilBlando12(perfil_blando_1_2.getText());
                datos.setPerfilBlando13(perfil_blando_1_3.getText());
                datos.setPerfilBlando14(perfil_blando_1_4.getText());
                
                datos.setConclusionesEsqueletales(conclusiones_esqueletales.getText());
                datos.setConclusionesDentales(conclusiones_dentales.getText());
                
                datos.setRelacionEsqueletal(relacion_esqueletal.getText());
                datos.setConResponsivaDe(con_responsiva_de.getText());
                datos.setTipoDeCrecimiento(tipo_de_crecimiento.getText());
                datos.setClaseMolar(clase_molar.getText());
                datos.setClaseCanina(clase_canina.getText());
                datos.setInclinacionDeIncSup(inclinacion_de_inc_sup.getText());
                datos.setInclinacionDeIncInf(inclinacion_de_inc_inf.getText());
                datos.setDiagnosticoObjetivos(diagnostico_objetivos.getText());
                datos.setDiagnosticoPronostico(diagnostico_pronostico.getText());
                
                datos.setPlanDeTratamiento(plan_de_tratamiento.getText());
                datos.setTiempoEstimadoDeTratamiento(tiempo_estimado_de_tratamiento.getText());
                
                if(datos_final==null){
                    query.saveDatosMedicosPaciente(datos);
                }else{
                    query.em.getTransaction().commit();
                }
 
          }catch(ParseException er){
                     Alert alert = new Alert(AlertType.WARNING);
                    alert.setHeaderText("La fecha de nacimiento es inválida");
                    tabPane.getSelectionModel().select(datos_generales);
                    alert.showAndWait();
                    
          }
            
        }
    }
    
    private String habitosBaseDatos(String habitos){
        String res = new String();
                if(habitos.contains("6346048505"))
                    res += " Succión digital/ ";
                if(habitos.contains("4066825042"))
                    res += " Deglución atípica/ ";
                if(habitos.contains("2754510641"))
                    res += " Postural /";
                if(habitos.contains("1666930104"))
                    res += " Otros: " + habitos.substring(40);
                return res;
        }
    
}
