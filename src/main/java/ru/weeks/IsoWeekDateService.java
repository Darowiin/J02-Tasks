package ru.weeks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Сервис для обработки входных данных, валидации и форматирования вывода
 * дат, рассчитанных по ISO-неделям.
 */
public class IsoWeekDateService {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;

    /**
     * Валидирует входные параметры и форматирует вывод понедельника и воскресенья.
     *
     * @param year Год.
     * @param weekNumber Номер недели.
     * @return Строка в формате: "Понедельник: YYYY-MM-DD, Воскресенье: YYYY-MM-DD"
     * @throws IllegalArgumentException Если год или номер недели невалидны.
     */
    public String formatWeekInfo(int year, int weekNumber) {
        if (year < 1 || year > 9999) {
            throw new IllegalArgumentException("Год должен быть в диапазоне 1-9999.");
        }
        if (weekNumber < 1 || weekNumber > 53) {
            throw new IllegalArgumentException("Номер недели должен быть от 1 до 53.");
        }

        LocalDate monday = IsoWeekDateCalculator.calculateMonday(year, weekNumber);
        LocalDate sunday = IsoWeekDateCalculator.calculateSunday(year, weekNumber);

        return String.format(
                "Понедельник: %s, Воскресенье: %s",
                monday.format(FORMATTER),
                sunday.format(FORMATTER)
        );
    }
}