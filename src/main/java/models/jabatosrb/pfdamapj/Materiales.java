package jabatosrb.pfdamapj;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Materiales {
    private SimpleIntegerProperty matId;
    private SimpleStringProperty matNombre;
    private SimpleIntegerProperty matStock;
    private SimpleStringProperty matDescripcion;
    private SimpleStringProperty matBeneficiario;
    private SimpleIntegerProperty matIdClub;
    private SimpleDoubleProperty matPrecio;

    public Materiales(Integer matId, String matNombre, Integer matStock, String matDescripcion, String matBeneficiario, Integer matIdClub, Double matPrecio) {
        this.matId = new SimpleIntegerProperty(matId);
        this.matNombre = new SimpleStringProperty(matNombre);
        this.matStock = new SimpleIntegerProperty(matStock);
        this.matDescripcion = new SimpleStringProperty(matDescripcion);
        this.matBeneficiario = new SimpleStringProperty(matBeneficiario);
        this.matIdClub = new SimpleIntegerProperty(matIdClub);
        this.matPrecio = new SimpleDoubleProperty(matPrecio);
    }

    public int getMatId() {return matId.get();}

    public SimpleIntegerProperty matIdProperty() {return matId;}

    public void setMatId(int matId) {this.matId.set(matId);}

    public String getMatNombre() {return matNombre.get();}

    public SimpleStringProperty matNombreProperty() {return matNombre;}

    public void setMatNombre(String matNombre) {this.matNombre.set(matNombre);}

    public int getMatStock() {return matStock.get();}

    public SimpleIntegerProperty matStockProperty() {return matStock;}

    public void setMatStock(int matStock) {this.matStock.set(matStock);}

    public String getMatDescripcion() {return matDescripcion.get();}

    public SimpleStringProperty matDescripcionProperty() {return matDescripcion;}

    public void setMatDescripcion(String matDescripcion) {this.matDescripcion.set(matDescripcion);}

    public String getMatBeneficiario() {return matBeneficiario.get();}

    public SimpleStringProperty matBeneficiarioProperty() {return matBeneficiario;}

    public void setMatBeneficiario(String matBeneficiario) {this.matBeneficiario.set(matBeneficiario);}

    public int getMatIdClub() {return matIdClub.get();}

    public SimpleIntegerProperty matIdClubProperty() {return matIdClub;}

    public void setMatIdClub(int matIdClub) {this.matIdClub.set(matIdClub);}

    public double getMatPrecio() {return matPrecio.get();}

    public SimpleDoubleProperty matPrecioProperty() {return matPrecio;}

    public void setMatPrecio(double matPrecio) {this.matPrecio.set(matPrecio);}
}
