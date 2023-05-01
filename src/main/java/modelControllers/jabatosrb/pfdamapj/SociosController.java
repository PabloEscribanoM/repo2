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
import java.util.HashMap;

public class SociosController {
    public static ArrayList<Socio> getFiltered(String filtro) throws SQLException, UnsupportedEncodingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        ArrayList<Socio> socios = new ArrayList<Socio>();
        String sql = "SELECT * FROM Socio WHERE soc_nombre LIKE CONCAT('%',?,'%') OR soc_apellido LIKE CONCAT('%',?,'%') OR soc_mote LIKE CONCAT('%',?,'%')";

        PreparedStatement ps = Conexion.getConnection().prepareStatement(sql);
        ps.setString(1, filtro);
        ps.setString(2, filtro);
        ps.setString(3, filtro);

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

    public static void updateSocio(Socio socio) throws SQLException, ParseException {
        String sql = "UPDATE socio SET " +
                "Soc_nombre = ?, Soc_apellido = ?, Soc_mote = ?, " +
                "Soc_email = ?, Soc_numTelefono = ?, Soc_cuentaBancaria = ?, " +
                "Soc_aporte = ?, Soc_adeudo = ?, Soc_fechaNacimiento = ? " +
                "WHERE Soc_id = ?";

        PreparedStatement ps = Conexion.getConnection().prepareStatement(sql);

        ps.setString(1, socio.getSocNombre());
        ps.setString(2, socio.getSocApellido());
        ps.setString(3, socio.getSocMote());
        ps.setString(4, socio.getSocEmail());
        ps.setString(5, socio.getSocNumTel());
        ps.setString(6, socio.getSocCuentaBancaria());
        ps.setDouble(7, socio.getSocAporte());
        ps.setDouble(8, socio.getSocAdeudo());
        ps.setDate(9, new java.sql.Date(socio.getSocFechaNacimientoDate().getTime()));
        ps.setInt(10, socio.getSocId());

        ps.executeUpdate();

        ps.close();
    }

    public static void deleteSocio(Socio socio) throws SQLException {
        String sql = "DELETE FROM socio WHERE Soc_id = ?";

        PreparedStatement ps = Conexion.getConnection().prepareStatement(sql);

        ps.setInt(1, socio.getSocId());

        ps.executeUpdate();

        ps.close();
    }

    public static void addSocio(Socio socio) throws SQLException, ParseException, UnsupportedEncodingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        String sql = "INSERT INTO socio VAlUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NULL)";

        PreparedStatement ps = Conexion.getConnection().prepareStatement(sql);

        ps.setString(1, socio.getSocNombre());
        ps.setString(2, socio.getSocApellido());
        ps.setString(3, socio.getSocMote());
        ps.setString(4, socio.getSocCuentaBancaria());
        ps.setString(5, socio.getSocEmail());
        ps.setString(6, Encrypter.encriptar(socio.getSocPwd(), PersistentData.getEncryptKey()));
        ps.setString(7, socio.getSocNumTel());
        ps.setDouble(8, socio.getSocAporte());
        ps.setDouble(9, socio.getSocAdeudo());
        ps.setDate(10, new java.sql.Date(socio.getSocFechaNacimientoDate().getTime()));
        ps.setDate(11, new java.sql.Date(new Date().getTime()));

        ps.executeUpdate();

        ps.close();
    }

    public static void updatePwds(String oldEncryptKey, String newEncryptKey) throws SQLException, UnsupportedEncodingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        HashMap<Integer, String> passwords = new HashMap<Integer, String>();
        String sql = "SELECT Soc_id, Soc_pwd FROM Socio";

        PreparedStatement ps = Conexion.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while(rs.next())
            passwords.put(rs.getInt("Soc_id"), Encrypter.desencriptar(rs.getString("Soc_pwd"), oldEncryptKey));

        rs.close();
        ps.close();

        sql = "UPDATE Socio SET Soc_pwd = ? WHERE Soc_id = ?";

        for (Integer id: passwords.keySet()){
            ps = Conexion.getConnection().prepareStatement(sql);
            ps.setString(1, Encrypter.encriptar(passwords.get(id), newEncryptKey));
            ps.setInt(2, id);
            ps.executeUpdate();
            ps.close();
        }
    }
}
