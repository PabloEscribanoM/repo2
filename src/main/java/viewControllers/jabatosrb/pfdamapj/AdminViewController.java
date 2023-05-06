package jabatosrb.pfdamapj;

import jabatosrb.pfdampj.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.ResourceBundle;

public class AdminViewController extends ViewUtilities implements Initializable {

    @FXML
    private Label textErr;
    @FXML
    private Button btnNext_mod;
    @FXML
    private Button btnAdd_del;
    @FXML
    private TextField textNombre;
    @FXML
    private TextField textApellidos;
    @FXML
    private TextField textEmail;
    @FXML
    private TextField textDni;
    @FXML
    private TextField textIBAN;
    @FXML
    private TextField textSalario;
    @FXML
    private TextField textArea;
    @FXML
    private DatePicker dateNacimiento;
    @FXML
    private TextField textTelefono;
    private boolean isMod=false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(PersistentData.getAdminMod()!=null){
            isMod=true;
            btnNext_mod.setText("Modificar");
            btnAdd_del.setText("Borrar");
            textNombre.setText(PersistentData.getAdminMod().getNombre());
            textApellidos.setText(PersistentData.getAdminMod().getApellidos());
            textDni.setText(PersistentData.getAdminMod().getDni());
            textIBAN.setText(PersistentData.getAdminMod().getCuentaBancaria());
            textSalario.setText(PersistentData.getAdminMod().getSalario() + "");
            textArea.setText(PersistentData.getAdminMod().getArea());
            textEmail.setText(PersistentData.getAdminMod().getEmail());
            textTelefono.setText(PersistentData.getAdminMod().getTelefono());
            System.out.println(PersistentData.getAdminMod().getFechaNacimiento());
            try {
                dateNacimiento.setValue(DateFormat.toLocalDate(PersistentData.getAdminMod().getFechaNacimientoDate()));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

        }else{
            btnNext_mod.setVisible(false);
        }
    }

    public void actionNext_mod(ActionEvent actionEvent) throws SQLException, ParseException {
        if(!validarCampos().equals("OK")){
            textErr.setText(validarCampos());
        }else{
            PersistentData.getAdminMod().setNombre(textNombre.getText().trim());
            PersistentData.getAdminMod().setApellidos(textApellidos.getText().trim());
            PersistentData.getAdminMod().setDni(textDni.getText().trim());
            PersistentData.getAdminMod().setCuentaBancaria(textIBAN.getText().trim());
            PersistentData.getAdminMod().setSalario(Double.parseDouble(textSalario.getText().trim()));
            PersistentData.getAdminMod().setArea(textArea.getText().trim());
            PersistentData.getAdminMod().setEmail(textEmail.getText().trim());
            PersistentData.getAdminMod().setTelefono(textTelefono.getText().trim());
            PersistentData.getAdminMod().setFechaNacimiento(DateFormat.toDate(dateNacimiento.getValue()));

            AdministradorController.updateAdministrador(PersistentData.getAdminMod());
            cerrarVentana(actionEvent);
        }
    }

    public void actionAdd_del(ActionEvent actionEvent) throws SQLException, ParseException, UnsupportedEncodingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        if (isMod){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setTitle("Confirmación de borrado");
            alert.setContentText("¿Estás seguro de querer borrar a " + PersistentData.getAdminMod().getNombre() + "?");
            alert.showAndWait();
            if(alert.getResult() == ButtonType.OK){
                AdministradorController.deleteAdministrador(PersistentData.getAdminMod());
                cerrarVentana(actionEvent);
            }
        }else{
            if(!validarCampos().equals("OK"))
                textErr.setText(validarCampos());
            else{
                String pass = PassGenerator.generatePass(15,PassGenerator.NUMBERS_LOWER_UPPER);
                PersistentData.setAdminMod(new Administrador(0, textNombre.getText().trim(), textApellidos.getText().trim(), textEmail.getText().trim(),
                        pass, textArea.getText().trim(), textDni.getText().trim(), new Date(),null, DateFormat.toDate(dateNacimiento.getValue()),
                         Double.parseDouble(textSalario.getText().trim()),  textIBAN.getText().trim(),textTelefono.getText().trim(),1));

                AdministradorController.addAdministrador(PersistentData.getAdminMod());
                /*
                new HiloMail(PersistentData.getAdminMod().getEmail(), "Registro como administrador - " + PersistentData.getClub().getClubNombre(),
                        "Hola " + PersistentData.getAdminMod().getNombre() + " " + PersistentData.getAdminMod().getApellidos() + ", \n" +
                        "Ha sido registrado como administrador de " + PersistentData.getClub().getClubNombre() + "\n" +
                        "Puede acceder a la aplicación de administradores usando su correro electrónico y la contraseña: \n" +
                        "\t\t" + pass
                ).start();
                */
                textNombre.setText("");
                textApellidos.setText("");
                textEmail.setText("");
                textDni.setText("");
                textArea.setText("");
                dateNacimiento.setValue(null);
                textIBAN.setText("");
                textSalario.setText("");
                textTelefono.setText("");

            }
        }
    }

    public void actionCancel(ActionEvent actionEvent) {cerrarVentana(actionEvent);}

    private String validarCampos() {
        if(textNombre.getText().trim().equals("")){
            textNombre.requestFocus();
            return "Nombre no puede ser vacio";
        }
        if(textApellidos.getText().trim().equals("")){
            textApellidos.requestFocus();
            return "Apellidos no puede ser vacio";
        }
        if(!textEmail.getText().trim().matches("[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}")){
            textEmail.requestFocus();
            return "Email no válido";
        }
        if(!textTelefono.getText().trim().matches("[0-9]{9}")){
            textTelefono.requestFocus();
            return "Teléfono no válido";
        }
        if(!textDni.getText().trim().matches("\\d{8}[a-zA-Z]")){
            textDni.requestFocus();
            return "DNI no válido";
        }
        if(textArea.getText().trim().equals("")){
            textArea.requestFocus();
            return "Area no válida";
        }
        if(!textIBAN.getText().trim().matches("[a-zA-Z]{2}[0-9]{22}")){
            System.out.println(textIBAN.getText().trim());
            textIBAN.requestFocus();
            return "Cuenta bancaria no válida";
        }
        if(!textSalario.getText().trim().matches("[0-9]+\\.?[0-9]*")){
            textSalario.requestFocus();
            return "El salario tiene que ser numérico";
        }
        if(DateFormat.anyos(DateFormat.toDate(dateNacimiento.getValue()))<18){
            dateNacimiento.requestFocus();
            return "Los administradores tienen que ser mayores de edad";
        }
        return "OK";
    }
}
