package ru.weeks;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class IsoWeekDateServiceTest {

    private final IsoWeekDateService service = new IsoWeekDateService();

    @Test
    @DisplayName("Проверка корректного форматирования для обычного случая")
    void testFormatWeekInfo_Success() {
        int year = 2023;
        int week = 50;

        String result = service.formatWeekInfo(year, week);

        String expected = "Понедельник: 2023-12-11, Воскресенье: 2023-12-17";
        assertEquals(expected, result, "Формат вывода должен строго соответствовать шаблону");
    }

    @Test
    @DisplayName("Проверка корректного форматирования для граничного случая")
    void testFormatWeekInfo_YearBoundary() {
        String result = service.formatWeekInfo(2024, 1);
        String expected = "Понедельник: 2024-01-01, Воскресенье: 2024-01-07";
        assertEquals(expected, result);
    }


    @ParameterizedTest(name = "Невалидный год: {0}")
    @ValueSource(ints = {0, 10000})
    @DisplayName("Должен выбрасывать исключение при невалидном годе")
    void testFormatWeekInfo_InvalidYear(int invalidYear) {
        assertThrows(IllegalArgumentException.class,
                () -> service.formatWeekInfo(invalidYear, 10),
                "Невалидный год (вне 1-9999) должен вызывать исключение");
    }

    @ParameterizedTest(name = "Невалидная неделя: {0}")
    @ValueSource(ints = {0, 54, -1})
    @DisplayName("Должен выбрасывать исключение при невалидном номере недели")
    void testFormatWeekInfo_InvalidWeekNumber(int invalidWeek) {
        assertThrows(IllegalArgumentException.class,
                () -> service.formatWeekInfo(2023, invalidWeek),
                "Невалидный номер недели (вне 1-53) должен вызывать исключение");
    }
}