module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires java.dotenv;
    requires com.jfoenix;

    opens com.fleetforyou.fleetforyou to javafx.fxml;
    exports com.fleetforyou.fleetforyou;
    exports com.fleetforyou.fleetforyou.Controllers;
    opens com.fleetforyou.fleetforyou.Controllers to javafx.fxml;
    exports com.fleetforyou.fleetforyou.Controllers.Clients;
    opens com.fleetforyou.fleetforyou.Controllers.Clients to javafx.fxml;
    exports com.fleetforyou.fleetforyou.Controllers.Extras;
    opens com.fleetforyou.fleetforyou.Controllers.Extras to javafx.fxml;
    exports com.fleetforyou.fleetforyou.Controllers.Rents;
    opens com.fleetforyou.fleetforyou.Controllers.Rents to javafx.fxml;
    exports com.fleetforyou.fleetforyou.Controllers.Stands;
    opens com.fleetforyou.fleetforyou.Controllers.Stands to javafx.fxml;
    exports com.fleetforyou.fleetforyou.Controllers.Vehicles;
    opens com.fleetforyou.fleetforyou.Controllers.Vehicles to javafx.fxml;
    exports com.fleetforyou.fleetforyou.Infrastructure.Connection;
    opens com.fleetforyou.fleetforyou.Infrastructure.Connection to javafx.fxml;
}