package w11w12;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class ShootTheAccountPlus {

    // added a checkAccount method which returns true/false based on whether they are allowed to bypass the $500 condition

  private int balance = 0;
  private List<Transaction> ListOfAllTransactions = new ArrayList<Transaction>();
  private String lastDebitTime;
  private boolean businessAccount;

  public ShootTheAccountPlus(boolean businessAccount) {
      this.businessAccount = businessAccount;
  }

  public ShootTheAccountPlus(int balance) {
    this.balance = balance;
  }

  public void deposite (int amount) {
    balance += amount;
  }

  public void setBalance (int amount) {
    balance = amount;
  }

  public int getBalance () {
    return balance;
  }

  public void addTransaction(String type, int amount){
      ListOfAllTransactions.add(new Transaction(type, amount));
  }

  public boolean checkAccount(){
      return businessAccount;
  }

  // this method has a long method smell
  public void CreditTransaction(int amount) {

	//increase the amount
	balance = balance + amount;

	//add to the transaction list
	ListOfAllTransactions.add(new Transaction("debit", amount));

  }


  // this method has a long method smell
  public void DebitTransaction(int amount) {

	  if (balance >= 500 || checkAccount()) {
		  //reduce the amount
		  balance = balance - amount;

		  //add to the transaction list
		  ListOfAllTransactions.add(new Transaction("debit", amount));

		  //update the last debit date
		  Calendar cal = Calendar.getInstance();

		  lastDebitTime = cal.get(Calendar.DATE) + "." + cal.get(Calendar.MONTH) + "." + cal.get(Calendar.YEAR);
	  }
  }

  // this method has a long method smell
  public void Transfer(int amount, ShootTheAccountPlus Benf) {

	  if (balance >= 500 || checkAccount() ) {
		  //reduce the amount
		  balance = balance - amount;

		  //add to the transaction list
		  ListOfAllTransactions.add(new Transaction("debit", amount));

		  //update the last debit date
		  Calendar cal = Calendar.getInstance();

		  lastDebitTime = cal.get(Calendar.DATE) + "." + cal.get(Calendar.MONTH) + "." + cal.get(Calendar.YEAR);

		  Benf.CreditTransaction(amount);
	  }
  }

  public void sendWarning() {
	  if (balance < 500 && !checkAccount())
		  System.out.println("Balance must be more than 500, please deposit");
  }
}
