package jabatosrb.pfdamapj;

import jabatosrb.pfdampj.DateFormat;
import jabatosrb.pfdampj.PersistentData;
import javafx.collections.FXCollections;
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

public class EscuelaViewController extends ViewUtilities implements Initializable {
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
    private TextField textNombrePadre;
    @FXML
    private TextField textApellidosPadre;
    @FXML
    private TextField textTelefono;
    @FXML
    private TextField textIBAN;
    @FXML
    private TextField textAporte;
    @FXML
    private TextField textAdeudo;
    @FXML
    private DatePicker dateNacimiento;
    @FXML
    private TextField textEmail;
    @FXML
    private ComboBox genero;
    private boolean isMod=false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        genero.setItems(FXCollections.observableArrayList("Masculino", "Femenino"));
        if(PersistentData.getEscuelaMod()!=null){
            isMod=true;
            btnNext_mod.setText("Modificar");
            btnAdd_del.setText("Borrar");
            textNombre.setText(PersistentData.getEscuelaMod().getNombre());
            textApellidos.setText(PersistentData.getEscuelaMod().getApellidos());
            textNombrePadre.setText(PersistentData.getEscuelaMod().getTutorNombre());
            textApellidosPadre.setText(PersistentData.getEscuelaMod().getTutorApellidos());
            textTelefono.setText(PersistentData.getEscuelaMod().getNumTelefono());
            textIBAN.setText(PersistentData.getEscuelaMod().getCuentaBancaria());
            textAporte.setText(PersistentData.getEscuelaMod().getAporte() + "");
            textAdeudo.setText(PersistentData.getEscuelaMod().getAdeudo() + "");
            textEmail.setText(PersistentData.getEscuelaMod().getTutorEmail());
            genero.getSelectionModel().select(PersistentData.getEscuelaMod().getGenero());
            try {
                dateNacimiento.setValue(DateFormat.toLocalDate(PersistentData.getEscuelaMod().getFechaNacimientoaDate()));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }else{
            btnNext_mod.setVisible(false);
            genero.getSelectionModel().selectFirst();
        }
    }

    public void actionNext_mod(ActionEvent actionEvent) throws SQLException, ParseException {
        if(!validarCampos().equals("OK")){
            textErr.setText(validarCampos());//POR HACER
        }else{
            PersistentData.getEscuelaMod().setNombre(textNombre.getText().trim());
            PersistentData.getEscuelaMod().setApellidos(textApellidos.getText().trim());
            PersistentData.getEscuelaMod().setTutorNombre(textNombrePadre.getText().trim());
            PersistentData.getEscuelaMod().setTutorApellidos(textApellidosPadre.getText().trim());
            PersistentData.getEscuelaMod().setNumTelefono(textTelefono.getText().trim());
            PersistentData.getEscuelaMod().setCuentaBancaria(textIBAN.getText().trim());
            PersistentData.getEscuelaMod().setAporte(Double.parseDouble(textAporte.getText().trim()));
            PersistentData.getEscuelaMod().setAdeudo(Double.parseDouble(textAdeudo.getText().trim()));
            PersistentData.getEscuelaMod().setTutorEmail(textEmail.getText().trim());
            PersistentData.getEscuelaMod().setFechaNacimiento(DateFormat.toDate(dateNacimiento.getValue()));
            PersistentData.getEscuelaMod().setGenero((String)genero.getSelectionModel().getSelectedItem());
            EscuelaController.updateEscuela(PersistentData.getEscuelaMod());
            cerrarVentana(actionEvent);
        }
    }

    public void actionAdd_del(ActionEvent actionEvent) throws SQLException, UnsupportedEncodingException, NoSuchPaddingException, IllegalBlockSizeException, ParseException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        if (isMod){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setTitle("Confirmación de borrado");
            alert.setContentText("¿Estás seguro de querer borrar a " + PersistentData.getEscuelaMod().getNombre() + "?");
            alert.showAndWait();
            if(alert.getResult() == ButtonType.OK){
                EscuelaController.deleteEscuela(PersistentData.getEscuelaMod());
                cerrarVentana(actionEvent);
            }
        }else{
            if(!validarCampos().equals("OK"))
                textErr.setText(validarCampos());
            else{
                PersistentData.setEscuelaMod(new Escuela(0, textNombre.getText().trim(), textApellidos.getText().trim(), (String)genero.getSelectionModel().getSelectedItem(),
                        null, textEmail.getText().trim(), textNombrePadre.getText().trim(), textApellidosPadre.getText().trim(), textTelefono.getText().trim(), DateFormat.toDate(dateNacimiento.getValue()),
                        new Date(), Double.parseDouble(textAporte.getText().trim()), 1, textIBAN.getText().trim(), Double.parseDouble(textAdeudo.getText().trim())));

                EscuelaController.addEscuela(PersistentData.getEscuelaMod());

                textNombre.setText("");
                textApellidos.setText("");
                textNombrePadre.setText("");
                textApellidosPadre.setText("");
                textTelefono.setText("");
                textIBAN.setText("");
                textAporte.setText("");
                textAdeudo.setText("");
                textEmail.setText("");
                dateNacimiento.setValue(null);
            }
        }
    }

    public void actionCancel(ActionEvent actionEvent) {
        cerrarVentana(actionEvent);
    }

    private String validarCampos() {
        if(textNombre.getText().trim().equals("")){
            textNombre.requestFocus();
            return "Nombre no puede ser vacio";
        }
        if(textApellidos.getText().trim().equals("")){
            textApellidos.requestFocus();
            return "Apellidos no puede ser vacio";
        }
        if(textNombrePadre.getText().trim().equals("")){
            textNombrePadre.requestFocus();
            return "Nombre no puede ser vacio";
        }
        if(textApellidosPadre.getText().trim().equals("")){
            textApellidosPadre.requestFocus();
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
        if(textIBAN.getText().trim().matches("[a-zA-Z]{2}\\d{22}")){
            textIBAN.requestFocus();
            return "Cuenta bancaria no válida";
        }
        if(!textAporte.getText().trim().matches("[0-9]+\\.?[0-9]*")){
            textAporte.requestFocus();
            return "El aporte tiene que ser numérico";
        }
        if(!textAdeudo.getText().trim().matches("[0-9]+\\.?[0-9]*")){
            textAdeudo.requestFocus();
            return "El aporte tiene que ser numérico";
        }
        if(DateFormat.anyos(DateFormat.toDate(dateNacimiento.getValue()))>=18 && DateFormat.anyos(DateFormat.toDate(dateNacimiento.getValue()))<4){
            dateNacimiento.requestFocus();
            return "Los alumnos tienen que tener entre 4 y 18 años de edad";
        }
        return "OK";
    }
}
