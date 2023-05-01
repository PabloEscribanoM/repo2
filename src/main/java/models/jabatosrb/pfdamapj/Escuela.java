package jabatosrb.pfdamapj;

import jabatosrb.pfdampj.DateFormat;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.text.ParseException;
import java.util.Date;

public class Escuela {

    private SimpleIntegerProperty id;
    private SimpleStringProperty nombre;
    private SimpleStringProperty apellidos;
    private SimpleStringProperty genero;
    private SimpleStringProperty numFicha;
    private SimpleStringProperty tutorEmail;
    private SimpleStringProperty tutorNombre;
    private SimpleStringProperty tutorApellidos;
    private SimpleStringProperty numTelefono;
    private SimpleStringProperty fechaNacimiento;
    private SimpleStringProperty fechaAlta;
    private SimpleDoubleProperty aporte;
    private SimpleIntegerProperty idClub;
    private SimpleStringProperty cuentaBancaria;
    private SimpleDoubleProperty adeudo;
    private SimpleStringProperty edad;

    public Escuela(int id, String nombre, String apellidos, String genero, String numFicha, String tutorEmail, String tutorNombre, String tutorApellidos, String numTelefono, Date fechaNacimiento, Date fechaAlta, double aporte, int idClub, String cuentaBancaria,double adeudo) {
        this.id = new SimpleIntegerProperty(id);
        this.nombre = new SimpleStringProperty(nombre);
        this.apellidos = new SimpleStringProperty(apellidos);
        this.genero = new SimpleStringProperty(genero);
        this.numFicha = new SimpleStringProperty(numFicha);
        this.tutorEmail = new SimpleStringProperty(tutorEmail);
        this.tutorNombre = new SimpleStringProperty(tutorNombre);
        this.tutorApellidos = new SimpleStringProperty(tutorApellidos);
        this.numTelefono = new SimpleStringProperty(numTelefono);
        this.fechaNacimiento = new SimpleStringProperty(DateFormat.fechaToString(fechaNacimiento));
        this.fechaAlta = new SimpleStringProperty(DateFormat.fechaToString(fechaAlta));
        this.aporte = new SimpleDoubleProperty(aporte);
        this.idClub = new SimpleIntegerProperty(idClub);
        this.cuentaBancaria = new SimpleStringProperty(cuentaBancaria);
        this.adeudo = new SimpleDoubleProperty(adeudo);
        int edad = DateFormat.anyos(fechaNacimiento);
        String cat = "";
        if(edad <= 10){
            cat += "PRERUGBY ";
            if (edad<=6)
                cat += "SUB 6";
            else if (edad<=8)
                cat += "SUB 8";
            else
                cat += "SUB 10";
        }else if(edad <= 12)
            cat += "MINIRUGBY SUB 12";
        else if(edad <= 14)
            cat += "MIDIRUGBY SUB 14";
        else if(edad <= 16)
            cat += "SUB 16";
        else
            cat += "SUB 18";
        this.edad = new SimpleStringProperty(cat);
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

    public String getGenero() {
        return genero.get();
    }

    public SimpleStringProperty getGeneroProperty() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero.set(genero);
    }

    public String getNumFicha() {
        return numFicha.get();
    }

    public SimpleStringProperty getNumFichaProperty() {
        return numFicha;
    }

    public void setNumFicha(String numFicha) {
        this.numFicha.set(numFicha);
    }

    public String getTutorEmail() {
        return tutorEmail.get();
    }

    public SimpleStringProperty getTutorEmailProperty() {
        return tutorEmail;
    }

    public void setTutorEmail(String tutorEmail) {
        this.tutorEmail.set(tutorEmail);
    }

    public String getTutorNombre() {
        return tutorNombre.get();
    }

    public SimpleStringProperty getTutorNombreProperty() {
        return tutorNombre;
    }

    public void setTutorNombre(String tutorNombre) {
        this.tutorNombre.set(tutorNombre);
    }

    public String getTutorApellidos() {
        return tutorApellidos.get();
    }

    public SimpleStringProperty getTutorApellidosProperty() {
        return tutorApellidos;
    }

    public void setTutorApellidos(String tutorApellidos) {
        this.tutorApellidos.set(tutorApellidos);
    }

    public String getNumTelefono() {
        return numTelefono.get();
    }

    public SimpleStringProperty getNumTelefonoProperty() {
        return numTelefono;
    }

    public void setNumTelefono(String numTelefono) {
        this.numTelefono.set(numTelefono);
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

    public double getAporte() {
        return aporte.get();
    }

    public SimpleDoubleProperty getAporteProperty() {
        return aporte;
    }

    public void setAporte(double aporte) {
        this.aporte.set(aporte);
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

    public String getCuentaBancaria() {
        return cuentaBancaria.get();
    }

    public SimpleStringProperty getCuentaBancariaProperty() {
        return cuentaBancaria;
    }

    public void setCuentaBancaria(String cuentaBancaria) {
        this.cuentaBancaria.set(cuentaBancaria);
    }

    public double getAdeudo() {
        return adeudo.get();
    }

    public SimpleDoubleProperty getAdeudoProperty() {
        return adeudo;
    }

    public void setAdeudo(double adeudo) {
        this.adeudo.set(adeudo);
    }

    public String getEdad() {
        return edad.get();
    }

    public SimpleStringProperty getEdadProperty() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad.set(edad);
    }
}
