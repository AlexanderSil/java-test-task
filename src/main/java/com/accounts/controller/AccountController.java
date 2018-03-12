package com.accounts.controller;

import com.accounts.model.Account;
import com.accounts.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.security.auth.login.AccountNotFoundException;
import java.net.URI;
import java.util.List;
import java.util.Optional;

/**
 * Created by alex on 3/10/18.
 */
@RestController
@RequestMapping(value = "/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("")
    public List<Account> getAllAccounts() {
        return accountService.getAllAccount();
    }


    @GetMapping("/{id}")
    public Account getAccount(@PathVariable long id) throws AccountNotFoundException {
        Optional<Account> account = accountService.getById(id);

        if (!account.isPresent())
            throw new AccountNotFoundException("id-" + id);

        return account.get();
    }

    @DeleteMapping("/{id}")
    public void deleteAccount(@PathVariable long id) {
        accountService.deleteById(id);
    }

    @PostMapping("/")
    public ResponseEntity<Object> createAccount(@RequestBody Account account) {
        Account savedAccount = accountService.save(account);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedAccount.getId()).toUri();

        return ResponseEntity.created(location).build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAccount(@RequestBody Account account, @PathVariable long id) {

        Optional<Account> accountOptional = accountService.getById(id);

        if (!accountOptional.isPresent())
            return ResponseEntity.notFound().build();

        account.setId(id);
        accountService.save(account);
        return ResponseEntity.noContent().build();
    }
}
