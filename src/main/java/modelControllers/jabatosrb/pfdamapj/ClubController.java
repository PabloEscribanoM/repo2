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

public class ClubController {
    public static ArrayList<Club> getFiltered(String filtro) throws SQLException, UnsupportedEncodingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        ArrayList<Club> clubLista = new ArrayList<Club>();
        String sql = "SELECT * FROM Club WHERE Club_nombre LIKE CONCAT('%',?,'%')";

        PreparedStatement ps = Conexion.getConnection().prepareStatement(sql);
        ps.setString(1, filtro);

        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            clubLista.add(new Club(
                    rs.getInt("Club_id"),
                    rs.getString("Club_nombre"),
                    rs.getString("Club_telefono"),
                    rs.getString("Club_identidadFiscal"),
                    rs.getDouble("Club_gastos"),
                    rs.getDouble("Club_ingresos"),
                    rs.getDouble("Club_resultadoTotal")
            ));
        }

        rs.close();
        ps.close();

        return clubLista;

    }

    public static void updateClub(Club club) throws SQLException, ParseException {
        String sql = "UPDATE Club SET " +
                "Club_nombre = ?, Club_telefono = ?, " +
                "Club_identidadFiscal = ?, Club_gastos = ?,  Club_ingresos = ? , Club_resultadoTotal = ? " +
                "WHERE Club_id = ?";

        PreparedStatement ps = Conexion.getConnection().prepareStatement(sql);

        ps.setString(1, club.getClubNombre());
        ps.setString(2, club.getClubTelefono());
        ps.setString(3, club.getClubIdFiscal());
        ps.setDouble(4, club.getClubGastos());
        ps.setDouble(5, club.getClubIngresos());
        ps.setDouble(6, club.getClubResTotal());
        ps.setInt(7, club.getClubId());

        ps.executeUpdate();

        ps.close();
    }
    public static void deleteClub(Club club) throws SQLException, ParseException {
        String sql = "DELETE FROM Club WHERE Club_id = ?";

        PreparedStatement ps = Conexion.getConnection().prepareStatement(sql);

        ps.setInt(1, club.getClubId());

        ps.executeUpdate();

        ps.close();
    }
    public static void addClub(Club club) throws SQLException, ParseException, UnsupportedEncodingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        String sql = "INSERT INTO Club VAlUES (NULL, ?, ?, ?, ?, ?, ?)";

        PreparedStatement ps = Conexion.getConnection().prepareStatement(sql);

        ps.setString(1, club.getClubNombre());
        ps.setString(2, club.getClubTelefono());
        ps.setString(3, club.getClubIdFiscal());
        ps.setDouble(4, club.getClubGastos());
        ps.setDouble(5, club.getClubIngresos());
        ps.setDouble(6, club.getClubResTotal());


        ps.executeUpdate();

        ps.close();
    }
}
