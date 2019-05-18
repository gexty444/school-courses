package w4;//package Week4;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AccountTest { 
   @Test
   public void Compare () {         // always pass unless Account() class raises an exception
	   Account a = new Account(); 
	   Account b = new Account(); 
	   b.compare(a);
   }

   @Test
   public void testCalAmount() {    // testing function calAmount()
	   Account a = new Account(); 
	   a.setBalance(10);
	   int amount = a.calAmount();
	   assertTrue(amount == 40);
   }
   
   @Test
   public void testSetupAccount() {
	   Account a = new Account(); 
   }    // test constructor
   
   @Test
   public void testWithDraw() {
	   Account a = new Account(); 
	   a.setBalance(100);
	   boolean success = a.withdraw(1000);          // testing if account class allows withdrawing more than balance
	   assertFalse(success);
   }
}
