package ru.skillfactory;

import java.util.Objects;

/**
 * Модель данных для работы с пользователем и его счётом.
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
     * Баланс
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
     * Аккаунт со всеми полями.
     *
     * @param username  Строка в произвольной форме.
     * @param password  Строка в произвольной форме.
     * @param requisite Строка в произвольной форме.
     * @param balance   лонг
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
