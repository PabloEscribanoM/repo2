package jabatosrb.pfdamapj;

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

public class FuturoSocioController {

    public static FuturoSocio getNext() throws SQLException, UnsupportedEncodingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        FuturoSocio futuroSocio = null;

        String sql = "SELECT * FROM Futuro_socio LIMIT 1";

        PreparedStatement ps = Conexion.getConnection().prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        if(rs.next()){
            Date fechaNacimiento = null;

            if(rs.getDate("Futuro_socio_fechaNacimiento")!=null)
                fechaNacimiento = new Date(rs.getDate("Futuro_socio_fechaNacimiento").getTime());

            futuroSocio = new FuturoSocio(
                    rs.getInt("futuro_socio_id"),
                    rs.getString("futuro_socio_nombre"),
                    rs.getString("futuro_socio_apellidos"),
                    rs.getString("futuro_socio_telefono"),
                    rs.getString("futuro_socio_email"),
                    fechaNacimiento,
                    rs.getString("Futuro_socio_cuentaBancaria"),
                    rs.getInt("Futuro_socio_id_club")

            );
        }

        rs.close();
        ps.close();

        return futuroSocio;

    }
    public static void deleteFuturoSocio(FuturoSocio futuroSocio) throws SQLException {
        String sql = "DELETE FROM Futuro_socio WHERE futuro_socio_id = ?";

        PreparedStatement ps = Conexion.getConnection().prepareStatement(sql);
        ps.setInt(1, futuroSocio.getId());

        ps.executeUpdate();

        ps.close();
    }
    public static void addFuturoSocio(FuturoSocio futuroSocio) throws SQLException, ParseException, UnsupportedEncodingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        String sql = "INSERT INTO Futuro_socio VAlUES (NULL, ?, ?, ?, ?, ?, 1, ?)";

        PreparedStatement ps = Conexion.getConnection().prepareStatement(sql);

        ps.setString(1, futuroSocio.getNombre());
        ps.setString(2, futuroSocio.getApellidos());
        ps.setString(3, futuroSocio.getTelefono());
        ps.setDate(4, new java.sql.Date(futuroSocio.getFechaNacimientoaDate().getTime()));
        ps.setString(5, futuroSocio.getCuentaBancaria());
        ps.setString(6, futuroSocio.getEmail());

        ps.executeUpdate();

        ps.close();
    }
}
