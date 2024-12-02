module GestionCinema {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.sql;


    // Opening packages to JavaFX for reflection-based access
    opens app to javafx.fxml;
    opens controllers to javafx.fxml;

    exports app;
    exports controllers;
    exports database;
    exports models;
}

