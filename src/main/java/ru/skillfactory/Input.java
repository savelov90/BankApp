package ru.skillfactory;

/**
 * Простенький интерфейс для ввода.
 */
public interface Input {

    /**
     * Спросить и получить ответ строкой.
     *
     * @param question печатаем вопрос и возвращаем ответ. Не печатайте пустоту,
     *                 метод должен работать в связке с вопросом.
     * @return возвращает ответ типом String.
     */
    String askStr(String question);

    String askUsername(String question);

    /**
     * Спросить и получить ответ интом.
     *
     * @param question печатаем вопрос и возвращаем ответ. Не печатайте пустоту,
     *                 метод должен работать в связке с вопросом.
     * @return возвращает ответ типом int.
     */
    int askInt(String question);

    /**
     * Спросить и получить ответ типом long.
     *
     * @param question печатаем вопрос и возвращаем ответ. Не печатайте пустоту,
     *                 метод должен работать в связке с вопросом.
     * @return возвращает ответ типом long.
     */
    long askLong(String question);
}
