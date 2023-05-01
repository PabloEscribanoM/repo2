package jabatosrb.pfdamapj;

import jabatosrb.pfdampj.DateFormat;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FuturoSocio {

    private SimpleIntegerProperty id;
    private SimpleStringProperty nombre;
    private SimpleStringProperty apellidos;
    private SimpleStringProperty telefono;
    private SimpleStringProperty descripcion;
    private SimpleStringProperty fechaAlta;
    private SimpleStringProperty fechaBaja;
    private SimpleStringProperty fechaNacimiento;
    private SimpleStringProperty cuentaBancaria;
    private SimpleIntegerProperty idClub;

    public FuturoSocio(int id, String nombre, String apellidos, String telefono, String descripcion, Date fechaAlta, Date fechaBaja, Date fechaNacimiento, String cuentaBancaria, int idClub) {
        this.id = new SimpleIntegerProperty(id);
        this.nombre = new SimpleStringProperty(nombre);
        this.apellidos = new SimpleStringProperty(apellidos);
        this.telefono = new SimpleStringProperty(telefono);
        this.descripcion = new SimpleStringProperty(descripcion);
        this.fechaAlta = new SimpleStringProperty(DateFormat.fechaToString(fechaAlta));
        this.fechaBaja = new SimpleStringProperty(DateFormat.fechaToString(fechaBaja));
        this.fechaNacimiento = new SimpleStringProperty(DateFormat.fechaToString(fechaNacimiento));
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

    public String getTelefono() {
        return telefono.get();
    }

    public SimpleStringProperty getTelefonoProperty() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono.set(telefono);
    }

    public String getDescripcion() {
        return descripcion.get();
    }

    public SimpleStringProperty getDescripcionProperty() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion.set(descripcion);
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
