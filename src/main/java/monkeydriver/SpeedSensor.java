package monkeydriver;

public interface SpeedSensor extends Sensor {
    @Override
    SpeedMessage publish();
}
