package proyectoeditorvideo;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import proyectoeditorvideo.Video;

public class FXMLDocumentController implements Initializable {
    
    Video vids;
    MediaPlayer mediaPlayer;
    
    @FXML
    private MediaView mediaView;
    
    @FXML
    private Slider progressBar;
    
    @FXML
    private void agregarVideo(ActionEvent event){
        
        if(mediaPlayer != null){
            mediaPlayer.stop();
        }
        
        String suri = vids.selectVideo();
        
        if(suri != null){
            Media media = new Media(suri);
            mediaPlayer = new MediaPlayer(media);
            mediaView.setMediaPlayer(mediaPlayer);
            mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>(){
                @Override
                public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                    progressBar.setValue(newValue.toSeconds());
                }
            });
            
            progressBar.setOnMousePressed(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent event) {
                    mediaPlayer.seek(Duration.seconds(progressBar.getValue()));
                }
            });
            
            progressBar.setOnMouseDragged(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent event) {
                    mediaPlayer.seek(Duration.seconds(progressBar.getValue()));
                }
            });
            
            mediaPlayer.play();
        }
    }
    
    @FXML
    private void playVideo(ActionEvent event){
        if(mediaPlayer != null){
            mediaPlayer.play();
        }
    }
    
    @FXML
    private void pausaVideo(ActionEvent event){
        if(mediaPlayer != null){
            mediaPlayer.pause();
        }
    }
    
    @FXML
    private void stopVideo(ActionEvent event){
        if(mediaPlayer != null){
            mediaPlayer.stop();
        }
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
    private void quitarAudio(ActionEvent event){
        System.out.println("Boton de quitar audio");
    }
    
    @FXML
    private void guardarTodo(ActionEvent event){
        System.out.println("Boton de guardar Todo");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Primera ejecución es acá
        vids = new Video();
    }    
    
}
