package ru.skillfactory;

/**
 * Интерфейс для ввода.
 */
public interface Input {

    /**
     * Спросить и получить ответ строкой.
     *
     * @param question печатаем вопрос и возвращаем ответ.
     * @return возвращает ответ типом String.
     */
    String askStr(String question);

    /**
     * Спросить и получить ответ строкой. Метод сделан для форматирования Username (приводит к большой букве и убирает пробелы случайные)
     *
     * @param question печатаем вопрос и возвращаем ответ.
     * @return возвращает ответ типом String.
     */

    String askUsername(String question);

    /**
     * Спросить и получить ответ интом.
     *
     * @param question печатаем вопрос и возвращаем ответ.
     * @return возвращает ответ типом int.
     */
    int askInt(String question);

    /**
     * Спросить и получить ответ типом long.
     *
     * @param question печатаем вопрос и возвращаем ответ.
     * @return возвращает ответ типом long.
     */
    long askLong(String question);
}
