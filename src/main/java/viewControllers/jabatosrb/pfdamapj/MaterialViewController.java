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

public class MaterialViewController extends ViewUtilities implements Initializable {
    @FXML
    private TextField textNombre;
    @FXML
    private TextField textStock;
    @FXML
    private ComboBox comboBenef;
    @FXML
    private TextArea textDesc;
    @FXML
    private Label textErr;
    @FXML
    private Button btnNext_mod;
    @FXML
    private Button btnAdd_del;
    @FXML
    private TextField textPrecio;
    private boolean isMod = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBenef.setItems(FXCollections.observableArrayList("M", "F", "E", "T"));
        if (PersistentData.getMaterialesMod()!=null){
            isMod = true;
            btnNext_mod.setText("Modificar");
            btnAdd_del.setText("Borrar");
            textNombre.setText(PersistentData.getMaterialesMod().getMatNombre());
            textStock.setText(PersistentData.getMaterialesMod().getMatStock() +"");
            textDesc.setText(PersistentData.getMaterialesMod().getMatDescripcion());
            textPrecio.setText(PersistentData.getMaterialesMod().getMatPrecio() + "");
            comboBenef.getSelectionModel().select(PersistentData.getMaterialesMod().getMatBeneficiario());
        }else{
            btnNext_mod.setVisible(false);
            comboBenef.getSelectionModel().selectFirst();
        }
    }

    public void actionNext_mod(ActionEvent actionEvent) throws SQLException, ParseException {
        if(!validarCampos().equals("OK")){
            textErr.setText(validarCampos());//POR HACER
        }else{
            PersistentData.getMaterialesMod().setMatNombre(textNombre.getText().trim());
            PersistentData.getMaterialesMod().setMatStock(Integer.parseInt(textStock.getText().trim()));
            PersistentData.getMaterialesMod().setMatDescripcion(textDesc.getText().trim());
            PersistentData.getMaterialesMod().setMatBeneficiario( (String) comboBenef.getSelectionModel().getSelectedItem());
            PersistentData.getMaterialesMod().setMatPrecio(Double.parseDouble(textPrecio.getText().trim()));

            MaterialesController.updateMateriales(PersistentData.getMaterialesMod());

            cerrarVentana(actionEvent);
        }
    }

    public void actionAdd_del(ActionEvent actionEvent) throws SQLException, ParseException, UnsupportedEncodingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        if (isMod){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setTitle("Confirmación de borrado");
            alert.setContentText("¿Estás seguro de querer borrar a " + PersistentData.getMaterialesMod().getMatNombre() + "?");
            alert.showAndWait();
            if(alert.getResult() == ButtonType.OK){
                MaterialesController.deleteMateriales(PersistentData.getMaterialesMod());
                cerrarVentana(actionEvent);
            }
        }else{
            if(!validarCampos().equals("OK"))
                textErr.setText(validarCampos());
            else{
                PersistentData.setMaterialesMod(new Materiales(0, textNombre.getText().trim(), Integer.parseInt( textStock.getText().trim()), textDesc.getText().trim(),
                        (String)comboBenef.getSelectionModel().getSelectedItem(),1, Double.parseDouble(textPrecio.getText().trim())));
//cambiar precio
                MaterialesController.addMateriales(PersistentData.getMaterialesMod());

                textNombre.setText("");
                textStock.setText("");
                textDesc.setText("");
                textPrecio.setText("");

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
        if(!textStock.getText().trim().matches("[0-9]+")){
            textStock.requestFocus();
            return "El stock tiene que ser numérico";
        }
        if(!textPrecio.getText().trim().matches("[0-9]+\\.?[0-9]*")){
            textPrecio.requestFocus();
            return "El precio tiene que ser numérico";
        }
        if(textDesc.getText().trim().equals("")){
            textDesc.requestFocus();
            return "La descripción no puede ser vacia";
        }
        return "OK";
    }
}
