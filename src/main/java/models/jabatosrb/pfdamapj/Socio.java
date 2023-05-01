package jabatosrb.pfdamapj;

import jabatosrb.pfdampj.DateFormat;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Socio {

    private SimpleIntegerProperty socId;
    private SimpleStringProperty socNombre;
    private SimpleStringProperty socApellido;
    private SimpleStringProperty socMote;
    private SimpleStringProperty socCuentaBancaria;
    private SimpleStringProperty socEmail;
    private SimpleStringProperty socPwd;
    private SimpleStringProperty socNumTel;
    private SimpleDoubleProperty socAporte;
    private SimpleDoubleProperty socAdeudo;
    private SimpleStringProperty socFechaNacimiento;
    private SimpleStringProperty socFechaAlta;
    private SimpleIntegerProperty idClub;

    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    public Socio(int socId, String socNombre, String socApellido, String socMote, String socCuentaBancaria, String socEmail, String socPwd, String socNumTel, double socAporte, double socAdeudo, Date socFechaNacimiento, Date socFechaAlta, int idClub) {
        this.socId = new SimpleIntegerProperty(socId);
        this.socNombre = new SimpleStringProperty(socNombre);
        this.socApellido = new SimpleStringProperty(socApellido);
        this.socMote = new SimpleStringProperty(socMote);
        this.socCuentaBancaria = new SimpleStringProperty(socCuentaBancaria);
        this.socEmail = new SimpleStringProperty(socEmail);
        this.socPwd = new SimpleStringProperty(socPwd);
        this.socNumTel = new SimpleStringProperty(socNumTel);
        this.socAporte = new SimpleDoubleProperty(socAporte);
        this.socAdeudo = new SimpleDoubleProperty(socAdeudo);
        this.socFechaNacimiento = new SimpleStringProperty(DateFormat.fechaToString(socFechaNacimiento));
        this.socFechaAlta = new SimpleStringProperty(DateFormat.fechaToString(socFechaAlta));
        this.idClub = new SimpleIntegerProperty(idClub);
    }

    public int getSocId() {
        return socId.get();
    }

    public SimpleIntegerProperty getSocIdProperty() {
        return socId;
    }

    public void setSocId(int socId) {
        this.socId.set(socId);
    }

    public String getSocNombre() {
        return socNombre.get();
    }

    public SimpleStringProperty getSocNombreProperty() {
        return socNombre;
    }

    public void setSocNombre(String socNombre) {
        this.socNombre.set(socNombre);
    }

    public String getSocApellido() {
        return socApellido.get();
    }

    public SimpleStringProperty getSocApellidoProperty() {
        return socApellido;
    }

    public void setSocApellido(String socApellido) {
        this.socApellido.set(socApellido);
    }

    public String getSocMote() {
        return socMote.get();
    }

    public SimpleStringProperty getSocMoteProperty() {
        return socMote;
    }

    public void setSocMote(String socMote) {
        this.socMote.set(socMote);
    }

    public String getSocCuentaBancaria() {
        return socCuentaBancaria.get();
    }

    public SimpleStringProperty getSocCuentaBancariaProperty() {
        return socCuentaBancaria;
    }

    public void setSocCuentaBancaria(String socCuentaBancaria) {
        this.socCuentaBancaria.set(socCuentaBancaria);
    }

    public String getSocEmail() {
        return socEmail.get();
    }

    public SimpleStringProperty getSocEmailProperty() {
        return socEmail;
    }

    public void setSocEmail(String socEmail) {
        this.socEmail.set(socEmail);
    }

    public String getSocPwd() {
        return socPwd.get();
    }

    public SimpleStringProperty getSocPwdProperty() {
        return socPwd;
    }

    public void setSocPwd(String socPwd) {
        this.socPwd.set(socPwd);
    }

    public String getSocNumTel() {
        return socNumTel.get();
    }

    public SimpleStringProperty getSocNumTelProperty() {
        return socNumTel;
    }

    public void setSocNumTel(String socNumTel) {
        this.socNumTel.set(socNumTel);
    }

    public double getSocAporte() {
        return socAporte.get();
    }

    public SimpleDoubleProperty getSocAporteProperty() {
        return socAporte;
    }

    public void setSocAporte(double socAporte) {
        this.socAporte.set(socAporte);
    }

    public double getSocAdeudo() {
        return socAdeudo.get();
    }

    public SimpleDoubleProperty getSocAdeudoProperty() {
        return socAdeudo;
    }

    public void setSocAdeudo(double socAdeudo) {
        this.socAdeudo.set(socAdeudo);
    }

    public String getSocFechaNacimiento() {
        return socFechaNacimiento.get();
    }

    public SimpleStringProperty getSocFechaNacimientoProperty() {
        return socFechaNacimiento;
    }

    public Date getSocFechaNacimientoDate() throws ParseException {
        return DateFormat.fechaToDate(socFechaNacimiento.get());
    }

    public void setSocFechaNacimiento(Date socFechaNacimiento) {
        this.socFechaNacimiento.set(DateFormat.fechaToString(socFechaNacimiento));
    }

    public String getSocFechaAlta() {
        return socFechaAlta.get();
    }

    public SimpleStringProperty getSocFechaAltaProperty() {
        return socFechaAlta;
    }

    public Date getSocFechaAltaDate() throws ParseException {
        return DateFormat.fechaToDate(socFechaAlta.get());
    }

    public void setSocFechaAlta(Date socFechaAlta) {
        this.socFechaAlta.set(DateFormat.fechaToString(socFechaAlta));
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

    @Override
    public String toString() {
        return socNombre.get() + ", " + socApellido.get() + ", \"" + socMote.get() + "\"";
    }
}
