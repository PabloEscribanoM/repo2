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

public class EntrenadoresController {
    public static ArrayList<Entrenadores> getFiltered(String filtro) throws SQLException, UnsupportedEncodingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        ArrayList<Entrenadores> entrenadoresLista = new ArrayList<Entrenadores>();
        String sql = "SELECT * FROM Entrenadores WHERE Entrenadores_nombre LIKE CONCAT('%',?,'%') OR Entrenadores_apellidos LIKE CONCAT('%',?,'%') AND Entrenadores_fechaBaja IS NULL";

        PreparedStatement ps = Conexion.getConnection().prepareStatement(sql);
        ps.setString(1, filtro);
        ps.setString(2, filtro);

        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            Date FechaAlta = null;
            Date FechaBaja = null;
            Date FechaNacimiento = null;

            if(rs.getDate("Entrenadores_fechaAlta")!=null)
                FechaNacimiento = new Date(rs.getDate("Entrenadores_fechaAlta").getTime());
            if(rs.getDate("Entrenadores_fechaBaja")!=null)
                FechaAlta = new Date(rs.getDate("Entrenadores_fechaBaja").getTime());
            if(rs.getDate("Entrenadores_fechaNacimiento")!=null)
                FechaAlta = new Date(rs.getDate("Entrenadores_fechaNacimiento").getTime());

            entrenadoresLista.add(new Entrenadores(
                    rs.getInt("Entrenadores_id"),
                    rs.getString("Entrenadores_nombre"),
                    rs.getString("Entrenadores_apellidos"),
                    rs.getString("Entrenadores_telefono"),
                    rs.getDouble("Entrenadores_salario"),
                    FechaAlta,
                    FechaBaja,
                    FechaNacimiento,
                    rs.getString("Entrenadores_categoria"),
                    rs.getString("Entrenadores_Dni"),
                    rs.getInt("Entrenadores_id_Club"),
                    rs.getString("Entrenadores_cuentaBancaria"),
                    rs.getString("Entrenadores_email")
            ));
        }

        rs.close();
        ps.close();

        return entrenadoresLista;

    }
    public static void updateEntrenadores(Entrenadores entrenadores) throws SQLException, ParseException {
        String sql = "UPDATE Entrenadores SET " +
                "Entrenadores_nombre = ?, Entrenadores_apellidos = ?, " +
                "Entrenadores_telefono = ?, Entrenadores_salario = ?, " +
                " Entrenadores_fechaNacimiento = ?, Entrenadores_categoria = ?," +
                " Entrenadores_Dni = ? , Entrenadores_cuentaBancaria = ? " +
                "WHERE Entrenadores_id = ?";

        PreparedStatement ps = Conexion.getConnection().prepareStatement(sql);

        ps.setString(1, entrenadores.getNombre());
        ps.setString(2, entrenadores.getApellidos());
        ps.setString(3, entrenadores.getTelefono());
        ps.setDouble(4, entrenadores.getSalario());
        ps.setDate(5, new java.sql.Date(entrenadores.getFechaNacimientoDate().getTime()));
        ps.setString(6, entrenadores.getCategoria());
        ps.setString(7, entrenadores.getDni());
        ps.setString(8, entrenadores.getCuentaBancaria());
        ps.setInt(9, entrenadores.getId());

        ps.executeUpdate();

        ps.close();
    }
    public static void deleteEntrenadores(Entrenadores entrenadores) throws SQLException, ParseException {
        String sql = "UPDATE Entrenadores SET Entrenadores_fechaBaja = ? WHERE Entrenadores_id = ?";

        PreparedStatement ps = Conexion.getConnection().prepareStatement(sql);
        ps.setDate(1, new java.sql.Date(new Date().getTime()));
        ps.setInt(2, entrenadores.getId());

        ps.executeUpdate();

        ps.close();
    }
    public static void addEntrenadores(Entrenadores entrenadores) throws SQLException, ParseException, UnsupportedEncodingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        String sql = "INSERT INTO Entrenadores VAlUES (NULL, ?, ?, ?, ?, ?, NULL , ?, ?, ?, 1, ?)";

        PreparedStatement ps = Conexion.getConnection().prepareStatement(sql);

        ps.setString(1, entrenadores.getNombre());
        ps.setString(2, entrenadores.getApellidos());
        ps.setString(3, entrenadores.getTelefono());
        //email
        ps.setDouble(4, entrenadores.getSalario());
        ps.setDate(5, new java.sql.Date(entrenadores.getFechaNacimientoDate().getTime()));
        ps.setDate(6, new java.sql.Date(new Date().getTime()));
        ps.setDate(7, new java.sql.Date(entrenadores.getFechaBajaDate().getTime()));
        ps.setString(8, entrenadores.getCategoria());
        ps.setString(9, entrenadores.getDni());
        ps.setString(10, entrenadores.getCuentaBancaria());


        ps.executeUpdate();

        ps.close();
    }
}
