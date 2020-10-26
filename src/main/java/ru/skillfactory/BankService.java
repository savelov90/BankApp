package ru.skillfactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;

import static java.util.Optional.ofNullable;

/**
 * BankService - класс, который нарушает принцип единственной ответственности. У нас он сразу
 * и хранит аккаунты, и реализует логику проверки баланса и переводов.
 */
public class BankService {
    /**
     * В Map-е храните аккаунты, ключ это реквизиты
     * (реквизиты у аккаунтов неизменяемые, это важно сохранить чтобы ключ в map всегда был такой же как у аккаунта)
     */
    private final Map<String, BankAccount> accounts = new HashMap<>();

    /**
     * Метод добавляете аккаунт в Map-у, если у аккаунта уникальные реквизиты.
     * Используется Map.putIfAbsent. Этот метод добавляет значание в map если ключ в map ранее не был.
     *
     * @param account Аккаунт с заполненными полями.
     */
    public void addAccount(BankAccount account) {
        accounts.putIfAbsent(account.getRequisite(), account);
    }

    /**
     * Метод проверяет что в Map-е есть аккаунт, если есть вернёт реквизиты.
     *
     * @param username валидная строка.
     * @param password валидная строка.
     * @return возвращает объект Optional, который будет содержат строку - requisite,
     * если передоваемого пользователя нет или пароль не совпадает, то
     * передаем пустой объект Optional и проверяем, что он пуст.
     */
    public Optional<String> getRequisiteIfPresent(String username, String password) {

//        if (username != null && password != null) {
        Optional<String> optional = ofNullable(null);

        for (Map.Entry<String, BankAccount> entry : accounts.entrySet()) {
            BankAccount check = entry.getValue();
            if (check.getUsername().equals(username) && check.getPassword().equals(password)) {
                optional = ofNullable(entry.getKey());
                break;
            } else {
                optional = ofNullable(null);
            }

        }
        return optional;
    }


    /**
     * Метод кол-во средств на передаваемых реквизитах.
     *
     * @param requisite реквизиты, строка в произвольном формате.
     * @return кол-во средств.
     */
    public long balance(String requisite) {
        long currentBalance = 0L;
        for (Map.Entry<String, BankAccount> entry : accounts.entrySet()) {

            if (entry.getKey().equals(requisite)) {
                BankAccount check = entry.getValue();
                currentBalance = check.getBalance();
                break;
            }
        }
        return currentBalance;
    }


    /**
     * Метод пополняет баланс.
     *
     * @param requisite реквизиты, строка в произвольном формате.
     * @param amount    сумма для пополнения.
     * @return возвращает true если баланс был увеличен.
     */
    public boolean topUpBalance(String requisite, long amount) {

        boolean done = false;
        for (Map.Entry<String, BankAccount> entry : accounts.entrySet()) {
            if (entry.getKey().equals(requisite)) {
                BankAccount check = entry.getValue();
                check.setBalance(check.getBalance() + amount);
                done = true;
                System.out.println("Баланс пополнен, текущий баланс: " + check.getBalance() + " рублей");
                break;
            } else {
                done = false;
            }
        }
        return done;
    }


    /**
     * Метод, если все условия соблюдены, переводит средства с одного счёта на другой.
     *
     * @param srcRequisite  реквизиты, строка в произвольном формате.
     * @param destRequisite реквизиты, строка в произвольном формате.
     * @param amount        кол-во средств.
     * @return true если выполнены все условия, средства фактически переведены.
     */
    public boolean transferMoney(String srcRequisite, String destRequisite, long amount) {


        boolean srcDone = false;
        boolean destDone = false;
        boolean checkAmm;
        BankAccount check = new BankAccount();
        BankAccount checkDest = new BankAccount();

        //проверка наличия пользователя, выполняющего перевод денег

        for (Map.Entry<String, BankAccount> entry : accounts.entrySet()) {
            if (entry.getKey().equals(srcRequisite)) {
                check = entry.getValue();
                srcDone = true;
                break;
            } else {
                srcDone = false;
            }
        }

        //проверка наличия пользователя, которому переводят деньги

        for (Map.Entry<String, BankAccount> entryDest : accounts.entrySet()) {
            if (entryDest.getKey().equals(destRequisite)) {
                checkDest = entryDest.getValue();
                destDone = true;
                break;
            } else {
                destDone = false;
            }
        }

        //проверка наличия денежных средств на балансе пользователя

        if (amount <= check.getBalance()) {
            check.setBalance(check.getBalance() - amount);
            checkDest.setBalance(checkDest.getBalance() + amount);
            System.out.println("Перевод выполнен, текущий баланс: " + check.getBalance() + " рублей");
            checkAmm = true;
        } else {
            checkAmm = false;
        }

        if (!destDone) {
            System.out.println("Неправильные реквизиты получателя, перевод не выполнен");
        } else if (!checkAmm) {
            System.out.println("Недостаточно средств...");
        }

        return srcDone & destDone & checkAmm; //если все условия соблюдены, то возвращаю true
    }
}



