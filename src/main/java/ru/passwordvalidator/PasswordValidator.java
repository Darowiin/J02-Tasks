package ru.passwordvalidator;

import java.util.ArrayList;
import java.util.List;

/** Проверка пароля на сложность.
 * Пароль должен отвечать следующим требованиям:
 * - не менее 8 символов в длину
 * - содержит строчные, заглавные буквы и цифры
 * - не должен совпадать с именем пользователя
 * - не должен содержать пробельных символов, табуляции и кавычек
 */
public class PasswordValidator {
    private static final String MSG_TOO_SHORT = "Пароль должен содержать не менее 8 символов.";
    private static final String MSG_NO_DIGIT = "Пароль должен содержать хотя бы одну цифру.";
    private static final String MSG_NO_LOWER = "Пароль должен содержать хотя бы одну строчную букву.";
    private static final String MSG_NO_UPPER = "Пароль должен содержать хотя бы одну заглавную букву.";
    private static final String MSG_USERNAME_MATCH = "Пароль не должен совпадать с именем пользователя.";
    private static final String MSG_CONTAINS_FORBIDDEN = "Пароль не должен содержать пробелы, табуляцию или кавычки (\").";

    /**
     * Проверка валидности пароля
     * @param password пароль
     * @param userName имя пользователя
     * @return возвращает true, если пароль отвечает всем требованиям
     */
    public static boolean isValidPassword(String password, String userName) {
        return validatePassword(password, userName).isValid();
    }

    /**
     * Проверяет валидность пароля и возвращает результат, содержащий список всех ошибок.
     * @param password пароль
     * @param userName имя пользователя
     * @return ValidationResult, содержащий статус валидности и список ошибок
     */
    public static ValidationResult validatePassword(String password, String userName) {
        List<String> errorMessages = new ArrayList<>();

        if (password.length() < 8) {
            errorMessages.add(MSG_TOO_SHORT);
        }
        if (!hasDigits(password)) {
            errorMessages.add(MSG_NO_DIGIT);
        }
        if (!hasLowercase(password)) {
            errorMessages.add(MSG_NO_LOWER);
        }
        if (!hasUppercase(password)) {
            errorMessages.add(MSG_NO_UPPER);
        }
        if (password.equals(userName)) {
            errorMessages.add(MSG_USERNAME_MATCH);
        }
        if (hasSpacesOrQuotes(password)) {
            errorMessages.add(MSG_CONTAINS_FORBIDDEN);
        }

        return new ValidationResult(errorMessages.isEmpty(), errorMessages);
    }

    private static boolean hasDigits(String text) {
        for (char symbol : text.toCharArray()) {
            if (Character.isDigit(symbol)) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasLowercase(String text) {
        for (char symbol : text.toCharArray()) {
            if (Character.isLowerCase(symbol)) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasUppercase(String text) {
        for (char symbol : text.toCharArray()) {
            if (Character.isUpperCase(symbol)) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasSpacesOrQuotes(String text) {
        for (char symbol : text.toCharArray()) {
            if (Character.isSpaceChar(symbol)
                    || symbol == '\t' || symbol == '"') {
                return true;
            }
        }
        return false;
    }

    /**
     * Запись (record) для хранения результата проверки.
     * @param isValid логический статус валидности
     * @param errorMessages список сообщений об ошибках (пустой, если isValid == true)
     */
    public record ValidationResult(boolean isValid, List<String> errorMessages) {}
}