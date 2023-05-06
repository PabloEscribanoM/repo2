package jabatosrb.pfdamapj;

import jabatosrb.pfdampj.DateFormat;
import jabatosrb.pfdampj.PassGenerator;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class JugadorViewController extends ViewUtilities implements Initializable {
    @FXML
    private Label textErr;
    @FXML
    private Button btnNext_mod;
    @FXML
    private Button btnAdd_del;
    @FXML
    private TextField textAporte;
    @FXML
    private TextField textAdeudo;
    @FXML
    private ComboBox nombre;
    @FXML
    private TextField fichaTxt;
    @FXML
    private ComboBox comboSeccion;
    @FXML
    private TextField temporadaTxt;
    @FXML
    private CheckBox federadoCheck;
    private boolean isMod=false;

    private ArrayList<Socio> sociosNoJugador;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboSeccion.setItems(FXCollections.observableArrayList("M", "F"));
        if(PersistentData.getSocioJugadorMod()!=null){
            isMod=true;
            btnNext_mod.setText("Modificar");
            btnAdd_del.setText("Borrar");
            nombre.setItems(FXCollections.observableArrayList(PersistentData.getSocioJugadorMod()));
            nombre.getSelectionModel().selectFirst();
            //selecionar el dato
            textAporte.setText(PersistentData.getSocioJugadorMod().getJugador().getAporte() + "");
            textAdeudo.setText(PersistentData.getSocioJugadorMod().getJugador().getAdeudo() + "");
            fichaTxt.setText(PersistentData.getSocioJugadorMod().getJugador().getNumFicha());
            comboSeccion.getSelectionModel().select(PersistentData.getSocioJugadorMod().getJugador().getSeccion());
            temporadaTxt.setText(PersistentData.getSocioJugadorMod().getJugador().getTemporada());
            federadoCheck.setSelected(PersistentData.getSocioJugadorMod().getJugador().getFederadoPrevio());
        }else{
            btnNext_mod.setVisible(false);
            try {
                sociosNoJugador = SocioJugadorController.getSociosNoJugador();
            } catch (SQLException | UnsupportedEncodingException | NoSuchPaddingException | IllegalBlockSizeException | NoSuchAlgorithmException | BadPaddingException | InvalidKeyException e) {
                throw new RuntimeException(e);
            }
            nombre.setItems(FXCollections.observableArrayList(sociosNoJugador));
            if(!sociosNoJugador.isEmpty()){
                nombre.getSelectionModel().selectFirst();
                comboSeccion.getSelectionModel().selectFirst();
            }
        }
    }
    public void actionNext_mod(ActionEvent actionEvent) throws SQLException, ParseException {
        if(!validarCampos().equals("OK")){
            textErr.setText(validarCampos());//no está hecho todavia el metodo
        }else{
            PersistentData.getSocioJugadorMod().getJugador().setAporte(Double.parseDouble(textAporte.getText().trim()));
            PersistentData.getSocioJugadorMod().getJugador().setAdeudo(Double.parseDouble(textAdeudo.getText().trim()));
            PersistentData.getSocioJugadorMod().getJugador().setNumFicha(fichaTxt.getText().trim());
            PersistentData.getSocioJugadorMod().getJugador().setSeccion((String)comboSeccion.getSelectionModel().getSelectedItem());
            PersistentData.getSocioJugadorMod().getJugador().setTemporada(temporadaTxt.getText().trim());
            PersistentData.getSocioJugadorMod().getJugador().setFederadoPrevio(federadoCheck.isSelected());
            JugadorController.updateJugadores(PersistentData.getSocioJugadorMod().getJugador());
            cerrarVentana(actionEvent);
        }
    }

    public void actionAdd_del(ActionEvent actionEvent) throws SQLException, UnsupportedEncodingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, ParseException {
        if (isMod){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setTitle("Confirmación de borrado");
            alert.setContentText("¿Estás seguro de querer borrar a " + PersistentData.getSocioJugadorMod().getMote() + "?");
            alert.showAndWait();
            if(alert.getResult() == ButtonType.OK){
                JugadorController.deleteJugadores(PersistentData.getSocioJugadorMod().getJugador());
                cerrarVentana(actionEvent);
            }
        }else{
            if(!validarCampos().equals("OK"))
                textErr.setText(validarCampos());
            else{
                PersistentData.setSocioJugadorMod(new SocioJugador(new Jugador(0, temporadaTxt.getText().trim(),
                        fichaTxt.getText().trim(), (String) comboSeccion.getSelectionModel().getSelectedItem(), Double.parseDouble(textAporte.getText().trim()),
                        federadoCheck.isSelected(), ((Socio)nombre.getSelectionModel().getSelectedItem()).getSocId(), 1,
                        Double.parseDouble(textAdeudo.getText().trim())), null, null, null, null, 0, 0));

                JugadorController.addJugador(PersistentData.getSocioJugadorMod().getJugador());

                sociosNoJugador = SocioJugadorController.getSociosNoJugador();
                nombre.setItems(FXCollections.observableArrayList(sociosNoJugador));
                if(!sociosNoJugador.isEmpty()){
                    nombre.getSelectionModel().selectFirst();
                }
                fichaTxt.setText("");
                temporadaTxt.setText("");
                federadoCheck.setSelected(false);
                textAporte.setText("");
                textAdeudo.setText("");
                textErr.setText("");
            }
        }
    }

    public void actionCancel(ActionEvent actionEvent) {
        cerrarVentana(actionEvent);
    }

    private String validarCampos() {
        if(fichaTxt.getText().trim().equals("")){
            fichaTxt.requestFocus();
            return "La ficha no puede ser vacía";
        }
        if (!temporadaTxt.getText().trim().matches("\\d{4}-\\d{4}")){
            temporadaTxt.requestFocus();
            return "La temporada no tiene un formato válido (YYYY-YYYY)";
        }
        if(!textAporte.getText().trim().matches("[0-9]+\\.?[0-9]*")){
            textAporte.requestFocus();
            return "El aporte tiene que ser numérico";
        }
        if(!textAdeudo.getText().trim().matches("[0-9]+\\.?[0-9]*")){
            textAdeudo.requestFocus();
            return "El aporte tiene que ser numérico";
        }
        return "OK";
    }

}
