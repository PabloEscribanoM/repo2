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
                    rs.getDouble("Club_resultadoTotal"),
                    rs.getDouble("Club_ingresos_previstos")
            ));
        }

        rs.close();
        ps.close();

        return clubLista;

    }

    public static void updateClub(Club club) throws SQLException, ParseException {
        String sql = "UPDATE Club SET " +
                "Club_nombre = ?, Club_telefono = ?, " +
                "Club_identidadFiscal = ?, Club_gastos = ?,  Club_ingresos = ? , Club_resultadoTotal = ?, Club_ingresos_previstos=? " +
                "WHERE Club_id = ?";

        PreparedStatement ps = Conexion.getConnection().prepareStatement(sql);

        ps.setString(1, club.getClubNombre());
        ps.setString(2, club.getClubTelefono());
        ps.setString(3, club.getClubIdFiscal());
        ps.setDouble(4, club.getClubGastos());
        ps.setDouble(5, club.getClubIngresos());
        ps.setDouble(6, club.getClubResTotal());
        ps.setDouble(7, club.getClubIngresosPrevistos());
        ps.setInt(8, club.getClubId());

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
        String sql = "INSERT INTO Club VAlUES (NULL, ?, ?, ?, ?, ?, ?,?)";

        PreparedStatement ps = Conexion.getConnection().prepareStatement(sql);

        ps.setString(1, club.getClubNombre());
        ps.setString(2, club.getClubTelefono());
        ps.setString(3, club.getClubIdFiscal());
        ps.setDouble(4, club.getClubGastos());
        ps.setDouble(5, club.getClubIngresos());
        ps.setDouble(6, club.getClubResTotal());
        ps.setDouble(7, club.getClubIngresosPrevistos());


        ps.executeUpdate();

        ps.close();
    }

    public static double calcularGastos() throws SQLException {
        String sql="SELECT SUM(Mat_precio) FROM Materiales";
        PreparedStatement ps = Conexion.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        double gastos;
        gastos = rs.getRow();
        String sql1="SELECT SUM(Entrenadores_salario) FROM Entrenadores";
        PreparedStatement ps1 = Conexion.getConnection().prepareStatement(sql1);
        ResultSet rs1 = ps.executeQuery();
        double gastos1;
        gastos1 = rs.getRow();
        String sql2="SELECT SUM(Administrador_Salario) FROM Administrador";
        PreparedStatement ps2= Conexion.getConnection().prepareStatement(sql2);
        ResultSet rs2 = ps.executeQuery();
        double gastos2;
        gastos2 = rs.getRow();

        double gastosTotales=gastos1+gastos+gastos2;

    return gastosTotales;

    }

    public static double calcularIngresos() throws SQLException {
        String sql="SELECT SUM(Esc_aporte) FROM Escuela";
        PreparedStatement ps = Conexion.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        double aporteEsc;
        aporteEsc = rs.getRow();
        String sql1="SELECT SUM(Jug_aporte) FROM Jugadores";
        PreparedStatement ps1 = Conexion.getConnection().prepareStatement(sql1);
        ResultSet rs1 = ps.executeQuery();
        double aporteJug;
        aporteJug = rs.getRow();
        String sql2="SELECT SUM(Patrocinador_aporte) FROM Patrocinador";
        PreparedStatement ps2 = Conexion.getConnection().prepareStatement(sql2);
        ResultSet rs2 = ps.executeQuery();
        double aportePatrocinador;
        aportePatrocinador = rs.getRow();
        double aporteReal= aporteJug+aporteEsc+aportePatrocinador;
        return aporteReal;

    }

    public static double calcucularAdeudo() throws SQLException {


        String sql="SELECT SUM(Esc_adeudo) FROM Escuela";
        PreparedStatement ps = Conexion.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        double adeudoEsc= rs.getRow();;
        String sql1="SELECT SUM(Jug_adeudo) FROM Jugadores";
        PreparedStatement ps1 = Conexion.getConnection().prepareStatement(sql1);
        ResultSet rs1 = ps.executeQuery();
        double adeudoJug= rs.getRow();

         double adeudo= adeudoJug+adeudoEsc;

        return  adeudo;

    }

    public static double dineroTotal(double aporte, double adeudo, double gastos){

        double dineroTotal= (adeudo+aporte)-gastos;

        return dineroTotal;
    }
    public static double dineroReal(double aporte, double gastos){

        double dineroReal= aporte-gastos;

        return dineroReal;
    }

}
