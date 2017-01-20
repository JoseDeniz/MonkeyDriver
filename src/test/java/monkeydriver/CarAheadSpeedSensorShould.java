package monkeydriver;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CarAheadSpeedSensorShould {

    @Test
    public void return_speed_of_the_front_car_having_its_relative_position() {
        ListBus bus = new ListBus();

        Sensor carAheadSensor = sensor();
        when(carAheadSensor.publish()).thenReturn(() -> "2", () -> "4");

        Sensor plateSensor = sensor();
        when(plateSensor.publish()).thenReturn(() -> "asd123", () -> "asd123");

        Sensor speedSensor = sensor();
        when(speedSensor.publish()).thenReturn(() -> "50", () -> "50");

        bus.send(carAheadSensor.publish());
        bus.send(plateSensor.publish());
        bus.send(speedSensor.publish());

        CarAheadSpeedSensor carAheadSpeedSensor = carAheadSpeedSensor();
        bus.subscribe(carAheadSpeedSensor);
        Message message = carAheadSpeedSensor.publish();
        bus.send(message);

        //verify(carAheadSpeedSensor).receive();

        // Vr = (4 - 2) / 1
        // V = 50 + 2 = 52
        assertThat(message.get(), is("52"));
    }

    private Sensor sensor() {
        return mock(Sensor.class);
    }

    private CarAheadSpeedSensor carAheadSpeedSensor() {
        return new CarAheadSpeedSensor() {
            @Override
            public Message publish() {
                return () -> "";
            }

            @Override
            public void receive(Message message) {

            }
        };
    }

}