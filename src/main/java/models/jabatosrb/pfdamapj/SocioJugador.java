package jabatosrb.pfdamapj;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;

public class SocioJugador {

    private Jugador jugador;
    private SimpleStringProperty nombre;
    private SimpleStringProperty apellidos;
    private SimpleStringProperty mote;
    private SimpleStringProperty seccion;
    private SimpleDoubleProperty aporteTotal;
    private SimpleDoubleProperty adeudoTotal;

    public SocioJugador(Jugador jugador, String nombre, String apellidos, String mote, String seccion, double aporteTotal, double adeudoTotal) {
        this.jugador = jugador;
        this.nombre = new SimpleStringProperty(nombre);
        this.apellidos = new SimpleStringProperty(apellidos);
        this.mote = new SimpleStringProperty(mote);
        this.seccion = new SimpleStringProperty(seccion);
        this.aporteTotal = new SimpleDoubleProperty(aporteTotal);
        this.adeudoTotal = new SimpleDoubleProperty(adeudoTotal);
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
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

    public String getMote() {
        return mote.get();
    }

    public SimpleStringProperty getMoteProperty() {
        return mote;
    }

    public void setMote(String mote) {
        this.mote.set(mote);
    }

    public String getSeccion() {
        return seccion.get();
    }

    public SimpleStringProperty getSeccionProperty() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion.set(seccion);
    }

    public double getAporteTotal() {
        return aporteTotal.get();
    }

    public SimpleDoubleProperty getAporteTotalProperty() {
        return aporteTotal;
    }

    public void setAporteTotal(double aporteTotal) {
        this.aporteTotal.set(aporteTotal);
    }

    public double getAdeudoTotal() {
        return adeudoTotal.get();
    }

    public SimpleDoubleProperty getAdeudoTotalProperty() {
        return adeudoTotal;
    }

    public void setAdeudoTotal(double adeudoTotal) {
        this.adeudoTotal.set(adeudoTotal);
    }

    @Override
    public String toString() {
        return nombre.get() + ", " + apellidos.get() + ", \"" + mote.get()  + "\"";
    }
}
