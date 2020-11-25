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
    private void evento(ActionEvent event){
        System.out.println("Hola friends");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Primera ejecución es acá
    }    
    
}
