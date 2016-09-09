package consultorio.dental;

import datos.Imagen;
import datos.Paciente;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Galeria extends Application {
    private Paciente p;
    private PacienteQuery query = new PacienteQuery();
    private List<Imagen> imagenes = new ArrayList<>();
    
    @Override public void start(Stage stage) {
        stage.setWidth(606);
        stage.setHeight(669);
        stage.setResizable(false);
        stage.setTitle("Imagenes de paciente");
        Pane root = new Pane();
        Scene scene = new Scene(root);
        scene.getStylesheets().addAll(this.getClass().getResource("estilo.css").toExternalForm());
        stage.setScene(scene);

        imagenes = query.imagenesPaciente(p.getIdpaciente());

        Button boton = new Button("Añadir imagen");
        boton.setOnAction((ActionEvent event) -> {
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AnadirImagen.fxml"));
                Parent root1 = (Parent) loader.load();
                AnadirImagenController controller = loader.<AnadirImagenController>getController();
                controller.initData(p.getIdpaciente());
                Stage stageAnadir = new Stage();
                stageAnadir.initModality(Modality.APPLICATION_MODAL);
                stageAnadir.setTitle("Añadir imagen");
                stageAnadir.setScene(new Scene(root1));
                stageAnadir.show();
                stage.close();
            }catch(Exception ex){
                ex.printStackTrace();
            }
        });

        BorderPane pane = new BorderPane();
        pane.setTop(boton);
        pane.setAlignment(boton, Pos.CENTER);
        
        String borderStyle = "-fx-border-color: #92c7d8;"
              + " -fx-border-width: 1;" ;
        
        Label labelDescripcion = new Label();
        labelDescripcion.setMaxWidth(600);
        labelDescripcion.setStyle(borderStyle);
        ImageView fullView = new ImageView();
        StackPane fullStackPane = new StackPane();
        fullView.setFitWidth(600);
        fullView.setFitHeight(450);
        fullView.setStyle(borderStyle);
        if(imagenes.isEmpty()){
            Image empty_image = new Image(getClass().getResourceAsStream("imagen_no_disponible.jpg"));
            fullView.setImage(empty_image);

            fullStackPane.getChildren().addAll(fullView);
        }else{
            fullView.setImage(new Image(new File(imagenes.get(0).getRuta()).toURI().toString()));
            labelDescripcion.setText((imagenes.get(0).getDescripcion()));// descripcion graaandeee
            fullStackPane.getChildren().addAll(fullView,labelDescripcion);
            fullStackPane.setAlignment(labelDescripcion,Pos.BOTTOM_LEFT);
            labelDescripcion.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
            HBox hBox = new HBox(5);
    
            for(int i = 0;i< imagenes.size(); i++ ){
                StackPane stackPane = new StackPane();
                Label descImagen = new Label(imagenes.get(i).getDescripcion());//descripcion chicaaa
                descImagen.setMaxWidth(200);
                descImagen.setStyle(borderStyle);
                descImagen.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
                ImageView view = new ImageView();
                
                view.setImage(new Image(new File(imagenes.get(i).getRuta()).toURI().toString()));
                
                view.setFitWidth(200);
                view.setFitHeight(150);
                view.setStyle(borderStyle);
                view.setId(Integer.toString(i));
                view.setOnMouseClicked(event -> {
                    fullView.setImage(view.getImage());
                    labelDescripcion.setText(imagenes.get(Integer.parseInt(view.getId())).getDescripcion());
                });
                
                Button boton_quitar= new Button("Quitar");
                boton_quitar.setId(imagenes.get(i).getIdImagen().toString());
                
                boton_quitar.setOnAction((ActionEvent event) -> {
                   
                    Alert alert = new Alert(AlertType.CONFIRMATION);
                    alert.setTitle("¿Seguro que deseas quitar la imagen?");
                    alert.setHeaderText("¿Seguro que deseas quitar esta imagen?");

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK){
                        query.deleteImagen(Integer.parseInt(boton_quitar.getId()));
                        hBox.getChildren().remove(stackPane);
                        
                        List<Imagen> imagenes_restantes = query.imagenesPaciente(p.getIdpaciente());
                        if(imagenes_restantes.isEmpty()){
                            fullView.setImage(new Image(getClass().getResourceAsStream("imagen_no_disponible.jpg")));
                            labelDescripcion.setText("");
                        }else{
                            fullView.setImage(new Image(new File(imagenes_restantes.get(0).getRuta()).toURI().toString()));
                            labelDescripcion.setText((imagenes.get(0).getDescripcion()));
                        }
                        
                    } 
                });
                stackPane.getChildren().addAll(view,boton_quitar,descImagen);
                stackPane.setAlignment(boton_quitar,Pos.TOP_RIGHT);
                stackPane.setAlignment(descImagen,Pos.BOTTOM_LEFT);
                hBox.getChildren().add(stackPane);
            }

            ScrollPane scroll = new ScrollPane(hBox);
            scroll.setMaxWidth(600);
            scroll.setMinHeight(165);
            scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

            
            pane.setBottom(scroll);
        }
        
        pane.setCenter(fullStackPane);
        root.getChildren().add(pane);
        stage.show();
    }
    
    public void initData(int idPaciente){
        p = query.pacientePorId(idPaciente).get(0);
    }
   
}