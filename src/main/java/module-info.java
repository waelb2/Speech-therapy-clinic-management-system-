module com.example.tp_poo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;


    opens com.example.tp_poo to javafx.fxml;
    exports com.example.tp_poo;
    exports Controllers.userControllers;
    opens Controllers.userControllers to javafx.fxml;
    exports Controllers.dashboardControllers;
    opens Controllers.dashboardControllers to javafx.fxml;
    exports Controllers.patientsControllers;
    opens Controllers.patientsControllers to javafx.fxml;
    exports  Controllers.statsControllers;
    opens  Controllers.statsControllers;
    exports Controllers.rdvControllers;
    opens  Controllers.rdvControllers to javafx.fxml;
    exports Controllers.settingsControllers;
    opens  Controllers.settingsControllers;
    exports Controllers.testsAnamsControllers;
    opens  Controllers.testsAnamsControllers;
}