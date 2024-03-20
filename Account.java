public abstract class Account { // АБСТРАКТНЫЙ КЛАСС
    protected double balance;
    protected String accountNumber;
    protected String clientName;

    public Account(double balance, String accountNumber, String clientName) { // КОНСТРУКТОР
        this.balance = balance;
        this.accountNumber = accountNumber;
        this.clientName = clientName;
    }

    // ПЕРЕГРУЖЕННЫЙ МЕТОД ДЛЯ СНЯТИЯ ФИКСИРОВАННОЙ СУММЫ
    public abstract void withdraw(double amount);

    // ПЕРЕГРУЖЕННЫЙ МЕТОД ДЛЯ СНЯТИЯ ПРОЦЕНТА ОТ СУММЫ
    public abstract void withdraw(double percentage, boolean isPercentage);

    // АБСТРАКТНЫЙ МЕТОД
    public abstract void deposit(double amount);

    public double getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getClientName() {
        return clientName;
    }
}
