module jabatosrb.pfdamapj {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    requires java.sql;

    requires java.mail;

    requires org.json;

    exports jabatosrb.pfdamapj;
    opens jabatosrb.pfdamapj to javafx.fxml;
}