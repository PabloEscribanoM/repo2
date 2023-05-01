package jabatosrb.pfdamapj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static Connection cnx = null;
    private static String bbddName = "ClubDeportivo";
    private static String user = "root";
    private static String pass = "AlumnoIFP";

    public static void openConnection(){
        try {
            cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + bbddName, user, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {

        if(cnx == null) {
            openConnection();
        }
        return cnx;
    }

    public static void closeConnection() {
        try {
            if(cnx != null && !cnx.isClosed()) {
                cnx.close();
            }else {
                System.err.println("La conexion no existe o ya esta cerrada.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
