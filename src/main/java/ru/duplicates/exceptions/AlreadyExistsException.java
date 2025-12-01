package ru.duplicates.exceptions;

/**
 * Исключение выбрасывается, когда значение, переданное в систему,
 * уже было введено ранее.
 */
public class AlreadyExistsException extends Exception {
    /** Хранит дублирующееся значение, вызвавшее исключение. */
    private final String value;

    /** Хранит порядковый номер ввода, под которым было сохранено исходное значение. */
    private final int position;

    /**
     * Создает новый экземпляр исключения с информацией о дубликате.
     * @param value Дублирующееся строковое значение.
     * @param position Позиция (номер ввода) исходного значения.
     */
    public AlreadyExistsException(String value, int position) {
        super("Значение '" + value + "' уже было введено под номером " + position);
        this.value = value;
        this.position = position;
    }

    /**
     * Возвращает дублирующееся строковое значение.
     * @return Дублирующееся значение.
     */
    public String getValue() {
        return value;
    }

    /**
     * Возвращает порядковый номер ввода, под которым было сохранено исходное значение.
     * @return Позиция исходного значения.
     */
    public int getPosition() {
        return position;
    }
}