/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consultorio.dental;

import datos.Ajustes;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Ricardo
 */
public class ConsultorioDental extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        PacienteQuery query = new PacienteQuery();
        List<Ajustes> lista_ajustes = new ArrayList<>();
        lista_ajustes = query.ajustes();
        if(lista_ajustes.isEmpty()){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Bienvenida.fxml"));
            Parent root1 = (Parent) loader.load();
            Stage bienvenida = new Stage();
            bienvenida.setTitle("Bienvenido");
            bienvenida.setScene(new Scene(root1));  
            bienvenida.show();
            bienvenida.toFront();
            bienvenida.setIconified(false);
        }else{
            Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.getIcons().add(new javafx.scene.image.Image(getClass().getResourceAsStream("dental6.png")));
            stage.setTitle("Dental Software"); 
           stage.setResizable(false);

            FadeTransition ft = new FadeTransition(Duration.millis(3000), root);
            ft.setFromValue(0.0);
            ft.setToValue(1.0);
            ft.play();
            stage.show();
            stage.toFront();
            stage.setIconified(false);
        }
        
    }


    
}
