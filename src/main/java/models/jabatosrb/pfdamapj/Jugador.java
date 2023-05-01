package jabatosrb.pfdamapj;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Jugador {

    private SimpleIntegerProperty id;
    private SimpleStringProperty temporada;
    private SimpleStringProperty numFicha;
    private SimpleStringProperty seccion;
    private SimpleDoubleProperty aporte;
    private SimpleBooleanProperty federadoPrevio;
    private SimpleIntegerProperty idSocio;
    private SimpleIntegerProperty idClub;
    private SimpleDoubleProperty adeudo;

    public Jugador(int id, String temporada, String numFicha, String seccion, Double aporte, Boolean federadoPrevio, int idSocio, int idClub, double adeudo) {
        this.id = new SimpleIntegerProperty(id);
        this.temporada = new SimpleStringProperty(temporada);
        this.numFicha = new SimpleStringProperty(numFicha);
        this.seccion = new SimpleStringProperty(seccion);
        this.aporte = new SimpleDoubleProperty(aporte);
        this.federadoPrevio = new SimpleBooleanProperty(federadoPrevio);
        this.idSocio = new SimpleIntegerProperty(idSocio);
        this.idClub = new SimpleIntegerProperty(idClub);
        this.adeudo = new SimpleDoubleProperty(adeudo);
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

    public String getTemporada() {
        return temporada.get();
    }

    public SimpleStringProperty getTemporadaProperty() {
        return temporada;
    }

    public void setTemporada(String temporada) {
        this.temporada.set(temporada);
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

    public String getSeccion() {
        return seccion.get();
    }

    public SimpleStringProperty getSeccionProperty() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion.set(seccion);
    }

    public double getAporte() {
        return aporte.get();
    }

    public SimpleDoubleProperty getAporteProperty() {
        return aporte;
    }

    public void setAporte(Double aporte) {
        this.aporte.set(aporte);
    }

    public Boolean getFederadoPrevio() {
        return federadoPrevio.get();
    }

    public SimpleBooleanProperty getFederadoPrevioProperty() {
        return federadoPrevio;
    }

    public void setFederadoPrevio(Boolean federadoPrevio) {
        this.federadoPrevio.set(federadoPrevio);
    }

    public int getIdSocio() {
        return idSocio.get();
    }

    public SimpleIntegerProperty getIdSocioProperty() {
        return idSocio;
    }

    public void setIdSocio(int idSocio) {
        this.idSocio.set(idSocio);
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

    public double getAdeudo() {
        return adeudo.get();
    }

    public SimpleDoubleProperty getAdeudoProperty() {
        return adeudo;
    }

    public void setAdeudo(double adeudo) {
        this.adeudo.set(adeudo);
    }
}
