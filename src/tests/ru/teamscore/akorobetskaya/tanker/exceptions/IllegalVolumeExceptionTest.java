package ru.teamscore.akorobetskaya.tanker.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IllegalVolumeExceptionTest {
    @Test
    void getMessage() {
        double volume = -10.33;
        var ex = new IllegalVolumeException(volume);
        assertEquals("Недопустимое значение объема: -10.33", ex.getMessage());
    }
}