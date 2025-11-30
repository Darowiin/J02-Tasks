package ru.vat;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Класс для выполнения расчетов налога на добавленную стоимость (НДС, VAT).
 * Реализует логику округления сумм до копеек (чек) и до рублей (декларация).
 */
public class Vat {
    /** Ставка НДС, 20% (0.20). */
    private static final BigDecimal VAT_RATE = new BigDecimal("0.20");

    /** Множитель для полной цены (1 + VAT_RATE), 1.20. */
    private static final BigDecimal VAT_MULTIPLIER = new BigDecimal("1.20");

    /** Неизменяемая стоимость товара без НДС. */
    private final BigDecimal priceWithoutVat;

    /**
     * Приватный конструктор, который используется фабричными методами.
     * @param priceWithoutVat Стоимость товара без НДС.
     */
    private Vat(BigDecimal priceWithoutVat) {
        validatePrice(priceWithoutVat);
        this.priceWithoutVat = priceWithoutVat;
    }

    /**
     * Проверяет, что переданная цена не является отрицательной.
     * @param price Проверяемая цена.
     * @throws IllegalArgumentException Если цена меньше нуля.
     */
    private static void validatePrice(BigDecimal price) {
        if (price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Цена не может быть отрицательной");
        }
    }

    /**
     * Фабричный метод для создания объекта Vat на основе цены, не включающей НДС.
     * @param price Стоимость товара без НДС.
     * @return Новый экземпляр Vat.
     */
    public static Vat fromPriceWithoutVat(BigDecimal price) {
        return new Vat(price);
    }

    /**
     * Фабричный метод для создания объекта Vat на основе цены, включающей НДС.
     * Выполняет обратный расчет цены без НДС с высокой точностью (15 знаков).
     * @param price Стоимость товара с НДС.
     * @return Новый экземпляр Vat.
     */
    public static Vat fromPriceWithVat(BigDecimal price) {
        validatePrice(price);

        BigDecimal calculatedPriceWithoutVat = price.divide(
                VAT_MULTIPLIER, 15, RoundingMode.HALF_UP
        );

        return new Vat(calculatedPriceWithoutVat);
    }

    /**
     * Возвращает полную стоимость товара, включая НДС, округленную до копеек.
     * @return Полная стоимость с НДС (два знака после запятой).
     */
    public BigDecimal getPriceWithVat() {
        return priceWithoutVat.multiply(VAT_MULTIPLIER)
                .setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * Возвращает стоимость товара без НДС, округленную до копеек.
     * @return Стоимость без НДС (два знака после запятой).
     */
    public BigDecimal getPriceWithoutVat() {
        return priceWithoutVat
                .setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * Возвращает сумму НДС, округленную до копеек (для чеков).
     * @return Сумма НДС для чека (два знака после запятой).
     */
    public BigDecimal getVatForReceipt() {
        return priceWithoutVat.multiply(VAT_RATE)
                .setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * Возвращает сумму НДС, округленную до рублей (для налоговой декларации).
     * Сумма 50 коп. и более округляется вверх.
     * @return Сумма НДС для декларации.
     */
    public BigDecimal getVatForDeclaration() {
        return priceWithoutVat.multiply(VAT_RATE)
                .setScale(0, RoundingMode.HALF_UP);
    }
}