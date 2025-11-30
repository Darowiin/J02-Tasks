package ru.months;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedClass;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class MonthInfoTest {
    @Nested
    @DisplayName("Проверка корректности методов для конструктора без параметров")
    class CurrentDateTests {
        private final MonthInfo monthInfo = new MonthInfo();
        private final Locale ruLocale = new Locale("ru", "RU");

        @Test
        @DisplayName("Проверка получения названия месяца для текущей даты")
        void testGetMonthName() {
            String expectedMonthName = LocalDate.now().getMonth().getDisplayName(TextStyle.FULL_STANDALONE, ruLocale);

            assertEquals(expectedMonthName, monthInfo.getMonthName());
        }

        @Test
        @DisplayName("Проверка получения значения месяца для текущей даты")
        void testGetMonthValue() {
            int expectedMonthValue = LocalDate.now().getMonthValue();

            assertEquals(expectedMonthValue, monthInfo.getMonthValue());
        }

        @Test
        @DisplayName("Проверка получения дня недели первого дня месяца для текущей даты")
        void testGetFirstDayOfMonthInWeek() {
            String expectedFirstDayOfMonth = LocalDate.now().withDayOfMonth(1).getDayOfWeek().getDisplayName(TextStyle.SHORT, ruLocale);

            assertEquals(expectedFirstDayOfMonth, monthInfo.getFirstDayOfMonthInWeek());
        }

        @Test
        @DisplayName("Проверка получения даты последнего дня месяца для текущей даты")
        void testGetLastDayDate() {
            LocalDate expectedDate =  LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth());

            assertEquals(expectedDate, monthInfo.getLastDayDate());
        }

        @Test
        @DisplayName("Проверка получения количества дней в месяце для текущей даты")
        void testGetLengthOfMonth() {
            int expectedLength = LocalDate.now().lengthOfMonth();

            assertEquals(expectedLength, monthInfo.getLengthOfMonth());
        }

        @Test
        @DisplayName("Проверка получения строки квартала (YYYY QN) для текущей даты")
        void testGetQuarterString() {
            int year = LocalDate.now().getYear();
            int month = LocalDate.now().getMonthValue();
            int quarter = (month - 1) / 3 + 1;

            String expectedQuarterString = year + " Q" + quarter;

            assertEquals(expectedQuarterString, monthInfo.getYearWithQuarter());
        }
    }

    @Nested
    @DisplayName("Проверка для конкретных дат")
    @ParameterizedClass
    @CsvSource({
            "2023-11-12, ноябрь, 11, 30, 2023 Q4, 2023-11-30",
            "1900-01-01, январь, 1, 31, 1900 Q1, 1900-01-31",
            "2020-02-12, февраль, 2, 29, 2020 Q1, 2020-02-29"
    })
    class SpecificDateTests {
        private final LocalDate testDate;
        private final String expectedMonthName;
        private final int expectedMonthValue;
        private final int expectedLength;
        private final String expectedQuarter;
        private final LocalDate expectedLastDayDate;

        private final MonthInfo monthInfo;
        private final Locale ruLocale = new Locale("ru", "RU");

        public SpecificDateTests(
                String dateString,
                String expectedMonthName,
                int expectedMonthValue,
                int expectedLength,
                String expectedQuarter,
                String expectedLastDayDateString
        ) {
            this.testDate = LocalDate.parse(dateString);
            this.expectedMonthName = expectedMonthName;
            this.expectedMonthValue = expectedMonthValue;
            this.expectedLength = expectedLength;
            this.expectedQuarter = expectedQuarter;
            this.expectedLastDayDate = LocalDate.parse(expectedLastDayDateString);

            this.monthInfo = new MonthInfo(this.testDate);
        }

        @Test
        @DisplayName("Проверка получения названия месяца")
        void testGetMonthName() {
            assertEquals(expectedMonthName, monthInfo.getMonthName());
        }

        @Test
        @DisplayName("Проверка получения значения месяца")
        void testGetMonthValue() {
            assertEquals(expectedMonthValue, monthInfo.getMonthValue());
        }

        @Test
        @DisplayName("Проверка получения дня недели первого дня месяца")
        void testGetFirstDayOfMonthInWeek() {
            String expectedFirstDayOfWeek = testDate.withDayOfMonth(1).getDayOfWeek().getDisplayName(TextStyle.SHORT, ruLocale);
            assertEquals(expectedFirstDayOfWeek, monthInfo.getFirstDayOfMonthInWeek());
        }

        @Test
        @DisplayName("Проверка получения даты последнего дня месяца")
        void testGetLastDayDate() {
            assertEquals(expectedLastDayDate, monthInfo.getLastDayDate());
        }

        @Test
        @DisplayName("Проверка получения количества дней в месяце")
        void testGetLengthOfMonth() {
            assertEquals(expectedLength, monthInfo.getLengthOfMonth());
        }

        @Test
        @DisplayName("Проверка получения строки квартала (YYYY QN) для текущей даты")
        void testGetQuarterString() {
            assertEquals(expectedQuarter, monthInfo.getYearWithQuarter());
        }
    }
}
