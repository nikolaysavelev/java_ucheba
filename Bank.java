import java.io.*;
import java.util.ArrayList;

public class Bank {
    private ArrayList<Account> accounts;

    public Bank() {
        accounts = new ArrayList<>();
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public void displayAllAccounts() { // ПРИМЕР ПОЛИМОРФИЗМА
        for (Account account : accounts) {
            System.out.print("Клиент: " + account.getClientName() +
                    ", Счет: " + account.getAccountNumber() +
                    ", Баланс: " + account.getBalance());

            if (account instanceof SavingsAccount savingsAccount) {
                System.out.println(", Ставка: " + savingsAccount.getInterestRate());
            } else {
                System.out.println();
            }
        }
    }

// ДЕМОНСТРАЦИЯ СОХРАНЕНИЯ В ФАЙЛ
    public void saveToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Account account : accounts) {
                if (account instanceof SavingsAccount) {
                    SavingsAccount savingsAccount = (SavingsAccount) account;
                    writer.write("1, Клиент: " + savingsAccount.getClientName() +
                            ", Сберсчет: " + savingsAccount.getAccountNumber() +
                            ", Баланс: " + savingsAccount.getBalance() +
                            ", Ставка: " + savingsAccount.getInterestRate());
                } else if (account instanceof CheckingAccount) {
                    CheckingAccount checkingAccount = (CheckingAccount) account;
                    writer.write("2, Клиент: " + checkingAccount.getClientName() +
                            ", Счет: " + checkingAccount.getAccountNumber() +
                            ", Баланс: " + checkingAccount.getBalance());
                }
                writer.newLine();
            }
        } catch (IOException e) { // Общее исключение, которое может возникнуть при работе с файлами, вводом-выводом.
            e.printStackTrace();
        }
    }

    // ДЕМОНСТРАЦИЯ ВЫЧИТКИ ИЗ ФАЙЛА
    public void loadFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                int accountType = Integer.parseInt(parts[0]); // Получаем тип аккаунта
                String clientName = parts[1].split(": ")[1];
                String accountNumber = parts[2].split(" ")[1];
                try {
                    double balance = Double.parseDouble(parts[3].split(" ")[1]);
                    if (accountType == 1) { // Если тип аккаунта 1, то это сберегательный счет
                        double interestRate = Double.parseDouble(parts[4].split(" ")[1]); // Получаем процентную ставку
                        addAccount(new SavingsAccount(balance, accountNumber, clientName, interestRate));
                    } else if (accountType == 2) { // Если тип аккаунта 2, то это обычный счет
                        addAccount(new CheckingAccount(balance, accountNumber, clientName));
                    }
                } catch (NumberFormatException e) { // Выкидываю исключение, если формат поля Баланс в файле неверный
                    System.out.println("Неверный формат поля Баланс в файле");
                }
            }
        // ИСКЛЮЧЕНИЯ
        } catch (FileNotFoundException e) { // Выкидываю исключение, если файл с таким именем не найден
            System.out.println("Файл с данными не найден: " + e.getMessage());
        } catch (IOException e) { //  Общее исключение, которое может возникнуть при работе с файлами, вводом-выводом.
            e.printStackTrace();
        }
    }
}
