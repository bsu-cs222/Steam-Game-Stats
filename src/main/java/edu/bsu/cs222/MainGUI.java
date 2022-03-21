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

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Scanner;
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

    private void makeRedirectButton(ArrayList<String> gameNameArrayList) {
        if (gameNameArrayList.size() == 1) {
            runSearch();
        } else {
            Label redirectLabel = new Label("Did you mean:");
            searchVbox.getChildren().add(redirectLabel);
            for (String gameName : gameNameArrayList) {
                Button button = new Button(gameName);
                searchVbox.getChildren().add(button);
                button.setOnAction((event) -> runSearch());
            }
        }
    }

    private void runSearch() {
        searchButton.setDisable(true);
        searchTextField.setDisable(true);
        executor.execute(() -> {
            Platform.runLater(this::updateGamePrice);
        });
    }

    public static void main(String[] args) {
        MainGUI steamSearch = new MainGUI();
        Scanner scanner = new Scanner(System.in);
        String gameName = scanner.nextLine();
        try {
            String plains = steamSearch.getLatestRevisionOf(gameName);
            System.out.println();
        } catch (IOException ioException) {
            System.err.println("Network connection problem: " + ioException.getMessage());
        }
    }

    private String getLatestRevisionOf(String gameName) throws IOException {
        String urlString = String.format("https://isthereanydeal.com/#/page:game/info?plain=monsterhunterrise", gameName);
        String encodedUrlString = URLEncoder.encode(urlString, Charset.defaultCharset());
        try {
            URL url = new URL(encodedUrlString);
            URLConnection connection = url.openConnection();
            connection.setRequestProperty("User-Agent", "Steam-Game-Stats/0.1");
            InputStream inputStream = connection.getInputStream();
            SteamSearch parser = new SteamSearch();
            String plains = parser.parse(inputStream);
            return plains;
        } catch (MalformedURLException malformedURLException) {
            throw new RuntimeException(malformedURLException);
        }
    }

    private void updateGamePrice() {
        makeGamePriceChart();
        searchButton.setDisable(false);
        searchTextField.setDisable(false);
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

