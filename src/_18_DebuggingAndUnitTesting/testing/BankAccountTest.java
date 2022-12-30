package _18_DebuggingAndUnitTesting.testing;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.TestCase.*;

public class BankAccountTest {

    private BankAccount account;
    private static int count;

    @BeforeClass
    public static void start() throws Exception {
        System.out.println("��� ���������� ����� �������� ����� ������ = " + count++);
    }

    @AfterClass
    public static void end() throws Exception {
        System.out.println("��� ����������� ����� ���������� �������� ������� ������ = " + count++);
    }

    @org.junit.Before
    public void setup() throws Exception {
        account = new BankAccount("������", "�����", 1000.00, BankAccount.CHECKING);
        System.out.println("��������� ���������� � ����� ����������� ������� = " + count++);
    }

    @org.junit.After
    public void after() throws Exception {
        System.out.println("��������� �������� ����� ����������� ������� = " + count++);
    }


    @org.junit.Test
    public void deposit() throws Exception {
        double balance = account.deposit(200.00, true);
        assertEquals(1200.00, balance, 0);
    }

    @org.junit.Test
    public void withdraw_branch() {
        double balance = account.withdraw(600.00, true);
        assertEquals(400.00, balance, 0);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void withdraw_notBranch() {
        double balance = account.withdraw(600.00, false);
        fail("������ ���� thrown an InlegalFrgumentExeption");

    }

    @org.junit.Test //(expected = IllegalArgumentException.class)
    public void withdraw_notBranchWithTry() {
        try {
            double balance = account.withdraw(600.00, false);
            fail("������ ���� thrown an InlegalFrgumentExeption");
        } catch (IllegalArgumentException e) {

        }

    }

    @org.junit.Test
    public void getBalance_deposit() {
        double balance = account.deposit(200.00, true);
        assertEquals(1200.00, account.getBalane(), 0);
    }

    @org.junit.Test
    public void getBalance_withdraw() {
        double balance = account.withdraw(200.00, true);
        assertEquals(800.00, account.getBalane(), 0);
    }


    @org.junit.Test
    public void isCheking_true() {
        BankAccount account = new BankAccount("�����", "�����", 1000.00, BankAccount.CHECKING);
        assertEquals("���� ������� �� �������� ������", BankAccount.CHECKING == 1, account.isChecking());

    }
}