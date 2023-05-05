package jabatosrb.pfdamapj;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class ViewUtilities {
    protected void cerrarVentana(Event event){
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }

    protected void ventanaModal(Event event, URL url, String title) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(url);
        stage.setScene(new Scene(root));
        stage.setTitle(title);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)event.getSource()).getScene().getWindow());
        stage.showAndWait();
    }
}
