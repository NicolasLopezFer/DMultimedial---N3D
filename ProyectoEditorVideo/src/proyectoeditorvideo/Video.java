package proyectoeditorvideo;

import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;

public class Video {
    
    private String filePath;
    private MediaPlayer mediaPlayer;
    
    public void Video(){}
    
    public String selectVideo(){
        System.out.println("Seleccionar Video");
        
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Select a File (*.mp4)", "*.mp4");
        fileChooser.getExtensionFilters().add(filter);
        File file = fileChooser.showOpenDialog(null);
        filePath = file.toURI().toString();
        
        return filePath;
    }
    
}
