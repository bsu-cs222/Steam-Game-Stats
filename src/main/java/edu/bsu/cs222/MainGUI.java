package edu.bsu.cs222;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainGUI extends Application {
    private final Executor executor = Executors.newSingleThreadExecutor();
    private final TabPane tabPane = new TabPane();
    private final Tab searchTab = new Tab("Search");
    private final Tab priceTab = new Tab("Price", new Label("Price information available:"));
    private final VBox searchVbox = new VBox();
    private final TextField searchTextField = new TextField();
    private final Button searchButton = new Button("Search");
    private final Label searchLabel = new Label("Search a Steam Game");

    public void start(Stage primaryStage) {
        searchVbox.getChildren().addAll(searchTextField, searchButton, searchLabel);
        searchTab.setContent(searchVbox);
        tabPane.getTabs().add(searchTab);
        tabPane.getTabs().add(priceTab);
        VBox vBox = new VBox(tabPane);
        Scene scene = new Scene(vBox,800,600);
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
        executor.execute(() -> Platform.runLater(this::updateGamePrice));
        searchButton.setDisable(false);
        searchTextField.setDisable(false);
    }

    private void updateGamePrice() {
        makeGamePriceChart();
        searchLabel.setText("Price tab has been updated");
    }

    private void makeGamePriceChart() {
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Month");
        yAxis.setLabel("Price ($)");
        final LineChart<Number,Number> priceHistoryLineChart = new LineChart<>(xAxis, yAxis);

        priceHistoryLineChart.setTitle("Price History");
        XYChart.Series steamSeries = new XYChart.Series();
        steamSeries.setName("Steam");
        //sample graph based on Fallout 4 TODO make a for loop to cycle through game price changes and make data points
        steamSeries.getData().add(new XYChart.Data(-12, 29.99));
        steamSeries.getData().add(new XYChart.Data(-11.75, 29.99));
        steamSeries.getData().add(new XYChart.Data(-11.75, 14.99));
        steamSeries.getData().add(new XYChart.Data(-11.5, 14.99));
        steamSeries.getData().add(new XYChart.Data(-11.5, 29.99));
        steamSeries.getData().add(new XYChart.Data(-10.5, 29.99));
        steamSeries.getData().add(new XYChart.Data(-10.5, 14.99));
        steamSeries.getData().add(new XYChart.Data(-10, 14.99));
        steamSeries.getData().add(new XYChart.Data(-10, 29.99));
        steamSeries.getData().add(new XYChart.Data(-9.5, 29.99));
        steamSeries.getData().add(new XYChart.Data(-9.5, 11.99));
        steamSeries.getData().add(new XYChart.Data(-8, 11.99));
        steamSeries.getData().add(new XYChart.Data(-8, 29.99));
        steamSeries.getData().add(new XYChart.Data(-7.9, 29.99));
        steamSeries.getData().add(new XYChart.Data(-7.9, 19.99));
        steamSeries.getData().add(new XYChart.Data(-7, 19.99));
        steamSeries.getData().add(new XYChart.Data(-7, 7.99));
        steamSeries.getData().add(new XYChart.Data(-6, 7.99));
        steamSeries.getData().add(new XYChart.Data(-6, 19.99));
        steamSeries.getData().add(new XYChart.Data(-4.75, 19.99));
        steamSeries.getData().add(new XYChart.Data(-4.75, 4.99));
        steamSeries.getData().add(new XYChart.Data(-4, 4.99));
        steamSeries.getData().add(new XYChart.Data(-4, 19.99));
        steamSeries.getData().add(new XYChart.Data(-3, 19.99));
        steamSeries.getData().add(new XYChart.Data(-3, 7.99));
        steamSeries.getData().add(new XYChart.Data(-2.75, 7.99));
        steamSeries.getData().add(new XYChart.Data(-2.75, 19.99));
        steamSeries.getData().add(new XYChart.Data(-2, 19.99));
        steamSeries.getData().add(new XYChart.Data(-2, 7.99));
        steamSeries.getData().add(new XYChart.Data(-1.5, 7.99));
        steamSeries.getData().add(new XYChart.Data(-1.5, 19.99));
        steamSeries.getData().add(new XYChart.Data(-1, 19.99));
        steamSeries.getData().add(new XYChart.Data(-1, 7.99));
        steamSeries.getData().add(new XYChart.Data(-.75, 7.99));
        steamSeries.getData().add(new XYChart.Data(-.75, 19.99));
        steamSeries.getData().add(new XYChart.Data(0, 19.99));

        priceHistoryLineChart.getData().add(steamSeries);
        priceTab.setContent(priceHistoryLineChart);
    }
}

