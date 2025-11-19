package ru.teamscore.akorobetskaya.percentage;

public class PercentCalculator {
    /**
     * Расчет доли от целого с ограничением [0; 1]
     * @param share Доля (числитель)
     * @param whole Целое (знаменатель)
     * @return Доля от 0 до 1 (0-100%)
     */
    public static double limitedPercent(double share, double whole) {
        if (whole < 0) {
            return Double.NaN;
        }
        var percent = share / whole;
        return percent < 0 ? 0 :
                percent > 1 ? 1 :
                        percent;
    }
}
