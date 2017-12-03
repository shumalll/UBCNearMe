package ui;

import com.teamdev.jxmaps.ControlPosition;
import com.teamdev.jxmaps.LatLng;
import com.teamdev.jxmaps.Map;
import com.teamdev.jxmaps.MapOptions;
import com.teamdev.jxmaps.MapReadyHandler;
import com.teamdev.jxmaps.MapStatus;
import com.teamdev.jxmaps.MapTypeControlOptions;
import com.teamdev.jxmaps.javafx.MapView;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class nearME extends Application{
    // Creation of a JavaFX map view
    final MapView mapView = new MapView();
    private StackPane stackPane;
    private BorderPane border;
    private VBox selectionPanel;

    @Override
    public void start(final Stage primaryStage) {
        border = new BorderPane();
        selectionPanel = new VBox();

        primaryStage.setTitle("UBCNearMe");

        // Setting of a ready handler to MapView object. onMapReady will be called when map initialization is done and
        // the map object is ready to use. Current implementation of onMapReady customizes the map object.
        mapView.setOnMapReadyHandler(new MapReadyHandler() {
            @Override
            public void onMapReady(MapStatus status) {
                // Check if the map is loaded correctly
                if (status == MapStatus.MAP_STATUS_OK) {
                    // Getting the associated map object
                    final Map map = mapView.getMap();
                    // Creating a map options object
                    MapOptions options = new MapOptions();
                    // Creating a map type control options object
                    MapTypeControlOptions controlOptions = new MapTypeControlOptions();
                    // Changing position of the map type control
                    controlOptions.setPosition(ControlPosition.TOP_RIGHT);
                    // Setting map type control options
                    options.setMapTypeControlOptions(controlOptions);
                    // Setting map options
                    map.setOptions(options);
                    // Setting the map center
                    map.setCenter(new LatLng(49.261178, -123.248804));
                    // Setting initial zoom value
                    map.setZoom(17.0);
                }
            }
        });

        selectionPanel = new VBox();
        selectionPanel.setPadding(new Insets(10));
        selectionPanel.setSpacing(8);

        Text title = new Text("Resources");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        selectionPanel.getChildren().add(title);

        CheckBox options[] = new CheckBox[]{
                new CheckBox("Washroom"),
                new CheckBox("Water Fountain"),
                new CheckBox("Study Room"),
                new CheckBox("Vending Machine")
        };

        for (int i=0; i<4; i++) {
            VBox.setMargin(options[i], new Insets(0, 0, 0, 8));
            selectionPanel.getChildren().add(options[i]);
        }

        border.setCenter(mapView);
        border.setRight(selectionPanel);

        Scene scene = new Scene(border, 700, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
