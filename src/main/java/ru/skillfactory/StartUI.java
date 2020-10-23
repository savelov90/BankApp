package ru.skillfactory;

import ru.skillfactory.actions.*;

import java.util.Optional;

/**
 * Класс, который запускает общение с пользователем.
 */
public class StartUI {

    /**
     * Здесь будет происходить инициализация меню, вы
     * 1. Авторизовываете пользователя.
     * 2. Печатаете меню.
     * 3. В зависимости от введённого числа запускаете нужную функцию.
     *
     * @param bankService BankService объект.
     * @param actions     массив с действиями.
     * @param input       Input объект.
     */
    public void init(BankService bankService, UserAction[] actions, Input input) {
        String requisite = authorization(bankService, input);
        showMenu(actions);
        boolean run = true;
        while (run) {
            int select = input.askInt("Выберите пункт меню: ");
            // Здесь такой if, который не даст выйти в ArrayIndexOutOfBoundsException.
            if (select >= 0 && select <= actions.length - 1) {
                // Мы по индексу массива вызываем метод execute нашего Action-объекта.
                run = actions[select].execute(bankService, input, requisite);
            } else {
                System.out.println("Неправильный пункт меню");
            }
        }
    }


    /**
     * Метод должен работать пока пользователь не авторизуется (пока отключил цикл!).
     *
     * @param bankService BankService объект.
     * @param input       Input объект.
     * @return возвращает реквизиты аккаунта, под которым авторизовался пользователь.
     * Получайте их вызывом метода getRequisiteIfPresent, класса BankService.
     */
    public static String authorization(BankService bankService, Input input) {

        String rsl = null;
        boolean authComplete = false;
        while (!authComplete) { // цикл отключён!!!
            /*
             * Запрашиваете у пользователя логин, пароль пока он не пройдёт авторизацию.
             * Авторизация пройдена при условие что в BankService есть пользователь с
             * данным логином и паролем (работайте только с теми пользователями что есть).
             */
            String login = input.askUsername("Ваш логин: ");
            String password = input.askStr("Ваш password: ");
            Optional<String> optional = bankService.getRequisiteIfPresent(login, password);

            if (!optional.isEmpty()) {
                rsl = optional.get();
                authComplete = true;
            } else {
                authComplete = false;
                System.out.println("Неправильный логин или пароль, попробуйте еще раз ");
            }

        }
        System.out.println("Авторизация выполнена успешно");
        return rsl;
    }

    /**
     * Печатается меню пользователя (только печатается, общения с пользователем нету).
     *
     * @param actions массив с действиями.
     */
    private void showMenu(UserAction[] actions) {
        System.out.println("Menu.");
        for (int index = 0; index < actions.length; index++) {
            System.out.println(index + ". " + actions[index].getTitle());
        }
    }

    public static void main(String[] args) {
        BankService bankService = new BankService();
        // здесь создадите несколько аккаунтов на проверку
        // данные осмысленно заполните, не просто пустые строки

        bankService.addAccount(new BankAccount("Евгений", "Qwerty", "1111", 150L));
        bankService.addAccount(new BankAccount("Андрей", "Aaaaaa", "1112", 1502L));
        bankService.addAccount(new BankAccount("Борис", "Qwerty1", "1113", 10000L));
        bankService.addAccount(new BankAccount("Михаил", "Qwerty12", "1114", 10L));
        bankService.addAccount(new BankAccount("Катерина", "Qwerty123", "1115", 1000));
        bankService.addAccount(new BankAccount("Елена", "Qwer", "1116", 1L));
        bankService.addAccount(new BankAccount("Аркадий", "Qwert", "1117", 10L));

        // В массиве хранятся объекты, которые представляют наши действия.
        UserAction[] actions = {
                new ShowBalanceAction(),
                new TopUpBalanceAction(),
                new TransferToAction(),
                new Exit()
        };
        // Наш Input можно менять на нужную реализацию (ValidateInput доделайте)
        Input input = new ValidateInput();
        // Запускаем наш UI передавая аргументами банковский сервис, экшены и Input.
        new StartUI().init(bankService, actions, input);
    }
}
