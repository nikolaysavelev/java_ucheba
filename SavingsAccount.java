public class SavingsAccount extends Account implements InterestBearing { // РЕАЛИЗАЦИЯ ИНТЕРФЕЙСА InterestBearing
    private double interestRate;

    public SavingsAccount(double balance, String accountNumber, String clientName, double interestRate) { // ПЕРЕГРУЖЕННЫЙ КОНСТРУКТОР
        super(balance, accountNumber, clientName);
        if (interestRate < 0) { // Выбрасываем исключение, когда процентная ставка введена отрицательной
            throw new IllegalArgumentException("Процентная ставка не может быть отрицательной");
        }
        this.interestRate = interestRate;
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
    }

    @Override
    public double addInterest() {
        balance *= (1 + (interestRate/100));
        return Math.round(balance);
    }

    public double getInterestRate() {
        return interestRate;
    }

    // Данные методы не добавлены в Main.java, реализована логика снятия средств с сберегательного счета для демонстрации перегрузки методов
    @Override
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Сумма " + amount + " успешно снята со сберегательного счета.");
        } else {
            System.out.println("Недостаточно средств на сберегательном счете.");
        }
    }

    @Override
    public void withdraw(double percentage, boolean isPercentage) {
        if (isPercentage) {
            double amountToWithdraw = balance * (percentage / 100.0);
            if (amountToWithdraw > 0 && amountToWithdraw <= balance) {
                balance -= amountToWithdraw;
                System.out.println("Сумма " + amountToWithdraw + " (" + percentage + "% от баланса) успешно снята со сберегательного счета.");
            } else {
                System.out.println("Недостаточно средств на сберегательном счете.");
            }
        } else {
            System.out.println("Некорректные параметры для снятия средств.");
        }
    }
}
