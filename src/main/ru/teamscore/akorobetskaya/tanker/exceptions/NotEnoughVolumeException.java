package ru.teamscore.akorobetskaya.tanker.exceptions;

public class NotEnoughVolumeException extends RuntimeException {
    private final double currentVolume;
    private final double volume;

    public NotEnoughVolumeException(double currentVolume, double volume) {
        super("Недостаточный объем для выдачи, не хватает " + (volume - currentVolume) + "л");
        this.currentVolume = currentVolume;
        this.volume = volume;
    }

    public double getCurrentVolume() {
        return currentVolume;
    }

    public double getVolume() {
        return volume;
    }
}