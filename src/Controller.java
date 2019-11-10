import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.util.Duration;

class CountDownTimer
{
    private static final String timerFormat = "%02d:%02d";
    private static final Integer STARTMINUTES = 25;
    private static final Integer STARTSECONDS = 0;

    private Integer minutesField;
    private Integer secondsField;

    private Integer secondsLeft;

    CountDownTimer()
    {
        minutesField = STARTMINUTES;
        secondsField = STARTSECONDS;
        secondsLeft = (minutesField * 60) + secondsField;
    }

    public void update(Text timer)
    {
        secondsLeft--;
        minutesField = secondsLeft / 60;
        secondsField = secondsLeft % 60;
        timer.setText(getCurrentTime());
    }

    private String getCurrentTime()
    {
        return String.format(timerFormat, minutesField, secondsField);
    }
}

public class Controller
{
    @FXML
    private Button startButton;

    @FXML
    private Text timer;

    public void startTimer()
    {
        CountDownTimer counter = new CountDownTimer();
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), actionEvent -> counter.update(timer)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.playFromStart();
    }
}
