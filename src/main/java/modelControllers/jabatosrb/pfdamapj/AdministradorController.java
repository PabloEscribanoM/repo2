package jabatosrb.pfdamapj;

import jabatosrb.pfdampj.Encrypter;
import jabatosrb.pfdampj.PersistentData;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class AdministradorController {

    public static Administrador getUser(String email, String pwd) throws SQLException, UnsupportedEncodingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        String EPass = Encrypter.encriptar(pwd, PersistentData.getEncryptKey());
        //System.out.println(EPass);
        Administrador user = null;
        Connection cnx = Conexion.getConnection();
        String sql = "SELECT * FROM Administrador WHERE Administrador_Email = ? AND Administrador_pwd = ?";
        PreparedStatement ps = cnx.prepareStatement(sql);
        ps.setString(1, email);
        ps.setString(2, EPass);
        ResultSet rs = ps.executeQuery();

        Date fechaAlta = null;
        Date fechaBaja = null;
        Date fechaNacimiento = null;

        if (rs.next()) {
            if(rs.getDate("Administrador_FechaAlta")!=null)
                fechaAlta = new Date(rs.getDate("Administrador_FechaAlta").getTime());
            if(rs.getDate("Administrador_FechaBaja")!=null)
                fechaBaja = new Date(rs.getDate("Administrador_FechaBaja").getTime());
            if(rs.getDate("Administrador_FechaNacimiento")!=null)
                fechaNacimiento = new Date(rs.getDate("Administrador_FechaNacimiento").getTime());

            user = new Administrador(
                    rs.getInt("Administrador_id"),
                    rs.getString("Administrador_nombre"),
                    rs.getString("Administrador_apellidos"),
                    rs.getString("Administrador_email"),
                    Encrypter.desencriptar(rs.getString("Administrador_pwd"), PersistentData.getEncryptKey()),
                    rs.getString("Administrador_area"),
                    rs.getString("Administrador_dni"),
                    fechaAlta,
                    fechaBaja,
                    fechaNacimiento,
                    rs.getDouble("Administrador_salario"),
                    rs.getString("Administrador_cuentaBancaria"),
                    rs.getInt("Administrador_Club_id"));
        }
        rs.close();
        ps.close();

        if (fechaBaja != null)
            return null;

        return user;

    }

    public static void updatePwds(String oldEncryptKey, String newEncryptKey) throws SQLException, UnsupportedEncodingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        HashMap<Integer, String> passwords = new HashMap<Integer, String>();
        String sql = "SELECT Administrador_id, Administrador_pwd FROM Administrador";

        PreparedStatement ps = Conexion.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while(rs.next())
            passwords.put(rs.getInt("Administrador_id"), Encrypter.desencriptar(rs.getString("Administrador_pwd"), oldEncryptKey));

        rs.close();
        ps.close();

        sql = "UPDATE Administrador SET Administrador_pwd = ? WHERE Administrador_id = ?";

        for (Integer id: passwords.keySet()) {

            ps = Conexion.getConnection().prepareStatement(sql);
            ps.setString(1, Encrypter.encriptar(passwords.get(id), newEncryptKey));
            ps.setInt(2, id);
            ps.executeUpdate();
            ps.close();
        }
    }
    public static ArrayList<Administrador> getFiltered(String filtro) throws SQLException, UnsupportedEncodingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        ArrayList<Administrador> Administradores = new ArrayList<Administrador>();
        String sql = "SELECT * FROM Administrador WHERE Administrador_Nombre LIKE CONCAT('%',?,'%') OR Administrador_Apellidos LIKE CONCAT('%',?,'%') AND WHERE Administrador_fechaBaja IS NULL";

        PreparedStatement ps = Conexion.getConnection().prepareStatement(sql);
        ps.setString(1, filtro);
        ps.setString(2, filtro);

        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            Date FechaAlta = null;
            Date FechaBaja = null;
            Date FechaNacimiento = null;

            if(rs.getDate("Administrador_fechaAlta")!=null)
                FechaNacimiento = new Date(rs.getDate("Administrador_fechaAlta").getTime());
            if(rs.getDate("Administrador_fechaBaja")!=null)
                FechaAlta = new Date(rs.getDate("Administrador_fechaBaja").getTime());
            if(rs.getDate("Administrador_fechaNacimiento")!=null)
                FechaAlta = new Date(rs.getDate("Administrador_fechaNacimiento").getTime());

            Administradores.add(new Administrador(
                    rs.getInt("Administrador_id"),
                    rs.getString("Administrador_nombre"),
                    rs.getString("Administrador_apellidos"),
                    rs.getString("Administrador_email"),
                    Encrypter.desencriptar(rs.getString("Administrador_pwd"), PersistentData.getEncryptKey()),
                    rs.getString("Administrador_area"),
                    rs.getString("Administrador_dni"),
                    FechaAlta,
                    FechaBaja,
                    FechaNacimiento,
                    rs.getDouble("Administrador_salario"),
                    rs.getString("Administrador_cuentaBancaria"),
                    rs.getInt("Administrador_Club_id")
            ));
        }

        rs.close();
        ps.close();

        return Administradores;

    }
    public static void updateAdministrador(Administrador administrador) throws SQLException, ParseException {
        String sql = "UPDATE Administrador SET " +
                "Administrador_Nombre = ?, Administrador_Apellidos = ?, " +
                "Administrador_Email = ?, Administrador_Area = ?, " +
                "Administrador_DNI = ?, Administrador_fechaNacimiento = ?, Administrador_Salario = ?," +
                "Administrador_cuentaBancaria = ? " +
                "WHERE Administrador_id = ?";

        PreparedStatement ps = Conexion.getConnection().prepareStatement(sql);

        ps.setString(1, administrador.getNombre());
        ps.setString(2, administrador.getApellidos());
        ps.setString(3, administrador.getEmail());
        ps.setString(4, administrador.getArea());
        ps.setString(5, administrador.getDni());
        ps.setDate(6, new java.sql.Date(administrador.getFechaNacimientoaDate().getTime()));
        ps.setDouble(7, administrador.getSalario());
        ps.setString(8, administrador.getCuentaBancaria());
        ps.setInt(9, administrador.getId());

        ps.executeUpdate();

        ps.close();
    }
    public static void deleteAdministrador(Administrador administrador) throws SQLException, ParseException {
        String sql = "UPDATE Administrador SET Administrador_fechaBaja = ? WHERE Administrador_id = ?";

        PreparedStatement ps = Conexion.getConnection().prepareStatement(sql);
        ps.setDate(1, new java.sql.Date(new Date().getTime()));
        ps.setInt(2, administrador.getId());

        ps.executeUpdate();

        ps.close();
    }
    public static void addAdministrador(Administrador administrador) throws SQLException, ParseException, UnsupportedEncodingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        String sql = "INSERT INTO Administrador VAlUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, NULL , ? , ? , NULL )";

        PreparedStatement ps = Conexion.getConnection().prepareStatement(sql);

        ps.setString(1, administrador.getNombre());
        ps.setString(2, administrador.getApellidos());
        ps.setString(3, administrador.getEmail());
        ps.setString(4, Encrypter.encriptar(administrador.getPwd(), PersistentData.getEncryptKey()));
        ps.setString(5, administrador.getArea());
        ps.setString(6, administrador.getDni());
        ps.setDate(7, new java.sql.Date(new Date().getTime()));
        ps.setDate(8, new java.sql.Date(administrador.getFechaBajaDate().getTime()));
        ps.setDate(9, new java.sql.Date(administrador.getFechaNacimientoaDate().getTime()));
        ps.setDouble(10, administrador.getSalario());
        ps.setString(11, administrador.getCuentaBancaria());


        ps.executeUpdate();

        ps.close();
    }
}
