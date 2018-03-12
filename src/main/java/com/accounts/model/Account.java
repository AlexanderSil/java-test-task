package com.accounts.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;

/**
 * Created by alex on 3/10/18.
 */
@Entity
public class Account implements Serializable {
    public Account(){}

    @Id
    @GeneratedValue
    private Long id;

    private String accountName;

    private String accountPassword;

    @Transient
    private String accountDetail;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountPassword() {
        return accountPassword;
    }

    public void setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
    }

    public String getAccountDetail() {
        return accountDetail;
    }

    public void setAccountDetail(String accountDetail) {
        this.accountDetail = accountDetail;
    }
}
