package ru.passwordvalidator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PasswordValidatorTest {
    private static final String MSG_TOO_SHORT = "Пароль должен содержать не менее 8 символов.";
    private static final String MSG_NO_DIGIT = "Пароль должен содержать хотя бы одну цифру.";
    private static final String MSG_NO_LOWER = "Пароль должен содержать хотя бы одну строчную букву.";
    private static final String MSG_NO_UPPER = "Пароль должен содержать хотя бы одну заглавную букву.";
    private static final String MSG_USERNAME_MATCH = "Пароль не должен совпадать с именем пользователя.";
    private static final String MSG_CONTAINS_FORBIDDEN = "Пароль не должен содержать пробелы, табуляцию или кавычки (\").";

    private void testPassword(String password, String username, boolean result) {
        // Act
        boolean validationResult = PasswordValidator.isValidPassword(password, username);

        // Assert
        assertEquals(result, validationResult);
    }

    private void testPasswordErrors(String password, String username, List<String> expectedErrors) {
        PasswordValidator.ValidationResult validationResult = PasswordValidator.validatePassword(password, username);

        List<String> actualErrors = validationResult.errorMessages();

        boolean isValid = validationResult.isValid();
        assertEquals(expectedErrors.isEmpty(), isValid, "Статус isValid должен зависеть от наличия ошибок.");

        assertIterableEquals(expectedErrors, actualErrors, "Список ошибок должен точно соответствовать ожидаемому.");
    }

    @ParameterizedTest
    @DisplayName("Valid password is accepted")
    @ValueSource(strings =
            {"Test6789", "1R2g3T4k5Y", "0123456789aA", "_TestUser74"})
    public void validPassword(String password) {
        testPassword(password, "TestUser", true);
    }

    @ParameterizedTest
    @DisplayName("Password is less 8 symbols")
    @ValueSource(strings =
            {"abcdefg", "_ _ _ ", "1234", "1", ""})
    public void tooShortPassword(String password) {
        testPassword(password, "TestUser", false);
    }

    @ParameterizedTest
    @DisplayName("Password has no digits")
    @ValueSource(strings =
            {"asdfghjkl", "__TgTg__", "AbCdEfGhIjKlMn"})
    public void noDigitsPassword(String password) {
        testPassword(password, "TestUser", false);
    }

    @ParameterizedTest
    @DisplayName("Password has no lower case letters")
    @ValueSource(strings =
            {"QWERTYU2", "01234ABCD", "P@$$W0RD"})
    public void noLowerCasePassword(String password) {
        testPassword(password, "TestUser", false);
    }

    @ParameterizedTest
    @DisplayName("Password has no upper case letters")
    @ValueSource(strings =
            {"qwerty1234", ")(*&56nm", "password5"})
    public void noUpperCasePassword(String password) {
        testPassword(password, "TestUser", false);
    }

    @Test
    @DisplayName("Password equals username")
    public void equalsUsernamePassword() {
        String username = "TestUser1";
        testPassword(username, username, false);
    }

    @ParameterizedTest
    @DisplayName("Password has spaces or quotes")
    @ValueSource(strings =
            {"qwerty 1234", "_&*^\tBg79", "\"34jdfgER", " \t\r\n\"Rg6*"})
    public void hasSpacesQuotesPassword(String password) {
        testPassword(password, "TestUser", false);
    }

    @Test
    @DisplayName("Проверка на отсутствие ошибок")
    void shouldReturnEmptyListForValidPassword() {
        testPasswordErrors("Password123", "User", List.of());
    }

    @Test
    @DisplayName("Ошибка: Пароль слишком короткий")
    void shouldFailOnTooShort() {
        testPasswordErrors("Abc1", "User", List.of(MSG_TOO_SHORT));
    }

    @Test
    @DisplayName("Ошибка: Нет цифр")
    void shouldFailOnNoDigits() {
        testPasswordErrors("PasswordTest", "User", List.of(MSG_NO_DIGIT));
    }

    @Test
    @DisplayName("Ошибка: Нет строчных букв")
    void shouldFailOnNoLowercase() {
        testPasswordErrors("PASSWORD123", "User", List.of(MSG_NO_LOWER));
    }

    @Test
    @DisplayName("Ошибка: Нет заглавных букв")
    void shouldFailOnNoUppercase() {
        testPasswordErrors("password123", "User", List.of(MSG_NO_UPPER));
    }

    @Test
    @DisplayName("Ошибка: Совпадает с именем пользователя")
    void shouldFailOnMatchUsername() {
        testPasswordErrors("TestUser123", "TestUser123", List.of(MSG_USERNAME_MATCH));
    }

    @Test
    @DisplayName("Ошибка: Содержит запрещенные символы")
    void shouldFailOnForbiddenSymbols() {
        testPasswordErrors("Pass word1", "User", List.of(MSG_CONTAINS_FORBIDDEN));
    }

    @Test
    @DisplayName("Комбинированная ошибка: Длина, Цифры, Регистр")
    void shouldFailOnMultipleErrors() {
        testPasswordErrors("abc", "User", List.of(
                MSG_TOO_SHORT,
                MSG_NO_DIGIT,
                MSG_NO_UPPER
        ));
    }

    @Test
    @DisplayName(" Комбинированная ошибка: Все возможные ошибки")
    void shouldFailOnAllErrors() {
        String username = "TEST";
        String password = username;

        testPasswordErrors(password, username, List.of(
                MSG_TOO_SHORT,
                MSG_NO_DIGIT,
                MSG_NO_LOWER,
                MSG_USERNAME_MATCH
        ));
    }
}