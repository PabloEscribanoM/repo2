package jabatosrb.pfdamapj;

import jabatosrb.pfdampj.PersistentData;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PrincipalController implements Initializable {

    // Socios
    @FXML
    private TextField sociosText;
    @FXML
    private TableView socioTabla;
    @FXML
    private TableColumn socioNombre, socioApellidos, socioMote, socioFehaAlta, socioEmail, socioTel, socioPagado, socioAdeudo, socioIBAN;
    private ArrayList<Socio> socios;
    // Jugadores
    @FXML
    private TextField jugadoresText;
    @FXML
    private TableView jugadorTabla;
    @FXML
    private TableColumn jugadorNombre, jugadorApellidos,jugadorMote, jugadorSeccion, jugadorAporte, jugadorAdeudo;
    private ArrayList<SocioJugador> jugadores;
    // Escuela
    @FXML
    private TextField escuelaText;
    @FXML
    private TableView escuelaTabla;
    @FXML
    private TableColumn escuelaNombre, escuelaApellidos, escuelaNombrePadre, escuelaApellidosPadre, escuelaEmail,
            escuelaTelefono, escuelaNacimiento, escuelaEdad, escuelaAporte, escuelaAdeudo, escuelaFechaAlta, escuelaIBAN;
    private ArrayList<Escuela> listaEscuela;
    // administradores
    @FXML
    public TableView adminTabla;
    @FXML
    private TextField adminText;
    @FXML
    public TableColumn adminNombre,adminApellidos,adminEmail,adminTelefono,adminArea,adminDni,adminSalario,adminFechaAlta,adminIBAN;
    private ArrayList<Administrador> listaAdministrador;
    // entrenadores
    @FXML
    private TextField entrenadoresText;
    private ArrayList<Entrenadores> listaEntrenadores;
    @FXML
    public TableView entrenadoresTabla;
    @FXML
    public TableColumn entrenadoresNombre,entrenadoresApellidos,entrenadoresEmail,entrenadoresTelefono,entrenadoresCategoria,entrenadoresDNI,entrenadoresSalario,entrenadoresFechaAlta,entrenadoresIBAN;
    //material
    @FXML
    private TextField materialText;
    private ArrayList<Materiales> listaMaterial;
    @FXML
    private TableView materialTabla;
    @FXML
    private TableColumn materialNombre,materialStock,materialBeneficiario;
    //patrocinador
    @FXML
    private TableView patroTabla;
    @FXML
    private TableColumn patroNombre, patroAporte, patroIBAN;
    @FXML
    private TextField patroText;
    private ArrayList<Patrocinador> patrocinadores;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // tabla socios
        socioNombre.setCellValueFactory(new PropertyValueFactory<>("socNombre"));
        socioApellidos.setCellValueFactory(new PropertyValueFactory<>("socApellido"));
        socioMote.setCellValueFactory(new PropertyValueFactory<>("socMote"));
        socioFehaAlta.setCellValueFactory(new PropertyValueFactory<>("socFechaAlta"));
        socioEmail.setCellValueFactory(new PropertyValueFactory<>("socEmail"));
        socioTel.setCellValueFactory(new PropertyValueFactory<>("socNumTel"));
        socioPagado.setCellValueFactory(new PropertyValueFactory<>("socAporte"));
        socioAdeudo.setCellValueFactory(new PropertyValueFactory<>("socAdeudo"));
        socioIBAN.setCellValueFactory(new PropertyValueFactory<>("socCuentaBancaria"));
        actualizarSocios();
        // tabla jugadores
        jugadorNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        jugadorApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        jugadorMote.setCellValueFactory(new PropertyValueFactory<>("mote"));
        jugadorSeccion.setCellValueFactory(new PropertyValueFactory<>("seccion"));
        jugadorAporte.setCellValueFactory(new PropertyValueFactory<>("aporteTotal"));
        jugadorAdeudo.setCellValueFactory(new PropertyValueFactory<>("adeudoTotal"));
        actualizarJugadores();
        // tabla escuela
        escuelaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        escuelaApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        escuelaNombrePadre.setCellValueFactory(new PropertyValueFactory<>("tutorNombre"));
        escuelaApellidosPadre.setCellValueFactory(new PropertyValueFactory<>("tutorApellidos"));
        escuelaEmail.setCellValueFactory(new PropertyValueFactory<>("tutorEmail"));
        escuelaTelefono.setCellValueFactory(new PropertyValueFactory<>("numTelefono"));
        escuelaNacimiento.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));
        escuelaEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));
        escuelaAporte.setCellValueFactory(new PropertyValueFactory<>("aporte"));
        escuelaAdeudo.setCellValueFactory(new PropertyValueFactory<>("adeudo"));
        escuelaFechaAlta.setCellValueFactory(new PropertyValueFactory<>("fechaAlta"));
        escuelaIBAN.setCellValueFactory(new PropertyValueFactory<>("cuentaBancaria"));
        actualizarEscuela();
        // tabla administracion
        adminNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        adminApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        adminEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        adminTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        adminArea.setCellValueFactory(new PropertyValueFactory<>("area"));
        adminDni.setCellValueFactory(new PropertyValueFactory<>("dni"));
        adminSalario.setCellValueFactory(new PropertyValueFactory<>("salario"));
        adminFechaAlta.setCellValueFactory(new PropertyValueFactory<>("fechaAlta"));
        adminIBAN.setCellValueFactory(new PropertyValueFactory<>("iban"));
        actualizarAdministracion();
        // tabla entrenadores
        entrenadoresNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        entrenadoresApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        entrenadoresEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        entrenadoresTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        entrenadoresCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        entrenadoresDNI.setCellValueFactory(new PropertyValueFactory<>("dni"));
        entrenadoresSalario.setCellValueFactory(new PropertyValueFactory<>("salario"));
        entrenadoresFechaAlta.setCellValueFactory(new PropertyValueFactory<>("fechaAlta"));
        entrenadoresIBAN.setCellValueFactory(new PropertyValueFactory<>("iban"));
        actualizarEntrenadores();
        // tabla materiales
        materialNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        materialStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        materialBeneficiario.setCellValueFactory(new PropertyValueFactory<>("beneficiario"));
        actualizarMateriales();
        // tabla patrocinador
        patroNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        patroAporte.setCellValueFactory(new PropertyValueFactory<>("aporte"));
        patroIBAN.setCellValueFactory(new PropertyValueFactory<>("cuentaBancaria"));
        actualizarPatrocinadores();

    }



    // socios
    public void SociosBuscar(ActionEvent actionEvent) {
        actualizarSocios();
    }

    public void sociosAniadir(ActionEvent actionEvent) throws IOException {
        ventanaModal(actionEvent, PrincipalController.class.getResource("fxml/socio_view.fxml"), "Nuevo socio");
        PersistentData.setSocioMod(null);
        actualizarSocios();
    }

    public void socioModificar(MouseEvent mouseEvent) throws IOException {
        PersistentData.setSocioMod((Socio)socioTabla.getSelectionModel().getSelectedItem());
        //abrir ventana modal
        ventanaModal(mouseEvent, PrincipalController.class.getResource("fxml/socio_view.fxml"), "Modificar socio - id: " + PersistentData.getSocioMod().getSocId());
        PersistentData.setSocioMod(null);
        actualizarSocios();
    }
    // jugadores
    public void jugadoresBuscar(ActionEvent actionEvent) {
        actualizarJugadores();
    }

    public void jugadoresAniadir(ActionEvent actionEvent) throws IOException {
        ventanaModal(actionEvent, PrincipalController.class.getResource("fxml/jugador_view.fxml"), "Nuevo jugador");
        PersistentData.setSocioJugadorMod(null);
        actualizarJugadores();
    }

    public void jugadoresModificar(MouseEvent mouseEvent) throws IOException {
        PersistentData.setSocioJugadorMod((SocioJugador) jugadorTabla.getSelectionModel().getSelectedItem());
        ventanaModal(mouseEvent, PrincipalController.class.getResource("fxml/jugador_view.fxml"), "Jugador id: " + PersistentData.getSocioJugadorMod().getJugador().getNumFicha());
        PersistentData.setSocioJugadorMod(null);
        actualizarJugadores();
    }
    // escuela
    public void escuelaBuscar(ActionEvent actionEvent) {
        actualizarEscuela();
    }

    public void escuelaAniadir(ActionEvent actionEvent) throws IOException {
        ventanaModal(actionEvent, PrincipalController.class.getResource("fxml/escuela_view.fxml"), "Nuevo niño");
        PersistentData.setEscuelaMod(null);
        actualizarEscuela();
    }

    public void escuelaModificar(MouseEvent mouseEvent) throws IOException {
        PersistentData.setEscuelaMod((Escuela) escuelaTabla.getSelectionModel().getSelectedItem());
        ventanaModal(mouseEvent, PrincipalController.class.getResource("fxml/escuela_view.fxml"), "Escuela id: " + PersistentData.getEscuelaMod().getId());
        PersistentData.setEscuelaMod(null);
        actualizarEscuela();
    }


    public void adminBuscar(ActionEvent actionEvent) {actualizarAdministracion();}

    public void adminAniadir(ActionEvent actionEvent) throws IOException {
        ventanaModal(actionEvent, PrincipalController.class.getResource("fxml/admin_view.fxml"), "Nuevo administrador");
        PersistentData.setAdminMod(null);
        actualizarAdministracion();
    }
    public void adminModificar(MouseEvent mouseEvent) throws IOException {
        PersistentData.setAdminMod((Administrador) adminTabla.getSelectionModel().getSelectedItem());
        ventanaModal(mouseEvent, PrincipalController.class.getResource("fxml/admin_view.fxml"), "Admin id: " + PersistentData.getAdminMod().getId());
        PersistentData.setAdminMod(null);
        actualizarAdministracion();
    }
    public void entrenadoresBuscar(ActionEvent actionEvent) {actualizarEntrenadores();}

    public void entrenadoresAniadir(ActionEvent actionEvent) throws IOException {
        ventanaModal(actionEvent, PrincipalController.class.getResource("fxml/entrenador_view.fxml"), "Nuevo entrenador");
        PersistentData.setEntrenadorMod(null);
        actualizarEntrenadores();
    }
    public void entrenadoresModificar(MouseEvent mouseEvent) throws IOException {
        PersistentData.setEntrenadorMod((Entrenadores) entrenadoresTabla.getSelectionModel().getSelectedItem());
        ventanaModal(mouseEvent, PrincipalController.class.getResource("fxml/entrenador_view.fxml"), "Entrenador id: " + PersistentData.getEntrenadorMod().getId());
        PersistentData.setEntrenadorMod(null);
        actualizarEntrenadores();
    }
    public void materialBuscar(ActionEvent actionEvent) {actualizarMateriales();}
    public void materialAniadir(ActionEvent actionEvent) throws IOException {
        ventanaModal(actionEvent, PrincipalController.class.getResource("fxml/material_view.fxml"), "Nuevo material");
        PersistentData.setMaterialesMod(null);
        actualizarMateriales();
    }
    public void materialModificar(MouseEvent mouseEvent) throws IOException {
        PersistentData.setMaterialesMod((Materiales) materialTabla.getSelectionModel().getSelectedItem());
        ventanaModal(mouseEvent, PrincipalController.class.getResource("fxml/material_view.fxml"), "Material id: " + PersistentData.getMaterialesMod().getMatId());
        PersistentData.setMaterialesMod(null);
        actualizarMateriales();
    }

    public void patroBuscar(ActionEvent actionEvent) {
        actualizarPatrocinadores();
    }

    public void patroAniadir(ActionEvent actionEvent) throws IOException {
        ventanaModal(actionEvent, PrincipalController.class.getResource("fxml/patrocinador_view.fxml"), "Nuevo patrocinador");
        PersistentData.setPatrocinadorMod(null);
        actualizarPatrocinadores();
    }

    public void patroModificar(MouseEvent mouseEvent) throws IOException {
        PersistentData.setPatrocinadorMod((Patrocinador) patroTabla.getSelectionModel().getSelectedItem());
        ventanaModal(mouseEvent, PrincipalController.class.getResource("fxml/patrocinador_view.fxml"), "Patrocinador id: " + PersistentData.getPatrocinadorMod().getId());
        PersistentData.setPatrocinadorMod(null);
        actualizarMateriales();
    }

    public void cerrarSesion(ActionEvent actionEvent) throws IOException {
        PersistentData.setUser(null);
        Parent root = FXMLLoader.load(PrincipalController.class.getResource("fxml/logIn_view.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void ventanaModal(Event event, URL url, String title) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(url);
        stage.setScene(new Scene(root));
        stage.setTitle(title);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)event.getSource()).getScene().getWindow());
        stage.showAndWait();
    }

    private void actualizarSocios() {
        try {
            socios = SociosController.getFiltered(sociosText.getText().trim());
        } catch (SQLException | UnsupportedEncodingException | NoSuchPaddingException | IllegalBlockSizeException | NoSuchAlgorithmException | BadPaddingException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
        socioTabla.setItems(FXCollections.observableArrayList(socios));
    }

    private void actualizarJugadores() {
        try{
            jugadores = SocioJugadorController.getFiltered(jugadoresText.getText().trim());
        } catch (SQLException | UnsupportedEncodingException | NoSuchPaddingException | IllegalBlockSizeException | NoSuchAlgorithmException | BadPaddingException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
        jugadorTabla.setItems(FXCollections.observableArrayList(jugadores));
    }

    private void actualizarEscuela(){
        try{
            listaEscuela = EscuelaController.getFiltered(escuelaText.getText().trim());
        } catch (SQLException | UnsupportedEncodingException | NoSuchPaddingException | IllegalBlockSizeException | NoSuchAlgorithmException | BadPaddingException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
        escuelaTabla.setItems(FXCollections.observableArrayList(listaEscuela));
    }
    private void actualizarAdministracion() {
        try{
            listaAdministrador = AdministradorController.getFiltered(adminText.getText().trim());
        } catch (SQLException | UnsupportedEncodingException | NoSuchPaddingException | IllegalBlockSizeException | NoSuchAlgorithmException | BadPaddingException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
        adminTabla.setItems(FXCollections.observableArrayList(listaAdministrador));
    }

    private void actualizarMateriales() {
        try{
            listaMaterial = MaterialesController.getFiltered(materialText.getText().trim());
        } catch (SQLException | UnsupportedEncodingException | NoSuchPaddingException | IllegalBlockSizeException | NoSuchAlgorithmException | BadPaddingException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
        materialTabla.setItems(FXCollections.observableArrayList(listaMaterial));
    }

    private void actualizarEntrenadores() {
        try{
            listaEntrenadores = EntrenadoresController.getFiltered(entrenadoresText.getText().trim());
        } catch (SQLException | UnsupportedEncodingException | NoSuchPaddingException | IllegalBlockSizeException | NoSuchAlgorithmException | BadPaddingException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
        entrenadoresTabla.setItems(FXCollections.observableArrayList(listaEntrenadores));
    }

    private void actualizarPatrocinadores(){
        try{
            patrocinadores = PatrocinadorController.getFiltered(patroText.getText().trim());
        } catch (SQLException | UnsupportedEncodingException | NoSuchPaddingException | IllegalBlockSizeException | NoSuchAlgorithmException | BadPaddingException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
        patroTabla.setItems(FXCollections.observableArrayList(patrocinadores));
    }
}
