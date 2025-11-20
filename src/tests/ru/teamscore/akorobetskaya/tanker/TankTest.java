package ru.teamscore.akorobetskaya.tanker;

import org.junit.jupiter.api.Test;
import ru.teamscore.akorobetskaya.tanker.exceptions.IllegalVolumeException;
import ru.teamscore.akorobetskaya.tanker.exceptions.NotEnoughVolumeException;
import ru.teamscore.akorobetskaya.tanker.exceptions.VolumeOverflowException;

import static org.junit.jupiter.api.Assertions.*;

class TankTest {
    @Test
    void ctorCorrectVolume() {
        double maxVolume = 100;
        var tank = new Tank(maxVolume);

        assertEquals(maxVolume, tank.getMaxVolume());

        assertEquals(0, tank.getCurrentVolume());
    }

    @Test
    void ctorWrongVolume() {
        double maxVolume = -1.5;

        IllegalVolumeException ex = assertThrows(IllegalVolumeException.class,
                () -> new Tank(maxVolume));

        assertEquals(maxVolume, ex.getVolume());
    }

    @Test
    void addTooMuch() {
        double maxVolume = 100;
        double currentVolume = 90;
        double addVolume = 15;
        Tank tank = new Tank(maxVolume);

        tank.add(currentVolume);

        VolumeOverflowException ex = assertThrows(VolumeOverflowException.class,
                () -> tank.add(addVolume));
        assertEquals(addVolume, ex.getVolume());
        assertEquals(currentVolume, ex.getCurrentVolume());
        assertEquals(maxVolume, ex.getMaxVolume());
    }

    @Test
    void takeTooMuch() {
        double maxVolume = 100;
        double currentVolume = 30;
        double takeVolume = 50;
        Tank tank = new Tank(maxVolume);

        tank.add(currentVolume);

        NotEnoughVolumeException ex = assertThrows(NotEnoughVolumeException.class,
                () -> tank.take(takeVolume));
        assertEquals(takeVolume, ex.getVolume());
        assertEquals(currentVolume, ex.getCurrentVolume());
    }
}