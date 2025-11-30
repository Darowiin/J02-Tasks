package ru.months;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * Класс для получения краткой информации о месяце для заданной даты.
 */
public class MonthInfo {
    private final LocalDate date;

    /**
     * Создаёт объект с указанной датой.
     * @param date дата, по которой определяется месяц
     */
    public MonthInfo(LocalDate date) {
        this.date = date;
    }

    /**
     * Создаёт объект для текущей даты.
     */
    public MonthInfo() {
        this.date = LocalDate.now();
    }

    /**
     * Возвращает полное русское название месяца.
     * @return название месяца на русском языке
     */
    public String getMonthName() {
        return date.getMonth().getDisplayName(TextStyle.FULL_STANDALONE, Locale.forLanguageTag("ru-RU"));
    }

    /**
     * Возвращает числовой номер месяца в году.
     * @return номер месяца от 1 до 12
     */
    public int getMonthValue() {
        return date.getMonth().getValue();
    }

    /**
     * Возвращает сокращённое русское название дня недели для первого дня месяца.
     * @return строка вида "пн", "вт", ...
     */
    public String getFirstDayOfMonthInWeek() {
        return date.withDayOfMonth(1).getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.forLanguageTag("ru-RU"));
    }

    /**
     * Возвращает дату последнего дня месяца.
     * @return LocalDate последнего дня месяца
     */
    public LocalDate getLastDayDate() {
        return date.withDayOfMonth(date.lengthOfMonth());
    }

    /**
     * Возвращает количество дней в месяце.
     * @return длина месяца в днях
     */
    public int getLengthOfMonth() {
        return date.lengthOfMonth();
    }

    /**
     * Возвращает строку с годом и номером квартала для текущей даты.
     * @return строка формата "YYYY QN"
     */
    public String getYearWithQuarter() {
        int month = date.getMonthValue();
        int quarter = ((month - 1) / 3) + 1;
        int year = date.getYear();

        return String.format("%d Q%d", year, quarter);
    }
}