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

public class MaterialesController {
    public static ArrayList<Materiales> getFiltered(String filtro) throws SQLException, UnsupportedEncodingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        ArrayList<Materiales> materialesLista = new ArrayList<Materiales>();
        String sql = "SELECT * FROM Materiales WHERE Mat_nombre LIKE CONCAT('%',?,'%') OR Mat_Beneficiario LIKE CONCAT('%',?,'%')";

        PreparedStatement ps = Conexion.getConnection().prepareStatement(sql);
        ps.setString(1, filtro);
        ps.setString(2, filtro);

        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            materialesLista.add(new Materiales(
                    rs.getInt("Mat_id"),
                    rs.getString("Mat_nombre"),
                    rs.getInt("Mat_stock"),
                    rs.getString("Mat_descripcion"),
                    rs.getString("Mat_Beneficiario"),
                    rs.getInt("Mat_id_Club"),
                    rs.getDouble("Mat_precio")
            ));
        }

        rs.close();
        ps.close();

        return materialesLista;

    }

    public static void updateMateriales(Materiales materiales) throws SQLException, ParseException {
        String sql = "UPDATE Materiales SET " +
                "Mat_nombre = ?, Mat_stock = ?, " +
                "Mat_descripcion = ?,  Mat_Beneficiario = ?, Mat_precio = ? " +
                "WHERE Mat_id = ?";

        PreparedStatement ps = Conexion.getConnection().prepareStatement(sql);

        ps.setString(1, materiales.getMatNombre());
        ps.setInt(2, materiales.getMatStock());
        ps.setString(3, materiales.getMatDescripcion());
        ps.setString(4, materiales.getMatBeneficiario());
        ps.setDouble(5, materiales.getMatPrecio());
        ps.setInt(6, materiales.getMatId());

        ps.executeUpdate();

        ps.close();
    }
    public static void deleteMateriales(Materiales materiales) throws SQLException, ParseException {
        String sql = "DELETE FROM Materiales WHERE Mat_id = ?";

        PreparedStatement ps = Conexion.getConnection().prepareStatement(sql);

        ps.setInt(1, materiales.getMatId());

        ps.executeUpdate();

        ps.close();
    }
    public static void addMateriales(Materiales materiales) throws SQLException, ParseException, UnsupportedEncodingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        String sql = "INSERT INTO Materiales VAlUES (NULL, ?, ?, ?, ?, ?, 1)";

        PreparedStatement ps = Conexion.getConnection().prepareStatement(sql);

        ps.setString(1, materiales.getMatNombre());
        ps.setDouble(2, materiales.getMatPrecio());
        ps.setInt(3, materiales.getMatStock());
        ps.setString(4, materiales.getMatDescripcion());
        ps.setString(5, materiales.getMatBeneficiario());


        ps.executeUpdate();

        ps.close();
    }
}
