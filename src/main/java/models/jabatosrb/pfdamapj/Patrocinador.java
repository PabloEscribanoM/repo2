package jabatosrb.pfdamapj;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Patrocinador {
    private SimpleIntegerProperty id;
    private SimpleStringProperty nombre;
    private SimpleStringProperty descripcion;
    private SimpleDoubleProperty aporte;
    private SimpleStringProperty cuentaBancaria;
    private SimpleIntegerProperty clubId;

    public Patrocinador(Integer id, String nombre, String descripcion, Double aporte, String cuentaBancaria, Integer clubId) {
        this.id = new SimpleIntegerProperty(id);
        this.nombre = new SimpleStringProperty(nombre);
        this.descripcion = new SimpleStringProperty(descripcion);
        this.aporte = new SimpleDoubleProperty(aporte);
        this.cuentaBancaria = new SimpleStringProperty(cuentaBancaria);
        this.clubId = new SimpleIntegerProperty(clubId);
    }

    public int getId() {return id.get();}

    public SimpleIntegerProperty idProperty() {return id;}

    public void setId(int id) {this.id.set(id);}

    public String getNombre() {return nombre.get();}

    public SimpleStringProperty nombreProperty() {return nombre;}

    public void setNombre(String nombre) {this.nombre.set(nombre);}

    public String getDescripcion() {return descripcion.get();}

    public SimpleStringProperty descripcionProperty() {return descripcion;}

    public void setDescripcion(String descripcion) {this.descripcion.set(descripcion);}

    public double getAporte() {return aporte.get();}

    public SimpleDoubleProperty aporteProperty() {return aporte;}

    public void setAporte(double aporte) {this.aporte.set(aporte);}

    public String getCuentaBancaria() {return cuentaBancaria.get();}

    public SimpleStringProperty cuentaBancariaProperty() {return cuentaBancaria;}

    public void setCuentaBancaria(String cuentaBancaria) {this.cuentaBancaria.set(cuentaBancaria);}

    public int getClubId() {return clubId.get();}

    public SimpleIntegerProperty clubIdProperty() {return clubId;}

    public void setClubId(int clubId) {this.clubId.set(clubId);}
}
