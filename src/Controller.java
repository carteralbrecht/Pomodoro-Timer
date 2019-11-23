import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import java.util.*;

class Pomodoro
{
    private static final String timerFormat = "%02d:%02d";
    private int secondsLeft;
    private int workPeriod;
    private int shortBreak;
    private int longBreak;
    private int cycleCount = 0;
    private Timer timer = null;
    StringProperty currentTime = new SimpleStringProperty();

    Pomodoro(int workPeriodInMinutes, int shortBreakInMinutes, int longBreakInMinutes)
    {
        this.workPeriod = workPeriodInMinutes;
        this.shortBreak = shortBreakInMinutes;
        this.longBreak = longBreakInMinutes;
        this.secondsLeft = workPeriod;
        updateCurrentTime();
    }

    private void tick()
    {
        if (--secondsLeft == 0)
            setupNextCycle();

        updateCurrentTime();
    }

    public void start()
    {
        if (timer != null)
            return;

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

    public void stopAndReset()
    {
        timer.cancel();
        resetSeconds();
        timer = null;
        updateCurrentTime();
    }

    private void updateCurrentTime()
    {
        this.currentTime.setValue(String.format(timerFormat, secondsLeft / 60, secondsLeft % 60));
    }

    private void setupNextCycle()
    {
        cycleCount++;
        resetSeconds();
    }

    private void resetSeconds()
    {
        if (cycleCount % 3 == 0)
            secondsLeft = workPeriod;
        if (cycleCount % 3 == 1)
            secondsLeft = shortBreak;
        if (cycleCount % 3 == 2)
            secondsLeft = longBreak;
    }
}

public class Controller
{
    @FXML
    private Button startButton;

    @FXML
    private Button stopButton;

    @FXML
    private Text timerText;

    private Pomodoro pomodoro = null;

    private void startTimer(int workPeriodInMinutes, int shortBreakInMinutes, int longBreakInMinutes)
    {
        if (pomodoro != null)
            return;
        pomodoro = new Pomodoro(workPeriodInMinutes, shortBreakInMinutes, longBreakInMinutes);
        timerText.textProperty().bind(pomodoro.currentTime);
        pomodoro.start();
    }
    
    public void onStartButtonClick()
    {
        startTimer(10, 3, 7 );
    }

    private void stopTimer()
    {
        pomodoro.stopAndReset();
    }

    public void onStopButtonClick()
    {
        stopTimer();
    }
}
