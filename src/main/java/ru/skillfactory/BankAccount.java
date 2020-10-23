package ru.skillfactory;

import java.util.Objects;

/**
 * Модель данных для работы с пользователем и его счётом (не больше одного).
 * Класс написан для примера, счёт и пользователя логичнее выносить отдельными сущностями.
 */
public class BankAccount {
    /**
     * Строка в произвольной форме.
     */
    private String username;
    /**
     * Строка в произвольной форме.
     */
    private String password;
    /**
     * Баланс (пусть будет в копейках, при печати можете в рубли переводить, для других валют аналогично).
     * Почему long? - Прочитайте тему, вдруг когда-нибудь спросят.
     *
     * https://ru.stackoverflow.com/questions/667706/%D0%92-%D1%87%D0%B5%D0%BC-%D1%85%D1%80%D0%B0%D0%BD%D0%B8%D1%82%D1%8C-%D0%B4%D0%B5%D0%BD%D1%8C%D0%B3%D0%B8-float-double
     */
    private long balance;
    /**
     * Строка в произвольной форме, используется для поиска получателя перевода. Реквизиты не должны изменяться -
     * хотите изменить пересоздайте аккаунт.
     */
    private String requisite;

    public BankAccount() {
    }

    /**
     * Аккаунт со всеми полями. Можно создать другие, но смотрите чтобы в BankService позже не было проблем
     * с аккаунтами у которых не все поля инициализированные.
     *
     * @param username Строка в произвольной форме.
     * @param password Строка в произвольной форме.
     * @param requisite Строка в произвольной форме.
     */

    public BankAccount(String username, String password, String requisite, long balance) {
        this.username = username;
        this.password = password;
        this.requisite = requisite;
        this.balance = balance;
    }


    /**
     * Геттер password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Сеттер password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Геттер username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Сеттер username.
     */
    public void setUsername(String username) {
        this.username = username;
    }


    /**
     * Геттер password.
     */
    public String getRequisite() {
        return requisite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BankAccount that = (BankAccount) o;
        if (!Objects.equals(username, that.username)) {
            return false;
        }
        return Objects.equals(requisite, that.requisite);
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (requisite != null ? requisite.hashCode() : 0);
        return result;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }
}
