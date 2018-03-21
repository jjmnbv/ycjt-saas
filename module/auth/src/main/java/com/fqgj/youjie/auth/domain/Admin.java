package com.fqgj.youjie.auth.domain;

import com.fqgj.common.domain.Account;
import com.fqgj.common.domain.Mobile;
import com.fqgj.common.domain.Password;
import com.fqgj.youjie.auth.enums.AdminTypeEnum;


/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (mr.vencnet@gmail.com)
 * Date: 2017/3/11
 * Time: 上午11:39
 */
public class Admin {
    private Long id;

    private Account account;

    private Password password;

    private Mobile mobile;

    private String name;

    private String phone;

    private AdminTypeEnum adminTypeEnum;

    public Admin() {
    }

    public Admin(String account, String password) {
        this.account = new Account(account);
        this.password = new Password(password);
    }

    public Admin(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public Admin setId(Long id) {
        this.id = id;
        return this;
    }

    public Account getAccount() {
        return account;
    }

    public Admin setAccount(Account account) {
        this.account = account;
        return this;
    }

    public Password getPassword() {
        return password;
    }

    public Admin setPassword(Password password) {
        this.password = password;
        return this;
    }

    public Mobile getMobile() {
        return mobile;
    }

    public Admin setMobile(Mobile mobile) {
        this.mobile = mobile;
        return this;
    }

    public String getName() {
        return name;
    }

    public Admin setName(String name) {
        this.name = name;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public Admin setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public AdminTypeEnum getAdminTypeEnum() {
        return adminTypeEnum;
    }

    public Admin setAdminTypeEnum(AdminTypeEnum adminTypeEnum) {
        this.adminTypeEnum = adminTypeEnum;
        return this;
    }
}
