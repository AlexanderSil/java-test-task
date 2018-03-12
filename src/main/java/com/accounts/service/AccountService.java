package com.accounts.service;

import com.accounts.dao.AccountDao;
import com.accounts.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by alex on 3/10/18.
 */
@Service
public class AccountService {

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private ExternalConnectionService externalConnectionService;

    public List<Account> getAllAccount() {
        return accountDao.findAll();
    }

    public Optional<Account> getById(long id) {
        Optional<Account> account = accountDao.findById(id);

        if (account.isPresent())
            account.get().setAccountDetail(externalConnectionService.getAccountDetail(account.get()));

        return account;
    }

    public void deleteById(long id) {
        accountDao.deleteById(id);
    }

    public Account save(Account account) {
        return accountDao.save(account);
    }

}
