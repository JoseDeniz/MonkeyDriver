package monkeydriver;

import org.junit.Before;
import org.junit.Test;

import static java.lang.Thread.sleep;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CarAheadSpeedSensorShould {

    private ListBus bus;
    private final int ONE_SECOND = 1000;

    @Before
    public void setUp() throws Exception {
        bus = new ListBus();
    }

    @Test
    public void return_speed_of_the_front_car_having_its_relative_position_in_one_second() throws InterruptedException {

        CarAheadSpeedSensor carAheadSpeedSensor = new CarAheadSpeedSensor();
        bus.subscribe(carAheadSpeedSensor);

        CarAheadDistanceSensor carAheadDistanceSensor = mock(CarAheadDistanceSensor.class);
        when(carAheadDistanceSensor.publish()).thenReturn(() -> "2", () -> "4");

        SpeedSensor speedSensor = mock(SpeedSensor.class);
        when(speedSensor.publish()).thenReturn(() -> "50", () -> "50");

        bus.send(carAheadDistanceSensor.publish());
        bus.send(speedSensor.publish());
        sleep(ONE_SECOND);
        bus.send(carAheadDistanceSensor.publish());
        bus.send(speedSensor.publish());

        assertThat(carAheadSpeedSensor.publish().get(), is("52.0"));
    }

}