package ru.teamscore.akorobetskaya.tanker.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotEnoughVolumeExceptionTest {
    @Test
    void getMessage() {
        double currentVolume = 10;
        double volume = 50;
        var ex = new NotEnoughVolumeException(currentVolume, volume);
        assertEquals("Недостаточный объем для выдачи, не хватает 40.0л", ex.getMessage());

    }
}