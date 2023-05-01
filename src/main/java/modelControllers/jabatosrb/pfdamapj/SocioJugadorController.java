package jabatosrb.pfdamapj;

import jabatosrb.pfdampj.Encrypter;
import jabatosrb.pfdampj.PersistentData;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class SocioJugadorController {
    // update delete add filter
    // nombre apellido mote socio
    // where Jug_id_Socio = Soc_id

    public static void updateJugadores(Jugador jugador) throws SQLException, ParseException {
        String sql = "UPDATE Jugadores SET " +
                "Jug_temporada = ?, Jug_numFicha = ?, Jug_Seccion = ?, " +
                "Jug_aporte = ?, Jug_federadoPrevio = ?, Jug_adeudo = ? " +
                "WHERE Jug_id_Socio = Soc_id";

        PreparedStatement ps = Conexion.getConnection().prepareStatement(sql);

        ps.setString(1, jugador.getTemporada());
        ps.setString(2, jugador.getNumFicha());
        ps.setString(3, jugador.getSeccion());
        ps.setDouble(4, jugador.getAporte());
        ps.setBoolean(5, jugador.getFederadoPrevio());
        ps.setDouble(6, jugador.getAdeudo());

        ps.executeUpdate();

        ps.close();
    }

    public static void deleteJugadores(Jugador jugador) throws SQLException {
        String sql = "DELETE ON CASCADE FROM Jugadores WHERE Jug_id_Socio = Soc_id";

        PreparedStatement ps = Conexion.getConnection().prepareStatement(sql);

        ps.setInt(1, jugador.getId());

        ps.executeUpdate();

        ps.close();
    }

    public static void addJugador(Jugador jugador) throws SQLException, ParseException, UnsupportedEncodingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        String sql = "INSERT INTO Jugadores VAlUES (NULL, ?, ?, ?, ?, ?, ?, NULL, NULL, ?)";

        PreparedStatement ps = Conexion.getConnection().prepareStatement(sql);

        ps.setString(1, jugador.getTemporada());
        ps.setString(2, jugador.getNumFicha());
        ps.setString(3, jugador.getSeccion());
        ps.setDouble(4, jugador.getAporte());
        ps.setBoolean(5, jugador.getFederadoPrevio());
        ps.setDouble(7, jugador.getAdeudo());

        ps.executeUpdate();

        ps.close();

    }
    public static ArrayList<SocioJugador> getFiltered(String filtro) throws SQLException, UnsupportedEncodingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        ArrayList<SocioJugador> jugadores = new ArrayList<SocioJugador>();
        String sql = "SELECT * FROM Jugadores j,Socio s WHERE Jug_id_Socio = Soc_id AND (soc_nombre LIKE CONCAT('%',?,'%') OR soc_apellido LIKE CONCAT('%',?,'%') OR soc_mote LIKE CONCAT('%',?,'%'))";

        PreparedStatement ps = Conexion.getConnection().prepareStatement(sql);
        ps.setString(1, filtro);
        ps.setString(2, filtro);
        ps.setString(3, filtro);

        ResultSet rs = ps.executeQuery();

        while(rs.next()){


            jugadores.add(new SocioJugador(
                    new Jugador(
                        rs.getInt("Jug_id"),
                        rs.getString("Jug_temporada"),
                        rs.getString("Jug_numFicha"),
                        rs.getString("Jug_Seccion"),
                        rs.getDouble("Jug_aporte"),
                        rs.getBoolean("Jug_federadoPrevio"),
                        rs.getInt("Jug_id_Socio"),
                        rs.getInt("Jug_id_Club"),
                        rs.getDouble("Jug_adeudo")),
                    rs.getString("Soc_nombre"),
                    rs.getString("Soc_apellido"),
                    rs.getString("Soc_mote"),
                    rs.getString("Jug_Seccion"),
                    rs.getDouble("Jug_aporte"),
                    rs.getDouble("Jug_adeudo")
            ));
        }

        rs.close();
        ps.close();

        return jugadores;

    }

    public static ArrayList<Socio> getSociosNoJugador() throws SQLException, UnsupportedEncodingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        ArrayList<Socio> socios = new ArrayList<Socio>();
        String sql = "SELECT s.* FROM socio s LEFT join jugadores j ON j.Jug_id_Socio = s.Soc_id where j.jug_id is null";

        PreparedStatement ps =Conexion.getConnection().prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            Date fechaAlta = null;
            Date fechaNacimiento = null;

            if(rs.getDate("Soc_fechaAlta")!=null)
                fechaAlta = new Date(rs.getDate("Soc_fechaAlta").getTime());
            if(rs.getDate("Soc_fechaNacimiento")!=null)
                fechaNacimiento = new Date(rs.getDate("Soc_fechaNacimiento").getTime());

            socios.add(new Socio(
                    rs.getInt("soc_id"),
                    rs.getString("soc_nombre"),
                    rs.getString("soc_apellido"),
                    rs.getString("soc_mote"),
                    rs.getString("soc_cuentaBancaria"),
                    rs.getString("soc_email"),
                    Encrypter.desencriptar(rs.getString("soc_pwd"), PersistentData.getEncryptKey()),
                    rs.getString("soc_numTelefono"),
                    rs.getDouble("soc_aporte"),
                    rs.getDouble("soc_adeudo"),
                    fechaNacimiento,
                    fechaAlta,
                    rs.getInt("soc_id_Club")
            ));
        }

        rs.close();
        ps.close();

        return socios;
    }
}
