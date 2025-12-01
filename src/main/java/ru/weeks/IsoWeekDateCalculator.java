package ru.weeks;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.WeekFields;

/**
 * Предоставляет статические методы для вычисления дат (Понедельник и Воскресенье)
 * по году и номеру недели в соответствии со стандартом ISO 8601.
 */
public class IsoWeekDateCalculator {
    /**
     * Вычисляет дату понедельника для указанного года и номера недели по стандарту ISO 8601.
     * Используется 4 января как начальная точка для корректной установки недельного года.
     *
     * @param year Год, основанный на неделе.
     * @param weekNumber Номер недели (от 1 до 52/53).
     * @return Объект LocalDate, представляющий понедельник.
     */
    public static LocalDate calculateMonday(int year, int weekNumber) {
        return LocalDate.of(year, 1, 4)
                .with(WeekFields.ISO.weekBasedYear(), year)
                .with(WeekFields.ISO.weekOfWeekBasedYear(), weekNumber)
                .with(WeekFields.ISO.dayOfWeek(), DayOfWeek.MONDAY.getValue());
    }

    /**
     * Вычисляет дату воскресенья для указанного года и номера недели.
     *
     * @param year Год, основанный на неделе.
     * @param weekNumber Номер недели.
     * @return Объект LocalDate, представляющий воскресенье.
     */
    public static LocalDate calculateSunday(int year, int weekNumber) {
        return calculateMonday(year, weekNumber).plusDays(6);
    }
}