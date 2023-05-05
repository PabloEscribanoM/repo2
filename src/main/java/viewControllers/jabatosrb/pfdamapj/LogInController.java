package jabatosrb.pfdamapj;

import jabatosrb.pfdampj.PersistentData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;

public class LogInController extends ViewUtilities{
    @FXML
    private PasswordField txtPwd;
    @FXML
    private TextField txtEmail;


    public void logInOnAction(ActionEvent actionEvent) throws SQLException, IOException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, ParseException {
        Administrador user = AdministradorController.getUser(txtEmail.getText(), txtPwd.getText());
        if (user !=null){
            //guardo los datos del usuario que ha iniciado sesión
            PersistentData.setUser(user);
            //cambiar a la ventana principal
            Parent root = FXMLLoader.load(LogInController.class.getResource("fxml/principal_view.fxml"));
            Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    public void registerOnAction(ActionEvent actionEvent) throws IOException {
        ventanaModal(actionEvent, LogInController.class.getResource("fxml/futuroSocio_view.fxml"), "Registro:");
        PersistentData.setFuturoSocioMod(null);
    }
}
