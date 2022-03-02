package edu.bsu.cs222;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MainGUI extends Application{
    public void start(Stage primaryStage) {

        TabPane tabPane = new TabPane();

        Tab SearchTab = new Tab("Search", new Label("Enter a game to search"));
        Tab PriceTab = new Tab("Price"  , new Label("Price information available:"));

        tabPane.getTabs().add(SearchTab);
        tabPane.getTabs().add(PriceTab);

        VBox vBox = new VBox(tabPane);
        Scene scene = new Scene(vBox);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Steam Game Ranker");

        primaryStage.show();
    }
}

