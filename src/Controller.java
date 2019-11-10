import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Duration;

public class Controller
{
    @FXML
    private Button startButton;

    @FXML
    private Label timer;

    private static final Integer STARTTIME = 25;
    private IntegerProperty timeSeconds = new SimpleIntegerProperty(STARTTIME);

    public void startTimer()
    {
        timer.textProperty().bind(timeSeconds.asString());
        Timeline timeline = new Timeline();
        timeSeconds.set(STARTTIME);
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(STARTTIME + 1), new KeyValue(timeSeconds, 0)));
        timeline.playFromStart();
    }
}
