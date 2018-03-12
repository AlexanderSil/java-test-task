package com.accounts.dao;

import com.accounts.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by alex on 3/10/18.
 */
@Repository
public interface AccountDao extends JpaRepository <Account, Long> {

    default Account findByAccountName(String name) {
        for (Account account : findAll()) {
            if (account.getAccountName().equals(name))
                return account;
        }
        return null;
    };

}
