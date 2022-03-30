package edu.bsu.cs222;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainGUI extends Application {
    public static final int SCENE_WIDTH = 800;
    public static final int SCENE_HEIGHT = 600;
    private final Executor executor = Executors.newSingleThreadExecutor();
    private final TabPane tabPane = new TabPane();
    private final Tab searchTab = new Tab("Search");
    private final Tab priceTab = new Tab("Price", new Label("No price information available"));
    private final VBox searchVbox = new VBox();
    private final TextField searchTextField = new TextField();
    private final Button searchButton = new Button("Search");
    private final Label searchLabel = new Label("Search a Steam Game");
    private int currentSteamPrice;

    public void start(Stage primaryStage) {
        searchVbox.getChildren().addAll(searchTextField, searchButton, searchLabel);
        searchTab.setContent(searchVbox);
        tabPane.getTabs().add(searchTab);
        tabPane.getTabs().add(priceTab);
        Scene scene = new Scene(tabPane, SCENE_WIDTH, SCENE_HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Steam Game Stats");
        checkEmptyTextField();
        String gameName = searchTextField.getText();
        searchButton.setOnAction((event) -> runSearch(gameName));
        primaryStage.show();
    }

    private void checkEmptyTextField(){
        searchButton.setDisable(true);
        searchTextField.textProperty().addListener((ov, t, t1) -> searchButton.setDisable(searchTextField.getText().equals("")));
    }

    private void runSearch(String gameName) {
        searchButton.setDisable(true);
        searchTextField.setDisable(true);
        executor.execute(() -> {
            currentSteamPrice = Integer.parseInt(APIDataGetter.urlMaker(gameName));
            Platform.runLater(this::updateGamePrice);
        });
        searchButton.setDisable(false);
        searchTextField.setDisable(false);
    }

    private void updateGamePrice() {
        priceTab.setContent(makeGamePriceBarGraph());
        searchLabel.setText("Price tab has been updated");

    }

    private StackedBarChart<String, Number> makeGamePriceBarGraph() {
        CategoryAxis xAxis = new CategoryAxis();

        xAxis.setCategories(FXCollections.observableArrayList(Arrays.asList
                ("Steam"))); //list can be edited to include multiple stores in the future

        xAxis.setLabel("Store");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Price ($)");
        StackedBarChart<String, Number> storePriceSummaryChart = new StackedBarChart<>(xAxis, yAxis);
        storePriceSummaryChart.setTitle("Store Price Summary");
        storePriceSummaryChart.setCategoryGap(500); //TODO make this value change with the size of the window
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName("Current");
        series1.getData().add(new XYChart.Data<>("Steam", currentSteamPrice));
        storePriceSummaryChart.getData().add(series1);
        return storePriceSummaryChart;
    }
}

