package proyectoeditorvideo;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import javax.imageio.ImageIO;

import proyectoeditorvideo.Video;

public class FXMLDocumentController implements Initializable {
    
    Video vids;
    
    MediaPlayer mediaPlayer;
    
    Image imagen;
    
    private int width;
    
    private int height;
    @FXML
    private MediaView mediaView;
    
    @FXML
    private Slider progressBar;
    
    @FXML
    private ImageView imagenVideo;
    
    @FXML 
    private Pane panelContenedor;
    
    private String sUriVideo;
    
    private String sUriImage;
    
    private String sUriAudio;
    
    @FXML
    private void agregarVideo(ActionEvent event){
        
        if(mediaPlayer != null){
            mediaPlayer.stop();
        }
        
        sUriVideo = vids.selectVideo();
        
        
        if(sUriVideo != null){
            Media media = new Media(sUriVideo);
            
            mediaPlayer = new MediaPlayer(media);
            mediaView.setMediaPlayer(mediaPlayer);
            
            System.out.println(mediaView.getFitWidth());
            System.out.println(mediaView.getFitHeight());
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
        
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Select a File (*.PNG)", "*.PNG");
        fileChooser.getExtensionFilters().add(filter);
        File file = fileChooser.showOpenDialog(null);
        if(file != null){
            sUriImage = file.toURI().toString();
            imagen = new Image(sUriImage,200,150,false,false);
            saveToFile(imagen);
            imagenVideo.setLayoutX(0);
            imagenVideo.setLayoutY(0);
            imagenVideo.setImage(imagen);
            imagenVideo.setFitWidth(imagen.getWidth());
            imagenVideo.setFitHeight(imagen.getHeight());
        }
    }
    
    public static void saveToFile(Image image) {
    File outputFile = new File("imageAux.png");
    BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
    try {
      ImageIO.write(bImage, "png", outputFile);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
    
    @FXML
    private void quitarImagen(ActionEvent event){
        
        imagenVideo.setImage(null);
        imagen = null;
    }
    
    @FXML
    private void moverIzquierdaImagen(ActionEvent event){
        
        if(imagen != null){
            if(imagenVideo.getLayoutX() != 0){
                imagenVideo.setLayoutX(imagenVideo.getLayoutX()-5);
            }
        }
      
    }
    
    @FXML
    private void moverArribaImagen(ActionEvent event){

        if(imagen != null){
            if(imagenVideo.getLayoutY() != 0){
                imagenVideo.setLayoutY(imagenVideo.getLayoutY()-5);
            }
        }
        
    }
    
    @FXML
    private void moverAbajoImagen(ActionEvent event){
        if(imagen != null){
            if(imagenVideo.getLayoutY()+imagenVideo.getFitHeight() <= 280){
                imagenVideo.setLayoutY(imagenVideo.getLayoutY()+5);
            }
        }
      
    }
    
    @FXML
    private void moverDerechaImagen(ActionEvent event){   
        if(imagen != null){
            if(imagenVideo.getLayoutX()+imagenVideo.getFitWidth() <= 500 ){
                imagenVideo.setLayoutX(imagenVideo.getLayoutX()+5);
            }
        }  
    }
    
    @FXML
    private void agregarAudio(ActionEvent event){
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Select a File (*.MP3)", "*.MP3");
        fileChooser.getExtensionFilters().add(filter);
        File file = fileChooser.showOpenDialog(null);
        if(file != null){
            sUriAudio = file.toURI().toString();
            mediaPlayer.stop();
            mediaPlayer.dispose();
            
        }
    }
    
    @FXML
    private void quitarAudio(ActionEvent event){
        System.out.println("Boton de quitar audio");
    }
    
    @FXML
    private void guardarTodo(ActionEvent event){
        
        //mediaPlayer.dispose();
        String nuevo = sUriVideo.substring(6);
        String nuevoAudio = sUriAudio.substring(6);
        if(sUriAudio == null && sUriImage != null)
        {
            VideoThumbnailsExample.agregarImagen(nuevo, "result.mp4", "imageAux.png");
        }
        else if(sUriAudio != null && sUriImage == null)
        {
            VideoThumbnailsExample.agregarMusica(nuevo, "result.mp4", nuevoAudio);
        }
        else if(sUriAudio != null && sUriImage != null)
        {
            VideoThumbnailsExample.agregarImagen(nuevo, "result.mp4", "imageAux.png");
            VideoThumbnailsExample.agregarMusica("result.mp4", "resultAudio.mp4", nuevoAudio);
            try {
        	File inputFile = new File("result.mp4");
                File tempFile = new File("resultAudio.mp4");
		Files.move(tempFile.toPath(), inputFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        }
        System.out.println("ACABE");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Primera ejecución es acá
        vids = new Video();
    }    
    
}
