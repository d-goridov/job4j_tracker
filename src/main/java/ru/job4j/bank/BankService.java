package ru.job4j.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс описывает банковский сервис
 * За основу взята структура HashMap,
 * в которой ключом является клиент,
 * а значение - список его счетов.
 * @author Dmitriy Goridov
 * @version 1.0
 */
public class BankService {
    private final Map<User, List<Account>> users = new HashMap<>();

    /**
     * метод добавляет клиента в банковский сервис
     * если его профиля еще нет
     * @param user - профиль клиента
     */
    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<Account>());
    }

    /**
     * метод добавляет счет клиенту, если
     * его профиль уже имеется в системе.
     * Также проверяется что такого счета у клиента еще нет.
     * @param passport - паспорт клиента
     * @param account - счет для добавления в профиль клиента
     */
    public void addAccount(String passport, Account account) {
        User user = findByPassport(passport);
        if (user != null) {
            List<Account> accounts = users.get(user);
            if (!accounts.contains(account)) {
                accounts.add(account);
            }
        }
    }

    /**
     * метод производит поиск профиля клиента
     * в системе по паспорту
     * @param passport - паспорт искомого клиента
     * @return профиль клиента, если он есть в системе,
     * в обратном случае - null
     */
    public User findByPassport(String passport) {
        return users.keySet().stream()
                .filter((el -> el.getPassport().equals(passport)))
                .findFirst()
                .orElse(null);
    }

    /**
     * метод производит поиск счета в системе
     * по паспорту клиента и реквизитам счета,
     * @param passport - паспорт клиента
     * @param requisite - реквизиты счета
     * @return - счет клиента. Eсли клиента нет в системе,
     * либо данного счета у найденного клиента нет - null
     */
    public Account findByRequisite(String passport, String requisite) {
        User finded = findByPassport(passport);
        if (finded != null) {
            return users.get(finded).stream()
                    .filter((el) -> el.getRequisite().equals(requisite))
                    .findFirst()
                    .orElse(null);
        }
        return null;
    }

    /**
     * метод описывает перевод средств со счета на счет
     * Производится проверка существования счетов отправителя и получателя
     * а также наличие требуемых средств для перевода у отправителя.
     * @param srcPassport - паспорт клиента отправителя средств
     * @param srcRequisite - реквизиты счета отправителя
     * @param destPassport - паспорт клиента получателя средств
     * @param destRequisite - реквизиты счета получателя
     * @param amount - сумма перевода
     * @return - true, если перевод произведен, в противном случае false
     */
    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {

        boolean rsl = false;
        Account src = findByRequisite(srcPassport, srcRequisite);
        Account dest = findByRequisite(destPassport, destRequisite);
        if (src != null && dest != null && src.getBalance() >= amount) {
            src.setBalance(src.getBalance() - amount);
            dest.setBalance(dest.getBalance() + amount);
            rsl = true;
        }
        return rsl;
    }
}
