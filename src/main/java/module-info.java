module clima {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires tools.jackson.databind;
    requires java.net.http;
    requires io.github.cdimascio.dotenv.java;
    requires tools.jackson.core;

    opens clima.models to tools.jackson.core, tools.jackson.databind;
    opens clima.ui to javafx.fxml;

    exports clima.models;
    exports clima.ui;
}