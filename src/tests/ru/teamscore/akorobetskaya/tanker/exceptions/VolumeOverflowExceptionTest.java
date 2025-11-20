package ru.teamscore.akorobetskaya.tanker.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VolumeOverflowExceptionTest {
    @Test
    void getMessage() {
        double maxVolume = 100000;
        double currentVolume = 0;
        double volume = maxVolume + currentVolume + 10;
        var ex = new VolumeOverflowException(maxVolume, currentVolume, volume);
        assertEquals("Переполнение цистерны на 10.0л", ex.getMessage());
    }
}