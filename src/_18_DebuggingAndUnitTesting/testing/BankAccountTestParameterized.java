package _18_DebuggingAndUnitTesting.testing;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static junit.framework.TestCase.assertEquals;

@RunWith(Parameterized.class)
public class BankAccountTestParameterized {
    private BankAccount account;
    private double amount;
    private boolean branch;
    private double expected;

    public BankAccountTestParameterized(double amount, boolean branch, double expected) {
        this.amount = amount;
        this.branch = branch;
        this.expected = expected;
    }
    @org.junit.Test
    public void getBalance_deposit() throws  Exception{
        double balance = account.deposit(amount, branch);
        assertEquals(expected, account.getBalane(), .0001);
    }

    @org.junit.Before
    public void setup(){
        account = new BankAccount("Никита", "Фокин", 1000.00, BankAccount.CHECKING);
        System.out.println("запуск теста ");
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testConditions(){
        return Arrays.asList(new Object[][]{
                {100.00,true,1100.00},
                {200.00,true,1200.00},
                {325.14,true,1325.14},
                {489.33,true,1489.33},
                {1000.00,true,2000.00}
        });
    }
}
