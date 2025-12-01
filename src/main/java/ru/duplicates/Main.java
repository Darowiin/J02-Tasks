package ru.duplicates;

import ru.duplicates.exceptions.AlreadyExistsException;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            DuplicateInputCheck inputCheck = new DuplicateInputCheck();
            String input;

            System.out.println("Проверка выбрасывания исключения при повторном вводе одинаковой строки");
            System.out.println("Введите текст построчно. Для выхода введите 'Выход'");

            while (true) {
                System.out.print("Ввод #" + inputCheck.getCounter() + ": ");

                input = scanner.nextLine();

                if (input.equalsIgnoreCase("Выход")) {
                    System.out.println("Программа завершена по команде 'Выход'.");
                    break;
                }

                inputCheck.addInput(input);
            }
        }
        catch (AlreadyExistsException ex) {
            System.err.println("Сообщение: " + ex.getMessage());
            System.err.println("Дубликат: '" + ex.getValue() + "'");
            System.err.println("Введен ранее под номером: " + ex.getPosition());
        }
    }
}
