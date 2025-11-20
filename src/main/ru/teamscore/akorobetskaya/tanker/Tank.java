package ru.teamscore.akorobetskaya.tanker;

import ru.teamscore.akorobetskaya.tanker.exceptions.IllegalVolumeException;
import ru.teamscore.akorobetskaya.tanker.exceptions.NotEnoughVolumeException;
import ru.teamscore.akorobetskaya.tanker.exceptions.VolumeOverflowException;

/**
 * Цистерна
 */
public class Tank {
    private final double maxVolume;
    private double currentVolume;

    /**
     * @param maxVolume максимальный объем цистерны в литрах
     */
    public Tank(double maxVolume) {
        if (maxVolume < 0) {
            throw new IllegalVolumeException(maxVolume);
        }
        this.maxVolume = maxVolume;
    }

    /**
     * Долить заданный объем в цистерну.
     * @param volume сколько литров долить
     */
    public void add(double volume) {
        if (volume < 0) {
            throw new IllegalVolumeException(volume);
        }
        if (currentVolume + volume > maxVolume) {
            throw new VolumeOverflowException(maxVolume, currentVolume, volume);
        }
        currentVolume += volume;
    }

    /**
     * Слить заданный объем из цистерны.
     * @param volume сколько литров слить
     */
    public void take(double volume) {
        if (volume < 0) {
            throw new IllegalVolumeException(volume);
        }
        if (currentVolume - volume < 0) {
            throw new NotEnoughVolumeException(currentVolume, volume);
        }
        currentVolume -= volume;
    }

    public double getMaxVolume() {
        return maxVolume;
    }

    public double getCurrentVolume() {
        return currentVolume;
    }

}
