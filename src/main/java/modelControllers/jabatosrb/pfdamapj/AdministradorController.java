package jabatosrb.pfdamapj;

import jabatosrb.pfdampj.DateFormat;
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
        String sql = "SELECT * FROM Administrador WHERE Administrador_Email = ? AND Administrador_pwd = ? AND Administrador_fechaBaja is null";
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
                    rs.getString("Administrador_Telefono"),
                    rs.getInt("Administrador_Club_id"));
        }
        rs.close();
        ps.close();

        if (fechaBaja != null)
            return null;

        return user;

    }

    public static Administrador getMaster() throws SQLException, UnsupportedEncodingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        return getUser("master@master.com", "1234");
    }

    public static void createMaster() throws ParseException, SQLException, UnsupportedEncodingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        addAdministrador(new Administrador(0, "MASTER", "MASTER", "master@master.com", "1234",
                                         "MASTER", "XXXXXXXXX", new Date(), null, DateFormat.fechaToDate("01-01-2020"),
                                       0.0, "XXXXXXXXX", "XXXXXXXXX", 1));
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
        String sql = "SELECT * FROM Administrador WHERE (Administrador_Nombre LIKE CONCAT('%',?,'%') OR Administrador_Apellidos LIKE CONCAT('%',?,'%')) AND Administrador_fechaBaja IS NULL AND Administrador_Email != 'master@master.com'";

        PreparedStatement ps = Conexion.getConnection().prepareStatement(sql);
        ps.setString(1, filtro);
        ps.setString(2, filtro);

        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            Date FechaAlta = null;
            Date FechaBaja = null;
            Date FechaNacimiento = null;

            if(rs.getDate("Administrador_fechaAlta")!=null)
                FechaAlta = new Date(rs.getDate("Administrador_fechaAlta").getTime());
            if(rs.getDate("Administrador_fechaBaja")!=null)
                FechaBaja = new Date(rs.getDate("Administrador_fechaBaja").getTime());
            if(rs.getDate("Administrador_fechaNacimiento")!=null)
                FechaNacimiento = new Date(rs.getDate("Administrador_fechaNacimiento").getTime());

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
                    rs.getString("Administrador_Telefono"),
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
                "Administrador_cuentaBancaria = ?, Administrador_Telefono = ? " +
                "WHERE Administrador_id = ?";

        PreparedStatement ps = Conexion.getConnection().prepareStatement(sql);

        ps.setString(1, administrador.getNombre());
        ps.setString(2, administrador.getApellidos());
        ps.setString(3, administrador.getEmail());
        ps.setString(4, administrador.getArea());
        ps.setString(5, administrador.getDni());
        ps.setDate(6, new java.sql.Date(administrador.getFechaNacimientoDate().getTime()));
        ps.setDouble(7, administrador.getSalario());
        ps.setString(8, administrador.getCuentaBancaria());
        ps.setString(9, administrador.getTelefono());
        ps.setInt(10, administrador.getId());

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
        String sql = "INSERT INTO Administrador VAlUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, NULL , ?, ? , ? , 1 )";

        PreparedStatement ps = Conexion.getConnection().prepareStatement(sql);

        ps.setString(1, administrador.getNombre());
        ps.setString(2, administrador.getApellidos());
        ps.setString(3, administrador.getTelefono());
        ps.setString(4, administrador.getEmail());
        ps.setString(5, Encrypter.encriptar(administrador.getPwd(), PersistentData.getEncryptKey()));
        ps.setString(6, administrador.getArea());
        ps.setString(7, administrador.getDni());
        ps.setDate(8, new java.sql.Date(new Date().getTime()));
        ps.setDate(9, new java.sql.Date(administrador.getFechaNacimientoDate().getTime()));
        ps.setDouble(10, administrador.getSalario());
        ps.setString(11, administrador.getCuentaBancaria());


        ps.executeUpdate();

        ps.close();
    }

    public static void updateUser(Administrador user) throws SQLException, ParseException, UnsupportedEncodingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        String sql = "UPDATE Administrador SET " +
                "Administrador_Nombre = ?, Administrador_Apellidos = ?, " +
                "Administrador_Email = ?, Administrador_Area = ?, " +
                "Administrador_DNI = ?, Administrador_fechaNacimiento = ?, Administrador_Salario = ?," +
                "Administrador_cuentaBancaria = ?, Administrador_Telefono = ?, Administrador_pwd = ? " +
                "WHERE Administrador_id = ?";

        PreparedStatement ps = Conexion.getConnection().prepareStatement(sql);

        ps.setString(1, user.getNombre());
        ps.setString(2, user.getApellidos());
        ps.setString(3, user.getEmail());
        ps.setString(4, user.getArea());
        ps.setString(5, user.getDni());
        ps.setDate(6, new java.sql.Date(user.getFechaNacimientoDate().getTime()));
        ps.setDouble(7, user.getSalario());
        ps.setString(8, user.getCuentaBancaria());
        ps.setString(9, user.getTelefono());
        ps.setString(10, Encrypter.encriptar(user.getPwd(), PersistentData.getEncryptKey()));
        ps.setInt(11, user.getId());

        ps.executeUpdate();

        ps.close();
    }
}
