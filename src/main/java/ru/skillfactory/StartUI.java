package ru.skillfactory;

import ru.skillfactory.actions.*;

import java.util.Optional;

/**
 * Класс, который запускает общение с пользователем приложения.
 */
public class StartUI {

    /**
     * Инициализация меню, вы
     * 1. Авторизация пользователя в методе authorization.
     * 2. Выводится меню для пользователя меню.
     * 3. В зависимости от введённого числа запускается нужная функция меню.
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
     * Метод работает, пока пользователь не авторизуется.
     *
     * @param bankService BankService объект.
     * @param input       Input объект.
     * @return возвращает реквизиты аккаунта, под которым авторизовался пользователь.
     * Получаю их вызывом метода getRequisiteIfPresent, класса BankService.
     */
    public static String authorization(BankService bankService, Input input) {

        String requisite = null;
        boolean authComplete = false;
        while (!authComplete) {
            /*
             * Запрашивается у пользователя логин, пароль пока он не пройдёт авторизацию.
             * Авторизация пройдена при условии что в BankService есть пользователь с
             * данным логином и паролем.
             */
            String login = input.askUsername("Ваш логин: ");
            String password = input.askStr("Ваш password: ");
            Optional<String> optional = bankService.getRequisiteIfPresent(login, password);

            if (optional.isPresent()) {
                requisite = optional.get();
                authComplete = true;
            } else {
                authComplete = false;
                System.out.println("Неправильный логин или пароль, попробуйте еще раз ");
            }

        }
        System.out.println("Авторизация выполнена успешно");
        return requisite;
    }

    /**
     * Печатается меню пользователя.
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
        // создаем несколько аккаунтов на проверку
        bankService.addAccount(new BankAccount("Евгений", "Qwerty", "1111", 150L));
        bankService.addAccount(new BankAccount("Андрей", "Aaaaaa", "1112", 1502L));
        bankService.addAccount(new BankAccount("Борис", "Qwerty1", "1113", 10000L));
        bankService.addAccount(new BankAccount("Михаил", "Qwerty12", "1114", 10L));
        bankService.addAccount(new BankAccount("Катерина", "Qwerty123", "1115", 1000L));
        bankService.addAccount(new BankAccount("Елена", "Qwer", "1116", 1L));
        bankService.addAccount(new BankAccount("Аркадий", "Qwert", "1117", 10L));

        // В массиве хранятся объекты, которые представляют наши действия.
        UserAction[] actions = {
                new ShowBalanceAction(),
                new TopUpBalanceAction(),
                new TransferToAction(),
                new Exit()
        };
        // Input изменен на ValidateInput, который проверяет правильность введенных данных
        Input input = new ValidateInput();
        // Запускаем наш UI передавая аргументами банковский сервис, экшены и Input.
        new StartUI().init(bankService, actions, input);
    }
}
