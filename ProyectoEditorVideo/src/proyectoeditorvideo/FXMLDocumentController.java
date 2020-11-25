package proyectoeditorvideo;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 *
 * @author nico
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private void agregarVideo(ActionEvent event){
        System.out.println("Boton de agregar video");
    }
    
    @FXML
    private void playVideo(ActionEvent event){
        System.out.println("Boton de play video");
    }
    
    @FXML
    private void pausaVideo(ActionEvent event){
        System.out.println("Boton de pausa video");
    }
    
    @FXML
    private void stopVideo(ActionEvent event){
        System.out.println("Boton de stop video");
    }
    
    @FXML
    private void agregarImagen(ActionEvent event){
        System.out.println("Boton de agregar imagen");
    }
    
    @FXML
    private void quitarImagen(ActionEvent event){
        System.out.println("Boton de quitar imagen");
    }
    
    @FXML
    private void moverIzquierdaImagen(ActionEvent event){
        System.out.println("Boton de mover izquierda imagen");
    }
    
    @FXML
    private void moverArribaImagen(ActionEvent event){
        System.out.println("Boton de mover arriba imagen");
    }
    
    @FXML
    private void moverAbajoImagen(ActionEvent event){
        System.out.println("Boton de moverAbajo imagen");
    }
    
    @FXML
    private void moverDerechaImagen(ActionEvent event){
        System.out.println("Boton de mover derecha imagen");
    }
    
    @FXML
    private void agregarAudio(ActionEvent event){
        System.out.println("Boton de agregar audio");
    }
    
    @FXML
    private void playAudio(ActionEvent event){
        System.out.println("Boton de play audio");
    }
    
    @FXML
    private void pausarAudio(ActionEvent event){
        System.out.println("Boton de pausar audio");
    }
    
    @FXML
    private void stopAudio(ActionEvent event){
        System.out.println("Boton de stop audio");
    }
    
    @FXML
    private void guardarTodo(ActionEvent event){
        System.out.println("Boton de guardar Todo");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Primera ejecución es acá
    }    
    
}
