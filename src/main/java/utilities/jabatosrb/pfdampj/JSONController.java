package jabatosrb.pfdampj;

import jabatosrb.pfdamapj.Main;
import org.json.JSONObject;

import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class JSONController {

    public static ArrayList<String> getDatos() throws IOException, URISyntaxException {
        BufferedReader br = new BufferedReader(new FileReader(new File(Main.class.getResource("config/EncryptKey.json").toURI())));
        String datos = br.readLine();
        br.close();
        ArrayList<String> array = new ArrayList<String>();
        JSONObject obj = new JSONObject(datos);
        array.add(obj.getString("MONTH"));
        array.add(obj.getString("KEY"));
        //metodo para recoger datos del JSON EncryptKey.json
        return array;
    }

    public static void saveDatos(ArrayList<String> keyData) throws IOException, URISyntaxException {
        JSONObject obj = new JSONObject();
        obj.put("MONTH", keyData.get(0));
        obj.put("KEY", keyData.get(1));

        FileWriter fw = new FileWriter("src\\main\\resources\\jabatosrb\\pfdamapj\\config\\EncryptKey.json");
        fw.write(obj.toString());
        System.out.println(obj.toString());
        fw.flush();
        fw.close();

        //metodo para escribir en el JSON EncryptKey.json
    }

}
