package ru.teamscore.akorobetskaya.tanker.exceptions;

public class IllegalVolumeException extends RuntimeException {
    private final double volume;

    public IllegalVolumeException(double volume) {
        super("Недопустимое значение объема: " + volume);
        this.volume = volume;
    }

    public double getVolume() {
        return volume;
    }
}
