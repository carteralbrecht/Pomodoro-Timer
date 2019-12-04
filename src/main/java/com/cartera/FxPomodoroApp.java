package com.cartera;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class FxPomodoroApp extends Application
{
    @Override
    public void start(Stage stage) throws Exception
    {
        stage.initStyle(StageStyle.UTILITY);
        stage.setResizable(false);

        Parent root = FXMLLoader.load(getClass().getResource("/Pomodoro.fxml"));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
