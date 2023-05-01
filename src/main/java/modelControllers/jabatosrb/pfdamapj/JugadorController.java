package jabatosrb.pfdamapj;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;

public class JugadorController {
    //  update delete add
    public static void updateJugadores(Jugador jugador) throws SQLException, ParseException {
        String sql = "UPDATE Jugadores SET " +
                "Jug_temporada = ?, Jug_numFicha = ?, Jug_Seccion = ?, " +
                "Jug_aporte = ?, Jug_federadoPrevio = ?, Jug_adeudo = ? " +
                "WHERE Jug_id = ?";

        PreparedStatement ps = Conexion.getConnection().prepareStatement(sql);

        ps.setString(1, jugador.getTemporada());
        ps.setString(2, jugador.getNumFicha());
        ps.setString(3, jugador.getSeccion());
        ps.setDouble(4, jugador.getAporte());
        ps.setBoolean(5, jugador.getFederadoPrevio());
        ps.setDouble(6, jugador.getAdeudo());
        ps.setInt(7, jugador.getId());

        ps.executeUpdate();

        ps.close();
    }

    public static void deleteJugadores(Jugador jugador) throws SQLException {
        String sql = "DELETE FROM Jugadores WHERE Jug_id = ?";

        PreparedStatement ps = Conexion.getConnection().prepareStatement(sql);

        ps.setInt(1, jugador.getId());

        ps.executeUpdate();

        ps.close();
    }

    public static void addJugador(Jugador jugador) throws SQLException, ParseException, UnsupportedEncodingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        String sql = "INSERT INTO Jugadores VAlUES (NULL, ?, ?, ?, ?, ?, NULL, NULL, ?)";

        PreparedStatement ps = Conexion.getConnection().prepareStatement(sql);

        ps.setString(1, jugador.getTemporada());
        ps.setString(2, jugador.getNumFicha());
        ps.setString(3, jugador.getSeccion());
        ps.setDouble(4, jugador.getAporte());
        ps.setBoolean(5, jugador.getFederadoPrevio());
        ps.setInt(6, jugador.getIdSocio());
        ps.setDouble(7, jugador.getAdeudo());

        ps.executeUpdate();

        ps.close();
    }
}
