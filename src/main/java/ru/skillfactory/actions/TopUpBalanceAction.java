package ru.skillfactory.actions;

import ru.skillfactory.*;

/**
 * Класс для реализации действия "Пополнить баланс", используется в StartUI.
 */
public class TopUpBalanceAction implements UserAction {

    @Override
    public String getTitle() {
        return "Пополнить баланс";
    }

    /**
     * Метод обращается к банковскому сервису, уточняет у пользователя на сколько он хочет
     * пополнить баланс, печатает баланс после пополнения.
     *
     * @param bankService BankService объект.
     * @param input       Input объект.
     * @param requisite   Строка в произвольной форме, используется для поиска пользователя.
     * @return возвращает всегда true, приложение продолжает работать.
     */
    @Override
    public boolean execute(BankService bankService, Input input, String requisite) {

        Long amount = input.askLong("Введите сумму: "); //дописать проверку введенного пользователем типа
        boolean done = bankService.topUpBalance(requisite, amount);
        if (done = true) {
            System.out.println("Операция выполнена успешно ");
        } else {
            System.out.println("Ошибка пополнения баланса ");
        }
        return true;
    }
}
