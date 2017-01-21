package monkeydriver;

public interface CarAheadDistanceSensor extends Sensor {
    @Override
    CarAheadDistanceMessage publish();
}
