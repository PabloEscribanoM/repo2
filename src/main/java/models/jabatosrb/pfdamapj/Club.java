package jabatosrb.pfdamapj;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Club {
    private SimpleIntegerProperty clubId;
    private SimpleStringProperty clubNombre;
    private SimpleStringProperty clubTelefono;
    private SimpleStringProperty clubIdFiscal;
    private SimpleDoubleProperty clubGastos;
    private SimpleDoubleProperty clubIngresos;
    private SimpleDoubleProperty clubResTotal;
    private SimpleDoubleProperty clubIngresosPrevistos;

    public Club(int clubId, String clubNombre, String clubTelefono, String clubIdFiscal, Double clubGastos, Double clubIngresos, Double clubResTotal, Double clubIngresosPrevisitos) {
        this.clubId = new SimpleIntegerProperty(clubId);
        this.clubNombre = new SimpleStringProperty(clubNombre);
        this.clubTelefono = new SimpleStringProperty(clubTelefono);
        this.clubIdFiscal = new SimpleStringProperty(clubIdFiscal);
        this.clubGastos = new SimpleDoubleProperty(clubGastos);
        this.clubIngresos = new SimpleDoubleProperty(clubIngresos);
        this.clubResTotal = new SimpleDoubleProperty(clubResTotal);
        this.clubIngresosPrevistos =new SimpleDoubleProperty(clubIngresosPrevisitos);
    }

    public int getClubId() {return clubId.get();}

    public SimpleIntegerProperty clubIdProperty() {return clubId;}

    public void setClubId(int clubId) {this.clubId.set(clubId);}

    public String getClubNombre() {return clubNombre.get();}

    public SimpleStringProperty clubNombreProperty() {return clubNombre;}

    public void setClubNombre(String clubNombre) {this.clubNombre.set(clubNombre);}

    public String getClubTelefono() {return clubTelefono.get();}

    public SimpleStringProperty clubTelefonoProperty() {return clubTelefono;}

    public void setClubTelefono(String clubTelefono) {this.clubTelefono.set(clubTelefono);}

    public String getClubIdFiscal() {return clubIdFiscal.get();}

    public SimpleStringProperty clubIdFiscalProperty() {return clubIdFiscal;}

    public void setClubIdFiscal(String clubIdFiscal) {this.clubIdFiscal.set(clubIdFiscal);}

    public double getClubGastos() {return clubGastos.get();}

    public SimpleDoubleProperty clubGastosProperty() {return clubGastos;}

    public void setClubGastos(double clubGastos) {this.clubGastos.set(clubGastos);}

    public double getClubIngresos() {return clubIngresos.get();}

    public SimpleDoubleProperty clubIngresosProperty() {return clubIngresos;}

    public void setClubIngresos(double clubIngresos) {this.clubIngresos.set(clubIngresos);}

    public double getClubResTotal() {return clubResTotal.get();}

    public SimpleDoubleProperty clubResTotalProperty() {return clubResTotal;}

    public double getClubIngresosPrevistos() {
        return clubIngresosPrevistos.get();
    }

    public SimpleDoubleProperty clubIngresosPrevistosProperty() {
        return clubIngresosPrevistos;
    }

    public void setClubIngresosPrevistos(double clubIngresosPrevistos) {
        this.clubIngresosPrevistos.set(clubIngresosPrevistos);
    }

    public void setClubResTotal(double clubResTotal) {this.clubResTotal.set(clubResTotal);

    }
}
