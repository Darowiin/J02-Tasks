package ru.vat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class VatTest {

    @Nested
    @DisplayName("Тесты валидации")
    class ValidationTests {

        @ParameterizedTest(name = "Цена без НДС: {0}")
        @ValueSource(strings = {"-100", "-0.01", "-123.45"})
        @DisplayName("Должен бросать исключение при отрицательной цене без НДС")
        void shouldThrowExceptionWhenPriceWithoutVatIsNegative(String negativePrice) {
            BigDecimal price = new BigDecimal(negativePrice);
            assertThrows(IllegalArgumentException.class, () -> Vat.fromPriceWithoutVat(price));
        }

        @ParameterizedTest(name = "Цена с НДС: {0}")
        @ValueSource(strings = {"-100", "-0.01", "-123.45"})
        @DisplayName("Должен бросать исключение при отрицательной цене с НДС")
        void shouldThrowExceptionWhenPriceWithVatIsNegative(String negativePrice) {
            BigDecimal price = new BigDecimal(negativePrice);
            assertThrows(IllegalArgumentException.class, () -> Vat.fromPriceWithVat(price));
        }
    }

    @Nested
    @DisplayName("Тесты инициализации ценой без НДС")
    class FromPriceWithoutVatTests {

        @ParameterizedTest(name = "Базовая цена: {1}")
        @CsvSource({
                "120.00,100.00,20.00,20",
                "1.20,1.00,0.20,0",
                "5.00,4.17,0.83,1",
                "0.01,0.01,0.00,0",
                "119.99,99.99,20.00,20",
                "120.49,100.41,20.08,20"
        })
        @DisplayName("Проверка всех расчетов при задании цены без НДС")
        void checkCalculationsWhenPriceWithoutVatIsSet(String expectedWithVat, String priceBase, String expectedVatReceipt, String expectedVatDeclaration) {
            BigDecimal base = new BigDecimal(priceBase);
            Vat vat = Vat.fromPriceWithoutVat(base);

            assertEquals(new BigDecimal(expectedWithVat), vat.getPriceWithVat(), "Полная стоимость с НДС");
            assertEquals(new BigDecimal(expectedVatReceipt), vat.getVatForReceipt(), "Сумма НДС в чеке");
            assertEquals(new BigDecimal(expectedVatDeclaration), vat.getVatForDeclaration(), "Сумма НДС для налоговой декларации");

            assertEquals(base.setScale(2, RoundingMode.HALF_UP), vat.getPriceWithoutVat(), "Стоимость без НДС");
        }
    }

    @Nested
    @DisplayName("Тесты инициализации ценой с НДС")
    class FromPriceWithVatTests {

        @ParameterizedTest(name = "Полная цена: {0}")
        @CsvSource(delimiter = '|', value = {
                "120.00|100.00|20.00|20",
                "121.00|100.83|20.17|20",
                "6.00|5.00|1.00|1",
                "5.99|4.99|1.00|1",
                "1.00|0.83|0.17|0"
        })
        @DisplayName("Проверка всех расчетов при задании цены С НДС")
        void checkCalculationsWhenPriceWithVatIsSet(String priceWithVat, String expectedPriceWithoutVat, String expectedVatReceipt, String expectedVatDeclaration) {
            BigDecimal priceFull = new BigDecimal(priceWithVat);
            Vat vat = Vat.fromPriceWithVat(priceFull);

            assertEquals(new BigDecimal(expectedPriceWithoutVat), vat.getPriceWithoutVat(), "База без НДС");
            assertEquals(new BigDecimal(expectedVatReceipt), vat.getVatForReceipt(), "НДС для чека");
            assertEquals(new BigDecimal(expectedVatDeclaration), vat.getVatForDeclaration(), "НДС для декларации");

            assertEquals(priceFull.setScale(2, RoundingMode.HALF_UP), vat.getPriceWithVat(), "Полная цена с НДС (проверка целостности)");
        }
    }
}