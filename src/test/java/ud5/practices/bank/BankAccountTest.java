package ud5.practices.bank;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import ud5.practices.bank.BankAccount;

@DisplayName("BankAccountTest")
class BankAccountTest {

    @Nested
    @DisplayName("Creando una cuenta Bancaria")
    class CreatingBankAccount {

        @Test
        @DisplayName("Crear una cuenta bancaria con el número de cuenta y el nombre de su dueño")
        void createAccountWithAccountNumberAndOwnerName() {
            BankAccount account = new BankAccount("1234567890", "Julio Climent");
            assertEquals("1234567890", account.getAccountNumber());
            assertEquals("Julio Climent", account.getOwnerName());
            assertEquals(0.0, account.getBalance());
        }

        @Test
        @DisplayName("Crear una cuenta bancaria con el número de cuenta, el nombre de su dueño y su saldo inicial")
        void createAccountWithAccountNumberOwnerNameAndInitialBalance() {
            BankAccount account = new BankAccount("0987654321", "Rogelio Tárrega", 100.0);
            assertEquals("0987654321", account.getAccountNumber());
            assertEquals("Rogelio Tárrega", account.getOwnerName());
            assertEquals(100.0, account.getBalance());
        }
    }

    @Nested
    @DisplayName("Depóstio de dinero")
    class DepositingMoney {

        @Test
        @DisplayName("Depositar dinero en la cuenta")
        void depositMoneyIntoAccount() {
            BankAccount account = new BankAccount("1234567890", "Julio Climent");
            assertTrue(account.deposit(50.0));
            assertEquals(50.0, account.getBalance());
        }

        @Test
        @DisplayName("No depositar dinero negativo en la cuenta")
        void notDepositNegativeAmount() {
            BankAccount account = new BankAccount("1234567890", "Julio Climent");
            assertFalse(account.deposit(-50.0));
            assertEquals(0.0, account.getBalance());
        }

        @Test
        @DisplayName("No depositar 0 en la cuenta")
        void notDepositZeroAmount() {
            BankAccount account = new BankAccount("1234567890", "Julio Climent");
            assertFalse(account.deposit(0.0));
            assertEquals(0.0, account.getBalance());
        }
    }

    @Nested
    @DisplayName("Retirando dinero de la cuenta")
    class WithdrawingMoney {

        @Test
        @DisplayName("Retirar dinero de la cuenta")
        void withdrawMoneyFromAccount() {
            BankAccount account = new BankAccount("1234567890", "Julio Climent", 100.0);
            assertTrue(account.withdraw(50.0));
            assertEquals(50.0, account.getBalance());
        }

        @Test
        @DisplayName("No retirar dinero negativo de la cuenta")
        void notWithdrawNegativeAmount() {
            BankAccount account = new BankAccount("1234567890", "Julio Climent", 100.0);
            assertFalse(account.withdraw(-50.0));
            assertEquals(100.0, account.getBalance());
        }

        @Test
        @DisplayName("No retirar 0 de la cuenta")
        void notWithdrawZeroAmount() {
            BankAccount account = new BankAccount("1234567890", "Julio Climent", 100.0);
            assertFalse(account.withdraw(0.0));
            assertEquals(100.0, account.getBalance());
        }

        @Test
        @DisplayName("No retirar mas dinero del saldo disponible")
        void notWithdrawMoreThanAvailableBalance() {
            BankAccount account = new BankAccount("1234567890", "Julio Climent", 100.0);
            assertFalse(account.withdraw(150.0));
            assertEquals(100.0, account.getBalance());
        }
    }

    @Nested
    @DisplayName("Transferir dinero entre diferentes cuentas")
    class TransferringMoney {

        @Test
        @DisplayName("Transferir dinero entre las cuentas")
        void transferMoneyBetweenAccounts() {
            BankAccount source = new BankAccount("1234567890", "Julio Climent", 100.0);
            BankAccount destination = new BankAccount("0987654321", "Rogelio Tárrega");
            assertTrue(source.transfer(destination, 50.0));
            assertEquals(50.0, source.getBalance());
            assertEquals(50.0, destination.getBalance());
        }

        @Test
        @DisplayName("No transferir dinero negativo entre las cuentas")
        void notTransferNegativeAmount() {
            BankAccount source = new BankAccount("1234567890", "Julio Climent", 100.0);
            BankAccount destination = new BankAccount("0987654321", "Rogelio Tárrega");
            assertFalse(source.transfer(destination, -50.0));
            assertEquals(100.0, source.getBalance());
            assertEquals(0.0, destination.getBalance());
        }

        @Test
        @DisplayName("No transferir 0 entre las cuentas")
        void notTransferZeroAmount() {
            BankAccount source = new BankAccount("1234567890", "Julio Climent", 100.0);
            BankAccount destination = new BankAccount("0987654321", "Rogelio Tárrega");
            assertFalse(source.transfer(destination, 0.0));
            assertEquals(100.0, source.getBalance());
            assertEquals(0.0, destination.getBalance());
        }

        @Test
        @DisplayName("No tranferir mas dinero del saldo disponible de la cuenta principal")
        void notTransferMoreThanAvailableBalanceFromSourceAccount() {
            BankAccount source = new BankAccount("1234567890", "Julio Climent", 100.0);
            BankAccount destination = new BankAccount("0987654321", "Rogelio Tárrega");
            assertFalse(source.transfer(destination, 150.0));
            assertEquals(100.0, source.getBalance());
            assertEquals(0.0, destination.getBalance());
        }
    }
}