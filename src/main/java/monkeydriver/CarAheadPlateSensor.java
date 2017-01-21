package monkeydriver;

public interface CarAheadPlateSensor extends Sensor {
    @Override
    CarAheadPlateMessage publish();
}
