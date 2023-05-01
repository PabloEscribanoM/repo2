package jabatosrb.pfdamapj;

import javafx.event.Event;
import javafx.scene.Node;
import javafx.stage.Stage;

public class ViewUtilities {
    protected void cerrarVentana(Event event){
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
}
