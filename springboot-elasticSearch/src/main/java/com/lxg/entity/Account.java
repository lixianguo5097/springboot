package com.lxg.entity;

import lombok.Data;

/**
 * @author LXG
 * @date 2021-11-4
 */
@Data
public class Account {
    private int account_number;
    private int balance;
    private String firstname;
    private String lastname;
    private int age;
    private String gender;
    private String address;
    private String employer;
    private String email;
    private String city;
    private String state;
}
