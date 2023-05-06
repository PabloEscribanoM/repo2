package jabatosrb.pfdamapj;

import jabatosrb.pfdampj.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;

public class Main extends Application {
    @Override
    public void init() throws Exception {
        //conectar a la base de datos
        Conexion.openConnection();
        //Recoger el club
        PersistentData.setClub(ClubController.getClub());

        //comprovar el encriptado de contraseñas
        String actualMonth = DateFormat.getMonth(new Date());

        ArrayList<String> keyData = JSONController.getDatos();

        String encryptKey = "";
        for (String data: keyData)
            encryptKey = encryptKey + data;

        if(!keyData.get(0).equals(actualMonth)){
            String oldEncryptKey = encryptKey;

            keyData.clear();
            keyData.add(actualMonth);
            String key = PassGenerator.generatePass(8, PassGenerator.NUMBERS);
            keyData.add(key);
            encryptKey = actualMonth + key;
            JSONController.saveDatos(keyData);
            System.out.println(keyData);
            //actualizar encriptado de contraseñas en toda la base de datos
            AdministradorController.updatePwds(oldEncryptKey, encryptKey);
            SociosController.updatePwds(oldEncryptKey, encryptKey);
            System.out.println(Encrypter.encriptar("1234", encryptKey));
        }

        PersistentData.setEncryptKey(encryptKey);

        //crear admin master si no existe
        if (AdministradorController.getMaster()==null){
            AdministradorController.createMaster();
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/logIn_view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle(PersistentData.getClub().getClubNombre() + " - Administración");
        stage.setMinWidth(600);
        stage.setMinHeight(400);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        //guardar datos del club
        ClubController.updateClub(PersistentData.getClub());
        //cerrar la base de datos
        Conexion.closeConnection();
    }

    public static void main(String[] args) {
        launch();
    }

}
