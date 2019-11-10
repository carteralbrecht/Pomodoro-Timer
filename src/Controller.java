import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.sql.Time;

class CountDownTimer
{
    private static final String timerFormat = "%02d:%02d";
    private static final Integer STARTMINUTES = 0;
    private static final Integer STARTSECONDS = 5;
    private Integer minutesField = STARTMINUTES;
    private Integer secondsField = STARTSECONDS;
    private Integer secondsLeft = (STARTMINUTES * 60) + STARTSECONDS;

    public void tick()
    {
        secondsLeft--;
        minutesField = secondsLeft / 60;
        secondsField = secondsLeft % 60;
    }

    public void stopAndReset()
    {
        minutesField = STARTMINUTES;
        secondsField = STARTSECONDS;
        secondsLeft = (STARTMINUTES * 60) + STARTSECONDS;
    }

    public String getCurrentTime()
    {
        return String.format(timerFormat, minutesField, secondsField);
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

    private CountDownTimer timer = new CountDownTimer();
    private Timeline timeline = null;

    public void startTimer()
    {
        if (timeline != null)
            return;

        timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), actionEvent ->
        {
            timer.tick();
            timerText.setText(timer.getCurrentTime());
        }));

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.playFromStart();
    }

    public void stopTimer()
    {
        if (timeline == null)
            return;
        timeline.stop();
        timeline = null;
        timer.stopAndReset();
        timerText.setText(timer.getCurrentTime());
    }
}
