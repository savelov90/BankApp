package ru.skillfactory;

/**
 * Этот класс обновлённая версия вашего консольного ввода, здесь не просто спрашивайте ввод, а проверяйте его.
 * Главная задача данного класса решить проблему ошибок ввода пользователем.
 *
 * Метод askInt должен точно возвращать число и не генерировать exceptions.
 * Требования к askStr сформулируйте сами или не переопределяйте его если с ним проблем не увидите.
 */
public class ValidateInput extends ConsoleInput {

    /**
     * Сами решите необходимо ли переопределение этого метода.
     */
    @Override
    public String askStr(String question) {

        return super.askStr(question);

    }

    public String askUsername(String question) {
        System.out.print(question);
        String username = scanner.nextLine();

        username = username.replaceAll(" ", "");

        try {
            username = username.substring(0, 1).toUpperCase() + username.substring(1);

        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Неправильный логин");
            username = askUsername("Ваш логин: ");
            return username;
        }
        return username;
    }

    /**
     * Необходимо ли переопределять метод, или убрать переопределение вы решаете сами.
     * На данный момент в этот методе может произойти непроверяемое исключение.
     */
    @Override
    public int askInt(String question) {

        String checkInt = askStr(question);
        checkInt = checkInt.replaceAll(" ", "");
        int parse;

        try {
            parse = Integer.parseInt(checkInt);

        } catch (NumberFormatException e) {
            System.out.println("Неправильный пункт меню");
            parse = askInt("Выберите пункт меню: ");
            return parse;
        }

        return parse;

    }

    /**
     * Сами решите необходимо ли переопределение этого метода.
     */
    @Override
    public long askLong(String question) {

        String checkLong = askStr(question);
        checkLong = checkLong.replaceAll(" ", "");
        long parse;

        try {
            parse = Long.parseLong(checkLong);

        } catch (NumberFormatException e) {
            System.out.println("Некоректное значение");
            parse = askLong("Повторите ввод: ");
            return parse;
        }

        return parse;
    }
}
