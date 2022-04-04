package ru.job4j.bank;

import java.util.*;

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
        Optional<User> rsl  = findByPassport(passport);
        if (rsl.isPresent()) {
            List<Account> accounts = users.get(rsl.get());
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
    public Optional<User> findByPassport(String passport) {
        return users.keySet().stream()
                .filter((el -> el.getPassport().equals(passport)))
                .findFirst();
    }

    /**
     * метод производит поиск счета в системе
     * по паспорту клиента и реквизитам счета,
     * @param passport - паспорт клиента
     * @param requisite - реквизиты счета
     * @return - счет клиента. Eсли клиента нет в системе,
     * либо данного счета у найденного клиента нет - null
     */
    public Optional<Account> findByRequisite(String passport, String requisite) {
        Optional<Account> res = Optional.empty();
        Optional<User> finded = findByPassport(passport);
        if (finded.isPresent()) {
            res = users.get(finded.get())
                    .stream()
                    .filter(el -> el.getRequisite().equals(requisite))
                    .findFirst();

        }
        return res;
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
        Optional<Account> src = findByRequisite(srcPassport, srcRequisite);
        Optional<Account> dest = findByRequisite(destPassport, destRequisite);
        if (src.isPresent() && dest.isPresent() && src.get().getBalance() >= amount) {
            src.get().setBalance(src.get().getBalance() - amount);
            dest.get().setBalance(dest.get().getBalance() + amount);
            rsl = true;
        }
        return rsl;
    }
}
