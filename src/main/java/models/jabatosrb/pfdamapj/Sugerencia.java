package jabatosrb.pfdamapj;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Sugerencia {

    private SimpleIntegerProperty id;
    private SimpleStringProperty nombre;
    private SimpleStringProperty mote;
    private SimpleStringProperty descripcion;
    private SimpleIntegerProperty socioId;
    private SimpleIntegerProperty clubId;

    public Sugerencia(int id, String nombre, String mote, String descripcion, int socioId, int clubId) {
        this.id = new SimpleIntegerProperty(id);
        this.nombre = new SimpleStringProperty(nombre);
        this.mote = new SimpleStringProperty(mote);
        this.descripcion = new SimpleStringProperty(descripcion);
        this.socioId = new SimpleIntegerProperty(socioId);
        this.clubId = new SimpleIntegerProperty(clubId);
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

    public String getMote() {
        return mote.get();
    }

    public SimpleStringProperty getMoteProperty() {
        return mote;
    }

    public void setMote(String mote) {
        this.mote.set(mote);
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

    public int getSocioId() {
        return socioId.get();
    }

    public SimpleIntegerProperty getSocioIdProperty() {
        return socioId;
    }

    public void setSocioId(int socioId) {
        this.socioId.set(socioId);
    }

    public int getClubId() {
        return clubId.get();
    }

    public SimpleIntegerProperty getClubIdProperty() {
        return clubId;
    }

    public void setClubId(int clubId) {
        this.clubId.set(clubId);
    }
}
