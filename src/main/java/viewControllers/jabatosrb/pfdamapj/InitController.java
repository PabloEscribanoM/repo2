package jabatosrb.pfdamapj;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class InitController {
    @FXML
    private Label lblSalida;
    @FXML
    private Button btnHola;

    public void holaOnAction(ActionEvent actionEvent) {
        lblSalida.setText("Hola");
    }
}
