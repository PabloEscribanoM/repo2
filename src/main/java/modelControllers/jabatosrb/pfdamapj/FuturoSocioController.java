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

    public static ArrayList<FuturoSocio> getFiltered(String filtro) throws SQLException, UnsupportedEncodingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        ArrayList<FuturoSocio> listaFuturosSocios = new ArrayList<FuturoSocio>();
        String sql = "SELECT * FROM Futuro_socio WHERE futuro_socio_nombre LIKE CONCAT('%',?,'%') OR futuro_socio_apellidos LIKE CONCAT('%',?,'%' AND WHERE Futuro_socio_fechaBaja IS NULL) ";

        PreparedStatement ps = Conexion.getConnection().prepareStatement(sql);
        ps.setString(1, filtro);
        ps.setString(2, filtro);

        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            Date FechaAlta = null;
            Date FechaBaja = null;
            Date FechaNacimiento = null;

            if(rs.getDate("Futuro_socio_fechaAlta")!=null)
                FechaNacimiento = new Date(rs.getDate("Futuro_socio_fechaAlta").getTime());
            if(rs.getDate("Futuro_socio_fechaBaja")!=null)
                FechaAlta = new Date(rs.getDate("Futuro_socio_fechaBaja").getTime());
            if(rs.getDate("Futuro_socio_fechaNacimiento")!=null)
                FechaNacimiento = new Date(rs.getDate("Futuro_socio_fechaNacimiento").getTime());

            listaFuturosSocios.add(new FuturoSocio(
                    rs.getInt("futuro_socio_id"),
                    rs.getString("futuro_socio_nombre"),
                    rs.getString("futuro_socio_apellidos"),
                    rs.getString("futuro_socio_telefono"),
                    rs.getString("Futuro_socio_descripcion"),
                    FechaAlta,
                    FechaBaja,
                    FechaNacimiento,
                    rs.getString("Futuro_socio_cuentaBancaria"),
                    rs.getInt("Futuro_socio_id_club")

            ));
        }

        rs.close();
        ps.close();

        return listaFuturosSocios;

    }
    public static void updateFuturoSocio(FuturoSocio futuroSocio) throws SQLException, ParseException {
        String sql = "UPDATE Futuro_socio SET " +
                "futuro_socio_nombre = ?, futuro_socio_apellidos = ?, " +
                "futuro_socio_telefono = ?, Futuro_socio_descripcion = ?,  " +
                "Futuro_socio_fechaNacimiento = ?, Futuro_socio_cuentaBancaria = ? " +
                "WHERE futuro_socio_id = ?";

        PreparedStatement ps = Conexion.getConnection().prepareStatement(sql);

        ps.setString(1, futuroSocio.getNombre());
        ps.setString(2, futuroSocio.getApellidos());
        ps.setString(3, futuroSocio.getTelefono());
        ps.setString(4, futuroSocio.getDescripcion());
        ps.setDate(5, new java.sql.Date(futuroSocio.getFechaNacimientoaDate().getTime()));
        ps.setString(6, futuroSocio.getCuentaBancaria());
        ps.setInt(7, futuroSocio.getId());

        ps.executeUpdate();

        ps.close();
    }
    public static void deleteFuturoSocio(FuturoSocio futuroSocio) throws SQLException {
        String sql = "UPDATE Futuro_socio SET Futuro_socio_fechaBaja = ? WHERE futuro_socio_id = ?";

        PreparedStatement ps = Conexion.getConnection().prepareStatement(sql);
        ps.setDate(1, new java.sql.Date(new Date().getTime()));
        ps.setInt(2, futuroSocio.getId());

        ps.executeUpdate();

        ps.close();
    }
    public static void addFuturoSocio(FuturoSocio futuroSocio) throws SQLException, ParseException, UnsupportedEncodingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        String sql = "INSERT INTO Escuela VAlUES (NULL, ?, ?, ?, ?, ?, NULL, ? , ?, NULL )";

        PreparedStatement ps = Conexion.getConnection().prepareStatement(sql);

        ps.setString(1, futuroSocio.getNombre());
        ps.setString(2, futuroSocio.getApellidos());
        ps.setString(3, futuroSocio.getTelefono());
        ps.setString(4, futuroSocio.getDescripcion());
        ps.setDate(5, new java.sql.Date(new Date().getTime()));
        ps.setDate(6, new java.sql.Date(futuroSocio.getFechaNacimientoaDate().getTime()));
        ps.setString(7, futuroSocio.getCuentaBancaria());

        ps.executeUpdate();

        ps.close();
    }
}
