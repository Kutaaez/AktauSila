package org.example.application;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.view.screens.MainMenuView;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) {
        MainMenuView menuView = new MainMenuView(primaryStage);
        primaryStage.setScene(menuView.getScene());
        primaryStage.setTitle("Toguz Kumalak");
        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}