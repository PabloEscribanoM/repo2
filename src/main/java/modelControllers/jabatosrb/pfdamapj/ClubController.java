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
    public static Club getClub() throws SQLException, UnsupportedEncodingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        Club club = null;
        String sql = "SELECT * FROM Club";

        PreparedStatement ps = Conexion.getConnection().prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        if(rs.next()){
            club = new Club(
                    rs.getInt("Club_id"),
                    rs.getString("Club_nombre"),
                    rs.getString("Club_telefono"),
                    rs.getString("Club_identidadFiscal"),
                    rs.getDouble("Club_gastos"),
                    rs.getDouble("Club_ingresos"),
                    rs.getDouble("Club_resultadoTotal"),
                    rs.getDouble("Club_ingresos_previstos")
            );
        }

        rs.close();
        ps.close();

        return club;

    }

    public static void updateClub(Club club) throws SQLException, ParseException {
        String sql = "UPDATE Club SET " +
                "Club_nombre = ?, " +
                "Club_gastos = ?,  Club_ingresos = ? , Club_resultadoTotal = ?, Club_ingresos_previstos=? " +
                "WHERE Club_id = ?";

        PreparedStatement ps = Conexion.getConnection().prepareStatement(sql);

        ps.setString(1, club.getClubNombre());
        ps.setDouble(2, club.getClubGastos());
        ps.setDouble(3, club.getClubIngresos());
        ps.setDouble(4, club.getClubResTotal());
        ps.setDouble(5, club.getClubIngresosPrevistos());
        ps.setInt(6, club.getClubId());

        ps.executeUpdate();

        ps.close();
    }

    public static double calcularGastos() throws SQLException {
        String sql="SELECT SUM(Mat_precio) FROM Materiales";
        PreparedStatement ps = Conexion.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        double gastos = 0;
        if (rs.next())
            gastos = rs.getDouble(1);
        rs.close();
        ps.close();

        sql="SELECT SUM(Entrenadores_salario) FROM Entrenadores";
        ps = Conexion.getConnection().prepareStatement(sql);
        rs = ps.executeQuery();
        double gastos1 = 0;
        if (rs.next())
            gastos1 = rs.getDouble(1);
        rs.close();
        ps.close();

        String sql2="SELECT SUM(Administrador_Salario) FROM Administrador";
        ps= Conexion.getConnection().prepareStatement(sql2);
        rs = ps.executeQuery();
        double gastos2 = 0;
        if (rs.next())
            gastos2 = rs.getDouble(1);
        rs.close();
        ps.close();

        return gastos1+gastos+gastos2;

    }

    public static double calcularIngresos() throws SQLException {
        String sql="SELECT SUM(Esc_aporte) FROM Escuela";
        PreparedStatement ps = Conexion.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        double aporteEsc = 0;
        if (rs.next())
            aporteEsc = rs.getDouble(1);
        rs.close();
        ps.close();

        sql="SELECT SUM(Jug_aporte) FROM Jugadores";
        ps = Conexion.getConnection().prepareStatement(sql);
        rs = ps.executeQuery();
        double aporteJug = 0;
        if (rs.next())
            aporteJug = rs.getDouble(1);
        rs.close();
        ps.close();

        sql="SELECT SUM(Patrocinador_aporte) FROM Patrocinador";
        ps = Conexion.getConnection().prepareStatement(sql);
        rs = ps.executeQuery();
        double aportePatrocinador = 0;
        if (rs.next())
            aportePatrocinador = rs.getDouble(1);
        rs.close();
        ps.close();

        return aporteJug+aporteEsc+aportePatrocinador;

    }

    public static double calcucularAdeudo() throws SQLException {

        String sql="SELECT SUM(Esc_adeudo) FROM Escuela";
        PreparedStatement ps = Conexion.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        double adeudoEsc= 0;
        if (rs.next())
            adeudoEsc = rs.getDouble(1);
        rs.close();
        ps.close();

        String sql1="SELECT SUM(Jug_adeudo) FROM Jugadores";
        ps = Conexion.getConnection().prepareStatement(sql1);
        rs = ps.executeQuery();
        double adeudoJug= 0;
        if (rs.next())
            adeudoJug = rs.getDouble(1);
        rs.close();
        ps.close();

        return adeudoJug+adeudoEsc;

    }

    public static double dineroTotal(double aporte, double adeudo, double gastos){

        double dineroTotal= (adeudo+aporte)-gastos;

        return dineroTotal;
    }
    public static double dineroReal(double aporte, double gastos){

        double dineroReal= aporte-gastos;

        return dineroReal;
    }

    public static double ingresosPrevistos(double aporte, double adeudo){
        return aporte + adeudo;
    }

}
