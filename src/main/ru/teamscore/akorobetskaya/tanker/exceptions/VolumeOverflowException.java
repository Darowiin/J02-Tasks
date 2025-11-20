package ru.teamscore.akorobetskaya.tanker.exceptions;

public class VolumeOverflowException extends RuntimeException {
    private final double maxVolume;
    private final double currentVolume;
    private final double volume;

    public VolumeOverflowException(double maxVolume, double currentVolume, double volume) {
        super("Переполнение цистерны на " + (currentVolume + volume - maxVolume) + "л");
        this.maxVolume = maxVolume;
        this.currentVolume = currentVolume;
        this.volume = volume;
    }

    public double getMaxVolume() {
        return maxVolume;
    }

    public double getCurrentVolume() {
        return currentVolume;
    }

    public double getVolume() {
        return volume;
    }
}
