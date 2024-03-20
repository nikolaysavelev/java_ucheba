import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InputMismatchException {
        Scanner scanner = new Scanner(System.in);
        Bank bank = new Bank();
        String loadFromFile;
        while (true) {
            System.out.print("Хотите загрузить данные счетов из файла? (Да/Нет): ");
            try {
                loadFromFile = scanner.nextLine();
                break;
            } catch (InputMismatchException e) {
                throw new InputMismatchException("Неверный ввод. Введите Да или Нет");
            }
        }
            if (loadFromFile.equalsIgnoreCase("Да")) {
                bank.loadFromFile("accounts.txt");
                bank.displayAllAccounts();
            } else if (loadFromFile.equalsIgnoreCase("Нет")) {
                System.out.print("Введите имя клиента: ");
                String clientName = scanner.nextLine();

                System.out.print("Введите номер счета: ");
                String accountNumber = scanner.nextLine();

                System.out.println("Введите тип счета:");
                System.out.println("1 - Если счет сберегательный");
                System.out.println("2 - Если счет обычный");
                int accountTypeChoice = scanner.nextInt();
                scanner.nextLine();

                if (accountTypeChoice == 1) {
                    System.out.print("Введите процентную ставку (годовые): ");
                    double interestRate = scanner.nextDouble();
                    scanner.nextLine();

                    SavingsAccount savingsAccount = new SavingsAccount(0, accountNumber, clientName, interestRate);
                    savingsAccount.deposit(200); // Тестируем, добавляем 200 условных единиц на счет
                    bank.addAccount(savingsAccount);
                    bank.displayAllAccounts();
                    bank.saveToFile("accounts.txt");
                    double amountAfterYear = savingsAccount.addInterest();
                    System.out.println("С такой процентной ставкой на счете через год будет: " + amountAfterYear);
                } else if (accountTypeChoice == 2) {
                    CheckingAccount checkingAccount = new CheckingAccount(0, accountNumber, clientName);
                    checkingAccount.deposit(500); // Тестируем, добавляем 500 условных единиц на счет
                    bank.addAccount(checkingAccount);
                    bank.displayAllAccounts();
                    bank.saveToFile("accounts.txt");
                } else {
                    System.out.println("Неверный выбор.");
                }
            }


    }
}