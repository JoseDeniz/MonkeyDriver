package monkeydriver;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Float.parseFloat;

class CarAheadSpeedSensor implements Subscriber, Sensor {

    private final List<Message> messages;

    CarAheadSpeedSensor() {
        messages = new ArrayList<>();
    }

    @Override
    public Message publish() {
        return () -> String.valueOf(constantSpeed() + relativeDistance());
    }

    @Override
    public void receive(Message message) {
        messages.add(0, message);
    }

    private Float constantSpeed() {
        return messages.stream()
                .filter(m -> m instanceof SpeedMessage)
                .map(m -> parseFloat(m.get()))
                .findFirst().orElse(.0f);
    }

    private Float relativeDistance() {
        return messages.stream()
                .filter(m -> m instanceof CarAheadDistanceMessage)
                .map(m -> parseFloat(m.get()))
                .reduce((acc, nextValue) -> acc - nextValue)
                .orElse(.0f);
    }
}
