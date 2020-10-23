package ru.skillfactory.actions;

import ru.skillfactory.*;

/**
 * Класс для реализации действия "Показать баланс", используется в StartUI.
 */
public class ShowBalanceAction implements UserAction {

    @Override
    public String getTitle() {
        return "Показать баланс";
    }

    /**
     * Метод обращается к банковскому сервису, печатает текущий баланс пользователя.
     *
     * @param bankService BankService объект.
     * @param input       Input объект.
     * @param requisite   Строка в произвольной форме, используется для поиска пользователя.
     * @return возвращает всегда true, приложение продолжает работать.
     */
    @Override
    public boolean execute(BankService bankService, Input input, String requisite) {

        Long balance = bankService.balance(requisite);
        System.out.println(balance + " рублей");
        return true;
    }
}
