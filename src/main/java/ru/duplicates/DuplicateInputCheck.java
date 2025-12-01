package ru.duplicates;

import ru.duplicates.exceptions.AlreadyExistsException;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс для проверки ввода пользователя на дублирование.
 * Использует HashMap для хранения истории введенных строк и их порядковых номеров.
 */
public class DuplicateInputCheck {

    /**
     * Карта для хранения истории ввода, где ключ - введенная строка (String),
     * а значение - порядковый номер ввода (Integer), начиная с 1.
     */
    private final Map<String, Integer> inputHistory;

    /** Счетчик, отслеживающий текущий порядковый номер ввода.
     * Используется как позиция для нового элемента и увеличивается после добавления.
     */
    private int counter;

    /**
     * Конструктор инициализирует хранилище истории и устанавливает счетчик ввода на 1.
     */
    public DuplicateInputCheck() {
        inputHistory = new HashMap<>();
        counter = 1;
    }

    /**
     * Проверяет, была ли строка {@code input} введена ранее.
     * Если строка новая, она добавляется в историю с текущим порядковым номером.
     * @param input Строка, вводимая пользователем.
     * @throws AlreadyExistsException Если {@code input} уже содержится в {@code inputHistory}.
     */
    public void addInput(String input) throws AlreadyExistsException {
        if (inputHistory.containsKey(input)) {
            throw new AlreadyExistsException(input, inputHistory.get(input));
        }

        inputHistory.put(input, counter);
        counter++;
    }

    /**
     * Возвращает текущий порядковый номер, который будет присвоен следующему вводимому значению.
     * @return Текущее значение счетчика.
     */
    public int getCounter() {
        return counter;
    }
}