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

public class EscuelaController {
    public static ArrayList<Escuela> getFiltered(String filtro) throws SQLException, UnsupportedEncodingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        ArrayList<Escuela> listaEscuela = new ArrayList<Escuela>();
        String sql = "SELECT * FROM Escuela WHERE Esc_nombre LIKE CONCAT('%',?,'%') OR Esc_Apellidos LIKE CONCAT('%',?,'%') OR Esc_Tutor_nombre LIKE CONCAT('%',?,'%') OR Esc_Tutor_apellidos LIKE CONCAT('%',?,'%') ";

        PreparedStatement ps = Conexion.getConnection().prepareStatement(sql);
        ps.setString(1, filtro);
        ps.setString(2, filtro);
        ps.setString(3, filtro);
        ps.setString(4, filtro);

        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            Date FechaNacimiento = null;
            Date FechaAlta = null;

            if(rs.getDate("Esc_FechaNacimiento")!=null)
                FechaNacimiento = new Date(rs.getDate("Esc_FechaNacimiento").getTime());
            if(rs.getDate("Esc_FechaAlta")!=null)
                FechaAlta = new Date(rs.getDate("Esc_FechaAlta").getTime());

            listaEscuela.add(new Escuela(
                    rs.getInt("Esc_id"),
                    rs.getString("Esc_nombre"),
                    rs.getString("Esc_Apellidos"),
                    rs.getString("Esc_Genero"),
                    rs.getString("Esc_numeroFicha"),
                    rs.getString("Esc_Tutor_email"),
                    rs.getString("Esc_Tutor_nombre"),
                    rs.getString("Esc_Tutor_apellidos"),
                    rs.getString("Esc_Tutor_numTelefono"),
                    FechaNacimiento,
                    FechaAlta,
                    rs.getDouble("Esc_aporte"),
                    rs.getInt("Esc_id_Club"),
                    rs.getString("Esc_cuentaBancaria"),
                    rs.getDouble("Esc_adeudo")
            ));
        }

        rs.close();
        ps.close();

        return listaEscuela;

    }
    public static void updateEscuela(Escuela escuela) throws SQLException, ParseException {
        String sql = "UPDATE Escuela SET " +
                "Esc_nombre = ?, Esc_Apellidos = ?, " +
                "Esc_Genero = ?, Esc_numeroFicha = ?, Esc_Tutor_email = ?, " +
                "Esc_Tutor_nombre = ?, Esc_Tutor_apellidos = ?, Esc_Tutor_numTelefono = ?, " +
                "Esc_FechaNacimiento = ?, Esc_aporte = ?, " +
                "Esc_cuentaBancaria = ?, Esc_adeudo = ? " +
                "WHERE Esc_id = ?";

        PreparedStatement ps = Conexion.getConnection().prepareStatement(sql);

        ps.setString(1, escuela.getNombre());
        ps.setString(2, escuela.getApellidos());
        ps.setString(3, escuela.getGenero());
        ps.setString(4, escuela.getNumFicha());
        ps.setString(5, escuela.getTutorEmail());
        ps.setString(6, escuela.getTutorNombre());
        ps.setString(7, escuela.getTutorApellidos());
        ps.setString(8, escuela.getNumTelefono());
        ps.setDate(9, new java.sql.Date(escuela.getFechaNacimientoaDate().getTime()));
        ps.setDouble(10, escuela.getAporte());
        ps.setString(11, escuela.getCuentaBancaria());
        ps.setDouble(12, escuela.getAdeudo());
        ps.setInt(13, escuela.getId());

        ps.executeUpdate();

        ps.close();
    }
    public static void deleteEscuela(Escuela escuela) throws SQLException {
        String sql = "DELETE FROM Escuela WHERE Esc_id = ?";

        PreparedStatement ps = Conexion.getConnection().prepareStatement(sql);

        ps.setInt(1, escuela.getId());

        ps.executeUpdate();

        ps.close();
    }
    public static void addEscuela(Escuela escuela) throws SQLException, ParseException, UnsupportedEncodingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        String sql = "INSERT INTO Escuela VAlUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NULL, ? , ? )";

        PreparedStatement ps = Conexion.getConnection().prepareStatement(sql);

        ps.setString(1, escuela.getNombre());
        ps.setString(2, escuela.getApellidos());
        ps.setString(3, escuela.getGenero());
        ps.setString(4, escuela.getNumFicha());
        ps.setString(5, escuela.getTutorEmail());
        ps.setString(6, escuela.getTutorNombre());
        ps.setString(7, escuela.getTutorApellidos());
        ps.setString(8, escuela.getNumTelefono());
        ps.setDate(9, new java.sql.Date(escuela.getFechaNacimientoaDate().getTime()));
        ps.setDate(10, new java.sql.Date(new Date().getTime()));
        ps.setDouble(11, escuela.getAporte());
        ps.setString(12, escuela.getCuentaBancaria());
        ps.setDouble(13, escuela.getAdeudo());


        ps.executeUpdate();

        ps.close();
    }
}
