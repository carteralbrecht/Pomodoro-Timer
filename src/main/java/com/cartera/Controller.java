package com.cartera;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import java.util.*;

public class Controller
{
    @FXML
    private Button startButton;

    @FXML
    private Button resetButton;

    @FXML
    private Text timerText;

    private Pomodoro pomodoro = null;

    // Leave in parameters for possible future customization
    private void startTimer(int workPeriodInMinutes, int shortBreakInMinutes, int longBreakInMinutes)
    {
        if (pomodoro == null)
        {
            pomodoro = new Pomodoro(workPeriodInMinutes, shortBreakInMinutes, longBreakInMinutes);
            timerText.textProperty().bind(pomodoro.currentTime);
        }

        if (!pomodoro.isRunning)
        {
            pomodoro.start();
        }
    }
    
    public void onStartButtonClick()
    {
        startTimer(15, 3, 10);
    }

    private void resetTimer()
    {
        if (pomodoro.isRunning)
            pomodoro.reset();
    }

    public void onResetButtonClick()
    {
        resetTimer();
    }
}
