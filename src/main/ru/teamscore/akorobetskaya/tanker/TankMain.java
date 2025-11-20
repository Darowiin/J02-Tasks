package ru.teamscore.akorobetskaya.tanker;

import ru.teamscore.akorobetskaya.tanker.exceptions.NotEnoughVolumeException;
import ru.teamscore.akorobetskaya.tanker.exceptions.VolumeOverflowException;

import java.util.Random;

public class TankMain {
    static final Random rnd = new Random(100);
    public static void main(String[] args) {
        Tank tank = new Tank(1000);

        while (true) {
            try {
                tank.add(getRandomVolume());
            } catch (VolumeOverflowException ex) {
                System.err.println(ex.getMessage());
                break;
            } finally {
                System.out.printf("Текущий объем: %.2fл\n", tank.getCurrentVolume());
            }
        }

        while (true) {
            try {
                tank.take(getRandomVolume());
            } catch (NotEnoughVolumeException ex) {
                System.err.println(ex.getMessage());
                break;
            } finally {
                System.out.printf("Текущий объем: %.2fл\n", tank.getCurrentVolume());
            }
        }
    }

    private static double getRandomVolume() {
        return Math.rint(rnd.nextDouble(5000, 20000)) / 100.0;
    }
}
