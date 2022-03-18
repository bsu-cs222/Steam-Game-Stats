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

import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainGUI extends Application {
    private final Executor executor = Executors.newSingleThreadExecutor();
    private final TabPane tabPane = new TabPane();
    private final Tab searchTab = new Tab("Search");
    private final Tab priceTab = new Tab("Price", new Label("Price information available:"));
    private final TextField textField = new TextField();
    private final Button searchButton = new Button("Search");
    private final Label searchLabel = new Label("Search a Steam Game");
    private String gamePrice;

    public void start(Stage primaryStage) {
        searchTab.setContent(searchLabel);
        searchTab.setContent(searchButton);
        tabPane.getTabs().add(searchTab);
        tabPane.getTabs().add(priceTab);
        VBox vBox = new VBox(tabPane);
        Scene scene = new Scene(vBox,800,600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Steam Game Stats");
        ArrayList<Integer> samplePriceHistory = new ArrayList<>();
        samplePriceHistory.add(60);
        samplePriceHistory.add(45);
        makeGamePriceChart();
        primaryStage.show();
    }

    private void makeRedirectButton(ArrayList<String> gameNameArrayList) {
        for (String gameName : gameNameArrayList) {
            Button button = new Button(gameName);
            searchTab.setContent(button);
        }
    }

    private void runSearch() {
        searchButton.setDisable(true);
        textField.setDisable(true);
        executor.execute(() -> {
            String gameName = textField.getText();
            /*try {
                gameStat = GameStat.getPriceHistory;
            } catch (IOException ioException) {
                gameStat = "There was network error\n" + ioException.getMessage();
            }*/
            Platform.runLater(this::updateGamePrice);
        });
    }

    private void updateGamePrice() {
        searchLabel.setText(gamePrice);
        searchButton.setDisable(false);
        textField.setDisable(false);
    }

    private void makeGamePriceChart() {
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Number of Month");
        yAxis.setLabel("Price ($)");
        final LineChart<Number,Number> priceHistoryLineChart = new LineChart<Number,Number>(xAxis,yAxis);

        priceHistoryLineChart.setTitle("Price History");
        XYChart.Series steamSeries = new XYChart.Series();
        steamSeries.setName("Steam");
        steamSeries.getData().add(new XYChart.Data(1, 23));
        steamSeries.getData().add(new XYChart.Data(2, 14));
        steamSeries.getData().add(new XYChart.Data(2, 15));
        steamSeries.getData().add(new XYChart.Data(4, 24));
        steamSeries.getData().add(new XYChart.Data(4, 34));
        steamSeries.getData().add(new XYChart.Data(6, 36));
        steamSeries.getData().add(new XYChart.Data(6, 22));
        steamSeries.getData().add(new XYChart.Data(8, 45));
        steamSeries.getData().add(new XYChart.Data(8, 43));
        steamSeries.getData().add(new XYChart.Data(10, 17));
        steamSeries.getData().add(new XYChart.Data(10, 29));
        steamSeries.getData().add(new XYChart.Data(12, 25));

        /*for (int numberOfPriceChanges = 0; numberOfPriceChanges <= priceHistoryArrayList.size(); numberOfPriceChanges++) {
            steamSeries.getData().add(new XYChart.Data(numberOfPriceChanges, priceHistoryArrayList.get(numberOfPriceChanges)));
        }*/

        priceHistoryLineChart.getData().add(steamSeries);
        priceTab.setContent(priceHistoryLineChart);
    }
}

