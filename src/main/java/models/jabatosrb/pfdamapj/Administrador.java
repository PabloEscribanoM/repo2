package jabatosrb.pfdamapj;

import jabatosrb.pfdampj.DateFormat;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.text.ParseException;
import java.util.Date;

public class Administrador {

    private SimpleIntegerProperty id;
    private SimpleStringProperty nombre;
    private SimpleStringProperty apellidos;
    private SimpleStringProperty email;
    private SimpleStringProperty pwd;
    private SimpleStringProperty area;
    private SimpleStringProperty dni;
    private SimpleStringProperty fechaAlta;
    private SimpleStringProperty fechaBaja;
    private SimpleStringProperty fechaNacimiento;
    private SimpleDoubleProperty salario;
    private SimpleStringProperty cuentaBancaria;
    private SimpleIntegerProperty idClub;

    public Administrador(int id, String nombre, String apellidos, String email, String pwd, String area, String dni, Date fechaAlta, Date fechaBaja, Date fechaNacimiento, double salario, String cuentaBancaria, int idClub) {
        this.id = new SimpleIntegerProperty(id);
        this.nombre = new SimpleStringProperty(nombre);
        this.apellidos = new SimpleStringProperty(apellidos);
        this.email = new SimpleStringProperty(email);
        this.pwd = new SimpleStringProperty(pwd);
        this.area = new SimpleStringProperty(area);
        this.dni = new SimpleStringProperty(dni);
        this.fechaAlta = new SimpleStringProperty(DateFormat.fechaToString(fechaAlta));
        this.fechaBaja = new SimpleStringProperty(DateFormat.fechaToString(fechaBaja));
        this.fechaNacimiento = new SimpleStringProperty(DateFormat.fechaToString(fechaNacimiento));
        this.salario = new SimpleDoubleProperty(salario);
        this.cuentaBancaria = new SimpleStringProperty(cuentaBancaria);
        this.idClub = new SimpleIntegerProperty(idClub);
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty getIdProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getNombre() {
        return nombre.get();
    }

    public SimpleStringProperty getNombreProperty() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public String getApellidos() {
        return apellidos.get();
    }

    public SimpleStringProperty getApellidosProperty() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos.set(apellidos);
    }

    public String getEmail() {
        return email.get();
    }

    public SimpleStringProperty getEmailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getPwd() {
        return pwd.get();
    }

    public SimpleStringProperty getPwdProperty() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd.set(pwd);
    }

    public String getArea() {
        return area.get();
    }

    public SimpleStringProperty getAreaProperty() {
        return area;
    }

    public void setArea(String area) {
        this.area.set(area);
    }

    public String getDni() {
        return dni.get();
    }

    public SimpleStringProperty getDniProperty() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni.set(dni);
    }

    public String getFechaAlta() {
        return fechaAlta.get();
    }

    public SimpleStringProperty getFechaAltaProperty() {
        return fechaAlta;
    }

    public Date getFechaAltaDate() throws ParseException {
        return DateFormat.fechaToDate(fechaAlta.get());
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta.set(DateFormat.fechaToString(fechaAlta));
    }

    public String getFechaBaja() {
        return fechaBaja.get();
    }

    public SimpleStringProperty getFechaBajaProperty() {
        return fechaBaja;
    }

    public Date getFechaBajaDate() throws ParseException {
        return DateFormat.fechaToDate(fechaBaja.get());
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja.set(DateFormat.fechaToString(fechaBaja));
    }

    public String getFechaNacimiento() {
        return fechaNacimiento.get();
    }

    public SimpleStringProperty getFechaNacimientoProperty() {
        return fechaNacimiento;
    }

    public Date getFechaNacimientoaDate() throws ParseException {
        return DateFormat.fechaToDate(fechaNacimiento.get());
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento.set(DateFormat.fechaToString(fechaNacimiento));
    }

    public double getSalario() {
        return salario.get();
    }

    public SimpleDoubleProperty getSalarioProperty() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario.set(salario);
    }

    public String getCuentaBancaria() {
        return cuentaBancaria.get();
    }

    public SimpleStringProperty getCuentaBancariaProperty() {
        return cuentaBancaria;
    }

    public void setCuentaBancaria(String cuentaBancaria) {
        this.cuentaBancaria.set(cuentaBancaria);
    }

    public int getIdClub() {
        return idClub.get();
    }

    public SimpleIntegerProperty getIdClubProperty() {
        return idClub;
    }

    public void setIdClub(int idClub) {
        this.idClub.set(idClub);
    }

}
