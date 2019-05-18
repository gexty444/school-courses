package com.example.C3Account;

public class CheckingAccount extends Account{
    public double overdraft = 5000;



    public CheckingAccount(int id,double balance){
        super(id,balance);
//        this.setId(id);
//        this.setBalance(balance);
    }

    public void withdraw(double amount){
        if (super.getBalance() - amount < -overdraft){
            System.out.println("over limit");
        }
        else{
            super.withdraw(amount);
        }
    }
}
