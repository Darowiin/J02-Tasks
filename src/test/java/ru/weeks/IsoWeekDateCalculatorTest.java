package ru.weeks;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IsoWeekDateCalculatorTest {

    @ParameterizedTest(name = "Год={0}, Неделя={1} -> Понедельник: {2}")
    @CsvSource({
            "2023, 50, 2023-12-11",
            "2023, 1, 2023-01-02",
            "2020, 53, 2020-12-28",
            "2024, 1, 2024-01-01",
            "2025, 52, 2025-12-22"
    })
    @DisplayName("Проверка расчета Понедельника (calculateMonday)")
    void testCalculateMonday(int year, int weekNumber, LocalDate expectedMonday) {
        LocalDate actualMonday = IsoWeekDateCalculator.calculateMonday(year, weekNumber);
        assertEquals(expectedMonday, actualMonday, "Понедельник должен соответствовать ISO-неделе");
    }

    @ParameterizedTest(name = "Год={0}, Неделя={1} -> Воскресенье: {2}")
    @CsvSource({
            "2023, 50, 2023-12-17",
            "2020, 53, 2021-01-03",
            "2024, 1, 2024-01-07"
    })
    @DisplayName("Проверка расчета Воскресенья (calculateSunday)")
    void testCalculateSunday(int year, int weekNumber, LocalDate expectedSunday) {
        LocalDate actualSunday = IsoWeekDateCalculator.calculateSunday(year, weekNumber);
        assertEquals(expectedSunday, actualSunday, "Воскресенье должно быть на 6 дней позже Понедельника");
    }
}