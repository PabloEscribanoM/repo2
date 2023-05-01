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

public class PatrocinadorController {
    public static ArrayList<Patrocinador> getFiltered(String filtro) throws SQLException, UnsupportedEncodingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        ArrayList<Patrocinador> patrocinadores = new ArrayList<Patrocinador>();
        String sql = "SELECT * FROM Patrocinador WHERE Patrocinador_nombre LIKE CONCAT('%',?,'%') OR Patrocinador_aporte LIKE CONCAT('%',?,'%')";

        PreparedStatement ps = Conexion.getConnection().prepareStatement(sql);
        ps.setString(1, filtro);
        ps.setString(2, filtro);

        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            patrocinadores.add(new Patrocinador(
                    rs.getInt("Patrocinador_id"),
                    rs.getString("Patrocinador_nombre"),
                    rs.getString("Patrocinador_descripcion"),
                    rs.getDouble("Patrocinador_aporte"),
                    rs.getString("Patrocinador_cuentaBancaria"),
                    rs.getInt("Patrocinador_Club_id")
            ));
        }

        rs.close();
        ps.close();

        return patrocinadores;

    }

    public static void updatePatrocinador(Patrocinador patrocinador) throws SQLException, ParseException {
        String sql = "UPDATE Patrocinador SET " +
                "Patrocinador_nombre = ?, Patrocinador_descripcion = ?, " +
                "Patrocinador_aporte = ?, Patrocinador_cuentaBancaria = ? " +
                "WHERE Patrocinador_id = ?";

        PreparedStatement ps = Conexion.getConnection().prepareStatement(sql);

        ps.setString(1, patrocinador.getNombre());
        ps.setString(2, patrocinador.getDescripcion());
        ps.setDouble(3, patrocinador.getAporte());
        ps.setString(4, patrocinador.getCuentaBancaria());
        ps.setInt(5, patrocinador.getId());

        ps.executeUpdate();

        ps.close();
    }
    public static void deletePatrocinador(Patrocinador patrocinador) throws SQLException, ParseException {
        String sql = "DELETE FROM Patrocinador WHERE Patrocinador_id = ?";

        PreparedStatement ps = Conexion.getConnection().prepareStatement(sql);

        ps.setInt(1, patrocinador.getId());

        ps.executeUpdate();

        ps.close();
    }
    public static void addPatrocinador(Patrocinador patrocinador) throws SQLException, ParseException, UnsupportedEncodingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        String sql = "INSERT INTO Patrocinador VAlUES (NULL, ?, ?, ?, ?, NULL)";

        PreparedStatement ps = Conexion.getConnection().prepareStatement(sql);

        ps.setString(1, patrocinador.getNombre());
        ps.setString(2, patrocinador.getDescripcion());
        ps.setDouble(3, patrocinador.getAporte());
        ps.setString(4, patrocinador.getCuentaBancaria());


        ps.executeUpdate();

        ps.close();
    }
}
