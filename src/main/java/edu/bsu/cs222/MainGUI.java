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
    private int currentSteamPrice = 0;
    private int lowSteamPrice = 0;
    private int currentGogPrice = 0;
    private int lowGogPrice = 0;

    public void start(Stage primaryStage) {
        searchVbox.getChildren().addAll(searchTextField, searchButton, searchLabel);
        priceTab.setClosable(false);
        searchTab.setClosable(false);
        searchTab.setContent(searchVbox);
        tabPane.getTabs().add(searchTab);
        tabPane.getTabs().add(priceTab);
        Scene scene = new Scene(tabPane, SCENE_WIDTH, SCENE_HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Steam Game Stats");
        checkEmptyTextField();
        searchButton.setOnAction((event) -> runSearch());
        primaryStage.show();
    }

    private void checkEmptyTextField(){
        searchButton.setDisable(true);
        searchTextField.textProperty().addListener((ov, t, t1) -> searchButton.setDisable(searchTextField.getText().equals("")));
    }

    private void runSearch() {
        searchButton.setDisable(true);
        searchTextField.setDisable(true);
        String gameName = searchTextField.getText();
        executor.execute(() -> {
            currentSteamPrice = APIDataGetter.currentPriceData(gameName, "steam");
            lowSteamPrice = APIDataGetter.historicalLowData(gameName, "steam");
            currentGogPrice = APIDataGetter.currentPriceData(gameName,"gog");
            lowGogPrice = APIDataGetter.currentPriceData(gameName,"gog");
            Platform.runLater(this::updateGamePrice);
        });
        searchButton.setDisable(false);
        searchTextField.setDisable(false);
    }

    private void updateGamePrice() {
        priceTab.setContent(makeGamePriceBarGraph());
        if (currentSteamPrice == -1||currentGogPrice == -1){
            searchLabel.setText("Price Data Incomplete");
        } else {
            searchLabel.setText("Price Tab Has Been Updated");
        }
    }

    private StackedBarChart<String, Number> makeGamePriceBarGraph() {
        CategoryAxis xAxis = new CategoryAxis();

        xAxis.setCategories(FXCollections.observableArrayList(Arrays.asList
                ("Steam", "gog"))); //list can be edited to include multiple stores in the future

        xAxis.setLabel("Store");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Price ($)");
        StackedBarChart<String, Number> storePriceSummaryChart = new StackedBarChart<>(xAxis, yAxis);
        storePriceSummaryChart.setTitle("Store Price Summary");
        storePriceSummaryChart.setCategoryGap(SCENE_WIDTH/3);
        XYChart.Series<String, Number> lowestPriceSeries = new XYChart.Series<>();
        lowestPriceSeries.setName("Low");
        lowestPriceSeries.getData().add(new XYChart.Data<>("Steam", lowSteamPrice));
        lowestPriceSeries.getData().add(new XYChart.Data<>("gog",lowGogPrice));
        XYChart.Series<String, Number> currentPriceSeries = new XYChart.Series<>();
        currentPriceSeries.setName("Current");
        currentPriceSeries.getData().add(new XYChart.Data<>("Steam", currentSteamPrice-lowSteamPrice));
        currentPriceSeries.getData().add(new XYChart.Data<>("gog", currentGogPrice-lowGogPrice));
        storePriceSummaryChart.getData().addAll(lowestPriceSeries,currentPriceSeries);
        return storePriceSummaryChart;
    }
}