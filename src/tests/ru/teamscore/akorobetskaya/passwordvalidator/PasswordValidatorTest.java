package ru.teamscore.akorobetskaya.passwordvalidator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class PasswordValidatorTest {

    @ParameterizedTest
    @DisplayName("Valid password is accepted")
    @ValueSource(strings =
            {"Test6789", "1R2g3T4k5Y", "0123456789aA", "_TestUser74"})
    public void validPassword(String password) {
        // Arrange
        String username = "TestUser";

        // Act
        boolean validationResult = PasswordValidator.isValidPassword(password, username);

        // Assert
        assertEquals(true, validationResult);
    }

    @ParameterizedTest
    @DisplayName("Valid password is less 8 symbols")
    @ValueSource(strings =
            {"abcdefg", "_ _ _ ", "1234", "1", ""})
    public void tooShortPassword(String password) {
        // Arrange
        String username = "TestUser";

        // Act
        boolean validationResult = PasswordValidator.isValidPassword(password, username);

        // Assert
        assertEquals(false, validationResult);
    }
}