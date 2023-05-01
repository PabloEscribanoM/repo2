package jabatosrb.pfdamapj;

import jabatosrb.pfdampj.DateFormat;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.text.ParseException;
import java.util.Date;

public class Entrenadores {
    private SimpleIntegerProperty id;
    private SimpleStringProperty nombre;
    private SimpleStringProperty apellidos;
    private SimpleStringProperty telefono;
    private SimpleDoubleProperty salario;
    private SimpleStringProperty fechaAlta;
    private SimpleStringProperty fechaBaja;
    private SimpleStringProperty fechaNacimiento;
    private SimpleStringProperty categoria;
    private SimpleStringProperty dni;
    private SimpleIntegerProperty idClub;
    private SimpleStringProperty cuentaBancaria;

    public Entrenadores(Integer id, String nombre, String apellidos, String telefono, Double salario, Date fechaAlta, Date fechaBaja, Date fechaNacimiento, String categoria, String dni, Integer idClub, String cuentaBancaria) {
        this.id = new SimpleIntegerProperty(id);
        this.nombre = new SimpleStringProperty(nombre);
        this.apellidos = new SimpleStringProperty(apellidos);
        this.telefono = new SimpleStringProperty(telefono);
        this.salario = new SimpleDoubleProperty(salario);
        this.fechaAlta = new SimpleStringProperty(DateFormat.fechaToString(fechaAlta));
        this.fechaBaja = new SimpleStringProperty(DateFormat.fechaToString(fechaBaja));
        this.fechaNacimiento = new SimpleStringProperty(DateFormat.fechaToString(fechaNacimiento));
        this.categoria = new SimpleStringProperty(categoria);
        this.dni = new SimpleStringProperty(dni);
        this.idClub = new SimpleIntegerProperty(idClub);
        this.cuentaBancaria = new SimpleStringProperty(cuentaBancaria);
    }

    public int getId() {return id.get();}

    public SimpleIntegerProperty idProperty() {return id;}

    public void setId(int id) {this.id.set(id);}

    public String getNombre() {return nombre.get();}

    public SimpleStringProperty nombreProperty() {return nombre;}

    public void setNombre(String nombre) {this.nombre.set(nombre);}

    public String getApellidos() {return apellidos.get();}

    public SimpleStringProperty apellidosProperty() {return apellidos;}

    public void setApellidos(String apellidos) {this.apellidos.set(apellidos);}

    public String getTelefono() {return telefono.get();}

    public SimpleStringProperty telefonoProperty() {return telefono;}

    public void setTelefono(String telefono) {this.telefono.set(telefono);}

    public double getSalario() {return salario.get();}

    public SimpleDoubleProperty salarioProperty() {return salario;}

    public void setSalario(double salario) {this.salario.set(salario);}

    public String getFechaAlta() {return fechaAlta.get();}

    public SimpleStringProperty fechaAltaProperty() {return fechaAlta;}

    public Date getFechaAltaDate() throws ParseException{return DateFormat.fechaToDate(fechaAlta.get());}

    public void setFechaAlta(Date fechaAlta) {this.fechaAlta.set(DateFormat.fechaToString(fechaAlta));}

    public String getFechaBaja() {return fechaBaja.get();}

    public SimpleStringProperty fechaBajaProperty() {return fechaBaja;}

    public Date getFechaBajaDate() throws ParseException{return DateFormat.fechaToDate(fechaBaja.get());}

    public void setFechaBaja(Date fechaBaja) {this.fechaBaja.set(DateFormat.fechaToString(fechaBaja));}

    public String getFechaNacimiento() {return fechaNacimiento.get();}

    public SimpleStringProperty fechaNacimientoProperty() {return fechaNacimiento;}

    public Date getFechaNacimientoDate() throws ParseException{return DateFormat.fechaToDate(fechaNacimiento.get());}

    public void setFechaNacimiento(Date fechaNacimiento) {this.fechaNacimiento.set(DateFormat.fechaToString(fechaNacimiento));}

    public String getCategoria() {return categoria.get();}

    public SimpleStringProperty categoriaProperty() {return categoria;}

    public void setCategoria(String categoria) {this.categoria.set(categoria);}

    public String getDni() {return dni.get();}

    public SimpleStringProperty dniProperty() {return dni;}

    public void setDni(String dni) {this.dni.set(dni);}

    public int getIdClub() {return idClub.get();}

    public SimpleIntegerProperty idClubProperty() {return idClub;}

    public void setIdClub(int idClub) {this.idClub.set(idClub);}

    public String getCuentaBancaria() {return cuentaBancaria.get();}

    public SimpleStringProperty cuentaBancariaProperty() {return cuentaBancaria;}

    public void setCuentaBancaria(String cuentaBancaria) {this.cuentaBancaria.set(cuentaBancaria);}
}
