package com.cartera;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

public class Pomodoro
{
    private static final Media sound = new Media(new File("./ding.mp3").toURI().toString());
    private static final String timerFormat = "%02d:%02d";
    private final int workPeriod;
    private final int shortBreak;
    private final int longBreak;
    private int secondsLeft;
    private int periodCount = 0;
    private boolean isWorkPeriod = false;
    private Timer timer;
    public boolean isRunning = false;
    StringProperty currentTime = new SimpleStringProperty();

    Pomodoro(int workPeriodInMinutes, int shortBreakInMinutes, int longBreakInMinutes)
    {
        this.workPeriod = workPeriodInMinutes * 60;
        this.shortBreak = shortBreakInMinutes * 60;
        this.longBreak = longBreakInMinutes * 60;
    }

    private void tick()
    {
        if (--secondsLeft == 0)
        {
            //MediaPlayer mediaPlayer = new MediaPlayer(sound);
            //mediaPlayer.play();
            setupNextCycle();
        }

        updateCurrentTime();
    }

    public void start()
    {
        if (isRunning)
            return;

        isRunning = true;
        isWorkPeriod = true;
        this.secondsLeft = workPeriod;
        updateCurrentTime();

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask()
        {
            @Override
            public void run()
            {
                tick();
            }
        }, 1000, 1000);
    }

    public void reset()
    {
        timer.cancel();
        periodCount = 0;
        secondsLeft = 0;
        isRunning = false;
        updateCurrentTime();
    }

    private void updateCurrentTime()
    {
        this.currentTime.setValue(String.format(timerFormat, secondsLeft / 60, secondsLeft % 60));
    }

    private void setupNextCycle()
    {
        isWorkPeriod = !isWorkPeriod;
        resetSeconds();
    }

    private void resetSeconds()
    {
        if (isWorkPeriod)
        {
            secondsLeft = workPeriod;
        }
        else
        {
            periodCount++;
            if (periodCount == 4)
            {
                secondsLeft = longBreak;
                periodCount = 0;
            }
            else
                secondsLeft = shortBreak;
        }
    }
}
